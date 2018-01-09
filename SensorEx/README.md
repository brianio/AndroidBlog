# SensorEx
�������Ѿ���Android�ֻ��ر�������û�д��������ԳƵ����ϹŶ���������ܼ����Ƚϳ��õĴ�������

##  SensorManager 
ʹ�ô�������Ҫ�Ȼ�ȡSensorManager ʵ��������ϵͳ���д������Ĺ�������

```
//��ȡSensorManagerʵ��
 SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
```
## ����������
- [���մ�������Sensor.TYPE_LIGHT](https://developer.android.com/reference/android/hardware/Sensor.html)
- [���ٶȴ�������Sensor.TYPE_ACCELEROMETER](https://developer.android.com/reference/android/hardware/Sensor.html)
- [��Ŵ�������Sensor.TYPE_MAGNETIC_FIELD](https://developer.android.com/reference/android/hardware/Sensor.html)

## ���ٶȴ�����
- ��ȡ���ٶȴ�����ʵ��,��������Ǵ���������
```
 Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

```
- ʵ��SensorEventListener�ӿ�

```
 private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //����������⵽����ֵ�����仯ʱ����ô˷���
            //�����event����������һ��values���飬�����Ǵ����������Ϣ
            
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //���������ȷ����仯ʱ������ô˷�����

        }
    };
```
- Ȼ��Ҫע��SensorEventListener�ӿڲ���ʹ����Ч

```
 mSensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
```
���У���ע�᷽��������������Ϊ��SensorEventListener�ӿ�ʵ����Sensorʵ���ʹ����������Ϣ�ĸ���Ƶ�ʡ���������������SENSOR_DELAY_FASTEST,SENSOR_DELAY_GAME,SENSOR_DELAY_NORMAL, SENSOR_DELAY_UI 4�֡�

- �����˳���ʹ���괫������Ҫ����unregisterListener()�����ͷ���Դ��

```
mSensorManager.unregisterListener(listener);

```
## ���򴫸���
���ݼ��ٶȴ�������������Ҫ��ȡһ��Sensorʵ����

```
Sensor sensor = Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

```
Ȼ�����������ٶȴ�����һ������onSensorChanged()����ͨ��SensorEvent��values���飬�����Ϳ��Եõ������������Ϣ�ˡ�

��Ȼ���ַ�����Ч���������Ѿ���Android�����ˡ�����Android�Ƽ��ķ�����ͨ�����ٶȺ͵شŴ�������ͬ����ó��ֻ���ת�ķ���ͽǶȡ�
- ���ȣ��Ȼ�ȡ���򴫸����ͼ��ٶȴ�����ʵ������ע�������

```
 //���ٶȴ�����
Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//�شŴ�����
Sensor magneticSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//ע�������
mSensorManager.registerListener(listener, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
mSensorManager.registerListener(listener, magneticSensor, SensorManager.SENSOR_DELAY_GAME);
```
���ڷ��򴫸����ľ�ȷ��Ҫ��Ƚϸߣ����������Ϣ��������ѡ��ΪSENSOR_DELAY_GAME��
- ��������onSensorChanged(()�л�ȡ��values���飬�ֱ��¼���򴫸����͵شŴ����������ֵ��Ȼ���������ֵ����getRotationMatrix()�����õ�һ��������ת������Ϣ������R.

```
SensorManager.getRotationMatrix(R,null,accelerometerValues,magneticValue);

```
���У���һ������R��һ������Ϊ9��float���飬�ڶ��������ǰѵش�����ת����������ͨ������Ϊnull���ɡ����������ĸ������ֱ��Ǽ��ٶȺ͵شŴ����������valuesֵ��
- �õ�R����󣬽��ŵ���SensorManager.getOrientation()���������㣬�õ��ֻ�����ת��ֵ��

```
SensorManager.getOrientation(R,values);
```
values��һ������3�����飬����values[0]��¼Χ��Z����ת���ȣ�values[1]��ָ�ֻ�Χ��x����ת���ȣ�values[2]��¼�ֻ���y����ת���ȡ�
- �ѻ���ת�ɽǶȡ�

```
Math.toDegrees(values[0])
```

### Reference
- ����һ�д��롷
- [Android Develeper](https://developer.android.com/reference/android/hardware/SensorManager.html)






