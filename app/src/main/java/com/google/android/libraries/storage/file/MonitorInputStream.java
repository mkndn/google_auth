package com.google.android.libraries.storage.file;

import android.net.Uri;
import com.google.android.libraries.storage.file.common.internal.ForwardingInputStream;
import com.google.android.libraries.storage.file.common.internal.Preconditions;
import com.google.android.libraries.storage.file.spi.Monitor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
final class MonitorInputStream extends ForwardingInputStream {
    private final List inputMonitors;

    private MonitorInputStream(InputStream inputStream, List list) {
        super(inputStream);
        this.inputMonitors = list;
        Preconditions.checkArgument(inputStream != null, "Input was null", new Object[0]);
    }

    public static MonitorInputStream newInstance(List list, Uri uri, InputStream inputStream) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Monitor.InputMonitor monitorRead = ((Monitor) it.next()).monitorRead(uri);
            if (monitorRead != null) {
                arrayList.add(monitorRead);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new MonitorInputStream(inputStream, arrayList);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        for (Monitor.InputMonitor inputMonitor : this.inputMonitors) {
            try {
                inputMonitor.close();
            } catch (Throwable th) {
            }
        }
        super.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int read = this.in.read();
        if (read != -1) {
            byte[] bArr = {(byte) read};
            for (Monitor.InputMonitor inputMonitor : this.inputMonitors) {
                inputMonitor.bytesRead(bArr, 0, 1);
            }
        }
        return read;
    }

    @Override // com.google.android.libraries.storage.file.common.internal.ForwardingInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        int read = this.in.read(bArr);
        if (read != -1) {
            for (Monitor.InputMonitor inputMonitor : this.inputMonitors) {
                inputMonitor.bytesRead(bArr, 0, read);
            }
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int read = this.in.read(bArr, i, i2);
        if (read != -1) {
            for (Monitor.InputMonitor inputMonitor : this.inputMonitors) {
                inputMonitor.bytesRead(bArr, i, read);
            }
        }
        return read;
    }
}
