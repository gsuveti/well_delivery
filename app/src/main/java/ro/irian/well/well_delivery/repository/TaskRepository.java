package ro.irian.well.well_delivery.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TaskRepository {

    private static final String TAG = "TaskRepository";

    private final TaskListLiveData taskListLiveData;
    private final FirebaseFirestore firebaseFirestore;

    @Inject
    public TaskRepository(FirebaseFirestore firebaseFirestore, TaskListLiveData taskListLiveData) {
        this.firebaseFirestore = firebaseFirestore;
        this.taskListLiveData = taskListLiveData;
        Log.d(TAG, "TaskRepository");
    }

    @NonNull
    public TaskListLiveData getTaskListLiveData() {
        return taskListLiveData;
    }
}
