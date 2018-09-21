package com.eslam.songs.holer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eslam.songs.R;
import com.eslam.songs.model.Song;
import com.eslam.songs.activity.DetailsActivity;

public class SongsViewHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView type;
    private final TextView artist;

    public SongsViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        type = itemView.findViewById(R.id.type);
        artist = itemView.findViewById(R.id.artist);
    }

    public void bind(final Song item, final Context context) {
        title.setText(item.getTitle());
        type.setText(item.getType());
        artist.setText(item.getArtist());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), DetailsActivity.class);
                intent.putExtra("Song", item);
                context.startActivity(intent);
            }
        });
    }
}
