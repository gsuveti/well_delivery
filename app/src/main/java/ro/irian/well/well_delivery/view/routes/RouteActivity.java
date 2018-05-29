package ro.irian.well.well_delivery.view.routes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ro.irian.well.well_delivery.R;
import ro.irian.well.well_delivery.di.Injectable;
import ro.irian.well.well_delivery.domain.Route;
import ro.irian.well.well_delivery.viewmodel.RouteViewModel;

public class RouteActivity extends AppCompatActivity implements Injectable, RouteFragment.OnListFragmentInteractionListener {
    @Inject
    RouteViewModel routeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        ButterKnife.bind(this);
    }

    @Override
    public void onListFragmentInteraction(Route item) {
        routeViewModel.activateRoute(item).observe(this,
                (value) -> {
                    finish();
                });
    }
}
