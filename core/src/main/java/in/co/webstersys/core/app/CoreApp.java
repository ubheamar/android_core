package in.co.webstersys.core.app;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import in.co.webstersys.core.R;
import in.co.webstersys.core.di.DaggerCoreComponent;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class CoreApp extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;
    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.font_regular))
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
    private void initializeComponent() {

        DaggerCoreComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }
    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }

}
