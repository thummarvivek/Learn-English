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
import com.example.learnenglish.fragment.SellFragment;
import com.example.learnenglish.userProcess.selectcart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.Myclass>{
    Context context;
    ArrayList<Book> arrayList;

   public RVAdapter(Context context,ArrayList<Book> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    public RVAdapter(SellFragment sellFragment, ArrayList<Book> arrayList) {
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.categorylist,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {


//        final Book temp=arrayList.get(position);

        holder.tv1.setText(arrayList.get(position).getId() + "");
        holder.tv2.setText(arrayList.get(position).getName());
        Picasso.get().load(arrayList.get(position).getUrl()).placeholder(R.drawable.placeimg).into(holder.imgview);

//        holder.imgview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(context, selectcart.class);
//                intent.putExtra("imagename",temp.getUrl());
//                intent.putExtra("header",temp.getName());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//
//
//
//            }
//        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {
        ImageView imgview;
        TextView tv1,tv2;

        CardView cardView;


        public Myclass(@NonNull View itemView) {
            super(itemView);
            imgview=itemView.findViewById(R.id.imageView);
            tv1=itemView.findViewById(R.id.textView);
            tv2=itemView.findViewById(R.id.textView2);
            cardView =itemView.findViewById(R.id.catcard);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, selectcart.class);
                    intent.putExtra("categoryid" , arrayList.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });
        }
    }
}

