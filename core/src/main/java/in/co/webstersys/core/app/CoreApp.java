package in.co.webstersys.core.app;

import android.app.Application;

import in.co.webstersys.core.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class CoreApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.font_regular))
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
