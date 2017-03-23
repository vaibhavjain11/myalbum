package com.simpplr.album.presenter;

import android.Manifest;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.content.Loader;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.simpplr.album.adapter.AlbumListAdapter;
import com.simpplr.album.constants.Constant;
import com.simpplr.album.model.AlbumModel;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by vaibhavjain on 22/3/17.
 */

public class AlbumListPresenter implements LoaderManager.LoaderCallbacks<Cursor> {

    private HomeView view;
    private Context context;
    private ArrayList<AlbumModel> albumList;
    private AlbumListAdapter adapter;


    public AlbumListPresenter(Context c, HomeView view) {
        this.view = view;
        this.context = c;
        albumList = new ArrayList<>();
    }

    public void filterList(String text) {
        adapter.filterList(text);
    }

    public void getAlbumList() {
        ((Activity) context).getLoaderManager().initLoader(0, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(context, MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Albums.ALBUM_ART, MediaStore.Audio.Albums.ALBUM
                        , MediaStore.Audio.Albums.ARTIST}, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        while (data.moveToNext()) {
            AlbumModel model = new AlbumModel();
            model.setName(data.getString(data.getColumnIndex(MediaStore.Audio.Albums.ALBUM)));
            model.setPath(data.getString(data.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART)));
            model.setArtistName(data.getString(data.getColumnIndex(MediaStore.Audio.Albums.ARTIST)));
            albumList.add(model);
        }

        view.getAlbumList(albumList);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public void setAdapter(AlbumListAdapter adapter) {
        this.adapter = adapter;
    }
}
