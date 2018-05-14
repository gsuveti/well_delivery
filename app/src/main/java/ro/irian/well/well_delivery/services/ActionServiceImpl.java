package ro.irian.well.well_delivery.services;

import com.birbit.android.jobqueue.JobManager;

import javax.inject.Inject;

import ro.irian.well.well_delivery.domain.Action;
import ro.irian.well.well_delivery.services.jobs.AddActionJob;

public class ActionServiceImpl implements ActionService {
    JobManager jobManager;

    @Inject
    public ActionServiceImpl(JobManager jobManager) {
        this.jobManager = jobManager;
    }

    public void addService(Action action) {
        this.jobManager.addJobInBackground(new AddActionJob(action));
    }
}
