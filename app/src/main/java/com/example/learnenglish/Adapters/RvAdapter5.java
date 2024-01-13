package com.example.learnenglish.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Apicategory.ShortNote;
import com.example.learnenglish.R;

import java.util.ArrayList;

public class RvAdapter5 extends RecyclerView.Adapter<RvAdapter5.Myclass> {
Context context;
ArrayList<ShortNote>arrayList;

   public RvAdapter5(Context context,ArrayList<ShortNote> arrayList){
       this.context = context;
       this.arrayList= arrayList;
   }

    @NonNull
    @Override
    public RvAdapter5.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.speakingcustom,parent,false);
        return new RvAdapter5.Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter5.Myclass holder, int position) {
//       holder.short1.setText(arrayList.get(position).getId()+ "");
        holder.short2.setText(arrayList.get(position).getText());
        holder.short3.setText(arrayList.get(position).getTextSecond());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {
       TextView short1,short2,short3;
        public Myclass(@NonNull View itemView) {
            super(itemView);
//            short1=itemView.findViewById(R.id.texttt);
            short2=itemView.findViewById(R.id.shortText);
            short3=itemView.findViewById(R.id.Shortshow);
        }
    }
}
