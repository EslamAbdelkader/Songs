package com.eslam.songs.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.eslam.songs.async.LoadListAsyncTask;
import com.eslam.songs.model.Song;

import java.util.List;

public class MainActivityViewModel extends ViewModel implements LoadListAsyncTask.OnSongsRetrieved {
    private MutableLiveData<List<Song>> songsLiveData = new MutableLiveData<>();

    public LiveData<List<Song>> getSongs() {
        return songsLiveData;
    }

    public void search(String query) {
        AsyncTask<String, Void, List<Song>> asyncTask = new LoadListAsyncTask(this);
        asyncTask.execute(query);
    }

    @Override
    public void onSongsRetrieved(List<Song> songs) {
        songsLiveData.setValue(songs);
    }

}
