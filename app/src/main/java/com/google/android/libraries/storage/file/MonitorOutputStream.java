package com.google.android.libraries.storage.file;

import android.net.Uri;
import com.google.android.libraries.storage.file.common.internal.ForwardingOutputStream;
import com.google.android.libraries.storage.file.common.internal.Preconditions;
import com.google.android.libraries.storage.file.spi.Monitor;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
final class MonitorOutputStream extends ForwardingOutputStream {
    private final List outputMonitors;

    private MonitorOutputStream(OutputStream outputStream, List list) {
        super(outputStream);
        this.outputMonitors = list;
        Preconditions.checkArgument(outputStream != null, "Output was null", new Object[0]);
    }

    public static MonitorOutputStream newInstanceForWrite(List list, Uri uri, OutputStream outputStream) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Monitor.OutputMonitor monitorWrite = ((Monitor) it.next()).monitorWrite(uri);
            if (monitorWrite != null) {
                arrayList.add(monitorWrite);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new MonitorOutputStream(outputStream, arrayList);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        for (Monitor.OutputMonitor outputMonitor : this.outputMonitors) {
            try {
                outputMonitor.close();
            } catch (Throwable th) {
            }
        }
        super.close();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) {
        this.out.write(i);
        byte[] bArr = {(byte) i};
        for (Monitor.OutputMonitor outputMonitor : this.outputMonitors) {
            outputMonitor.bytesWritten(bArr, 0, 1);
        }
    }

    @Override // com.google.android.libraries.storage.file.common.internal.ForwardingOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) {
        this.out.write(bArr);
        for (Monitor.OutputMonitor outputMonitor : this.outputMonitors) {
            outputMonitor.bytesWritten(bArr, 0, bArr.length);
        }
    }

    @Override // com.google.android.libraries.storage.file.common.internal.ForwardingOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
        for (Monitor.OutputMonitor outputMonitor : this.outputMonitors) {
            outputMonitor.bytesWritten(bArr, i, i2);
        }
    }
}
