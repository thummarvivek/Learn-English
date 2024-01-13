package com.example.learnenglish.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Apicategory.Aence;
import com.example.learnenglish.R;

import java.util.ArrayList;

public class SptAdapter extends RecyclerView.Adapter<SptAdapter.Myclass> {
    Context context;
    ArrayList<Aence>arrayList;


    public SptAdapter(Context context, ArrayList<Aence> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public SptAdapter.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.sptcustom,parent,false);
        return new SptAdapter.Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SptAdapter.Myclass holder, int position) {
        holder.tot1.setText(arrayList.get(position).getPst());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder{
        TextView tot1;

        public Myclass(@NonNull View itemView) {
            super(itemView);
            tot1=itemView.findViewById(R.id.sptt001);
        }
    }
}
