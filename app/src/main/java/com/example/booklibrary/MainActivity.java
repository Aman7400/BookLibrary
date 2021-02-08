package com.example.booklibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> ids, titles,authors,ratings,reviews,dates;
    private ArrayList<Book> books;
    private RecyclerView mBooksRecyclerView;

    private FloatingActionButton mAddBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* Add Books Activity*/

        mAddBook = findViewById(R.id.addBooks);
        mAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddBooks.class));
                finish();
            }
        });

        /* Add Books to Recycler View */

        ids = new ArrayList<>();
        titles = new ArrayList<>();
        authors = new ArrayList<>();
        ratings = new ArrayList<>();
        reviews = new ArrayList<>();
        dates = new ArrayList<>();
        books = new ArrayList<>();

        displayBooks();

        /* Create an ArrayList of Books */

        for (int i = 0; i <titles.size() ; i++) {
            books.add(new Book(ids.get(i),titles.get(i),authors.get(i),ratings.get(i),dates.get(i),reviews.get(i)));
        }

        mBooksRecyclerView = findViewById(R.id.addedBooksRecyclerView);
        mBooksRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        /* Swipe to Delete */
        mBooksRecyclerView.setAdapter(new BookAdapter(this,books));







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_all,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.deleteAllBooks){

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Delete All Books")
                    .setMessage("Are You Sure to Delete All Books  ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
                    myDatabaseHelper.deleteAll();
                    recreate();
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alertDialog.show();

        }
        return super.onOptionsItemSelected(item);
    }

    void displayBooks(){

        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
        Cursor cursor =  myDatabaseHelper.showBooks();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Books Added", Toast.LENGTH_SHORT).show();
        }else {

            while (cursor.moveToNext()){
                ids.add( cursor.getString(0));
               titles.add( cursor.getString(1));
               authors.add( cursor.getString(2));
               ratings.add( cursor.getString(3));
               dates.add( cursor.getString(4));
               reviews.add( cursor.getString(5));
            }

        }

    }

}