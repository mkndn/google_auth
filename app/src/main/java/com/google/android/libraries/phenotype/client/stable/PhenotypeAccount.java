package com.google.android.libraries.phenotype.client.stable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeAccount {
    public static final PhenotypeAccount LOGGED_OUT_ACCOUNT = new PhenotypeAccount();
    public final String name = "";
    public final String type = "__logged_out_type";

    private PhenotypeAccount() {
    }

    public static boolean isSupportedAccountType(String str) {
        if (!"com.google".equals(str) && !"com.google.work".equals(str) && !"cn.google".equals(str) && !"__logged_out_type".equals(str)) {
            return false;
        }
        return true;
    }
}
