package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Comparator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Flag extends AbstractSafeParcelable implements Comparable {
    public static final Parcelable.Creator CREATOR = new FlagCreator();
    public static final Comparator NON_VALUE_COMPARATOR = new Comparator() { // from class: com.google.android.gms.phenotype.Flag.1
        @Override // java.util.Comparator
        public int compare(Flag flag, Flag flag2) {
            if (flag.flagStorageType == flag2.flagStorageType) {
                return flag.name.compareTo(flag2.name);
            }
            return flag.flagStorageType - flag2.flagStorageType;
        }
    };
    public static final int VALUE_TYPE_BOOLEAN = 2;
    public static final int VALUE_TYPE_BYTES = 5;
    public static final int VALUE_TYPE_DOUBLE = 3;
    public static final int VALUE_TYPE_LONG = 1;
    public static final int VALUE_TYPE_STRING = 4;
    final boolean booleanValue;
    final byte[] bytesValue;
    final double doubleValue;
    public final int flagStorageType;
    public final int flagValueType;
    final long longValue;
    public final String name;
    final String stringValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Flag(String str, long j, boolean z, double d, String str2, byte[] bArr, int i, int i2) {
        this.name = str;
        this.longValue = j;
        this.booleanValue = z;
        this.doubleValue = d;
        this.stringValue = str2;
        this.bytesValue = bArr;
        this.flagValueType = i;
        this.flagStorageType = i2;
    }

    private static int compare(byte b, byte b2) {
        return b - b2;
    }

    @Override // java.lang.Comparable
    public int compareTo(Flag flag) {
        int compareTo = this.name.compareTo(flag.name);
        if (compareTo != 0) {
            return compareTo;
        }
        int compare = compare(this.flagValueType, flag.flagValueType);
        if (compare != 0) {
            return compare;
        }
        switch (this.flagValueType) {
            case 1:
                return compare(this.longValue, flag.longValue);
            case 2:
                return compare(this.booleanValue, flag.booleanValue);
            case 3:
                return Double.compare(this.doubleValue, flag.doubleValue);
            case 4:
                return compare(this.stringValue, flag.stringValue);
            case 5:
                byte[] bArr = this.bytesValue;
                byte[] bArr2 = flag.bytesValue;
                if (bArr == bArr2) {
                    return 0;
                }
                if (bArr == null) {
                    return -1;
                }
                if (bArr2 == null) {
                    return 1;
                }
                for (int i = 0; i < Math.min(this.bytesValue.length, flag.bytesValue.length); i++) {
                    int compare2 = compare(this.bytesValue[i], flag.bytesValue[i]);
                    if (compare2 != 0) {
                        return compare2;
                    }
                }
                return compare(this.bytesValue.length, flag.bytesValue.length);
            default:
                throw new AssertionError("Invalid enum value: " + this.flagValueType);
        }
    }

    public boolean equals(Object obj) {
        int i;
        if (obj instanceof Flag) {
            Flag flag = (Flag) obj;
            if (PhenotypeCore.equals(this.name, flag.name) && (i = this.flagValueType) == flag.flagValueType && this.flagStorageType == flag.flagStorageType) {
                switch (i) {
                    case 1:
                        return this.longValue == flag.longValue;
                    case 2:
                        return this.booleanValue == flag.booleanValue;
                    case 3:
                        return this.doubleValue == flag.doubleValue;
                    case 4:
                        return PhenotypeCore.equals(this.stringValue, flag.stringValue);
                    case 5:
                        return Arrays.equals(this.bytesValue, flag.bytesValue);
                    default:
                        throw new AssertionError("Invalid enum value: " + this.flagValueType);
                }
            }
            return false;
        }
        return false;
    }

    public boolean getBoolean() {
        if (this.flagValueType != 2) {
            throw new IllegalArgumentException("Not a boolean type");
        }
        return this.booleanValue;
    }

    public byte[] getBytesValue() {
        if (this.flagValueType != 5) {
            throw new IllegalArgumentException("Not a bytes type");
        }
        return (byte[]) Preconditions.checkNotNull(this.bytesValue);
    }

    public double getDouble() {
        if (this.flagValueType != 3) {
            throw new IllegalArgumentException("Not a double type");
        }
        return this.doubleValue;
    }

    public long getLong() {
        if (this.flagValueType != 1) {
            throw new IllegalArgumentException("Not a long type");
        }
        return this.longValue;
    }

    public String getString() {
        if (this.flagValueType != 4) {
            throw new IllegalArgumentException("Not a String type");
        }
        return (String) Preconditions.checkNotNull(this.stringValue);
    }

    public String toString() {
        return toString(new StringBuilder());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        FlagCreator.writeToParcel(this, parcel, i);
    }

    private static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    private static int compare(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    public String toString(StringBuilder sb) {
        sb.append("Flag(");
        sb.append(this.name);
        sb.append(", ");
        switch (this.flagValueType) {
            case 1:
                sb.append(this.longValue);
                break;
            case 2:
                sb.append(this.booleanValue);
                break;
            case 3:
                sb.append(this.doubleValue);
                break;
            case 4:
                sb.append("'");
                sb.append((String) Preconditions.checkNotNull(this.stringValue));
                sb.append("'");
                break;
            case 5:
                sb.append("'");
                sb.append(Base64.encodeToString((byte[]) Preconditions.checkNotNull(this.bytesValue), 3));
                sb.append("'");
                break;
            default:
                String str = this.name;
                throw new AssertionError("Invalid type: " + str + ", " + this.flagValueType);
        }
        sb.append(", ");
        sb.append(this.flagValueType);
        sb.append(", ");
        sb.append(this.flagStorageType);
        sb.append(")");
        return sb.toString();
    }

    private static int compare(String str, String str2) {
        if (str == str2) {
            return 0;
        }
        if (str == null) {
            return -1;
        }
        if (str2 == null) {
            return 1;
        }
        return str.compareTo(str2);
    }

    private static int compare(boolean z, boolean z2) {
        if (z == z2) {
            return 0;
        }
        return z ? 1 : -1;
    }
}
