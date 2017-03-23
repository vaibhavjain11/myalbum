package com.simpplr.album.presenter;

/**
 * Created by vaibhavjain on 23/3/17.
 */

public class LoginPresenter {

    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void attemptLogin(String userName, String email, String password) {
        if (userName.isEmpty()) {
            loginView.onInvalidUserName(true);
            return;
        }
        if (!userName.equals("nullvoidinfinity")) {
            loginView.onInvalidUserName(false);
            return;
        }
        if (email.isEmpty()) {
            loginView.onInvalidEmail(true);
            return;
        }
        if (!email.equals("piyushrajput@null.net")) {
            loginView.onInvalidEmail(false);
            return;
        }
        if (password.isEmpty()) {
            loginView.onInvalidPassowrd(true);
            return;
        }
        if (!password.equals("_Simp_1234")) {
            loginView.onInvalidPassowrd(false);
            return;
        }

        loginView.checkOperation();

    }
}
