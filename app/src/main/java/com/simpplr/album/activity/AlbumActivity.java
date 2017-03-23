package com.simpplr.album.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.simpplr.album.R;
import com.simpplr.album.adapter.AlbumListAdapter;
import com.simpplr.album.constants.Constant;
import com.simpplr.album.model.AlbumModel;
import com.simpplr.album.presenter.AlbumListPresenter;
import com.simpplr.album.presenter.HomeView;

import java.util.List;

public class AlbumActivity extends BaseActivity implements HomeView, ActivityCompat.OnRequestPermissionsResultCallback {

    RecyclerView recyclerView;
    AlbumListAdapter adapter;
    AlbumListPresenter presenter;
    private List<AlbumModel> albumList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        init();
        checkPermissions();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void checkPermissions() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return;
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            /* This method returns true if the app has requested this permission previously
            and the user denied the request */
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Snackbar.make(findViewById(R.id.activity_album), R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            @TargetApi(Build.VERSION_CODES.M)
                            public void onClick(View v) {
                                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.REQUEST_PERMISSION);
                            }
                        }).show();
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.REQUEST_PERMISSION);
            }
        } else {
            initilizePresenter();
        }
    }

    private void initilizePresenter() {
        presenter = new AlbumListPresenter(this, this);
        presenter.getAlbumList();
    }

    @Override
    public void getAlbumList(List<AlbumModel> list) {
        albumList = list;
        adapter = new AlbumListAdapter(this, list);
        recyclerView.setAdapter(adapter);
        presenter.setAdapter(adapter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Constant.REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initilizePresenter();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("Search Album..");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerView.scrollToPosition(0);
                presenter.filterList(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
