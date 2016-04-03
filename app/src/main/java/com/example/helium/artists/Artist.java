package com.example.helium.artists;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Artist {
    String name;
    JSONArray genres;
    Integer tracks;
    Integer albums;
    String description;
    String bigImageURL;
    String smallImageURL;

    Artist(JSONObject json_artist) throws JSONException{
        name = json_artist.getString("name");
        genres = json_artist.getJSONArray("genres");
        tracks =  json_artist.getInt("tracks");
        albums = json_artist.getInt("albums");
        description = json_artist.getString("description");
        bigImageURL = json_artist.getJSONObject("cover").getString("big");
        smallImageURL = json_artist.getJSONObject("cover").getString("small");

    }

    public String getGenresString() {
        try {
            return genres.join(", ");
        }
        catch (JSONException e){
            return "Genres not available";
        }
    }





}
