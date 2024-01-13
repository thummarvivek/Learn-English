package com.example.learnenglish.Adapters;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Apicategory.Tence;
import com.example.learnenglish.R;
import com.example.learnenglish.userseen.all_vocabulary_tence;

import java.util.ArrayList;
import java.util.Locale;

public class RVAdapter4 extends RecyclerView.Adapter<RVAdapter4.Myclass>{

    Context context;
    ArrayList<Tence> arrayList;

    public RVAdapter4(Context context, ArrayList<Tence> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RVAdapter4.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.all_verb_tence_custom,parent,false);
        return new RVAdapter4.Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter4.Myclass holder, int position) {
        holder.tv1.setText(arrayList.get(position).getId() + "");
        holder.tv2.setText(arrayList.get(position).getVerb());
        holder.tv3.setText(arrayList.get(position).getTranslate());
        holder.tv4.setText(arrayList.get(position).getPastForm());
        holder.tv5.setText(arrayList.get(position).getPastParticiple());
        holder.tv6.setText(arrayList.get(position).getPresentParticiple());
        holder.tv7.setText(arrayList.get(position).getPresentTence());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder{

        TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tvoice;
        TextToSpeech tts;


        public Myclass(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.textView0);
            tv2=itemView.findViewById(R.id.textView);
            tv3=itemView.findViewById(R.id.textView3);
            tv4=itemView.findViewById(R.id.textView6);
            tv5=itemView.findViewById(R.id.textView9);
            tv6=itemView.findViewById(R.id.textView12);
            tv7=itemView.findViewById(R.id.textView15);
            tvoice=itemView.findViewById(R.id.voicetxt);

            tts=new TextToSpeech(itemView.getContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if (i!=TextToSpeech.ERROR){
                        tts.setLanguage(Locale.UK);
                    }

                }
            });

            tvoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String gvoice =tv2.getText().toString();
                    tts.speak(gvoice,TextToSpeech.QUEUE_FLUSH,null);

                }
            });
        }
    }
}
