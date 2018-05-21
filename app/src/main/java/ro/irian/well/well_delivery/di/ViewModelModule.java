package ro.irian.well.well_delivery.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ro.irian.well.well_delivery.viewmodel.LoginViewModel;
import ro.irian.well.well_delivery.viewmodel.RouteViewModel;
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
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}