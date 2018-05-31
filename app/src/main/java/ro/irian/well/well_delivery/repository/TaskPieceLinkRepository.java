package ro.irian.well.well_delivery.repository;

import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;
import javax.inject.Singleton;

import ro.irian.well.well_delivery.domain.TaskPieceLink;

@Singleton
public class TaskPieceLinkRepository extends FirebaseRepository<TaskPieceLink> {

    private static final String TAG = "TaskRepository";

    @Inject
    public TaskPieceLinkRepository(FirebaseFirestore firebaseFirestore) {
        super(firebaseFirestore.collection("task_piece_links"), TaskPieceLink.class);
    }

}
