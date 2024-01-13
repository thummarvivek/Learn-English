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

public class PaptAdapter extends RecyclerView.Adapter<PaptAdapter.Mycalss> {
    Context context;
    ArrayList<Aence>arrayList;

    public PaptAdapter(Context context, ArrayList<Aence> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PaptAdapter.Mycalss onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.paptcustom,parent,false);
        return new Mycalss(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PaptAdapter.Mycalss holder, int position) {
        holder.tot7.setText(arrayList.get(position).getPapt());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Mycalss extends RecyclerView.ViewHolder {
        TextView tot7;
        public Mycalss(@NonNull View itemView) {
            super(itemView);
            tot7 =itemView.findViewById(R.id.papt007);
        }
    }
}
