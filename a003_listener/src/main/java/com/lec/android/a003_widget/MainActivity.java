package com.lec.android.a003_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvResult;
    EditText et;
    //onCreate()
    //액티비티(화면 객체)가 생성될때 호출되는 메소드
    //액티비티 초기화 하는 코드 작성.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn1); //btn1 정수값! --> java(generated)에생긴다 간혹 깨질수
        Button btn2 = findViewById(R.id.btn2); //상수와 변수이름 동일하게하는것이 좋다
        Button btn3 = findViewById(R.id.btn3);

        tvResult = findViewById(R.id.tvResult);
        et = findViewById(R.id.et);

        final LinearLayout ll = findViewById(R.id.ll);// null로주고 하면 local이 바뀌기떄문에 아예 final로주는것이 좋다
        // ll = findViewById(R.id.ll);




        //방법2 : 익명 클래스(anonymous class)사용.
        btn2.setOnClickListener(new View.OnClickListener() { //Onclick는 이것밖에없다
            @Override
            public void onClick(View v) { //클릭되었을때 호출되는 메솧드(콜백 메소드 callback method)
                Log.d("myapp", "버튼 2가 클릭되었습니다");
                tvResult.setText("버튼2 가 클릭됨");
                tvResult.setBackgroundColor(Color.YELLOW);
            }
        });


        //다양한 이벤트, 다양한 리스너 등록 가능
        btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {//롱클릭 발생시 수행하는 콜벡 메소드

                Log.d("myapp", "버튼2가 롱클릭 되었습니다.");
                tvResult.setText("버튼2가 롱클릭 되었습니다.");
                tvResult.setBackgroundColor(Color.CYAN);
                //return false; // false 리턴하면 이벤트가 click 까지간다.
                return true; //true 리턴하면 이벤트가 Long click 으로 소멸 (consume)된다.
            }
        });
        //방법3 : Lambda -expression 사용하기 , file -->  Project structure --> 밑에두개 1.8로 바꿈 -->setting으로 해결하는것
        // 세팅 필요! ppt 참조!
        btn3.setOnClickListener((v) -> { //onClik(view v)
            Log.d("myapp", "버튼3 가 클릭 되었다");
            tvResult.setText("버튼3 클릭됨");
            ll.setBackgroundColor(Color.rgb(152, 234, 42));

        });

        //방법4 : implement 한 클래스 사용 하나만들어놓고 쓰기
        Button btnA = findViewById(R.id.btnA); //View id를 찾아라
        Button btnB = findViewById(R.id.btnB);
        Button btnC = findViewById(R.id.btnC);
        Button btnD = findViewById(R.id.btnD);
        Button btnE = findViewById(R.id.btnE);
        Button btnF = findViewById(R.id.btnF);

        class MyListener implements View.OnClickListener {

            String name;

            public MyListener(String name) {
                this.name = name;
            }

            @Override
            public void onClick(View v) {
                String tag = (String)v.getTag();
                String text = (String) ((Button) v).getText(); //getText() CharSequence 객체 리턴 그래서 String 사용
                String msg = String.format("%s 버튼 %s 이 클릭[%s]", name, text, tag);
                Log.d("myapp", msg);
                tvResult.setText(msg);
                et.setText(et.getText().append(name));//기존의 텍스트에 name append

            }
        }
        btnA.setOnClickListener(new MyListener("안녕1"));
        btnB.setOnClickListener(new MyListener("안녕2"));
        btnC.setOnClickListener(new MyListener("안녕3"));
        btnD.setOnClickListener(new MyListener("안녕4"));
        btnE.setOnClickListener(new MyListener("안녕5"));
        btnF.setOnClickListener(new MyListener("안녕6")); //동일한 동작을 수행하는 개체는 이렇게하는것이좋더ㅏ


        //방법 : 액티비티가 implement
        Button btnClear =findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        //연습
        //+,- 버튼 누르면 tvResult 의 글씨가 점점 커지고 /작아지게 하기
        //getTextSize() : float 값 리턴
        Button BtnInc = findViewById(R.id.BtnInc);


        Button BtnDec = findViewById(R.id.BtnDec);
        BtnInc.getTextSize();

        BtnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float size = tvResult.getTextSize();
                Log.d("myapp","글꼴사이즈: " +size);
                tvResult.setTextSize(0,size+5);
            }
        });

        BtnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float size = tvResult.getTextSize();
                Log.d("myapp","글꼴사이즈: " +size);
                tvResult.setTextSize(0,size-5);
            }
        });



    }//end onCReate

//***************************************************************************************************

    //방법 1 : 레이아웃 xmL 의 onXXX속성으로 지정
    //리스너를 장착하는 첫번째 방법
    public void changeText(View v){// alt+enter = quick pass ,import하는것
        //Lod.d(tag,message)
        // Log창의 Debug 메세지로 출력
        Log.d("myapp","버튼 1이 클릭되었습니다.");
        tvResult.setText("버튼1 이 클릭 되었습니다.");
                //service 로 assist해준다
                //밑에 Verbose 모든 메시지 다보는것
    }



//***************************************************************************************************



    // 방법 5 : 액티비티가 implement 한 것을 사용
    @Override
    public void onClick(View v) {
        Log.d("myapp","Clear 버튼이 클릭되었습니다.");
        tvResult.setText("Clear버튼이 클릭되었습니다");
        et.setText("버튼1 이 클릭되었습니다");
    }
}//end Activity
