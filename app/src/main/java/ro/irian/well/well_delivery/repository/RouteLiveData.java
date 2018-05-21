package ro.irian.well.well_delivery.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import ro.irian.well.well_delivery.domain.Route;

@Singleton
public class RouteLiveData extends LiveData<List<Route>> {

    private static final String TAG = "RouteLiveData";


    private CollectionReference collectionReference;

    private FirebaseFirestore firebaseFirestore;

    @Inject
    public RouteLiveData(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
        this.collectionReference = firebaseFirestore.collection("routes");

    }

    @Override
    protected void onActive() {
        super.onActive();
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot querySnapshot,
                                @Nullable FirebaseFirestoreException e) {
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
            }
        });
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }

}
