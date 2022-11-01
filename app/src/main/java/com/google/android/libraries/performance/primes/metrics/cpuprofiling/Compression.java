package com.google.android.libraries.performance.primes.metrics.cpuprofiling;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterInputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Compression {
    public static void compressBytes(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[1024];
        DeflaterInputStream deflaterInputStream = new DeflaterInputStream(inputStream, new Deflater(9));
        while (true) {
            try {
                int read = deflaterInputStream.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                } else {
                    deflaterInputStream.close();
                    return;
                }
            } catch (Throwable th) {
                try {
                    deflaterInputStream.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
                throw th;
            }
        }
    }
}
