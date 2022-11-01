package kotlin.coroutines;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface CoroutineContext {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Element extends CoroutineContext {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Key {
    }

    Element get(Key key);
}
