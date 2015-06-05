package com.onaram.angelomarano.spotifystreamer;

/**
 * Created by angelomarano on 6/3/15.
 */
public class ArtistSearchResults {
    private String albumName;
    private String artistArtUri;

    public ArtistSearchResults(String name) {
        albumName = name;
    }

    public ArtistSearchResults(String name, String artUri) {
        this.albumName = name;
        this.artistArtUri = artUri;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistArtUri() {
        return artistArtUri;
    }

    public void setArtistArtUri(String artistArtUri) {
        this.artistArtUri = artistArtUri;
    }
}
