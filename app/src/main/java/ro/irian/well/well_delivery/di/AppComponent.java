package ro.irian.well.well_delivery.di;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import ro.irian.well.well_delivery.App;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class,
        ActionModule.class,
        BuildersModule.class})
public interface AppComponent {
    void inject(App application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(App app);

        AppComponent build();
    }
}
