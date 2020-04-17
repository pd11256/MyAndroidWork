package com.lec.android.a007_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_two);

        Button btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //액티비티 종료
            }
        });

        //넘겨받은 Intent 객체를 받는다
        final Intent intent =getIntent(); //main에서 값을 꺼내온다 ,주의 이름그대로 꺼내온다

        int a= intent.getIntExtra("num",0); //"num"이라는 name 으로넘어온 값
                                                            // 만약 "num"이라는 name 이 인텐트에 없었으면
                                                            //디폴트값(두번째 매개변수)를 리턴
        int b =intent.getIntExtra("num2",0);
        int c =intent.getIntExtra("num3",999);//"num3"라는 이름은 없었다.

        long l = intent.getLongExtra("long",0);

        String msg = intent.getStringExtra("msg"); //리턴값이 Object인 경우, 디폴트값 설정 없다. ,name이 없으면 null리턴

        TextView tv1 = findViewById(R.id.tv1);
        tv1.setText("인텐트 받은 값: " + a + " : " +b + " : "+c+ " : " +l+" : " +msg);

        //Person 데이터 받기
        Person p = (Person)intent.getSerializableExtra("Person");

        TextView tv2 = findViewById(R.id.tv2);
        tv2.setText("Person 받은 값 :" +p.getName()+ " : " + p.getAge());

        Button btnToMain = findViewById(R.id.btnToMain);
        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(intent);
            }
        });



    }// end onCreate()
}//end Activity
