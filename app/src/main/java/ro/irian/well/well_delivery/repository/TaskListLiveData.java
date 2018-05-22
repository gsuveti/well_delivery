package ro.irian.well.well_delivery.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import ro.irian.well.well_delivery.domain.Task;

@Singleton
public class TaskListLiveData extends LiveData<List<Task>> {

    public static final String TAG = "TaskListLiveData";

    private CollectionReference collectionReference;

    private FirebaseFirestore firebaseFirestore;

    @Inject
    public TaskListLiveData(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
        this.collectionReference = firebaseFirestore.collection("tasks");
    }

    @Override
    protected void onActive() {
        super.onActive();
        collectionReference.addSnapshotListener((querySnapshot, e) -> {
            if (e != null) {
                Log.w(TAG, "Listen failed.", e);
                return;
            }

            List<Task> tasks = querySnapshot.getDocuments().stream().map(documentSnapshot -> {
                Task task = documentSnapshot.toObject(Task.class);
                task.setId(documentSnapshot.getId());
                return task;
            }).collect(Collectors.toList());
            setValue(tasks);
        });
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }
}
