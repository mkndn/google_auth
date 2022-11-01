package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Storage {
    private static Storage sInstance;
    private static final Lock sLk = new ReentrantLock();
    private final Lock mLk = new ReentrantLock();
    private final SharedPreferences mPrefs;

    Storage(Context context) {
        this.mPrefs = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static Storage getInstance(Context context) {
        Preconditions.checkNotNull(context);
        Lock lock = sLk;
        lock.lock();
        try {
            if (sInstance == null) {
                sInstance = new Storage(context.getApplicationContext());
            }
            Storage storage = sInstance;
            lock.unlock();
            return storage;
        } catch (Throwable th) {
            sLk.unlock();
            throw th;
        }
    }

    private String getKey(String str, String str2) {
        return str + ":" + str2;
    }

    public void clear() {
        this.mLk.lock();
        try {
            this.mPrefs.edit().clear().apply();
        } finally {
            this.mLk.unlock();
        }
    }

    protected String getFromStore(String str) {
        this.mLk.lock();
        try {
            return this.mPrefs.getString(str, null);
        } finally {
            this.mLk.unlock();
        }
    }

    public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
        return getSavedGoogleSignInAccount(getFromStore("defaultGoogleSignInAccount"));
    }

    public GoogleSignInOptions getSavedDefaultGoogleSignInOptions() {
        return getSavedGoogleSignInOptions(getFromStore("defaultGoogleSignInAccount"));
    }

    GoogleSignInAccount getSavedGoogleSignInAccount(String str) {
        String fromStore;
        if (TextUtils.isEmpty(str) || (fromStore = getFromStore(getKey("googleSignInAccount", str))) == null) {
            return null;
        }
        try {
            return GoogleSignInAccount.fromJsonString(fromStore);
        } catch (JSONException e) {
            return null;
        }
    }

    GoogleSignInOptions getSavedGoogleSignInOptions(String str) {
        String fromStore;
        if (TextUtils.isEmpty(str) || (fromStore = getFromStore(getKey("googleSignInOptions", str))) == null) {
            return null;
        }
        try {
            return GoogleSignInOptions.fromJsonString(fromStore);
        } catch (JSONException e) {
            return null;
        }
    }

    public String getSavedRefreshToken() {
        return getFromStore("refreshToken");
    }

    public void saveDefaultGoogleSignInAccount(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        saveToStore("defaultGoogleSignInAccount", googleSignInAccount.getObfuscatedIdentifier());
        saveGoogleSignInAccount(googleSignInAccount, googleSignInOptions);
    }

    void saveGoogleSignInAccount(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        String obfuscatedIdentifier = googleSignInAccount.getObfuscatedIdentifier();
        saveToStore(getKey("googleSignInAccount", obfuscatedIdentifier), googleSignInAccount.toJsonForStorage());
        saveToStore(getKey("googleSignInOptions", obfuscatedIdentifier), googleSignInOptions.toJson());
    }

    protected void saveToStore(String str, String str2) {
        this.mLk.lock();
        try {
            this.mPrefs.edit().putString(str, str2).apply();
        } finally {
            this.mLk.unlock();
        }
    }
}
