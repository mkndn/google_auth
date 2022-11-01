package com.google.common.flogger.backend;

import com.google.common.flogger.LogSite;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.flogger.context.Tags;
import com.google.common.flogger.util.RecursionDepth;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Platform {
    private static String ANDROID_PLATFORM = "com.google.common.flogger.backend.android.AndroidPlatform";
    private static String GOOGLE_PLATFORM = "com.google.common.flogger.backend.google.GooglePlatform";
    private static String DEFAULT_PLATFORM = "com.google.common.flogger.backend.system.DefaultPlatform";
    private static final String[] AVAILABLE_PLATFORMS = {"com.google.common.flogger.backend.android.AndroidPlatform", "com.google.common.flogger.backend.google.GooglePlatform", "com.google.common.flogger.backend.system.DefaultPlatform"};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LazyHolder {
        private static final Platform INSTANCE = loadFirstAvailablePlatform(Platform.AVAILABLE_PLATFORMS);

        private static Platform loadFirstAvailablePlatform(String[] strArr) {
            Platform platform;
            try {
                platform = PlatformProvider.getPlatform();
            } catch (NoClassDefFoundError e) {
                platform = null;
            }
            if (platform != null) {
                return platform;
            }
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                try {
                    return (Platform) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    th = th;
                    if (th instanceof InvocationTargetException) {
                        th = th.getCause();
                    }
                    sb.append('\n').append(str).append(": ").append(th);
                }
            }
            throw new IllegalStateException(sb.insert(0, "No logging platforms found:").toString());
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class LogCallerFinder {
        public abstract LogSite findLogSite(Class cls, int i);

        public abstract String findLoggingClass(Class cls);
    }

    public static LoggerBackend getBackend(String str) {
        return LazyHolder.INSTANCE.getBackendImpl(str);
    }

    public static LogCallerFinder getCallerFinder() {
        return LazyHolder.INSTANCE.getCallerFinderImpl();
    }

    public static String getConfigInfo() {
        return LazyHolder.INSTANCE.getConfigInfoImpl();
    }

    public static ContextDataProvider getContextDataProvider() {
        return LazyHolder.INSTANCE.getContextDataProviderImpl();
    }

    public static int getCurrentRecursionDepth() {
        return RecursionDepth.getCurrentDepth();
    }

    public static long getCurrentTimeNanos() {
        return LazyHolder.INSTANCE.getCurrentTimeNanosImpl();
    }

    public static Metadata getInjectedMetadata() {
        return getContextDataProvider().getMetadata();
    }

    public static Tags getInjectedTags() {
        return getContextDataProvider().getTags();
    }

    public static boolean shouldForceLogging(String str, Level level, boolean z) {
        return getContextDataProvider().shouldForceLogging(str, level, z);
    }

    protected abstract LoggerBackend getBackendImpl(String str);

    protected abstract LogCallerFinder getCallerFinderImpl();

    protected abstract String getConfigInfoImpl();

    protected ContextDataProvider getContextDataProviderImpl() {
        return ContextDataProvider.getNoOpProvider();
    }

    protected long getCurrentTimeNanosImpl() {
        return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
    }
}
