package com.eslam.songs.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Song implements Parcelable {
    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel source) {
            return new Song(source);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
    private String title;
    private String type;
    private String imageUrl;
    private String artist;

    public Song(String title, String type, String imageUrl, String artist) {
        this.title = title;
        this.type = type;
        this.imageUrl = imageUrl;
        this.artist = artist;
    }

    public Song(JSONObject jsonObject) {
        try {
            title = jsonObject.getString("title");
            type = jsonObject.getString("type");
            imageUrl = jsonObject.getJSONObject("cover").getString("large");
            artist = jsonObject.getJSONObject("mainArtist").getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
            populateDefaultData();
        }
    }

    private void populateDefaultData() {
        if(title == null)
            title = "";
        if(type == null)
            type = "";
        if (imageUrl == null)
            imageUrl = "";
        if(artist == null)
            imageUrl = "";
    }

    protected Song(Parcel in) {
        this.title = in.readString();
        this.type = in.readString();
        this.imageUrl = in.readString();
        this.artist = in.readString();
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.type);
        dest.writeString(this.imageUrl);
        dest.writeString(this.artist);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) &&
                Objects.equals(type, song.type) &&
                Objects.equals(imageUrl, song.imageUrl) &&
                Objects.equals(artist, song.artist);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, type, imageUrl, artist);
    }
}
