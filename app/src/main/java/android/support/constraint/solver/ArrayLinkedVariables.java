package android.support.constraint.solver;

import android.support.constraint.solver.SolverVariable;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ArrayLinkedVariables {
    private final Cache mCache;
    private final ArrayRow mRow;
    int currentSize = 0;
    private int ROW_SIZE = 8;
    private SolverVariable candidate = null;
    private int[] mArrayIndices = new int[8];
    private int[] mArrayNextIndices = new int[8];
    private float[] mArrayValues = new float[8];
    private int mHead = -1;
    private int mLast = -1;
    private boolean mDidFillOnce = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayLinkedVariables(ArrayRow arrayRow, Cache cache) {
        this.mRow = arrayRow;
        this.mCache = cache;
    }

    public final void add(SolverVariable solverVariable, float f) {
        int[] iArr;
        if (f == 0.0f) {
            return;
        }
        int i = this.mHead;
        if (i == -1) {
            this.mHead = 0;
            this.mArrayValues[0] = f;
            this.mArrayIndices[0] = solverVariable.id;
            this.mArrayNextIndices[this.mHead] = -1;
            this.currentSize++;
            if (!this.mDidFillOnce) {
                this.mLast++;
                return;
            }
            return;
        }
        int i2 = -1;
        for (int i3 = 0; i != -1 && i3 < this.currentSize; i3++) {
            int i4 = this.mArrayIndices[i];
            if (i4 != solverVariable.id) {
                if (this.mArrayIndices[i] < solverVariable.id) {
                    i2 = i;
                }
                i = this.mArrayNextIndices[i];
            } else {
                float[] fArr = this.mArrayValues;
                float f2 = fArr[i] + f;
                fArr[i] = f2;
                if (f2 == 0.0f) {
                    if (i == this.mHead) {
                        this.mHead = this.mArrayNextIndices[i];
                    } else {
                        int[] iArr2 = this.mArrayNextIndices;
                        iArr2[i2] = iArr2[i];
                    }
                    this.mCache.mIndexedVariables[i4].removeClientEquation(this.mRow);
                    if (this.mDidFillOnce) {
                        this.mLast = i;
                    }
                    this.currentSize--;
                    return;
                }
                return;
            }
        }
        int i5 = this.mLast;
        int i6 = i5 + 1;
        if (!this.mDidFillOnce) {
            i5 = i6;
        } else {
            int[] iArr3 = this.mArrayIndices;
            if (iArr3[i5] != -1) {
                i5 = iArr3.length;
            }
        }
        int[] iArr4 = this.mArrayIndices;
        if (i5 >= iArr4.length && this.currentSize < iArr4.length) {
            int i7 = 0;
            while (true) {
                int[] iArr5 = this.mArrayIndices;
                if (i7 >= iArr5.length) {
                    break;
                } else if (iArr5[i7] == -1) {
                    i5 = i7;
                    break;
                } else {
                    i7++;
                }
            }
        }
        int[] iArr6 = this.mArrayIndices;
        if (i5 >= iArr6.length) {
            i5 = iArr6.length;
            int i8 = this.ROW_SIZE;
            int i9 = i8 + i8;
            this.ROW_SIZE = i9;
            this.mDidFillOnce = false;
            this.mLast = i5 - 1;
            this.mArrayValues = Arrays.copyOf(this.mArrayValues, i9);
            this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
            this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
        }
        this.mArrayIndices[i5] = solverVariable.id;
        this.mArrayValues[i5] = f;
        if (i2 != -1) {
            int[] iArr7 = this.mArrayNextIndices;
            iArr7[i5] = iArr7[i2];
            iArr7[i2] = i5;
        } else {
            this.mArrayNextIndices[i5] = this.mHead;
            this.mHead = i5;
        }
        this.currentSize++;
        if (!this.mDidFillOnce) {
            this.mLast++;
        }
        if (this.mLast >= this.mArrayIndices.length) {
            this.mDidFillOnce = true;
            this.mLast = iArr.length - 1;
        }
    }

    public final void clear() {
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.currentSize = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean containsKey(SolverVariable solverVariable) {
        int i = this.mHead;
        if (i == -1) {
            return false;
        }
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            if (this.mArrayIndices[i] != solverVariable.id) {
                i = this.mArrayNextIndices[i];
            } else {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void divideByAmount(float f) {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            float[] fArr = this.mArrayValues;
            fArr[i] = fArr[i] / f;
            i = this.mArrayNextIndices[i];
        }
    }

    public final float get(SolverVariable solverVariable) {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            if (this.mArrayIndices[i] != solverVariable.id) {
                i = this.mArrayNextIndices[i];
            } else {
                return this.mArrayValues[i];
            }
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SolverVariable getVariable(int i) {
        int i2 = this.mHead;
        for (int i3 = 0; i2 != -1 && i3 < this.currentSize; i3++) {
            if (i3 != i) {
                i2 = this.mArrayNextIndices[i2];
            } else {
                return this.mCache.mIndexedVariables[this.mArrayIndices[i2]];
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final float getVariableValue(int i) {
        int i2 = this.mHead;
        for (int i3 = 0; i2 != -1 && i3 < this.currentSize; i3++) {
            if (i3 != i) {
                i2 = this.mArrayNextIndices[i2];
            } else {
                return this.mArrayValues[i2];
            }
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void invert() {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            float[] fArr = this.mArrayValues;
            fArr[i] = -fArr[i];
            i = this.mArrayNextIndices[i];
        }
    }

    public final void put(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            remove(solverVariable);
            return;
        }
        int i = this.mHead;
        if (i == -1) {
            this.mHead = 0;
            this.mArrayValues[0] = f;
            this.mArrayIndices[0] = solverVariable.id;
            this.mArrayNextIndices[this.mHead] = -1;
            this.currentSize++;
            if (!this.mDidFillOnce) {
                this.mLast++;
                return;
            }
            return;
        }
        int i2 = -1;
        for (int i3 = 0; i != -1 && i3 < this.currentSize; i3++) {
            if (this.mArrayIndices[i] != solverVariable.id) {
                if (this.mArrayIndices[i] < solverVariable.id) {
                    i2 = i;
                }
                i = this.mArrayNextIndices[i];
            } else {
                this.mArrayValues[i] = f;
                return;
            }
        }
        int i4 = this.mLast;
        int i5 = i4 + 1;
        if (!this.mDidFillOnce) {
            i4 = i5;
        } else {
            int[] iArr = this.mArrayIndices;
            if (iArr[i4] != -1) {
                i4 = iArr.length;
            }
        }
        int[] iArr2 = this.mArrayIndices;
        if (i4 >= iArr2.length && this.currentSize < iArr2.length) {
            int i6 = 0;
            while (true) {
                int[] iArr3 = this.mArrayIndices;
                if (i6 >= iArr3.length) {
                    break;
                } else if (iArr3[i6] == -1) {
                    i4 = i6;
                    break;
                } else {
                    i6++;
                }
            }
        }
        int[] iArr4 = this.mArrayIndices;
        if (i4 >= iArr4.length) {
            i4 = iArr4.length;
            int i7 = this.ROW_SIZE;
            int i8 = i7 + i7;
            this.ROW_SIZE = i8;
            this.mDidFillOnce = false;
            this.mLast = i4 - 1;
            this.mArrayValues = Arrays.copyOf(this.mArrayValues, i8);
            this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
            this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
        }
        this.mArrayIndices[i4] = solverVariable.id;
        this.mArrayValues[i4] = f;
        if (i2 != -1) {
            int[] iArr5 = this.mArrayNextIndices;
            iArr5[i4] = iArr5[i2];
            iArr5[i2] = i4;
        } else {
            this.mArrayNextIndices[i4] = this.mHead;
            this.mHead = i4;
        }
        int i9 = this.currentSize + 1;
        this.currentSize = i9;
        if (!this.mDidFillOnce) {
            this.mLast++;
        }
        if (i9 >= this.mArrayIndices.length) {
            this.mDidFillOnce = true;
        }
    }

    public final float remove(SolverVariable solverVariable) {
        if (this.candidate == solverVariable) {
            this.candidate = null;
        }
        int i = this.mHead;
        if (i == -1) {
            return 0.0f;
        }
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.currentSize) {
            int i4 = this.mArrayIndices[i];
            if (i4 != solverVariable.id) {
                i2++;
                i3 = i;
                i = this.mArrayNextIndices[i];
            } else {
                if (i == this.mHead) {
                    this.mHead = this.mArrayNextIndices[i];
                } else {
                    int[] iArr = this.mArrayNextIndices;
                    iArr[i3] = iArr[i];
                }
                this.mCache.mIndexedVariables[i4].removeClientEquation(this.mRow);
                this.currentSize--;
                this.mArrayIndices[i] = -1;
                if (this.mDidFillOnce) {
                    this.mLast = i;
                }
                return this.mArrayValues[i];
            }
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateClientEquations(ArrayRow arrayRow) {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            this.mCache.mIndexedVariables[this.mArrayIndices[i]].addClientEquation(arrayRow);
            i = this.mArrayNextIndices[i];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateFromRow(ArrayRow arrayRow, ArrayRow arrayRow2) {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayIndices[i] == arrayRow2.variable.id) {
                float f = this.mArrayValues[i];
                remove(arrayRow2.variable);
                ArrayLinkedVariables arrayLinkedVariables = arrayRow2.variables;
                int i3 = arrayLinkedVariables.mHead;
                for (int i4 = 0; i3 != -1 && i4 < arrayLinkedVariables.currentSize; i4++) {
                    add(this.mCache.mIndexedVariables[arrayLinkedVariables.mArrayIndices[i3]], arrayLinkedVariables.mArrayValues[i3] * f);
                    i3 = arrayLinkedVariables.mArrayNextIndices[i3];
                }
                arrayRow.constantValue += arrayRow2.constantValue * f;
                arrayRow2.variable.removeClientEquation(arrayRow);
                i = this.mHead;
                i2 = 0;
            } else {
                i = this.mArrayNextIndices[i];
                i2++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateFromSystem(ArrayRow arrayRow, ArrayRow[] arrayRowArr) {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            SolverVariable solverVariable = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
            if (solverVariable.definitionId != -1) {
                float f = this.mArrayValues[i];
                remove(solverVariable);
                ArrayRow arrayRow2 = arrayRowArr[solverVariable.definitionId];
                if (!arrayRow2.isSimpleDefinition) {
                    ArrayLinkedVariables arrayLinkedVariables = arrayRow2.variables;
                    int i3 = arrayLinkedVariables.mHead;
                    for (int i4 = 0; i3 != -1 && i4 < arrayLinkedVariables.currentSize; i4++) {
                        add(this.mCache.mIndexedVariables[arrayLinkedVariables.mArrayIndices[i3]], arrayLinkedVariables.mArrayValues[i3] * f);
                        i3 = arrayLinkedVariables.mArrayNextIndices[i3];
                    }
                }
                arrayRow.constantValue += arrayRow2.constantValue * f;
                arrayRow2.variable.removeClientEquation(arrayRow);
                i = this.mHead;
                i2 = 0;
            } else {
                i = this.mArrayNextIndices[i];
                i2++;
            }
        }
    }

    public String toString() {
        int i = this.mHead;
        String str = "";
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            str = ((str + " -> ") + this.mArrayValues[i] + " : ") + this.mCache.mIndexedVariables[this.mArrayIndices[i]];
            i = this.mArrayNextIndices[i];
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0059 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public SolverVariable pickPivotCandidate() {
        int i = this.mHead;
        SolverVariable solverVariable = null;
        SolverVariable solverVariable2 = null;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            float[] fArr = this.mArrayValues;
            float f = fArr[i];
            if (f < 0.0f) {
                if (f > -0.001f) {
                    fArr[i] = 0.0f;
                    f = 0.0f;
                }
                if (f == 0.0f) {
                    SolverVariable solverVariable3 = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
                    if (solverVariable3.mType == SolverVariable.Type.UNRESTRICTED) {
                        if (f >= 0.0f) {
                            if (solverVariable2 == null) {
                                solverVariable2 = solverVariable3;
                            }
                        } else {
                            return solverVariable3;
                        }
                    } else if (f < 0.0f && (solverVariable == null || solverVariable3.strength < solverVariable.strength)) {
                        solverVariable = solverVariable3;
                    }
                }
                i = this.mArrayNextIndices[i];
            } else {
                if (f < 0.001f) {
                    fArr[i] = 0.0f;
                    f = 0.0f;
                }
                if (f == 0.0f) {
                }
                i = this.mArrayNextIndices[i];
            }
        }
        if (solverVariable2 != null) {
            return solverVariable2;
        }
        return solverVariable;
    }
}
