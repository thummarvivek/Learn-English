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

public class PapctAdapter extends RecyclerView.Adapter<PapctAdapter.Myclass> {
    Context context;
    ArrayList<Aence>arrayList;

    public PapctAdapter(Context context, ArrayList<Aence> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PapctAdapter.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.papctcustom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PapctAdapter.Myclass holder, int position) {
        holder.tot8.setText(arrayList.get(position).getPapct());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {
        TextView tot8;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            tot8 =itemView.findViewById(R.id.papct008);
        }
    }
}
