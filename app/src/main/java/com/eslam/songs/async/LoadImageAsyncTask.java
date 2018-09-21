package com.eslam.songs.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class LoadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private OnImageRetrievedListener listener;

    public LoadImageAsyncTask(OnImageRetrievedListener listener) {
        this.listener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        Bitmap bitmap = getBitmapFromURL(params[0]);

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        listener.onImageRetrieved(result);
    }


    public Bitmap getBitmapFromURL(String src) {
        try {
            if (src.startsWith("//")) {
                src = String.format("%s%s", "http:", src);
            }
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public interface OnImageRetrievedListener {
        void onImageRetrieved(Bitmap image);
    }
}
