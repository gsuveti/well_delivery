package ro.irian.well.well_delivery.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ro.irian.well.well_delivery.domain.Route;

@Singleton
public class RouteLiveData extends LiveData<List<Route>> {

    private static final String TAG = "RouteLiveData";

    private EventListener listener = (EventListener<DocumentSnapshot>) (snapshot, e) -> {
        if (e != null) {
            Log.w(TAG, "Listen failed.", e);
            return;
        }

        String source = snapshot != null && snapshot.getMetadata().hasPendingWrites()
                ? "Local" : "Server";

        if (snapshot != null && snapshot.exists()) {
            Log.d(TAG, source + " data: " + snapshot.getData());
        } else {
            Log.d(TAG, source + " data: null");
        }
    };

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
        collectionReference.addSnapshotListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }

}
