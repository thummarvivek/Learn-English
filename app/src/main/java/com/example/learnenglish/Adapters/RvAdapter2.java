package com.example.learnenglish.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Apicategory.Book;
import com.example.learnenglish.R;
import java.util.ArrayList;

public class RvAdapter2 extends RecyclerView.Adapter<RvAdapter2.Myclass>{

    Context context;
    ArrayList<Book> arrayList;

//    RvAdapter2(Context context,ArrayList<> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RvAdapter2.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.grammarcustom,parent,false);
        return new RvAdapter2.Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter2.Myclass holder, int position) {
        holder.tv1.setText(arrayList.get(position).getId() + "");
        holder.tv2.setText(arrayList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder{

        TextView tv1,tv2;


        public Myclass(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.textView);
            tv2=itemView.findViewById(R.id.textView2);
        }
    }
}
