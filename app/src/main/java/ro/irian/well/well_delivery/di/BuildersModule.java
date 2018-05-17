package ro.irian.well.well_delivery.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ro.irian.well.well_delivery.view.DevActivity;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract DevActivity bindMainActivity();

    // Add bindings for other sub-components here
}
