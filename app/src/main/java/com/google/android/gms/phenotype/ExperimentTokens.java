package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ExperimentTokens extends AbstractSafeParcelable {
    public static final ExperimentTokens EMPTY;
    public static final byte[][] EMPTY_BYTES;
    public final byte[][] additionalDirectExperimentTokens;
    public final byte[][] alwaysCrossExperimentTokens;
    public final byte[] directExperimentToken;
    public final byte[][] gaiaCrossExperimentTokens;
    public final int[] genericDimensions;
    public final byte[][] otherCrossExperimentTokens;
    public final byte[][] pseudonymousCrossExperimentTokens;
    public final String user;
    public final int[] weakExperimentIds;
    public static final Parcelable.Creator CREATOR = new ExperimentTokensCreator();
    private static final CrossExtractor GAIA_CROSS_EXTRACTOR = new CrossExtractor() { // from class: com.google.android.gms.phenotype.ExperimentTokens.1
    };
    private static final CrossExtractor PSEUD_CROSS_EXTRACTOR = new CrossExtractor() { // from class: com.google.android.gms.phenotype.ExperimentTokens.2
    };
    private static final CrossExtractor ALWAYS_CROSS_EXTRACTOR = new CrossExtractor() { // from class: com.google.android.gms.phenotype.ExperimentTokens.3
    };
    private static final CrossExtractor OTHER_CROSS_EXTRACTOR = new CrossExtractor() { // from class: com.google.android.gms.phenotype.ExperimentTokens.4
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    interface CrossExtractor {
    }

    static {
        byte[][] bArr = new byte[0];
        EMPTY_BYTES = bArr;
        EMPTY = new ExperimentTokens("", null, bArr, bArr, bArr, bArr, null, null, null);
    }

    public ExperimentTokens(String str, byte[] bArr, byte[][] bArr2, byte[][] bArr3, byte[][] bArr4, byte[][] bArr5, int[] iArr, byte[][] bArr6, int[] iArr2) {
        this.user = str;
        this.directExperimentToken = bArr;
        this.gaiaCrossExperimentTokens = bArr2;
        this.pseudonymousCrossExperimentTokens = bArr3;
        this.alwaysCrossExperimentTokens = bArr4;
        this.otherCrossExperimentTokens = bArr5;
        this.weakExperimentIds = iArr;
        this.additionalDirectExperimentTokens = bArr6;
        this.genericDimensions = iArr2;
    }

    private static List asSortedList(int[] iArr) {
        if (iArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(i));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static List sortGenericDimensions(int[] iArr) {
        if (iArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(iArr.length >> 1);
        for (int i = 0; i < iArr.length; i += 2) {
            arrayList.add(new GenericDimension(iArr[i], iArr[i + 1]));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ExperimentTokens) {
            ExperimentTokens experimentTokens = (ExperimentTokens) obj;
            return PhenotypeCore.equals(this.user, experimentTokens.user) && Arrays.equals(this.directExperimentToken, experimentTokens.directExperimentToken) && PhenotypeCore.equals(asSortedList(this.gaiaCrossExperimentTokens), asSortedList(experimentTokens.gaiaCrossExperimentTokens)) && PhenotypeCore.equals(asSortedList(this.pseudonymousCrossExperimentTokens), asSortedList(experimentTokens.pseudonymousCrossExperimentTokens)) && PhenotypeCore.equals(asSortedList(this.alwaysCrossExperimentTokens), asSortedList(experimentTokens.alwaysCrossExperimentTokens)) && PhenotypeCore.equals(asSortedList(this.otherCrossExperimentTokens), asSortedList(experimentTokens.otherCrossExperimentTokens)) && PhenotypeCore.equals(asSortedList(this.weakExperimentIds), asSortedList(experimentTokens.weakExperimentIds)) && PhenotypeCore.equals(asSortedList(this.additionalDirectExperimentTokens), asSortedList(experimentTokens.additionalDirectExperimentTokens)) && PhenotypeCore.equals(sortGenericDimensions(this.genericDimensions), sortGenericDimensions(experimentTokens.genericDimensions));
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ExperimentTokens");
        sb.append("(");
        String str = this.user;
        sb.append(str == null ? "null" : "'" + str + "'");
        sb.append(", ");
        toString(sb, "direct=", this.directExperimentToken);
        sb.append(", ");
        toString(sb, "GAIA=", this.gaiaCrossExperimentTokens);
        sb.append(", ");
        toString(sb, "PSEUDO=", this.pseudonymousCrossExperimentTokens);
        sb.append(", ");
        toString(sb, "ALWAYS=", this.alwaysCrossExperimentTokens);
        sb.append(", ");
        toString(sb, "OTHER=", this.otherCrossExperimentTokens);
        sb.append(", ");
        sb.append("weak=");
        sb.append(Arrays.toString(this.weakExperimentIds));
        sb.append(", ");
        toString(sb, "directs=", this.additionalDirectExperimentTokens);
        sb.append(", ");
        sb.append("genDims=");
        sb.append(Arrays.toString(sortGenericDimensions(this.genericDimensions).toArray()));
        sb.append(")");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ExperimentTokensCreator.writeToParcel(this, parcel, i);
    }

    private static List asSortedList(byte[][] bArr) {
        if (bArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte[] bArr2 : bArr) {
            Preconditions.checkNotNull(bArr2);
            arrayList.add(Base64.encodeToString(bArr2, 3));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static void toString(StringBuilder sb, String str, byte[] bArr) {
        sb.append(str);
        sb.append("=");
        if (bArr == null) {
            sb.append("null");
            return;
        }
        sb.append("'");
        sb.append(Base64.encodeToString(bArr, 3));
        sb.append("'");
    }

    private static void toString(StringBuilder sb, String str, byte[][] bArr) {
        sb.append(str);
        sb.append("=");
        if (bArr == null) {
            sb.append("null");
            return;
        }
        sb.append("(");
        int length = bArr.length;
        boolean z = true;
        int i = 0;
        while (i < length) {
            byte[] bArr2 = bArr[i];
            if (!z) {
                sb.append(", ");
            }
            sb.append("'");
            Preconditions.checkNotNull(bArr2);
            sb.append(Base64.encodeToString(bArr2, 3));
            sb.append("'");
            i++;
            z = false;
        }
        sb.append(")");
    }
}
