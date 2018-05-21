package ro.irian.well.well_delivery.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import javax.inject.Inject;

import ro.irian.well.well_delivery.domain.User;
import ro.irian.well.well_delivery.repository.LoginRepository;

public class LoginViewModel extends ViewModel {
    private final LoginRepository loginRepository;

    @Inject
    public LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public LiveData<User> getUserLiveData() {
        return this.loginRepository.getUserLiveData();
    }

    public void login(String email, String password) {
        Log.d("LoginViewModel", "LoginRepository");
        this.loginRepository.login(email, password);
    }

    @Override
    protected void onCleared() {

    }
}
