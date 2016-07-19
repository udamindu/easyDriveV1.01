package com.nivek.kleo.easydrivev101;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    SQLiteDatabase db;
    ImageButton map;
    Cursor c;
    String path;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("cardashboardf",MODE_PRIVATE,null);
        try {

            c = db.rawQuery("SELECT * FROM applicationBackground",null);

            int count = c.getCount();
            c.moveToFirst();
            path = c.getString(c.getColumnIndex("path"));
            Log.d("PAAAAATTTTTTHHH",path);



        }catch (SQLiteException e){
            //Toast.makeText(EmergencyCall.this,"You can add your prefferred emergancy number from settings",Toast.LENGTH_LONG).show();
        }

        if(path!=null){
            LinearLayout l = (LinearLayout)findViewById(R.id.linearlayoutmain);
            int sdk = Build.VERSION.SDK_INT;

            if(sdk< Build.VERSION_CODES.JELLY_BEAN){
                if(path.equals("cloudsbg")){
                    l.setBackgroundDrawable(getResources().getDrawable(R.drawable.cloudsbg));
                }
                else if(path.equals("darkbluebg")){
                    l.setBackgroundDrawable(getResources().getDrawable(R.drawable.darkbluebg));
                }
                else if(path.equals("amzbg")){
                    l.setBackgroundDrawable(getResources().getDrawable(R.drawable.amzbg));
                }


            }
            else{
                if(path.equals("cloudsbg")){
                    l.setBackground(getResources().getDrawable(R.drawable.cloudsbg));
                }
                else if(path.equals("darkbluebg")){
                    l.setBackground(getResources().getDrawable(R.drawable.darkbluebg));
                }
                else if(path.equals("amzbg")){
                    l.setBackground(getResources().getDrawable(R.drawable.amzbg));
                }
            }
        }

        map = (ImageButton) findViewById(R.id.imgLocation);

        map.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:"));
              startActivity(i);
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void goToMusic(View view){
        Intent intent = new Intent(this,MusicPlayer.class);
        startActivity(intent);
    }

    public void goToSetting(View view){
        Intent intent = new Intent(this,Settings.class);
        startActivity(intent);
    }

    public void goToEmergencyNumber(View view){
        Intent intent = new Intent(this,EmergencyCall.class);
        startActivity(intent);

    }

    public void goToCamera(View view){
        Intent intent = new Intent(this,Camera.class);
        startActivity(intent);
    }

    public void goToDynamics(View view){
        Intent intent = new Intent(this,DrivingDynamics.class);
        startActivity(intent);
    }

}
