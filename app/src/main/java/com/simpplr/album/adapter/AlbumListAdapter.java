package com.simpplr.album.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.simpplr.album.R;
import com.simpplr.album.model.AlbumModel;
import com.simpplr.album.presenter.AdapterView;
import com.simpplr.album.presenter.AlbumAdapterPresenter;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by vaibhavjain on 22/3/17.
 */

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.AlbumHolder> implements AdapterView {

    private List<AlbumModel> list;
    private Context context;
    private AlbumAdapterPresenter albumAdapterPresenter;

    public AlbumListAdapter(Context context, List<AlbumModel> albumModelList) {
        this.context = context;
        this.list = albumModelList;
        albumAdapterPresenter = new AlbumAdapterPresenter(context, albumModelList, this);
    }

    @Override
    public AlbumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false);
        return new AlbumHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumHolder holder, int position) {

        Glide.with(context)
                .load(list.get(position).getPath())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .centerCrop()
                .placeholder(R.mipmap.default_placeholder_b)
                .into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.artist.setText(list.get(position).getArtistName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(String s) {
        albumAdapterPresenter.filter(s);
    }

    @Override
    public void updateAdapter(List<AlbumModel> albumModelList) {
        this.list = albumModelList;
        notifyDataSetChanged();
    }

    public class AlbumHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView name;
        public TextView artist;

        public AlbumHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.thumbNail);
            name = (TextView) itemView.findViewById(R.id.albumName);
            artist = (TextView) itemView.findViewById(R.id.artist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    albumAdapterPresenter.launchNextActivity(list.get(getAdapterPosition()).getName());
                }
            });
        }
    }
}
