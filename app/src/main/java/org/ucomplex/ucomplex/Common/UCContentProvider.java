package org.ucomplex.ucomplex.Common;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;

import static org.ucomplex.ucomplex.Common.UCDBOpenHelper.KEY_EVENTS_TIME;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 15/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UCContentProvider extends ContentProvider {

    public static final Uri CONTENT_URI = Uri.parse("content://org.ucomplex.eventsprovider/events");
    private static final int ALLROWS = 1;
    private static final int SINGLE_ROW = 2;
    private static final UriMatcher uriMatcher;
    private static HashMap<String, String> EVENTS_PROJECTION_MAP;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("org.ucomplex.eventsprovider", "events", ALLROWS);
        uriMatcher.addURI("org.ucomplex.eventsprovider", "events/#", SINGLE_ROW);
    }

    UCDBOpenHelper mSqlOpenHelper;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        mSqlOpenHelper = new UCDBOpenHelper(getContext(), UCDBOpenHelper.DATABASE_NAME, null, UCDBOpenHelper.DATABASE_VERSION);
        db = mSqlOpenHelper.getWritableDatabase();
        return db != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String groupBy = null;
        String having = null;
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(UCDBOpenHelper.TABLE_EVENTS);
        switch (uriMatcher.match(uri)) {
            case SINGLE_ROW:
                String rowID = uri.getPathSegments().get(1);
                queryBuilder.appendWhere(KEY_EVENTS_TIME + "=" + rowID);
                break;
            case ALLROWS:
                queryBuilder.setProjectionMap(EVENTS_PROJECTION_MAP);
                break;
            default:
                break;
        }
        Cursor c =  queryBuilder.query(db, projection, selection, selectionArgs, groupBy, having, sortOrder);
        if (getContext() != null) {
            c.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case ALLROWS:
                return "vnd.android.cursor.dir/org.ucomplex.eventsprovider";
            /**
             * Get a particular student
             */
            case SINGLE_ROW:
                return "vnd.android.cursor.item/org.ucomplex.eventsprovider";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        long rowID = db.insert(UCDBOpenHelper.TABLE_EVENTS, "", values);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(_uri, null);
            }
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count;
        switch (uriMatcher.match(uri)){
            case ALLROWS:
                count = db.delete(UCDBOpenHelper.TABLE_EVENTS, selection, selectionArgs);
                break;
            case SINGLE_ROW:
                String id = uri.getPathSegments().get(1);
                count = db.delete( UCDBOpenHelper.TABLE_EVENTS, KEY_EVENTS_TIME +  " = " + id +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count;
        switch (uriMatcher.match(uri)) {
            case ALLROWS:
                count = db.update(UCDBOpenHelper.TABLE_EVENTS, values, selection, selectionArgs);
                break;
            case SINGLE_ROW:
                count = db.update(UCDBOpenHelper.TABLE_EVENTS, values,
                        KEY_EVENTS_TIME + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" +selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }
        if (getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }
}
