package ro.irian.well.well_delivery.view.tasks;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ro.irian.well.well_delivery.R;
import ro.irian.well.well_delivery.di.Injectable;
import ro.irian.well.well_delivery.domain.Task;
import ro.irian.well.well_delivery.viewmodel.TaskDetailViewModel;

public class TaskDetailActivity extends AppCompatActivity implements Injectable {

    private static final String TAG = "TaskDetailActivity";

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferences sharedPreferences;

    TaskDetailViewModel taskDetailViewModel;
    Task task;
    Resources res;

    @BindView(R.id.new_task)
    TextView newTaskTextView;
    @BindView(R.id.task_cities)
    TextView taskCitiesTextView;
    @BindView(R.id.task_pickup_interval)
    TextView taskPickupIntervalTextView;
    @BindView(R.id.task_delivery_interval)
    TextView taskDeliveryIntervalTextView;
    @BindView(R.id.task_sender_name)
    TextView taskSenderNameTextView;
    @BindView(R.id.task_receiver_name)
    TextView taskReceiverNameTextView;
    @BindView(R.id.task_pieces_summary)
    TextView taskPiecesSummaryTextView;
    @BindView(R.id.task_pieces_awb_value)
    TextView taskAwbTextView;
    @BindView(R.id.task_pickup_location_value)
    TextView taskPickupLocationTextView;
    @BindView(R.id.task_pickup_interval_value)
    TextView taskPickupIntervalDetailTextView;
    @BindView(R.id.task_pickup_contact_person_value)
    TextView taskPickupContactPersonTextView;
    @BindView(R.id.task_delivery_location_value)
    TextView taskDeliveryLocationTextView;
    @BindView(R.id.task_delivery_interval_value)
    TextView taskDeliveryIntervalDetailTextView;
    @BindView(R.id.task_delivery_contact_person_value)
    TextView taskDeliveryContactPersonTextView;
    @BindView(R.id.task_accept_buttons)
    LinearLayout taskAcceptButtonsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        res = getResources();
        ButterKnife.bind(this);

        taskDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskDetailViewModel.class);

    }

    @Override
    protected void onStart() {
        super.onStart();
        String taskID = getIntent().getStringExtra("taskID");

        taskDetailViewModel.getTaskByID(taskID).observe(this, task -> {
            this.task = task;
            updateUI();
        });
    }

    private void updateUI() {

        if (task.isActive()) {
            taskAcceptButtonsLayout.setVisibility(View.GONE);
        }

        taskCitiesTextView.setText(res.getString(R.string.task_cities, task.getPickupCity(), task.getDeliveryCity()));
        taskPickupIntervalTextView.setText(res.getString(R.string.time_interval, task.getPickupStartTime(), task.getPickupEndTime()));
        taskPickupIntervalDetailTextView.setText(res.getString(R.string.time_interval, task.getPickupStartTime(), task.getPickupEndTime()));
        taskDeliveryIntervalTextView.setText(res.getString(R.string.time_interval, task.getDeliveryStartTime(), task.getDeliveryEndTime()));
        taskDeliveryIntervalDetailTextView.setText(res.getString(R.string.time_interval, task.getDeliveryStartTime(), task.getDeliveryEndTime()));
        taskSenderNameTextView.setText(task.getSender());
        taskReceiverNameTextView.setText(task.getConsignee());
        taskPiecesSummaryTextView.setText(res.getQuantityString(R.plurals.pieces_number_weight, task.getTotalPieces(), task.getTotalPieces(), task.getTotalWeight()));
        taskAwbTextView.setText(task.getAwb());
        taskPickupContactPersonTextView.setText(task.getPickupContactName());
        taskDeliveryContactPersonTextView.setText(task.getDeliveryContactName());

        //todo: implementat NEW
//        if(task.isNew()){
//            newTaskTextView.setVisibility(View.VISIBLE);
//        }

        //TODO: trebuie si orasul concatenat??
        taskPickupLocationTextView.setText(task.getPickupAddress());
        taskDeliveryLocationTextView.setText(task.getDeliveryAddress());

    }

    @SuppressLint("MissingPermission")
    @OnClick(R.id.task_delivery_contact_person_call)
    public void callDeliveryContactPerson() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + task.getDeliveryContactPhone()));
        startActivity(intent);
    }

    @SuppressLint("MissingPermission")
    @OnClick(R.id.task_pickup_contact_person_call)
    public void callPickupContactPerson() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + task.getPickupContactPhone()));
        startActivity(intent);
    }

    @SuppressLint("MissingPermission")
    @OnClick(R.id.task_dispatcher_call)
    public void callDispatcher() {
        //TODO: de schimbat butonul de call (poate si de schimbat action, in cea cu dial?)
        //TODO: pus undeva (in ceva constants class, sau in strings?) numarul de telefon de la dispecer!
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0753854648"));
        startActivity(intent);
    }

    @OnClick(R.id.task_pickup_scan_button)
    public void pickupScan() {
        Toast.makeText(this, "Pickup Scan", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.task_delivery_scan_button)
    public void deliveryScan() {
        Toast.makeText(this, "Delivery Scan", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.task_reject)
    public void rejectTask() {
        //TODO: in repo (+, e de ajuns finish?)
        taskDetailViewModel.rejectTask(task.getId());
        finish();
    }

    @OnClick(R.id.task_accept)
    public void acceptTask() {
//        task.setActive(true);
        taskAcceptButtonsLayout.setVisibility(View.GONE);
        taskDetailViewModel.setTaskAsActive(task.getId());
    }
}
