package com.example.learnenglish.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Apicategory.Book;
import com.example.learnenglish.R;
import com.example.learnenglish.userProcess.selectcart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Myclass> {
    Context context;
    ArrayList<Book>arrayList;

public SearchAdapter(Context context, ArrayList<Book> arrayList){
    this.context=context;
    this.arrayList=arrayList;
}

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_custom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {
        Picasso.get().load(arrayList.get(position).getUrl()).placeholder(R.drawable.placeimg).into(holder.serimg);
        holder.sertxt.setText(arrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder{
      ImageView serimg;
      TextView  sertxt;

      CardView cardView;

        public Myclass(@NonNull View itemView) {
            super(itemView);

            serimg= itemView.findViewById(R.id.serimage);
            sertxt= itemView.findViewById(R.id.srctxtview);
            cardView= itemView.findViewById(R.id.sercart);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, selectcart.class);
                    intent.putExtra("categoryid",arrayList.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
