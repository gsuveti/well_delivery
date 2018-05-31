package ro.irian.well.well_delivery.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.LiveDataReactiveStreams;


import javax.inject.Inject;

import ro.irian.well.well_delivery.domain.Task;
import ro.irian.well.well_delivery.repository.TaskRepository;

public class TaskDetailViewModel extends ViewModel {

    private final TaskRepository taskRepository;

    @Inject
    public TaskDetailViewModel(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public LiveData<Task> getTaskByID(String taskID) {
        return LiveDataReactiveStreams.fromPublisher(this.taskRepository.getOne(taskID));
    }

    public void setTaskAsActive(String taskID) {
        this.taskRepository.setTaskAsActive(taskID);
    }

    public void rejectTask(String taskID) {
        this.taskRepository.rejectTask(taskID);
    }
}
