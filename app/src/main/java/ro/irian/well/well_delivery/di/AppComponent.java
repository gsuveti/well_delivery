package ro.irian.well.well_delivery.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import ro.irian.well.well_delivery.App;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class,
        BuildersModule.class})
public interface AppComponent {

    void inject(App app);

    Application providesApplication();

}
