package android.support.constraint.solver;

import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SolverVariable {
    public static final int STRENGTH_EQUALITY = 5;
    public static final int STRENGTH_HIGH = 3;
    public static final int STRENGTH_HIGHEST = 4;
    public static final int STRENGTH_LOW = 1;
    public static final int STRENGTH_MEDIUM = 2;
    public static final int STRENGTH_NONE = 0;
    private static int uniqueId = 1;
    public float computedValue;
    private String mName;
    Type mType;
    public int id = -1;
    int definitionId = -1;
    public int strength = 0;
    float[] strengthVector = new float[6];
    ArrayRow[] mClientEquations = new ArrayRow[8];
    int mClientEquationsCount = 0;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(Type type) {
        this.mType = type;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addClientEquation(ArrayRow arrayRow) {
        int i = 0;
        while (true) {
            int i2 = this.mClientEquationsCount;
            if (i < i2) {
                if (this.mClientEquations[i] == arrayRow) {
                    return;
                }
                i++;
            } else {
                ArrayRow[] arrayRowArr = this.mClientEquations;
                if (i2 >= arrayRowArr.length) {
                    int length = arrayRowArr.length;
                    this.mClientEquations = (ArrayRow[]) Arrays.copyOf(arrayRowArr, length + length);
                }
                ArrayRow[] arrayRowArr2 = this.mClientEquations;
                int i3 = this.mClientEquationsCount;
                arrayRowArr2[i3] = arrayRow;
                this.mClientEquationsCount = i3 + 1;
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearStrengths() {
        for (int i = 0; i < 6; i++) {
            this.strengthVector[i] = 0.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeClientEquation(ArrayRow arrayRow) {
        int i = 0;
        for (int i2 = 0; i2 < this.mClientEquationsCount; i2++) {
            if (this.mClientEquations[i2] == arrayRow) {
                while (true) {
                    int i3 = this.mClientEquationsCount;
                    if (i < (i3 - i2) - 1) {
                        ArrayRow[] arrayRowArr = this.mClientEquations;
                        int i4 = i2 + i;
                        arrayRowArr[i4] = arrayRowArr[i4 + 1];
                        i++;
                    } else {
                        this.mClientEquationsCount = i3 - 1;
                        return;
                    }
                }
            }
        }
    }

    public void reset() {
        this.mName = null;
        this.mType = Type.UNKNOWN;
        this.strength = 0;
        this.id = -1;
        this.definitionId = -1;
        this.computedValue = 0.0f;
        this.mClientEquationsCount = 0;
    }

    public void setType(Type type) {
        this.mType = type;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String strengthsToString() {
        String str = this + "[";
        for (int i = 0; i < this.strengthVector.length; i++) {
            String str2 = str + this.strengthVector[i];
            if (i < this.strengthVector.length - 1) {
                str = str2 + ", ";
            } else {
                str = str2 + "] ";
            }
        }
        return str;
    }

    public String toString() {
        return "" + this.mName;
    }
}
