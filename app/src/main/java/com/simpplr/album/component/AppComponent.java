package com.simpplr.album.component;

import com.simpplr.album.activity.BaseActivity;
import com.simpplr.album.activity.LoginActivity;
import com.simpplr.album.activity.SplashActivity;
import com.simpplr.album.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vaibhavjain on 23/3/17.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(SplashActivity activity);
    void inject(LoginActivity activity);
}
