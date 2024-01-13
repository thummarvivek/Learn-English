package com.example.learnenglish.userseen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.learnenglish.Adapters.AboutAdapter;
import com.example.learnenglish.R;

public class About_us extends AppCompatActivity {

    ListView listView;

    String  tit[]={"Welcome to our LearnEnglish where we specialize in LearnEnglish and selling books. Our passion for literature and desire to promote sustainability led us to create this platform where book lovers can find unique and rare books, while also contributing to reducing waste and environmental impact.",
                    "",
                    "",
                    "",
                    ""
    };

    String  descri[]={"",
                          "Our team consists of avid readers and book collectors who understand the value of preserving literary treasures. We carefully curate our collection of books, selecting only the best and most interesting titles to offer to our customers. We take great care to ensure that each book is in good condition, and we provide accurate descriptions of any wear or damage.",
                          "Let's focus on common English learning and English speaking abilities provides.In addition to buy books, We provide books to people at a cheaper price than the market price. our Platform is not only earn money but also contribute to the sustainable economy by extending the life of your books.",
                          "Don't be afraid of making mistakes. People often get things wrong. Good language learners notice their mistakes and learn from them. that's our learn english topic provides of works in our app.",
                          "Thank you for visiting our website, and we look forward to helping you discover your next favorite book!"
            };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        listView=findViewById(R.id.aboutbar);

        AboutAdapter aboutAdapter= new AboutAdapter(About_us.this,tit,descri);
        listView.setAdapter(aboutAdapter);
    }
}