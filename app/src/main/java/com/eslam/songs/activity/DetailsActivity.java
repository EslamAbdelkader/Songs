package com.eslam.songs.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.eslam.songs.viewmodel.DetailsViewModel;
import com.eslam.songs.viewmodel.DetailsViewModelProviderFactory;
import com.eslam.songs.R;
import com.eslam.songs.model.Song;

public class DetailsActivity extends AppCompatActivity {

    private TextView title;
    private TextView type;
    private TextView artist;
    private ImageView imageView;

    private DetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Song song = getIntent().getParcelableExtra("Song");

        viewModel = ViewModelProviders.of(this, new DetailsViewModelProviderFactory(song)).get(DetailsViewModel.class);

        title = findViewById(R.id.title);
        type = findViewById(R.id.type);
        artist = findViewById(R.id.artist);
        imageView = findViewById(R.id.imageView);

        viewModel.getSong().observe(this, new Observer<Song>() {
            @Override
            public void onChanged(@Nullable Song song) {
                title.setText(song.getTitle());
                type.setText(song.getType());
                artist.setText(song.getArtist());
            }
        });

        viewModel.getImage().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(@Nullable Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        });
    }
}
