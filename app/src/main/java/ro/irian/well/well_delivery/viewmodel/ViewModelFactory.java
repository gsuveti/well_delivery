package ro.irian.well.well_delivery.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) throws IllegalArgumentException {

        Provider<T> provider = (Provider<T>) (
                creators.containsKey(modelClass) ? creators.get(modelClass) :
                        creators.entrySet()
                                .stream()
                                .filter(creator -> modelClass.isAssignableFrom(creator.getKey()))
                                .findFirst().map(creator -> creator.getValue()).orElse(null));

        if (provider == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return provider.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
