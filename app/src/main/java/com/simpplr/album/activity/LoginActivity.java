package com.simpplr.album.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.simpplr.album.R;
import com.simpplr.album.constants.Constant;
import com.simpplr.album.presenter.LoginPresenter;
import com.simpplr.album.presenter.LoginView;

import javax.inject.Inject;

/**
 * A login screen that offers login via username/email/password.
 */
public class LoginActivity extends BaseActivity implements LoginView {

    // UI references.
    private TextView mUserNameView;
    private TextView mEmailView;
    private EditText mPasswordView;
    boolean cancel = false;
    View focusView = null;
    LoginPresenter loginPresenter;

    @Inject
    SharedPreferences sharedPrefrences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appComponent.inject(this);
        // Set up the login form.
        mUserNameView = (TextView) findViewById(R.id.username);
        mEmailView = (TextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel = false;
                loginPresenter.attemptLogin(mUserNameView.getText().toString(),mEmailView.getText().toString(),mPasswordView.getText().toString());
            }
        });

        loginPresenter = new LoginPresenter(this);

    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */


    @Override
    public void onInvalidUserName(boolean isEmpty) {
        if (isEmpty)
            mUserNameView.setError(getString(R.string.error_field_required));
        else
            mUserNameView.setError(getString(R.string.user_field_wrong));
        focusView = mUserNameView;
        cancel = true;
        checkOperation();
    }

    @Override
    public void checkOperation() {
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            startActivity(new Intent(this,AlbumActivity.class));
            SharedPreferences.Editor editor = sharedPrefrences.edit();
            editor.putBoolean(Constant.IS_LOGGED_IN,true);
            editor.commit();
            finish();
        }
    }

    @Override
    public void onInvalidEmail(boolean isEmpty) {
        if (isEmpty)
            mEmailView.setError(getString(R.string.error_field_required));
        else
            mEmailView.setError(getString(R.string.error_invalid_email));

        focusView = mEmailView;
        cancel = true;
        checkOperation();

    }

    @Override
    public void onInvalidPassowrd(boolean isEmpty) {
        if (isEmpty)
            mPasswordView.setError(getString(R.string.error_field_required));
        else
            mPasswordView.setError(getString(R.string.error_incorrect_password));

        focusView = mPasswordView;
        cancel = true;
        checkOperation();
    }

}

