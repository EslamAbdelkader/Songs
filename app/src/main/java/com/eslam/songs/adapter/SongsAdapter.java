package com.eslam.songs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.eslam.songs.R;
import com.eslam.songs.model.Song;
import com.eslam.songs.holer.SongsViewHolder;

public class SongsAdapter extends ListAdapter<Song, SongsViewHolder> {
    private Context context;

    public SongsAdapter(Context context) {
        super(new DiffUtil.ItemCallback<Song>() {
            @Override
            public boolean areItemsTheSame(Song oldItem, Song newItem) {
                return oldItem.getTitle().equals(newItem.getTitle());
            }

            @Override
            public boolean areContentsTheSame(Song oldItem, Song newItem) {
                return oldItem.getTitle().equals(newItem.getTitle());
            }
        });
        this.context = context;
    }

    @NonNull
    @Override
    public SongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.song_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongsViewHolder holder, int position) {
        holder.bind(getItem(position),context);
    }
}
