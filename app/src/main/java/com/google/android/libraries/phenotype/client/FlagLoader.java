package com.google.android.libraries.phenotype.client;

import android.os.Binder;

/* compiled from: PG */
/* loaded from: classes.dex */
interface FlagLoader {

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.phenotype.client.FlagLoader$-CC  reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static Object executeBinderAware(BinderAwareFunction binderAwareFunction) {
            try {
                return binderAwareFunction.execute();
            } catch (SecurityException e) {
                long clearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    return binderAwareFunction.execute();
                } finally {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface BinderAwareFunction {
        Object execute();
    }

    Object getFlag(String str);
}
