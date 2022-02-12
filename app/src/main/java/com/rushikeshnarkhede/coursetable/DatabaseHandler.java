package com.rushikeshnarkhede.coursetable;

import static android.os.Build.ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.Key;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Course";
    private static final String TABLE_NAME = "course";
    SQLiteDatabase db = null;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COURSE_TABLE = "CREATE TABLE IF NOT EXISTS course " + "(ID INTEGER PRIMARY KEY,NAME TEXT,DURATION TEXT,DESCRIPTION TEXT)";
        db.execSQL(CREATE_COURSE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS course");
// Create tables again
        onCreate(db);
    }

    public void addCourse(Course course) {
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("ID", course.get_id()); //course id
            values.put("NAME", course.get_name()); // Course Name
            values.put("DURATION", course.get_duration()); // course duration
            values.put("DESCRIPTION", course.get_description()); //course description
// insert(String table, String nullColumnHack, ContentValues values)
            db.insert("course", null, values);
// Inserting Contacts
            Log.d("Insert: ", "Record Added ..");
// Closing database connection
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCourse(Course course) {

        SQLiteDatabase db = this.getWritableDatabase();
        int course_id = course.get_id();
        ContentValues cVals = new ContentValues();
        cVals.put("ID", course.get_id());
        cVals.put("NAME", course.get_name());
        cVals.put("DURATION", course.get_duration());
        cVals.put("DESCRIPTION", course.get_description());
        int count = db.update(TABLE_NAME,
                cVals,
                "ID = ?",
                new String[]{String.valueOf(course_id)});
    }

    public void deleteCourse(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID = ?", new String[]{String.valueOf(userid)});
        db.close();
    }

    // code to get all courses in a list view
    public ArrayList<Course> getAllCourse() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Course> courseList = new ArrayList<Course>();
// Select All Query
        String selectQuery = "SELECT * FROM course";
// rawQuery(String sql, String[] selectionArgs)
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
// looping through all rows and adding to list if (cursor.moveToFirst()) {
        do {
            Course course = new Course();
            course.set_id(Integer.parseInt(cursor.getString(0)));
            course.set_name(cursor.getString(1));
            course.set_duration(cursor.getString(2));
            course.set_description(cursor.getString(3));

// Adding contact to list
            courseList.add(course);
        } while (cursor.moveToNext());
// return contact list
        return courseList;
    }

    // Get User Details based on userid
    public ArrayList<Course> GetUserByUserId(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Course> courseList = new ArrayList<Course>();
        Cursor cursor = db.query
                (TABLE_NAME, new String[]{"id", "name", "duration", "description"},
                        "id = ?", new String[]{String.valueOf(userid)},
                        null, null, null, null);
        if (cursor.moveToNext()) {
            Course course = new Course();
            course.set_id(Integer.parseInt(cursor.getString(0)));
            course.set_name(cursor.getString(1));
            course.set_duration(cursor.getString(2));
            course.set_description(cursor.getString(3));
            // Adding contact to list
            courseList.add(course);
        }
        while (cursor.moveToNext()) ;
// return contact list
        return courseList;
    }
}
