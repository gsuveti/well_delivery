package ro.irian.well.well_delivery.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ro.irian.well.well_delivery.view.DevActivity;
import ro.irian.well.well_delivery.view.login.LoginActivity;
import ro.irian.well.well_delivery.view.routes.RouteFragment;

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract DevActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract RouteFragment bindRouteFragment();

    // Add bindings for other sub-components here
}
