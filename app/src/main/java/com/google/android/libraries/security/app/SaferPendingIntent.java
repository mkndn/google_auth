package com.google.android.libraries.security.app;

import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.google.common.base.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SaferPendingIntent {
    public static final int FLAG_IMMUTABLE;
    public static final int FLAG_MUTABLE = 1;
    public static final int FLAG_MUTABLE_ACTION = 3;
    public static final int FLAG_MUTABLE_CATEGORY = 9;
    public static final int FLAG_MUTABLE_CLIP_DATA = 17;
    public static final int FLAG_MUTABLE_DATA = 5;
    public static final ClipData SENTINEL_CLIP_DATA;

    static {
        FLAG_IMMUTABLE = Build.VERSION.SDK_INT > 22 ? 67108864 : 0;
        SENTINEL_CLIP_DATA = Build.VERSION.SDK_INT >= 16 ? ClipData.newIntent("", new Intent()) : null;
    }

    private static Intent fillUnsetProperties(Intent intent, int i, int i2) {
        Preconditions.checkArgument((i & 95) == 0, "Cannot set any dangerous parts of intent to be mutable.");
        Preconditions.checkArgument(intent.getComponent() != null, "Must set component on Intent.");
        if (isSet(i2, 1)) {
            Preconditions.checkArgument(isSet(i, 67108864) ? false : true, "Cannot set mutability flags if PendingIntent.FLAG_IMMUTABLE is set.");
        } else {
            Preconditions.checkArgument((Build.VERSION.SDK_INT < 23 || isSet(i, 67108864)) ? true : true, "Must set PendingIntent.FLAG_IMMUTABLE for SDK >= 23 if no parts of intent are mutable.");
        }
        Intent intent2 = new Intent(intent);
        if (Build.VERSION.SDK_INT < 23 || !isSet(i, 67108864)) {
            if (intent2.getPackage() == null) {
                intent2.setPackage(intent2.getComponent().getPackageName());
            }
            if (!isSet(i2, 3) && intent2.getAction() == null) {
                intent2.setAction("");
            }
            if (!isSet(i2, 9) && intent2.getCategories() == null) {
                intent2.addCategory("");
            }
            if (!isSet(i2, 5) && intent2.getData() == null) {
                intent2.setDataAndType(Uri.EMPTY, "*/*");
            }
            if (!isSet(i2, 17) && Build.VERSION.SDK_INT >= 16 && intent2.getClipData() == null) {
                intent2.setClipData(SENTINEL_CLIP_DATA);
            }
        }
        return intent2;
    }

    public static PendingIntent getActivity(Context context, int i, Intent intent, int i2) {
        return getActivityUnsafe(context, i, intent, i2, 0);
    }

    public static PendingIntent getActivityUnsafe(Context context, int i, Intent intent, int i2, int i3) {
        return PendingIntent.getActivity(context, i, fillUnsetProperties(intent, i2, i3), i2);
    }

    public static PendingIntent getBroadcast(Context context, int i, Intent intent, int i2) {
        return getBroadcastUnsafe(context, i, intent, i2, 0);
    }

    public static PendingIntent getBroadcastUnsafe(Context context, int i, Intent intent, int i2, int i3) {
        return PendingIntent.getBroadcast(context, i, fillUnsetProperties(intent, i2, i3), i2);
    }

    private static boolean isSet(int i, int i2) {
        return (i & i2) == i2;
    }
}
