package ro.irian.well.well_delivery.view.handover;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ro.irian.well.well_delivery.R;

public class HandoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handover);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
