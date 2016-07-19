package com.nivek.kleo.easydrivev101;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


public class ChangeBackground extends ActionBarActivity {

    ImageButton background1;
    ImageButton background2;
    ImageButton background3;
    LinearLayout linearLayout;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);

        background1 =(ImageButton)findViewById(R.id.imgbackButton1);
        background2 =(ImageButton)findViewById(R.id.imgbackButton2);
        background3 =(ImageButton)findViewById(R.id.imgbackButton3);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayoutmain);
        //linearLayout.setBackgroundResource(resid);

        db = openOrCreateDatabase("cardashboardf",MODE_PRIVATE,null);

        db.execSQL("CREATE TABLE IF NOT EXISTS applicationBackground(path varchar);");

        background1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
               db.delete("applicationBackground",null,null);
               db.execSQL("INSERT INTO applicationBackground VALUES ('cloudsbg')");
               Toast.makeText(ChangeBackground.this,"Succesfully Changed",Toast.LENGTH_LONG).show();
               Intent i = new Intent(ChangeBackground.this,MainActivity.class);
               startActivity(i);
            }
        });

        background2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                db.delete("applicationBackground",null,null);
                db.execSQL("INSERT INTO applicationBackground VALUES ('darkbluebg')");
                Toast.makeText(ChangeBackground.this,"Succesfully Changed",Toast.LENGTH_LONG).show();
                Intent i = new Intent(ChangeBackground.this,MainActivity.class);
                startActivity(i);
            }
        });

        background3.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
               db.delete("applicationBackground",null,null);
                db.execSQL("INSERT INTO applicationBackground VALUES ('amzbg')");
                Toast.makeText(ChangeBackground.this,"Succesfully Changed",Toast.LENGTH_LONG).show();
                Intent i = new Intent(ChangeBackground.this,MainActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_background, menu);
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
