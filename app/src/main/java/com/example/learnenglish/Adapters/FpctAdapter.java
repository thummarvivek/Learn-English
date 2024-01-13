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

public class FpctAdapter extends RecyclerView.Adapter<FpctAdapter.Myclass> {
    Context context;
    ArrayList<Aence>arrayList;

    public FpctAdapter(Context context, ArrayList<Aence> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public FpctAdapter.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fpctcustom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FpctAdapter.Myclass holder, int position) {
        holder.tot12.setText(arrayList.get(position).getFpct());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder{
        TextView tot12;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            tot12 =itemView.findViewById(R.id.fpct0012);

        }
    }
}
