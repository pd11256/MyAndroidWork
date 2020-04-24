package com.lec.android.a018_touch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    TextView tvResult;

    //3개까지의 멀티터치를 다루기 위한 배열.
    int id [] =new int[3];
    int x[] =new int[3];
    int y[] = new int[3];
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        tvResult = findViewById(R.id.tvResult);





    }// end onCreate
    //액티비티에서 발생한 터치 이벤트 처리 ( onCreate에서 버튼터치를 오버라이딩 하지않고 사용한다)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
       int pointCount = event.getPointerCount();//현재 터치 발생한 포인트 개수를 얻어온다.
        if(pointCount>3) pointCount =3; //4개 이상의 포인트 터치했더라도 3개까지만 처리 .

        switch(event.getAction() & MotionEvent.ACTION_MASK){// & 논리연산자아니고 비트연산자로 씀
            case MotionEvent.ACTION_DOWN: //한개 포인트에 대한 DOWN 이 발생했을때
                id[0] = event.getPointerId(0);
                x[0] = (int)(event.getX());
                y[0] = (int)(event.getY());

                result = "싱글터치 : \n";
                result += "{" + x[0] + ", "+y[0]+"}";
                break;
        case MotionEvent.ACTION_POINTER_DOWN: //두개 이상의 포인트에 대한 DOWN 이 발생했을때
                    result = "멀티터치 : \n";
                    for(int i =0; i < pointCount; i++){
                        id[i] = event.getPointerId(i);
                        x[i] = (int)(event.getX(i));
                        y[i] = (int)(event.getY(i));
                        result += "id["+ id[i]+"] (" +x[i]+", " + y[i]+")\n";
                    }
               break;

               case MotionEvent.ACTION_MOVE:
                   result = "멀티터치  : \n";
                   for(int i =0; i < pointCount; i++){
                       id[i] = event.getPointerId(i);
                       x[i] = (int)(event.getX(i));
                       y[i] = (int)(event.getY(i));
                       result += "id["+ id[i]+"] (" +x[i]+", " + y[i]+")\n";
                   }
                   break;
                   case MotionEvent.ACTION_UP:
                       result = "";
                       break;
        }
        tvResult.setText(result);

        return super.onTouchEvent(event);
    }
}//end class

