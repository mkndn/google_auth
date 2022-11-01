package com.android.apksig.internal.apk;

import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ApkSigResult {
    public final int signatureSchemeVersion;
    public boolean verified;
    public final List mSigners = new ArrayList();
    private final List mWarnings = new ArrayList();
    private final List mErrors = new ArrayList();

    public ApkSigResult(int i) {
        this.signatureSchemeVersion = i;
    }

    public boolean containsErrors() {
        if (this.mErrors.isEmpty()) {
            if (this.mSigners.isEmpty()) {
                return false;
            }
            for (ApkSignerInfo apkSignerInfo : this.mSigners) {
                if (apkSignerInfo.containsErrors()) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public boolean containsWarnings() {
        if (this.mWarnings.isEmpty()) {
            if (this.mSigners.isEmpty()) {
                return false;
            }
            for (ApkSignerInfo apkSignerInfo : this.mSigners) {
                if (apkSignerInfo.containsWarnings()) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
