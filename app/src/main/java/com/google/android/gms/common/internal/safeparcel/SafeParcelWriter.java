package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.internal.view.SupportMenu;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SafeParcelWriter {
    public static int beginObjectHeader(Parcel parcel) {
        return beginVariableData(parcel, 20293);
    }

    private static int beginVariableData(Parcel parcel, int i) {
        parcel.writeInt(i | SupportMenu.CATEGORY_MASK);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    public static void finishObjectHeader(Parcel parcel, int i) {
        finishVariableData(parcel, i);
    }

    private static void finishVariableData(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i - 4);
        parcel.writeInt(dataPosition - i);
        parcel.setDataPosition(dataPosition);
    }

    public static void writeBoolean(Parcel parcel, int i, boolean z) {
        writeHeader(parcel, i, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    public static void writeBooleanObject(Parcel parcel, int i, Boolean bool, boolean z) {
        if (bool == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        writeHeader(parcel, i, 4);
        parcel.writeInt(bool.booleanValue() ? 1 : 0);
    }

    public static void writeBundle(Parcel parcel, int i, Bundle bundle, boolean z) {
        if (bundle == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeBundle(bundle);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeByteArray(Parcel parcel, int i, byte[] bArr, boolean z) {
        if (bArr == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeByteArray(bArr);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeByteArrayArray(Parcel parcel, int i, byte[][] bArr, boolean z) {
        if (bArr == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeInt(bArr.length);
        for (byte[] bArr2 : bArr) {
            parcel.writeByteArray(bArr2);
        }
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeDouble(Parcel parcel, int i, double d) {
        writeHeader(parcel, i, 8);
        parcel.writeDouble(d);
    }

    public static void writeDoubleObject(Parcel parcel, int i, Double d, boolean z) {
        if (d == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        writeHeader(parcel, i, 8);
        parcel.writeDouble(d.doubleValue());
    }

    private static void writeHeader(Parcel parcel, int i, int i2) {
        if (i2 >= 65535) {
            parcel.writeInt(i | SupportMenu.CATEGORY_MASK);
            parcel.writeInt(i2);
            return;
        }
        parcel.writeInt(i | (i2 << 16));
    }

    public static void writeIBinder(Parcel parcel, int i, IBinder iBinder, boolean z) {
        if (iBinder == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeStrongBinder(iBinder);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeInt(Parcel parcel, int i, int i2) {
        writeHeader(parcel, i, 4);
        parcel.writeInt(i2);
    }

    public static void writeIntArray(Parcel parcel, int i, int[] iArr, boolean z) {
        if (iArr == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeIntArray(iArr);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeIntegerObject(Parcel parcel, int i, Integer num, boolean z) {
        if (num == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        writeHeader(parcel, i, 4);
        parcel.writeInt(num.intValue());
    }

    public static void writeLong(Parcel parcel, int i, long j) {
        writeHeader(parcel, i, 8);
        parcel.writeLong(j);
    }

    public static void writeLongList(Parcel parcel, int i, List list, boolean z) {
        if (list == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        int size = list.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeLong(((Long) list.get(i2)).longValue());
        }
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeLongObject(Parcel parcel, int i, Long l, boolean z) {
        if (l == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        writeHeader(parcel, i, 8);
        parcel.writeLong(l.longValue());
    }

    public static void writeParcelable(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcelable.writeToParcel(parcel, i2);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeString(Parcel parcel, int i, String str, boolean z) {
        if (str == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeString(str);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeStringArray(Parcel parcel, int i, String[] strArr, boolean z) {
        if (strArr == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeStringArray(strArr);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeStringList(Parcel parcel, int i, List list, boolean z) {
        if (list == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeStringList(list);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeTypedArray(Parcel parcel, int i, Parcelable[] parcelableArr, int i2, boolean z) {
        if (parcelableArr == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeInt(parcelableArr.length);
        for (Parcelable parcelable : parcelableArr) {
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                writeTypedItemWithSize(parcel, parcelable, i2);
            }
        }
        finishVariableData(parcel, beginVariableData);
    }

    private static void writeTypedItemWithSize(Parcel parcel, Parcelable parcelable, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        parcelable.writeToParcel(parcel, i);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    public static void writeTypedList(Parcel parcel, int i, List list, boolean z) {
        if (list == null) {
            if (z) {
                writeHeader(parcel, i, 0);
                return;
            }
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        int size = list.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            Parcelable parcelable = (Parcelable) list.get(i2);
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                writeTypedItemWithSize(parcel, parcelable, 0);
            }
        }
        finishVariableData(parcel, beginVariableData);
    }
}
