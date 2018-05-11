package ro.irian.well.well_delivery.di;

import dagger.Module;
import dagger.Provides;
import ro.irian.well.well_delivery.domain.User;

/**
 * Binds all sub-components within the app.
 */
@Module
public class MainActivityModule {

    @Provides
    User provideUser() {
        return new User("george@MainActivity");
    }
}
