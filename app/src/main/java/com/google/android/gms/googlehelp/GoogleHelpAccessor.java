package com.google.android.gms.googlehelp;

import com.google.android.gms.feedback.BaseFeedbackProductSpecificData;
import com.google.android.gms.googlehelp.internal.common.TogglingData;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleHelpAccessor {
    private final GoogleHelp googleHelp;

    public GoogleHelpAccessor(GoogleHelp googleHelp) {
        this.googleHelp = googleHelp;
    }

    public BaseFeedbackProductSpecificData getFeedbackPsd() {
        return this.googleHelp.getFeedbackPsd();
    }

    public BaseHelpProductSpecificData getHelpPsd() {
        return this.googleHelp.getHelpPsd();
    }

    public int getSyncHelpPsdTimeoutMs() {
        return this.googleHelp.getSyncHelpPsdTimeoutMs();
    }

    @Deprecated
    public TogglingData getTogglingData() {
        return this.googleHelp.getTogglingData();
    }

    public GoogleHelpAccessor setClientVersion(int i) {
        this.googleHelp.setClientVersion(i);
        return this;
    }

    public GoogleHelpAccessor setHasFeedbackPsd(boolean z) {
        this.googleHelp.setHasFeedbackPsd(z);
        return this;
    }

    public GoogleHelpAccessor setHasHelpPsd(boolean z) {
        this.googleHelp.setHasHelpPsd(z);
        return this;
    }

    public GoogleHelpAccessor setSyncPsd(List list) {
        this.googleHelp.setSyncPsd(list);
        return this;
    }
}
