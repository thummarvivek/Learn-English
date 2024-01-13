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

public class FptAdapter extends RecyclerView.Adapter<FptAdapter.Myclass>{
    Context context;
    ArrayList<Aence>arrayList;

    public FptAdapter(Context context, ArrayList<Aence> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public FptAdapter.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fptcustom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FptAdapter.Myclass holder, int position) {
        holder.tot11.setText(arrayList.get(position).getFpt());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {

        TextView tot11;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            tot11 =itemView.findViewById(R.id.fctt0011);
        }
    }
}
