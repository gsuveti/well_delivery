package ro.irian.well.well_delivery.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import ro.irian.well.well_delivery.domain.Route;
import ro.irian.well.well_delivery.repository.RouteRepository;

public class RouteViewModel extends ViewModel {

    private final RouteRepository routeRepository;

    @Inject
    public RouteViewModel(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public LiveData<List<Route>> getRouteLiveData(String driverID) {
        return this.routeRepository.getRouteLiveDataByUser(driverID);
    }

    @Override
    protected void onCleared() {

    }
}
