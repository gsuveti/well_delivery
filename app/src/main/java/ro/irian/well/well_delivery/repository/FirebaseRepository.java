package ro.irian.well.well_delivery.repository;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public abstract class FirebaseRepository<T extends BaseEntity> implements Repository<T, Query> {
    protected final CollectionReference collection;
    private final Class<T> typeParameterClass;

    protected FirebaseRepository(CollectionReference collection, Class<T> typeParameterClass) {
        this.collection = collection;
        this.typeParameterClass = typeParameterClass;
    }

    @Override
    public Flowable<T> getOne(String ID) {
        Observable<T> observable = Observable.create(emitter -> {
            this.collection.document(ID).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                        emitter.onNext(mapToEntity(documentSnapshot));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new Exception("No such document"));
                    }
                } else {
                    emitter.onError(task.getException());
                }
            });
        });
        return observable.toFlowable(BackpressureStrategy.BUFFER);
    }


    @Override
    public Flowable<List<T>> getList(Query query) {
        Observable<List<T>> observable = Observable.create(emitter -> {
            Query queryOrCollection = (query != null) ? query : collection;
            queryOrCollection.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    List<T> list = task.getResult().getDocuments().stream()
                            .map(this::mapToEntity)
                            .collect(Collectors.toList());
                    emitter.onNext(list);
                    emitter.onComplete();
                } else {
                    emitter.onError(task.getException());
                }
            });
        });
        return observable.toFlowable(BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<List<T>> getList() {
        return getList(null);
    }


    @Override
    public Flowable<T> observeOne(String ID) {
        Observable<T> observable = Observable.create(emitter -> {
            ListenerRegistration listenerRegistration = this.collection.document(ID).addSnapshotListener(
                    (DocumentSnapshot documentSnapshot, FirebaseFirestoreException exception) -> {
                        if (exception != null) {
                            emitter.onError(exception);
                        } else {
                            emitter.onNext(this.mapToEntity(Objects.requireNonNull(documentSnapshot)));
                        }
                    });
            emitter.setCancellable(listenerRegistration::remove);
        });
        return observable.toFlowable(BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<List<T>> observeList(Query query) {
        Observable<List<T>> observable = Observable.create(emitter -> {
            Query queryOrCollection = (query != null) ? query : collection;
            ListenerRegistration listenerRegistration = queryOrCollection.addSnapshotListener(
                    (QuerySnapshot querySnapshot, FirebaseFirestoreException exception) -> {
                        if (exception != null) {
                            emitter.onError(exception);
                        } else {
                            List<T> list = Objects.requireNonNull(querySnapshot).getDocuments()
                                    .stream()
                                    .map(this::mapToEntity)
                                    .collect(Collectors.toList());
                            emitter.onNext(list);
                        }
                    });
            emitter.setCancellable(listenerRegistration::remove);
        });
        return observable.toFlowable(BackpressureStrategy.BUFFER);
    }


    @Override
    public Flowable<List<T>> observeList() {
        return observeList(null);
    }

    private T mapToEntity(DocumentSnapshot documentSnapshot) {
        T entity = documentSnapshot.toObject(typeParameterClass);
        entity.setId(documentSnapshot.getId());
        return entity;
    }
}
