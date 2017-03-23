package com.simpplr.album.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.simpplr.album.R;
import com.simpplr.album.adapter.SongsAdapter;
import com.simpplr.album.constants.Constant;
import com.simpplr.album.model.SongModel;
import com.simpplr.album.presenter.SongView;
import com.simpplr.album.presenter.SongsPresenter;

import java.util.List;

public class SongsActivity extends BaseActivity implements SongView {

    String  albumId;
    private RecyclerView recyclerView;
    private SongsAdapter songsAdapter;
    private String albumImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        getSupportActionBar().setTitle("Songs");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            albumId = bundle.getString(Constant.ALBUM_ID);
            albumImage = bundle.getString(Constant.ALBUM_IMAGE);
        }

        init();

        SongsPresenter songsPresenter = new SongsPresenter(this, albumId, albumImage,this);
        songsPresenter.getSongsList();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.songsRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void showSongList(List<SongModel> list) {
        songsAdapter = new SongsAdapter(this, list);
        recyclerView.setAdapter(songsAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }
}
