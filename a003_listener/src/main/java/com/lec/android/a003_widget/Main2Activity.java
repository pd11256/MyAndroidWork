package com.lec.android.a003_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    int num1;
    int temp;
    int result;
    boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btn1 = findViewById(R.id.btn1); //View id를 찾아라
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btn0 = findViewById(R.id.btn0);
        Button btnMinus = findViewById(R.id.btnminus);
        Button btnMulti = findViewById(R.id.btnmulti);
        Button btnDiv = findViewById(R.id.btndiv);
        Button btnResult = findViewById(R.id.btnresult);
        Button btnAdd = findViewById(R.id.btnadd);
        Button btnClear = findViewById(R.id.btnclear);

        final TextView tv = (TextView) findViewById(R.id.textView);
        View.OnClickListener Click = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TextView textView = (TextView) findViewById(R.id.tv);
                if (flag==false){
                    tv.setText("");
                }
                String text = (String) tv.getText();
                text += ((Button) v).getText();
                tv.setText(text);
            }
        };

        btn1.setOnClickListener(Click);
        btn2.setOnClickListener(Click);
        btn3.setOnClickListener(Click);
        btn4.setOnClickListener(Click);
        btn5.setOnClickListener(Click);
        btn6.setOnClickListener(Click);
        btn6.setOnClickListener(Click);
        btn7.setOnClickListener(Click);
        btn8.setOnClickListener(Click);
        btn9.setOnClickListener(Click);
        btn0.setOnClickListener(Click);
        btnMinus.setOnClickListener(Click);
        btnMulti.setOnClickListener(Click);
        btnAdd.setOnClickListener(Click);
        btnResult.setOnClickListener(Click);
        btnDiv.setOnClickListener(Click);
        btnClear.setOnClickListener(Click);

        View.OnClickListener calcul = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btnAdd) {   //+ 버튼이 눌렸을때
                    String text = (String)tv.getText();//TextView 에 있는 text를 text에 저장
                    num1 = Integer.parseInt(text);  //num1 에 text 인트형으로 저장
                    temp = 1;   //temp 에 1 저장 temp 에 값으로 사칙연산을 확인함
                    tv.setText(""); //TextView 에 글씨 지움
                } else if (v == btnMinus) {
                    String text = (String) tv.getText();
                    num1 = Integer.parseInt(text);
                    temp = 2;
                    tv.setText("");
                } else if (v == btnDiv) {
                    String text = (String) tv.getText();
                    num1 = Integer.parseInt(text);
                    temp = 3;
                    tv.setText("");
                } else if (v == btnMulti) {
                    String text = (String) tv.getText();
                    num1 = Integer.parseInt(text);
                    temp = 4;
                    tv.setText("");
                } else if (v == btnResult) {  //= 버튼이 눌렸을때
                    if (temp == 1) {  //temp 확인 1은 +
                        result = num1 + Integer.parseInt(tv.getText().toString());
                        //result 에 num1 + TextView 의 값을 넣어줌
                        tv.setText(String.valueOf(result)); //result 값을 출력
                    } else if (temp == 2) {
                        result = num1 - Integer.parseInt(tv.getText().toString());
                        tv.setText(String.valueOf(result));
                    } else if (temp == 3) {
                        result = num1 / Integer.parseInt(tv.getText().toString());
                        tv.setText(String.valueOf(result));
                    } else if (temp == 4) {
                        result = num1 * Integer.parseInt(tv.getText().toString());
                        tv.setText(String.valueOf(result));
                    }
                    flag=false;
                }
            }
        };
        btnAdd.setOnClickListener(calcul);
        btnResult.setOnClickListener(calcul);
        btnMinus.setOnClickListener(calcul);
        btnDiv.setOnClickListener(calcul);
        btnMulti.setOnClickListener(calcul);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 0;
                tv.setText("");
            }
        });
    }
}
