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

import ro.irian.well.well_delivery.domain.Route;

@Singleton
public class RouteRepository {
    private static final String TAG = "RouteRepository";
    private final CollectionReference collection;

    @Inject
    public RouteRepository(FirebaseFirestore firebaseFirestore) {
        this.collection = firebaseFirestore.collection("routes");
        Log.d(TAG, "RouteRepository");
    }

    public LiveData<List<Route>> getRouteLiveData() {
        return this.getRouteLiveData(null);
    }

    public LiveData<List<Route>> getRouteLiveDataByUser(String driverID) {
        return this.getRouteLiveData(this.collection.whereEqualTo("driverID", driverID));
    }

    public LiveData<List<Route>> getRouteLiveData(Query query) {
        final ListenerRegistration[] registration = {null};

        LiveData<List<Route>> liveData = new LiveData<List<Route>>() {
            @Override
            protected void onActive() {
                super.onActive();
                EventListener<QuerySnapshot> querySnapshotEventListener = (querySnapshot, e) -> {
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        return;
                    }
                    List<Route> routes = querySnapshot.getDocuments().stream().map(documentSnapshot -> {
                        Route route = documentSnapshot.toObject(Route.class);
                        route.setId(documentSnapshot.getId());
                        return route;
                    }).collect(Collectors.toList());
                    setValue(routes);
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
