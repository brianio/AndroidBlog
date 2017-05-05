package com.geelaro.network;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geelaro.network.net.SocketUtil;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView tvResponse;
    private static final int SHOW_RESPONSE = 0;
    private Button sendRequest;
    private static final int NUMDAYS = 7;
    //
    private Button wBAuth;
    //
    private AuthInfo mAuthInfo;
    //tcp连接
    private Button socketClient;
    //udp 连接
    private Button udpClient;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    String[] weatherResults = new String[NUMDAYS];

                    try {
                        weatherResults = getWeatherFromJson(response, NUMDAYS);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //更新UI
                    StringBuilder str=new StringBuilder();
                    for (String s : weatherResults != null ? weatherResults : new String[0]) {
                        str.append(s).append("\n");
                    }
                    tvResponse.setText(str.toString());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //返回数据
        tvResponse = (TextView) findViewById(R.id.response_text);
        //请求按钮
        sendRequest = (Button) findViewById(R.id.send_request);
        sendRequest.setOnClickListener(this);
        //
        mAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        WbSdk.install(this,mAuthInfo);
        //授权管理
        wBAuth=(Button)findViewById(R.id.weibo_oauth);
        wBAuth.setOnClickListener(this);
        //socket TCP客户端连接
        socketClient=(Button)findViewById(R.id.tcp_client);
        socketClient.setOnClickListener(this);
        //socket UDP客户端连接
        udpClient = (Button)findViewById(R.id.udp_client);
        udpClient.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_request:
                getWeatherForecast();
                break;
            case R.id.weibo_oauth:
                startActivity(new Intent(this,WBAuthActivity.class));
                break;
            case R.id.tcp_client:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SocketUtil.tcpClient();
                        }
                    }).start();
                break;
            case R.id.udp_client:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SocketUtil.udpClient();
                    }
                }).start();
                break;
            default:
                break;
        }

    }


    private void sendRequestWithHttpURLConn() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                try {
                    String baseUri = "http://www.zhihu.com";
                    URL url = new URL(baseUri);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    //Settings
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(8000);//ms
                    urlConnection.setReadTimeout(8000);
                    //输入流
                    InputStream in = urlConnection.getInputStream();
                    //读取输入流
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    //MQ消息队列
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    //返回数据存到message中
                    message.obj = response.toString();

                    mHandler.sendMessage(message);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        //断开连接
                        urlConnection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void getWeatherForecast() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;
                String forecastJsonStr = null;

                String locationCode = "94013";
                String format = "json";
                String units = "metric";
                int numDays = 7;

                try {
                    final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
                    final String QUERY_PARAM = "q";
                    final String FORMAT_PARAM = "mode";
                    final String UNITS_PARAM = "units";
                    final String DAYS_PARAM = "cnt";
                    final String APPID_PARAM = "APPID";

                    Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                            .appendQueryParameter(QUERY_PARAM, locationCode)
                            .appendQueryParameter(FORMAT_PARAM, format)
                            .appendQueryParameter(UNITS_PARAM, units)
                            .appendQueryParameter(DAYS_PARAM, Integer.toString(numDays))
                            .appendQueryParameter(APPID_PARAM, BuildConfig.OPEN_WEATHER_MAP_API_KEY)
                            .build();

                    URL url = new URL(builtUri.toString());
                    Log.d(TAG, "URL is " + url);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    //Settings
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(8000);//ms
                    urlConnection.setReadTimeout(8000);
                    //输入流
                    InputStream in = urlConnection.getInputStream();
                    //读取输入流
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    forecastJsonStr = response.toString();


                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        //断开连接
                        urlConnection.disconnect();
                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                //MQ消息队列
                Message message = new Message();
                message.what = SHOW_RESPONSE;
                //返回数据存到message中
                message.obj = forecastJsonStr;

                mHandler.sendMessage(message);
            }
        }).start();
    }

    /**
     * 使用JSONObject解析Json
     * @param results
     * @param numDays
     * @return
     * @throws JSONException
     */
    private String[] getWeatherFromJson(String results, int numDays) throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String OWM_LIST = "list";
        final String OWM_CITY = "city";
        final String OWM_CITY_NAME = "name";
        final String OWM_WEATHER = "weather";
        final String OWM_TEMPERATURE = "temp";
        final String OWM_MAX = "max";
        final String OWM_MIN = "min";
        final String OWM_DESCRIPTION = "main";

        JSONObject forecastJson = new JSONObject(results);
        JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);
        //City name
        JSONObject city = forecastJson.getJSONObject(OWM_CITY);
        String cityName=city.getString(OWM_CITY_NAME);


        String[] resultStr = new String[numDays];
        int length = weatherArray.length();

        for (int i = 0; i < length; i++) {
            String description;
            String lowHigh;

            // 每一天的天气数据
            JSONObject dayForecast=weatherArray.getJSONObject(i);
            //description
            JSONObject weatherForecast=dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            description=weatherForecast.getString(OWM_DESCRIPTION);
            //temperature
            JSONObject temperatureObj=dayForecast.getJSONObject(OWM_TEMPERATURE);
            double high=temperatureObj.getDouble(OWM_MAX);
            double low=temperatureObj.getDouble(OWM_MIN);
            lowHigh = low +"/" +high;

            //
            resultStr[i]=cityName+"--"+description+"--"+lowHigh;
        }

        if (resultStr.length == 0) {
            return null;
        }

        return resultStr;
    }

    /**
     * 使用Gson解析
     */
    private String[] getWeatherFromGson(String forecast,int numDays){


        return null;
    }
}
