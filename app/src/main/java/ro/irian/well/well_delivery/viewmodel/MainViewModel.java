package ro.irian.well.well_delivery.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import ro.irian.well.well_delivery.domain.Driver;
import ro.irian.well.well_delivery.repository.DriverRepository;

public class MainViewModel extends ViewModel {
    private final DriverRepository driverRepository;

    @Inject
    public MainViewModel(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public LiveData<Driver> getDriverLiveData(String userId) {
        return this.driverRepository.getDriverLiveData(userId);
    }

    @Override
    protected void onCleared() {

    }
}
