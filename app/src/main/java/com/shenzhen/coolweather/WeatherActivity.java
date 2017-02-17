package com.shenzhen.coolweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shenzhen.coolweather.bean.Forecast;
import com.shenzhen.coolweather.bean.Weather;
import com.shenzhen.coolweather.service.AutoServeice;
import com.shenzhen.coolweather.utils.HttpUtil;
import com.shenzhen.coolweather.utils.Utility;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author wizong @Date 2017/2/17 10:43 @Description TODO
 */
public class WeatherActivity extends AppCompatActivity {

    public static final String WEATHER = "weather";
    @BindView(R.id.bing_pic_img)
    ImageView          bingPicImg;
    @BindView(R.id.nav_button)
    Button             navButton;
    @BindView(R.id.title_city)
    TextView           titleCity;
    @BindView(R.id.title_update_time)
    TextView           titleUpdateTime;
    @BindView(R.id.degree_text)
    TextView           degreeText;
    @BindView(R.id.weather_info_text)
    TextView           weatherInfoText;
    @BindView(R.id.forecast_layout)
    LinearLayout       forecastLayout;
    @BindView(R.id.aqi_text)
    TextView           aqiText;
    @BindView(R.id.pm25_text)
    TextView           pm25Text;
    @BindView(R.id.comfort_text)
    TextView           comfortText;
    @BindView(R.id.car_wash_text)
    TextView           carWashText;
    @BindView(R.id.sport_text)
    TextView           sportText;
    @BindView(R.id.weather_layout)
    ScrollView         weatherLayout;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    //    @BindView(R.id.choose_area_fragment)
    //    Fragment           chooseAreaFragment;
    @BindView(R.id.drawer_layout)
    DrawerLayout       drawerLayout;
    private String weatherId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            //设置透明状态栏,这样才能让 ContentView 向上
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            //        window.setStatusBarColor(0x00000000);

//            ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
//            View      mChildView   = mContentView.getChildAt(0);
//            if (mChildView != null) {
//                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
//                ViewCompat.setFitsSystemWindows(mChildView, false);
//            }
        }


        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String                  spWeather   = preferences.getString(WEATHER, null);
        String                  bing_pic    = preferences.getString("bing_pic", null);
        if (!TextUtils.isEmpty(bing_pic)) {
            Glide.with(this).load(bing_pic).into(bingPicImg);
        } else {
            loadImg();
        }
        if (TextUtils.isEmpty(spWeather)) {
            weatherLayout.setVisibility(View.INVISIBLE);
            weatherId = getIntent().getStringExtra("weather_id");
            if (!TextUtils.isEmpty(weatherId)) {
                requestWeather(weatherId);
            }
        } else {
            Weather weather = Utility.handleWeatherResponse(spWeather);
            showWeather(weather);
            weatherLayout.setVisibility(View.VISIBLE);
        }
        swipeRefresh.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                weatherId = preferences.getString("weather_id", null);
                if (!TextUtils.isEmpty(weatherId)) {
                    requestWeather(weatherId);
                } else {
                    Toast.makeText(WeatherActivity.this, "暂无最新天气信息", Toast.LENGTH_SHORT).show();
                }
            }
        });

        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void loadImg() {
        final String imgApi = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(imgApi, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String imgUrl = response.body().string();
                if (!TextUtils.isEmpty(imgUrl)) {
                    SharedPreferences              sp       = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this);
                    final SharedPreferences.Editor bing_pic = sp.edit().putString("bing_pic", "");
                    bing_pic.apply();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(WeatherActivity.this).load(imgUrl).into(bingPicImg);
                        }
                    });
                }
            }
        });
    }

    private void showWeather(Weather weather) {
        if (weather != null && "ok".equals(weather.status)) {
            Intent intent = new Intent(this, AutoServeice.class);
            startService(intent);
        } else {
            Toast.makeText(WeatherActivity.this, "获取天气失败", Toast.LENGTH_SHORT).show();
        }
        String cityName    = weather.basic.cityName;
        String updateTime  = weather.basic.update.updateTime.split(" ")[1];
        String degree      = weather.now.temperature + "℃";
        String weatherInfo = weather.now.more.info;
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        forecastLayout.removeAllViews();
        for (Forecast forecast : weather.forecastList) {
            View     view     = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
            TextView dateText = (TextView) view.findViewById(R.id.date_text);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            TextView maxText  = (TextView) view.findViewById(R.id.max_text);
            TextView minText  = (TextView) view.findViewById(R.id.min_text);
            dateText.setText(forecast.date);
            infoText.setText(forecast.more.info);
            maxText.setText(forecast.temperature.max + "℃");
            minText.setText(forecast.temperature.min + "℃");
            forecastLayout.addView(view);
        }
        if (weather.aqi != null) {
            aqiText.setText(weather.aqi.city.aqi);
            pm25Text.setText(weather.aqi.city.pm25);
        } else {
            aqiText.setText("暂无数据");
            pm25Text.setText("暂无数据");

        }
        String comfort = "舒适度：" + weather.suggestion.comfort.info;
        String carWash = "洗车指数：" + weather.suggestion.carWash.info;
        String sport   = "运行建议：" + weather.suggestion.sport.info;
        comfortText.setText(comfort);
        carWashText.setText(carWash);
        sportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);
        //        Intent intent = new Intent(this, AutoUpdateService.class);
        //        startService(intent);
    }

    public void requestWeather(final String weatherId) {
        String api = "http://guolin.tech/api/weather/?cityid=" + weatherId + "&key=9e6e334fabce4317a0c236fcc740ddd9";
        HttpUtil.sendOkHttpRequest(api, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气失败", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String  result  = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(result);
                if (weather != null && "ok".equals(weather.status)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreferences        sp     = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this);
                            SharedPreferences.Editor editor = sp.edit().putString(WEATHER, result);
                            editor.apply();
                            showWeather(weather);
                            Toast.makeText(WeatherActivity.this, "已获取最新天气信息", Toast.LENGTH_SHORT).show();
                            swipeRefresh.setRefreshing(false);
                        }
                    });
                }
            }
        });
    }

    @OnClick(R.id.nav_button)
    public void onClick() {

    }
}
