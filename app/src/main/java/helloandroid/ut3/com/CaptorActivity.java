package helloandroid.ut3.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CaptorActivity extends AppCompatActivity implements SensorEventListener {

    private TextView label_x;
    private TextView label_y;
    private TextView label_z;
    private SensorManager sm = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captor);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        label_x = (TextView) findViewById(R.id.txt_x);
        label_y = (TextView) findViewById(R.id.txt_y);
        label_z = (TextView) findViewById(R.id.txt_z);

    }

    public void startConnectActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /*
    onResume et onStop :
    Ces deux méthodes (provenant de Activity) sont appelées respectivement
    à chaque fois que l'exécution de l'activité reprend ou est mise en pause
.
    On va s'abbonner à chaque reprise, et se désabonner lorsque notre application
    est mise en pause.

    Attention : L'ordre entre l'appel de la méthode 'super().onXxx()' est important!
    */

    @Override
    protected void onResume() {
        super.onResume();
        Sensor mMagneticField = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sm.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop(){
        super.onStop();
        sm.unregisterListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensor = event.sensor.getType();
        float [] values = event.values;

        synchronized (this) {
            if (sensor == Sensor.TYPE_MAGNETIC_FIELD){
                float x = values[0];
                float y = values[1];
                float z = values[2];
                label_x.setText(String.valueOf(x));
                label_y.setText(String.valueOf(y));
                label_z.setText(String.valueOf(z));
                Log.d("onSensorChanged", " x= "+x+" y= "+y+" z= "+z);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}