package com.geelaro.locationwork;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView lbsTxView;
    private LocationManager locationManager;
    private static final int SHOW_LOCATION = 0;

    //
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_LOCATION:
                    String position = (String) msg.obj;
                    lbsTxView.setText(position);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lbsTxView = (TextView) findViewById(R.id.lbs_text);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //
        List<String> providerList = locationManager.getProviders(true);
        String provider;
        if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;

        } else if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;

        } else {
            //当前无可用位置提供器
            Toast.makeText(this, "No location provider to use", Toast.LENGTH_SHORT).show();
            return;
        }

        Location location = locationManager.getLastKnownLocation(provider);


        if (location != null) {
            //显示当前设备的位置信息
            showLocation(location);

        }
        locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    /**
     *
     */
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            //更新设备地理位置信息
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    /**
     * 反向地理编码，将经纬度转换为地址
     */
    public void showLocation(final Location location) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;
                //经纬度
                String lat = String.valueOf(location.getLatitude());
                String lng = String.valueOf(location.getLongitude());
                StringBuilder urlStr = new StringBuilder();
                String baseUri = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";
                urlStr.append(baseUri).append(lat).append(",")
                        .append(lng).append("&sensor=false");
                Log.d(TAG, "run: " + urlStr.toString());

                //

                try {
                    URL url = new URL(urlStr.toString());
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");

                    reader = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream()));
                    StringBuffer buffer = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    //Json解析
                    JSONObject jsonObject = new JSONObject(buffer.toString());
                    JSONArray resultsArray = jsonObject.getJSONArray("results");
                    if (resultsArray.length() > 0) {
                        JSONObject subObject = resultsArray.getJSONObject(0);
                        //
                        String address = subObject.getString("formatted_address");
                        //
                        Message message = new Message();
                        message.obj = address;
                        message.what = SHOW_LOCATION;
                        mHandler.sendMessage(message);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();
    }
}
