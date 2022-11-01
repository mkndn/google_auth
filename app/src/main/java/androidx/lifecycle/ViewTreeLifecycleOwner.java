package androidx.lifecycle;

import android.view.View;
import androidx.lifecycle.runtime.R$id;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ViewTreeLifecycleOwner {
    public static void set(View view, LifecycleOwner lifecycleOwner) {
        view.setTag(R$id.view_tree_lifecycle_owner, lifecycleOwner);
    }
}
