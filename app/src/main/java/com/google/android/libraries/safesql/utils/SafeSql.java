package com.google.android.libraries.safesql.utils;

import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class SafeSql {
    /* JADX INFO: Access modifiers changed from: protected */
    public static SafeSql create(String str, List list) {
        return new AutoValue_SafeSql(str, list);
    }

    public String[] args() {
        return (String[]) queryArgs().toArray(new String[0]);
    }

    public abstract String query();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract List queryArgs();
}
