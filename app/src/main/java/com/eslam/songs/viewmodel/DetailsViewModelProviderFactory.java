package com.eslam.songs.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.eslam.songs.model.Song;

public class DetailsViewModelProviderFactory implements ViewModelProvider.Factory {
    private Song song;

    public DetailsViewModelProviderFactory(Song song) {
        this.song = song;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
            return (T) new DetailsViewModel(song);
        }
        return null;
    }
}
