package ro.irian.well.well_delivery.services.jobs;

import android.content.Context;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;

public class JobManagerFactory {

    private static JobManager jobManager;

    public static synchronized JobManager getJobManager() {
        return jobManager;
    }

    public static synchronized JobManager getJobManager(Context context) {
        if (jobManager == null) {
            jobManager = configureJobManager(context);
        }
        return jobManager;
    }

    private static JobManager configureJobManager(Context context) {
        Configuration.Builder builder = new Configuration.Builder(context)
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(3)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120);


        return new JobManager(builder.build());
    }
}
