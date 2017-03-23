package com.simpplr.album.presenter;

import com.simpplr.album.model.AlbumModel;

import java.util.List;

/**
 * Created by vaibhavjain on 22/3/17.
 */

public interface HomeView {

    void getAlbumList(List<AlbumModel> list);
}
