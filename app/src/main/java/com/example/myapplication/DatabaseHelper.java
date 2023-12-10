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
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "calculateroof";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_LENGTH = "length";
    public static final String COLUMN_WIDTH = "width";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_SQUARE = "square";
    public static final String COLUMN_D = "d";
    public static final String COLUMN_C = "c";
    public static final String COLUMN_S = "s";

    public List<Project> getAllProjects() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        List<Project> projects = new ArrayList<>();

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
                        int typeIndex = cursor.getColumnIndex(COLUMN_TYPE);
                        int lengthIndex = cursor.getColumnIndex(COLUMN_LENGTH);
                        int widthIndex = cursor.getColumnIndex(COLUMN_WIDTH);
                        int heightIndex = cursor.getColumnIndex(COLUMN_HEIGHT);
                        int squareIndex = cursor.getColumnIndex(COLUMN_SQUARE);
                        int dIndex = cursor.getColumnIndex(COLUMN_D);
                        int cIndex = cursor.getColumnIndex(COLUMN_C);
                        int sIndex = cursor.getColumnIndex(COLUMN_S);

                        if (nameIndex >= 0 && typeIndex >= 0 && lengthIndex >= 0 &&
                                widthIndex >= 0 && heightIndex >= 0 && squareIndex >= 0 &&
                                dIndex >= 0 && cIndex >= 0 && sIndex >= 0) {

                            String name = cursor.getString(nameIndex);
                            String type = cursor.getString(typeIndex);
                            double length = cursor.getDouble(lengthIndex);
                            double width = cursor.getDouble(widthIndex);
                            double height = cursor.getDouble(heightIndex);
                            double square = cursor.getDouble(squareIndex);
                            double d = cursor.getDouble(dIndex);
                            double c = cursor.getDouble(cIndex);
                            double s = cursor.getDouble(sIndex);

                            projects.add(new Project(name, type, length, width, height, square, d, c, s));
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
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_LENGTH + " REAL, " +
                COLUMN_WIDTH + " REAL, " +
                COLUMN_HEIGHT + " REAL, " +
                COLUMN_SQUARE + " REAL, " +
                COLUMN_D + " REAL, " +
                COLUMN_C + " REAL, " +
                COLUMN_S + " REAL);";

        db.execSQL(createTableQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            String updateQuery = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_TYPE + " TEXT;";
            db.execSQL(updateQuery);

            updateQuery = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_HEIGHT + " REAL;";
            db.execSQL(updateQuery);

            updateQuery = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_D + " REAL;";
            db.execSQL(updateQuery);

            updateQuery = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_C + " REAL;";
            db.execSQL(updateQuery);

            updateQuery = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_S + " REAL;";
            db.execSQL(updateQuery);
        }
    }



    public long insertData(String name, String type, double length, double width, double height,
                           double square, double d, double c, double s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_LENGTH, length);
        values.put(COLUMN_WIDTH, width);
        values.put(COLUMN_HEIGHT, height);
        values.put(COLUMN_SQUARE, square);
        values.put(COLUMN_D, d);
        values.put(COLUMN_C, c);
        values.put(COLUMN_S, s);

        long newRowId = db.insert(TABLE_NAME, null, values);
        return newRowId;
    }



    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public int deleteData(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        int deletedRows = db.delete(TABLE_NAME, selection, selectionArgs);
        db.close();
        return deletedRows;
    }

    public int deleteLastData() {
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT MAX(" + COLUMN_ID + ") FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        long lastEntryId = -1;
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    lastEntryId = cursor.getLong(0);
                }
            } finally {
                cursor.close();
            }
        }
        if (lastEntryId != -1) {
            String selection = COLUMN_ID + "=?";
            String[] selectionArgs = {String.valueOf(lastEntryId)};
            return db.delete(TABLE_NAME, selection, selectionArgs);
        }

        return 0;
    }

    public int deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, null, null);
    }
}
