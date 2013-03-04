package com.guoli.hotel.activity.hotel;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.widget.AbstractAdapter;

public class PicGridActivity extends BaseActivity2 implements OnItemClickListener {

    GridView mGridView;

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        mGridView = (GridView) findViewById(R.id.gridView1);
        mGridView.setAdapter(new PicGridAdapter(this, getData()));
        mGridView.setOnItemClickListener(this);
    }

    @Override
    public int getContentId() {
        return R.layout.pic_grid_activity;
    }

    public ArrayList<PicModel> getData() {
        ArrayList<PicModel> data = new ArrayList<PicGridActivity.PicModel>();
        for (int i = 0; i < 11; i++) {
            PicModel model = new PicModel();
            model.url = "";
            model.name = "图(" + (i + 1) + ")";
            data.add(model);
        }
        return data;
    }

    public static class PicGridAdapter extends AbstractAdapter<PicModel> {

        public PicGridAdapter(Context context, List<PicModel> data) {
            super(context, data);
        }

        @Override
        public View CreateView(int position, View convertView, LayoutInflater inflater) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.pic_grid_cell, null);
            }
            ((TextView) convertView.findViewById(R.id.name)).setText(getItem(position).name);
            return convertView;
        }

    }

   
    public static class PicModel implements Parcelable {

        public static final Parcelable.Creator<PicModel> CREATOR = new Parcelable.Creator<PicModel>() {
            public PicModel createFromParcel(Parcel in) {
                return new PicModel(in);
            }

            public PicModel[] newArray(int size) {
                return new PicModel[size];
            }
        };
        
        public PicModel() {
            super();
        }

        public String url;
        public String name;

        public PicModel(Parcel in) {
            url=in.readString();
            name=in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(url);
            dest.writeString(name);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Intent intent = new Intent(this, BigPicActivity.class);
        intent.putParcelableArrayListExtra("data", getData());
        intent.putExtra("index", arg2);
        startActivity(intent);
    }

}
