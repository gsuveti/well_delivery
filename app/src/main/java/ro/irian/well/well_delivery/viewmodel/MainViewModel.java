package ro.irian.well.well_delivery.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import ro.irian.well.well_delivery.domain.Driver;
import ro.irian.well.well_delivery.domain.Route;
import ro.irian.well.well_delivery.repository.DriverRepository;
import ro.irian.well.well_delivery.repository.RouteRepository;
import ro.irian.well.well_delivery.repository.TaskRepository;

public class MainViewModel extends ViewModel {
    private final DriverRepository driverRepository;
    private final RouteRepository routeRepository;
    private final TaskRepository taskRepository;

    @Inject
    public MainViewModel(DriverRepository driverRepository, RouteRepository routeRepository, TaskRepository taskRepository) {
        this.driverRepository = driverRepository;
        this.routeRepository = routeRepository;
        this.taskRepository = taskRepository;
    }

    public LiveData<Driver> getDriverLiveData(String userId) {
        return this.driverRepository.getDriverLiveData(userId);
    }

    public LiveData<Route> getRouteByID(String routeID) {
        return this.routeRepository.getRouteByID(routeID);
    }

    public void setTaskAsActive(String taskID) {
        this.taskRepository.setTaskAsActive(taskID);
    }

    @Override
    protected void onCleared() {

    }
}
