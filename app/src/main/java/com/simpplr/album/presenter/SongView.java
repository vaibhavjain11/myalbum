package com.simpplr.album.presenter;

import com.simpplr.album.model.SongModel;

import java.util.List;

/**
 * Created by vaibhavjain on 23/3/17.
 */

public interface SongView {

    void showSongList(List<SongModel> list);
}
