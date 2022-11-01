package com.google.android.apps.authenticator.enroll2sv.wizard;

import java.util.Comparator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AccountNameByDomainThenUsernameComparator implements Comparator {
    @Override // java.util.Comparator
    public int compare(String str, String str2) {
        int lastIndexOf = str.lastIndexOf(64);
        int lastIndexOf2 = str2.lastIndexOf(64);
        String str3 = "";
        String substring = lastIndexOf == -1 ? "" : str.substring(lastIndexOf + 1);
        if (lastIndexOf2 != -1) {
            str3 = str2.substring(lastIndexOf2 + 1);
        }
        if (lastIndexOf != -1) {
            str = str.substring(0, lastIndexOf);
        }
        if (lastIndexOf2 != -1) {
            str2 = str2.substring(0, lastIndexOf2);
        }
        int compareToIgnoreCase = substring.compareToIgnoreCase(str3);
        if (compareToIgnoreCase != 0) {
            return compareToIgnoreCase;
        }
        return str.compareToIgnoreCase(str2);
    }
}
