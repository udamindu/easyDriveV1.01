package com.nivek.kleo.easydrivev101;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class DrivingDynamics extends ActionBarActivity {

    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_dynamics);

        /*btn1 = (ImageButton) findViewById(R.id.imageButton);
        btn1 = (ImageButton) findViewById(R.id.imageButton2);
        btn1 = (ImageButton) findViewById(R.id.imageButton3);

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DrivingDynamics.this,VibrationCal.class);
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DrivingDynamics.this,TiltBall.class);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DrivingDynamics.this,StopWatch.class);
                startActivity(i);
            }
        }); */
    }

    public void goToTilt(View view){
        Intent intent = new Intent(this,TiltBall.class);
        startActivity(intent);
    }

    public void goToSpeed(View view){
        Intent intent = new Intent(this,Speed.class);
        startActivity(intent);
    }

    public void goToStopWatch(View view){
        Intent intent = new Intent(this,StopWatch.class);
        startActivity(intent);
    }

    public void goToImpactMeter(View view){
        Intent intent = new Intent(this,VibrationCal.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_driving_dynamics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
