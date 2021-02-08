package com.example.booklibrary;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter <BookAdapter.BookViewHolder>  {

    private Context context;
    private ArrayList<Book> mBooks;
    private Dialog mInfo;

    public BookAdapter(Context context , ArrayList<Book> books) {

        this.context = context;
        this.mBooks = books;


    }


    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
     View view =    layoutInflater.inflate(R.layout.added_book,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {




//        holder.mSerialNo.setText(""+(position+1));
        holder.mTitle.setText(mBooks.get(position).getBookName());
        holder.mAuthor.setText(mBooks.get(position).getBookAuthor());
        holder.mRating.setText(String.valueOf(mBooks.get(position).getBookRating()));
        holder.mBookHolderCard.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                mInfo = new Dialog(context);
                mInfo.setContentView(R.layout.book_popup);

                TextView infoTitle  = mInfo.findViewById(R.id.popupTitle);
                infoTitle.setText(mBooks.get(position).getBookName());

                TextView infoAuthor  = mInfo.findViewById(R.id.popupAuthor);
                infoAuthor.setText(mBooks.get(position).getBookAuthor());

                TextView infoDateAdded  = mInfo.findViewById(R.id.addedOn);
                infoDateAdded.setText(mBooks.get(position).getAddedOn());

                TextView infoReview = mInfo.findViewById(R.id.popupReview);
                infoReview.setText(mBooks.get(position).getBookReview());

                Button bookDelete = mInfo.findViewById(R.id.deleteBook);
                bookDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyDatabaseHelper databaseHelper = new MyDatabaseHelper(context);
                        databaseHelper.deleteBook(mBooks.get(position).getBookId());

                        Intent intent =   new Intent(context,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        ((MainActivity)context).finish();
                        context.startActivity(intent);



                    }
                });

                Button updateBookInfo = mInfo.findViewById(R.id.updateBook);
                updateBookInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(context,UpdateBook.class);
                        intent.putExtra("id",mBooks.get(position).getBookId());
                        intent.putExtra("name",mBooks.get(position).getBookName());
                        intent.putExtra("author",mBooks.get(position).getBookAuthor());
                        intent.putExtra("review",mBooks.get(position).getBookReview());
                        intent.putExtra("rating",mBooks.get(position).getBookRating());


                        context.startActivity(intent);
                    }
                });


                mInfo.show();




            }
        });


    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        TextView mSerialNo, mTitle, mAuthor, mRating;
        LinearLayout mBookHolderCard;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

//            mSerialNo = itemView.findViewById(R.id.serialNo);
            mTitle = itemView.findViewById(R.id.BookTitle);
            mAuthor = itemView.findViewById(R.id.BookAuthor);
            mRating = itemView.findViewById(R.id.BookRating);
            mBookHolderCard = itemView.findViewById(R.id.bookCardHolder);

            mBookHolderCard.setAnimation(AnimationUtils.loadAnimation(context,R.anim.recycler_item_animation));


        }
    }

}
