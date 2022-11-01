package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LongSparseArray implements Cloneable {
    private boolean garbage;
    private long[] keys;
    private int size;
    private Object[] values;

    public LongSparseArray() {
        this(0, 1, null);
    }

    public LongSparseArray(int i) {
        if (i == 0) {
            this.keys = ContainerHelpersKt.EMPTY_LONGS;
            this.values = ContainerHelpersKt.EMPTY_OBJECTS;
            return;
        }
        int idealLongArraySize = ContainerHelpersKt.idealLongArraySize(i);
        this.keys = new long[idealLongArraySize];
        this.values = new Object[idealLongArraySize];
    }

    private final void gc() {
        Object obj;
        int i = this.size;
        long[] jArr = this.keys;
        Object[] objArr = this.values;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj2 = objArr[i3];
            obj = LongSparseArrayKt.DELETED;
            if (obj2 != obj) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj2;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.garbage = false;
        this.size = i2;
    }

    public void append(long j, Object obj) {
        int i = this.size;
        if (i != 0 && j <= this.keys[i - 1]) {
            put(j, obj);
            return;
        }
        if (this.garbage && i >= this.keys.length) {
            gc();
        }
        int i2 = this.size;
        if (i2 >= this.keys.length) {
            int idealLongArraySize = ContainerHelpersKt.idealLongArraySize(i2 + 1);
            long[] jArr = new long[idealLongArraySize];
            Object[] objArr = new Object[idealLongArraySize];
            long[] jArr2 = this.keys;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.values;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.keys = jArr;
            this.values = objArr;
        }
        this.keys[i2] = j;
        this.values[i2] = obj;
        this.size = i2 + 1;
    }

    public void clear() {
        int i = this.size;
        Object[] objArr = this.values;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.size = 0;
        this.garbage = false;
    }

    /* renamed from: clone */
    public LongSparseArray m9clone() {
        LongSparseArray longSparseArray = (LongSparseArray) super.clone();
        longSparseArray.keys = (long[]) this.keys.clone();
        longSparseArray.values = (Object[]) this.values.clone();
        return longSparseArray;
    }

    public int indexOfKey(long j) {
        if (this.garbage) {
            gc();
        }
        return ContainerHelpersKt.binarySearch(this.keys, this.size, j);
    }

    public long keyAt(int i) {
        boolean z = false;
        if (i >= 0 && i < this.size) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i).toString());
        }
        if (this.garbage) {
            gc();
        }
        return this.keys[i];
    }

    public void put(long j, Object obj) {
        Object obj2;
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j);
        if (binarySearch >= 0) {
            this.values[binarySearch] = obj;
            return;
        }
        int i = binarySearch ^ (-1);
        if (i < this.size) {
            Object obj3 = this.values[i];
            obj2 = LongSparseArrayKt.DELETED;
            if (obj3 == obj2) {
                this.keys[i] = j;
                this.values[i] = obj;
                return;
            }
        }
        if (this.garbage && this.size >= this.keys.length) {
            gc();
            i = ContainerHelpersKt.binarySearch(this.keys, this.size, j) ^ (-1);
        }
        int i2 = this.size;
        if (i2 >= this.keys.length) {
            int idealLongArraySize = ContainerHelpersKt.idealLongArraySize(i2 + 1);
            long[] jArr = new long[idealLongArraySize];
            Object[] objArr = new Object[idealLongArraySize];
            long[] jArr2 = this.keys;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.values;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.keys = jArr;
            this.values = objArr;
        }
        int i3 = this.size;
        if (i3 - i != 0) {
            long[] jArr3 = this.keys;
            int i4 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i4, i3 - i);
            Object[] objArr3 = this.values;
            System.arraycopy(objArr3, i, objArr3, i4, this.size - i);
        }
        this.keys[i] = j;
        this.values[i] = obj;
        this.size++;
    }

    public void remove(long j) {
        Object obj;
        Object obj2;
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j);
        if (binarySearch >= 0) {
            Object obj3 = this.values[binarySearch];
            obj = LongSparseArrayKt.DELETED;
            if (obj3 != obj) {
                Object[] objArr = this.values;
                obj2 = LongSparseArrayKt.DELETED;
                objArr[binarySearch] = obj2;
                this.garbage = true;
            }
        }
    }

    public void removeAt(int i) {
        Object obj;
        Object obj2;
        Object obj3 = this.values[i];
        obj = LongSparseArrayKt.DELETED;
        if (obj3 != obj) {
            Object[] objArr = this.values;
            obj2 = LongSparseArrayKt.DELETED;
            objArr[i] = obj2;
            this.garbage = true;
        }
    }

    public int size() {
        if (this.garbage) {
            gc();
        }
        return this.size;
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.size * 28);
        sb.append('{');
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(keyAt(i2));
            sb.append('=');
            Object valueAt = valueAt(i2);
            if (valueAt != sb) {
                sb.append(valueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    public Object valueAt(int i) {
        boolean z = false;
        if (i >= 0 && i < this.size) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + i).toString());
        }
        if (this.garbage) {
            gc();
        }
        return this.values[i];
    }

    public Object get(long j) {
        Object obj;
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j);
        if (binarySearch >= 0) {
            Object obj2 = this.values[binarySearch];
            obj = LongSparseArrayKt.DELETED;
            if (obj2 != obj) {
                return this.values[binarySearch];
            }
        }
        return null;
    }

    public Object get(long j, Object obj) {
        Object obj2;
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j);
        if (binarySearch >= 0) {
            Object obj3 = this.values[binarySearch];
            obj2 = LongSparseArrayKt.DELETED;
            if (obj3 != obj2) {
                return this.values[binarySearch];
            }
        }
        return obj;
    }

    public /* synthetic */ LongSparseArray(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 10 : i);
    }
}
