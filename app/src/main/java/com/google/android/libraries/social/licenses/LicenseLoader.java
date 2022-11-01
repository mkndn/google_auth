package com.google.android.libraries.social.licenses;

import android.content.Context;
import androidx.loader.content.AsyncTaskLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/* compiled from: PG */
/* loaded from: classes.dex */
final class LicenseLoader extends AsyncTaskLoader {
    private List licenses;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LicenseLoader(Context context) {
        super(context.getApplicationContext());
    }

    @Override // androidx.loader.content.Loader
    protected void onStartLoading() {
        List list = this.licenses;
        if (list != null) {
            deliverResult(list);
        } else {
            forceLoad();
        }
    }

    @Override // androidx.loader.content.Loader
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override // androidx.loader.content.Loader
    public void deliverResult(List list) {
        this.licenses = list;
        super.deliverResult((Object) list);
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public List loadInBackground() {
        TreeSet treeSet = new TreeSet();
        treeSet.addAll(Licenses.getLicenses(getContext()));
        return Collections.unmodifiableList(new ArrayList(treeSet));
    }
}
