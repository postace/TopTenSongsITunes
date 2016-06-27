package com.postace.top10songsitunes;

/**
 * Created by Hai on 6/27/2016.
 */
public class Songs {
    private String songName;
    private String artist;
    private String price;
    private String releaseDate;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Song: " + getSongName() + "\n" +
                "Artist: " + getArtist() + "\n" +
                "Price: " + getPrice() + "\n" +
                "Release date: " + getReleaseDate();
    }
}
