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
import com.simpplr.album.model.SongModel;

import java.util.List;

/**
 * Created by vaibhavjain on 23/3/17.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsHolder>{

    private List<SongModel> list;
    private Context context;
    public SongsAdapter(Context context, List<SongModel> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public SongsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new SongsHolder(view);
    }

    @Override
    public void onBindViewHolder(SongsHolder holder, int position) {

        Glide.with(context)
                .load(list.get(position).getData())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .centerCrop()
                .placeholder(R.mipmap.default_placeholder_b)
                .into(holder.imageView);
        holder.name.setText(list.get(position).getTitle());
        holder.artistName.setText(list.get(position).getArtist());
        long time = Integer.parseInt(list.get(position).getDuration());
        holder.duration.setText(convertMiliSecondToMinute(time));

    }

    private String convertMiliSecondToMinute(long time) {

        int second = (int)time/1000;
        int min = second/60;
        second = second - min*60;
        return String.format("%02d : %02d",min,second).toString();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SongsHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView name;
        public TextView artistName;
        public TextView duration;
        public SongsHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.thumbNail);
            name = (TextView) itemView.findViewById(R.id.albumName);
            artistName = (TextView) itemView.findViewById(R.id.artist);
            duration = (TextView)itemView.findViewById(R.id.duration);
        }
    }
}
