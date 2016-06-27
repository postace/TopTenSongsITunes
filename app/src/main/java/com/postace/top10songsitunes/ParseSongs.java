package com.postace.top10songsitunes;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Hai on 6/27/2016.
 */
public class ParseSongs {
    private String xmlData;
    private ArrayList<Songs> songs;

    public ParseSongs(String _xmlData) {
        this.xmlData = _xmlData;
        songs = new ArrayList<>();
    }

    public ArrayList<Songs> getSongs() {
        return songs;
    }

    // Processing xml and adding it to song's object
    public boolean process() {
        Songs currentSong = null;
        boolean inEntry = false;
        String textValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(this.xmlData));           // read xml
            int eventType = xpp.getEventType();
            // loop all of xml
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("entry")) {
                            inEntry = true;
                            currentSong = new Songs();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (inEntry) {
                            if (tagName.equalsIgnoreCase("entry")) {
                                songs.add(currentSong);
                                inEntry = false;
                            } else if (tagName.equalsIgnoreCase("name")) {
                                currentSong.setSongName(textValue);
                            } else if (tagName.equalsIgnoreCase("artist")) {
                                currentSong.setArtist(textValue);
                            } else if (tagName.equalsIgnoreCase("price")) {
                                currentSong.setPrice(textValue);
                            } else if (tagName.equalsIgnoreCase("releaseDate")) {
                                currentSong.setReleaseDate(textValue);
                            }
                        }
                        break;
                    default:
                        // nothing else to do
                }
                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Songs data: songs) {
            Log.d("SongsInfo", "------------------");
            Log.d("SongsInfo", "Name: " + data.getSongName());
            Log.d("SongsInfo", "Artist: " + data.getArtist());
            Log.d("SongsInfo", "Price: " + data.getPrice());
            Log.d("SongsInfo", "Release date: " + data.getReleaseDate());
        }

        return true;
    }
}
