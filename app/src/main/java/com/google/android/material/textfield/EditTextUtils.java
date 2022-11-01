package com.google.android.material.textfield;

import android.widget.EditText;

/* compiled from: PG */
/* loaded from: classes.dex */
class EditTextUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isEditable(EditText editText) {
        return editText.getInputType() != 0;
    }
}
