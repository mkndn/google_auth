package androidx.fragment.app.strictmode;

import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FragmentTagUsageViolation extends Violation {
    private final ViewGroup parentContainer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentTagUsageViolation(Fragment fragment, ViewGroup viewGroup) {
        super(fragment, "Attempting to use <fragment> tag to add fragment " + fragment + " to container " + viewGroup);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.parentContainer = viewGroup;
    }
}
