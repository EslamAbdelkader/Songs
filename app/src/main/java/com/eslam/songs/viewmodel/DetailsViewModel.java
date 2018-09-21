package com.eslam.songs.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;

import com.eslam.songs.async.LoadImageAsyncTask;
import com.eslam.songs.model.Song;

public class DetailsViewModel extends ViewModel implements LoadImageAsyncTask.OnImageRetrievedListener {
    private MutableLiveData<Song> songLiveData = new MutableLiveData<>();
    private MutableLiveData<Bitmap> imageLiveData = new MutableLiveData<>();

    public DetailsViewModel(Song song) {
        songLiveData.setValue(song);
        LoadImageAsyncTask loadImage = new LoadImageAsyncTask(this);
        loadImage.execute(song.getImageUrl());
    }

    public LiveData<Song> getSong() {
        return songLiveData;
    }

    public LiveData<Bitmap> getImage() {
        return imageLiveData;
    }

    @Override
    public void onImageRetrieved(Bitmap image) {
        imageLiveData.setValue(image);
    }
}
