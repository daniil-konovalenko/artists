package com.example.helium.artists;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

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

        setTitle(artist.name);
        NetworkImageView imageView = (NetworkImageView) findViewById(R.id.bigImageView);
        imageView.setImageUrl(artist.bigImageURL, imageLoader);


    }

}
