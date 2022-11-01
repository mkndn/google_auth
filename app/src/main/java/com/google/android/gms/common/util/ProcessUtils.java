package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import com.google.android.gms.common.internal.Preconditions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ProcessUtils {
    private static String myProcessName = null;
    private static int myPid = 0;
    private static long uptimeStartMillis = 0;

    private static int getMyPid() {
        if (myPid == 0) {
            myPid = Process.myPid();
        }
        return myPid;
    }

    public static String getMyProcessName() {
        if (myProcessName == null) {
            myProcessName = getProcessName(getMyPid());
        }
        return myProcessName;
    }

    static String getProcessName(int i) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        String str = null;
        if (i <= 0) {
            return null;
        }
        try {
            bufferedReader = getBufferedReaderOfFile("/proc/" + i + "/cmdline");
        } catch (IOException e) {
            bufferedReader = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            str = ((String) Preconditions.checkNotNull(bufferedReader.readLine())).trim();
            IOUtils.closeQuietly(bufferedReader);
        } catch (IOException e2) {
            IOUtils.closeQuietly(bufferedReader);
            return str;
        } catch (Throwable th2) {
            bufferedReader2 = bufferedReader;
            th = th2;
            IOUtils.closeQuietly(bufferedReader2);
            throw th;
        }
        return str;
    }

    private static BufferedReader getBufferedReaderOfFile(String str) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return new BufferedReader(new FileReader(str));
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }
}
