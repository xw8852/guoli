package com.guoli.hotel.activity.hotel;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.DatePicker;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.CallActivity;
import com.guoli.hotel.utils.DateUtils;
import com.guoli.hotel.widget.BottomTabbar;

public class SearchHotelActivity extends CallActivity implements OnItemSelectedListener {

    /** 城市标识 */
    public static final int PAGE_CITY = 0;
    /** 入住时间标识 */
    public static final int PAGE_OCCUPANCY_DATE = 1;
    /** 离店时间标识 */
    public static final int PAGE_LEAVE_DATE = 2;
    /** 价格标识 */
    public static final int PAGE_PRICE = 3;
    /** 星级标识 */
    public static final int PAGE_LEVEL = 4;
    /** 行政区域标识 */
    public static final int PAGE_AREA = 5;
    /** 入住时间选择弹出框标识 */
    private static final int DIALOG_DATEPICKER_OCCUPANCY = 1;
    /** 离店时间选择弹出框标识 */
    private static final int DIALOG_DATEPICKER_LEAVE = 2;
    
    private static final String FORMAT_STYLE = "yyyy-MM-dd";

    /** 入住城市 */
    private TextView mCityView;
    /** 入住日期 */
    private TextView mOccupancyView;
    /** 离店日期 */
    private TextView mLeaveView;
    /** 价格 */
    private TextView mPriceView;
    /** 星级 */
    private TextView mLevelView;
    /** 行政区域 */
    private TextView mAreaView;

    /***/
    /***/

    public SearchHotelActivity() {
        mLayoutId = R.layout.search_hotel;
        mRightDrawableId = R.drawable.btn_top_phone;
        mTitleTextId = R.string.search_hotel_title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showRightBtn();
        new BottomTabbar(this, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_hotel, menu);
        return true;
    }

    @Override
    protected void findViews() {
        mLeaveView = (TextView) findViewById(R.id.leave_date_btn);
        TextView searchBtn = (TextView) findViewById(R.id.search_btn);
        mOccupancyView = (TextView) findViewById(R.id.occupancy_date_btn);
        mCityView = (TextView) findViewById(R.id.cityName);
        mPriceView = (TextView) findViewById(R.id.priceBtn);
        mLevelView = (TextView) findViewById(R.id.starBtn);
        mAreaView = (TextView) findViewById(R.id.areaBtn);

        mOccupancyView.setOnClickListener(this);
        mLeaveView.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        mCityView.setOnClickListener(this);
        mLevelView.setOnClickListener(this);
        mAreaView.setOnClickListener(this);
        mPriceView.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    public void onClick(View v) {
        super.onClick(v);
        Intent intent = null;
        switch (v.getId()) {
        case R.id.cityName:
            // 入住城市
            intent = new Intent();
            intent.setClass(this, CitySelectActivity.class);
            startActivityForResult(intent, PAGE_CITY);
            break;
        case R.id.occupancy_date_btn:
            // 入住日期
            /*
             * intent = new Intent(); intent.setClass(this, OccupancyDateSelectActivity.class);
             * startActivityForResult(intent, PAGE_OCCUPANCY_DATE);
             */
            showDialog(DIALOG_DATEPICKER_OCCUPANCY);
            break;
        case R.id.leave_date_btn:
            // 离店时间
            /*
             * intent = new Intent(); intent.setClass(this, LeaveDateSelectActivity.class);
             * startActivityForResult(intent, PAGE_LEAVE_DATE);
             */
            showDialog(DIALOG_DATEPICKER_LEAVE);
            break;
        case R.id.priceBtn:
            // 价格
            intent = new Intent();
            intent.setClass(this, PriceListActivity.class);
            startActivityForResult(intent, PAGE_PRICE);
            break;
        case R.id.starBtn:
            // 星级
            intent = new Intent();
            intent.setClass(this, LevelListActivity.class);
            startActivityForResult(intent, PAGE_LEVEL);
            break;
        case R.id.areaBtn:
            // 区域
            intent = new Intent();
            intent.setClass(this, AreaListActivity.class);
            startActivityForResult(intent, PAGE_AREA);
            break;
        case R.id.search_btn:
            // 搜索
            enterHotelSearchResultActivity();
            break;
        default:
            break;
        }
    }

    /**
     * 
     * enterHotelSearchResultActivity:跳转到酒店查询结果页�? <br/>
     * 
     * @author maple
     * @since JDK 1.6
     */
    private void enterHotelSearchResultActivity() {
        // 跳转到酒店搜索结果页�?
        Intent intent = new Intent();
        intent.setClass(this, HotelSearchResultActivity.class);
        startActivity(intent);
    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
        case PAGE_CITY:
            String cityName = data == null ? "" : data.getStringExtra(CitySelectActivity.KEY_CITY_NAME);
            setViewText(mCityView, cityName);
            break;
        case PAGE_OCCUPANCY_DATE:
            String date = data == null ? "" : data.getStringExtra(DateSelectActivity.KEY_DATE);
            setViewText(mOccupancyView, date);
            break;
        case PAGE_LEAVE_DATE:
            String leaveDate = data == null ? "" : data.getStringExtra(DateSelectActivity.KEY_DATE);
            setViewText(mLeaveView, leaveDate);
            break;
        case PAGE_PRICE:
            String price = data == null ? "" : data.getStringExtra(PriceListActivity.KEY_PRICE);
            setViewText(mPriceView, price);
            break;
        case PAGE_LEVEL:
            String level = data == null ? "" : data.getStringExtra(LevelListActivity.KEY_LEVEL);
            setViewText(mLevelView, level);
            break;
        case PAGE_AREA:
            String area = data == null ? "" : data.getStringExtra(AreaListActivity.KEY_AREA);
            setViewText(mAreaView, area);
            break;

        default:
            break;
        }
    }

    /**
     * 
     * setViewText:设置textView的值. <br/>
     * 
     * @author maple
     * @param view
     * @param text
     * @since JDK 1.6
     */
    private void setViewText(TextView view, String text) {
        if (view == null) { return; }
        view.setText(text == null ? "" : text);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Log.i("SearchHotelActivity", "onCreateDialog()--->.....");
        switch (id) {
        case DIALOG_DATEPICKER_OCCUPANCY:
            DateInfo info = getOccupancyDateInfo();
            return new DatePickerDialog(this, occupancyDateListener, info.year, info.month, info.day);
        case DIALOG_DATEPICKER_LEAVE:
            DateInfo leaveInfo = getLeaveDateInfo();
            return new DatePickerDialog(this, leaveDateListener, leaveInfo.year, leaveInfo.month, leaveInfo.day);
        default:
            break;
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        Log.i("SearchHotelActivity", "onPrepareDialog()--->.....");
        switch (id) {
        case DIALOG_DATEPICKER_OCCUPANCY:
            DateInfo info = getOccupancyDateInfo();
            if (info != null) {
                ((DatePickerDialog) dialog).updateDate(info.year, info.month, info.day);
            }
            break;
        case DIALOG_DATEPICKER_LEAVE:
            DateInfo leaveInfo = getLeaveDateInfo();
            if (leaveInfo != null) {
                ((DatePickerDialog) dialog).updateDate(leaveInfo.year, leaveInfo.month, leaveInfo.day);
            }
            break;
        default:
            break;
        }
    }

    private OnDateSetListener occupancyDateListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String date = DateUtils.getDateWithFormat(year, monthOfYear, dayOfMonth, FORMAT_STYLE);
            mOccupancyView.setText(date);
        }
    };

    private OnDateSetListener leaveDateListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String date = DateUtils.getDateWithFormat(year, monthOfYear, dayOfMonth, FORMAT_STYLE);
            mLeaveView.setText(date);
            //TODO 此处系统默认的执行两次,待确认.
            /*if (checkLeaveDate((String) mOccupancyView.getText(), date)) {
                mLeaveView.setText(date);
                return;
            }
            String msg = getString(R.string.dialog_date_check_msg);
            DialogUtils.showDialog("", msg, SearchHotelActivity.this);*/
        }
    };

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
    
    /**
     * 
     * getOccupancyDateInfo:获取入住时间对象. <br/>
     * @author maple
     * @return
     * @since JDK 1.6
     */
    private DateInfo getOccupancyDateInfo(){
        CharSequence dateChar = mOccupancyView.getText();
        String date = (TextUtils.isEmpty(dateChar) ? DateUtils.getCurrentDate(FORMAT_STYLE)
                : (String) dateChar);
        return convertToDateInfo(date);
    }
    
    /**
     * 
     * getLeaveDateInfo:获取离店日期对象. <br/>
     * @author maple
     * @return
     * @since JDK 1.6
     */
    private DateInfo getLeaveDateInfo(){
        CharSequence startDate = mOccupancyView.getText();
        CharSequence endDate = mLeaveView.getText();
        String date = null;
        if (TextUtils.isEmpty(startDate) && TextUtils.isEmpty(endDate)) {
            date = DateUtils.getCurrentDate(FORMAT_STYLE);
            return convertToDateInfo(date);
        }
        if (TextUtils.isEmpty(endDate) && !TextUtils.isEmpty(startDate)) {
            return convertToDateInfo((String) startDate);
        }
        return convertToDateInfo((String) endDate);
    }
    
    /*private boolean checkLeaveDate(String startDate, String endDate){
        if (!TextUtils.isEmpty(startDate) && !TextUtils.isEmpty(endDate)) {
            long startTime = DateUtils.getTime((String) startDate, FORMAT_STYLE);
            long endTime = DateUtils.getTime((String) endDate, FORMAT_STYLE);
            Log.i("DEBUG", "checkLeaveDate()---> startDate=" + startDate + ", endDate=" + endDate
                    + ", startTime=" + startTime + ", endTime=" + endTime);
            return startTime <= endTime;
        }
        return true;
    }*/

    private class DateInfo {
        int year;
        int month;
        int day;
    }
}
