package com.lec.android.a019_graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main2);

        setContentView(new MyView2(this));

    }
}

class MyView2 extends View {
    int x=0;
    int y=0;
    public MyView2(Context context){
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
            Paint paint = new Paint();

            //무엇을 먼저 그리느냐에따라 글씨가 사각형뒤인지 , 사격형이 글씨뒤인지나누어진다!!!!!!!!!!!!!!!!!
            paint.setColor(Color.RED);
             paint.setTextSize(80);


            paint.setColor(Color.BLUE);
            canvas.drawText("글씨",300,300,paint);
        canvas.drawRect(x-100,y-100,x+100,y+100,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:

                x = (int)event.getX();
                y = (int)event.getY();
                invalidate();//화면을 다시 그려주기 --> onDraw() 호출함.

        }

        return true;
    }
}