package com.simpplr.album.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.simpplr.album.component.AppComponent;
import com.simpplr.album.component.DaggerAppComponent;
import com.simpplr.album.module.AppModule;

/**
 * Created by vaibhavjain on 23/3/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected AppComponent appComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

    }

}
