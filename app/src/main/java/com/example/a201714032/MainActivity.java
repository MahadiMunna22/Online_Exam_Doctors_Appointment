package com.example.a201714032;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView appointment,history;
    private Sensor mySensor;
    private SensorManager SM;
    private double x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SM=(SensorManager)getSystemService((SENSOR_SERVICE));
        mySensor=SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        appointment = findViewById(R.id.newAppointment);
        history = findViewById(R.id.history);

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),New_Appointment.class);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),History.class);
                startActivity(i);
            }
        });

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        x=event.values[0];
        if(x>=8.0)
        {
            Intent i = new Intent(getApplicationContext(),History.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(),"Left",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onResume()
    {
        super.onResume();
        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause()
    {
        super.onPause();
        SM.unregisterListener(this);
    }
}
