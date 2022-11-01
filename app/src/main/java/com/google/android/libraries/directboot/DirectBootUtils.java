package com.google.android.libraries.directboot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DirectBootUtils {
    private static volatile boolean isUserUnlocked;
    private static boolean useNewUserUnlocked;
    private static UserManager userManager;

    static {
        isUserUnlocked = !supportsDirectBoot();
        useNewUserUnlocked = false;
    }

    private DirectBootUtils() {
    }

    private static boolean checkUserUnlocked(Context context) {
        boolean computeUserUnlockedWithUserManager;
        if (isUserUnlocked) {
            return true;
        }
        synchronized (DirectBootUtils.class) {
            if (isUserUnlocked) {
                return true;
            }
            if (useNewUserUnlocked) {
                computeUserUnlockedWithUserManager = computeUserUnlockedWithPackageManager(context);
            } else {
                computeUserUnlockedWithUserManager = computeUserUnlockedWithUserManager(context);
            }
            if (computeUserUnlockedWithUserManager) {
                isUserUnlocked = computeUserUnlockedWithUserManager;
            }
            return computeUserUnlockedWithUserManager;
        }
    }

    private static boolean computeUserUnlockedWithPackageManager(Context context) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(new Intent().setClassName(context, DirectBootHelperService.class.getName()), 268435968);
        return (queryIntentServices == null || queryIntentServices.isEmpty()) ? false : true;
    }

    public static Context getDeviceProtectedStorageContext(Context context) {
        if (context.isDeviceProtectedStorage()) {
            return context;
        }
        return context.createDeviceProtectedStorageContext();
    }

    public static Context getDeviceProtectedStorageContextOrFallback(Context context) {
        if (!supportsDirectBoot()) {
            return context;
        }
        return getDeviceProtectedStorageContext(context);
    }

    static UserManager getUserManager(Context context) {
        if (userManager == null) {
            userManager = (UserManager) context.getSystemService(UserManager.class);
        }
        return userManager;
    }

    public static boolean isDirectBoot(Context context) {
        return supportsDirectBoot() && !checkUserUnlocked(context);
    }

    public static boolean isUserUnlocked(Context context) {
        return !supportsDirectBoot() || checkUserUnlocked(context);
    }

    public static /* synthetic */ void lambda$runWhenUnlocked$0(AtomicBoolean atomicBoolean, Context context, BroadcastReceiver broadcastReceiver) {
        if (atomicBoolean.compareAndSet(false, true)) {
            context.unregisterReceiver(broadcastReceiver);
        }
    }

    public static /* synthetic */ Object lambda$runWhenUnlocked$1(final Runnable runnable, final Context context, final CallbackToFutureAdapter.Completer completer) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.google.android.libraries.directboot.DirectBootUtils.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (atomicBoolean.compareAndSet(false, true)) {
                    context2.unregisterReceiver(this);
                    runnable.run();
                    completer.set(null);
                }
            }
        };
        context.registerReceiver(broadcastReceiver, new IntentFilter("android.intent.action.USER_UNLOCKED"));
        if (isUserUnlocked(context) && atomicBoolean.compareAndSet(false, true)) {
            context.unregisterReceiver(broadcastReceiver);
            runnable.run();
            completer.set(null);
            return "DirectBootUtils.runWhenUnlocked";
        }
        completer.addCancellationListener(new Runnable() { // from class: com.google.android.libraries.directboot.DirectBootUtils$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DirectBootUtils.lambda$runWhenUnlocked$0(atomicBoolean, context, broadcastReceiver);
            }
        }, MoreExecutors.directExecutor());
        return "DirectBootUtils.runWhenUnlocked";
    }

    public static ListenableFuture runWhenUnlocked(final Context context, final Runnable runnable) {
        if (isUserUnlocked(context)) {
            runnable.run();
            return Futures.immediateVoidFuture();
        }
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: com.google.android.libraries.directboot.DirectBootUtils$$ExternalSyntheticLambda0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return DirectBootUtils.lambda$runWhenUnlocked$1(runnable, context, completer);
            }
        });
    }

    public static boolean supportsDirectBoot() {
        return Build.VERSION.SDK_INT >= 24;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0032, code lost:
        if (r0 == false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0034, code lost:
        com.google.android.libraries.directboot.DirectBootUtils.userManager = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0036, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean computeUserUnlockedWithUserManager(Context context) {
        boolean z = true;
        int i = 1;
        while (true) {
            if (i <= 2) {
                UserManager userManager2 = getUserManager(context);
                if (userManager2 == null) {
                    return true;
                }
                try {
                    if (userManager2.isUserUnlocked()) {
                        break;
                    } else if (userManager2.isUserRunning(Process.myUserHandle())) {
                        z = false;
                    }
                } catch (NullPointerException e) {
                    Log.w("DirectBootUtils", "Failed to check if user is unlocked.", e);
                    userManager = null;
                    i++;
                }
            } else {
                z = false;
                break;
            }
        }
    }
}
