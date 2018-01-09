package com.geelaro.sensorex;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); //SensorManager实例

        //加速度传感器
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //地磁传感器
        Sensor magneticSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        //注册
        mSensorManager.registerListener(listener, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(listener, magneticSensor, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(listener);
        }

    }


    private SensorEventListener listener = new SensorEventListener() {
        float[] accelerometerValues = new float[3];
        float[] magneticValue = new float[3];
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                accelerometerValues = event.values.clone();
                //获取xyz方向的加速度
                float xValue = Math.abs(accelerometerValues[0]);
                float yValue = Math.abs(accelerometerValues[1]);
                float zValue = Math.abs(accelerometerValues[2]);
                if (xValue > 15 || yValue > 15 || zValue > 15) {
                    Toast.makeText(getApplicationContext(), "摇一摇了。", Toast.LENGTH_SHORT).show();
                }
            } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                magneticValue = event.values.clone();
            }

            float[] R =  new float[9];
            float[] values = new float[3];

            //得到一个包含旋转矩阵的R数组
            SensorManager.getRotationMatrix(R,null,accelerometerValues,magneticValue);
            //得到旋转角度values
            SensorManager.getOrientation(R,values);

            Log.d("MainActivity", "values[0] is "+Math.toDegrees(values[0]));
            Log.d("MainActivity", "values[1] is "+Math.toDegrees(values[1]));
            Log.d("MainActivity", "values[2] is "+Math.toDegrees(values[2]));


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
