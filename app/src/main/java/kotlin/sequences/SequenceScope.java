package kotlin.sequences;

import java.util.Iterator;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class SequenceScope {
    public abstract Object yield(Object obj, Continuation continuation);

    public abstract Object yieldAll(Iterator it, Continuation continuation);

    public final Object yieldAll(Sequence sequence, Continuation continuation) {
        Object yieldAll = yieldAll(sequence.iterator(), continuation);
        return yieldAll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? yieldAll : Unit.INSTANCE;
    }
}
