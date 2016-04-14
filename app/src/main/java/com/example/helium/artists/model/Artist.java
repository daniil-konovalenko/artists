package com.example.helium.artists.model;


import android.text.TextUtils;

import com.example.helium.artists.util.JSONHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


public class Artist implements Serializable{
    private String name;
    private ArrayList<String> genres;
    private Integer tracks;
    private Integer albums;
    private String description;
    private String bigImageURL;
    private String smallImageURL;

    public Artist (String name,
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

    public Artist(JSONObject json_artist) throws JSONException{
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

    public String getName(){
        return name;
    }

    public ArrayList<String> getGenres(){
        return genres;
    }

    public Integer getTracks(){
        return tracks;
    }

    public Integer getAlbums(){
        return albums;
    }

    public String getDescription(){
        return description;
    }

    public String getBigImageURL(){
        return bigImageURL;
    }

    public String getSmallImageURL(){
        return smallImageURL;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGenres(ArrayList<String> genres){
        this.genres = genres;
    }

    public void setTracks(Integer tracks){
        this.tracks = tracks;
    }

    public void setAlbums(Integer albums){
        this.albums = albums;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setBigImageURL(String url){
        this.bigImageURL = url;
    }

    public void setSmallImageURL(String url){
        this.smallImageURL = url;
    }



}

