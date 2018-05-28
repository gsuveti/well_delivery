package ro.irian.well.well_delivery.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;
import javax.inject.Singleton;

import ro.irian.well.well_delivery.domain.User;

@Singleton
public class LoginRepository {
    private static final String TAG = "LoginRepository";
    private FirebaseAuth firebaseAuth;

    @Inject
    public LoginRepository(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
        Log.d("LoginRepository", "LoginRepository");
    }


    public LiveData<User> login(String email, String password) {
        MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success");
                FirebaseUser user = firebaseAuth.getCurrentUser();
                userMutableLiveData.setValue(new User(user.getUid(), user.getEmail(), user.getDisplayName()));
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.getException());
                userMutableLiveData.setValue(null);
            }
        });
        return userMutableLiveData;
    }
}
