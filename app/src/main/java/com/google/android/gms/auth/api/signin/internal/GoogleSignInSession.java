package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleSignInSession {
    private static GoogleSignInSession sInstance = null;
    GoogleSignInAccount mLastGoogleSignInAccount;
    GoogleSignInOptions mLastGoogleSignInOptions;
    Storage mStorage;

    private GoogleSignInSession(Context context) {
        Storage storage = Storage.getInstance(context);
        this.mStorage = storage;
        this.mLastGoogleSignInAccount = storage.getSavedDefaultGoogleSignInAccount();
        this.mLastGoogleSignInOptions = this.mStorage.getSavedDefaultGoogleSignInOptions();
    }

    public static synchronized GoogleSignInSession getInstance(Context context) {
        GoogleSignInSession orCreateInstance;
        synchronized (GoogleSignInSession.class) {
            orCreateInstance = getOrCreateInstance(context.getApplicationContext());
        }
        return orCreateInstance;
    }

    private static synchronized GoogleSignInSession getOrCreateInstance(Context context) {
        synchronized (GoogleSignInSession.class) {
            GoogleSignInSession googleSignInSession = sInstance;
            if (googleSignInSession != null) {
                return googleSignInSession;
            }
            GoogleSignInSession googleSignInSession2 = new GoogleSignInSession(context);
            sInstance = googleSignInSession2;
            return googleSignInSession2;
        }
    }

    public synchronized void clear() {
        this.mStorage.clear();
        this.mLastGoogleSignInAccount = null;
        this.mLastGoogleSignInOptions = null;
    }

    public synchronized void setLastGoogleSignInAccount(GoogleSignInOptions googleSignInOptions, GoogleSignInAccount googleSignInAccount) {
        this.mStorage.saveDefaultGoogleSignInAccount(googleSignInAccount, googleSignInOptions);
        this.mLastGoogleSignInAccount = googleSignInAccount;
        this.mLastGoogleSignInOptions = googleSignInOptions;
    }
}
