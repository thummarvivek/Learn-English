package com.example.learnenglish.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Apicategory.Book;
import com.example.learnenglish.Apicategory.Tence;
import com.example.learnenglish.R;
import java.util.ArrayList;

public class RvAdapter1 extends RecyclerView.Adapter<RvAdapter1.Myclass>{

    Context context;
    ArrayList<Tence> arrayList;

    public RvAdapter1(Context context, ArrayList<Tence> arrayList)
   {
       this.context = context;
       this.arrayList = arrayList;
   }

    @NonNull
    @Override
    public RvAdapter1.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.vocabularycustom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter1.Myclass holder, int position) {
        holder.tv1.setText(arrayList.get(position).getId() + "");
        holder.tv2.setText(arrayList.get(position).getVerb());
        holder.tv3.setText(arrayList.get(position).getTranslate());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder{

        TextView tv1,tv2,tv3;


        public Myclass(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.textView0);
            tv2=itemView.findViewById(R.id.textView);
            tv3=itemView.findViewById(R.id.textView3);
        }
    }
}

