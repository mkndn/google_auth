package kotlin.coroutines;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Continuation {
    CoroutineContext getContext();

    void resumeWith(Object obj);
}
