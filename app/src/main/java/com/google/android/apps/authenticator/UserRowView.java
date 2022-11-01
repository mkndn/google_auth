package com.google.android.apps.authenticator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewDebug;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.apps.authenticator2.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class UserRowView extends LinearLayout {
    public UserRowView(Context context) {
        super(context);
    }

    private String getTalkBackText() {
        Context context = getContext();
        StringBuilder sb = new StringBuilder();
        CharSequence text = ((TextView) findViewById(R.id.pin_value)).getText();
        if (context.getString(R.string.empty_pin).equals(text)) {
            sb = sb.append(context.getString(R.string.counter_pin));
        } else {
            for (int i = 0; i < text.length(); i++) {
                if (sb.length() > 0) {
                    sb.append(' ');
                }
                sb.append(text.charAt(i));
            }
        }
        CharSequence text2 = ((TextView) findViewById(R.id.current_user)).getText();
        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(text2);
        return sb.toString();
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        accessibilityEvent.setClassName(getClass().getName());
        accessibilityEvent.setPackageName(getContext().getPackageName());
        accessibilityEvent.getText().add(getTalkBackText());
        return true;
    }

    @Override // android.view.View
    @ViewDebug.ExportedProperty(category = "accessibility")
    public CharSequence getContentDescription() {
        return getTalkBackText();
    }

    public UserRowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
