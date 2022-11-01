package com.google.android.libraries.safesql.utils;

import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SafeSqlBuilder {
    private final StringBuilder mQueryBuilder = new StringBuilder();
    private final List mQueryArgs = new ArrayList();

    private SafeSqlBuilder() {
    }

    public static final SafeSqlBuilder builder() {
        return new SafeSqlBuilder();
    }

    public SafeSqlBuilder append(String str) {
        this.mQueryBuilder.append(str);
        return this;
    }

    public SafeSqlBuilder appendArgs(String str, Object... objArr) {
        this.mQueryBuilder.append(str);
        if (objArr != null) {
            if (this.mQueryArgs.size() + objArr.length > 999) {
                throw new IllegalArgumentException("Single SQL statements support at most 999 parameters.");
            }
            for (Object obj : objArr) {
                if (obj != null) {
                    this.mQueryArgs.add(obj.toString());
                } else {
                    throw new IllegalArgumentException("Bind argument can't be null for query" + str);
                }
            }
        }
        return this;
    }

    public SafeSql build() {
        return SafeSql.create(this.mQueryBuilder.toString(), this.mQueryArgs);
    }
}
