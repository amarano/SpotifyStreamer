package com.onaram.angelomarano.spotifystreamer;

/**
 * Created by angelomarano on 6/3/15.
 */
public class ArtistSearchResults {
    private String albumName;
    private String artistArtUri;
    private String id;

    public ArtistSearchResults(String name, String url, String id) {
        this.albumName = name;
        this.artistArtUri = url;
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistArtUri() {
        return artistArtUri;
    }

    public String getId() {
        return id;
    }
}
