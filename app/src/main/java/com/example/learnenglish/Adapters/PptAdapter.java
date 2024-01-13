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

public class PptAdapter extends RecyclerView.Adapter<PptAdapter.Myclass> {
    Context context;
    ArrayList<Aence>arrayList;

    public PptAdapter(Context context,ArrayList<Aence>arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PptAdapter.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pptcustom,parent,false);
        return new PptAdapter.Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PptAdapter.Myclass holder, int position) {
        holder.tot3.setText(arrayList.get(position).getPpt());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {
        TextView tot3;

        public Myclass(@NonNull View v) {
            super(v);
            tot3=v.findViewById(R.id.ppt003);
        }
    }
}
