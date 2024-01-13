package com.example.learnenglish.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Apicategory.Book;
import com.example.learnenglish.Apicategory.SmartEnglish1;
import com.example.learnenglish.R;
import com.example.learnenglish.userseen.Smartenglish;

import java.util.ArrayList;
import java.util.Random;

public class RvAdapter3 extends RecyclerView.Adapter<RvAdapter3.Myclass>{

    Context context;
    ArrayList<SmartEnglish1> arrayList;

    public RvAdapter3(Context context, ArrayList<SmartEnglish1> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RvAdapter3.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.smartenglishcustom,parent,false);
        return new RvAdapter3.Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter3.Myclass holder, int position) {
//        holder.tv1.setText(arrayList.get(position).getId() + "");
        holder.tv2.setText(arrayList.get(position).getSentence());
        holder.tv3.setText(arrayList.get(position).getTranslate());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder{

//        TextView tv1,tv2,tv3;
    TextView tv2,tv3;


        public Myclass(@NonNull View itemView) {
            super(itemView);
//            tv1=itemView.findViewById(R.id.text0);
            tv2=itemView.findViewById(R.id.text2);
            tv3=itemView.findViewById(R.id.text4);
        }
    }

}
