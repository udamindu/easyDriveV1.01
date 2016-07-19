package com.nivek.kleo.easydrivev101;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.nivek.kleo.easydrivev101.R;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.SensorEventListener;


public class TiltBall extends Activity {

    BallView mBallView = null;
    Handler RedrawHandler = new Handler(); //so redraw occurs in main thread
    Timer mTmr = null;
    TimerTask mTsk = null;
    int mScrWidth, mScrHeight;
    android.graphics.PointF mBallPos, mBallSpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //hide title bar
        //set app to full screen and keep screen on
        getWindow().setFlags(0xFFFFFFFF,
                LayoutParams.FLAG_FULLSCREEN | LayoutParams.FLAG_KEEP_SCREEN_ON);
        ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilt_ball);
//create pointer to main screen
        final FrameLayout mainView =
                (android.widget.FrameLayout) findViewById(R.id.main_view);
        //get screen dimensions
        Display display = getWindowManager().getDefaultDisplay();
        mScrWidth = display.getWidth();
        mScrHeight = display.getHeight();
        mBallPos = new android.graphics.PointF();
        mBallSpd = new android.graphics.PointF();

        //create variables for ball position and speed
        mBallPos.x = mScrWidth / 2;
        mBallPos.y = mScrHeight / 2;
        mBallSpd.x = 0;
        mBallSpd.y = 0;

        //create initial ball
        mBallView = new BallView(this, mBallPos.x, mBallPos.y, 25
        );

        mainView.addView(mBallView); //add ball to main screen
        mBallView.invalidate(); //call onDraw in BallView


        //listener for accelerometer, use anonymous class for simplicity
        ((SensorManager) getSystemService(Context.SENSOR_SERVICE)).registerListener(
                new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent event) {
                        //set ball speed based on phone tilt (ignore Z axis)
                        mBallSpd.x = -event.values[0];
                        mBallSpd.y = event.values[1];
                        //timer event will redraw ball
                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    } //ignore this event
                },
                ((SensorManager) getSystemService(Context.SENSOR_SERVICE))
                        .getSensorList(Sensor.TYPE_ACCELEROMETER).get(0), SensorManager.SENSOR_DELAY_NORMAL);

        //listener for touch event
        mainView.setOnTouchListener(new android.view.View.OnTouchListener() {
            public boolean onTouch(android.view.View v, android.view.MotionEvent e) {
                //set ball position based on screen touch
                mBallPos.x = e.getX();
                mBallPos.y = e.getY();
                //timer event will redraw ball
                return true;
            }
        });
    } //OnCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Exit"); //only one menu item
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getTitle() == "Exit") //user clicked Exit
            finish(); //will call onPause
        return super.onOptionsItemSelected(item);
    }

    public void onPause() //app moved to background, stop background threads
    {
        mTmr.cancel(); //kill\release timer (our only background thread)
        mTmr = null;
        mTsk = null;
        super.onPause();
    }

    public void onResume() //app moved to foreground (also occurs at app startup)
    {
        //create timer to move ball to new position
        mTmr = new Timer();
        mTsk = new TimerTask() {
            public void run() {
                //if debugging with external device,
                //  a cat log viewer will be needed on the device
                android.util.Log.d(
                        "TiltBall","Timer Hit - " + mBallPos.x + ":" + mBallPos.y);
                //move ball based on current speed
                mBallPos.x += mBallSpd.x;
                mBallPos.y += mBallSpd.y;
                //if ball goes off screen, reposition to opposite side of screen
                if (mBallPos.x > mScrWidth) mBallPos.x=0;
                if (mBallPos.y > mScrHeight) mBallPos.y=0;
                if (mBallPos.x < 0) mBallPos.x=mScrWidth;
                if (mBallPos.y < 0) mBallPos.y=mScrHeight;
                //update ball class instance
                mBallView.mX = mBallPos.x;
                mBallView.mY = mBallPos.y;
                //redraw ball. Must run in background thread to prevent thread lock.
                RedrawHandler.post(new Runnable() {
                    public void run() {
                        mBallView.invalidate();
                    }});
            }}; // TimerTask

        mTmr.schedule(mTsk,10,10); //start timer
        super.onResume();
    } // onResume

    public void onDestroy() //main thread stopped
    {
        super.onDestroy();
        System.runFinalizersOnExit(true); //wait for threads to exit before clearing app
        android.os.Process.killProcess(android.os.Process.myPid());  //remove app from memory
    }

    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }
}
