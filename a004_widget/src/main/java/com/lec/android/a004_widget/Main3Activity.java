package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    CheckBox cb1, cb2, cb3, cb4;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);

        tvResult = findViewById(R.id.tvResult);

        Button btnComplete = findViewById(R.id.btnComplete);
        //여기까지 등장인물 나열
        //버튼을 클릭하면 체크된 내용들만 결과 출력하기

        btnComplete.setOnClickListener(new View.OnClickListener() { //버튼은 무조건이렇게선언 무조건!! 기억하기!!
            @Override
            public void onClick(View v) {
                //체크박스가 체크됫느냐 안됫느냐
                String result = "";
                //boolean 타입
                if(cb1.isChecked()) result = result + cb1.getText();
                if(cb2.isChecked()) result = result + cb2.getText();
                if(cb3.isChecked()) result = result + cb3.getText();
                if(cb4.isChecked()) result = result + cb4.getText();
                tvResult.setText("선택결과: " + result);
            }
        });

        cb1.setOnCheckedChangeListener(new CbListener());
        cb2.setOnCheckedChangeListener(new CbListener());
        cb3.setOnCheckedChangeListener(new CbListener());
        cb4.setOnCheckedChangeListener(new CbListener());
    }// end onCreate() activity가 만들어질때 생성됨 ,초기화 부분은 여기에 다넣어주어야한다

    //두가지중 COmpoindbutton 잘고르기
    class CbListener implements CompoundButton.OnCheckedChangeListener{
//        Alt + insert 하면 오버라이드 선택할수있음
//        CheckBox 의 '상태'가 변할때마다 호출되는 메소드
//        isChecked : true <-check 상태, false <-uncheck 상태
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int count = 0;
            if(cb1.isChecked()) count++;
            if(cb2.isChecked()) count++;
            if(cb3.isChecked()) count++;
            if(cb4.isChecked()) count++;
            tvResult.setText(count + "개 선택");
        }
    }



}// end Activity
