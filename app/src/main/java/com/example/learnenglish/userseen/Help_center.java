package com.example.learnenglish.userseen;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ListView;

import com.example.learnenglish.Adapters.HelpAdapter;
import com.example.learnenglish.R;

public class Help_center extends AppCompatActivity {

    ListView listView;

    String title[]={"Welcome to our Help Center for learn english and buy books! We're here to help you navigate our platform and answer any questions you may have",
            "Buy BOOKS:\n\nIf you have books you would like to buy, here are the steps to follow:-",
            "Create an account:" ,"Upload your listings:","Our book price:","Find a book:"
            ,"\nLearn english: \nIf you want to learn English from our platform, here's how to get started"
            ,"English verb:" ,"Skills development:" ,"Tense:" ,""};
    String description[]={
            "",
            ""
            ,"The first step to buy books and learn english is to create an account on our Application. This will enable you to upload and manage your listings."
            ,"Once you have an account, you can start uploading personal details and your listings. Ensure that the accurate details are correct for your account which is the user base for managing the account."
            ,"Our book price is set by default. You trust our product. Learn English app provides cheap price to buy book."
            ,"Use our search bar to find the books you're interested in."
            ,""
            ," Our team provides many verbs for the user to list. It is the first stage of learning."
            , "This app helps develop the user's skills and provides advanced grammar, smart English, speaking skills and much more."
            ," We provide all types of Tence."
            ,"If you have any further questions about selling or reselling books on our platform, please don't hesitate to contact us. Our customer service team is available to assist you with any issues or concerns. Thank you for choosing our platform to buy or sell your old books"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);

        listView=findViewById(R.id.helpbar);

        HelpAdapter helpAdapter= new HelpAdapter(Help_center.this,title,description);
        listView.setAdapter(helpAdapter);



    }
}