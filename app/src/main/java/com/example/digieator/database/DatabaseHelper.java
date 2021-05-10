package com.example.digieator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.digieator.database.model.Answer;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "answers_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Answer.CREATE_TABLE);
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Answer.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    //methods required to store or retrieve the answers
    public long insertAnswer(String note) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.

        values.put(Answer.COLUMN_ANSWER, note);

        // insert row
        long id = db.insert(Answer.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public Answer getAnswer(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Answer.TABLE_NAME,
                new String[]{Answer.COLUMN_ID, Answer.COLUMN_ANSWER, Answer.COLUMN_TIMESTAMP},
                Answer.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Answer answer = new Answer(
                cursor.getInt(cursor.getColumnIndex(Answer.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Answer.COLUMN_ANSWER)),
                cursor.getString(cursor.getColumnIndex(Answer.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return answer;
    }


    public List<Answer> getAllAnswers() {
        List<Answer> answers = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Answer.TABLE_NAME + " ORDER BY " +
                Answer.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Answer answer = new Answer();
                answer.setId(cursor.getInt(cursor.getColumnIndex(Answer.COLUMN_ID)));
                answer.setAnswer(cursor.getString(cursor.getColumnIndex(Answer.COLUMN_ANSWER)));
                answer.setTimestamp(cursor.getString(cursor.getColumnIndex(Answer.COLUMN_TIMESTAMP)));

                answers.add(answer);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return answers;
    }


    public int getAnswersCount() {
        String countQuery = "SELECT  * FROM " + Answer.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }




    public void deleteAnswer(Answer answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Answer.TABLE_NAME, Answer.COLUMN_ID + " = ?",
                new String[]{String.valueOf(answer.getId())});
        db.close();
    }
}// end of DatabaseHelper class
