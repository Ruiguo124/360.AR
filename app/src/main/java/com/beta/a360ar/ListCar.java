package com.beta.a360ar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;


public class ListCar extends AppCompatActivity {



    String items[] = new String[]{"Acura", "Audi", "BMW", "Cadillac", "Chevrolet", "GMC", "Honda", "Lexus", "Toyota", "Volvo"};

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
        Intent intent = new Intent(this, Toyota.class);
        startActivity(intent);
    }

    /*
    ListView listView;
    List list = new ArrayList();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_car);

        listView = (ListView) findViewById(R.id.TheList);



        list.add("Acura ");
        list.add("Audi ");
        list.add("BMW ");
        list.add("Cadillac");
        list.add("Chevrolet");
        list.add("GMC");
        list.add("Honda");
        list.add("Lexus");
        list.add("Toyota");
        list.add("Volvo");

        adapter = new ArrayAdapter(ListCar.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

            }
        });
    }
    */

}
