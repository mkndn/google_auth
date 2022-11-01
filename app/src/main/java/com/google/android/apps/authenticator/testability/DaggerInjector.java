package com.google.android.apps.authenticator.testability;

import android.util.Log;
import dagger.ObjectGraph;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DaggerInjector {
    private static final String TAG = DaggerInjector.class.getSimpleName();
    private static volatile ObjectGraph mObjectGraph;

    public static void init(Object obj) {
        mObjectGraph = obj != null ? ObjectGraph.create(obj) : null;
    }

    public static void inject(Object obj) {
        if (mObjectGraph == null) {
            Log.w(TAG, "Dagger injection has not been initialized!");
        } else {
            mObjectGraph.inject(obj);
        }
    }
}
