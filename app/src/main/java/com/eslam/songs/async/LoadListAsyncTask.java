package com.eslam.songs.async;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.eslam.songs.model.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadListAsyncTask extends AsyncTask<String, Void, List<Song>> {

    private OnSongsRetrieved listener;

    public LoadListAsyncTask(OnSongsRetrieved listener) {
        this.listener = listener;
    }

    @Override
    protected List<Song> doInBackground(String... strings) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("X-MM-GATEWAY-KEY", "Ge6c853cf-5593-a196-efdb-e3fd7b881eca");
            JSONTokener tokener = getResult("http://staging-gateway.mondiamedia.com/v0/api/gateway/token/client", "POST", headers);
            JSONObject jsonObject = new JSONObject(tokener);
            String accessToken = jsonObject.getString("accessToken");

            headers.clear();
            headers.put("Authorization","Bearer "+accessToken);
            tokener = getResult("http://staging-gateway.mondiamedia.com/v2/api/sayt/flat?query="+strings[0]+"&limit=20","GET",headers);
            JSONArray array = new JSONArray(tokener);
            ArrayList<Song> songs = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                Song song = new Song(array.getJSONObject(i));
                songs.add(song);
            }
            return songs;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Song> songs) {
        super.onPostExecute(songs);
        listener.onSongsRetrieved(songs);
    }

    @NonNull
    private JSONTokener getResult(String url, String method, Map<String, String> headers) throws IOException {
        URL httpURL = new URL(url);
        HttpURLConnection httpConn = (HttpURLConnection) httpURL.openConnection();
        httpConn.setRequestMethod(method);
        if (headers != null) {
            for (String key : headers.keySet()) {
                httpConn.setRequestProperty(key, headers.get(key));
            }
        }
        InputStream inputStream = httpConn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer readTextBuf = new StringBuffer();
        String line = bufferedReader.readLine();
        while (line != null) {
            // Append the text to string buffer.
            readTextBuf.append(line);

            // Continue to read text line.
            line = bufferedReader.readLine();
        }
        return new JSONTokener(readTextBuf.toString());
    }

    public interface OnSongsRetrieved {
        void onSongsRetrieved(List<Song> songs);
    }
}
