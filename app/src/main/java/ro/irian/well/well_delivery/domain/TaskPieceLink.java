package ro.irian.well.well_delivery.domain;

import ro.irian.well.well_delivery.repository.BaseEntity;

public class TaskPieceLink extends BaseEntity {
    private String taskID;
    private String pieceID;

    public TaskPieceLink() {
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getPieceID() {
        return pieceID;
    }

    public void setPieceID(String pieceID) {
        this.pieceID = pieceID;
    }
}
