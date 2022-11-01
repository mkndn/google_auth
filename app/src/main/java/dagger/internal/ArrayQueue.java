package dagger.internal;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ArrayQueue extends AbstractCollection implements Queue, Cloneable, Serializable {
    private static final long serialVersionUID = 2340985798034038923L;
    private transient Object[] elements = new Object[16];
    private transient int head;
    private transient int tail;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class QueueIterator implements Iterator {
        private int cursor;
        private int fence;
        private int lastRet;

        private QueueIterator() {
            this.cursor = ArrayQueue.this.head;
            this.fence = ArrayQueue.this.tail;
            this.lastRet = -1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.cursor != this.fence;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (this.cursor == this.fence) {
                throw new NoSuchElementException();
            }
            Object obj = ArrayQueue.this.elements[this.cursor];
            if (ArrayQueue.this.tail == this.fence && obj != null) {
                int i = this.cursor;
                this.lastRet = i;
                this.cursor = (i + 1) & (ArrayQueue.this.elements.length - 1);
                return obj;
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.Iterator
        public void remove() {
            int i = this.lastRet;
            if (i < 0) {
                throw new IllegalStateException();
            }
            if (ArrayQueue.this.delete(i)) {
                this.cursor = (this.cursor - 1) & (ArrayQueue.this.elements.length - 1);
                this.fence = ArrayQueue.this.tail;
            }
            this.lastRet = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean delete(int i) {
        Object[] objArr = this.elements;
        int length = objArr.length - 1;
        int i2 = this.head;
        int i3 = this.tail;
        int i4 = (i - i2) & length;
        int i5 = (i3 - i) & length;
        if (i4 < ((i3 - i2) & length)) {
            if (i4 < i5) {
                if (i2 <= i) {
                    System.arraycopy(objArr, i2, objArr, i2 + 1, i4);
                } else {
                    System.arraycopy(objArr, 0, objArr, 1, i);
                    objArr[0] = objArr[length];
                    System.arraycopy(objArr, i2, objArr, i2 + 1, length - i2);
                }
                objArr[i2] = null;
                this.head = (i2 + 1) & length;
                return false;
            }
            if (i < i3) {
                System.arraycopy(objArr, i + 1, objArr, i, i5);
                this.tail = i3 - 1;
            } else {
                System.arraycopy(objArr, i + 1, objArr, i, length - i);
                objArr[length] = objArr[0];
                System.arraycopy(objArr, 1, objArr, 0, i3);
                this.tail = (i3 - 1) & length;
            }
            return true;
        }
        throw new ConcurrentModificationException();
    }

    private void doubleCapacity() {
        int i = this.head;
        Object[] objArr = this.elements;
        int length = objArr.length;
        int i2 = length - i;
        int i3 = length + length;
        if (i3 < 0) {
            throw new IllegalStateException("Sorry, queue too big");
        }
        Object[] objArr2 = new Object[i3];
        System.arraycopy(objArr, i, objArr2, 0, i2);
        System.arraycopy(this.elements, 0, objArr2, i2, i);
        this.elements = objArr2;
        this.head = 0;
        this.tail = length;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        allocateElements(readInt);
        this.head = 0;
        this.tail = readInt;
        for (int i = 0; i < readInt; i++) {
            this.elements[i] = objectInputStream.readObject();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        int length = this.elements.length - 1;
        for (int i = this.head; i != this.tail; i = (i + 1) & length) {
            objectOutputStream.writeObject(this.elements[i]);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Queue
    public boolean add(Object obj) {
        if (obj == null) {
            throw new NullPointerException("e == null");
        }
        Object[] objArr = this.elements;
        int i = this.tail;
        objArr[i] = obj;
        int length = (objArr.length - 1) & (i + 1);
        this.tail = length;
        if (length == this.head) {
            doubleCapacity();
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        int i = this.head;
        int i2 = this.tail;
        if (i != i2) {
            this.tail = 0;
            this.head = 0;
            int length = this.elements.length - 1;
            do {
                this.elements[i] = null;
                i = (i + 1) & length;
            } while (i != i2);
        }
    }

    /* renamed from: clone */
    public ArrayQueue m683clone() {
        try {
            ArrayQueue arrayQueue = (ArrayQueue) super.clone();
            Object[] objArr = (Object[]) Array.newInstance(this.elements.getClass().getComponentType(), this.elements.length);
            Object[] objArr2 = this.elements;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            arrayQueue.elements = objArr;
            return arrayQueue;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        int length = this.elements.length - 1;
        int i = this.head;
        while (true) {
            Object obj2 = this.elements[i];
            if (obj2 == null) {
                return false;
            }
            if (!obj.equals(obj2)) {
                i = (i + 1) & length;
            } else {
                return true;
            }
        }
    }

    @Override // java.util.Queue
    public Object element() {
        Object obj = this.elements[this.head];
        if (obj == null) {
            throw new NoSuchElementException();
        }
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.head == this.tail;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new QueueIterator();
    }

    @Override // java.util.Queue
    public boolean offer(Object obj) {
        return add(obj);
    }

    @Override // java.util.Queue
    public Object peek() {
        return this.elements[this.head];
    }

    @Override // java.util.Queue
    public Object poll() {
        int i = this.head;
        Object[] objArr = this.elements;
        Object obj = objArr[i];
        if (obj == null) {
            return null;
        }
        objArr[i] = null;
        this.head = (i + 1) & (objArr.length - 1);
        return obj;
    }

    @Override // java.util.Queue
    public Object remove() {
        Object poll = poll();
        if (poll == null) {
            throw new NoSuchElementException();
        }
        return poll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return (this.tail - this.head) & (this.elements.length - 1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    private void allocateElements(int i) {
        int i2 = 8;
        if (i >= 8) {
            int i3 = i | (i >>> 1);
            int i4 = i3 | (i3 >>> 2);
            int i5 = i4 | (i4 >>> 4);
            int i6 = i5 | (i5 >>> 8);
            i2 = (i6 | (i6 >>> 16)) + 1;
            if (i2 < 0) {
                i2 >>>= 1;
            }
        }
        this.elements = new Object[i2];
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        int size = size();
        if (objArr.length < size) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
        }
        int i = this.head;
        int i2 = this.tail;
        if (i < i2) {
            System.arraycopy(this.elements, i, objArr, 0, size());
        } else if (i > i2) {
            Object[] objArr2 = this.elements;
            int length = objArr2.length - i;
            System.arraycopy(objArr2, i, objArr, 0, length);
            System.arraycopy(this.elements, 0, objArr, length, this.tail);
        }
        if (objArr.length > size) {
            objArr[size] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        int length = this.elements.length - 1;
        int i = this.head;
        while (true) {
            Object obj2 = this.elements[i];
            if (obj2 == null) {
                return false;
            }
            if (!obj.equals(obj2)) {
                i = (i + 1) & length;
            } else {
                delete(i);
                return true;
            }
        }
    }
}
