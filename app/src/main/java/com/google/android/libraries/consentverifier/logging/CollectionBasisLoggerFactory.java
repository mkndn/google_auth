package com.google.android.libraries.consentverifier.logging;

import android.content.Context;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.libraries.consentverifier.flags.Flags;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CollectionBasisLoggerFactory {
    private static CollectionBasisLogger defaultLogger;
    private static final Object defaultLoggerLock = new Object();
    private static boolean loggingEnabled;

    public static CollectionBasisLogger getCollectionBasisLogger(Context context, AppInfoHelper appInfoHelper) {
        maybeUpdateLogger(context, appInfoHelper);
        return defaultLogger;
    }

    private static void maybeUpdateLogger(Context context, AppInfoHelper appInfoHelper) {
        if (defaultLogger == null || loggingEnabled != shouldEnableLogging(context, appInfoHelper)) {
            synchronized (defaultLoggerLock) {
                boolean shouldEnableLogging = shouldEnableLogging(context, appInfoHelper);
                if (defaultLogger == null || loggingEnabled != shouldEnableLogging) {
                    if (shouldEnableLogging) {
                        defaultLogger = new ClearcutEventLogger(ClearcutLogger.newBuilder(context, "COLLECTION_BASIS_VERIFIER").build());
                    } else {
                        defaultLogger = new VoidLogger();
                    }
                    loggingEnabled = shouldEnableLogging;
                }
            }
        }
    }

    public static boolean shouldEnableLogging(Context context, AppInfoHelper appInfoHelper) {
        return Flags.enableLogging() && ((long) appInfoHelper.getVersionCode(context)) >= Flags.minAppVersionCodeToLog();
    }
}
