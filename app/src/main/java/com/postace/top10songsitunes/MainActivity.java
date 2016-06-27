package com.postace.top10songsitunes;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private static final String URL_APPLE_RSS_FEEDS = "http://ax.itunes.apple.com/" +
            "WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml";
    private String mFileContents;
    private Button btnParse;
    private ListView xmlListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnParse = (Button) findViewById(R.id.btnParse);
        xmlListView = (ListView) findViewById(R.id.xmlListView);

        DownloadData downloadData = new DownloadData();
        downloadData.execute(URL_APPLE_RSS_FEEDS);

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Add parse activation code
                ParseSongs parseSongs = new ParseSongs(mFileContents);
                parseSongs.process();
            }
        });
    }

    /**
     * Class DownloadData
     * return : XML string represent list 10 songs
     */
    private class DownloadData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            mFileContents = downloadXMLFile(params[0]);
            if (mFileContents == null) {
                Log.d("DownloadData","An error occur");
            }

            return mFileContents;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("DownloadData", "Result: " + result);
        }

        // Download xml file from given URL, return xml string
        private String downloadXMLFile(String urlPath) {
            StringBuilder tempBuffer = new StringBuilder();
            try {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int response = connection.getResponseCode();
                Log.d("DownloadData","Response code was: " + response);
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                int charRead;
                char[] inputBuffer = new char[500];
                while (true) {
                    charRead = isr.read(inputBuffer);
                    if (charRead <= 0)                  // Ending of stream line
                        break;
                    tempBuffer.append(String.copyValueOf(inputBuffer, 0, charRead));
                }

                return tempBuffer.toString();
            } catch (IOException e) {
                Log.d("DownloadData","IO Exception reading data: " + e.getMessage());
            } catch (SecurityException se) {
                Log.d("DownloadData","Need permission " + se.getMessage());
            }

            return null;
        }

    }
}
