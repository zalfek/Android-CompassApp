package marmiss.aleksejs.compassapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    Sensor orientSensor;
    SensorManager sman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sman = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensors = sman.getSensorList(Sensor.TYPE_ORIENTATION);
        if(sensors.size()==0){finish();return;}
        orientSensor = sensors.get(0);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ImageView imageView = findViewById(R.id.compass_image);
        float[] val = event.values.clone();
        imageView.setRotation(-val[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        sman.registerListener(this,orientSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    protected void onPause(){
        super.onPause();
        sman.unregisterListener(this);
    }
}
