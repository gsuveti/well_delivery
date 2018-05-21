package ro.irian.well.well_delivery.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;
import javax.inject.Singleton;

import ro.irian.well.well_delivery.domain.User;

@Singleton
public class LoginRepository {
    private static final String TAG = "LoginRepository";
    private final FirebaseFirestore firebaseFirestore;
    private final FirebaseAuthLiveData firebaseAuthLiveData;
    private FirebaseAuth firebaseAuth;

    @Inject
    public LoginRepository(FirebaseFirestore firebaseFirestore, FirebaseAuth firebaseAuth, FirebaseAuthLiveData firebaseAuthLiveData) {
        this.firebaseFirestore = firebaseFirestore;
        this.firebaseAuth = firebaseAuth;
        this.firebaseAuthLiveData = firebaseAuthLiveData;
        Log.d("LoginRepository", "LoginRepository");
    }

   
    @NonNull
    public LiveData<User> getUserLiveData() {
        return firebaseAuthLiveData;
    }

    public void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password);
    }
}
