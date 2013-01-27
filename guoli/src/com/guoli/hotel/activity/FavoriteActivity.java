package com.guoli.hotel.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.guoli.hotel.R;

public class FavoriteActivity extends BaseActivity2 {

	ListView favorite;
	FavoriteAdapter adapter;


    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        favorite = (ListView) findViewById(R.id.favoriteList);
        adapter = new FavoriteAdapter(FavoriteActivity.this);
        favorite.setAdapter(adapter);        
    }

    @Override
    public int getContentId() {
        return R.layout.favorite;
    }

}
