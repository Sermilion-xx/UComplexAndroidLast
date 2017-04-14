package org.ucomplex.ucomplex.Common;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.ucomplex.ucomplex.Modules.Events.model.EventItem;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 14/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class EventsDBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "org.ucomplex.ucomplex_android.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_EVENTS = "events";
    public static final String KEY_EVENTS_PK_ID = "_id";
    public static final String KEY_EVENTS_ID = "id";
    public static final String KEY_EVENTS_TEXT = "event_text";
    public static final String KEY_EVENTS_PARAMS = "params";
    public static final String KEY_EVENTS_TYPE = "type";
    public static final String KEY_EVENTS_TIME = "time";
    public static final String KEY_EVENTS_SEEN = "seen";


    private static final String TABLE_EVENT_PARAMS = "event_params";
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
    public static final String KEY_EVENT_PARAMS_EVENT_NAME = "name";
    public static final String KEY_EVENT_PARAMS_DATE = "date";
    public static final String KEY_EVENT_PARAMS_AUTHOR = "author";


    private static final String DB_CREATE_EVENTS = "create table " +
            TABLE_EVENTS + " (" + KEY_EVENTS_PK_ID + " integer PRIMARY KEY autoincrement, " +
            KEY_EVENTS_ID + " integer, " +
            KEY_EVENTS_TEXT + " text not null, " +
            KEY_EVENTS_PARAMS + " integer FOREIGN KEY (" + KEY_EVENTS_PARAMS + ") REFERENCES " + TABLE_EVENT_PARAMS + "." + KEY_EVENT_PARAMS_ID + ", " +
            KEY_EVENTS_TIME + " text not null, " +
            KEY_EVENTS_SEEN + " integer, " +
            KEY_EVENTS_TYPE + " integer);";

    private static final String DB_CREATE_EVENT_PARAMS = "create table " +
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

    public EventsDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
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
            db.execSQL("DROP TABLE IF IT EXISTS " + TABLE_EVENTS);
            db.execSQL("DROP TABLE IF IT EXISTS " + TABLE_EVENT_PARAMS);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        onCreate(db);
    }

    public void insertEvents (SQLiteDatabase db, EventItem item) {
        String sql = "INSERT or replace INTO " + TABLE_EVENTS + " VALUES('" + item.getId() + "', " +
                "'" + item.getEventText() + "', " +
                "'" + item.getParamsObj().getId() + "', " +
                "'" + item.getType() + "', " +
                "'" + item.getTime() + "', " +
                "'" + item.getSeen() + "')";
        db.execSQL(sql);
    }

    public void insertEventParam(SQLiteDatabase db, EventItem.EventParams params) {
        String sql = "INSERT or replace INTO " + TABLE_EVENT_PARAMS + " VALUES('" + params.getId() + "', " +
                "'" + params.getMessage() + "', " +
                "'" + params.getName() + "', " +
                "'" + params.getPhoto() + "', " +
                "'" + params.getCode() + "', " +
                "'" + params.getGcourse() + "', " +
                "'" + params.getCourseName() + "', " +
                "'" + params.getHourType() + "', " +
                "'" + params.getType() + "', " +
                "'" + params.getSemester() + "', " +
                "'" + params.getYear() + "', " +
                "'" + params.getEventName() + "', " +
                "'" + params.getDate() + "', " +
                "'" + params.getAuthor() + "')";
        db.execSQL(sql);
    }

    public List<EventItem> getEvents(SQLiteDatabase db) {
        final String EVENTS_QUERY = "SELECT * FROM " + TABLE_EVENTS + " e INNER JOIN " + TABLE_EVENT_PARAMS + " p ON e."+ KEY_EVENTS_PARAMS + " = p." + KEY_EVENT_PARAMS_ID + "";
        Cursor cursor = db.rawQuery(EVENTS_QUERY, null);

        int KEY_EVENTS_ID_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENTS_ID);
        int KEY_EVENTS_TEXT_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENTS_TEXT);
        int KEY_EVENTS_PARAMS_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENTS_PARAMS);
        int KEY_EVENTS_TYPE_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENTS_TYPE);
        int KEY_EVENTS_TIME_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENTS_TIME);
        int KEY_EVENTS_SEEN_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENTS_SEEN);

        int KEY_EVENT_PARAMS_ID_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENT_PARAMS_ID);
        int KEY_EVENT_PARAMS_MESSAGE_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENT_PARAMS_MESSAGE);
        int KEY_EVENT_PARAMS_NAME_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENT_PARAMS_NAME);
        int KEY_EVENT_PARAMS_PHOTO_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENT_PARAMS_PHOTO);
        int KEY_EVENT_PARAMS_CODE_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENT_PARAMS_CODE);
        int KEY_EVENT_PARAMS_GCOURSE_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENT_PARAMS_GCOURSE);
        int KEY_EVENT_PARAMS_COURSE_NAME_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENT_PARAMS_COURSE_NAME);
        int KEY_EVENT_PARAMS_HOUT_TYPE_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENT_PARAMS_HOUT_TYPE);
        int KEY_EVENT_PARAMS_TYPE_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENT_PARAMS_TYPE);
        int KEY_EVENT_PARAMS_SEMESTER_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENT_PARAMS_SEMESTER);
        int KEY_EVENT_PARAMS_YEAR_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENT_PARAMS_YEAR);
        int KEY_EVENT_PARAMS_EVENT_NAME_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENT_PARAMS_EVENT_NAME);
        int KEY_EVENT_PARAMS_DATE_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENT_PARAMS_DATE);
        int KEY_EVENT_PARAMS_AUTHOR_INDEX = cursor.getColumnIndexOrThrow(KEY_EVENT_PARAMS_AUTHOR);

        List<EventItem> items = new ArrayList<>();
        while (cursor.moveToNext()) {
            EventItem item = new EventItem();
            item.setId(cursor.getInt(KEY_EVENTS_ID_INDEX));
            item.setEventText(cursor.getString(KEY_EVENTS_TEXT_INDEX));
            item.setSeen(cursor.getInt(KEY_EVENTS_SEEN_INDEX));
            item.setTime(cursor.getString(KEY_EVENTS_TIME_INDEX));
            item.setType(cursor.getInt(KEY_EVENTS_TYPE_INDEX));
            item.getParamsObj().setId(cursor.getInt(KEY_EVENT_PARAMS_ID_INDEX));
            item.getParamsObj().setMessage(cursor.getString(KEY_EVENT_PARAMS_MESSAGE_INDEX));
            item.getParamsObj().setName(cursor.getString(KEY_EVENT_PARAMS_NAME_INDEX));
            item.getParamsObj().setPhoto(cursor.getInt(KEY_EVENT_PARAMS_PHOTO_INDEX));
            item.getParamsObj().setCode(cursor.getString(KEY_EVENT_PARAMS_CODE_INDEX));
            item.getParamsObj().setGcourse(cursor.getInt(KEY_EVENT_PARAMS_GCOURSE_INDEX));
            item.getParamsObj().setCourseName(cursor.getString(KEY_EVENT_PARAMS_COURSE_NAME_INDEX));
            item.getParamsObj().setHourType(cursor.getInt(KEY_EVENT_PARAMS_HOUT_TYPE_INDEX));
            item.getParamsObj().setType(cursor.getInt(KEY_EVENT_PARAMS_TYPE_INDEX));
            item.getParamsObj().setSemester(cursor.getString(KEY_EVENT_PARAMS_SEMESTER_INDEX));
            item.getParamsObj().setYear(cursor.getString(KEY_EVENT_PARAMS_YEAR_INDEX));
            item.getParamsObj().setEventName(cursor.getString(KEY_EVENT_PARAMS_EVENT_NAME_INDEX));
            item.getParamsObj().setDate(cursor.getString(KEY_EVENT_PARAMS_DATE_INDEX));
            item.getParamsObj().setAuthor(cursor.getString(KEY_EVENT_PARAMS_AUTHOR_INDEX));
            items.add(item);
        }
        cursor.close();
        return items;
    }
}
