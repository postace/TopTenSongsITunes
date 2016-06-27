package com.postace.top10songsitunes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private static final String URL_APPLE_RSS_FEEDS = "http://ax.itunes.apple.com/" +
            "WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml";
    private Button btnParse;
    private ListView xmlListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnParse = (Button) findViewById(R.id.btnParse);
        xmlListView = (ListView) findViewById(R.id.xmlListView);
    }
}
