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

public class FctAdapter extends RecyclerView.Adapter<FctAdapter.Myclass> {
    Context context;
    ArrayList<Aence>arrayList;

    public FctAdapter(Context context, ArrayList<Aence> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public FctAdapter.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fctcustom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FctAdapter.Myclass holder, int position) {
        holder.tot10.setText(arrayList.get(position).getFct());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {
        TextView tot10;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            tot10 =itemView.findViewById(R.id.fct0010);
        }
    }
}
