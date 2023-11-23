package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Roof.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "calculateroof";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_WIDTH = "width";
    public static final String COLUMN_LENGTH = "length";
    public static final String COLUMN_SQUARE = "square";

    public List<Project> getAllProjects() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        List<Project> projects = new ArrayList<>();

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
                        int widthIndex = cursor.getColumnIndex(COLUMN_WIDTH);
                        int lengthIndex = cursor.getColumnIndex(COLUMN_LENGTH);
                        int squareIndex = cursor.getColumnIndex(COLUMN_SQUARE);

                        if (nameIndex >= 0 && widthIndex >= 0 && lengthIndex >= 0 && squareIndex >= 0) {
                            String name = cursor.getString(nameIndex);
                            double width = cursor.getDouble(widthIndex);
                            double length = cursor.getDouble(lengthIndex);
                            double square = cursor.getDouble(squareIndex);

                            projects.add(new Project(name, width, length, square));
                        }
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        }
        return projects;
    }


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_WIDTH + " REAL, " +
                COLUMN_LENGTH + " REAL, " +
                COLUMN_SQUARE + " REAL);";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Вы можете добавить логику обновления, если это необходимо
    }

    public long insertData(String name, double width, double length) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_WIDTH, width);
        values.put(COLUMN_LENGTH, length);
        values.put(COLUMN_SQUARE, width * length);

        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public int deleteData(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "id=?";
        String[] selectionArgs = {String.valueOf(id)};
        int deletedRows = db.delete(TABLE_NAME, selection, selectionArgs);
        db.close();
        return deletedRows;
    }
}

