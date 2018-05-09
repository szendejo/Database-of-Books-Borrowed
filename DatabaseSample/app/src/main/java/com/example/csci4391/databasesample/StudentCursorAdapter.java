package com.example.csci4391.databasesample;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by csci4391 on 4/12/18.
 */

public class StudentCursorAdapter extends CursorAdapter {
    public StudentCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.row_student, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView textName = (TextView) view.findViewById(R.id.textName);
        TextView textMajor = (TextView) view.findViewById(R.id.textMajor);

        // Extract properties from cursor
        String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
        String major = cursor.getString(cursor.getColumnIndexOrThrow("Major"));

        // Populate fields with extracted properties
        textName.setText(name);
        textMajor.setText(major);
    }


}
