package com.uhcl.bmo.assignment5;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by BMo on 4/17/2018.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Library";
    private static final int DB_VER = 1;
    private static final String BOOK_TABLE = "Book";
    private static final String TITLE_COLUMN = "Title";
    private static final String AUTHOR_COLUMN = "Author";
    private static final String SUBJECT_COLUMN = "Subject";
    private static final String DUE_COLUMN = "Due";

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + BOOK_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + TITLE_COLUMN + " TEXT," +
                AUTHOR_COLUMN + " TEXT," + SUBJECT_COLUMN + " TEXT," + DUE_COLUMN + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BOOK_TABLE);

        // create new tables
        onCreate(db);
    }

    //insert new records to table
    public void insertBook(Book stu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITLE_COLUMN, stu.getTitle());
        values.put(AUTHOR_COLUMN, stu.getAuthor());
        values.put(SUBJECT_COLUMN, stu.getSubject());
        values.put(DUE_COLUMN, stu.getDue());

        db.insert(BOOK_TABLE, null,values);
    }

    //update a record in table
    public void updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITLE_COLUMN, book.getTitle());
        values.put(AUTHOR_COLUMN, book.getAuthor());
        values.put(SUBJECT_COLUMN, book.getSubject());
        values.put(DUE_COLUMN, book.getDue());

        db.update(BOOK_TABLE, values, TITLE_COLUMN + " = ?",
                new String[]{ String.valueOf(book.getTitle()) });
    }

    //delete a record from table
    public void deleteBook(String bookTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BOOK_TABLE, TITLE_COLUMN + " = ?",
                new String[]{ String.valueOf(bookTitle) });
    }

    //return a cursor with all book records
    public  Cursor fetchAllBooksCursor() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " +  BOOK_TABLE + " ORDER BY " + DUE_COLUMN + " DESC";

        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }


}
