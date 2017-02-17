package com.shenzhen.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.shenzhen.coolweather.bean.Weather;
import com.shenzhen.coolweather.utils.HttpUtil;
import com.shenzhen.coolweather.utils.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoServeice extends Service {
    private static final String WEATHER = "weather";
    private String bing_pic;

    public AutoServeice() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWeather();
        updatePic();
//        long time = 2 * 60 * 60 * 1000;
        long time = 60 * 1000;
        long triggerArTime = SystemClock.currentThreadTimeMillis() + time;

        AlarmManager  manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this, AutoServeice.class);
        PendingIntent pi = PendingIntent.getService(AutoServeice.this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerArTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWeather() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String            spWeather   = preferences.getString(WEATHER, null);
        bing_pic = preferences.getString("bing_pic", null);
        if (!TextUtils.isEmpty(spWeather)) {
            Weather weather = Utility.handleWeatherResponse(spWeather);
            if (weather != null) {
                String weatherId = weather.basic.weatherId;
                String api       = "http://guolin.tech/api/weather/?cityid=" + weatherId + "&key=9e6e334fabce4317a0c236fcc740ddd9";
                HttpUtil.sendOkHttpRequest(api, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        Toast.makeText(AutoServeice.this, "服务异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String  result  = response.body().string();
                        final Weather weather = Utility.handleWeatherResponse(result);
                        if (weather != null && "ok".equals(weather.status)) {
                            SharedPreferences        sp     = PreferenceManager.getDefaultSharedPreferences(AutoServeice.this);
                            SharedPreferences.Editor editor = sp.edit().putString(WEATHER, result);
                            editor.apply();
                        }
                    }
                });
            }
        }
    }

    private void updatePic() {

        if (TextUtils.isEmpty(bing_pic)) {
            final String imgApi = "http://guolin.tech/api/bing_pic";
            HttpUtil.sendOkHttpRequest(imgApi, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String imgUrl = response.body().string();
                    if (!TextUtils.isEmpty(imgUrl)) {
                        SharedPreferences              sp       = PreferenceManager.getDefaultSharedPreferences(AutoServeice.this);
                        final SharedPreferences.Editor bing_pic = sp.edit().putString("bing_pic", "");
                        bing_pic.apply();
                    }
                }
            });
        }
    }
}
