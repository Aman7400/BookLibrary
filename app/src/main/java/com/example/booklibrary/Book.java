package com.example.booklibrary;

public class Book {

    private String mBookId, mBookName, mBookAuthor, mBookReview, mBookRating,mAddedOn;



    public Book(String mBookId, String mBookName, String mBookAuthor, String mBookRating,String mAddedOn ,String mBookReview) {
        this.mBookId = mBookId;
        this.mBookName = mBookName;
        this.mBookAuthor = mBookAuthor;
        this.mBookReview = mBookReview;
        this.mBookRating = mBookRating;
        this.mAddedOn = mAddedOn;
    }

    public String getBookId() {
        return mBookId;
    }

    public String getBookRating() {
        return mBookRating;
    }

    public String getBookAuthor() {
        return mBookAuthor;
    }

    public String getBookName() {
        return mBookName;
    }

    public String getBookReview() {
        return mBookReview;
    }

    public String getAddedOn() {
        return mAddedOn;
    }
}
