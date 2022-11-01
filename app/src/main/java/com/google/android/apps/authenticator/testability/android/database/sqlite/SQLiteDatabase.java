package com.google.android.apps.authenticator.testability.android.database.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SQLiteDatabase {
    private final android.database.sqlite.SQLiteDatabase delegate;

    public SQLiteDatabase(android.database.sqlite.SQLiteDatabase sQLiteDatabase) {
        this.delegate = sQLiteDatabase;
    }

    public void close() {
        this.delegate.close();
    }

    public void execSQL(String str) {
        this.delegate.execSQL(str);
    }

    public long insert(String str, String str2, ContentValues contentValues) {
        return this.delegate.insert(str, str2, contentValues);
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        return this.delegate.query(str, strArr, str2, strArr2, str3, str4, str5);
    }

    public Cursor rawQuery(String str, String[] strArr) {
        return this.delegate.rawQuery(str, strArr);
    }

    public android.database.sqlite.SQLiteDatabase unwrap() {
        return this.delegate;
    }

    public int update(String str, ContentValues contentValues, String str2, String[] strArr) {
        return this.delegate.update(str, contentValues, str2, strArr);
    }
}
