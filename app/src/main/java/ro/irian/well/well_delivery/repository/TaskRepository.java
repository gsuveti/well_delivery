package ro.irian.well.well_delivery.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import ro.irian.well.well_delivery.domain.Task;

@Singleton
public class TaskRepository {

    private static final String TAG = "TaskRepository";
    private final FirebaseFirestore firebaseFirestore;
    private final CollectionReference collection;

    @Inject
    public TaskRepository(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
        this.collection = firebaseFirestore.collection("tasks");
    }

    public LiveData<List<Task>> getTaskLiveData() {
        return this.getTaskLiveData(null);
    }

    public LiveData<List<Task>> getTaskListLiveDataByRouteID(String routeID) {
        return this.getTaskLiveData(this.collection.whereEqualTo("routeID", routeID));
    }

    public LiveData<List<Task>> getTaskLiveData(Query query) {
        final ListenerRegistration[] registration = {null};

        LiveData<List<Task>> liveData = new LiveData<List<Task>>() {
            @Override
            protected void onActive() {
                super.onActive();
                EventListener<QuerySnapshot> querySnapshotEventListener = (querySnapshot, e) -> {
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        return;
                    }
                    List<Task> tasks = querySnapshot.getDocuments().stream().map(documentSnapshot -> {
                        Task task = documentSnapshot.toObject(Task.class);
                        task.setId(documentSnapshot.getId());
                        return task;
                    }).collect(Collectors.toList());
                    setValue(tasks);
                };
                Query queryOrCollection = (query != null) ? query : collection;
                registration[0] = queryOrCollection.addSnapshotListener(querySnapshotEventListener);
            }

            @Override
            protected void onInactive() {
                super.onInactive();
                registration[0].remove();
            }
        };

        return liveData;
    }
}
