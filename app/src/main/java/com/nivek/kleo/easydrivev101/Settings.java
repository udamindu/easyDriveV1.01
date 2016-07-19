package com.nivek.kleo.easydrivev101;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Settings extends ActionBarActivity {

    ArrayAdapter<String> ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ArrayList<String> arrSettings = new ArrayList<>();
        arrSettings.add("Add an Emergency Number");
        arrSettings.add("Change Background Picture");
        arrSettings.add("Text Message");

        ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrSettings);
        ListView lv = (ListView)findViewById(R.id.listView);

        lv.setAdapter(ad);


       /* lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(ad.getItem(position).equals("Change Background Picture")){
                    Intent i = new Intent(Settings.this,ChangeBackground.class);
                    startActivity(i);
                }
            }
        }); */

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(ad.getItem(position).equals("Text Message")){
                    Intent i = new Intent(Settings.this,TextMessage.class);
                    startActivity(i);
                }
                Toast.makeText(Settings.this,ad.getItem(position),Toast.LENGTH_LONG).show();
                if(ad.getItem(position).equals("Add an Emergency Number")){
                    Intent i = new Intent(Settings.this,AddEmergencyNumber.class);
                    startActivity(i);
                }
                if(ad.getItem(position).equals("Change Background Picture")){
                    Intent i = new Intent(Settings.this,ChangeBackground.class);
                    startActivity(i);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
