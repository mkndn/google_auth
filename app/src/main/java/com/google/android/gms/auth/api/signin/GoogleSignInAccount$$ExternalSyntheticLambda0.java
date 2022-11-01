package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class GoogleSignInAccount$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ GoogleSignInAccount$$ExternalSyntheticLambda0 INSTANCE = new GoogleSignInAccount$$ExternalSyntheticLambda0();

    private /* synthetic */ GoogleSignInAccount$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareTo;
        compareTo = ((Scope) obj).getScopeUri().compareTo(((Scope) obj2).getScopeUri());
        return compareTo;
    }
}
