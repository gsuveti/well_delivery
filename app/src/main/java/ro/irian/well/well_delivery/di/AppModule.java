package ro.irian.well.well_delivery.di;

import android.app.Application;

import com.birbit.android.jobqueue.JobManager;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

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


    @Provides
    @Singleton
    FirebaseFirestore providesFirebaseFirestore() {
        return FirebaseFirestore.getInstance();
    }

    @Provides
    @Singleton
    FirebaseStorage providesFirebaseStorage() {
        return FirebaseStorage.getInstance();
    }

}
