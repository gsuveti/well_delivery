package ro.irian.well.well_delivery.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import ro.irian.well.well_delivery.domain.Task;
import ro.irian.well.well_delivery.repository.TaskRepository;

public class TaskViewModel extends ViewModel {

    private final TaskRepository taskRepository;

    @Inject
    public TaskViewModel(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public LiveData<List<Task>> getTaskListLiveData() {
        return this.taskRepository.getTaskListLiveData();
    }

    @Override
    protected void onCleared() {

    }
}
