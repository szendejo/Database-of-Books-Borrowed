package com.example.csci4391.databasesample;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by csci4391 on 4/3/18.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "University";
    private static final int DB_VER = 1;
    private static final String STUDENT_TABLE = "Student";
    private static final String NAME_COLUMN = "Name";
    private static final String MAJOR_COLUMN = "Major";

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + STUDENT_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COLUMN + " TEXT," +
                MAJOR_COLUMN + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);

        // create new tables
        onCreate(db);
    }

    public void insertStudent(Student stu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN, stu.getName());
        values.put(MAJOR_COLUMN, stu.getMajor());

        db.insert(STUDENT_TABLE, null,values);
    }

    public  Cursor fetchAllStudentsCursor() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + STUDENT_TABLE;

        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }


    public List<Student> fetchAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Student> studentsList = new ArrayList<Student>();
        String selectQuery = "SELECT  * FROM " + STUDENT_TABLE;

        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Student stu = new Student();
                stu.setName(c.getString(c.getColumnIndex(NAME_COLUMN)));
                stu.setMajor(c.getString(c.getColumnIndex(MAJOR_COLUMN)));

                // adding to the list
                studentsList.add(stu);
            } while (c.moveToNext());
        }
        return studentsList;
    }

    public void updateStudent(Student stu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN, stu.getName());
        values.put(MAJOR_COLUMN, stu.getMajor());

        db.update(STUDENT_TABLE, values, NAME_COLUMN + " = ?",
                new String[]{ String.valueOf(stu.getName()) });
    }

    public void deleteStudent(String stuName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STUDENT_TABLE, NAME_COLUMN + " = ?",
                new String[]{ String.valueOf(stuName) });
    }







}
