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

public class PastAdapter extends RecyclerView.Adapter<PastAdapter.Myclass> {
    Context context;
    ArrayList<Aence>arrayList;

    public PastAdapter(Context context, ArrayList<Aence> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PastAdapter.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pastcustom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PastAdapter.Myclass holder, int position) {
        holder.tot5.setText(arrayList.get(position).getPast());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {
        TextView tot5;

        public Myclass(@NonNull View itemView) {
            super(itemView);
            tot5 =itemView.findViewById(R.id.past005);

        }
    }
}
