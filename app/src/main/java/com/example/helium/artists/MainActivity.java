package com.example.helium.artists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;



import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    // log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Artists JSON url
    private String url;

    private ArtistAdapter artistAdapter;
    private List<Artist> artists_list = new ArrayList<>();

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewMain);
        artistAdapter = new ArtistAdapter(this, artists_list);
        listView.setAdapter(artistAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        url = getString(R.string.fetch_url);

        JsonArrayRequest artistsRequest = new JsonArrayRequest(Request.Method.GET, url, "",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        writeJSONToArtistList(response);

                        artistAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        });

        AppController.getInstance().addToRequestQueue(artistsRequest);

    }


    protected void writeJSONToArtistList(JSONArray artists_json){

        for (Integer i = 0; i < artists_json.length(); i++){
            try {
                Artist current_artist = new Artist(artists_json.getJSONObject(i));
                this.artists_list.add(current_artist);
            }
            catch (JSONException e){
                e.printStackTrace();
                Log.e("JSONToArtist", e.getMessage());
            }

        }
    }
    
}
