package ro.irian.well.well_delivery.view.tasks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ro.irian.well.well_delivery.R;
import ro.irian.well.well_delivery.di.Injectable;
import ro.irian.well.well_delivery.domain.Task;
import ro.irian.well.well_delivery.viewmodel.TaskViewModel;

public class TaskListActivity extends AppCompatActivity implements Injectable {

    private static final String TAG = "TaskListActivity";

    @BindView(R.id.task_list_view)
    RecyclerView mRecyclerView;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    TaskViewModel taskViewModel;

    @Inject
    EventBus eventbus;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        ButterKnife.bind(this);

        mAdapter = new TaskRecyclerViewAdapter(taskList, this);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        final Observer<List<Task>> taskObserver = taskList -> {
            if (taskList != null) {
                this.taskList.clear();
                this.taskList.addAll(taskList);
                mAdapter.notifyDataSetChanged();
            }
        };

        taskViewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskViewModel.class);
        taskViewModel.getTaskListLiveData().observe(this, taskObserver);
    }
}
