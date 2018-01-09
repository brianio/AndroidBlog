# SensorEx
传感器已经是Android手机必备技术，没有传感器可以称的是老古董。下面介绍几个比较常用的传感器。

##  SensorManager 
使用传感器需要先获取SensorManager 实例，它是系统所有传感器的管理器。

```
//获取SensorManager实例
 SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
```
## 传感器类型
- [光照传感器：Sensor.TYPE_LIGHT](https://developer.android.com/reference/android/hardware/Sensor.html)
- [加速度传感器：Sensor.TYPE_ACCELEROMETER](https://developer.android.com/reference/android/hardware/Sensor.html)
- [电磁传感器：Sensor.TYPE_MAGNETIC_FIELD](https://developer.android.com/reference/android/hardware/Sensor.html)

## 加速度传感器
- 获取加速度传感器实例,传入参数是传感器类型
```
 Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

```
- 实现SensorEventListener接口

```
 private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //当传感器监测到的数值发生变化时会调用此方法
            //传入的event参数包含了一个values数组，里面是传感器输出信息
            
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //传感器精度发生变化时，会调用此方法。

        }
    };
```
- 然后还要注册SensorEventListener接口才能使其生效

```
 mSensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
```
其中，此注册方法的三个参数分为是SensorEventListener接口实例，Sensor实例和传感器输出信息的更新频率。第三个参数共有SENSOR_DELAY_FASTEST,SENSOR_DELAY_GAME,SENSOR_DELAY_NORMAL, SENSOR_DELAY_UI 4种。

- 程序退出或使用完传感器后，要调用unregisterListener()方法释放资源。

```
mSensorManager.unregisterListener(listener);

```
## 方向传感器
根据加速度传感器，我们需要获取一个Sensor实例。

```
Sensor sensor = Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

```
然后其他跟加速度传感器一样，在onSensorChanged()方法通过SensorEvent的values数组，这样就可以得到传感器输出信息了。

虽然这种方法有效，但是它已经被Android废弃了。现在Android推荐的方法是通过加速度和地磁传感器共同计算得出手机旋转的方向和角度。
- 首先，先获取方向传感器和加速度传感器实例，并注册监听器

```
 //加速度传感器
Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//地磁传感器
Sensor magneticSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//注册监听器
mSensorManager.registerListener(listener, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
mSensorManager.registerListener(listener, magneticSensor, SensorManager.SENSOR_DELAY_GAME);
```
由于方向传感器的精确度要求比较高，这里输出信息更新速率选择为SENSOR_DELAY_GAME。
- 接下来在onSensorChanged(()中获取到values数组，分别记录方向传感器和地磁传感器输出的值。然后把这两个值传入getRotationMatrix()方法得到一个包含旋转矩阵信息的数组R.

```
SensorManager.getRotationMatrix(R,null,accelerometerValues,magneticValue);

```
其中，第一个参数R是一个长度为9的float数组，第二个参数是把地磁向量转换成重力，通常定义为null即可。第三、第四个参数分别是加速度和地磁传感器输出的values值。
- 得到R数组后，接着调用SensorManager.getOrientation()方法来计算，得到手机的旋转数值。

```
SensorManager.getOrientation(R,values);
```
values是一个长度3的数组，其中values[0]记录围绕Z轴旋转弧度，values[1]是指手机围绕x轴旋转弧度，values[2]记录手机绕y轴旋转弧度。
- 把弧度转成角度。

```
Math.toDegrees(values[0])
```

### Reference
- 《第一行代码》
- [Android Develeper](https://developer.android.com/reference/android/hardware/SensorManager.html)






