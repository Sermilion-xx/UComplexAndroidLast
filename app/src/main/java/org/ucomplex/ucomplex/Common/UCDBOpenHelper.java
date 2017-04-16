package org.ucomplex.ucomplex.Common;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 14/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UCDBOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "org.ucomplex.ucomplex_android.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_EVENTS = "events";

    public static final String KEY_EVENTS_PK_ID = "_id";
    public static final String KEY_EVENTS_ID = "id";
    public static final String KEY_EVENTS_TEXT = "event_text";
    public static final String KEY_EVENTS_PARAMS = "params";
    public static final String KEY_EVENTS_TYPE = "type";
    public static final String KEY_EVENTS_TIME = "time";
    public static final String KEY_EVENTS_SEEN = "seen";

    public static final String TABLE_EVENT_PARAMS = "event_params";
    public static final String KEY_EVENT_PARAMS_PK_ID = "_id";
    public static final String KEY_EVENT_PARAMS_ID = "id";
    public static final String KEY_EVENT_PARAMS_MESSAGE = "messsage";
    public static final String KEY_EVENT_PARAMS_NAME = "name";
    public static final String KEY_EVENT_PARAMS_PHOTO = "photo";
    public static final String KEY_EVENT_PARAMS_CODE = "code";
    public static final String KEY_EVENT_PARAMS_GCOURSE = "gcourse";
    public static final String KEY_EVENT_PARAMS_COURSE_NAME = "course_name";
    public static final String KEY_EVENT_PARAMS_HOUT_TYPE = "hour_type";
    public static final String KEY_EVENT_PARAMS_TYPE = "type";
    public static final String KEY_EVENT_PARAMS_SEMESTER = "semester";
    public static final String KEY_EVENT_PARAMS_YEAR = "year";
    public static final String KEY_EVENT_PARAMS_EVENT_NAME = "event_name";
    public static final String KEY_EVENT_PARAMS_DATE = "date";
    public static final String KEY_EVENT_PARAMS_AUTHOR = "author";


    public static final String DB_CREATE_EVENTS = "create table " +
            TABLE_EVENTS + " (" + KEY_EVENTS_PK_ID + " integer PRIMARY KEY autoincrement, " +
            KEY_EVENTS_ID + " integer, " +
            KEY_EVENTS_TEXT + " text not null, " +
            KEY_EVENTS_PARAMS + " integer, " +
            KEY_EVENTS_TIME + " text not null, " +
            KEY_EVENTS_SEEN + " integer, " +
            KEY_EVENTS_TYPE + " integer," +
            "FOREIGN KEY (" + KEY_EVENTS_PARAMS + ") REFERENCES " + TABLE_EVENT_PARAMS + "(" + KEY_EVENT_PARAMS_PK_ID + "));";

    public static final String DB_CREATE_EVENT_PARAMS = "create table " +
            TABLE_EVENT_PARAMS + " (" + KEY_EVENT_PARAMS_PK_ID + " integer PRIMARY KEY autoincrement, " +
            KEY_EVENT_PARAMS_ID + " integer, " +
            KEY_EVENT_PARAMS_MESSAGE + " text, " +
            KEY_EVENT_PARAMS_NAME + " text, " +
            KEY_EVENT_PARAMS_PHOTO + " text, " +
            KEY_EVENT_PARAMS_CODE + " text, " +
            KEY_EVENT_PARAMS_GCOURSE + " text, " +
            KEY_EVENT_PARAMS_COURSE_NAME + " text, " +
            KEY_EVENT_PARAMS_HOUT_TYPE + " text, " +
            KEY_EVENT_PARAMS_TYPE + " text, " +
            KEY_EVENT_PARAMS_SEMESTER + " text, " +
            KEY_EVENT_PARAMS_YEAR + " text, " +
            KEY_EVENT_PARAMS_EVENT_NAME + " text, " +
            KEY_EVENT_PARAMS_DATE + " text, " +
            KEY_EVENT_PARAMS_AUTHOR + " text);";

    private static SQLiteDatabase sqliteDb;
    private static UCDBOpenHelper INSTANCE;

    public UCDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public UCDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteDatabase getConnection(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UCDBOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
            try {
                sqliteDb = INSTANCE.getWritableDatabase();
            } catch (SQLiteException ex) {
                sqliteDb = INSTANCE.getReadableDatabase();
            }
        }
        return sqliteDb;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(DB_CREATE_EVENT_PARAMS);
            db.execSQL(DB_CREATE_EVENTS);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT_PARAMS);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        onCreate(db);
    }
}
