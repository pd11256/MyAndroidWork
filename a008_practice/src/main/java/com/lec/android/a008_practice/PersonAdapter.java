package com.lec.android.a008_practice;




import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    List<Person> items = new ArrayList<>();

    static PersonAdapter adapter;
    //Adapter 생성자
    public PersonAdapter(){this.adapter = this;}



    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View itemView =  inf.inflate(R.layout.item,parent,false);

        return new ViewHolder(itemView);
    }


    @Override //position 몇번째 데이터값 가져온다
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position) {
        Person item = items.get(position); //Arraylist의 메서드
        holder.setItem(item);

    }


    @Override
    public int getItemCount() {
        return items.size(); //arrayList에 담겨잇는 개수 리턴
    }

    //nested class (static inner) 로 ViewHolder클래스 정의
    static class ViewHolder extends RecyclerView.ViewHolder{

        //ViewHolder 에 담긴 가각의 View 들을 담을 변수 ,xml에서 버튼이나 여러가지 기능추가시 여기다가 추가

        TextView tvName, tvAge, tvAddress;
        ImageButton btnDelete;

        // 생성자필수*******************ViewHolder 생성자에다가 각각의 장치를 장착해준다!!!!!!!! ! ! ! ! !
        public ViewHolder(@NonNull View itemView) { //tiem 레이아웃의 view 객체가 전달됨.
            super(itemView);

            // View 객체 가져오기

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            btnDelete= itemView.findViewById(R.id.btnDelete);
            
            
            //삭제버튼누르면 item 삭제
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //position 정보 필요하다
                    //adapter 로 부터 데이터 삭제도 진행되어야한다.
                    adapter.removeItem(getAdapterPosition()); //데이터 삭제
                    //데이터 변경(수정 삭제 추가) 내역이 adapter 에 반영되어야 정상적으로 동작함!! ★★★
                    adapter.notifyDataSetChanged(); //이거하나만 해주면된다.

                }
            });


        }// end 생성자

        public void setItem(Person item){

            tvName.setText(item.getName());
            tvAge.setText(item.getAge());
            tvAddress.setText(item.getAddress());
        }
    }//end ViewHolder

    private void removeItem(int position) {
        items.remove(position);
    }
    public void addPerson(Person item) {
        items.add(item);
    }


}
