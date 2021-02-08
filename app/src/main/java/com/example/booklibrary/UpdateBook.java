package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class UpdateBook extends AppCompatActivity {


    private EditText mUpdatedTitle,mUpdatedAuthor,mUpdatedReview;
    private RatingBar mUpdatedRating;
    private Button mUpdateBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        /* Set Title */
        getSupportActionBar().setTitle("Update Book");

        /* Fill Old values in text Field */

        String bookId = getIntent().getStringExtra("id");

        mUpdatedTitle = findViewById(R.id.updatedBookTitle);
        mUpdatedTitle.setText(getIntent().getStringExtra("name"));

        mUpdatedAuthor = findViewById(R.id.updatedBookAuthor);
        mUpdatedAuthor.setText(getIntent().getStringExtra("author"));

        mUpdatedReview = findViewById(R.id.updatedBookReview);
        mUpdatedReview.setText(getIntent().getStringExtra("review"));

        mUpdatedRating = findViewById(R.id.updatedRating);
        mUpdatedRating.setRating(Float.parseFloat(getIntent().getStringExtra("rating")));

        mUpdateBook = findViewById(R.id.updateBtn);
        mUpdateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(UpdateBook.this);
                myDatabaseHelper.updateBook(bookId,
                        mUpdatedTitle.getText().toString().trim(),
                        mUpdatedAuthor.getText().toString().trim(),
                        mUpdatedReview.getText().toString().trim(),
                        String.valueOf(mUpdatedRating.getRating())
                        );

                Intent intent =  new Intent(UpdateBook.this,MainActivity.class);
                startActivity(intent);
            }
        });

        

    }
}