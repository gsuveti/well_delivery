package ro.irian.well.well_delivery.view.handover;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import ro.irian.well.well_delivery.R;
import ro.irian.well.well_delivery.di.Injectable;
import ro.irian.well.well_delivery.view.handover.dummy.DummyContent;
import ro.irian.well.well_delivery.viewmodel.HandoverViewModel;

public class HandoverActivity extends AppCompatActivity implements PieceListFragment.OnListFragmentInteractionListener, Injectable {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    HandoverViewModel handoverViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handover);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        handoverViewModel = ViewModelProviders.of(this, viewModelFactory).get(HandoverViewModel.class);
        handoverViewModel.getTask("yoXqcejX3g4Q1qMVS9M8").observe(this, task -> {
            Log.d("tag", task.getId());
        });
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
