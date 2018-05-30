package ro.irian.well.well_delivery.view.handover;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ro.irian.well.well_delivery.R;
import ro.irian.well.well_delivery.view.handover.dummy.DummyContent;

public class HandoverActivity extends AppCompatActivity implements PieceListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handover);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
