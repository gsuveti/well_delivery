package ro.irian.well.well_delivery.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ro.irian.well.well_delivery.viewmodel.HandoverViewModel;
import ro.irian.well.well_delivery.viewmodel.LoginViewModel;
import ro.irian.well.well_delivery.viewmodel.MainViewModel;
import ro.irian.well.well_delivery.viewmodel.RouteViewModel;
import ro.irian.well.well_delivery.viewmodel.TaskDetailViewModel;
import ro.irian.well.well_delivery.viewmodel.TaskViewModel;
import ro.irian.well.well_delivery.viewmodel.ViewModelFactory;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RouteViewModel.class)
    abstract ViewModel bindRouteViewModel(RouteViewModel routeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TaskViewModel.class)
    abstract ViewModel bindTaskViewModel(TaskViewModel taskViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TaskDetailViewModel.class)
    abstract ViewModel bindTaskDetailViewModel(TaskDetailViewModel taskDetailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HandoverViewModel.class)
    abstract ViewModel bindHandoverViewModel(HandoverViewModel handoverViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}
