package com.eslam.songs.holer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eslam.songs.R;
import com.eslam.songs.activity.DetailsActivity;
import com.eslam.songs.model.Song;

public class SongsViewHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView type;
    private final TextView artist;
    private final ImageView imageView;

    public SongsViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        type = itemView.findViewById(R.id.type);
        artist = itemView.findViewById(R.id.artist);
        imageView = itemView.findViewById(R.id.imageView);
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
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder);
        Glide.with(itemView).load("http:"+item.getImageUrl()).apply(requestOptions).into(imageView);
    }
}
