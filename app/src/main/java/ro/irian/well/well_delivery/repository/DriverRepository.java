package ro.irian.well.well_delivery.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;
import javax.inject.Singleton;

import ro.irian.well.well_delivery.domain.Driver;

@Singleton
public class DriverRepository {
    private static final String TAG = "DriverRepository";
    private final FirebaseFirestore firebaseFirestore;
    private final CollectionReference collection;
    private Driver driver;

    @Inject
    public DriverRepository(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
        this.collection = firebaseFirestore.collection("drivers");
    }

    public Driver getDriver() {
        return driver;
    }

    public LiveData<Driver> getDriverLiveData(String userId) {
        MutableLiveData<Driver> driverMutableLiveData = new MutableLiveData<>();
        this.collection.document(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    driver = document.toObject(Driver.class);
                    driver.setId(document.getId());
                    driverMutableLiveData.setValue(driver);
                } else {
                    driverMutableLiveData.setValue(null);
                }
            } else {
                driverMutableLiveData.setValue(null);
            }
        });
        return driverMutableLiveData;
    }
}
