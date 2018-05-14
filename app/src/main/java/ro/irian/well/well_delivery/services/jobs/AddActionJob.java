package ro.irian.well.well_delivery.services.jobs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

import ro.irian.well.well_delivery.domain.Action;

public class AddActionJob extends Job {
    private final Action action;

    public AddActionJob(Action action) {
        super(new Params(JobPriority.MID));
        this.action = action;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        Thread.sleep(10000);
        Log.d("AddActionJob", action.getName());
    }


    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        return RetryConstraint.CANCEL;
    }
}
