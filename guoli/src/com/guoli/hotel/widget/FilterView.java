package com.guoli.hotel.widget;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.guoli.hotel.R;


public class FilterView extends RelativeLayout implements OnClickListener {


    ListView mfilter1;
    Button mfilterType1;

    String preffix_1;
    int valueId_1;
    int arrayId_1;
    int selected_index_1;
    int sortValue_1;
    OnFilterClickListener listener_1;

    ListView mfilter2;
    Button mfilterType2;

    String preffix_2;
    int valueId_2;
    int arrayId_2;
    int selected_index_2;
    int sortValue_2;
    OnFilterClickListener listener_2;

    ListView mfilter3;
    Button mfilterType3;

    String preffix_3;
    int valueId_3;
    int arrayId_3;
    int selected_index_3;
    int sortValue_3;
    OnFilterClickListener listener_3;

    SpringModel[] models;
    String preffix_0;
    Button mfilterType0;
    View mfilter_0;
    int bigSelectedIndex;
    int smallSelectedIndex;
    ListView mListView_0;
    ListView mListView_1;

    public FilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.filter_widget_view, this);
//        mfilter1 = (ListView) findViewById(R.id.listView1);
//        mfilter2 = (ListView) findViewById(R.id.listView2);
//        mfilter3 = (ListView) findViewById(R.id.listView3);
        mfilterType1 = (Button) findViewById(R.id.button1);
        mfilterType2 = (Button) findViewById(R.id.button2);
        mfilterType3 = (Button) findViewById(R.id.button3);
        mfilterType0 = (Button) findViewById(R.id.button0);
        mfilterType1.setOnClickListener(mTypeClickListener_1);
        mfilterType2.setOnClickListener(mTypeClickListener_2);
        mfilterType3.setOnClickListener(mTypeClickListener_3);
        mfilter1.setOnItemClickListener(mItemClickListener_1);
        mfilter2.setOnItemClickListener(mItemClickListener_2);
        mfilter3.setOnItemClickListener(mItemClickListener_3);
//        mfilter_0 = findViewById(R.id.layout0);
//        mfilterType0.setOnClickListener(mTypeClickListener_0);
//        mListView_0 = (ListView) mfilter_0.findViewById(R.id.list0);
//        mListView_1 = (ListView) mfilter_0.findViewById(R.id.list1);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        setVisibility(View.GONE);
    }

    /**
     * 
     * @param arrayId
     *            显示的数组资源id,默认选择为第一个
     * @param valueId
     *            显示对应的值的资源id 长度必须2者保持一致，此值可不设置，即传-1;
     * @param sortId
     *            对应的排序的值，非必须参数,忽略此参数则传-1
     * @param preffix
     *            前缀，显示在上方按钮上，可以不设置
     * @param defaultPosition
     *            默认postion，不能小于0
     * @param listener
     *            点击过滤的监听
     */
    public void setFilter1(int arrayId, int valueId, int sortId, String preffix, int defaultPosition, OnFilterClickListener listener) {
        defaultPosition = Math.max(0, defaultPosition);
        arrayId_1 = arrayId;
        valueId_1 = valueId;
        sortValue_1 = sortId;
        listener_1 = listener;
        mfilter1.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.simple_list_item_single_choice, getContext().getResources()
                .getStringArray(arrayId)));
        mfilter1.setItemChecked(defaultPosition, true);
        String selected = getResources().getStringArray(arrayId)[defaultPosition];
        mfilterType1.setText(TextUtils.isEmpty(preffix) ? selected : preffix + ":" + selected);
        preffix_1 = preffix;
        mfilterType1.setVisibility(View.VISIBLE);
    }

    /**
     * 
     * @param preffix
     *            对应标题按钮上显示的前缀文字
     * @param defaultBigSelectedIndex
     *            选中的大类
     * @param defaultSmallSelectedIndex
     *            选中的小类
     * @param listener
     *            监听
     * @param model
     *            数据模型,参照类详情
     */
    public void setFilter0(String preffix, int defaultBigSelectedIndex, int defaultSmallSelectedIndex, final onFilterClickListener2 listener,
            SpringModel... model) {
        if (model == null || model.length < 1)
            return;
        models = model;
        String selected = "全部";
        preffix_0 = preffix;
        mfilterType0.setVisibility(View.VISIBLE);
        bigSelectedIndex = Math.max(0, Math.min(defaultBigSelectedIndex, model.length));
        if (model[bigSelectedIndex].sortArray > 0) {
            selected = getResources().getStringArray(model[bigSelectedIndex].sortArray)[0];
        } else {
            selected = model[bigSelectedIndex].key;
        }
        if (model.length > 0 && model[bigSelectedIndex].valueArray > 0) {
            smallSelectedIndex = Math.max(0,
                    Math.min(getResources().getStringArray(model[bigSelectedIndex].valueArray).length, defaultSmallSelectedIndex));
            selected = getResources().getStringArray(model[bigSelectedIndex].valueArray)[smallSelectedIndex];
        }
        if (bigSelectedIndex == 0 || smallSelectedIndex == 0)
            mfilterType0.setText(TextUtils.isEmpty(preffix_0) ? selected : preffix_0 + ":" + selected);
        mListView_0.setAdapter(new SpringAdapter(Arrays.asList(model), getContext()));
        mListView_0.setItemChecked(bigSelectedIndex, true);
        mListView_0.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (models[position].valueArray > 0) {
                    mListView_1.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.simple_list_item_single_choice2, getResources()
                            .getStringArray(models[position].valueArray)));
                    if (position == bigSelectedIndex) {
                        mListView_1.setItemChecked(smallSelectedIndex, true);
                    }
                } else {
                    mListView_1.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.simple_list_item_single_choice2, new String[] {}));
                }
            }
        });
        mListView_0.getOnItemClickListener().onItemClick(null, null, bigSelectedIndex, bigSelectedIndex);
        mListView_1.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bigSelectedIndex = mListView_0.getCheckedItemPosition();
                smallSelectedIndex = position;
                SpringModel model = models[bigSelectedIndex];
                String selected = model.valueArray > 0 ? getResources().getStringArray(model.valueArray)[smallSelectedIndex] : "";
                if (listener != null) {
                    listener.onFilterItemClick(model.key, "" + model.keyId, selected,
                            model.valueArrayId > 0 ? getResources().getStringArray(model.valueArrayId)[smallSelectedIndex] : null,
                            model.sortArray > 0 ? getResources().getStringArray(model.sortArray)[smallSelectedIndex] : null);
                }
                mfilterType0.setText(TextUtils.isEmpty(preffix_0) ? selected : preffix_0 + ":" + selected);
                mfilter_0.setVisibility(View.GONE);
                setVisibility(View.GONE);
            }
        });
    }

    /**
     * 
     * @param arrayId
     *            显示的数组资源id,默认选择为第一个
     * @param valueId
     *            显示对应的值的资源id 长度必须2者保持一致，此值可不设置，即传-1;
     * @param sortId
     *            对应的排序的值，非必须参数,忽略此参数则传-1
     * @param preffix
     *            前缀，显示在上方按钮上，可以不设置
     * @param defaultPosition
     *            默认postion，不能小于0
     * @param listener
     *            点击过滤的监听
     */
    public void setFilter2(int arrayId, int valueId, int sortId, String preffix, int defaultPosition, OnFilterClickListener listener) {
        defaultPosition = Math.max(0, defaultPosition);
        arrayId_2 = arrayId;
        valueId_2 = valueId;
        sortValue_2 = sortId;
        listener_2 = listener;
        mfilter2.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.simple_list_item_single_choice, getContext().getResources()
                .getStringArray(arrayId)));
        mfilter2.setItemChecked(defaultPosition, true);
        String selected = getResources().getStringArray(arrayId)[defaultPosition];
        mfilterType2.setText(TextUtils.isEmpty(preffix) ? selected : preffix + ":" + selected);
        preffix_2 = preffix;
        mfilterType2.setVisibility(View.VISIBLE);
    }

    /**
     * 
     * @param arrayId
     *            显示的数组资源id,默认选择为第一个
     * @param valueId
     *            显示对应的值的资源id 长度必须2者保持一致，此值可不设置，即传-1;
     * @param sortId
     *            对应的排序的值，非必须参数,忽略此参数则传-1
     * @param preffix
     *            前缀，显示在上方按钮上，可以不设置
     * @param defaultPosition
     *            默认postion，不能小于0
     * @param listener
     *            点击过滤的监听
     */
    public void setFilter3(int arrayId, int valueId, int sortId, String preffix, int defaultPosition, OnFilterClickListener listener) {
        defaultPosition = Math.max(0, defaultPosition);
        arrayId_3 = arrayId;
        valueId_3 = valueId;
        sortValue_3 = sortId;
        listener_3 = listener;
        mfilter3.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.simple_list_item_single_choice, getContext().getResources()
                .getStringArray(arrayId)));
        mfilter3.setItemChecked(defaultPosition, true);
        String selected = getResources().getStringArray(arrayId)[0];
        mfilterType3.setText(TextUtils.isEmpty(preffix) ? selected : preffix + ":" + selected);
        preffix_3 = preffix;
        mfilterType3.setVisibility(View.VISIBLE);
    }

    private View.OnClickListener mTypeClickListener_1 = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (mfilter1.getVisibility() == View.GONE)
                mfilter1.setVisibility(View.VISIBLE);
            else
                mfilter1.setVisibility(View.GONE);
            mfilter2.setVisibility(View.GONE);
            mfilter3.setVisibility(View.GONE);
            mfilter_0.setVisibility(View.GONE);
        }
    };
    private View.OnClickListener mTypeClickListener_2 = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            mfilter_0.setVisibility(View.GONE);
            mfilter1.setVisibility(View.GONE);
            if (mfilter2.getVisibility() == View.GONE)
                mfilter2.setVisibility(View.VISIBLE);
            else
                mfilter2.setVisibility(View.GONE);
            mfilter3.setVisibility(View.GONE);
        }
    };
    private View.OnClickListener mTypeClickListener_3 = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            mfilter_0.setVisibility(View.GONE);
            mfilter1.setVisibility(View.GONE);
            mfilter2.setVisibility(View.GONE);
            if (mfilter3.getVisibility() == View.GONE)
                mfilter3.setVisibility(View.VISIBLE);
            else
                mfilter3.setVisibility(View.GONE);
        }
    };
    private View.OnClickListener mTypeClickListener_0 = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            mfilter3.setVisibility(View.GONE);
            mfilter1.setVisibility(View.GONE);
            mfilter2.setVisibility(View.GONE);
            if (mfilter_0.getVisibility() == View.GONE)
                mfilter_0.setVisibility(View.VISIBLE);
            else
                mfilter_0.setVisibility(View.GONE);
        }
    };

    private OnItemClickListener mItemClickListener_1 = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String selected = getResources().getStringArray(arrayId_1)[position];
            mfilterType1.setText(TextUtils.isEmpty(preffix_1) ? selected : preffix_1 + ":" + selected);
            selected_index_1 = position;
            String[] values = null;
            if (valueId_1 > 0) {
                values = getResources().getStringArray(valueId_1);
            }

            if (listener_1 != null)
                listener_1.onFilterItemClick(selected, values == null ? "-1" : values[position],
                        sortValue_1 > 0 ? getResources().getStringArray(sortValue_1)[position] : sortValue_1 + "");
            mfilter1.setVisibility(View.GONE);
            setVisibility(View.GONE);
        }
    };

    private OnItemClickListener mItemClickListener_2 = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String selected = getResources().getStringArray(arrayId_2)[position];
            mfilterType2.setText(TextUtils.isEmpty(preffix_2) ? selected : preffix_2 + ":" + selected);
            selected_index_2 = position;
            String[] values = null;
            if (valueId_2 > 0) {
                values = getResources().getStringArray(valueId_2);
            }
            if (listener_2 != null)
                listener_2.onFilterItemClick(selected, values == null ? "-1" : values[position],
                        sortValue_2 > 0 ? getResources().getStringArray(sortValue_2)[position] : sortValue_2 + "");
            mfilter2.setVisibility(View.GONE);
            setVisibility(View.GONE);
        }
    };

    private OnItemClickListener mItemClickListener_3 = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String selected = getResources().getStringArray(arrayId_3)[position];
            mfilterType3.setText(TextUtils.isEmpty(preffix_3) ? selected : preffix_3 + ":" + selected);
            selected_index_3 = position;
            String[] values = null;
            if (valueId_3 > 0) {
                values = getResources().getStringArray(valueId_3);
            }
            if (listener_3 != null)
                listener_3.onFilterItemClick(selected, values == null ? "-1" : values[position],
                        sortValue_3 > 0 ? getResources().getStringArray(sortValue_3)[position] : sortValue_3 + "");
            mfilter3.setVisibility(View.GONE);
            setVisibility(View.GONE);
        }
    };

    public static interface onFilterClickListener2 {
        /**
         * 
         * @param key
         *            键名
         * @param keyId
         *            设置的键的id
         * @param value
         *            对应选中的项
         * @param valudId
         *            选中项的id
         * @param sortId
         *            选中项对应的排序id
         */
        public void onFilterItemClick(String key, String keyId, String value, String valudId, String sortId);
    }

    public static interface OnFilterClickListener {
        /**
         * 
         * @param name
         *            取决于你设置的arrayId中的值
         * @param value
         *            取决于你设置的valueId中的值
         * @param sortValue
         *            取决于你设置的sortId中的值
         */
        public void onFilterItemClick(String name, String value, String sortValue);
    }

    public static class SpringModel {
        /**
         * iconResId 图标资源id，没有传-1
         */
        public int iconResId;
        /**
         * key 显示的键名
         */
        public String key;
        /**
         * 键所对应的id，没有传-1
         */
        public int keyId;
        /**
         * valueArray 键所对应的选择项数组，没有传-1
         */
        public int valueArray;
        /**
         * valueArrayId 选择项数组对应的id，没有传-1
         * 
         */
        public int valueArrayId;
        /**
         * sortArray 选择项对应的排序数字id，没有传-1
         */
        public int sortArray;

        /**
         * 
         * @param iconResId
         *            图标资源id，没有传-1
         * @param key
         *            显示的键名
         * @param keyId
         *            键所对应的id,没有传-1
         * @param valueArray
         *            键所对应的选择项数组，没有传-1
         * @param valueArrayId
         *            选择项数组对应的id，没有传-1
         * @param sortArray
         *            选择项对应的排序数字id，没有传-1
         */
        public SpringModel(int iconResId, String key, int keyId, int valueArray, int valueArrayId, int sortArray) {
            super();
            this.iconResId = iconResId;
            this.key = key;
            this.keyId = keyId;
            this.valueArray = valueArray;
            this.valueArrayId = valueArrayId;
            this.sortArray = sortArray;
        }

    }

    private class SpringAdapter extends AbstractAdapter<SpringModel> {

        public SpringAdapter(List<SpringModel> data, Context context) {
            super(context,data );
        }

        @Override
        public View CreateView(int position, View convertView, LayoutInflater inflater) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.simple_list_item_single_choice, null, false);
            }
            CheckedTextView tv = (CheckedTextView) convertView;
            if (getItem(position).iconResId > 0)
                tv.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(getItem(position).iconResId), null, null, null);
            tv.setText(getItem(position).key);
            return convertView;
        }



	
    }
}
