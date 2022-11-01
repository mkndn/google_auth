package com.google.common.flogger.backend.android;

import android.os.Build;
import android.util.Log;
import com.google.common.flogger.LogSite;
import com.google.common.flogger.backend.LoggerBackend;
import com.google.common.flogger.backend.Platform;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.flogger.util.CallerFinder;
import dalvik.system.VMStack;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidPlatform extends Platform {
    private static final Platform.LogCallerFinder CALLER_FINDER;
    private static final boolean IS_ROBOLECTRIC;
    private static final boolean USE_FAST_ANDROID_STACK = InternalStackVerifier.isVmStackPresent();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class InternalStackVerifier {
        InternalStackVerifier() {
        }

        static boolean isVmStackPresent() {
            return AndroidPlatform.isVmStackPresent();
        }
    }

    static {
        IS_ROBOLECTRIC = Build.FINGERPRINT == null || "robolectric".equals(Build.FINGERPRINT);
        Log.class.getName();
        CALLER_FINDER = new Platform.LogCallerFinder() { // from class: com.google.common.flogger.backend.android.AndroidPlatform.1
            @Override // com.google.common.flogger.backend.Platform.LogCallerFinder
            public LogSite findLogSite(Class cls, int i) {
                return LogSite.INVALID;
            }

            @Override // com.google.common.flogger.backend.Platform.LogCallerFinder
            public String findLoggingClass(Class cls) {
                StackTraceElement findCallerOf;
                if (AndroidPlatform.USE_FAST_ANDROID_STACK) {
                    try {
                        if (cls.equals(AndroidPlatform.getStackClass1())) {
                            return VMStack.getStackClass2().getName();
                        }
                    } catch (Throwable th) {
                    }
                }
                if (!AndroidPlatform.IS_ROBOLECTRIC || (findCallerOf = CallerFinder.findCallerOf(cls, 1)) == null) {
                    return null;
                }
                return findCallerOf.getClassName();
            }
        };
    }

    static Class getStackClass1() {
        return VMStack.getStackClass2();
    }

    static boolean isVmStackPresent() {
        try {
            Class.forName("dalvik.system.VMStack").getMethod("getStackClass2", new Class[0]);
            return InternalStackVerifier.class.getName().equals(staticGetLoggingClassName());
        } catch (Throwable th) {
            return false;
        }
    }

    static String staticGetLoggingClassName() {
        try {
            return VMStack.getStackClass2().getName();
        } catch (Throwable th) {
            return null;
        }
    }

    @Override // com.google.common.flogger.backend.Platform
    protected LoggerBackend getBackendImpl(String str) {
        return ProxyAndroidLoggerBackend.create(str);
    }

    @Override // com.google.common.flogger.backend.Platform
    protected Platform.LogCallerFinder getCallerFinderImpl() {
        return CALLER_FINDER;
    }

    @Override // com.google.common.flogger.backend.Platform
    protected String getConfigInfoImpl() {
        return "platform: Android";
    }

    @Override // com.google.common.flogger.backend.Platform
    protected ContextDataProvider getContextDataProviderImpl() {
        return ProxyContextDataProvider.getInstance();
    }
}
