package ro.irian.well.well_delivery;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

public class LocationUtils {

    FusedLocationProviderClient mFusedLocationClient;

    public LocationUtils(Activity activity) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
    }

    @SuppressLint("MissingPermission")
    public Task<Location> getLocation() {
        return mFusedLocationClient.getLastLocation();
    }
}
