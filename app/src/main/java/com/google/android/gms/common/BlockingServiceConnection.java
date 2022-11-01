package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BlockingServiceConnection implements ServiceConnection {
    boolean mUsed = false;
    private final BlockingQueue mBlockingQueue = new LinkedBlockingQueue();

    public IBinder getService() {
        Preconditions.checkNotMainThread("BlockingServiceConnection.getService() called on main thread");
        if (this.mUsed) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.mUsed = true;
        return (IBinder) this.mBlockingQueue.take();
    }

    public IBinder getServiceWithTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkNotMainThread("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if (this.mUsed) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.mUsed = true;
        IBinder iBinder = (IBinder) this.mBlockingQueue.poll(j, timeUnit);
        if (iBinder == null) {
            throw new TimeoutException("Timed out waiting for the service connection");
        }
        return iBinder;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.mBlockingQueue.add(iBinder);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
    }
}
