package com.shenzhen.coolweather;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.coolweather.db.City;
import com.shenzhen.coolweather.db.County;
import com.shenzhen.coolweather.db.Province;
import com.shenzhen.coolweather.utils.HttpUtil;
import com.shenzhen.coolweather.utils.Utility;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author wizong @Date 2017/2/16 16:57 @Description TODO
 */
public class ChooseAreaFragment extends Fragment {

    public static final String WEATHER_ID = "weather_id";
    private TextView titleViewTv;
    private Button   BackBtn;
    private ListView chooseLv;

    private static final int LEVEL_PROVINCE = 340;
    private static final int LEVEL_CITY     = 137;
    private static final int LEVEL_COUNTY   = 46;

    private ProgressDialog progressDialog;

    private ArrayAdapter<String> adapter;

    private ArrayList<String> datas;

    private List<Province> provinces;
    private List<City>     cities;
    private List<County>   counties;

    private Province selectProvince;
    private City     selectCity;

    private int currentLevel;
    private String baseUrl = "http://guolin.tech/api/china";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.frg_choose_area, container, false);

        BackBtn = (Button) view.findViewById(R.id.choose_area_btn_back);
        titleViewTv = (TextView) view.findViewById(R.id.title_view);
        chooseLv = (ListView) view.findViewById(R.id.choose_area_list);
        datas = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, datas);
        chooseLv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        chooseLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private String weatherId;

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectProvince = provinces.get(i);
                    queryCity();
                } else if (currentLevel == LEVEL_CITY) {
                    selectCity = cities.get(i);
                    queryCounty();
                } else if (currentLevel == LEVEL_COUNTY) {
                    Toast.makeText(getContext(), "你选中的是：" + counties.get(i).getCountyName(), Toast.LENGTH_SHORT).show();
                    weatherId = counties.get(i).getWeatherId();

                    if (!TextUtils.isEmpty(weatherId)) {
                        SharedPreferences        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
                        SharedPreferences.Editor ed = sp.edit().putString(WEATHER_ID, weatherId);
                        ed.apply();
                    }
                    if (getActivity() instanceof MainActivity) {
                        Intent intent = new Intent(getActivity(), WeatherActivity.class);
                        intent.putExtra(WEATHER_ID, weatherId);
                        startActivity(intent);
                        getActivity().finish();
                    } else if (getActivity() instanceof WeatherActivity){
                        ((WeatherActivity) getActivity()).drawerLayout.closeDrawers();
                        ((WeatherActivity) getActivity()).swipeRefresh.setRefreshing(true);
                        ((WeatherActivity) getActivity()).requestWeather(weatherId);
                    }
                }
            }
        });
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_COUNTY) {
                    queryCity();
                } else if (currentLevel == LEVEL_CITY) {
                    queryProvince();
                }
            }
        });
        queryProvince();
    }

    private void queryProvince() {
        titleViewTv.setText("中国");
        BackBtn.setVisibility(View.GONE);
        provinces = DataSupport.findAll(Province.class);
        if (provinces.size() > 0) {
            datas.clear();
            for (Province province : provinces) {
                datas.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            chooseLv.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        } else {
            queryForServer(baseUrl, "province");
        }
    }

    private void queryCity() {
        titleViewTv.setText(selectProvince.getProvinceName());
        BackBtn.setVisibility(View.VISIBLE);
        cities = DataSupport.where("provinceid=?", String.valueOf(selectProvince.getId())).find(City.class);
        if (cities.size() > 0) {
            datas.clear();
            for (City city : cities) {
                datas.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            chooseLv.setSelection(0);
            currentLevel = LEVEL_CITY;
        } else {
            String api = baseUrl + "/" + selectProvince.getProvinceCode();
            queryForServer(api, "city");
        }
    }

    private void queryCounty() {
        titleViewTv.setText(selectCity.getCityName());
        BackBtn.setVisibility(View.VISIBLE);
        counties = DataSupport.where("cityid=?", String.valueOf(selectCity.getId())).find(County.class);
        if (counties.size() > 0) {
            datas.clear();
            for (County county : counties) {
                datas.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            chooseLv.setSelection(0);
            currentLevel = LEVEL_COUNTY;
        } else {
            String api = "http://guolin.tech/api/china/" + selectProvince.getProvinceCode() + "/" + selectCity.getCityCode();
            queryForServer(api, "county");
        }
    }

    private void queryForServer(String api, final String type) {
        showLoadingDialog();
        HttpUtil.sendOkHttpRequest(api, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeLoadingDialog();
                        Toast.makeText(getContext(), "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();

                boolean isResult = false;
                if ("province".equals(type)) {
                    isResult = Utility.handleProvinceResponse(result);
                } else if ("city".equals(type)) {
                    isResult = Utility.handleCityResponse(result, selectProvince.getId());
                } else if ("county".equals(type)) {
                    isResult = Utility.handleCountyResponse(result, selectCity.getId());
                }
                if (isResult) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeLoadingDialog();
                            if ("province".equals(type)) {
                                queryProvince();
                            } else if ("city".equals(type)) {
                                queryCity();
                            } else if ("county".equals(type)) {
                                queryCounty();
                            }
                        }
                    });
                }
            }
        });
    }

    private void closeLoadingDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    private void showLoadingDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("加载中...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }
}
