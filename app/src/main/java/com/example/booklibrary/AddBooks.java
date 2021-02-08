package com.example.booklibrary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import java.time.LocalDate;

public class AddBooks extends AppCompatActivity {

    private EditText mTitle,mAuthor,mReview;
    private RatingBar mRating;
    private Button mAddBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);


        /* Set Title */
        getSupportActionBar().setTitle("Add Book");

        mTitle = findViewById(R.id.bookTitle);
        mAuthor = findViewById(R.id.updatedBookAuthor);
        mReview = findViewById(R.id.updatedBookReview);
        mRating = findViewById(R.id.updatedRating);

        mAddBook = findViewById(R.id.updateBtn);
        mAddBook.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(mTitle.getText().toString())){
                    mTitle.setError("Book name can't be empty");
                    return;
                }
                if(TextUtils.isEmpty(mAuthor.getText().toString())){
                    mAuthor.setError("Author name can't be empty");
                    return;
                }


                String  addedOn =  LocalDate.now().getDayOfMonth()
                        + " " + LocalDate.now().getMonth().toString().substring(0,3) + ", " + LocalDate.now().getYear();


                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(AddBooks.this);
                myDatabaseHelper.addBooks(
                        mTitle.getText().toString().trim(),
                        mAuthor.getText().toString().trim(),
                       String.valueOf(mRating.getRating()),
                        addedOn,
                        mReview.getText().toString().trim()

                );


                startActivity(new Intent(AddBooks.this,MainActivity.class));
                finish();

            }
        });


    }
}