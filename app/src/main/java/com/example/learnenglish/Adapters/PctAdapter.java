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

public class PctAdapter extends RecyclerView.Adapter<PctAdapter.Myclass> {
    Context context;
    ArrayList<Aence>arrayList;

    public PctAdapter(Context context,ArrayList<Aence> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public PctAdapter.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.pctcustom,parent,false);
        return new PctAdapter.Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PctAdapter.Myclass holder, int position) {
        holder.tot2.setText(arrayList.get(position).getPct());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder{
        TextView tot2;

        public Myclass(@NonNull View itemView) {
            super(itemView);
            tot2=itemView.findViewById(R.id.pctt002);
        }
    }
}
