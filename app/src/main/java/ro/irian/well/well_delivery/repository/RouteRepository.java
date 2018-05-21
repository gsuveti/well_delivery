package ro.irian.well.well_delivery.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RouteRepository {

    private static final String TAG = "RouteRepository";

    private final FirebaseFirestore firebaseFirestore;
    private final RouteLiveData routeLiveData;

    @Inject
    public RouteRepository(FirebaseFirestore firebaseFirestore, RouteLiveData routeLiveData) {
        this.firebaseFirestore = firebaseFirestore;
        this.routeLiveData = routeLiveData;
        Log.d(TAG, "RouteRepository");
    }

    @NonNull
    public RouteLiveData getRouteLiveData() {
        return routeLiveData;
    }
}
