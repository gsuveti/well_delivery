package ro.irian.well.well_delivery.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import javax.inject.Inject;

import ro.irian.well.well_delivery.domain.Driver;
import ro.irian.well.well_delivery.domain.User;
import ro.irian.well.well_delivery.repository.DriverRepository;
import ro.irian.well.well_delivery.repository.LoginRepository;

public class LoginViewModel extends ViewModel {
    private final LoginRepository loginRepository;
    private final DriverRepository driverRepository;

    @Inject
    public LoginViewModel(LoginRepository loginRepository, DriverRepository driverRepository) {
        this.loginRepository = loginRepository;
        this.driverRepository = driverRepository;
    }

    public LiveData<Driver> getDriverLiveData(String userId) {
        return this.driverRepository.getDriverLiveData(userId);
    }

    public LiveData<User> login(String email, String password) {
        Log.d("LoginViewModel", "LoginRepository");
        return this.loginRepository.login(email, password);
    }

    @Override
    protected void onCleared() {

    }
}
