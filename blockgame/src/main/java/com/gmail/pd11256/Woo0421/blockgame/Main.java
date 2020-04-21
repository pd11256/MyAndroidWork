package com.gmail.pd11256.Woo0421.blockgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btnInfo =findViewById(R.id.btnInfo);
        Button btnHowto =findViewById(R.id.btnHowto);
        Button btnStart =findViewById(R.id.btnStart);


        btnStart.setOnClickListener(new View.OnClickListener() { //제일오래걸림
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getApplicationContext(),Start.class);
                startActivity(intent);
            }
        });
        btnHowto.setOnClickListener(new View.OnClickListener() { //첫번째
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), HowToplay.class);
                startActivity(intent);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), Info.class);
                startActivity(intent);
            }
        });





    }//end onCreate


}//end class
