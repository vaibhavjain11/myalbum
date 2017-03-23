package com.simpplr.album.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.simpplr.album.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vaibhavjain on 23/3/17.
 */

@Module
public class AppModule {

    BaseActivity baseActivity;
    public AppModule(BaseActivity activity){
        this.baseActivity = activity;
    }

    @Provides
    @Singleton
    SharedPreferences getSharedPrefrences(){
        return baseActivity.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }
}
