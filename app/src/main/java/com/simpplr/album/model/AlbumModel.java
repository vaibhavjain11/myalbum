package com.simpplr.album.model;

/**
 * Created by vaibhavjain on 22/3/17.
 */

public class AlbumModel {

    String path;
    String name;
    String artistName;
    String  albumId;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String  getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String  albumId) {
        this.albumId = albumId;
    }
}
