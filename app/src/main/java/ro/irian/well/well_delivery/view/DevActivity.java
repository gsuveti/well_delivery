package ro.irian.well.well_delivery.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import ro.irian.well.well_delivery.LocationUtils;
import ro.irian.well.well_delivery.R;
import ro.irian.well.well_delivery.di.Injectable;
import ro.irian.well.well_delivery.domain.Action;
import ro.irian.well.well_delivery.domain.Route;
import ro.irian.well.well_delivery.domain.User;
import ro.irian.well.well_delivery.services.ActionService;
import ro.irian.well.well_delivery.view.routes.RouteFragment;

public class DevActivity extends AppCompatActivity implements RouteFragment.OnListFragmentInteractionListener, HasSupportFragmentInjector, Injectable {


    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    private static final String TAG = "DevActivity";

    @BindView(R.id.message)
    TextView mTextMessage;
    @BindView(R.id.latitude)
    TextView mTextLatitude;
    @BindView(R.id.longitude)
    TextView mTextLongitude;
    @BindView(R.id.permissions)
    TextView mTextPermissions;

    @Inject
    User user;
    @Inject
    ActionService actionService;
    @Inject
    EventBus eventbus;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            actionService.addService(new Action(String.valueOf(item.getTitle())));
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Subscribe()
    public void onMessageEvent(String event) {
        Log.d("AddActionJob", "in UI: " + event);
    }

    @Override
    public void onStart() {
        super.onStart();
        eventbus.register(this);
    }

    @Override
    public void onStop() {
        eventbus.unregister(this);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);

        ButterKnife.bind(this);

        getLocationPermissions();

        mTextMessage.setText(user.getName());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    void getLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mTextPermissions.setVisibility(View.VISIBLE);
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mTextPermissions.setVisibility(View.GONE);
                } else {
                    getLocationPermissions();
                }
            }
        }
    }

    @OnClick(R.id.updateCoords)
    public void updateCoords() {
        new LocationUtils(this).getLocation().addOnSuccessListener(
                this, location -> {
                    mTextLatitude.setText(String.valueOf(location.getLatitude()));
                    mTextLongitude.setText(String.valueOf(location.getLongitude()));

                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse("geo:"+45.74+","+21.22));
                    intent.setData(Uri.parse("geo:0,0?q=lidl dumbravita"));
                    startActivity(intent);
                }
        );
    }

    @Override
    public void onListFragmentInteraction(Route item) {
        Toast.makeText(this, "bla", Toast.LENGTH_LONG).show();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
