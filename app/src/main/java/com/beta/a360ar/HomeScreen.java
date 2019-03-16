package com.beta.a360ar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;



public class HomeScreen extends AppCompatActivity {

    private Button goARButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        goARButton = (Button) findViewById(R.id.button2);

        goARButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAR();
            }
        });
    }

    public void openAR(){
        Intent intent = new Intent(this, ListCar.class);
        startActivity(intent);
    }
}
