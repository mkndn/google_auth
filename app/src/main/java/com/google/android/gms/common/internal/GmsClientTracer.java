package com.google.android.gms.common.internal;

import android.content.ServiceConnection;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GmsClientTracer {
    private static Delegate delegateSingleton = null;
    private static volatile boolean usedAlreadyDebugOnly;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface AlreadyTraced {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Delegate {
        boolean enableSafeUnbind();

        boolean enableThrowExceptionWhenDoubleBind();

        void getService(IGmsServiceBroker iGmsServiceBroker, IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest);

        ServiceConnection traceServiceConnection(ServiceConnection serviceConnection);
    }

    public static boolean enableSafeUnbind() {
        Delegate delegate = getDelegate();
        if (delegate == null) {
            return true;
        }
        return delegate.enableSafeUnbind();
    }

    public static boolean enableThrowExceptionWhenDoubleBind() {
        Delegate delegate = getDelegate();
        if (delegate == null) {
            return false;
        }
        return delegate.enableThrowExceptionWhenDoubleBind();
    }

    private static Delegate getDelegate() {
        if (BuildConstants.APK_IS_DEBUG_APK) {
            usedAlreadyDebugOnly = true;
        }
        return delegateSingleton;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void getService(IGmsServiceBroker iGmsServiceBroker, IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest) {
        Delegate delegate = getDelegate();
        if (delegate == null) {
            iGmsServiceBroker.getService(iGmsCallbacks, getServiceRequest);
        } else {
            delegate.getService(iGmsServiceBroker, iGmsCallbacks, getServiceRequest);
        }
    }

    public static ServiceConnection traceServiceConnection(ServiceConnection serviceConnection) {
        Delegate delegate = getDelegate();
        if (delegate == null) {
            return serviceConnection;
        }
        return delegate.traceServiceConnection(serviceConnection);
    }
}
