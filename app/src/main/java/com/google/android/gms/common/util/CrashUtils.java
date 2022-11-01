package com.google.android.gms.common.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Debug;
import android.os.DropBoxManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.apps.authenticator.testability.android.os.PowerManager;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashUtils {
    private static final int IMPORTANCE_FOREGROUND;
    private static final String[] SYSTEM_CLASS_PREFIXES = {"android.", "com.android.", "dalvik.", "java.", "javax."};
    private static boolean isUnderTest;
    private static int lastThrowableInstanceHash;
    private static int lastThrowableStackHash;
    private static DropBoxManager testDropBoxManager;
    private static boolean testIsGmsProcess;
    private static boolean testIsPackageSide;
    private static int testLines;

    static {
        IMPORTANCE_FOREGROUND = Build.VERSION.SDK_INT >= 23 ? 125 : 100;
        testDropBoxManager = null;
        isUnderTest = false;
        testLines = -1;
        lastThrowableStackHash = 0;
        lastThrowableInstanceHash = 0;
    }

    public static boolean addDynamiteErrorToDropBox(Context context, Throwable th) {
        return addErrorToDropBoxInternal(context, th, PowerManager.ON_AFTER_RELEASE);
    }

    private static synchronized boolean addErrorToDropBoxInternal(Context context, String str, String str2, int i, Throwable th) {
        synchronized (CrashUtils.class) {
            Preconditions.checkNotNull(context);
            if (isPackageSide() && !Strings.isEmptyOrWhitespace(str)) {
                int hashCode = str.hashCode();
                int hashCode2 = th == null ? lastThrowableInstanceHash : th.hashCode();
                if (lastThrowableStackHash == hashCode && lastThrowableInstanceHash == hashCode2) {
                    return false;
                }
                lastThrowableStackHash = hashCode;
                lastThrowableInstanceHash = hashCode2;
                DropBoxManager dropBoxManager = testDropBoxManager;
                if (dropBoxManager == null) {
                    dropBoxManager = (DropBoxManager) context.getSystemService("dropbox");
                }
                if (dropBoxManager != null && dropBoxManager.isTagEnabled("system_app_crash")) {
                    dropBoxManager.addText("system_app_crash", getCrashString(context, str, str2, i));
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    public static synchronized Throwable filterThrowableInternal(Throwable th) {
        Throwable filterThrowableInternal;
        synchronized (CrashUtils.class) {
            filterThrowableInternal = filterThrowableInternal(th, true);
        }
        return filterThrowableInternal;
    }

    static synchronized String getCrashString(Context context, String str, String str2, int i) {
        String crashString;
        synchronized (CrashUtils.class) {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            crashString = getCrashString(context, str, str2, i, runningAppProcessInfo);
        }
        return crashString;
    }

    private static boolean isGoogleStackElement(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith(":com.google.android.gms");
    }

    private static boolean isInGmsProcess() {
        return isUnderTest ? testIsGmsProcess : ClientLibraryUtils.isPackageSide();
    }

    private static boolean isPackageSide() {
        return isUnderTest ? testIsPackageSide : BuildConstants.IS_PACKAGE_SIDE;
    }

    public static boolean isSystemClassPrefixInternal(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str2 : SYSTEM_CLASS_PREFIXES) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    public static synchronized Throwable filterThrowableInternal(Throwable th, boolean z) {
        boolean z2;
        synchronized (CrashUtils.class) {
            LinkedList linkedList = new LinkedList();
            for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
                linkedList.push(th2);
            }
            Throwable th3 = null;
            boolean z3 = false;
            while (!linkedList.isEmpty()) {
                Throwable th4 = (Throwable) linkedList.pop();
                StackTraceElement[] stackTrace = th4.getStackTrace();
                ArrayList arrayList = new ArrayList();
                arrayList.add(new StackTraceElement(th4.getClass().getName(), "<filtered>", "<filtered>", 1));
                for (StackTraceElement stackTraceElement : stackTrace) {
                    String className = stackTraceElement.getClassName();
                    if (!isGoogleStackElement(stackTraceElement.getFileName()) && !isInGmsProcess()) {
                        z2 = false;
                        z3 |= z2;
                        if (!z2 && !isSystemClassPrefixInternal(className)) {
                            stackTraceElement = new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1);
                        }
                        arrayList.add(stackTraceElement);
                    }
                    z2 = true;
                    z3 |= z2;
                    if (!z2) {
                        stackTraceElement = new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1);
                    }
                    arrayList.add(stackTraceElement);
                }
                String th5 = z ? "<filtered>" : th4.toString();
                if (th3 == null) {
                    th3 = new Throwable(th5);
                } else {
                    th3 = new Throwable(th5, th3);
                }
                th3.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
            }
            if (z3) {
                return th3;
            }
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0086 A[Catch: all -> 0x0210, TryCatch #1 {, blocks: (B:4:0x0003, B:7:0x0055, B:8:0x0063, B:10:0x0067, B:17:0x006f, B:19:0x0077, B:21:0x0086, B:23:0x008e, B:25:0x0096, B:27:0x009e, B:29:0x00b3, B:31:0x00c8, B:32:0x00d7, B:36:0x00e7, B:40:0x00f4, B:42:0x0114, B:44:0x011b, B:45:0x012a, B:47:0x0135, B:48:0x0138, B:51:0x0141, B:55:0x0153, B:60:0x015d, B:77:0x01e4, B:93:0x0202, B:97:0x0208, B:90:0x01fc, B:98:0x0209, B:54:0x014b), top: B:107:0x0003, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0114 A[Catch: all -> 0x0210, TryCatch #1 {, blocks: (B:4:0x0003, B:7:0x0055, B:8:0x0063, B:10:0x0067, B:17:0x006f, B:19:0x0077, B:21:0x0086, B:23:0x008e, B:25:0x0096, B:27:0x009e, B:29:0x00b3, B:31:0x00c8, B:32:0x00d7, B:36:0x00e7, B:40:0x00f4, B:42:0x0114, B:44:0x011b, B:45:0x012a, B:47:0x0135, B:48:0x0138, B:51:0x0141, B:55:0x0153, B:60:0x015d, B:77:0x01e4, B:93:0x0202, B:97:0x0208, B:90:0x01fc, B:98:0x0209, B:54:0x014b), top: B:107:0x0003, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x011b A[Catch: all -> 0x0210, TryCatch #1 {, blocks: (B:4:0x0003, B:7:0x0055, B:8:0x0063, B:10:0x0067, B:17:0x006f, B:19:0x0077, B:21:0x0086, B:23:0x008e, B:25:0x0096, B:27:0x009e, B:29:0x00b3, B:31:0x00c8, B:32:0x00d7, B:36:0x00e7, B:40:0x00f4, B:42:0x0114, B:44:0x011b, B:45:0x012a, B:47:0x0135, B:48:0x0138, B:51:0x0141, B:55:0x0153, B:60:0x015d, B:77:0x01e4, B:93:0x0202, B:97:0x0208, B:90:0x01fc, B:98:0x0209, B:54:0x014b), top: B:107:0x0003, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0135 A[Catch: all -> 0x0210, TryCatch #1 {, blocks: (B:4:0x0003, B:7:0x0055, B:8:0x0063, B:10:0x0067, B:17:0x006f, B:19:0x0077, B:21:0x0086, B:23:0x008e, B:25:0x0096, B:27:0x009e, B:29:0x00b3, B:31:0x00c8, B:32:0x00d7, B:36:0x00e7, B:40:0x00f4, B:42:0x0114, B:44:0x011b, B:45:0x012a, B:47:0x0135, B:48:0x0138, B:51:0x0141, B:55:0x0153, B:60:0x015d, B:77:0x01e4, B:93:0x0202, B:97:0x0208, B:90:0x01fc, B:98:0x0209, B:54:0x014b), top: B:107:0x0003, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x015d A[Catch: all -> 0x0210, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0003, B:7:0x0055, B:8:0x0063, B:10:0x0067, B:17:0x006f, B:19:0x0077, B:21:0x0086, B:23:0x008e, B:25:0x0096, B:27:0x009e, B:29:0x00b3, B:31:0x00c8, B:32:0x00d7, B:36:0x00e7, B:40:0x00f4, B:42:0x0114, B:44:0x011b, B:45:0x012a, B:47:0x0135, B:48:0x0138, B:51:0x0141, B:55:0x0153, B:60:0x015d, B:77:0x01e4, B:93:0x0202, B:97:0x0208, B:90:0x01fc, B:98:0x0209, B:54:0x014b), top: B:107:0x0003, inners: #10 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static synchronized String getCrashString(Context context, String str, String str2, int i, ActivityManager.RunningAppProcessInfo runningAppProcessInfo) {
        StringBuilder sb;
        int i2;
        synchronized (CrashUtils.class) {
            sb = new StringBuilder(1024);
            sb.append("Process: ").append(Strings.nullToEmpty(str2)).append("\n");
            sb.append("PID: ").append(runningAppProcessInfo.pid).append("\n");
            sb.append("UID: ").append(runningAppProcessInfo.uid).append("\n");
            sb.append("Package: ").append("com.google.android.gms");
            int i3 = BuildConstants.APK_BUILD_VERSION_CODE;
            String str3 = BuildConstants.APK_BUILD_VERSION_NAME;
            if (isInGmsProcess()) {
                try {
                    PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(context.getPackageName(), 0);
                    i3 = packageInfo.versionCode;
                    try {
                        if (packageInfo.versionName != null) {
                            str3 = packageInfo.versionName;
                        }
                    } catch (Exception e) {
                        e = e;
                        Log.w("CrashUtils", "Error while trying to get the package information! Using static version.", e);
                        sb.append(" v").append(i3);
                        if (!TextUtils.isEmpty(str3)) {
                        }
                        sb.append("\n");
                        if (runningAppProcessInfo.importance > IMPORTANCE_FOREGROUND) {
                        }
                        sb.append("Foreground: ").append(!(runningAppProcessInfo.importance > IMPORTANCE_FOREGROUND) ? "Yes" : "No").append("\n");
                        sb.append("Build: ").append(Build.FINGERPRINT).append("\n");
                        if (Debug.isDebuggerConnected()) {
                        }
                        if (i != 0) {
                        }
                        sb.append("\n");
                        if (!TextUtils.isEmpty(str)) {
                        }
                        if (isInGmsProcess()) {
                        }
                        if (i2 > 0) {
                        }
                        return sb.toString();
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
            sb.append(" v").append(i3);
            if (!TextUtils.isEmpty(str3)) {
                if (str3.contains("(") && !str3.contains(")")) {
                    if (str3.endsWith("-")) {
                        str3 = str3 + "111111111";
                    }
                    str3 = str3 + ")";
                }
                sb.append(" (").append(str3).append(")");
            }
            sb.append("\n");
            sb.append("Foreground: ").append(!(runningAppProcessInfo.importance > IMPORTANCE_FOREGROUND) ? "Yes" : "No").append("\n");
            sb.append("Build: ").append(Build.FINGERPRINT).append("\n");
            if (Debug.isDebuggerConnected()) {
                sb.append("Debugger: Connected\n");
            }
            if (i != 0) {
                sb.append("DD-EDD: ").append(i).append("\n");
            }
            sb.append("\n");
            if (!TextUtils.isEmpty(str)) {
                sb.append(str);
            }
            if (isInGmsProcess()) {
                i2 = 0;
            } else {
                try {
                    i2 = Settings.Secure.getInt(context.getContentResolver(), "logcat_for_system_app_crash", 0);
                } catch (RuntimeException e3) {
                    Log.e("CrashUtils", "Error detecting allowed logcat lines.", e3);
                    i2 = 0;
                }
                int i4 = testLines;
                if (i4 >= 0) {
                    i2 = i4;
                }
            }
            if (i2 > 0) {
                sb.append("\n");
                InputStreamReader inputStreamReader = null;
                try {
                    try {
                        try {
                            Process start = new ProcessBuilder("/system/bin/logcat", "-v", "time", "-b", "events", "-b", "system", "-b", "main", "-b", "crash", "-t", String.valueOf(i2)).redirectErrorStream(true).start();
                            try {
                                start.getOutputStream().close();
                            } catch (IOException e4) {
                            }
                            try {
                                start.getErrorStream().close();
                            } catch (IOException e5) {
                            }
                            InputStreamReader inputStreamReader2 = new InputStreamReader(start.getInputStream());
                            try {
                                char[] cArr = new char[8192];
                                while (true) {
                                    int read = inputStreamReader2.read(cArr);
                                    if (read <= 0) {
                                        break;
                                    }
                                    sb.append(cArr, 0, read);
                                }
                                inputStreamReader2.close();
                            } catch (IOException e6) {
                                e = e6;
                                inputStreamReader = inputStreamReader2;
                                Log.e("CrashUtils", "Error running logcat", e);
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                                return sb.toString();
                            } catch (Throwable th) {
                                th = th;
                                inputStreamReader = inputStreamReader2;
                                if (inputStreamReader != null) {
                                    try {
                                        inputStreamReader.close();
                                    } catch (IOException e7) {
                                    }
                                }
                                throw th;
                            }
                        } catch (IOException e8) {
                            e = e8;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (IOException e9) {
                }
            }
        }
        return sb.toString();
    }

    public static boolean addErrorToDropBoxInternal(Context context, Throwable th, int i) {
        boolean z;
        try {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(th);
            if (isPackageSide()) {
                if (!isInGmsProcess() && (th = filterThrowableInternal(th)) == null) {
                    return false;
                }
                return addErrorToDropBoxInternal(context, Log.getStackTraceString(th), ProcessUtils.getMyProcessName(), i, th);
            }
            return false;
        } catch (Exception e) {
            try {
                z = isInGmsProcess();
            } catch (Exception e2) {
                Log.e("CrashUtils", "Error determining which process we're running in!", e2);
                z = false;
            }
            if (!z) {
                Log.e("CrashUtils", "Error adding exception to DropBox!", e);
                return false;
            }
            throw e;
        }
    }
}
