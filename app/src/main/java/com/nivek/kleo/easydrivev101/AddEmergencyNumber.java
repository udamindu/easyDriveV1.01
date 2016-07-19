package com.nivek.kleo.easydrivev101;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddEmergencyNumber extends ActionBarActivity {

    SQLiteDatabase db;
    Button btEmergency;
    EditText name;
    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emergency_number);

        db = openOrCreateDatabase("cardashboardf",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS emergencyNumber(name varchar,number varchar);");

        btEmergency = (Button)findViewById(R.id.emergencyAddButton);

        btEmergency.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name = (EditText) findViewById(R.id.emergencyName);
                number = (EditText) findViewById(R.id.emergencyNumber);
                db.execSQL("INSERT INTO emergencyNumber VALUES ('" + name.getText().toString() + "','" + number.getText().toString() + "')");
                Toast.makeText(AddEmergencyNumber.this,number.getText().toString(),Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_emergency_number, menu);
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
