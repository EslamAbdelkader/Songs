package com.eslam.songs.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.eslam.songs.viewmodel.MainActivityViewModel;
import com.eslam.songs.R;
import com.eslam.songs.model.Song;
import com.eslam.songs.adapter.SongsAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel;
    SongsAdapter adapter = new SongsAdapter(this);
    RecyclerView recyclerView;
    EditText searchEditText;
    ImageView searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getSongs().observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(@Nullable List<Song> songs) {
                updateList(songs);
            }
        });
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        searchButton = findViewById(R.id.searchButton);
        searchEditText = findViewById(R.id.searchEditText);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString();
                if (!TextUtils.isEmpty(query)) {
                    mainActivityViewModel.search(query);
                }
            }
        });
    }

    private void updateList(List<Song> songs) {
        adapter.submitList(songs);
    }
}
