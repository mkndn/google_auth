package com.android.apksig.internal.apk;

import com.android.apksig.ApkVerificationIssue;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ApkSignerInfo {
    public long timestamp;
    public List certs = new ArrayList();
    public List certificateLineage = new ArrayList();
    private final List mWarnings = new ArrayList();
    private final List mErrors = new ArrayList();

    public void addWarning(int i, Object... objArr) {
        this.mWarnings.add(new ApkVerificationIssue(i, objArr));
    }

    public boolean containsErrors() {
        return !this.mErrors.isEmpty();
    }

    public boolean containsWarnings() {
        return !this.mWarnings.isEmpty();
    }

    public List getErrors() {
        return this.mErrors;
    }

    public List getWarnings() {
        return this.mWarnings;
    }
}
