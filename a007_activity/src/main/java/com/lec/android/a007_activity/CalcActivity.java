package com.lec.android.a007_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class CalcActivity extends AppCompatActivity {

    //화면이 없는 activity 생성기능
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int num1 = intent.getIntExtra("num1",0);
        int num2 = intent.getIntExtra("num2",0);

        intent.putExtra("plus",num1 + num2);
        intent.putExtra("minus",num1 - num2);

        //호출한 화면에 값 되돌려 주기
        setResult(RESULT_OK, intent); //RESULT_OK 이 함수에있는기능

        finish(); // onDestory() 를 호출한것과 동일
    }
}
