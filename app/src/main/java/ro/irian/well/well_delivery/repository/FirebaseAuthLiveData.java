package ro.irian.well.well_delivery.repository;

import android.arch.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;
import javax.inject.Singleton;

import ro.irian.well.well_delivery.domain.User;

@Singleton
public class FirebaseAuthLiveData extends LiveData<User> {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener =
            (FirebaseAuth firebaseAuth) -> {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    setValue(new User(firebaseUser.getEmail()));
                } else {
                    setValue(null);
                }
            };


    @Inject
    public FirebaseAuthLiveData(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;

    }

    @Override
    protected void onActive() {
        super.onActive();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }


}
