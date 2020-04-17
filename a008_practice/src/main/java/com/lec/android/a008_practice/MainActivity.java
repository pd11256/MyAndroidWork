package com.lec.android.a008_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText etName, etAge, etAdress;
    TextView tvName,tvAge, tvAddress;
    Button btnAdd;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //실행

        etName = findViewById(R.id.etName);
        etAge =findViewById(R.id.etAge);
        etAdress = findViewById(R.id.etAddress);
        rv= findViewById(R.id.rv);
       // btnAdd =findViewById(R.id.btnAdd);


        tvName=findViewById(R.id.tvName);
        tvAge=findViewById(R.id.tvAge);
        tvAddress=findViewById(R.id.tvAddress);


        //RecyclerView 를 사용하기 위해서는 LayoutManager 지정해주어야 한다.*************
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //RecyclerView.LayoutManager layoutManager =new GridLayoutManager(this,4);
        rv.setLayoutManager(layoutManager);

       btnAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               addData(v);
           }
       });


    } //end onCreate()

    protected void addData(View v){
        int idx = D.next();
        //리스트 맨 앞에 추가
        adapter.addItem(0,new Phonebook(D.FACEID[idx], D.NAME[idx], D.PHONE[idx],D.EMAIL[idx]));
        adapter.notifyDataSetChanged(); //1.데이터 변경을 Adapter 에 알리고, 리스트뷰에 반영
    }
}//end class