package ro.irian.well.well_delivery.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import ro.irian.well.well_delivery.domain.Task;

//import com.google.android.gms.tasks.Task;

@Singleton
public class TaskListLiveData extends LiveData<List<Task>> {

    public static final String TAG = "TaskListLiveData";

    private Query documentReference;

    private FirebaseFirestore firebaseFirestore;

    private final String MYUSER = "sv23V1ZYF4XZqrMplHG2";

    @Inject
    public TaskListLiveData(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
        DocumentReference userDocumentReference = firebaseFirestore.collection("users").document(MYUSER);
        this.documentReference = firebaseFirestore.collection("tasks").whereEqualTo("activeUser", userDocumentReference);
    }

    @Override
    protected void onActive() {
        super.onActive();
        documentReference.addSnapshotListener((querySnapshot, e) -> {

            if (e != null) {
                Log.w(TAG, "Listen failed.", e);
                return;
            }


//            if(task.isSuccessful()){
//                DocumentSnapshot documentSnapshot = task.getResult();
//                if(documentSnapshot.exists()){
//                    List<Task> tasks = (List) documentSnapshot.getData().get("activeTasks");
//                    Log.d("TAG", "bla");
//                }
//            }
//            List<Task> tasks = task.getResult().
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
