package com.simpplr.album.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Filter;

import com.simpplr.album.activity.SongsActivity;
import com.simpplr.album.constants.Constant;
import com.simpplr.album.model.AlbumModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaibhavjain on 23/3/17.
 */

public class AlbumAdapterPresenter extends Filter{

    Context context;
    private List<AlbumModel> list;
    private List<AlbumModel> filteredList;
    private AdapterView view;

    public AlbumAdapterPresenter(Context context, List<AlbumModel> list, AdapterView view){
        this.context = context;
        this.list = list;
        this.filteredList = new ArrayList<>();
        this.view = view;
    }

    public void launchNextActivity(String name){
        Intent intent = new Intent(context, SongsActivity.class);
        for(AlbumModel model : list){
            if(model.getName().equalsIgnoreCase(name)){
                intent.putExtra(Constant.ALBUM_ID,model.getName());
                intent.putExtra(Constant.ALBUM_IMAGE,model.getPath());
                break;
            }
        }

        context.startActivity(intent);
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.clear();
        final FilterResults filterResults = new FilterResults();
        for(AlbumModel model : list){
            if(model.getName().toLowerCase().trim().contains(constraint)){
                filteredList.add(model);
            }
        }

        filterResults.values = filteredList;
        filterResults.count = filteredList.size();
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        view.updateAdapter(filteredList);
    }

}
