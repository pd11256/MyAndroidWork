package com.lec.android.a008_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    PersonAdapter adapter;
    RecyclerView rv;
    EditText etName,etAge,etAddress;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            rv = findViewById(R.id.rv);

            //RecyclerView 를 사용하기 위해서는 LayoutManager 지정해주어야 한다.*************
            RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rv.setLayoutManager(layoutManager);
            adapter = new PersonAdapter(); //RecyclerView 에 Adapter 장착!
            rv.setAdapter(adapter);


            etName= findViewById(R.id.etName);
            etAge= findViewById(R.id.etAge);
            etAddress= findViewById(R.id.etAddress);

            btnAdd = findViewById(R.id.btnAdd);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Person person = new Person(etName.getText().toString(),etAge.getText().toString(),etAddress.getText().toString());
                    adapter.addPerson(person);
                    etName.setText("");
                    etAge.setText("");
                    etAddress.setText("");

                }
            });



    } //end create

        //샘플 데이터 가져오기

}//end class
