/**
 * Project Name:Guoli
 * File Name:HotDetailActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-11下午5:08:51
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
 */

package com.guoli.hotel.activity.hotel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.CallActivity;
import com.guoli.hotel.activity.order.EditOrderActivity;
import com.guoli.hotel.bean.HotelDetailInfo;
import com.guoli.hotel.bean.HotelParamsInfo;
import com.guoli.hotel.bean.RoomTypeInfo;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.HotelRoom;
import com.guoli.hotel.net.response.bean.RoomRespInfo;
import com.guoli.hotel.parse.HotelRoomParse;
import com.guoli.hotel.utils.CallUtils;
import com.guoli.hotel.utils.DateUtils;
import com.guoli.hotel.utils.NetUtils;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Response;

/**
 * ClassName:HotelDetailActivity <br/>
 * 
 * @Description: 酒店详情页面 Date: 2013-1-11 下午5:08:51 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class HotelDetailActivity extends CallActivity implements OnClickListener {

    private ListView mRoomsListView;
    private RoomAdapter mAdapter;
    private HotelRoom mHotelRoom;
    private TextView mInDateView;
    private TextView mOutDateView;
    private static final int DIALOG_IN_DATE = 1;
    private static final int DIALOG_OUT_DATE = 2;
    private static final String FORMAT_STYLE = "yyyy-MM-dd";
    /** 酒店房型关键字 */
    public static final String KEY_REQUEST = "hotel_room";
    RoomRespInfo info;

    private static final String TAG = HotelDetailActivity.class.getSimpleName();

    public HotelDetailActivity() {
        mLayoutId = R.layout.hotel_detail;
        mTitleTextId = R.string.hotel_detail;
        mRightDrawableId = R.drawable.btn_top_phone;
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        mHotelRoom = getHotelRoom();
        loadData();
        showLeftBtn();
        showRightBtn();
    }

    private HotelRoom getHotelRoom() {
        Intent intent = getIntent();
        if (intent == null) { return null; }
        Bundle bundle = intent.getExtras();
        if (bundle == null) { return null; }
        Object obj = bundle.get(KEY_REQUEST);
        if (!(obj instanceof HotelRoom)) { return null; }
        return (HotelRoom) obj;
    }

    private void loadData() {
        // TODO 有本地缓存的情况下,合理的逻辑应该是先查询本地数据,如果本地数据没有查询到再判断网络是否可用
        // 网络如果可以用则查询网络数据
        if (!NetUtils.isNetworkWell(this)) {
            loadLocalData();
            return;
        }
        loadNetworkData();
    }

    protected void loadNetworkData() {
        showLoadingDialog(R.string.loading_msg);
        GuoliRequest request = new GuoliRequest("hotel_room", mHotelRoom);
        Log.i(TAG, "request=" + request.Params.toParams());
        Manager.getInstance().executePoset(request, mListener);
    }

    protected void loadLocalData() {
        // TODO Auto-generated method stub
    }

    @Override
    protected void findViews() {
        TextView collectBtn = (TextView) findViewById(R.id.collection_btn);
        // TODO framelayout布局
        View picLayout = findViewById(R.id.pic_view);
        View addressLayout = findViewById(R.id.address_layout);
        View historyLayout = findViewById(R.id.history_layout);
        mInDateView = ((TextView) findViewById(R.id.inDateView));
        mOutDateView = ((TextView) findViewById(R.id.outDateView));
        mRoomsListView = (ListView) findViewById(R.id.listView1);
        collectBtn.setOnClickListener(this);
        picLayout.setOnClickListener(this);
        addressLayout.setOnClickListener(this);
        historyLayout.setOnClickListener(this);
        mInDateView.setOnClickListener(this);
        mOutDateView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = null;
        switch (v.getId()) {
        case R.id.collection_btn:

            break;
        case R.id.pic_view:
            intent = new Intent(this, PicGridActivity.class);
            startActivity(intent);
            break;
        case R.id.address_layout:
            intent = new Intent();
            intent.setClass(this, HotelLocationActivity.class);
            startActivity(intent);
            break;
        case R.id.history_layout:
            intent = new Intent();
            HotelRoom room = getHotelRoom();
            intent.putExtra(HotelInfoActivity.KEY_HOTEL_SHOPINFO_ID, room == null ? "" : room.getId());
            intent.setClass(this, HotelInfoActivity.class);
            startActivity(intent);
            break;
        case R.id.inDateView:
            showDialog(DIALOG_IN_DATE);
            break;
        case R.id.outDateView:
            showDialog(DIALOG_OUT_DATE);
            break;
        default:
            break;
        }
    }

    /**
     * 
     * initRoomsTypeViews:初始化房间类型视图. <br/>
     * 
     * @author maple
     * @param roomInfos
     * @since JDK 1.6
     */
    private void initRoomsTypeViews(List<RoomTypeInfo> roomInfos) {
        if (roomInfos == null || roomInfos.size() < 1) { return; }
        mAdapter = new RoomAdapter(roomInfos);
        mRoomsListView.setAdapter(mAdapter);
        mRoomsListView.setOnItemClickListener(mRoomOnItemClickListener);
    }

    AdapterView.OnItemClickListener mRoomOnItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Holder holder = (Holder) arg1.getTag();
            RoomModel model = mAdapter.getItem(arg2);
            if (model.isShowMore) {
                holder.more.setVisibility(View.GONE);
                model.isShowMore = false;
            } else {
                holder.more.setVisibility(View.VISIBLE);
                model.isShowMore = true;
            }
        }
    };

    private class RoomAdapter extends BaseAdapter {
        private List<RoomModel> models;
        private String[] bedTypes;
        private String[] breakfastTypes;
        private String[] addBed;
        private String[] netwrok;

        public RoomAdapter(List<RoomTypeInfo> roomInfos) {
            super();
            bedTypes = getResources().getStringArray(R.array.room_bed);
            breakfastTypes = getResources().getStringArray(R.array.room_breakfast);
            addBed = getResources().getStringArray(R.array.add_bed);
            netwrok = getResources().getStringArray(R.array.network);
            models = new ArrayList<HotelDetailActivity.RoomModel>();
            for (RoomTypeInfo roomInfo : roomInfos) {
                RoomModel model = new RoomModel();
                model.mInfo = roomInfo;
                models.add(model);
            }
        }

        @Override
        public int getCount() {
            return models.size();
        }

        @Override
        public RoomModel getItem(int position) {
            return models.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            RoomModel model = getItem(position);
            if (model == null) { return null; }
            Holder holder = null;
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.hotel_detail_room_item, null);
                holder = new Holder();
                holder.simple = view.findViewById(R.id.simple_room);
                holder.more = view.findViewById(R.id.more_room);
                holder.button = (Button) view.findViewById(R.id.scheduleBtn);
                holder.priceView = (TextView) view.findViewById(R.id.roomPriceView);
                holder.discountView = (TextView) view.findViewById(R.id.roomDiscountView);
                holder.typeNameView = (TextView) view.findViewById(R.id.typeNameView);
                holder.bedBreakfastView = (TextView) view.findViewById(R.id.bedBreakfastView);
                holder.areaView = (TextView) view.findViewById(R.id.areaView);
                holder.addBedView = (TextView) view.findViewById(R.id.addBedView);
                holder.allowSmokingView = (TextView) view.findViewById(R.id.smokeView);
                holder.networkView = (TextView) view.findViewById(R.id.networkView);
                holder.floorView = (TextView) view.findViewById(R.id.floorView);
                holder.bedWithView = (TextView) view.findViewById(R.id.bedWidthView);
                holder.numView = (TextView) view.findViewById(R.id.numView);

                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            initItemViews(model, holder);
            holder.button.setOnClickListener(new ButtonOnClickListene(position));
            return view;
        }

        /** 预定、电询按钮事件 */
        private class ButtonOnClickListene implements View.OnClickListener {
            int position;
            
            public ButtonOnClickListene(int position) {
                super();
                this.position = position;
            }

            @Override
            public void onClick(View v) {
                Object obj = v.getTag();
                float price = 0;
                if (obj instanceof Float) {
                    price = (Float) obj;
                }
                if (price > 1) { // 预订
                    if(mHotelRoom==null||getItem(position)==null||info==null){
                        Toast.makeText(HotelDetailActivity.this,
                                "操作无效",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.setClass(HotelDetailActivity.this, EditOrderActivity.class);
                    intent.putExtra("HOTEL_NAME", info.getHotelInfo().getName());
                    intent.putExtra(EditOrderActivity.HOTEL_ROOM, new Gson().toJson(mHotelRoom));
                    intent.putExtra(EditOrderActivity.ROOMI_TYPE, new Gson().toJson(getItem(position)));
                    startActivity(intent);
                    return;
                }
                // 电询
                CallUtils utils = new CallUtils(HotelDetailActivity.this);
                utils.callServer();
            }
        };

        /**
         * 
         * initItemViews:初始化listView item. <br/>
         * 
         * @author maple
         * @param model
         * @param holder
         * @since JDK 1.6
         */
        private void initItemViews(RoomModel model, Holder holder) {
            if (model == null || holder == null || model.mInfo == null) { return; }
            RoomTypeInfo info = model.mInfo;
            holder.more.setVisibility(model.isShowMore ? View.VISIBLE : View.GONE);
            holder.typeNameView.setText(info.getName() + "");
            holder.discountView.setText(formatDiscount(info.getDiscount()));
            holder.priceView.setText("￥" + (int) info.getPrice());
            int bebType = info.getBedType();
            String bedContent = "";
            if (bebType - 1 > 0 && bebType - 1 < bedTypes.length) {
                bedContent = bedTypes[bebType - 1];
            }
            int breakfastType = info.getBreakfastType();
            String breakfastContent = "";
            if (breakfastType - 1 > 0 && breakfastType - 1 < breakfastTypes.length) {
                breakfastContent = breakfastTypes[breakfastType - 1];
            }
            String bedBreakfast = getResources().getString(R.string.bed_and_breakfast);
            bedBreakfast = String.format(bedBreakfast, bedContent, breakfastContent);
            holder.bedBreakfastView.setText(bedBreakfast);
            // 初始化隐藏视图内容
            holder.areaView.setText(formatContent(R.string.room_area, info.getArea() + ""));
            holder.addBedView.setText(formatContent(R.string.add_bed, addBed[info.getIsAddBed()]));
            holder.allowSmokingView.setText(formatContent(R.string.allow_smoking, "否"));
            holder.networkView.setText(formatContent(R.string.have_net, netwrok[info.getBroadbandType() - 1]));
            holder.floorView.setText(formatContent(R.string.floor_num, "8"));
            holder.bedWithView.setText(formatContent(R.string.bed_width, "1.8"));
            holder.numView.setText(formatContent(R.string.pople_num, info.getCheckNum() + ""));

            String btnTex = getResources().getString(info.getActprice() < 1 ? R.string.call : R.string.book);
            holder.button.setText(btnTex);
            holder.button.setTag(info.getActprice());
        }

        private String formatContent(int resId, String value) {
            String content = getResources().getString(resId);
            return String.format(content, value);
        }

        /**
         * 
         * formatDiscount:格式化折扣. <br/>
         * 
         * @author maple
         * @param discount
         * @return
         * @since JDK 1.6
         */
        private String formatDiscount(double discount) {
            return new DecimalFormat("0.##").format(discount);
        }
    }

    static class RoomModel {
        RoomTypeInfo mInfo;
        boolean isShowMore;
    }

    private class Holder {
        TextView typeNameView;
        TextView discountView;
        TextView priceView;
        TextView bedBreakfastView;
        TextView areaView;
        TextView addBedView;
        TextView allowSmokingView;
        TextView networkView;
        TextView floorView;
        TextView bedWithView;
        TextView numView;
        @SuppressWarnings("unused")
        View simple;
        View more;
        Button button;
    }

    private IResponseListener mListener = new IResponseListener() {

        @Override
        public void onSuccess(Response response) {
            dismissLoadingDialog();
            Log.i(TAG, "response=" + (response == null ? null : response.result));
            if (response == null) {
            return;
            }
             info = new HotelRoomParse().parseResponse(response);
            
            initViews(info);
        }

        @Override
        public void onError(Response response) {
            dismissLoadingDialog();
        }
    };

    /**
     * 
     * initViews:初始化视图. <br/>
     * 
     * @author maple
     * @param respInfo
     * @since JDK 1.6
     */
    private void initViews(RoomRespInfo respInfo) {
        if (respInfo == null) { return; }
        HotelDetailInfo info = respInfo.getHotelInfo();
        if (info != null) {
            ((TextView) findViewById(R.id.name_view)).setText(info.getName());
            ((RatingBar) findViewById(R.id.star_level)).setNumStars(info.getStar());
            ((TextView) findViewById(R.id.address_textview)).setText(info.getAddress());
            String count = getResources().getString(R.string.pic_count);
            count = String.format(count, respInfo.getPicCount());
            ((TextView) findViewById(R.id.pic_count_view)).setText(count);
            // 酒店历史
            initHistoryViews(info);
        }
        HotelParamsInfo paramsInfo = respInfo.getParamsInfo();
        if (paramsInfo != null) {
            // 入住日期
            mInDateView.setText(paramsInfo.getStartDate());
            // 离店日期
            mOutDateView.setText(paramsInfo.getEndDate());
        }
        initRoomsTypeViews(respInfo.getRoomTypeInfos());
    }

    private void initHistoryViews(HotelDetailInfo info) {
        if (info == null) { return; }
        String history = getResources().getString(R.string.history);
        history = String.format(history, info.getOpendate(), info.getDecorationdate());
        ((TextView) findViewById(R.id.history_layout)).setText(history);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Log.i("SearchHotelActivity", "onCreateDialog()--->.....");
        switch (id) {
        case DIALOG_IN_DATE:
            DateInfo info = getInDateInfo();
            return new DatePickerDialog(this, mInDateListener, info.year, info.month, info.day);
        case DIALOG_OUT_DATE:
            DateInfo leaveInfo = getOutDateInfo();
            return new DatePickerDialog(this, mOutDateListener, leaveInfo.year, leaveInfo.month, leaveInfo.day);
        default:
            break;
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        Log.i("SearchHotelActivity", "onPrepareDialog()--->.....");
        switch (id) {
        case DIALOG_IN_DATE:
            DateInfo info = getInDateInfo();
            if (info != null) {
                ((DatePickerDialog) dialog).updateDate(info.year, info.month, info.day);
            }
            break;
        case DIALOG_OUT_DATE:
            DateInfo leaveInfo = getOutDateInfo();
            if (leaveInfo != null) {
                ((DatePickerDialog) dialog).updateDate(leaveInfo.year, leaveInfo.month, leaveInfo.day);
            }
            break;
        default:
            break;
        }
    }

    /**
     * 
     * getOccupancyDateInfo:获取入住时间对象. <br/>
     * 
     * @author maple
     * @return
     * @since JDK 1.6
     */
    private DateInfo getInDateInfo() {
        CharSequence dateChar = mInDateView.getText();
        String date = (TextUtils.isEmpty(dateChar) ? DateUtils.getCurrentDate(FORMAT_STYLE)
                : (String) dateChar);
        return convertToDateInfo(date);
    }

    /**
     * 
     * getLeaveDateInfo:获取离店日期对象. <br/>
     * 
     * @author maple
     * @return
     * @since JDK 1.6
     */
    private DateInfo getOutDateInfo() {
        CharSequence startDate = mInDateView.getText();
        CharSequence endDate = mOutDateView.getText();
        String date = null;
        if (TextUtils.isEmpty(startDate) && TextUtils.isEmpty(endDate)) {
            date = DateUtils.getCurrentDate(FORMAT_STYLE);
            return convertToDateInfo(date);
        }
        if (TextUtils.isEmpty(endDate) && !TextUtils.isEmpty(startDate)) { return convertToDateInfo((String) startDate); }
        return convertToDateInfo((String) endDate);
    }

    /**
     * 
     * convertToDateInfo:把yyyy-MM-dd格式的日子字符串转换为一个DateInfo对象. <br/>
     * 
     * @author maple
     * @param date
     *            yyyy-MM-dd
     * @return
     * @since JDK 1.6
     */
    private DateInfo convertToDateInfo(String date) {
        if (TextUtils.isEmpty(date)) { return null; }
        DateInfo info = new DateInfo();
        info.year = DateUtils.getYearFromString(date, FORMAT_STYLE);
        info.month = DateUtils.getMonthFromString(date, FORMAT_STYLE);
        info.day = DateUtils.getDayFromString(date, FORMAT_STYLE);
        return info;
    }

    private OnDateSetListener mInDateListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String date = DateUtils.getDateWithFormat(year, monthOfYear, dayOfMonth, FORMAT_STYLE);
            mInDateView.setText(date);
        }
    };

    private OnDateSetListener mOutDateListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String date = DateUtils.getDateWithFormat(year, monthOfYear, dayOfMonth, FORMAT_STYLE);
            mOutDateView.setText(date);
        }
    };

    private class DateInfo {
        int year;
        int month;
        int day;
    }
}
