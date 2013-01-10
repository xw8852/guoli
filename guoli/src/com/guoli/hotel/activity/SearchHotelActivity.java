package com.guoli.hotel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.guoli.hotel.R;

public class SearchHotelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_hotel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_hotel, menu);
        return true;
    }

}
