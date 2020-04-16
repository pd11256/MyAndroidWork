package com.lec.android.a005_image;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.NetworkErrorException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.NetworkOnMainThreadException;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
/**
 *  인터넷 상의 이미지 보여주기
 *      1. 권한을 획득한다 (인터넷에 접근할수 있는 권한을 획득한다)  - 메니페스트 파일 --> internet경로추가
 *      2. Thread 에서 웹의 이미지를 받아온다 - honeycomb(3.0) 버젼 부터 바뀜
 *      3. 외부쓰레드에서 메인 UI에 접근하려면 Handler 를 사용해야 한다.** 안드로이드 정책때문에
 *
 * 절차
 *
 */




public class Main4Activity extends AppCompatActivity {

    // 이미지 URL, 반드시 https:// 이어야 한다.
    String imgUrl = "https://developer.android.com/studio/images/studio-icon-stable.png";


    ImageView iv1;
    TextView tvUrl;

    Handler handler = new Handler(); //외부 쓰레드에서 메인 UI화면에 그릴때 사용




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        iv1 =findViewById(R.id.iv1);
        tvUrl =findViewById(R.id.tvUrl);

        // Thread t = new Thread(Runnable);
        Thread t = new Thread(new Runnable() {

            //별도의 작업 Thread 이다 Main을 건드릴수없다
            @Override
            public void run() {
                try {
                    //Thread 없이 수행하면
                    //android.os.NetworkOnMainThreadException 오류
                    URL url = new URL(imgUrl);
                    InputStream in = url.openStream();
                    final Bitmap bm = BitmapFactory.decodeStream(in);

                    //android.NetworkOnMainThreadException 오류
                    //iv1.setImageBitmap(bm);
                    //android.view.ViewRootImpl$CalledFromWrongThreadException:
                    // Only the original thread that created a view hierarchy can touch its views.
                    //Main Thread 에있던 view에서 별도의 Thread를 만들고 다시 Main Thread를 건드리려고 하지만
                    //이작업은 허용되지않는다 한드시 Handler를 거쳐야한다.


                    //즉 -->
                    // 외부쓰레드에서 메인UI 에 접근할때는
                    // 반드시 Handler 객체 사용.
                    // ※ Handler를 사용하지 않으면 어떻게 되는지 보자
                    

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iv1.setImageBitmap(bm);
                            tvUrl.setText(imgUrl);
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        t.start();

        // Bitmap <-InpustStream <- URL <- "url" ***이순서대로 뽑은거임 천천히 따라가보기

    }
}
