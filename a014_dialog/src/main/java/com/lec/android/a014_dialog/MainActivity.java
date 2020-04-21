package com.lec.android.a014_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //대화상자 객체
    Dialog dlg1;
    ImageView ivDlgBanner;  //layout11 에있는 이미지뷰어
    Button btnDlgEvent; //layout11 에있는 이벤트처리버튼

    Dialog dlg2;
    TextView tvResult; //main의 결과창

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult =findViewById(R.id.tvResult);

        //Dialog 클래스로 다이얼로그 객체 생성및 세팅
        dlg1 = new Dialog(this); //다이얼로그 객체 생성******
        dlg1.setContentView(R.layout.dialog_layout11); // 다이얼로그 화면 등록*******

        //Dialog 안의 view 객체들 얻어오기
        ivDlgBanner =dlg1.findViewById(R.id.ivDlgBanner);//*****
        btnDlgEvent =dlg1.findViewById(R.id.btnDlgEvent);//*****

        //TODO
        btnDlgEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivDlgBanner.setImageResource(R.drawable.face22);
                Toast.makeText(getApplicationContext(), "다이얼로그 버튼을 눌렀어요",Toast.LENGTH_SHORT).show();
            }
        });

        //Activity 에 Dialog등록하기
        dlg1.setOwnerActivity(MainActivity.this);
        dlg1.setCanceledOnTouchOutside(true); //다이얼로그 바깥 영역 클릭시(혹은 back 버튼 클릭시) hide() 상태가 됨.
                                //종료할 것인지 여부 ,True:종료됨, False : 종료안됨.

        // #2
        dlg2 =new Dialog(this); //*****
        dlg2.setContentView(R.layout.dialog_layout12);//*****

        //Activity 에 Dialog등록하기
        dlg2.setOwnerActivity(MainActivity.this);
        dlg2.setCanceledOnTouchOutside(true);



       final EditText etName =dlg2.findViewById(R.id.etName); //final 생각하기
        Button btnOk =dlg2.findViewById(R.id.btnOk);
        Button btnCancel =dlg2.findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str =etName.getText().toString();
                tvResult.setText(str);
                dlg2.dismiss(); //다이얼로그 객체 제거
            }
        });



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg2.dismiss();
            }
        });
        dlg2.setOnShowListener(new DialogInterface.OnShowListener() { //입력후 대화상자를 다시열면 초기화상태로열어주는 매소드
            @Override
            public void onShow(DialogInterface dialog) {
                etName.setText("");
            }
        });

    } //end onCreate()

    public void showDialog1(View v){
        //TODO
        dlg1.show(); //다이얼로그 띄우기
    }

    public void showDialog2(View v){
        //TODO
        dlg2.show();
    }


}
