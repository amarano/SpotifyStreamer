package com.onaram.angelomarano.spotifystreamer;

/**
 * Created by angelomarano on 6/3/15.
 */
public class ArtistSearchResults {
    private String albumName;

    public ArtistSearchResults(String name) {
        albumName = name;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
