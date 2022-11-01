package dagger.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class Memoizer {
    private final Map map = new HashMap();
    private final Lock readLock;
    private final Lock writeLock;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Memoizer() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.readLock = reentrantReadWriteLock.readLock();
        this.writeLock = reentrantReadWriteLock.writeLock();
    }

    abstract Object create(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object get(Object obj) {
        if (obj == null) {
            throw new NullPointerException("key == null");
        }
        this.readLock.lock();
        try {
            Object obj2 = this.map.get(obj);
            if (obj2 == null) {
                this.readLock.unlock();
                Object create = create(obj);
                if (create == null) {
                    throw new NullPointerException("create returned null");
                }
                this.writeLock.lock();
                try {
                    this.map.put(obj, create);
                    return create;
                } finally {
                    this.writeLock.unlock();
                }
            }
            return obj2;
        } finally {
            this.readLock.unlock();
        }
    }

    public final String toString() {
        this.readLock.lock();
        try {
            return this.map.toString();
        } finally {
            this.readLock.unlock();
        }
    }
}
