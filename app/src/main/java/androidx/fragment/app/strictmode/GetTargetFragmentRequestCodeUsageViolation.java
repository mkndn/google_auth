package androidx.fragment.app.strictmode;

import android.support.v4.app.Fragment;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GetTargetFragmentRequestCodeUsageViolation extends TargetFragmentUsageViolation {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetTargetFragmentRequestCodeUsageViolation(Fragment fragment) {
        super(fragment, Intrinsics.stringPlus("Attempting to get target request code from fragment ", fragment));
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }
}
