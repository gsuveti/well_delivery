package ro.irian.well.well_delivery.di;

import android.content.Context;

import com.birbit.android.jobqueue.JobManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ro.irian.well.well_delivery.App;
import ro.irian.well.well_delivery.services.jobs.JobManagerFactory;

@Module(includes = ViewModelModule.class)
class AppModule {

    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    JobManager providesJobManager(Context context) {
        return JobManagerFactory.getJobManager(context);
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

    @Provides
    @Singleton
    FirebaseAuth providesFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

}
