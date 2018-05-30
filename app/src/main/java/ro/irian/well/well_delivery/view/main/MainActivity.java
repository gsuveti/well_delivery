package ro.irian.well.well_delivery.view.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ro.irian.well.well_delivery.R;
import ro.irian.well.well_delivery.di.Injectable;
import ro.irian.well.well_delivery.domain.Driver;
import ro.irian.well.well_delivery.domain.Task;
import ro.irian.well.well_delivery.view.MapsActivity;
import ro.irian.well.well_delivery.view.dev.DevActivity;
import ro.irian.well.well_delivery.view.handover.HandoverActivity;
import ro.irian.well.well_delivery.view.routes.RouteActivity;
import ro.irian.well.well_delivery.view.tasks.TaskDetailActivity;
import ro.irian.well.well_delivery.view.tasks.TaskListActivity;
import ro.irian.well.well_delivery.view.tasks.TaskListFragment;
import ro.irian.well.well_delivery.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TaskListFragment.OnListFragmentInteractionListener, Injectable {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    MainViewModel mainViewModel;
    @Inject
    SharedPreferences sharedPreferences;

    Driver driver;
    TextView userEmailTextView;
    TextView userNameTextView;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
        View headerView = navigationView.getHeaderView(0);
        userEmailTextView = headerView.findViewById(R.id.userEmail);
        userNameTextView = headerView.findViewById(R.id.userName);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sign_out) {
            this.signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void signOut() {
        mAuth.signOut();
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_routeList) {
            this.startActivity(new Intent(this, RouteActivity.class));
        } else if (id == R.id.nav_taskList) {
            this.startActivity(new Intent(this, TaskListActivity.class));
        } else if (id == R.id.nav_handover) {
            this.startActivity(new Intent(this, HandoverActivity.class));
        } else if (id == R.id.nav_dev) {
            this.startActivity(new Intent(this, DevActivity.class));
        } else if (id == R.id.nav_dev_maps) {
            this.startActivity(new Intent(this, MapsActivity.class));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.fab)
    public void onClickFab(View view) {
        startActivity(new Intent(this, HandoverActivity.class));
    }

    @Override
    public void onStart() {
        super.onStart();
        String uid = sharedPreferences.getString("uid", null);
        if (uid != null) {
            mainViewModel.getDriverLiveData(uid).observe(this, driver -> {
                this.driver = driver;
                this.userNameTextView.setText(driver.getName());
                this.userEmailTextView.setText(driver.getId());
            });

            String activeRouteID = sharedPreferences.getString("activeRouteID", null);
            if (activeRouteID == null) {
                Intent intent = new Intent(this, RouteActivity.class);
                startActivity(intent);
            }
        } else {
            this.signOut();
        }
    }

    @Override
    public void onTaskClicked(Task task) {
        Intent intent = new Intent(this, TaskDetailActivity.class);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("taskID", task.getId());
        editor.commit();

        intent.putExtra("taskID", task.getId());
        startActivity(intent);
    }


    @Override
    public void onTaskAccepted(Task task) {
        Log.d("test", task.getId());
        this.mainViewModel.setTaskAsActive(task.getId());
    }
}
