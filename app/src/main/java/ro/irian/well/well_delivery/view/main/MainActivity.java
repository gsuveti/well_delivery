package ro.irian.well.well_delivery.view.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ro.irian.well.well_delivery.R;
import ro.irian.well.well_delivery.di.Injectable;
import ro.irian.well.well_delivery.domain.Driver;
import ro.irian.well.well_delivery.domain.Route;
import ro.irian.well.well_delivery.view.DevActivity;
import ro.irian.well.well_delivery.view.MapsActivity;
import ro.irian.well.well_delivery.view.login.LoginActivity;
import ro.irian.well.well_delivery.view.routes.RouteFragment;
import ro.irian.well.well_delivery.view.tasks.TaskListActivity;
import ro.irian.well.well_delivery.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Injectable, RouteFragment.OnListFragmentInteractionListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @Inject
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

        navigationView.setNavigationItemSelectedListener(this);

        String uid = sharedPreferences.getString("uid", null);
        if (uid != null) {
            mainViewModel.getDriverLiveData(uid).observe(this, driver -> {
                this.driver = driver;
                this.userNameTextView.setText(driver.getName());
                this.userEmailTextView.setText(driver.getId());
            });
        } else {
            this.signOut();
        }

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
        startActivity(new Intent(this, LoginActivity.class));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_taskList) {
            this.startActivity(new Intent(this, TaskListActivity.class));
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

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
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    public void onListFragmentInteraction(Route item) {
        Toast.makeText(this, item.getId(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
