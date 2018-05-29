package ro.irian.well.well_delivery.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import ro.irian.well.well_delivery.domain.Task;
import ro.irian.well.well_delivery.repository.TaskRepository;

public class TaskViewModel extends ViewModel {
    private final TaskRepository taskRepository;
    private final MediatorLiveData<List<Task>> taskListLiveData;
    private LiveData<List<Task>> source;

    @Inject
    public TaskViewModel(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.taskListLiveData = new MediatorLiveData<>();
    }

    public LiveData<List<Task>> getTaskListLiveData() {
        return this.taskListLiveData;
    }

    public void setActiveRouteID(String activeRouteID) {
        if (this.source != null) {
            taskListLiveData.removeSource(source);
        }
        this.source = this.taskRepository.getTaskListLiveDataByRouteID(activeRouteID);
        taskListLiveData.addSource(source, value -> taskListLiveData.setValue(value));
    }

    @Override
    protected void onCleared() {

    }
}
