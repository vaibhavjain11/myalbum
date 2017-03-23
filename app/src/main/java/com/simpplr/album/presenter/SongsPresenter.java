package com.simpplr.album.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import com.simpplr.album.model.SongModel;

import java.util.ArrayList;

/**
 * Created by vaibhavjain on 23/3/17.
 */

public class SongsPresenter implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context context;
    private String albumId;
    private String albumImage;
    private ArrayList<SongModel> list;
    private SongView songView;
    public SongsPresenter(Context context,String albumId,String albumImage, SongView view){
        this.context = context;
        this.albumId = albumId;
        this.albumImage = albumImage;
        this.songView = view;
        list = new ArrayList<>();

    }

    public void getSongsList(){
          ((Activity)context).getLoaderManager().initLoader(0,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID
        };
        String selection = "album='"+albumId+"'";
        Loader<Cursor> loader = new CursorLoader(context, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,null,null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if(data.getCount()>0) {
            data.moveToFirst();
            do {
               SongModel model = new SongModel();
                model.setTitle(data.getString(data.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                model.setArtist(data.getString(data.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                model.setData(albumImage);
                model.setDuration(data.getString(data.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                list.add(model);
            } while (data.moveToNext());

            songView.showSongList(list);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
