package com.simpplr.album.presenter;

import com.simpplr.album.model.AlbumModel;

import java.util.List;

/**
 * Created by vaibhavjain on 23/3/17.
 */

public interface AdapterView {

    void updateAdapter(List<AlbumModel> albumModelList);
}
