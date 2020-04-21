package com.gmail.pd11256.Woo0421.blockgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Start extends AppCompatActivity implements View.OnClickListener {

    TextView tvTime; //시간표시
    TextView tvPoint; //점수표시

    int time =30; //시간값
    int point = 0; //점수값


    //블럭 이미지 리소스배열
    int [] img = {R.drawable.block_red, R.drawable.block_green,R.drawable.block_blue};

    //떨어지는 블럭의 ImageView 배열 객체
    ImageView[] iv =new ImageView[8]; //iv[0] <-- null 처음에

    private Vibrator vibrator; //진동
    private SoundPool soundPool; //음향

    private int soundID_OK;   //음향 id : 블럭 맞추었을때
    private int soundID_Error; //음향 id ; 블럭 못맞추었을때


    private MediaPlayer mp; //배경음악재생

    final int DIALOG_TIMEOVER=1; // 다이얼로그 ID

    Handler handler = new Handler(); //시간,
    //터가좋군
    // 게임진행 쓰레드
    class GameThread extends Thread{
        @Override
        public void run() {
            //시간을 1초마다 다시 표시(업데이트)
            //Handler 사용하여 화면 UI 업데이트
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //TODO
                    if (time >= 0) {
                        tvTime.setText("Time: "+time);

                        if(time>0){
                            time--; //1초 감소, 1초후에 다시 Run()수행
                            handler.postDelayed(this,1000);
                        } else{
                            //time -> 0이 된경우.
                            //TODO 팝업띄우기
                            AlertDialog.Builder builder
                                    =new AlertDialog.Builder(Start.this);
                            builder.setTitle("타임아웃")
                                    .setMessage("점수 : "+ point)
                                    .setNegativeButton("그만하기", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish(); //현재 화면 종료.
                                        }
                                    })
                            .setPositiveButton("다시하기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 게임 리셋하고, 새 게임 시작
                                    time =30;
                                    point =0;
                                    tvTime.setText("Time: "+time);
                                    tvPoint.setText("Point: "+point);
                                    new GameThread().start(); //새로운 게임 시작
                                }
                            })
                            .setCancelable(false);
                            builder.show();
                        }//end if

                    }//end if

                }//end run
            }, 1000); //몇초후에 시간 표시 할것인지


        }//end rn

    }//end GameThread






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //상태바 (status bar)없애기 , 반드시 setContentView() 하기전에 없애기
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//윈도우객체

        setContentView(R.layout.start);


        //레이아웃 객체(뷰) 초기화
        tvTime = findViewById(R.id.tvTime);
        tvPoint = findViewById(R.id.tvPoint);

        ImageView ivRed =findViewById(R.id.ivRed);
        ImageView ivBlue =findViewById(R.id.ivBlue);
        ImageView ivGreen =findViewById(R.id.ivGreen);

        iv[0] = findViewById(R.id.ivBlock1);
        iv[1] = findViewById(R.id.ivBlock2);
        iv[2] = findViewById(R.id.ivBlock3);
        iv[3] = findViewById(R.id.ivBlock4);
        iv[4] = findViewById(R.id.ivBlock5);
        iv[5] = findViewById(R.id.ivBlock6);
        iv[6] = findViewById(R.id.ivBlock7);
        iv[7] = findViewById(R.id.ivBlock8);



        //게임이 시작되면, 초기화, 랜덤으로 블럭의 색상 지정
        for(int i = 0; i <iv.length; i++ ){
            //0, 1, 2 <-red, green, blue
            int num =new Random().nextInt(3); // 0,1,2 중의 랜덤 정수
            iv[i].setImageResource(img[num]);
            iv[i].setTag(num + ""); //태그를 버튼의 색상 판단하기 위한 값으로 활용
        }

        //점수 초기화
        point = 0;
        tvPoint.setText("점수:" + point);

        // r, g, b 버튼의 이벤트 리스너 등록
        //자기자신 implement했기때문에 this 가 나옴
        ivRed.setOnClickListener(this);
        ivGreen.setOnClickListener(this);
        ivBlue.setOnClickListener(this);

        //시간표시 ,게임진행 쓰레드 시작하기
        //TODO
        new GameThread().start();

    } //end onCreate()

//OnResume ,onPause 이두개를 사용한이유 알아야됨.. 잘모르겟음

    @Override
    public void onClick(View v) {
    //TODO
    //버튼을 눌렀을때 호출되는 콜백
    // 블럭과 같은 버튼이 눌렸는지 판별, 같은 블럭이면 이미지 블럭 한칸씩 내려오기, 맨 위에는 새로운 블럭 생성

        boolean isOk =false; //맞추었는지 판별 결과

        ImageView imageView =(ImageView) v;

        switch (imageView.getId()){
            //맨 아래 블럭 iv[7] 의  ImageView의 색상과 일치하는 버튼인지 판정
            case R.id.ivRed: //빨강버튼 클릭시, 리소스배열 번호가 0번이 였다, tag로 붙였다
                if("0".equals(iv[7].getTag().toString())) isOk=true; //빨강블럭의 tag값 "0"
                break;
            case R.id.ivGreen: //초록버튼 클릭시, tag값 "1"
                if("1".equals(iv[7].getTag().toString())) isOk=true;
                break;
            case R.id.ivBlue: //파랑버튼 클릭시, tag값 "2"
                if("2".equals(iv[7].getTag().toString())) isOk=true;
                break;
        }//end switch
        if(isOk) {//버튼 색깔을 맞추었다면!
            //위의 7개 블럭을 한칸 아래로 이동
            for(int i=iv.length -2; i>=0; i--){
                int num = Integer.parseInt(iv[i].getTag().toString()); //"0", "1", "2"
                iv[i+1].setImageResource(img[num]); //i 아래쪽 블럭 이미지 업데이트
                iv[i+1].setTag(num + ""); //i 아래쪽 블럭 tag값 업데이트
            }//end for

            //가장 위의 블럭 ImageView 는 랜덤으로 생성
            int num = new Random().nextInt(3); //0,1,2
            iv[0].setImageResource(img[num]);
            iv[0].setTag(num+"");

            //진동 & 음향
            vibrator.vibrate(200);
            soundPool.play(soundID_OK, 1,1,0,0,1);

            //점수 올리기
            point++;
            tvPoint.setText("점수: " + point);
        }
        else{//버튼 색깔이 틀리다면!

            //진동 & 음향
            vibrator.vibrate(new long[] {20, 80, 20, 80, 20, 80},-1);
            soundPool.play(soundID_OK, 1,1,0,0,1);

            //점수 깍기
            point--;
            tvPoint.setText("점수: "+ point);

        }//end if

    }//end onClick()

    @Override
    protected void onResume() {
        super.onResume();
        //자원 획득
        //TO

        //Vibrator 객체얻어오기
        vibrator =(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        //SoundPool 객체
        soundPool =new SoundPool.Builder().setMaxStreams(5).build();
        soundID_OK = soundPool.load(Start.this , R.raw.gun3,1);
        soundID_Error = soundPool.load(Start.this , R.raw.error,1);

        //MediaPlayer 객체,  배경음악 연주 시작
        mp =MediaPlayer.create(getApplicationContext(), R.raw.bgm);
        mp.setLooping(false); //반복재생 안함
        mp.start(); //배경음악 재생 시작.
    }

    @Override
    protected void onPause() { //지거나 ,다른화면이 튀어나오면 사용자는 다른화면을 건드릴수없다
        super.onPause();
        //자원해제
        //TODO
        if(mp != null){
            mp.stop();
            mp.release();
        }
    }


}//end Activity
