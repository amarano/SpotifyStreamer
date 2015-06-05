package com.onaram.angelomarano.spotifystreamer;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;


public class SearchActivity extends ListActivity {

    private ArrayList<ArtistSearchResults> searchResults;
    private ArrayList<ArtistAlbumResults> trackResults;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            this.searchForArtist(query);
        }
    }

    public void searchForArtist(String query){
        new SearchArtistAsyncTask().execute(query);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String artistId = this.searchResults.get(position).getId();
        new GetArtistAlbumsAsyncTask().execute(artistId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class GetArtistAlbumsAsyncTask extends AsyncTask<String, Void, ArrayList<ArtistAlbumResults>> {

        @Override
        protected ArrayList<ArtistAlbumResults> doInBackground(String... params) {
            String artistId = params[0];
            SpotifyApi api = new SpotifyApi();
            SpotifyService service = api.getService();
            Tracks tracks = service.getArtistTopTrack(artistId);

            for (Track track : tracks.tracks) {
                ArtistAlbumResults results = new ArtistAlbumResults(track.id, track.name, track.album.images.get(0).url);
                trackResults.add(results);
            }

            return trackResults;
        }

    }

    public class SearchArtistAsyncTask extends AsyncTask<String, Void, ArrayList<ArtistSearchResults>> {

        @Override
        protected ArrayList<ArtistSearchResults> doInBackground(String... params) {
            SpotifyApi api = new SpotifyApi();
            SpotifyService service = api.getService();
            ArtistsPager results = service.searchArtists(params[0]);
            results.artists.limit = 10;

            searchResults = new ArrayList<ArtistSearchResults>();

            List<Artist> artists = results.artists.items;

            for (Artist artist : artists) {
                ArtistSearchResults result = new ArtistSearchResults(artist.name, artist.images.get(0).url, artist.id);
                searchResults.add(result);
            }
            return searchResults;
        }

        @Override
        protected void onPostExecute(ArrayList<ArtistSearchResults> artistSearchResults) {
            ArtistSearchResultAdapter adapter = new ArtistSearchResultAdapter(getApplicationContext(),
                    R.layout.artist_row_layout,
                    artistSearchResults);
            setListAdapter(adapter);
        }
    }

}
