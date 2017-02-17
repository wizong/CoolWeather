package com.shenzhen.coolweather;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

public class MainActivity extends AppCompatActivity {

    private static final String WEATHER_ID = "weather_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String weather_id = PreferenceManager.getDefaultSharedPreferences(this).getString(WEATHER_ID, null);
        if (!TextUtils.isEmpty(weather_id)) {
            Intent intent = new Intent(this, WeatherActivity.class);
            intent.putExtra(WEATHER_ID, weather_id);
            startActivity(intent);
            finish();
        }
    }
}
