package ro.irian.well.well_delivery.di;

import com.birbit.android.jobqueue.JobManager;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ro.irian.well.well_delivery.services.ActionService;
import ro.irian.well.well_delivery.services.ActionServiceImpl;

@Module
public class ActionModule {

    @Provides
    @Singleton
    ActionService providesActionService(JobManager jobManager, EventBus eventBus) {
        return new ActionServiceImpl(jobManager, eventBus);
    }
}
