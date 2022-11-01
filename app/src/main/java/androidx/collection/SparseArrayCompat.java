package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SparseArrayCompat implements Cloneable {
    private static final Companion Companion = new Companion(null);
    @Deprecated
    private static final Object DELETED = new Object();
    private boolean garbage;
    private int[] keys;
    private int size;
    private Object[] values;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SparseArrayCompat() {
        this(0, 1, null);
    }

    public SparseArrayCompat(int i) {
        if (i == 0) {
            this.keys = ContainerHelpersKt.EMPTY_INTS;
            this.values = ContainerHelpersKt.EMPTY_OBJECTS;
            return;
        }
        int idealIntArraySize = ContainerHelpersKt.idealIntArraySize(i);
        this.keys = new int[idealIntArraySize];
        this.values = new Object[idealIntArraySize];
    }

    private final void gc() {
        int i = this.size;
        int[] iArr = this.keys;
        Object[] objArr = this.values;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != DELETED) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.garbage = false;
        this.size = i2;
    }

    public void append(int i, Object obj) {
        int i2 = this.size;
        if (i2 != 0 && i <= this.keys[i2 - 1]) {
            put(i, obj);
            return;
        }
        if (this.garbage && i2 >= this.keys.length) {
            gc();
        }
        int i3 = this.size;
        if (i3 >= this.keys.length) {
            int idealIntArraySize = ContainerHelpersKt.idealIntArraySize(i3 + 1);
            int[] iArr = new int[idealIntArraySize];
            Object[] objArr = new Object[idealIntArraySize];
            int[] iArr2 = this.keys;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.values;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.keys = iArr;
            this.values = objArr;
        }
        this.keys[i3] = i;
        this.values[i3] = obj;
        this.size = i3 + 1;
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
    public SparseArrayCompat m10clone() {
        SparseArrayCompat sparseArrayCompat = (SparseArrayCompat) super.clone();
        sparseArrayCompat.keys = (int[]) this.keys.clone();
        sparseArrayCompat.values = (Object[]) this.values.clone();
        return sparseArrayCompat;
    }

    public int keyAt(int i) {
        if (this.garbage) {
            gc();
        }
        return this.keys[i];
    }

    public void put(int i, Object obj) {
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, i);
        if (binarySearch >= 0) {
            this.values[binarySearch] = obj;
            return;
        }
        int i2 = binarySearch ^ (-1);
        int i3 = this.size;
        if (i2 < i3) {
            Object[] objArr = this.values;
            if (objArr[i2] == DELETED) {
                this.keys[i2] = i;
                objArr[i2] = obj;
                return;
            }
        }
        if (this.garbage && i3 >= this.keys.length) {
            gc();
            i2 = ContainerHelpersKt.binarySearch(this.keys, this.size, i) ^ (-1);
        }
        int i4 = this.size;
        if (i4 >= this.keys.length) {
            int idealIntArraySize = ContainerHelpersKt.idealIntArraySize(i4 + 1);
            int[] iArr = new int[idealIntArraySize];
            Object[] objArr2 = new Object[idealIntArraySize];
            int[] iArr2 = this.keys;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.values;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.keys = iArr;
            this.values = objArr2;
        }
        int i5 = this.size;
        if (i5 - i2 != 0) {
            int[] iArr3 = this.keys;
            int i6 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i6, i5 - i2);
            Object[] objArr4 = this.values;
            System.arraycopy(objArr4, i2, objArr4, i6, this.size - i2);
        }
        this.keys[i2] = i;
        this.values[i2] = obj;
        this.size++;
    }

    public void remove(int i) {
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, i);
        if (binarySearch >= 0) {
            Object[] objArr = this.values;
            Object obj = objArr[binarySearch];
            Object obj2 = DELETED;
            if (obj != obj2) {
                objArr[binarySearch] = obj2;
                this.garbage = true;
            }
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
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "buffer.toString()");
        return sb2;
    }

    public Object valueAt(int i) {
        if (this.garbage) {
            gc();
        }
        return this.values[i];
    }

    public Object get(int i) {
        Object obj;
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, i);
        if (binarySearch >= 0 && (obj = this.values[binarySearch]) != DELETED) {
            return obj;
        }
        return null;
    }

    public Object get(int i, Object obj) {
        Object obj2;
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, i);
        return (binarySearch < 0 || (obj2 = this.values[binarySearch]) == DELETED) ? obj : obj2;
    }

    public /* synthetic */ SparseArrayCompat(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 10 : i);
    }
}
