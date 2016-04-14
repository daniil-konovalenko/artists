package com.example.helium.artists;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity {
    // log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Artists JSON url
    private String url;

    private ArtistAdapter artistAdapter;
    private ArrayList<Artist> artistsList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

        listView = (ListView) findViewById(R.id.listViewMain);
        artistAdapter = new ArtistAdapter(this, artistsList);
        listView.setAdapter(artistAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        url = getString(R.string.fetch_url);

        final JsonArrayRequest artistsRequest = getJSONArrayRequest(url);
        listView.setFastScrollEnabled(true);
        listView.setFastScrollAlwaysVisible(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ArtistActivity.class);
                Artist artist = (Artist) artistAdapter.getItem(position);
                intent.putExtra("artist", artist);
                startActivity(intent);

            }
        });

        refreshData();
    }


    protected void writeJSONToArtistList(JSONArray artists_json){
        artistsList.clear();
        for (Integer i = 0; i < artists_json.length(); i++){
            try {
                Artist current_artist = new Artist(artists_json.getJSONObject(i));
                this.artistsList.add(current_artist);
                Collections.sort(artistsList, artistComparator);
            }
            catch (JSONException e){
                e.printStackTrace();
                Log.e("JSONToArtist", e.getMessage());
            }

        }
    }

    private JsonArrayRequest getJSONArrayRequest(String url) {

        return new JsonArrayRequest(Request.Method.GET, url, "",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        writeJSONToArtistList(response);

                        artistAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public Comparator<Artist> artistComparator = new Comparator<Artist>() {
        @Override
        public int compare(Artist lhs, Artist rhs) {
            return lhs.getName().compareTo(rhs.getName());
        }
    };

    private void refreshData(){
        JsonArrayRequest request = getJSONArrayRequest(url);
        AppController.getInstance().addToRequestQueue(request);
    }

}
