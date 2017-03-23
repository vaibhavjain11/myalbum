package com.simpplr.album.presenter;

/**
 * Created by vaibhavjain on 23/3/17.
 */

public interface LoginView {

    void onInvalidUserName(boolean isEmpty);
    void onInvalidEmail(boolean isEmpty);
    void onInvalidPassowrd(boolean isEmpty);
    void checkOperation();

}
