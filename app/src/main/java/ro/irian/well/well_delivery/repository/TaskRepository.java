package ro.irian.well.well_delivery.repository;

import android.annotation.SuppressLint;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import ro.irian.well.well_delivery.domain.Piece;
import ro.irian.well.well_delivery.domain.Task;
import ro.irian.well.well_delivery.domain.TaskPieceLink;

@Singleton
public class TaskRepository extends FirebaseRepository<Task> {
    private static final String TAG = "TaskRepository";
    private final PieceRepository pieceRepository;
    private final TaskPieceLinkRepository taskPieceLinkRepository;


    @Inject
    public TaskRepository(FirebaseFirestore firebaseFirestore, PieceRepository pieceRepository, TaskPieceLinkRepository taskPieceLinkRepository) {
        super(firebaseFirestore.collection("tasks"), Task.class);
        this.pieceRepository = pieceRepository;
        this.taskPieceLinkRepository = taskPieceLinkRepository;
    }

    public Flowable<List<Task>> getListByRouteID(String routeID) {
        return this.getList(this.collection.whereEqualTo("routeID", routeID));
    }

    public void setTaskAsActive(String taskID) {
        this.collection.document(taskID).update("active", true);
    }


    @SuppressLint("CheckResult")
    public Flowable<Task> observeOneWithPieces(String ID) {
        Flowable<Task> taskFlowable = this.observeOne(ID);
        Flowable<List<TaskPieceLink>> taskPieceLinkFlowable = this.taskPieceLinkRepository.
                observeList(taskPieceLinkRepository.collection.whereEqualTo("taskID", ID));

        return Flowable.combineLatest(taskFlowable, taskPieceLinkFlowable, (task, taskPieceLinks) -> {
            taskPieceLinks.forEach(taskPieceLink -> task.getPiecesMap().put(taskPieceLink.getPieceID(), null));
            return task;
        }).switchMap((Task task) -> {
            List<Flowable<Piece>> flowablePieces = task.getPiecesMap().keySet().stream()
                    .map(this.pieceRepository::getOne)
                    .collect(Collectors.toList());

            return Flowable.zip(flowablePieces, Arrays::asList).map((List<Object> pieces) -> {
                pieces.forEach((Object item) -> {
                    Piece piece = (Piece) item;
                    task.getPiecesMap().put(piece.getId(), piece);
                });
                return task;
            });
        });
    }

}
