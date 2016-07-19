package com.nivek.kleo.easydrivev101;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class EmergencyCall extends ActionBarActivity {

    ArrayAdapter<String> ad;
    ArrayList<String> arrEmContacts;
    SQLiteDatabase db;
    Cursor c;
    String number;
    String policeNumber;
    String fireNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);

        arrEmContacts = new ArrayList<>();
        arrEmContacts.add("Police");
        arrEmContacts.add("Fire Rescue");

        try {
            db = openOrCreateDatabase("cardashboardf",MODE_PRIVATE,null);
            c = db.rawQuery("SELECT * FROM emergencyNumber",null);

            int count = c.getCount();
            c.moveToFirst();

            for(int i=0;i<count;i++){
                arrEmContacts.add(c.getString(c.getColumnIndex("name")));
                c.moveToNext();
            }
            c.moveToLast();
            number = c.getString(c.getColumnIndex("number"));

        }catch (SQLiteException e){
            Toast.makeText(EmergencyCall.this,"You can add your prefferred emergancy number from settings",Toast.LENGTH_LONG).show();
        }
        policeNumber = "119";
        fireNumber = "114";

        ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrEmContacts);
        ListView emNumberslist = (ListView)findViewById(R.id.listView2);
        emNumberslist.setAdapter(ad);

        emNumberslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Intent.ACTION_CALL);
                if (ad.getItem(position).equals("Police")) {

                    i.setData(Uri.parse("tel:" + policeNumber));

                }
                else if(ad.getItem(position).equals("Fire Rescue")){

                    i.setData(Uri.parse("tel:"+fireNumber));

                }
                else{
                    if (number!=null){
                        i.setData(Uri.parse("tel:"+number));
                    }
                }
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_emergency_call, menu);
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
