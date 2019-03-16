package com.beta.a360ar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;


public class HomeScreen extends AppCompatActivity {

    private Button goARButton;
    private static final String url = "jdbc:mysql://hackathon-db.bdc.n360.io/hackathon";
    private static final String user = "events";
    private static final String pass = "Hack@th0n2019";
    private TextView makeText;
    private TextView modelText;
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

        View inflatedView = getLayoutInflater().inflate(R.layout.info_board, null);
        modelText = (TextView) inflatedView.findViewById(R.id.modelTextView);
        makeText = (TextView) inflatedView.findViewById(R.id.makeTextView);




        modelText.setText("Toyota");
        makeText.setText("Prius");






    }

    public void openAR(){
        Intent intent = new Intent(this, ListCar.class);
        startActivity(intent);
    }
}
