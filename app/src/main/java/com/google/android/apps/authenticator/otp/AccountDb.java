package com.google.android.apps.authenticator.otp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.apps.authenticator.auditlog.AddAccountEvent;
import com.google.android.apps.authenticator.auditlog.AuditLogDb;
import com.google.android.apps.authenticator.auditlog.ImportEvent;
import com.google.android.apps.authenticator.otp.PasscodeGenerator;
import com.google.android.apps.authenticator.util.Base32String;
import com.google.android.apps.authenticator.util.EncryptUtil;
import com.google.android.apps.authenticator.util.FileUtilities;
import com.google.android.apps.authenticator.util.Utilities;
import com.google.android.libraries.safesql.utils.SafeSql;
import com.google.android.libraries.safesql.utils.SafeSqlBuilder;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AccountDb {
    static final String COUNTER_COLUMN = "counter";
    static final String ENCRYPTED_COLUMN = "isencrypted";
    public static final String GOOGLE_CORP_ACCOUNT_NAME = "Google Internal 2Factor";
    public static final String ID_COLUMN = "_id";
    private static final int INVALID_ID = -1;
    public static final String ISSUER_COLUMN = "issuer";
    private static final String LOCAL_TAG = "GAuthenticator.AcctDb";
    static final int MAX_DUPLICATE_NAMES = 20;
    public static final String NAME_COLUMN = "email";
    static final String ORIGINAL_NAME_COLUMN = "original_name";
    static final String PROVIDER_COLUMN = "provider";
    static final int PROVIDER_UNKNOWN = 0;
    static final String SECRET_COLUMN = "secret";
    private static final String TABLE_INFO_COLUMN_NAME_COLUMN = "name";
    public static final String TABLE_NAME = "accounts";
    static final String TYPE_COLUMN = "type";
    public final AuditLogDb auditLog;
    public Context context;
    SQLiteDatabase mDatabase;
    static final /* synthetic */ boolean $assertionsDisabled = true;
    public static final Integer DEFAULT_HOTP_COUNTER = 0;
    public static final Integer DEFAULT_ENCRYPTED_VALUE = 0;
    public static final String GOOGLE_ISSUER_NAME = "Google";
    static final String[] AUTO_UPGRADE_ISSUERS = {GOOGLE_ISSUER_NAME, "Dropbox"};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class AccountDbOpenException extends RuntimeException {
        public AccountDbOpenException(String str, Exception exc) {
            super(str, exc);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum OtpType {
        TOTP(0),
        HOTP(1);
        
        public final Integer value;

        OtpType(Integer num) {
            this.value = num;
        }

        public static OtpType getEnum(Integer num) {
            OtpType[] values;
            for (OtpType otpType : values()) {
                if (otpType.value.equals(num)) {
                    return otpType;
                }
            }
            return TOTP;
        }
    }

    public AccountDb(Context context) {
        this.context = context;
        SQLiteDatabase openDatabase = openDatabase(context);
        this.mDatabase = openDatabase;
        openDatabase.setForeignKeyConstraintsEnabled(true);
        this.mDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER DEFAULT %s, %s INTEGER, %s INTEGER DEFAULT %s, %s TEXT DEFAULT NULL, %s TEXT DEFAULT NULL, %s INTEGER DEFAULT %s)", TABLE_NAME, ID_COLUMN, NAME_COLUMN, SECRET_COLUMN, COUNTER_COLUMN, DEFAULT_HOTP_COUNTER, TYPE_COLUMN, PROVIDER_COLUMN, 0, ISSUER_COLUMN, ORIGINAL_NAME_COLUMN, ENCRYPTED_COLUMN, DEFAULT_ENCRYPTED_VALUE));
        Collection listTableColumnNamesLowerCase = listTableColumnNamesLowerCase();
        if (!listTableColumnNamesLowerCase.contains(PROVIDER_COLUMN.toLowerCase(Locale.US))) {
            this.mDatabase.execSQL(String.format("ALTER TABLE %s ADD COLUMN %s INTEGER DEFAULT %s", TABLE_NAME, PROVIDER_COLUMN, 0));
        }
        if (!listTableColumnNamesLowerCase.contains(ISSUER_COLUMN.toLowerCase(Locale.US))) {
            this.mDatabase.execSQL(String.format("ALTER TABLE %s ADD COLUMN %s TEXT DEFAULT NULL", TABLE_NAME, ISSUER_COLUMN));
            autoUpgradeOlderAccountsWithIssuerPrefix();
        }
        if (!listTableColumnNamesLowerCase.contains(ORIGINAL_NAME_COLUMN.toLowerCase(Locale.US))) {
            this.mDatabase.execSQL(String.format("ALTER TABLE %s ADD COLUMN %s TEXT DEFAULT NULL", TABLE_NAME, ORIGINAL_NAME_COLUMN));
            Log.i(LOCAL_TAG, "Original name column added to database. ");
        }
        if (!listTableColumnNamesLowerCase.contains(ENCRYPTED_COLUMN.toLowerCase(Locale.US))) {
            this.mDatabase.execSQL(String.format("ALTER TABLE %s ADD COLUMN %s TEXT DEFAULT NULL", TABLE_NAME, ENCRYPTED_COLUMN));
            autoUpdateWithEncryptedSecret();
            Log.i(LOCAL_TAG, "isEncrypted column successfully added. Database consistent: " + isDbConsistent());
        }
        this.auditLog = new AuditLogDb(this.mDatabase);
    }

    private void autoUpdateWithEncryptedSecret() {
        Log.i(LOCAL_TAG, "Old version detected...updating with encrypted secrets");
        for (AccountIndex accountIndex : getAccounts()) {
            if (accountIndex.getIsEncrypted().intValue() == 0) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(SECRET_COLUMN, new EncryptUtil().wrapSecret(accountIndex.getSecret(), this.context));
                SafeSql whereClause = whereClause(accountIndex);
                int update = this.mDatabase.update(TABLE_NAME, contentValues, whereClause.query(), whereClause.args());
                if (update > 1) {
                    Log.wtf(LOCAL_TAG, String.format("Unexpectedly changed %s rows while auto-upgrading account: %s", Integer.valueOf(update), accountIndex.toString()));
                } else if (update == 0) {
                    Log.i(LOCAL_TAG, "Secret column not updated");
                } else {
                    Log.i(LOCAL_TAG, String.format("Successfully updated secrets column for account: %s", accountIndex.getIssuer()));
                }
                contentValues.put(ENCRYPTED_COLUMN, (Integer) 1);
                int update2 = this.mDatabase.update(TABLE_NAME, contentValues, whereClause.query(), whereClause.args());
                if (update2 > 1) {
                    Log.wtf(LOCAL_TAG, String.format("Unexpectedly changed %s rows while auto-upgrading account: %s", Integer.valueOf(update2), accountIndex.toString()));
                } else if (update2 == 0) {
                    Log.i(LOCAL_TAG, "isEncrypted Column not updated");
                } else {
                    Log.i(LOCAL_TAG, "Successfully updated isEncrypted column");
                }
            }
        }
    }

    private void autoUpgradeOlderAccountsWithIssuerPrefix() {
        String[] strArr;
        for (AccountIndex accountIndex : getAccounts()) {
            if (accountIndex.getIssuer() != null) {
                Log.wtf(LOCAL_TAG, "Existing new-style account detected during account upgrade process: " + accountIndex.toString());
            } else {
                for (String str : AUTO_UPGRADE_ISSUERS) {
                    if (accountIndex.getName().startsWith(str + ":")) {
                        Log.d(LOCAL_TAG, "Auto-upgrading old-style account: " + accountIndex.toString());
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(ISSUER_COLUMN, str);
                        SafeSql whereClause = whereClause(accountIndex);
                        if (this.mDatabase.update(TABLE_NAME, contentValues, whereClause.query(), whereClause.args()) > 1) {
                            Log.wtf(LOCAL_TAG, "Unexpectedly changed multiple rows while auto-upgrading account: " + accountIndex.toString());
                        }
                    }
                }
            }
        }
    }

    private static boolean cursorIsEmpty(Cursor cursor) {
        return cursor == null || cursor.getCount() == 0;
    }

    private static byte[] decodeKey(String str) {
        return Base32String.decode(str);
    }

    public static boolean deleteDatabase(Context context) {
        return context.deleteDatabase(FileUtilities.DATABASES_PATH);
    }

    private Account encryptAccountSecret(Account account) {
        return Account.builder().setIsEncrypted(1).setSecret(new EncryptUtil().wrapSecret(account.secret(), this.context)).setName(account.name()).setIssuer(account.issuer()).setOtpType(account.otpType()).setCounter(account.counter()).build();
    }

    private static Cursor getAccountCursor(SQLiteDatabase sQLiteDatabase, AccountIndex accountIndex) {
        SafeSql whereClause = whereClause(accountIndex);
        return sQLiteDatabase.query(TABLE_NAME, null, whereClause.query(), whereClause.args(), null, null, null);
    }

    public static String getFormattedNameFor(String str, String str2) {
        return new AccountIndex(str, str2).toString();
    }

    public static int getId(SQLiteDatabase sQLiteDatabase, AccountIndex accountIndex) {
        Cursor accountCursor = getAccountCursor(sQLiteDatabase, accountIndex);
        try {
            if (!cursorIsEmpty(accountCursor)) {
                accountCursor.moveToFirst();
                int i = accountCursor.getInt(accountCursor.getColumnIndex(ID_COLUMN));
                if (accountCursor != null) {
                    accountCursor.close();
                }
                return i;
            } else if (accountCursor != null) {
                accountCursor.close();
                return -1;
            } else {
                return -1;
            }
        } catch (Throwable th) {
            if (accountCursor != null) {
                try {
                    accountCursor.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    private Cursor getIssuerCursor(String str) {
        SafeSql whereClauseForIssuer = whereClauseForIssuer(str);
        return this.mDatabase.query(TABLE_NAME, null, whereClauseForIssuer.query(), whereClauseForIssuer.args(), null, null, null);
    }

    public static PasscodeGenerator.Signer getSigningOracle(String str) {
        try {
            byte[] decodeKey = decodeKey(str);
            final Mac mac = Mac.getInstance("HMACSHA1", "AndroidOpenSSL");
            mac.init(new SecretKeySpec(decodeKey, ""));
            return new PasscodeGenerator.Signer() { // from class: com.google.android.apps.authenticator.otp.AccountDb.1
                @Override // com.google.android.apps.authenticator.otp.PasscodeGenerator.Signer
                public byte[] sign(byte[] bArr) {
                    return mac.doFinal(bArr);
                }
            };
        } catch (Base32String.DecodingException | IllegalArgumentException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException e) {
            Log.e(LOCAL_TAG, e.getMessage());
            return null;
        }
    }

    private boolean insertNewAccount(Account account) {
        AccountIndex accountIndex = new AccountIndex(account.name(), account.issuer());
        if (!indexExists(accountIndex)) {
            return this.mDatabase.insert(TABLE_NAME, null, newContentValuesWith(account)) != -1;
        }
        Account accountInfo = getAccountInfo(findSimilarExistingIndex(accountIndex));
        if ($assertionsDisabled || accountInfo != null) {
            if (accountInfo.isEncrypted().intValue() == 1) {
                accountInfo = Account.builder().setSecret(new EncryptUtil().unwrapSecret(accountInfo.secret(), this.context)).setName(accountInfo.name()).setIssuer(accountInfo.issuer()).setOtpType(accountInfo.otpType()).setCounter(accountInfo.counter()).build();
            }
            if (account.isEncrypted().intValue() == 1) {
                account = Account.builder().setSecret(new EncryptUtil().unwrapSecret(account.secret(), this.context)).setName(account.name()).setIssuer(account.issuer()).setOtpType(account.otpType()).setCounter(account.counter()).build();
            }
            return account.equals(accountInfo);
        }
        throw new AssertionError();
    }

    private boolean isAccountValid(String str, int i) {
        if (i == 1) {
            return !new EncryptUtil().unwrapSecret(str, this.context).isEmpty();
        }
        return true;
    }

    private Collection listTableColumnNamesLowerCase() {
        return listTableColumnNamesLowerCase(this.mDatabase, TABLE_NAME);
    }

    private static ContentValues newContentValuesWith(Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COLUMN, account.name());
        contentValues.put(ISSUER_COLUMN, account.issuer());
        contentValues.put(ORIGINAL_NAME_COLUMN, account.name());
        contentValues.put(SECRET_COLUMN, account.secret());
        contentValues.put(TYPE_COLUMN, Integer.valueOf(account.otpType().ordinal()));
        contentValues.put(ENCRYPTED_COLUMN, account.isEncrypted());
        if (account.counter() != null) {
            contentValues.put(COUNTER_COLUMN, account.counter());
        }
        return contentValues;
    }

    static SafeSql whereClause(AccountIndex accountIndex) {
        Preconditions.checkNotNull(accountIndex);
        SafeSqlBuilder appendArgs = SafeSqlBuilder.builder().appendArgs("email = ? AND ", accountIndex.getName());
        SafeSql whereClauseForIssuer = whereClauseForIssuer(accountIndex.getIssuer());
        return appendArgs.appendArgs(whereClauseForIssuer.query(), whereClauseForIssuer.args()).build();
    }

    private static SafeSql whereClauseForIssuer(String str) {
        return str != null ? SafeSqlBuilder.builder().appendArgs("issuer = ?", str).build() : SafeSqlBuilder.builder().append("issuer IS NULL").build();
    }

    public boolean accountAlreadyExists(AccountIndex accountIndex) {
        return findSimilarExistingIndex(accountIndex) != null;
    }

    public AccountIndex add(Account account) {
        if (account.isEncrypted().intValue() != 1) {
            account = encryptAccountSecret(account);
        }
        this.mDatabase.beginTransaction();
        try {
            AccountIndex addWithoutAuditLogEntry = addWithoutAuditLogEntry(account);
            this.auditLog.addEvent(AddAccountEvent.builder().setAccountIndex(addWithoutAuditLogEntry).build());
            this.mDatabase.setTransactionSuccessful();
            return addWithoutAuditLogEntry;
        } finally {
            this.mDatabase.endTransaction();
        }
    }

    public List addAll(List list) {
        ArrayList arrayList = new ArrayList();
        this.mDatabase.beginTransaction();
        try {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Account account = (Account) it.next();
                if (account.isEncrypted().intValue() != 1) {
                    account = encryptAccountSecret(account);
                }
                arrayList.add(addWithoutAuditLogEntry(account));
            }
            this.auditLog.addEvent(ImportEvent.builder().setAmount(list.size()).build());
            this.mDatabase.setTransactionSuccessful();
            return arrayList;
        } finally {
            this.mDatabase.endTransaction();
        }
    }

    public void close() {
        this.mDatabase.close();
    }

    public void delete(AccountIndex accountIndex) {
        SafeSql whereClause = whereClause(accountIndex);
        this.mDatabase.delete(TABLE_NAME, whereClause.query(), whereClause.args());
    }

    public boolean deleteAllData() {
        this.mDatabase.delete(TABLE_NAME, null, null);
        return true;
    }

    public AccountIndex findSimilarExistingIndex(AccountIndex accountIndex) {
        if (indexExists(accountIndex)) {
            return accountIndex;
        }
        if (accountIndex.getIssuer() == null) {
            return null;
        }
        Cursor issuerCursor = getIssuerCursor(accountIndex.getIssuer());
        if (issuerCursor == null) {
            if (issuerCursor != null) {
                issuerCursor.close();
            }
            return null;
        }
        try {
            int columnIndex = issuerCursor.getColumnIndex(NAME_COLUMN);
            while (issuerCursor.moveToNext()) {
                AccountIndex accountIndex2 = new AccountIndex(issuerCursor.getString(columnIndex), accountIndex.getIssuer());
                if (accountIndex.getStrippedName().equals(accountIndex2.getStrippedName())) {
                    if (issuerCursor != null) {
                        issuerCursor.close();
                    }
                    return accountIndex2;
                }
            }
            if (issuerCursor != null) {
                issuerCursor.close();
            }
            return null;
        } catch (Throwable th) {
            if (issuerCursor != null) {
                try {
                    issuerCursor.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public Account getAccountInfo(AccountIndex accountIndex) {
        Cursor accountCursor = getAccountCursor(accountIndex);
        try {
            if (!cursorIsEmpty(accountCursor)) {
                accountCursor.moveToFirst();
                Account build = Account.builder().setName(accountCursor.getString(accountCursor.getColumnIndexOrThrow(NAME_COLUMN))).setIssuer(accountCursor.getString(accountCursor.getColumnIndexOrThrow(ISSUER_COLUMN))).setSecret(accountCursor.getString(accountCursor.getColumnIndexOrThrow(SECRET_COLUMN))).setOtpType(OtpType.getEnum(Integer.valueOf(accountCursor.getInt(accountCursor.getColumnIndexOrThrow(TYPE_COLUMN))))).setCounter(Integer.valueOf(accountCursor.getInt(accountCursor.getColumnIndexOrThrow(COUNTER_COLUMN)))).setIsEncrypted(Integer.valueOf(accountCursor.getInt(accountCursor.getColumnIndexOrThrow(ENCRYPTED_COLUMN)))).build();
                if (accountCursor != null) {
                    accountCursor.close();
                }
                return build;
            } else if (accountCursor != null) {
                accountCursor.close();
                return null;
            } else {
                return null;
            }
        } catch (Throwable th) {
            if (accountCursor != null) {
                try {
                    accountCursor.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public List getAccounts() {
        String str;
        Cursor query = this.mDatabase.query(TABLE_NAME, null, null, null, null, null, null, null);
        try {
            if (cursorIsEmpty(query)) {
                ImmutableList of = ImmutableList.of();
                if (query != null) {
                    query.close();
                }
                return of;
            }
            int count = query.getCount();
            int columnIndex = query.getColumnIndex(NAME_COLUMN);
            int columnIndex2 = query.getColumnIndex(ISSUER_COLUMN);
            int columnIndex3 = query.getColumnIndex(SECRET_COLUMN);
            int columnIndex4 = query.getColumnIndex(ENCRYPTED_COLUMN);
            ImmutableList.Builder builder = ImmutableList.builder();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i2 < count) {
                query.moveToPosition(i2);
                String string = query.getString(columnIndex);
                String string2 = query.getString(columnIndex3);
                Integer valueOf = Integer.valueOf(i);
                if (columnIndex2 < 0) {
                    str = null;
                } else {
                    str = query.getString(columnIndex2);
                }
                if (columnIndex4 >= 0) {
                    valueOf = Integer.valueOf(query.getInt(columnIndex4));
                }
                if (isAccountValid(string2, valueOf.intValue())) {
                    i3++;
                    builder.add((Object) new AccountIndex(string, string2, valueOf, str));
                } else {
                    Log.w(LOCAL_TAG, "Invalid account.");
                }
                i2++;
                i = 0;
            }
            Log.d(LOCAL_TAG, String.format("There are %s accounts", Integer.valueOf(i3)));
            ImmutableList build = builder.build();
            if (query != null) {
                query.close();
            }
            return build;
        } catch (Throwable th) {
            if (query != null) {
                try {
                    query.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public Integer getCounter(AccountIndex accountIndex) {
        Account accountInfo = getAccountInfo(accountIndex);
        if (accountInfo == null) {
            return null;
        }
        return accountInfo.counter();
    }

    public String getOriginalName(AccountIndex accountIndex) {
        Cursor accountCursor = getAccountCursor(accountIndex);
        try {
            if (!cursorIsEmpty(accountCursor)) {
                accountCursor.moveToFirst();
                int columnIndex = accountCursor.getColumnIndex(ORIGINAL_NAME_COLUMN);
                if (columnIndex < 0) {
                    if (accountCursor != null) {
                        accountCursor.close();
                    }
                    return null;
                }
                String string = accountCursor.getString(columnIndex);
                if (accountCursor != null) {
                    accountCursor.close();
                }
                return string;
            }
            if (accountCursor != null) {
                accountCursor.close();
            }
            return null;
        } catch (Throwable th) {
            if (accountCursor != null) {
                try {
                    accountCursor.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public String getSecret(AccountIndex accountIndex) {
        Account accountInfo = getAccountInfo(accountIndex);
        if (accountInfo == null) {
            return null;
        }
        if (accountInfo.isEncrypted().intValue() == 0) {
            accountInfo = encryptAccountSecret(accountInfo);
        }
        return new EncryptUtil().unwrapSecret(accountInfo.secret(), this.context);
    }

    public OtpType getType(AccountIndex accountIndex) {
        Account accountInfo = getAccountInfo(accountIndex);
        if (accountInfo == null) {
            return null;
        }
        return accountInfo.otpType();
    }

    public void incrementCounter(AccountIndex accountIndex) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COUNTER_COLUMN, Integer.valueOf(getCounter(accountIndex).intValue() + 1));
        SafeSql whereClause = whereClause(accountIndex);
        this.mDatabase.update(TABLE_NAME, contentValues, whereClause.query(), whereClause.args());
    }

    public boolean indexExists(AccountIndex accountIndex) {
        Cursor accountCursor = getAccountCursor(accountIndex);
        try {
            boolean z = !cursorIsEmpty(accountCursor);
            if (accountCursor != null) {
                accountCursor.close();
            }
            return z;
        } catch (Throwable th) {
            if (accountCursor != null) {
                try {
                    accountCursor.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public AccountIndex overwrite(Account account) {
        if (account.isEncrypted().intValue() == 0) {
            account = encryptAccountSecret(account);
        }
        ContentValues newContentValuesWith = newContentValuesWith(account);
        AccountIndex accountIndex = new AccountIndex(account.name(), account.issuer());
        Log.i(LOCAL_TAG, "Adding account: " + String.valueOf(accountIndex));
        AccountIndex findSimilarExistingIndex = findSimilarExistingIndex(accountIndex);
        if (findSimilarExistingIndex == null) {
            throw new IllegalArgumentException("There is no existing account to overwrite");
        }
        Log.i(LOCAL_TAG, "Will overwrite similar account: " + String.valueOf(findSimilarExistingIndex));
        SafeSql whereClause = whereClause(findSimilarExistingIndex);
        this.mDatabase.update(TABLE_NAME, newContentValuesWith, whereClause.query(), whereClause.args());
        Log.i(LOCAL_TAG, "Overwrote existing OTP seed for: " + String.valueOf(findSimilarExistingIndex));
        return accountIndex;
    }

    public boolean rename(AccountIndex accountIndex, String str) {
        Preconditions.checkNotNull(accountIndex);
        Preconditions.checkNotNull(accountIndex.getName());
        Preconditions.checkNotNull(str);
        if (accountIndex.getName().equals(str)) {
            return true;
        }
        if (indexExists(new AccountIndex(str, accountIndex.getIssuer()))) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COLUMN, str);
        SafeSql whereClause = whereClause(accountIndex);
        int update = this.mDatabase.update(TABLE_NAME, contentValues, whereClause.query(), whereClause.args());
        if (update > 1) {
            Log.wtf(LOCAL_TAG, "Unexpectedly changed multiple rows during rename. Database consistent: " + isDbConsistent());
        }
        return update > 0;
    }

    public void swapId(AccountIndex accountIndex, AccountIndex accountIndex2) {
        this.mDatabase.beginTransaction();
        try {
            try {
                int id = getId(accountIndex);
                int id2 = getId(accountIndex2);
                ContentValues contentValues = new ContentValues();
                contentValues.put(ID_COLUMN, (Integer) (-1));
                SafeSql whereClause = whereClause(accountIndex2);
                this.mDatabase.updateWithOnConflict(TABLE_NAME, contentValues, whereClause.query(), whereClause.args(), 1);
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put(ID_COLUMN, Integer.valueOf(id2));
                SafeSql whereClause2 = whereClause(accountIndex);
                this.mDatabase.updateWithOnConflict(TABLE_NAME, contentValues2, whereClause2.query(), whereClause2.args(), 1);
                ContentValues contentValues3 = new ContentValues();
                contentValues3.put(ID_COLUMN, Integer.valueOf(id));
                this.mDatabase.updateWithOnConflict(TABLE_NAME, contentValues3, whereClause.query(), whereClause.args(), 1);
                this.mDatabase.setTransactionSuccessful();
            } catch (SQLiteException e) {
                throw new AccountDbIdUpdateFailureException(String.format("Updating the Id failed for %s and %s", accountIndex, accountIndex2), e);
            }
        } finally {
            this.mDatabase.endTransaction();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class AccountDbDuplicateLimitException extends RuntimeException {
        public AccountDbDuplicateLimitException(String str) {
            super(str);
        }

        public AccountDbDuplicateLimitException(String str, Exception exc) {
            super(str, exc);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class AccountDbIdUpdateFailureException extends Exception {
        public AccountDbIdUpdateFailureException(String str) {
            super(str);
        }

        public AccountDbIdUpdateFailureException(String str, Exception exc) {
            super(str, exc);
        }
    }

    private AccountIndex addWithoutAuditLogEntry(Account account) {
        int i = 0;
        Account account2 = account;
        while (!insertNewAccount(account2)) {
            i++;
            if (i < 20) {
                account2 = Account.builder().setName(account.name() + "(" + i + ")").setIssuer(account.issuer()).setSecret(account.secret()).setOtpType(account.otpType()).setCounter(account.counter()).setIsEncrypted(account.isEncrypted()).build();
            } else {
                throw new AccountDbDuplicateLimitException("Too many accounts with same name: " + account.name());
            }
        }
        return new AccountIndex(account2.name(), account2.issuer());
    }

    static Collection listTableColumnNamesLowerCase(SQLiteDatabase sQLiteDatabase, String str) {
        ArrayList newArrayList = Lists.newArrayList();
        Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA table_info(" + str + ")", new String[0]);
        if (rawQuery != null) {
            try {
                int columnIndexOrThrow = rawQuery.getColumnIndexOrThrow(TABLE_INFO_COLUMN_NAME_COLUMN);
                while (rawQuery.moveToNext()) {
                    newArrayList.add(rawQuery.getString(columnIndexOrThrow).toLowerCase(Locale.US));
                }
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
        if (rawQuery != null) {
            rawQuery.close();
        }
        return newArrayList;
    }

    private static SQLiteDatabase openDatabase(Context context) {
        int i = 0;
        while (true) {
            try {
                return context.openOrCreateDatabase(FileUtilities.DATABASES_PATH, 0, null);
            } catch (SQLiteException e) {
                if (i < 2) {
                    i++;
                } else {
                    throw new AccountDbOpenException("Failed to open AccountDb database in three tries.\n" + FileUtilities.getFilesystemInfoForErrorString(context), e);
                }
            }
        }
    }

    public boolean isDbConsistent() {
        boolean z;
        Collection listTableColumnNamesLowerCase = listTableColumnNamesLowerCase();
        String[] strArr = {ID_COLUMN, NAME_COLUMN, SECRET_COLUMN, COUNTER_COLUMN, TYPE_COLUMN, PROVIDER_COLUMN, ISSUER_COLUMN, ORIGINAL_NAME_COLUMN, ENCRYPTED_COLUMN};
        if (listTableColumnNamesLowerCase.size() > 9) {
            Log.w(LOCAL_TAG, "Database has extra columns");
        }
        if (listTableColumnNamesLowerCase.size() < 9) {
            Log.e(LOCAL_TAG, "Database is missing columns");
            z = false;
        } else {
            z = true;
        }
        for (int i = 0; i < 9; i++) {
            String str = strArr[i];
            if (!listTableColumnNamesLowerCase.contains(str.toLowerCase(Locale.US))) {
                Log.e(LOCAL_TAG, "Database is missing column: " + str);
                z = false;
            }
        }
        for (AccountIndex accountIndex : getAccounts()) {
            SafeSql whereClause = whereClause(accountIndex);
            Cursor query = this.mDatabase.query(TABLE_NAME, null, whereClause.query(), whereClause.args(), null, null, null);
            if (query == null) {
                try {
                    Log.e(LOCAL_TAG, "Failed to get a cursor for account: " + accountIndex.toString());
                    if (query == null) {
                        z = false;
                    } else {
                        query.close();
                        z = false;
                    }
                } catch (Throwable th) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th2) {
                            Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                        }
                    }
                    throw th;
                }
            } else {
                if (query.getCount() != 1) {
                    Log.e(LOCAL_TAG, "Multiple copies detected for account: " + accountIndex.toString());
                    z = false;
                }
                if (query != null) {
                    query.close();
                }
            }
        }
        return z;
    }

    private Cursor getAccountCursor(AccountIndex accountIndex) {
        return getAccountCursor(this.mDatabase, accountIndex);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class AccountIndex implements Serializable {
        private final Integer isEncrypted;
        private final String issuer;
        private final String name;
        private final String secret;

        public AccountIndex(String str, String str2) {
            Preconditions.checkNotNull(str);
            this.name = str;
            this.secret = null;
            this.isEncrypted = 0;
            this.issuer = TextUtils.isEmpty(str2) ? null : str2;
        }

        public boolean equals(Object obj) {
            boolean equals;
            if (obj == null || !(obj instanceof AccountIndex)) {
                return false;
            }
            AccountIndex accountIndex = (AccountIndex) obj;
            String str = this.issuer;
            if (str == null) {
                equals = accountIndex.issuer == null;
            } else {
                equals = str.equals(accountIndex.issuer);
            }
            return this.name.equals(accountIndex.name) && equals;
        }

        public Integer getIsEncrypted() {
            return this.isEncrypted;
        }

        public String getIssuer() {
            return this.issuer;
        }

        public String getName() {
            return this.name;
        }

        public String getSecret() {
            return this.secret;
        }

        public String getStrippedName() {
            if (!Strings.isNullOrEmpty(this.issuer)) {
                if (this.name.startsWith(this.issuer + ":")) {
                    return this.name.substring(this.issuer.length() + 1).trim();
                }
            }
            return this.name.trim();
        }

        public int hashCode() {
            String str = this.issuer;
            if (str == null) {
                return this.name.hashCode();
            }
            return (this.name + "|" + str).hashCode();
        }

        public String toString() {
            return Utilities.getCombinedTextForIssuerAndAccountName(this.issuer, getStrippedName());
        }

        public AccountIndex(String str, String str2, Integer num, String str3) {
            Preconditions.checkNotNull(str);
            this.name = str;
            this.secret = str2;
            this.issuer = TextUtils.isEmpty(str3) ? null : str3;
            this.isEncrypted = num;
        }
    }

    int getId(AccountIndex accountIndex) {
        return getId(this.mDatabase, accountIndex);
    }
}
