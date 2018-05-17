package ro.irian.well.well_delivery.services;

import com.birbit.android.jobqueue.JobManager;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import ro.irian.well.well_delivery.domain.Action;
import ro.irian.well.well_delivery.services.jobs.AddActionJob;

public class ActionServiceImpl implements ActionService {
    JobManager jobManager;
    EventBus eventBus;

    @Inject
    public ActionServiceImpl(JobManager jobManager, EventBus eventBus) {
        this.jobManager = jobManager;
        this.eventBus = eventBus;
    }

    public void addService(Action action) {
        AddActionJob addActionJob = new AddActionJob(action, eventBus);
        this.jobManager.addJobInBackground(addActionJob);
    }
}
