package com.google.apps.tiktok.inject;

import android.content.Context;
import dagger.hilt.internal.GeneratedComponentManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SingletonEntryPoints {
    public static Object getEntryPoint(Context context, Class cls) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof GeneratedComponentManager) {
            try {
                return cls.cast(((GeneratedComponentManager) applicationContext).generatedComponent());
            } catch (ClassCastException e) {
                throw new IllegalStateException("Failed to get an entry point. Did you mark your interface with @SingletonEntryPoint?", e);
            }
        }
        throw new IllegalStateException("Given application context does not implement GeneratedComponentManager: " + String.valueOf(applicationContext.getClass()));
    }
}
