package ro.irian.well.well_delivery.view.routes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ro.irian.well.well_delivery.R;
import ro.irian.well.well_delivery.di.Injectable;
import ro.irian.well.well_delivery.domain.Route;
import ro.irian.well.well_delivery.repository.RouteRepository;
import ro.irian.well.well_delivery.view.main.MainActivity;
import ro.irian.well.well_delivery.viewmodel.RouteViewModel;

public class RouteActivity extends AppCompatActivity implements Injectable, RouteFragment.OnListFragmentInteractionListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    RouteViewModel routeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

    }

    @Override
    public void onListFragmentInteraction(Route item) {

        Toast.makeText(this, item.getName(), Toast.LENGTH_SHORT).show();
        routeViewModel.activateRoute(item);
        startActivity(new Intent(this, MainActivity.class));
    }

    @OnClick(R.id.fab)
    public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
