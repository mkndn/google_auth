package com.google.android.apps.authenticator.barcode;

import com.google.android.apps.authenticator.util.permissions.PermissionRequestor;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BarcodeCaptureActivity$$InjectAdapter extends Binding implements Provider, MembersInjector {
    private Binding mPermissionRequestor;

    public BarcodeCaptureActivity$$InjectAdapter() {
        super("com.google.android.apps.authenticator.barcode.BarcodeCaptureActivity", "members/com.google.android.apps.authenticator.barcode.BarcodeCaptureActivity", false, BarcodeCaptureActivity.class);
    }

    @Override // dagger.internal.Binding
    public void attach(Linker linker) {
        this.mPermissionRequestor = linker.requestBinding("com.google.android.apps.authenticator.util.permissions.PermissionRequestor", BarcodeCaptureActivity.class, getClass().getClassLoader());
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public BarcodeCaptureActivity get() {
        BarcodeCaptureActivity barcodeCaptureActivity = new BarcodeCaptureActivity();
        injectMembers(barcodeCaptureActivity);
        return barcodeCaptureActivity;
    }

    @Override // dagger.internal.Binding
    public void getDependencies(Set set, Set set2) {
        set2.add(this.mPermissionRequestor);
    }

    @Override // dagger.internal.Binding
    public void injectMembers(BarcodeCaptureActivity barcodeCaptureActivity) {
        barcodeCaptureActivity.mPermissionRequestor = (PermissionRequestor) this.mPermissionRequestor.get();
    }
}
