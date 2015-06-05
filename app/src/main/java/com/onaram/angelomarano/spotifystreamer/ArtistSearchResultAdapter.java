package com.onaram.angelomarano.spotifystreamer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by angelomarano on 6/3/15.
 */
public class ArtistSearchResultAdapter extends ArrayAdapter<ArtistSearchResults> {

    public ArtistSearchResultAdapter(Context context, int resource, ArtistSearchResults[] objects) {
        super(context, resource, objects);
    }

    public ArtistSearchResultAdapter(Context context, int resource, List<ArtistSearchResults> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.artist_row_layout, null);
        }

        ArtistSearchResults item = getItem(position);

        if (item != null) {
            TextView artistNameView = (TextView) v.findViewById(R.id.artistName);
            artistNameView.setText(item.getAlbumName());
            ImageView artistArt = (ImageView) v.findViewById(R.id.artistArt);
            Picasso.with(getContext()).load(item.getArtistArtUri()).into(artistArt);
        }

        return v;

    }
}
