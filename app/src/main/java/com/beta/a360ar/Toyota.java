package com.beta.a360ar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Toyota extends AppCompatActivity {

    String items[] = new String[]{"Toyota Prius", "Toyota Corolla", "Toyota Camry"};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_car);

        ListView listView = (ListView) findViewById(R.id.TheList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openToyota();
            }
        });
    }

    public void openToyota(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
