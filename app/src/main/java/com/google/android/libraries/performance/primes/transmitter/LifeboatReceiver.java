package com.google.android.libraries.performance.primes.transmitter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LifeboatReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra("MetricSnapshot") && intent.hasExtra("Transmitters")) {
            try {
                MetricSnapshot parseFrom = MetricSnapshot.parseFrom((byte[]) Preconditions.checkNotNull(intent.getByteArrayExtra("MetricSnapshot")), ExtensionRegistryLite.getGeneratedRegistry());
                final BroadcastReceiver.PendingResult goAsync = goAsync();
                String[] strArr = (String[]) Preconditions.checkNotNull(intent.getStringArrayExtra("Transmitters"));
                ArrayList arrayList = new ArrayList(strArr.length);
                for (String str : strArr) {
                    try {
                        Constructor<?> declaredConstructor = Class.forName(str).getDeclaredConstructor(new Class[0]);
                        declaredConstructor.setAccessible(true);
                        arrayList.add(((MetricSnapshotTransmitter) declaredConstructor.newInstance(new Object[0])).transmit(context, parseFrom));
                    } catch (Throwable th) {
                        Log.e("PrimesLifeboatReceiver", String.format("Unable to transmit the crash using %s.", str), th);
                    }
                }
                ListenableFuture successfulAsList = Futures.successfulAsList(arrayList);
                goAsync.getClass();
                successfulAsList.addListener(new Runnable() { // from class: com.google.android.libraries.performance.primes.transmitter.LifeboatReceiver$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        goAsync.finish();
                    }
                }, MoreExecutors.directExecutor());
            } catch (InvalidProtocolBufferException e) {
                Log.e("PrimesLifeboatReceiver", "Unable to parse the payload of MetricSnapshot.", e);
            }
        }
    }
}
