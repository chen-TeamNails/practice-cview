package com.gogo.demo1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ScrollPanelListView scrollPanelListView;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        scrollPanelListView = findViewById(R.id.scrollView);
        scrollPanelListView.setAdapter(new MainAdapter(mContext,
                Arrays.asList(new String[]{"1", "2", "3", "4", "5", "1", "2", "3", "4", "5",
                        "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5",})
        ));

    }
}
