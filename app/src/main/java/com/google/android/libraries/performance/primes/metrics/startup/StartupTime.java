package com.google.android.libraries.performance.primes.metrics.startup;

import android.os.Build;
import android.os.StrictMode;
import android.system.Os;
import android.system.OsConstants;
import com.google.common.base.Optional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StartupTime {
    private static volatile Optional processCreationMs = null;

    private static Object getJiffies() {
        Object obj = Class.forName("libcore.io.Libcore").getField("os").get(null);
        return obj.getClass().getMethod("sysconf", Integer.TYPE).invoke(obj, Integer.valueOf(((Integer) Class.forName("libcore.io.OsConstants").getField("_SC_CLK_TCK").get(null)).intValue()));
    }

    static final Optional getJiffiesPerSecond() {
        long longValue;
        if (Build.VERSION.SDK_INT >= 21) {
            longValue = Os.sysconf(OsConstants._SC_CLK_TCK);
        } else {
            try {
                longValue = ((Long) getJiffies()).longValue();
            } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | NullPointerException | SecurityException | InvocationTargetException e) {
                return Optional.absent();
            }
        }
        if (longValue > 0) {
            return Optional.of(Long.valueOf(longValue));
        }
        return Optional.absent();
    }

    public static Optional getProcessCreationMs() {
        Optional optional = processCreationMs;
        if (optional == null) {
            Optional processCreationMsInternal = getProcessCreationMsInternal();
            processCreationMs = processCreationMsInternal;
            return processCreationMsInternal;
        }
        return optional;
    }

    private static Optional getProcessCreationMsInternal() {
        Optional jiffiesPerSecond = getJiffiesPerSecond();
        if (!jiffiesPerSecond.isPresent()) {
            return Optional.absent();
        }
        Optional readStat = readStat();
        if (!readStat.isPresent()) {
            return Optional.absent();
        }
        Optional parseProcessCreationTimeJiffies = parseProcessCreationTimeJiffies((ByteBuffer) readStat.get());
        if (!parseProcessCreationTimeJiffies.isPresent()) {
            return Optional.absent();
        }
        return Optional.of(Long.valueOf(TimeUnit.SECONDS.toMillis(((Long) parseProcessCreationTimeJiffies.get()).longValue()) / ((Long) jiffiesPerSecond.get()).longValue()));
    }

    static Optional parseProcessCreationTimeJiffies(ByteBuffer byteBuffer) {
        if (skipPidCommState(byteBuffer) && skipFields(byteBuffer, 18)) {
            return parseNumericField(byteBuffer);
        }
        return Optional.absent();
    }

    private static Optional readStat() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        byte[] bArr = new byte[440];
        try {
            try {
                FileInputStream fileInputStream = new FileInputStream(new File("/proc/self/stat"));
                try {
                    int read = fileInputStream.read(bArr);
                    fileInputStream.close();
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    return Optional.of(ByteBuffer.wrap(bArr, 0, read));
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                    }
                    throw th;
                }
            } catch (IOException e) {
                Optional absent = Optional.absent();
                StrictMode.setThreadPolicy(allowThreadDiskReads);
                return absent;
            }
        } catch (Throwable th3) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th3;
        }
    }

    private static boolean skipFields(ByteBuffer byteBuffer, int i) {
        while (byteBuffer.hasRemaining() && i > 0) {
            if (byteBuffer.get() == 32) {
                i--;
            }
        }
        return i == 0;
    }

    private static boolean skipPidCommState(ByteBuffer byteBuffer) {
        boolean z;
        boolean z2;
        while (true) {
            if (byteBuffer.remaining() > 17) {
                if (byteBuffer.get() == 40) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            int i = 16;
            while (true) {
                if (i >= 0) {
                    if (byteBuffer.get(byteBuffer.position() + i) == 41) {
                        byteBuffer.position(byteBuffer.position() + i + 1);
                        z2 = true;
                        break;
                    }
                    i--;
                } else {
                    z2 = false;
                    break;
                }
            }
            if (z2 && byteBuffer.get() == 32) {
                return skipFields(byteBuffer, 1);
            }
            return false;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003d, code lost:
        return com.google.common.base.Optional.absent();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Optional parseNumericField(ByteBuffer byteBuffer) {
        boolean z = false;
        long j = 0;
        while (true) {
            if (!byteBuffer.hasRemaining()) {
                break;
            }
            byte b = byteBuffer.get();
            if (b != 32) {
                if (b < 48 || b > 57 || j > 922337203685477580L) {
                    break;
                }
                j = (j * 10) + (b - 48);
                z = true;
            } else if (z) {
                return Optional.of(Long.valueOf(j));
            }
        }
    }
}
