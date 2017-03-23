package com.simpplr.album.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.simpplr.album.R;
import com.simpplr.album.constants.Constant;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appComponent.inject(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPreferences.getBoolean(Constant.IS_LOGGED_IN, false)) {
                    startActivity(new Intent(SplashActivity.this, AlbumActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }

                finish();
            }
        }, 2000);
    }

}
