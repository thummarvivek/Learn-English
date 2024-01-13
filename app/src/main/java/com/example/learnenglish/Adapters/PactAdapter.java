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

public class PactAdapter extends RecyclerView.Adapter<PactAdapter.Myclass> {
    Context context;
    ArrayList<Aence>arrayList;

    public PactAdapter(Context context, ArrayList<Aence> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PactAdapter.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pactcustom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PactAdapter.Myclass holder, int position) {
        holder.tot6.setText(arrayList.get(position).getPact());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder{
        TextView tot6;
        public Myclass(@NonNull View itemView) {
            super(itemView);
            tot6 =itemView.findViewById(R.id.pact006);
        }
    }
}
