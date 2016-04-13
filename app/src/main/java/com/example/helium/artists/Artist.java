package com.example.helium.artists;


import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


public class Artist implements Serializable{
    String name;
    ArrayList<String> genres;
    Integer tracks;
    Integer albums;
    String description;
    String bigImageURL;
    String smallImageURL;

    Artist (String name,
            ArrayList<String> genres,
            Integer tracks,
            Integer albums,
            String description,
            String bigImageURL,
            String smallImageURL){
        this.name = name;
        this.genres = genres;
        this.tracks = tracks;
        this.albums = albums;
        this.description = description;
        this.bigImageURL = bigImageURL;
        this.smallImageURL = smallImageURL;
    }

    Artist(JSONObject json_artist) throws JSONException{
        name = json_artist.getString("name");
        genres = (ArrayList<String>) JSONHelper.toList(json_artist.getJSONArray("genres"));
        tracks =  json_artist.getInt("tracks");
        albums = json_artist.getInt("albums");
        description = json_artist.getString("description");
        bigImageURL = json_artist.getJSONObject("cover").getString("big");
        smallImageURL = json_artist.getJSONObject("cover").getString("small");

    }

    public String getGenresString() {
        return TextUtils.join(", ", genres);
    }
}

