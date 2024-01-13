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

public class PpctAdapter extends RecyclerView.Adapter<PpctAdapter.Myclass> {
    Context context;
    ArrayList<Aence>arrayList;

    public PpctAdapter(Context context, ArrayList<Aence> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PpctAdapter.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ppctcustom,parent,false);
        return new PpctAdapter.Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PpctAdapter.Myclass holder, int position) {
        holder.tot4.setText(arrayList.get(position).getPpct());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {
        TextView tot4;

        public Myclass(@NonNull View itemView) {
            super(itemView);
            tot4=itemView.findViewById(R.id.ppct004);

        }
    }
}
