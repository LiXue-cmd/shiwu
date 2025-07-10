package com.example.shiwu.Sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MydatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 3; // 增加版本号

    // 单例模式
    private static MydatabaseHelper instance;

    private MydatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized MydatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new MydatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建student表
        String CREATE_STUDENT_TABLE = "CREATE TABLE student ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT, "
                + "ranking INTEGER"
                + ")";
        db.execSQL(CREATE_STUDENT_TABLE);

        // 创建manager表
        String CREATE_MANAGER_TABLE = "CREATE TABLE manager ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT UNIQUE, "
                + "password TEXT"
                + ")";
        db.execSQL(CREATE_MANAGER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 简单处理：删除所有表，重新创建
        db.execSQL("DROP TABLE IF EXISTS student");
        db.execSQL("DROP TABLE IF EXISTS manager");
        onCreate(db);
    }
}