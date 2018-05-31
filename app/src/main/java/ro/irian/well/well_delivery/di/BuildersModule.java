package ro.irian.well.well_delivery.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ro.irian.well.well_delivery.view.dev.DevActivity;
import ro.irian.well.well_delivery.view.handover.HandoverActivity;
import ro.irian.well.well_delivery.view.login.LoginActivity;
import ro.irian.well.well_delivery.view.main.MainActivity;
import ro.irian.well.well_delivery.view.routes.RouteActivity;
import ro.irian.well.well_delivery.view.routes.RouteFragment;
import ro.irian.well.well_delivery.view.tasks.TaskDetailActivity;
import ro.irian.well.well_delivery.view.tasks.TaskListActivity;
import ro.irian.well.well_delivery.view.tasks.TaskListFragment;

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract DevActivity bindDevActivity();

    @ContributesAndroidInjector
    abstract TaskListActivity bindTaskListActivity();

    @ContributesAndroidInjector
    abstract TaskDetailActivity bindTaskDetailActivity();

    @ContributesAndroidInjector
    abstract TaskListFragment bindTaskListTaskListFragment();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract RouteFragment bindRouteFragment();

    @ContributesAndroidInjector
    abstract RouteActivity bindRouteActivity();

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract HandoverActivity bindHandoverActivity();

    // Add bindings for other sub-components here
}
