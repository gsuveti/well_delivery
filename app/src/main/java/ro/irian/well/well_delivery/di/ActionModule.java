package ro.irian.well.well_delivery.di;

import com.birbit.android.jobqueue.JobManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ro.irian.well.well_delivery.services.ActionService;
import ro.irian.well.well_delivery.services.ActionServiceImpl;

@Module
public class ActionModule {

    public ActionModule() {

    }


    @Provides
    @Singleton
    ActionService providesActionService(JobManager jobManager) {
        return new ActionServiceImpl(jobManager);
    }
}
