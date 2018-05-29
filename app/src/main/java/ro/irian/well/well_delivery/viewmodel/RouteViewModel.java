package ro.irian.well.well_delivery.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.SharedPreferences;

import java.util.List;

import javax.inject.Inject;

import ro.irian.well.well_delivery.domain.Route;
import ro.irian.well.well_delivery.repository.RouteRepository;

public class RouteViewModel extends ViewModel {

    private final RouteRepository routeRepository;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    public RouteViewModel(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public LiveData<List<Route>> getRouteLiveData(String driverID) {
        return this.routeRepository.getRouteLiveDataByDriverID(driverID);
    }

    @Override
    protected void onCleared() {

    }

    public void activateRoute(Route route) {
        this.routeRepository.activateRoute(route).addOnSuccessListener(
                aVoid -> {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("activeRouteID", route.getId());
                    editor.commit();
                    //                Log.d(TAG, "DocumentSnapshot successfully written!");
                }
        );
    }
}
