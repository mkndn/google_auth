package com.google.protobuf;

import java.util.Arrays;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtobufArrayList extends AbstractProtobufList implements RandomAccess {
    private static final ProtobufArrayList EMPTY_LIST;
    private Object[] array;
    private int size;

    static {
        ProtobufArrayList protobufArrayList = new ProtobufArrayList(new Object[0], 0);
        EMPTY_LIST = protobufArrayList;
        protobufArrayList.makeImmutable();
    }

    ProtobufArrayList() {
        this(new Object[10], 0);
    }

    private static Object[] createArray(int i) {
        return new Object[i];
    }

    public static ProtobufArrayList emptyList() {
        return EMPTY_LIST;
    }

    private void ensureIndexInRange(int i) {
        if (i >= 0 && i < this.size) {
            return;
        }
        throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
    }

    private String makeOutOfBoundsExceptionMessage(int i) {
        return "Index:" + i + ", Size:" + this.size;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public void add(int i, Object obj) {
        int i2;
        ensureIsMutable();
        if (i >= 0 && i <= (i2 = this.size)) {
            Object[] objArr = this.array;
            if (i2 < objArr.length) {
                System.arraycopy(objArr, i, objArr, i + 1, i2 - i);
            } else {
                Object[] createArray = createArray(((i2 * 3) / 2) + 1);
                System.arraycopy(this.array, 0, createArray, 0, i);
                System.arraycopy(this.array, i, createArray, i + 1, this.size - i);
                this.array = createArray;
            }
            this.array[i] = obj;
            this.size++;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
    }

    @Override // java.util.AbstractList, java.util.List
    public Object get(int i) {
        ensureIndexInRange(i);
        return this.array[i];
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Object remove(int i) {
        int i2;
        ensureIsMutable();
        ensureIndexInRange(i);
        Object[] objArr = this.array;
        Object obj = objArr[i];
        if (i < this.size - 1) {
            System.arraycopy(objArr, i + 1, objArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return obj;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Object set(int i, Object obj) {
        ensureIsMutable();
        ensureIndexInRange(i);
        Object[] objArr = this.array;
        Object obj2 = objArr[i];
        objArr[i] = obj;
        this.modCount++;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    public ProtobufArrayList mutableCopyWithCapacity(int i) {
        if (i < this.size) {
            throw new IllegalArgumentException();
        }
        return new ProtobufArrayList(Arrays.copyOf(this.array, i), this.size);
    }

    private ProtobufArrayList(Object[] objArr, int i) {
        this.array = objArr;
        this.size = i;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Object obj) {
        ensureIsMutable();
        int i = this.size;
        Object[] objArr = this.array;
        if (i == objArr.length) {
            this.array = Arrays.copyOf(objArr, ((i * 3) / 2) + 1);
        }
        Object[] objArr2 = this.array;
        int i2 = this.size;
        this.size = i2 + 1;
        objArr2[i2] = obj;
        this.modCount++;
        return true;
    }
}
