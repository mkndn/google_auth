package com.google.android.gms.feedback.internal.common;

import android.os.Bundle;
import android.util.Pair;
import java.security.SecureRandom;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FeedbackUtils {
    @Deprecated
    public static String createDefaultSessionId() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis + "-" + Math.abs(new SecureRandom().nextLong());
    }

    public static Bundle createPsdBundle(List list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        Bundle bundle = new Bundle(size);
        for (int i = 0; i < size; i++) {
            Pair pair = (Pair) list.get(i);
            bundle.putString((String) pair.first, (String) pair.second);
        }
        return bundle;
    }
}
