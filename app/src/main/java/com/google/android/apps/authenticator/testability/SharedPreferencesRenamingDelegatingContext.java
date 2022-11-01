package com.google.android.apps.authenticator.testability;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SharedPreferencesRenamingDelegatingContext extends ContextWrapper {
    private final String mPrefix;

    public SharedPreferencesRenamingDelegatingContext(Context context, String str) {
        super(context);
        this.mPrefix = str;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SharedPreferences getSharedPreferences(String str, int i) {
        return super.getSharedPreferences(this.mPrefix + str, i);
    }
}
