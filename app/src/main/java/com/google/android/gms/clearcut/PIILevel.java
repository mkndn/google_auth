package com.google.android.gms.clearcut;

import java.util.EnumSet;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum PIILevel {
    ZWIEBACK(2),
    ANDROID_ID(4),
    GAIA(8),
    ACCOUNT_NAME(16);
    
    public static final EnumSet zwiebackOnly;
    private final int id;
    public static final EnumSet noRestrictions = EnumSet.allOf(PIILevel.class);
    public static final EnumSet deidentified = EnumSet.noneOf(PIILevel.class);

    static {
        PIILevel pIILevel;
        zwiebackOnly = EnumSet.of(pIILevel);
    }

    PIILevel(int i) {
        this.id = i;
    }

    public static int encode(EnumSet enumSet) {
        if (enumSet.equals(noRestrictions)) {
            return 0;
        }
        Iterator it = enumSet.iterator();
        int i = -1;
        while (it.hasNext()) {
            i &= ((PIILevel) it.next()).getId() ^ (-1);
        }
        return i;
    }

    public int getId() {
        return this.id;
    }
}
