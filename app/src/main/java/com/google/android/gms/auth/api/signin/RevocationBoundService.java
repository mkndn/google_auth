package com.google.android.gms.auth.api.signin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.auth.api.signin.internal.RevocationService;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RevocationBoundService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (!"com.google.android.gms.auth.api.signin.RevocationBoundService.disconnect".equals(intent.getAction()) && !"com.google.android.gms.auth.api.signin.RevocationBoundService.clearClientState".equals(intent.getAction())) {
            Log.w("RevocationService", "Unknown action sent to RevocationBoundService: " + intent.getAction());
            return null;
        }
        if (Log.isLoggable("RevocationService", 2)) {
            Log.v("RevocationService", "RevocationBoundService handling " + intent.getAction());
        }
        return new RevocationService(this);
    }
}
