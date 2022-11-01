package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.BuildConstants;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CallingContext {
    private static Delegate delegateSingleton;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Delegate {
        ModuleInfo getCallingModuleInfo();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ModuleInfo {
        public String getCallingEntryPoint() {
            throw null;
        }

        public String getCallingModuleId() {
            throw null;
        }

        public boolean getTelemetryDisabled() {
            throw null;
        }
    }

    public static ModuleInfo getCallingModuleInfo() {
        Delegate delegate;
        if (BuildConstants.IS_PACKAGE_SIDE && (delegate = getDelegate()) != null) {
            return delegate.getCallingModuleInfo();
        }
        return null;
    }

    private static Delegate getDelegate() {
        return delegateSingleton;
    }
}
