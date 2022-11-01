package com.google.android.libraries.performance.primes.metriccapture;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.os.Process;
import com.google.common.base.Preconditions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessStats {
    private static volatile ActivityManager activityManager = null;
    static volatile boolean callingProcessForegroundNotionEnabled;

    private ProcessStats() {
    }

    public static ActivityManager getActivityManager(Context context) {
        if (activityManager == null) {
            synchronized (ProcessStats.class) {
                if (activityManager == null) {
                    activityManager = (ActivityManager) Preconditions.checkNotNull(context.getSystemService("activity"));
                }
            }
        }
        return activityManager;
    }

    public static String getCurrentProcessName() {
        return getProcessNameFromProcFile(Process.myPid());
    }

    private static String getProcessNameFromProcFile(int i) {
        BufferedReader bufferedReader;
        String str = null;
        str = null;
        str = null;
        BufferedReader bufferedReader2 = null;
        if (i <= 0) {
            return null;
        }
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/" + i + "/cmdline"));
            try {
                str = ((String) Preconditions.checkNotNull(bufferedReader.readLine())).trim();
                bufferedReader.close();
            } catch (IOException e) {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return str;
            } catch (Throwable th) {
                th = th;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e2) {
                    }
                }
                throw th;
            }
        } catch (IOException e3) {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
        return str;
    }

    public static String getShortProcessName(Context context) {
        return getShortProcessName(context.getPackageName(), getCurrentProcessName());
    }

    public static boolean isAppInForeground(Context context) {
        if (callingProcessForegroundNotionEnabled) {
            return isMyProcessInForeground(context);
        }
        return isAnyAppProcessInForeground(context);
    }

    public static boolean isMyProcessInForeground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = getActivityManager(context).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == Process.myPid()) {
                return runningAppProcessInfo.importance == 100;
            }
        }
        return false;
    }

    public static String getShortProcessName(String str, String str2) {
        if (str2 != null && str != null) {
            if (str2.startsWith(str)) {
                int length = str.length();
                if (str2.length() == length) {
                    return null;
                }
                return str2.substring(length + 1);
            }
            return str2;
        }
        return str2;
    }

    public static boolean isAnyAppProcessInForeground(Context context) {
        boolean z;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) Preconditions.checkNotNull(context.getSystemService("activity"))).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            String packageName = context.getPackageName();
            String str = packageName + ":";
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100 && (runningAppProcessInfo.processName.equals(packageName) || runningAppProcessInfo.processName.startsWith(str))) {
                    if (Build.VERSION.SDK_INT < 23) {
                        z = isScreenOn(context);
                    } else {
                        z = true;
                    }
                    if (z) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public static boolean isScreenOn(Context context) {
        PowerManager powerManager = (PowerManager) Preconditions.checkNotNull(context.getSystemService("power"));
        if (Build.VERSION.SDK_INT >= 20) {
            return powerManager.isInteractive();
        }
        return powerManager.isScreenOn();
    }
}
