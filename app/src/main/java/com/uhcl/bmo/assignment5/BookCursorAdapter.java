package com.uhcl.bmo.assignment5;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by BMo on 4/17/2018.
 */

public class BookCursorAdapter extends CursorAdapter {
    public BookCursorAdapter(Context context, Cursor c, int flags){
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.row_book, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView textTitle = (TextView) view.findViewById(R.id.textTitle);
        TextView textDuedate = (TextView) view.findViewById(R.id.textDuedate);

        // Extract properties from cursor
        String title = cursor.getString(cursor.getColumnIndexOrThrow("Title"));
        String due = cursor.getString(cursor.getColumnIndexOrThrow("Due"));

        // Populate fields with extracted properties
        textTitle.setText(title);
        textDuedate.setText(due);
    }

}
