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
import android.widget.EditText;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.CallActivity;
import com.guoli.hotel.bean.AreaInfo;
import com.guoli.hotel.bean.SearchInfo;
import com.guoli.hotel.net.bean.CityInfo;
import com.guoli.hotel.utils.DateUtils;
import com.guoli.hotel.utils.DialogUtils;
import com.guoli.hotel.utils.ResourceUtils;
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
    
    private static final String TAG = SearchHotelActivity.class.getSimpleName();

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
    /**搜索关键字*/
    private EditText mKeyWordView;
    /***/
    private int mAreaType = AREA_TYPE_DEFAULT;
    /**行政区域*/
    private static final int AREA_TYPE_DEFAULT = 1;
    /**商圈*/
    private static final int AREA_TYPE_SHOPPING = 2;
    

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
        initDefault();
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
        mKeyWordView = (EditText) findViewById(R.id.key_word);

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
            CityInfo info = (CityInfo) mCityView.getTag();
            intent.putExtra(CitySelectActivity.KEY_CITYINFO, info);
            intent.setClass(this, CitySelectActivity.class);
            startActivityForResult(intent, PAGE_CITY);
            break;
        case R.id.occupancy_date_btn:
            // 入住日期
            showDialog(DIALOG_DATEPICKER_OCCUPANCY);
            break;
        case R.id.leave_date_btn:
            // 离店时间
            showDialog(DIALOG_DATEPICKER_LEAVE);
            break;
        case R.id.priceBtn:
            // 价格
            intent = new Intent();
            intent.putExtra(PriceListActivity.KEY_PRICE, mPriceView.getText());
            intent.setClass(this, PriceListActivity.class);
            startActivityForResult(intent, PAGE_PRICE);
            break;
        case R.id.starBtn:
            // 星级
            intent = new Intent();
            intent.putExtra(LevelListActivity.KEY_LEVEL, mLevelView.getText());
            intent.setClass(this, LevelListActivity.class);
            startActivityForResult(intent, PAGE_LEVEL);
            break;
        case R.id.areaBtn:
            // 区域
            enterToAreaListActivity();
            break;
        case R.id.search_btn:
            // 搜索
            enterHotelSearchResultActivity();
            break;
        default:
            break;
        }
    }
    
    private void enterToAreaListActivity(){
        Intent intent = new Intent();
        intent.putExtra(AreaListActivity.KEY_AREA, mAreaView.getText());
        intent.setClass(this, AreaListActivity.class);
        CityInfo info = (CityInfo) mCityView.getTag();
        //接口文档中规定城市编码/入住/离开时间为必填
        if (info != null && !TextUtils.isEmpty(info.getCityCode())) {
            intent.putExtra(AreaListActivity.KEY_CITY_CODE, info.getCityCode());
            intent.putExtra(AreaListActivity.KEY_TYPE, mAreaType);
            
        }
        startActivityForResult(intent, PAGE_AREA);
    }
    
    /**
     * 
     * enterHotelSearchResultActivity:跳转到酒店查询结果页�? <br/>
     * 
     * @author maple
     * @since JDK 1.6
     */
    private void enterHotelSearchResultActivity() {
        SearchInfo info = getSearchInfo();
        if (info == null) {
            Log.i(TAG, "enterHotelSearchResultActivity()--->搜索条件对象不能为空.....");
            return;
        }
        if (DateUtils.compareDate(info.getEndDate(), info.getStartDate(), FORMAT_STYLE) != 1) {
            String msg = getString(R.string.dialog_date_check_msg);
            DialogUtils.showDialog("", msg, SearchHotelActivity.this);
            return;
        }
        // 跳转到酒店搜索结果页�?
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(HotelSearchResultActivity.KEY_SEARCHINFO, getSearchInfo());
        intent.putExtras(bundle);
        intent.setClass(this, HotelSearchResultActivity.class);
        startActivity(intent);
    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }
    
    /**
     * 
     * getObject:获取intent中bundle内指定key对应的值. <br/>
     * @author maple
     * @param intent
     * @param key
     * @return
     * @since JDK 1.6
     */
    private Object getObject(Intent intent, String key){
        if (TextUtils.isEmpty(key) || intent == null) {
            return null;
        }
        Bundle bundle = intent.getExtras();
        return bundle == null ? null : bundle.get(key);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
        case PAGE_CITY:
            setCityView(data);
            break;
        case PAGE_OCCUPANCY_DATE:
            break;
        case PAGE_LEAVE_DATE:
            break;
        case PAGE_PRICE:
            if (data == null) {
                break;
            }
            String price = data.getStringExtra(PriceListActivity.KEY_PRICE);
            if (!TextUtils.isEmpty(price)) {
                setViewText(mPriceView, price);
            }
            break;
        case PAGE_LEVEL:
            if (data == null) {
                return;
            }
            String level = data.getStringExtra(LevelListActivity.KEY_LEVEL);
            if (!TextUtils.isEmpty(level)) {
                setViewText(mLevelView, level);
            }
            break;
        case PAGE_AREA:
            setAreaView(data);
            break;

        default:
            break;
        }
    }
    /**
     * 设置默认值
     */
    private void initDefault(){
        //TODO:设置默认城市
        /**
         *       "name":"上海","code":"310000","py":"S"
         */
        CityInfo info=new CityInfo();
        info.setCityCode("310000");
        info.setCityName("上海");
        info.setFirstChar("S");
        info.setChecked(true);
        setViewText(mCityView, info.getCityName());
        mCityView.setTag(info);
        String date = DateUtils.addDateWithCurrentDate(1, DateUtils.FORMAT_DATE_YYMMDD);
        mOccupancyView.setText(date);
        date = DateUtils.addDateWithCurrentDate(2, DateUtils.FORMAT_DATE_YYMMDD);
        mLeaveView.setText(date);
        mPriceView.setText(getResources().getStringArray(R.array.price_value)[0]);
        mLevelView.setText(getResources().getStringArray(R.array.price_value)[0]);
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
    
    /**
     * 
     * setCityView:设置城市视图的值. <br/>
     * @author maple
     * @param intent
     * @since JDK 1.6
     */
    private void setCityView(Intent intent){
        Object obj = getObject(intent, CitySelectActivity.KEY_CITYINFO);
        if (!(obj instanceof CityInfo)) {
            mCityView.setText("");
            return;
        }
        CityInfo info = (CityInfo) obj;
        setViewText(mCityView, info.getCityName());
        mCityView.setTag(info);
    }
    
    /**
     * 
     * setAreaView:设置区域视图的值. <br/>
     * @author maple
     * @param intent
     * @since JDK 1.6
     */
    private void setAreaView(Intent intent){
        Object obj = getObject(intent, AreaListActivity.KEY_AREA);
        if (!(obj instanceof AreaInfo)) {
            mAreaView.setText("");
            return;
        }
        AreaInfo info = (AreaInfo) obj;
        setViewText(mAreaView, info.getName());
        mAreaView.setTag(info);
        obj = getObject(intent, AreaListActivity.KEY_TYPE);
        if (obj instanceof Integer) {
            mAreaType = (Integer) obj;
        }
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
        
        private boolean isRunned;
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if (isRunned) {
                isRunned = false;
                return;
            }
            isRunned = true;
            String endDate = DateUtils.getDateWithFormat(year, monthOfYear, dayOfMonth, FORMAT_STYLE);
            String startDate = mOccupancyView.getText().toString();
            if (DateUtils.compareDate(endDate, startDate, FORMAT_STYLE) == 1) {
                mLeaveView.setText(endDate);
                return;
            }
            String msg = getString(R.string.dialog_date_check_msg);
            DialogUtils.showDialog("", msg, SearchHotelActivity.this);
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
        String date = (TextUtils.isEmpty(dateChar) ? DateUtils.addDateWithCurrentDate(1, FORMAT_STYLE)
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
            date = DateUtils.addDateWithCurrentDate(2, FORMAT_STYLE);
            return convertToDateInfo(date);
        }
        if (TextUtils.isEmpty(endDate) && !TextUtils.isEmpty(startDate)) {
            return convertToDateInfo((String) startDate);
        }
        return convertToDateInfo((String) endDate);
    }

    private class DateInfo {
        int year;
        int month;
        int day;
    }
    
    /**
     * 
     * getSearchInfo:获取搜索条件对象. <br/>
     * @author maple
     * @return
     * @since JDK 1.6
     */
    private SearchInfo getSearchInfo(){
        CityInfo cityInfo = (CityInfo) mCityView.getTag();
        //接口文档中规定城市编码/入住/离开时间为必填
        if (cityInfo == null) {
            return null;
        }
        CharSequence startDate = mOccupancyView.getText();
        if (TextUtils.isEmpty(startDate)) {
            return null;
        }
        CharSequence endDate = mLeaveView.getText();
        if (TextUtils.isEmpty(endDate)) {
            return null;
        }
        SearchInfo info = new SearchInfo();
        info.setCityCode(cityInfo.getCityCode());
        info.setCityName(cityInfo.getCityName());
        info.setStartDate((String) startDate);
        info.setEndDate((String) endDate);
        info.setPrice(getPrice());
        info.setLevel(getLevel());
        info.setKeyWord(mKeyWordView.getText().toString());
        //排序默认为果粒推荐
        info.setOrderKey(getResources().getStringArray(R.array.order_key)[0]);
        
        Object obj = mAreaView.getTag();
        if (!(obj instanceof AreaInfo)) {
            return info;
        }
        AreaInfo areaInfo = (AreaInfo) obj;
        info.setAreaType(String.valueOf(mAreaType));
        info.setArea(areaInfo.getCode());
        return info;
    }
    
    /**
     * 
     * getPrice:获取价格区域. <br/>
     * @author maple
     * @return
     * @since JDK 1.6
     */
    private String getPrice(){
        CharSequence priceChar = mPriceView.getText();
        return ResourceUtils.getInstance(this).getKey(R.array.price_key, R.array.price_value, (String)priceChar);
    }
    
    /**
     * 
     * getLevel:获取酒店级别. <br/>
     * @author maple
     * @return
     * @since JDK 1.6
     */
    private String getLevel(){
        CharSequence level = mLevelView.getText();
        return ResourceUtils.getInstance(this).getKey(R.array.level_key, R.array.level_value, (String) level);
    }
}
