package com.google.android.libraries.safesql.utils;

import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_SafeSql extends SafeSql {
    private final String query;
    private final List queryArgs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SafeSql(String str, List list) {
        if (str == null) {
            throw new NullPointerException("Null query");
        }
        this.query = str;
        if (list == null) {
            throw new NullPointerException("Null queryArgs");
        }
        this.queryArgs = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SafeSql) {
            SafeSql safeSql = (SafeSql) obj;
            return this.query.equals(safeSql.query()) && this.queryArgs.equals(safeSql.queryArgs());
        }
        return false;
    }

    @Override // com.google.android.libraries.safesql.utils.SafeSql
    public String query() {
        return this.query;
    }

    @Override // com.google.android.libraries.safesql.utils.SafeSql
    List queryArgs() {
        return this.queryArgs;
    }

    public String toString() {
        String str = this.query;
        return "SafeSql{query=" + str + ", queryArgs=" + String.valueOf(this.queryArgs) + "}";
    }

    public int hashCode() {
        return ((this.query.hashCode() ^ 1000003) * 1000003) ^ this.queryArgs.hashCode();
    }
}
