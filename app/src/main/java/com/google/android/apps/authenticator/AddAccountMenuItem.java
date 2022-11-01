package com.google.android.apps.authenticator;

import android.content.Context;
import android.content.Intent;
import com.google.android.apps.authenticator.otp.EnterKeyActivity;
import com.google.android.apps.authenticator2.R;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public enum AddAccountMenuItem {
    ENTER_KEY(R.string.enroll2sv_choose_account_page_enter_key_label, R.drawable.quantum_gm_ic_keyboard_googblue_24),
    SCAN_BARCODE(R.string.enroll2sv_choose_account_page_scan_barcode_label, R.drawable.quantum_gm_ic_local_see_googblue_24);
    
    final int iconId;
    final int labelId;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.apps.authenticator.AddAccountMenuItem$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$apps$authenticator$AddAccountMenuItem;

        static {
            int[] iArr = new int[AddAccountMenuItem.values().length];
            $SwitchMap$com$google$android$apps$authenticator$AddAccountMenuItem = iArr;
            try {
                iArr[AddAccountMenuItem.ENTER_KEY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$apps$authenticator$AddAccountMenuItem[AddAccountMenuItem.SCAN_BARCODE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    AddAccountMenuItem(int i, int i2) {
        this.labelId = i;
        this.iconId = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Intent onClick(Context context) {
        switch (AnonymousClass1.$SwitchMap$com$google$android$apps$authenticator$AddAccountMenuItem[ordinal()]) {
            case 1:
                return new Intent(context, EnterKeyActivity.class);
            case 2:
                return AuthenticatorActivity.getLaunchIntentActionScanBarcode(context, false);
            default:
                throw new IllegalStateException("Unexpected menu item clicked");
        }
    }
}
