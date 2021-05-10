package com.example.digieator.database.model;


public class Answer {
    public static final String TABLE_NAME = "answers";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ANSWER = "answer";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String answer;
    private String timestamp;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_ANSWER + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public Answer() {
    }

    public Answer(int id, String answer, String timestamp) {
        this.id = id;
        this.answer = answer;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}//end of Answer class
