package androidx.core.view;

import android.view.View;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewKt {
    public static final Sequence getAllViews(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return SequencesKt.sequence(new ViewKt$allViews$1(view, null));
    }
}
