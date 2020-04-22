package com.lec.android.a010_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
// SQLite3 : 안드로이드에 기본탑재된 모바일 용으로 제작된 경량화 DB
//          C언어로 엔진이 제작되어 가볍다
//          안드로이드에서 sqLite3 를 쉽게 사용할 수 있도록 SQLiteOpenHelper클래스제공

// SQLite 를 사용한 데이터 저장
//   1. SQLiteOpenHelper 를 상속받은 클래스 정의
//      onCreate(), onUpgrade() 작성
//   2. 위 Helper 로부터 SQLiteDatabase  DB 객체 추출
//   3. DML 명령은 : execSQL()
//      SELECT 명령은 : rawQuery() --> 결과는 Cursor 객체 사용하여 다룸



public class Main3Activity extends AppCompatActivity {
    MySQLiteOpenHelper3 helper;
    String dbName = "st_file.db"; //'파일' 의 형태로 DB가 저장된다.
    int dbVersion =1 ; //데이터베이스 버젼
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //객체 뽑아내기
        helper =new MySQLiteOpenHelper3(
                this, //현재 화면의 제어권자
                    dbName, //DB이름
                        null, //커서팩토리-null : 표준 커서가 사용됨.
                            dbVersion // DB버전
                 );

        //helper로부터 뽑아내기
        try {
            db = helper.getWritableDatabase(); //일고 스끼 가능한 db
//        db = helper.getReadableDatabase() //읽기 전용 DB (ex: SELECT만 사용하는경우 ..)
        }catch (SQLException e){
            e.printStackTrace();
            Log.e("myapp","데이터베이스를 얻어올 수 없음");
            finish(); //액티비티 종료
        }
       // insert();
        select();
       // update();
       // delete();


    }//end onCreate
    void delete(){
        //TODO
        db.execSQL("delete from mytable where name = '솨솨솨'");
        Log.d("myapp", "delete 성공");
    }

    void update(){
        //TODO
        db.execSQL("UPDATE mytable SET name = '하하하' WHERE id=1");
        Log.d("myapp", "업데이트 성공");
    }
    void select(){
        //TODO
        Cursor c = db.rawQuery("SELECT*FROM mytable", null);
        while (c.moveToNext()){
            int id = c.getInt(0); //컬럼 인덱스 0부터 시작!!!! ,jdbc 는 1부터 시작
            String name = c.getString(1);
            Log.d("myapp","id: "+id +"/name: "+name);
        }
    }

    void insert(){
        db.execSQL("Insert INTO mytable (name) values ('김김김')");
        db.execSQL("Insert INTO mytable (name) values ('이이이')");
        db.execSQL("Insert INTO mytable (name) values ('웅우웅')");
        db.execSQL("Insert INTO mytable (name) values ('나나나난')");
        db.execSQL("Insert INTO mytable (name) values ('솨솨솨')");
        Log.d("myapp", "Insert 성공~!");
    }

}//end class
