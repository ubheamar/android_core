package in.co.webstersys.core.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import in.co.webstersys.core.app.CoreApp;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class})
public interface CoreComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        CoreComponent build();
    }

    void inject(CoreApp app);
}
