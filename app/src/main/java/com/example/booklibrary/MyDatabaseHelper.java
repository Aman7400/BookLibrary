package com.example.booklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {


    private Context context;

    private static final String DB_NAME = "BOOKS.DB";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "Added_Books";
    private static final String COL_ID = "id";
    private static final String COL_BOOK_NAME = "Title";
    private static final String COL_AUTHOR = "Author";
    private static final String COL_RATING = "Rating";
    private static final String COL_REVIEW = "Review";
    private static final String COL_ADDED_ON = "Added_On";






    public MyDatabaseHelper(@Nullable Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String query = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_BOOK_NAME + " TEXT, " + COL_AUTHOR + " TEXT, " +
                COL_RATING + " TEXT, " + COL_ADDED_ON + " TEXT, " +
                COL_REVIEW + " TEXT);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    /* Add Books to Database */

     void addBooks(String title, String author, String  rating, String addedOn, String review){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_BOOK_NAME,title);
        cv.put(COL_AUTHOR,author);
        cv.put(COL_RATING,rating);
        cv.put(COL_REVIEW,review);
        cv.put(COL_ADDED_ON,addedOn);


        long result = db.insert(TABLE_NAME,null,cv);

        /* Check Book added Successfully */

        if(result == -1){
            Toast.makeText(context, "Some Error Occurred", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Book Added", Toast.LENGTH_SHORT).show();
        }

    }

    /* Show Books in Database */

     Cursor showBooks(){

        String query = "SELECT * FROM " + TABLE_NAME ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if (db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;


    }

    /* Update Book Details */

    void updateBook(String id,String updatedName, String updatedAuthor, String upDatedReview, String updatedRating){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_BOOK_NAME,updatedName);
        cv.put(COL_AUTHOR,updatedAuthor);
        cv.put(COL_RATING,updatedRating);
        cv.put(COL_REVIEW,upDatedReview);


        long result = db.update(TABLE_NAME,cv,
                "id=?",new String[]{id}
                );

        /* Check Book added Successfully */

        if(result == -1){
            Toast.makeText(context, "Some Error Occurred", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Book Updated", Toast.LENGTH_SHORT).show();
        }


    }


    /* Delete a book  */

    void deleteBook(String id){

        SQLiteDatabase db = this.getWritableDatabase();

      long result =   db.delete(TABLE_NAME,"id=?",new String[]{id});


        if(result == -1){
            Toast.makeText(context, "Some Error Occurred", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Book Removed", Toast.LENGTH_SHORT).show();
            Log.d("remove", "deleteBook: book removed ");
        }

    }


    /* Delete All Books */

    void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME);
    }


}
