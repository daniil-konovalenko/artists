package com.example.helium.artists.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.helium.artists.R;
import com.example.helium.artists.app.AppController;
import com.example.helium.artists.model.Artist;

import java.util.List;


public class ArtistAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private List<Artist> objects;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ArtistAdapter(Context context, List<Artist> artists_list){
        this.ctx = context;
        this.objects = artists_list;
        this.lInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        if (objects != null)
        return objects.size();
        else return 0;
    }

    @Override
    public Object getItem(int position){
        return objects.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;

        if (lInflater == null){
            lInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null){
            view = lInflater.inflate(R.layout.artist_layout, parent, false);
        }
        if (imageLoader == null){
            imageLoader = AppController.getInstance().getImageLoader();
        }


        NetworkImageView coverImageView = (NetworkImageView) view.findViewById(R.id.coverImageView);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView genres = (TextView) view.findViewById(R.id.genres);
        TextView albumsTracksCount = (TextView) view.findViewById(R.id.albumsTracksCount);

        // Getting artist from the row
        Artist artist = (Artist) getItem(position);

        // cover image
        coverImageView.setImageUrl(artist.getSmallImageURL(), imageLoader);

        // Artist's name
        name.setText(artist.getName());

        // Artist's genres
        genres.setText(artist.getGenresString());

        // Number of artist's albums and tracks in proper plural form

        String AlbumsCount = ctx.getResources().
                getQuantityString(R.plurals.albums, artist.getAlbums(), artist.getAlbums());
        String TracksCount = ctx.getResources().
                getQuantityString(R.plurals.tracks, artist.getTracks(), artist.getTracks());

        albumsTracksCount.setText(String.format("%s, %s", AlbumsCount, TracksCount));

        return view;
    }
}
