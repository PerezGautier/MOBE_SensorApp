package helloandroid.ut3.com;

import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements  View.OnTouchListener {

    // on défini un handler qui représentera notre timer :
    private Handler mHandler;
    private TextView tv;
    private TextView or;
    private ImageView photo;
    private float posx;
    private float posy;
    private int cpt = 10;
    private SensorManager sm = null;

    // un Runnable qui sera appelé par le timer
    private Runnable mUpdateTimeTask = new Runnable(){
        public void run(){
            // inserez ici ce que vous voulez executer
            // tv.setText("essaie : "+cpt+" posx : " +posx+"posy : "+posy);
            photo.setImageResource(R.drawable.cookies);
            Log.d("update1", " cookies");
            mHandler.postDelayed(mUpdateTimeTask2, 1000);
        }
    };

    private Runnable mUpdateTimeTask2 = new Runnable(){
        public void run(){
            // inserez ici ce que vous voulez executer
            // tv.setText("essaie : "+cpt+" posx : " +posx+"posy : "+posy);
            photo.setImageResource(R.drawable.ic_launcher_foreground);
            Log.d("update2", " logo android");
            mHandler.postDelayed(mUpdateTimeTask, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        // on crée un nouveau TextView, qui est un widget permettant
        // d'afficher du texte
        // tv = new TextView(this);
        // or = new TextView(this);

        // remplacer tout le contenu de notre activité par le TextView
        // setContentView(tv);
        // setContentView(or);

        // configurer le texte à faire afficher par notre widget
        // String text = "Hello, Android ! You are "+ Build.MODEL;
        // tv.setText(text);

        // on utilise une permission qu'il faut rajouter dans le manifest
        // Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // vibrator.vibrate(2000);

        // tv.setOnTouchListener(this);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        photo = findViewById(R.id.imageView);

        mHandler.postDelayed(mUpdateTimeTask, 1000);
    }

    public void startConnectActivity(View view){
        Intent intent = new Intent(this, CaptorActivity.class);
        startActivity(intent);
    }

    // on implémente la méthode onTouch() de l'interface OnTouchListener :
    @Override
    public boolean onTouch(View view, MotionEvent event){
        cpt--;
        if (cpt == 0){
            System.exit(RESULT_OK);
        }else{
            posx = event.getX();
            posy = event.getY();

        }
        return true;
    }


}