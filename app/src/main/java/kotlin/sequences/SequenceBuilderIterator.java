package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class SequenceBuilderIterator extends SequenceScope implements Iterator, Continuation {
    private Iterator nextIterator;
    private Continuation nextStep;
    private Object nextValue;
    private int state;

    private final Throwable exceptionalState() {
        switch (this.state) {
            case 4:
                return new NoSuchElementException();
            case 5:
                return new IllegalStateException("Iterator has failed.");
            default:
                return new IllegalStateException("Unexpected state of the iterator: " + this.state);
        }
    }

    private final Object nextNotReady() {
        if (hasNext()) {
            return next();
        }
        throw new NoSuchElementException();
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (true) {
            switch (this.state) {
                case 0:
                    break;
                case 1:
                    Iterator it = this.nextIterator;
                    Intrinsics.checkNotNull(it);
                    if (!it.hasNext()) {
                        this.nextIterator = null;
                        break;
                    } else {
                        this.state = 2;
                        return true;
                    }
                case 2:
                case 3:
                    return true;
                case 4:
                    return false;
                default:
                    throw exceptionalState();
            }
            this.state = 5;
            Continuation continuation = this.nextStep;
            Intrinsics.checkNotNull(continuation);
            this.nextStep = null;
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m692constructorimpl(Unit.INSTANCE));
        }
    }

    @Override // java.util.Iterator
    public Object next() {
        switch (this.state) {
            case 0:
            case 1:
                return nextNotReady();
            case 2:
                this.state = 1;
                Iterator it = this.nextIterator;
                Intrinsics.checkNotNull(it);
                return it.next();
            case 3:
                this.state = 0;
                Object obj = this.nextValue;
                this.nextValue = null;
                return obj;
            default:
                throw exceptionalState();
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        ResultKt.throwOnFailure(obj);
        this.state = 4;
    }

    public final void setNextStep(Continuation continuation) {
        this.nextStep = continuation;
    }

    @Override // kotlin.sequences.SequenceScope
    public Object yield(Object obj, Continuation continuation) {
        this.nextValue = obj;
        this.state = 3;
        this.nextStep = continuation;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutine_suspended : Unit.INSTANCE;
    }

    @Override // kotlin.sequences.SequenceScope
    public Object yieldAll(Iterator it, Continuation continuation) {
        if (it.hasNext()) {
            this.nextIterator = it;
            this.state = 2;
            this.nextStep = continuation;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutine_suspended : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
