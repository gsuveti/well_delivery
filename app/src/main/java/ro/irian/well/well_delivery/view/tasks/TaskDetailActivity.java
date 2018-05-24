package ro.irian.well.well_delivery.view.tasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ro.irian.well.well_delivery.R;

public class TaskDetailActivity extends AppCompatActivity {

    private static final String TAG = "TaskDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        String taskID = getIntent().getStringExtra("taskID");
        Log.d(TAG, taskID);
    }
}
