package com.google.android.apps.authenticator.auditlog;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.apps.authenticator.auditlog.AuditLogEvent;
import com.google.android.apps.authenticator.otp.AccountDb;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.joda.time.Instant;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AuditLogDb {
    private static final String ACCOUNT_FOREIGN_KEY_NAME = "account";
    private static final String ADD_ACCOUNT_TABLE_NAME = "auditlog_add_account";
    private static final String CURRENT_TIMER_COLUMN_NAME = "current_timer";
    private static final String EXPORT_TABLE_NAME = "auditlog_export";
    private static final String ID_COLUMN_NAME = "_id";
    private static final String IMPORT_TABLE_NAME = "auditlog_import";
    private static final String LAST_NOTIFIED_ID_COLUMN_NAME = "last_notified_id";
    private static final String MIGRATION_AMOUNT_COLUMN_NAME = "migration_amount";
    private static final String NEXT_ID_COLUMN_NAME = "next_id";
    private static final String TIMER_COLUMN_NAME = "timer";
    private static final String TIMER_TABLE_NAME = "auditlog_timer";
    private static final String TIMESTAMP_COLUMN_NAME = "timestamp";
    private final SQLiteDatabase database;

    public AuditLogDb(SQLiteDatabase sQLiteDatabase) {
        this.database = sQLiteDatabase;
        sQLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER NOT NULL PRIMARY KEY ON CONFLICT IGNORE CHECK (%s = 0),%s INTEGER NOT NULL,%s INTEGER NOT NULL,%s INTEGER NOT NULL)", TIMER_TABLE_NAME, "_id", "_id", CURRENT_TIMER_COLUMN_NAME, LAST_NOTIFIED_ID_COLUMN_NAME, NEXT_ID_COLUMN_NAME));
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", (Integer) 0);
        contentValues.put(CURRENT_TIMER_COLUMN_NAME, (Long) 1L);
        contentValues.put(LAST_NOTIFIED_ID_COLUMN_NAME, (Long) 0L);
        contentValues.put(NEXT_ID_COLUMN_NAME, (Long) 1L);
        sQLiteDatabase.insertOrThrow(TIMER_TABLE_NAME, null, contentValues);
        sQLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY,%s DATETIME NOT NULL,%s INTEGER NOT NULL,%s INTEGER NOT NULL)", EXPORT_TABLE_NAME, "_id", TIMESTAMP_COLUMN_NAME, TIMER_COLUMN_NAME, MIGRATION_AMOUNT_COLUMN_NAME));
        sQLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY,%s DATETIME NOT NULL,%s INTEGER NOT NULL,%s INTEGER NOT NULL)", IMPORT_TABLE_NAME, "_id", TIMESTAMP_COLUMN_NAME, TIMER_COLUMN_NAME, MIGRATION_AMOUNT_COLUMN_NAME));
        sQLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY,%s DATETIME NOT NULL,%s INTEGER NOT NULL,%s INTEGER NOT NULL UNIQUE ON CONFLICT REPLACE,FOREIGN KEY(%s) REFERENCES %s(%s) ON DELETE CASCADE ON UPDATE CASCADE)", ADD_ACCOUNT_TABLE_NAME, "_id", TIMESTAMP_COLUMN_NAME, TIMER_COLUMN_NAME, ACCOUNT_FOREIGN_KEY_NAME, ACCOUNT_FOREIGN_KEY_NAME, AccountDb.TABLE_NAME, "_id"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getAndIncrementNextId() {
        this.database.beginTransaction();
        try {
            Cursor rawQuery = this.database.rawQuery(String.format("SELECT %s FROM %s", NEXT_ID_COLUMN_NAME, TIMER_TABLE_NAME), new String[0]);
            rawQuery.moveToFirst();
            long j = rawQuery.getLong(rawQuery.getColumnIndexOrThrow(NEXT_ID_COLUMN_NAME));
            this.database.execSQL(String.format("UPDATE %s SET %s = %s + 1", TIMER_TABLE_NAME, NEXT_ID_COLUMN_NAME, NEXT_ID_COLUMN_NAME));
            this.database.setTransactionSuccessful();
            if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } finally {
            this.database.endTransaction();
        }
    }

    public void addEvent(AuditLogEvent auditLogEvent) {
        this.database.beginTransaction();
        try {
            auditLogEvent.accept(new AuditLogEvent.Visitor() { // from class: com.google.android.apps.authenticator.auditlog.AuditLogDb.1
                @Override // com.google.android.apps.authenticator.auditlog.AuditLogEvent.Visitor
                public Long visit(AddAccountEvent addAccountEvent) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_id", Long.valueOf(AuditLogDb.this.getAndIncrementNextId()));
                    contentValues.put(AuditLogDb.TIMESTAMP_COLUMN_NAME, Long.valueOf(addAccountEvent.timestamp().getMillis()));
                    contentValues.put(AuditLogDb.TIMER_COLUMN_NAME, Long.valueOf(AuditLogDb.this.getCurrentTimer()));
                    contentValues.put(AuditLogDb.ACCOUNT_FOREIGN_KEY_NAME, Integer.valueOf(AccountDb.getId(AuditLogDb.this.database, addAccountEvent.accountIndex())));
                    return Long.valueOf(AuditLogDb.this.database.insertOrThrow(AuditLogDb.ADD_ACCOUNT_TABLE_NAME, null, contentValues));
                }

                @Override // com.google.android.apps.authenticator.auditlog.AuditLogEvent.Visitor
                public Long visit(ExportEvent exportEvent) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_id", Long.valueOf(AuditLogDb.this.getAndIncrementNextId()));
                    contentValues.put(AuditLogDb.TIMESTAMP_COLUMN_NAME, Long.valueOf(exportEvent.timestamp().getMillis()));
                    contentValues.put(AuditLogDb.MIGRATION_AMOUNT_COLUMN_NAME, Integer.valueOf(exportEvent.amount()));
                    contentValues.put(AuditLogDb.TIMER_COLUMN_NAME, Long.valueOf(AuditLogDb.this.getCurrentTimer()));
                    return Long.valueOf(AuditLogDb.this.database.insertOrThrow(AuditLogDb.EXPORT_TABLE_NAME, null, contentValues));
                }

                @Override // com.google.android.apps.authenticator.auditlog.AuditLogEvent.Visitor
                public Long visit(ImportEvent importEvent) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_id", Long.valueOf(AuditLogDb.this.getAndIncrementNextId()));
                    contentValues.put(AuditLogDb.TIMESTAMP_COLUMN_NAME, Long.valueOf(importEvent.timestamp().getMillis()));
                    contentValues.put(AuditLogDb.MIGRATION_AMOUNT_COLUMN_NAME, Integer.valueOf(importEvent.amount()));
                    contentValues.put(AuditLogDb.TIMER_COLUMN_NAME, Long.valueOf(AuditLogDb.this.getCurrentTimer()));
                    return Long.valueOf(AuditLogDb.this.database.insertOrThrow(AuditLogDb.IMPORT_TABLE_NAME, null, contentValues));
                }
            });
            this.database.setTransactionSuccessful();
        } finally {
            this.database.endTransaction();
        }
    }

    public void clearPendingMigrationNotifications() {
        this.database.execSQL(String.format("UPDATE %s SET %s = %s - 1", TIMER_TABLE_NAME, LAST_NOTIFIED_ID_COLUMN_NAME, NEXT_ID_COLUMN_NAME));
    }

    public void deleteOutdatedEvents(long j) {
        this.database.beginTransaction();
        try {
            long currentTimer = getCurrentTimer() - (j / AuditLogConfig.TIMER_INCREMENT_INTERVAL_MS);
            this.database.execSQL(String.format("DELETE FROM %s WHERE %s <= CAST(? AS INTEGER)", EXPORT_TABLE_NAME, TIMER_COLUMN_NAME), new String[]{Long.toString(currentTimer)});
            this.database.execSQL(String.format("DELETE FROM %s WHERE %s <= CAST(? AS INTEGER)", IMPORT_TABLE_NAME, TIMER_COLUMN_NAME), new String[]{Long.toString(currentTimer)});
            this.database.execSQL(String.format("DELETE FROM %s WHERE %s <= CAST(? AS INTEGER)", ADD_ACCOUNT_TABLE_NAME, TIMER_COLUMN_NAME), new String[]{Long.toString(currentTimer)});
            this.database.setTransactionSuccessful();
        } finally {
            this.database.endTransaction();
        }
    }

    public List getAllEvents() {
        ArrayList arrayList = new ArrayList();
        this.database.beginTransaction();
        try {
            arrayList.addAll(getAllExportEvents());
            arrayList.addAll(getAllImportEvents());
            arrayList.addAll(getAllAddAccountEvents());
            this.database.setTransactionSuccessful();
            this.database.endTransaction();
            Collections.sort(arrayList, Collections.reverseOrder());
            return arrayList;
        } catch (Throwable th) {
            this.database.endTransaction();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getCurrentTimer() {
        Cursor rawQuery = this.database.rawQuery(String.format("SELECT %s FROM %s", CURRENT_TIMER_COLUMN_NAME, TIMER_TABLE_NAME), new String[0]);
        try {
            rawQuery.moveToFirst();
            long j = rawQuery.getLong(rawQuery.getColumnIndexOrThrow(CURRENT_TIMER_COLUMN_NAME));
            if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } catch (Throwable th) {
            if (rawQuery != null) {
                try {
                    rawQuery.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public void incrementTimer() {
        this.database.execSQL(String.format("UPDATE %s SET %s = %s + 1", TIMER_TABLE_NAME, CURRENT_TIMER_COLUMN_NAME, CURRENT_TIMER_COLUMN_NAME));
    }

    private List getAllAddAccountEvents() {
        Cursor rawQuery = this.database.rawQuery(String.format("SELECT %s,%s.%s AS %s,%s - %s AS %s,%s,%s FROM %s JOIN %s JOIN %s ON %s.%s = %s.%s", TIMESTAMP_COLUMN_NAME, ADD_ACCOUNT_TABLE_NAME, "_id", "_id", CURRENT_TIMER_COLUMN_NAME, TIMER_COLUMN_NAME, "age", AccountDb.NAME_COLUMN, AccountDb.ISSUER_COLUMN, TIMER_TABLE_NAME, ADD_ACCOUNT_TABLE_NAME, AccountDb.TABLE_NAME, AccountDb.TABLE_NAME, "_id", ADD_ACCOUNT_TABLE_NAME, ACCOUNT_FOREIGN_KEY_NAME), new String[0]);
        try {
            ArrayList arrayList = new ArrayList();
            int count = rawQuery.getCount();
            int columnIndexOrThrow = rawQuery.getColumnIndexOrThrow(TIMESTAMP_COLUMN_NAME);
            int columnIndexOrThrow2 = rawQuery.getColumnIndexOrThrow("age");
            int columnIndexOrThrow3 = rawQuery.getColumnIndexOrThrow(AccountDb.NAME_COLUMN);
            int columnIndexOrThrow4 = rawQuery.getColumnIndexOrThrow(AccountDb.ISSUER_COLUMN);
            int columnIndexOrThrow5 = rawQuery.getColumnIndexOrThrow("_id");
            for (int i = 0; i < count; i++) {
                rawQuery.moveToPosition(i);
                arrayList.add(AddAccountEvent.builder().setTimestamp(new Instant(rawQuery.getLong(columnIndexOrThrow))).setAge(rawQuery.getLong(columnIndexOrThrow2)).setAccountIndex(new AccountDb.AccountIndex(rawQuery.getString(columnIndexOrThrow3), rawQuery.getString(columnIndexOrThrow4))).setId(rawQuery.getLong(columnIndexOrThrow5)).build());
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (rawQuery != null) {
                try {
                    rawQuery.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    private List getAllExportEvents() {
        Cursor rawQuery = this.database.rawQuery(String.format("SELECT %s,%s,%s.%s AS %s,%s - %s AS %s FROM %s JOIN %s", TIMESTAMP_COLUMN_NAME, MIGRATION_AMOUNT_COLUMN_NAME, EXPORT_TABLE_NAME, "_id", "_id", CURRENT_TIMER_COLUMN_NAME, TIMER_COLUMN_NAME, "age", TIMER_TABLE_NAME, EXPORT_TABLE_NAME), new String[0]);
        try {
            ArrayList arrayList = new ArrayList();
            int count = rawQuery.getCount();
            int columnIndexOrThrow = rawQuery.getColumnIndexOrThrow(TIMESTAMP_COLUMN_NAME);
            int columnIndexOrThrow2 = rawQuery.getColumnIndexOrThrow(MIGRATION_AMOUNT_COLUMN_NAME);
            int columnIndexOrThrow3 = rawQuery.getColumnIndexOrThrow("age");
            int columnIndexOrThrow4 = rawQuery.getColumnIndexOrThrow("_id");
            for (int i = 0; i < count; i++) {
                rawQuery.moveToPosition(i);
                arrayList.add(ExportEvent.builder().setTimestamp(new Instant(rawQuery.getLong(columnIndexOrThrow))).setAmount(rawQuery.getInt(columnIndexOrThrow2)).setAge(rawQuery.getLong(columnIndexOrThrow3)).setId(rawQuery.getLong(columnIndexOrThrow4)).build());
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (rawQuery != null) {
                try {
                    rawQuery.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    private List getAllImportEvents() {
        Cursor rawQuery = this.database.rawQuery(String.format("SELECT %s,%s,%s.%s AS %s,%s - %s AS %s FROM %s JOIN %s", TIMESTAMP_COLUMN_NAME, MIGRATION_AMOUNT_COLUMN_NAME, IMPORT_TABLE_NAME, "_id", "_id", CURRENT_TIMER_COLUMN_NAME, TIMER_COLUMN_NAME, "age", TIMER_TABLE_NAME, IMPORT_TABLE_NAME), new String[0]);
        try {
            ArrayList arrayList = new ArrayList();
            int count = rawQuery.getCount();
            int columnIndexOrThrow = rawQuery.getColumnIndexOrThrow(TIMESTAMP_COLUMN_NAME);
            int columnIndexOrThrow2 = rawQuery.getColumnIndexOrThrow(MIGRATION_AMOUNT_COLUMN_NAME);
            int columnIndexOrThrow3 = rawQuery.getColumnIndexOrThrow("age");
            int columnIndexOrThrow4 = rawQuery.getColumnIndexOrThrow("_id");
            for (int i = 0; i < count; i++) {
                rawQuery.moveToPosition(i);
                arrayList.add(ImportEvent.builder().setTimestamp(new Instant(rawQuery.getLong(columnIndexOrThrow))).setAmount(rawQuery.getInt(columnIndexOrThrow2)).setAge(rawQuery.getLong(columnIndexOrThrow3)).setId(rawQuery.getLong(columnIndexOrThrow4)).build());
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (rawQuery != null) {
                try {
                    rawQuery.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public boolean hasPendingExportNotifications() {
        boolean z = false;
        Cursor rawQuery = this.database.rawQuery(String.format("SELECT count(*) AS %s FROM %s JOIN %s WHERE %s.%s > %s", "rowCount", EXPORT_TABLE_NAME, TIMER_TABLE_NAME, EXPORT_TABLE_NAME, "_id", LAST_NOTIFIED_ID_COLUMN_NAME), new String[0]);
        try {
            if (rawQuery.moveToFirst()) {
                if (rawQuery.getLong(rawQuery.getColumnIndexOrThrow("rowCount")) > 0) {
                    z = true;
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return z;
        } catch (Throwable th) {
            if (rawQuery != null) {
                try {
                    rawQuery.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public boolean hasPendingImportNotifications() {
        boolean z = false;
        Cursor rawQuery = this.database.rawQuery(String.format("SELECT count(*) AS %s FROM %s JOIN %s WHERE %s.%s > %s", "rowCount", IMPORT_TABLE_NAME, TIMER_TABLE_NAME, IMPORT_TABLE_NAME, "_id", LAST_NOTIFIED_ID_COLUMN_NAME), new String[0]);
        try {
            if (rawQuery.moveToFirst()) {
                if (rawQuery.getLong(rawQuery.getColumnIndexOrThrow("rowCount")) > 0) {
                    z = true;
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return z;
        } catch (Throwable th) {
            if (rawQuery != null) {
                try {
                    rawQuery.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public boolean hasRecentExportEvents(long j) {
        boolean z = false;
        Cursor rawQuery = this.database.rawQuery(String.format("SELECT count(*) AS %s FROM %s JOIN %s WHERE %s - %s < CAST(? AS INTEGER)", "rowCount", EXPORT_TABLE_NAME, TIMER_TABLE_NAME, CURRENT_TIMER_COLUMN_NAME, TIMER_COLUMN_NAME), new String[]{Long.toString(j / AuditLogConfig.TIMER_INCREMENT_INTERVAL_MS)});
        try {
            if (rawQuery.moveToFirst()) {
                if (rawQuery.getLong(rawQuery.getColumnIndexOrThrow("rowCount")) > 0) {
                    z = true;
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return z;
        } catch (Throwable th) {
            if (rawQuery != null) {
                try {
                    rawQuery.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public boolean hasRecentImportEvents(long j) {
        boolean z = false;
        Cursor rawQuery = this.database.rawQuery(String.format("SELECT count(*) AS %s FROM %s JOIN %s WHERE %s - %s < CAST(? AS INTEGER)", "rowCount", IMPORT_TABLE_NAME, TIMER_TABLE_NAME, CURRENT_TIMER_COLUMN_NAME, TIMER_COLUMN_NAME), new String[]{Long.toString(j / AuditLogConfig.TIMER_INCREMENT_INTERVAL_MS)});
        try {
            if (rawQuery.moveToFirst()) {
                if (rawQuery.getLong(rawQuery.getColumnIndexOrThrow("rowCount")) > 0) {
                    z = true;
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return z;
        } catch (Throwable th) {
            if (rawQuery != null) {
                try {
                    rawQuery.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }
}
