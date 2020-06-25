package com.example.todoit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import java.util.Calendar;

public class splashScreen extends Activity {
    Handler handler;
    LinearLayoutCompat ln;
    TextView tvTime;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        ln = (LinearLayoutCompat)findViewById(R.id.container);
        tvTime = (TextView)findViewById(R.id.tv_time_morning);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay >= 0 && timeOfDay < 12){
            //Morning
            ln.setBackground(getDrawable(R.drawable.good_morning_img));
            tvTime.setText("Good Morning");
        }
        else if (timeOfDay >= 12 && timeOfDay < 16){
            //Afternoon
        }
        else  if (timeOfDay >= 16 && timeOfDay < 21){
            //Evening
        }
        else  if (timeOfDay >= 21 && timeOfDay < 24){
            //Night
            ln.setBackground(getDrawable(R.drawable.good_night_img));
            tvTime.setText("Good Night");
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}

