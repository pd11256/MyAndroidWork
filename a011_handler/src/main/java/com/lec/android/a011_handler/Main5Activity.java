package com.lec.android.a011_handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {

    int mainValue =0;
    int backValue1 =0;
    int backValue2 =0;

    TextView tvMainValue,tvBackValue1,tvBackValue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);


        tvMainValue=findViewById(R.id.tvMainValue);
        tvBackValue1=findViewById(R.id.tvBackValue1);
        tvBackValue2 =findViewById(R.id.tvBackValue2);

        Log.d("myapp","PRE !!");
        Backgroundtask backgroundTask = new Backgroundtask();
        backgroundTask.execute(100); //맨위에서 이렇게 실행해야한다 .
        Log.d("myapp","POST !!");  // <-- 과연 언제 찍힐까



    }//end onCreate


    // AsyncTask<Params, Progress, Result>
//      Params: doItBackground 에서 사용할 변수 타입
//      Progress: onProgress 에서 사용할 변수의 타입
//      Result : onPostExecute 에서 사용할 변수의 타입
    class Backgroundtask extends AsyncTask<Integer,Integer,Integer>{

        //백그라운드 작업. 시작하기 전에 호출


        @Override
        protected void onPreExecute() {
            Log.d("myapp","onPreExecute");
            super.onPreExecute();
        }

        //백그라운드 작업. 반드시 구현! ,여기가 본론
        @Override
        protected Integer doInBackground(Integer... integers) {//가변매개변수, integers 는 Integer[]
            for(backValue1=0; backValue1<integers[0]; backValue1++){
                if(backValue1 % 10 ==0){
                    publishProgress(backValue1); //progress 상태를 update 뽑아냄 --> onProgressUpdate 호출되고 매개변수가 전달됨.
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return backValue1;  //onPostExecute 에 넘거나는 값 매개변수 integer에들어감
        }// ※ doInBackground() 에서 시간이 많이 걸리는 '통신' 작업이나 복잡한 연산 작업등을 (비동기로)수행케 해야 한다.





        //백그라운드 작업 도중 (여러번) 호출 가능, 진행상황 업데이트,중간중간 UI 업데이트시 사용
        @Override
        protected void onProgressUpdate(Integer... values) { //class Background extends AsyncTask<Integer,Integer,Integer> 의두번째 integer값이 여기임
            Log.d("myapp","Progress : " + values[0] + "%"); //publishProgress(i) 가 보낸 값
            super.onProgressUpdate(values);

            tvBackValue1.setText("onProgressUpdate: "+values[0]); // **개중요 많이씀 Handler 없이도 메인UI접근 가능!! 이러한이유때문에 사용
        }




        //doInBackground() 완료되면 호출
        @Override
        protected void onPostExecute(Integer integer) { //doInBackground 에서 return한 값을 매개변수로받는다
            Log.d("myapp","Result : "+ integer);
            super.onPostExecute(integer);

            tvBackValue1.setText("onPostExecute: "+ integer); // **개중요 많이씀 Handler 없이도 메인UI접근 가능!!
        }
    }//end AsyncTask

    public void mOnClick(View v){
        mainValue++;
        tvMainValue.setText("MainValue: "+mainValue);
    }


}//end class
