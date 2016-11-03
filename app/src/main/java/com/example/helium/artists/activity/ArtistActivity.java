package com.example.helium.artists.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.helium.artists.R;
import com.example.helium.artists.app.AppController;
import com.example.helium.artists.model.Artist;


public class ArtistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        Intent intent = getIntent();
        Artist artist = (Artist) intent.getSerializableExtra("artist");
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(artist.getName());
        NetworkImageView imageView = (NetworkImageView) findViewById(R.id.bigImageView);
        imageView.setImageUrl(artist.getBigImageURL(), imageLoader);

        // Setting genres
        TextView genres = (TextView) findViewById(R.id.genres);
        genres.setText(artist.getGenresString());

        // Setting number of albums and tracks
        TextView albumsTracksCount = (TextView) findViewById(R.id.albums_and_tracks_count);

        String AlbumsCount = getResources().
                getQuantityString(R.plurals.albums, artist.getAlbums(), artist.getAlbums());
        String TracksCount = getResources().
                getQuantityString(R.plurals.tracks, artist.getTracks(), artist.getTracks());

        albumsTracksCount.setText(String.format("%s, %s", AlbumsCount, TracksCount));


        // Setting description
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(artist.getDescription());

    }


}
