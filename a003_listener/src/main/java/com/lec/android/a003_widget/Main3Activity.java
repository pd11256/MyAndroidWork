package com.lec.android.a003_widget;

// 참고하기
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnC, btnEqual, btnPlus, btnMinus, btnMulti, btnDiv;
    EditText et;
    String cal1 = "";
    String cal2 = "";
    String symbol = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        et = findViewById(R.id.et);
        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btnPlus = findViewById(R.id.buttonPlus);
        btnMinus = findViewById(R.id.buttonMinus);
        btnMulti = findViewById(R.id.buttonMulti);
        btnDiv = findViewById(R.id.buttonDiv);
        btnEqual = findViewById(R.id.buttonEqual);
        btnC = findViewById(R.id.buttonC);

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal2 = et.getText().toString();
                if(symbol.equals("") || cal1.equals("") || cal2.equals("")) return; //빈칸일때 는 다시돌아간다는뜻
                double result = 0;

                try {
                    if(symbol.equals("+")) {
                        result = Double.parseDouble(cal1) + Double.parseDouble(cal2);
                        et.setText(String.valueOf(result));
                    } else if(symbol.equals("-")) {
                        result = Double.parseDouble(cal1) - Double.parseDouble(cal2);
                        et.setText(String.valueOf(result));
                    } else if(symbol.equals("X")) {
                        result = Double.parseDouble(cal1) * Double.parseDouble(cal2);
                        et.setText(String.valueOf(result));
                    } else {
                        result = Double.parseDouble(cal1) / Double.parseDouble(cal2);
                        et.setText(String.valueOf(result));
                    }

                } catch (Exception e){
                    et.setText("");

                } finally {
                    cal1 = "";
                    cal2 = "";
                    symbol = "";
                }

            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
            }
        });

        class numberListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                String text = (String)((Button)v).getText();
                et.setText(et.getText().append(text));
            }
        }

        class calListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                cal1 = et.getText().toString();
                symbol = (String)((Button)v).getText();
                et.setText("");
                Log.d("listener", cal1);// logcat에 어떻게출력되는지 나오게하는것 즉 ,에러확인이나 디버깅확인을위해!!!
            }
        }
        //symbol 연산 , cal1 = num1 , cal2 = num2

        btn0.setOnClickListener(new numberListener());
        btn1.setOnClickListener(new numberListener());
        btn2.setOnClickListener(new numberListener());
        btn3.setOnClickListener(new numberListener());
        btn4.setOnClickListener(new numberListener());
        btn5.setOnClickListener(new numberListener());
        btn6.setOnClickListener(new numberListener());
        btn7.setOnClickListener(new numberListener());
        btn8.setOnClickListener(new numberListener());
        btn9.setOnClickListener(new numberListener());
        btnPlus.setOnClickListener(new calListener());
        btnMinus.setOnClickListener(new calListener());
        btnMulti.setOnClickListener(new calListener());
        btnDiv.setOnClickListener(new calListener());
    }
}
