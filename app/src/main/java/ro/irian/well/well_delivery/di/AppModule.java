package ro.irian.well.well_delivery.di;

import android.app.Application;

import com.birbit.android.jobqueue.JobManager;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ro.irian.well.well_delivery.services.jobs.JobManagerFactory;

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }


    @Provides
    @Singleton
    JobManager providesJobManager() {
        return JobManagerFactory.getJobManager(mApplication);
    }


    @Provides
    @Singleton
    EventBus providesEventBus() {
        return EventBus.getDefault();
    }

}
