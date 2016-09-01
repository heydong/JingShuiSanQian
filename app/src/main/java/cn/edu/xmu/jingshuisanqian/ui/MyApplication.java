package cn.edu.xmu.jingshuisanqian.ui;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;

/**
 * Created by hd_chen on 2016/9/1.
 */
public class MyApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
    }
}
