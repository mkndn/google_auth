package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ConstraintWidgetContainer extends WidgetContainer {
    static boolean ALLOW_ROOT_GROUP = true;
    public static final int OPTIMIZATION_ALL = 2;
    public static final int OPTIMIZATION_BASIC = 4;
    public static final int OPTIMIZATION_CHAIN = 8;
    public static final int OPTIMIZATION_NONE = 1;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    private Snapshot mSnapshot;
    int mWrapHeight;
    int mWrapWidth;
    protected LinearSystem mSystem = new LinearSystem();
    protected LinearSystem mBackgroundSystem = null;
    private int mHorizontalChainsSize = 0;
    private int mVerticalChainsSize = 0;
    private ConstraintWidget[] mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
    private ConstraintWidget[] mVerticalChainsArray = new ConstraintWidget[4];
    private ConstraintWidget[] mHorizontalChainsArray = new ConstraintWidget[4];
    private int mOptimizationLevel = 2;
    private boolean[] flags = new boolean[3];
    private ConstraintWidget[] mChainEnds = new ConstraintWidget[4];
    private boolean mWidthMeasuredTooSmall = false;
    private boolean mHeightMeasuredTooSmall = false;

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        int i = 0;
        while (true) {
            int i2 = this.mHorizontalChainsSize;
            if (i < i2) {
                if (this.mHorizontalChainsArray[i] == constraintWidget) {
                    return;
                }
                i++;
            } else {
                int i3 = i2 + 1;
                ConstraintWidget[] constraintWidgetArr = this.mHorizontalChainsArray;
                if (i3 >= constraintWidgetArr.length) {
                    int length = constraintWidgetArr.length;
                    this.mHorizontalChainsArray = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr, length + length);
                }
                ConstraintWidget[] constraintWidgetArr2 = this.mHorizontalChainsArray;
                int i4 = this.mHorizontalChainsSize;
                constraintWidgetArr2[i4] = constraintWidget;
                this.mHorizontalChainsSize = i4 + 1;
                return;
            }
        }
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        int i = 0;
        while (true) {
            int i2 = this.mVerticalChainsSize;
            if (i < i2) {
                if (this.mVerticalChainsArray[i] == constraintWidget) {
                    return;
                }
                i++;
            } else {
                int i3 = i2 + 1;
                ConstraintWidget[] constraintWidgetArr = this.mVerticalChainsArray;
                if (i3 >= constraintWidgetArr.length) {
                    int length = constraintWidgetArr.length;
                    this.mVerticalChainsArray = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr, length + length);
                }
                ConstraintWidget[] constraintWidgetArr2 = this.mVerticalChainsArray;
                int i4 = this.mVerticalChainsSize;
                constraintWidgetArr2[i4] = constraintWidget;
                this.mVerticalChainsSize = i4 + 1;
                return;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:207:0x04d6  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x04d8 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void applyHorizontalChain(LinearSystem linearSystem) {
        int i;
        LinearSystem linearSystem2;
        ConstraintWidget constraintWidget;
        boolean z;
        int i2;
        ConstraintWidget constraintWidget2;
        ConstraintWidget constraintWidget3;
        LinearSystem linearSystem3;
        SolverVariable solverVariable;
        SolverVariable solverVariable2;
        int i3;
        int i4;
        ConstraintWidgetContainer constraintWidgetContainer = this;
        LinearSystem linearSystem4 = linearSystem;
        int i5 = 0;
        int i6 = 0;
        while (i6 < constraintWidgetContainer.mHorizontalChainsSize) {
            ConstraintWidget constraintWidget4 = constraintWidgetContainer.mHorizontalChainsArray[i6];
            int countMatchConstraintsChainedWidgets = countMatchConstraintsChainedWidgets(linearSystem, constraintWidgetContainer.mChainEnds, constraintWidget4, 0, constraintWidgetContainer.flags);
            ConstraintWidget constraintWidget5 = constraintWidgetContainer.mChainEnds[2];
            if (constraintWidget5 == null) {
                i = i6;
                linearSystem2 = linearSystem4;
            } else if (constraintWidgetContainer.flags[1]) {
                int drawX = constraintWidget4.getDrawX();
                while (constraintWidget5 != null) {
                    linearSystem4.addEquality(constraintWidget5.mLeft.mSolverVariable, drawX);
                    ConstraintWidget constraintWidget6 = constraintWidget5.mHorizontalNextWidget;
                    drawX += constraintWidget5.mLeft.getMargin() + constraintWidget5.getWidth() + constraintWidget5.mRight.getMargin();
                    constraintWidget5 = constraintWidget6;
                }
                i = i6;
                linearSystem2 = linearSystem4;
            } else {
                boolean z2 = constraintWidget4.mHorizontalChainStyle == 0;
                boolean z3 = constraintWidget4.mHorizontalChainStyle == 2;
                boolean z4 = constraintWidgetContainer.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                int i7 = constraintWidgetContainer.mOptimizationLevel;
                if ((i7 == 2 || i7 == 8) && constraintWidgetContainer.flags[i5] && constraintWidget4.mHorizontalChainFixedPosition && !z3 && !z4 && constraintWidget4.mHorizontalChainStyle == 0) {
                    Optimizer.applyDirectResolutionHorizontalChain(constraintWidgetContainer, linearSystem4, countMatchConstraintsChainedWidgets, constraintWidget4);
                    i = i6;
                    linearSystem2 = linearSystem4;
                } else {
                    char c = 3;
                    if (countMatchConstraintsChainedWidgets != 0 && !z3) {
                        float f = 0.0f;
                        ConstraintWidget constraintWidget7 = null;
                        while (constraintWidget5 != null) {
                            if (constraintWidget5.mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                                int margin = constraintWidget5.mLeft.getMargin();
                                if (constraintWidget7 != null) {
                                    margin += constraintWidget7.mRight.getMargin();
                                }
                                linearSystem4.addGreaterThan(constraintWidget5.mLeft.mSolverVariable, constraintWidget5.mLeft.mTarget.mSolverVariable, margin, constraintWidget5.mLeft.mTarget.mOwner.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT ? 2 : 3);
                                int margin2 = constraintWidget5.mRight.getMargin();
                                if (constraintWidget5.mRight.mTarget.mOwner.mLeft.mTarget != null && constraintWidget5.mRight.mTarget.mOwner.mLeft.mTarget.mOwner == constraintWidget5) {
                                    margin2 += constraintWidget5.mRight.mTarget.mOwner.mLeft.getMargin();
                                }
                                linearSystem4.addLowerThan(constraintWidget5.mRight.mSolverVariable, constraintWidget5.mRight.mTarget.mSolverVariable, -margin2, constraintWidget5.mRight.mTarget.mOwner.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT ? 2 : 3);
                            } else {
                                f += constraintWidget5.mHorizontalWeight;
                                if (constraintWidget5.mRight.mTarget != null) {
                                    i4 = constraintWidget5.mRight.getMargin();
                                    if (constraintWidget5 != constraintWidgetContainer.mChainEnds[3]) {
                                        i4 += constraintWidget5.mRight.mTarget.mOwner.mLeft.getMargin();
                                    }
                                } else {
                                    i4 = 0;
                                }
                                linearSystem4.addGreaterThan(constraintWidget5.mRight.mSolverVariable, constraintWidget5.mLeft.mSolverVariable, i5, 1);
                                linearSystem4.addLowerThan(constraintWidget5.mRight.mSolverVariable, constraintWidget5.mRight.mTarget.mSolverVariable, -i4, 1);
                            }
                            constraintWidget7 = constraintWidget5;
                            constraintWidget5 = constraintWidget5.mHorizontalNextWidget;
                        }
                        if (countMatchConstraintsChainedWidgets == 1) {
                            ConstraintWidget constraintWidget8 = constraintWidgetContainer.mMatchConstraintsChainedWidgets[i5];
                            int margin3 = constraintWidget8.mLeft.getMargin();
                            if (constraintWidget8.mLeft.mTarget != null) {
                                margin3 += constraintWidget8.mLeft.mTarget.getMargin();
                            }
                            int margin4 = constraintWidget8.mRight.getMargin();
                            if (constraintWidget8.mRight.mTarget != null) {
                                margin4 += constraintWidget8.mRight.mTarget.getMargin();
                            }
                            SolverVariable solverVariable3 = constraintWidget4.mRight.mTarget.mSolverVariable;
                            ConstraintWidget[] constraintWidgetArr = constraintWidgetContainer.mChainEnds;
                            if (constraintWidget8 == constraintWidgetArr[3]) {
                                solverVariable3 = constraintWidgetArr[1].mRight.mTarget.mSolverVariable;
                            }
                            if (constraintWidget8.mMatchConstraintDefaultWidth == 1) {
                                linearSystem4.addGreaterThan(constraintWidget4.mLeft.mSolverVariable, constraintWidget4.mLeft.mTarget.mSolverVariable, margin3, 1);
                                linearSystem4.addLowerThan(constraintWidget4.mRight.mSolverVariable, solverVariable3, -margin4, 1);
                                linearSystem4.addEquality(constraintWidget4.mRight.mSolverVariable, constraintWidget4.mLeft.mSolverVariable, constraintWidget4.getWidth(), 2);
                                i = i6;
                                linearSystem2 = linearSystem4;
                            } else {
                                linearSystem4.addEquality(constraintWidget8.mLeft.mSolverVariable, constraintWidget8.mLeft.mTarget.mSolverVariable, margin3, 1);
                                linearSystem4.addEquality(constraintWidget8.mRight.mSolverVariable, solverVariable3, -margin4, 1);
                                i = i6;
                                linearSystem2 = linearSystem4;
                            }
                        } else {
                            int i8 = 0;
                            while (true) {
                                int i9 = countMatchConstraintsChainedWidgets - 1;
                                if (i8 >= i9) {
                                    break;
                                }
                                ConstraintWidget[] constraintWidgetArr2 = constraintWidgetContainer.mMatchConstraintsChainedWidgets;
                                ConstraintWidget constraintWidget9 = constraintWidgetArr2[i8];
                                i8++;
                                ConstraintWidget constraintWidget10 = constraintWidgetArr2[i8];
                                SolverVariable solverVariable4 = constraintWidget9.mLeft.mSolverVariable;
                                SolverVariable solverVariable5 = constraintWidget9.mRight.mSolverVariable;
                                SolverVariable solverVariable6 = constraintWidget10.mLeft.mSolverVariable;
                                SolverVariable solverVariable7 = constraintWidget10.mRight.mSolverVariable;
                                ConstraintWidget[] constraintWidgetArr3 = constraintWidgetContainer.mChainEnds;
                                int i10 = countMatchConstraintsChainedWidgets;
                                if (constraintWidget10 != constraintWidgetArr3[c]) {
                                    solverVariable2 = solverVariable7;
                                } else {
                                    solverVariable2 = constraintWidgetArr3[1].mRight.mSolverVariable;
                                }
                                int margin5 = constraintWidget9.mLeft.getMargin();
                                if (constraintWidget9.mLeft.mTarget != null && constraintWidget9.mLeft.mTarget.mOwner.mRight.mTarget != null && constraintWidget9.mLeft.mTarget.mOwner.mRight.mTarget.mOwner == constraintWidget9) {
                                    margin5 += constraintWidget9.mLeft.mTarget.mOwner.mRight.getMargin();
                                }
                                linearSystem4.addGreaterThan(solverVariable4, constraintWidget9.mLeft.mTarget.mSolverVariable, margin5, 2);
                                int margin6 = constraintWidget9.mRight.getMargin();
                                if (constraintWidget9.mRight.mTarget != null && constraintWidget9.mHorizontalNextWidget != null) {
                                    margin6 += constraintWidget9.mHorizontalNextWidget.mLeft.mTarget != null ? constraintWidget9.mHorizontalNextWidget.mLeft.getMargin() : 0;
                                }
                                linearSystem4.addLowerThan(solverVariable5, constraintWidget9.mRight.mTarget.mSolverVariable, -margin6, 2);
                                if (i8 != i9) {
                                    i3 = 2;
                                } else {
                                    int margin7 = constraintWidget10.mLeft.getMargin();
                                    if (constraintWidget10.mLeft.mTarget != null && constraintWidget10.mLeft.mTarget.mOwner.mRight.mTarget != null && constraintWidget10.mLeft.mTarget.mOwner.mRight.mTarget.mOwner == constraintWidget10) {
                                        margin7 += constraintWidget10.mLeft.mTarget.mOwner.mRight.getMargin();
                                    }
                                    linearSystem4.addGreaterThan(solverVariable6, constraintWidget10.mLeft.mTarget.mSolverVariable, margin7, 2);
                                    ConstraintAnchor constraintAnchor = constraintWidget10.mRight;
                                    ConstraintWidget[] constraintWidgetArr4 = constraintWidgetContainer.mChainEnds;
                                    if (constraintWidget10 == constraintWidgetArr4[3]) {
                                        constraintAnchor = constraintWidgetArr4[1].mRight;
                                    }
                                    int margin8 = constraintAnchor.getMargin();
                                    if (constraintAnchor.mTarget != null && constraintAnchor.mTarget.mOwner.mLeft.mTarget != null && constraintAnchor.mTarget.mOwner.mLeft.mTarget.mOwner == constraintWidget10) {
                                        margin8 += constraintAnchor.mTarget.mOwner.mLeft.getMargin();
                                    }
                                    i3 = 2;
                                    linearSystem4.addLowerThan(solverVariable2, constraintAnchor.mTarget.mSolverVariable, -margin8, 2);
                                }
                                if (constraintWidget4.mMatchConstraintMaxWidth > 0) {
                                    linearSystem4.addLowerThan(solverVariable5, solverVariable4, constraintWidget4.mMatchConstraintMaxWidth, i3);
                                }
                                ArrayRow createRow = linearSystem.createRow();
                                createRow.createRowEqualDimension(constraintWidget9.mHorizontalWeight, f, constraintWidget10.mHorizontalWeight, solverVariable4, constraintWidget9.mLeft.getMargin(), solverVariable5, constraintWidget9.mRight.getMargin(), solverVariable6, constraintWidget10.mLeft.getMargin(), solverVariable2, constraintWidget10.mRight.getMargin());
                                linearSystem4.addConstraint(createRow);
                                countMatchConstraintsChainedWidgets = i10;
                                c = 3;
                            }
                            i = i6;
                            linearSystem2 = linearSystem4;
                        }
                    } else {
                        ConstraintWidget constraintWidget11 = constraintWidget5;
                        ConstraintWidget constraintWidget12 = null;
                        ConstraintWidget constraintWidget13 = null;
                        boolean z5 = false;
                        while (constraintWidget11 != null) {
                            ConstraintWidget constraintWidget14 = constraintWidget11.mHorizontalNextWidget;
                            if (constraintWidget14 == null) {
                                constraintWidget12 = constraintWidgetContainer.mChainEnds[1];
                                z5 = true;
                            }
                            if (z3) {
                                ConstraintAnchor constraintAnchor2 = constraintWidget11.mLeft;
                                int margin9 = constraintAnchor2.getMargin();
                                if (constraintWidget13 != null) {
                                    margin9 += constraintWidget13.mRight.getMargin();
                                }
                                linearSystem4.addGreaterThan(constraintAnchor2.mSolverVariable, constraintAnchor2.mTarget.mSolverVariable, margin9, constraintWidget5 != constraintWidget11 ? 3 : 1);
                                if (constraintWidget11.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                                    ConstraintAnchor constraintAnchor3 = constraintWidget11.mRight;
                                    if (constraintWidget11.mMatchConstraintDefaultWidth == 1) {
                                        linearSystem4.addEquality(constraintAnchor3.mSolverVariable, constraintAnchor2.mSolverVariable, Math.max(constraintWidget11.mMatchConstraintMinWidth, constraintWidget11.getWidth()), 3);
                                    } else {
                                        linearSystem4.addGreaterThan(constraintAnchor2.mSolverVariable, constraintAnchor2.mTarget.mSolverVariable, constraintAnchor2.mMargin, 3);
                                        linearSystem4.addLowerThan(constraintAnchor3.mSolverVariable, constraintAnchor2.mSolverVariable, constraintWidget11.mMatchConstraintMinWidth, 3);
                                    }
                                }
                            } else if (!z2 && z5 && constraintWidget13 != null) {
                                if (constraintWidget11.mRight.mTarget == null) {
                                    linearSystem4.addEquality(constraintWidget11.mRight.mSolverVariable, constraintWidget11.getDrawRight());
                                } else {
                                    linearSystem4.addEquality(constraintWidget11.mRight.mSolverVariable, constraintWidget12.mRight.mTarget.mSolverVariable, -constraintWidget11.mRight.getMargin(), 5);
                                }
                            } else if (!z2 && !z5 && constraintWidget13 == null) {
                                if (constraintWidget11.mLeft.mTarget == null) {
                                    linearSystem4.addEquality(constraintWidget11.mLeft.mSolverVariable, constraintWidget11.getDrawX());
                                } else {
                                    linearSystem4.addEquality(constraintWidget11.mLeft.mSolverVariable, constraintWidget4.mLeft.mTarget.mSolverVariable, constraintWidget11.mLeft.getMargin(), 5);
                                }
                            } else {
                                ConstraintAnchor constraintAnchor4 = constraintWidget11.mLeft;
                                ConstraintAnchor constraintAnchor5 = constraintWidget11.mRight;
                                int margin10 = constraintAnchor4.getMargin();
                                int margin11 = constraintAnchor5.getMargin();
                                constraintWidget = constraintWidget11;
                                z = z2;
                                linearSystem4.addGreaterThan(constraintAnchor4.mSolverVariable, constraintAnchor4.mTarget.mSolverVariable, margin10, 1);
                                i2 = i6;
                                linearSystem4.addLowerThan(constraintAnchor5.mSolverVariable, constraintAnchor5.mTarget.mSolverVariable, -margin11, 1);
                                SolverVariable solverVariable8 = constraintAnchor4.mTarget != null ? constraintAnchor4.mTarget.mSolverVariable : null;
                                if (constraintWidget13 == null) {
                                    solverVariable8 = constraintWidget4.mLeft.mTarget != null ? constraintWidget4.mLeft.mTarget.mSolverVariable : null;
                                }
                                if (constraintWidget14 == null) {
                                    constraintWidget2 = constraintWidget12.mRight.mTarget != null ? constraintWidget12.mRight.mTarget.mOwner : null;
                                } else {
                                    constraintWidget2 = constraintWidget14;
                                }
                                if (constraintWidget2 != null) {
                                    SolverVariable solverVariable9 = constraintWidget2.mLeft.mSolverVariable;
                                    if (!z5) {
                                        solverVariable = solverVariable9;
                                    } else {
                                        solverVariable = constraintWidget12.mRight.mTarget != null ? constraintWidget12.mRight.mTarget.mSolverVariable : null;
                                    }
                                    if (solverVariable8 != null && solverVariable != null) {
                                        SolverVariable solverVariable10 = constraintAnchor4.mSolverVariable;
                                        SolverVariable solverVariable11 = constraintAnchor5.mSolverVariable;
                                        SolverVariable solverVariable12 = solverVariable8;
                                        constraintWidget3 = constraintWidget4;
                                        linearSystem3 = linearSystem4;
                                        linearSystem.addCentering(solverVariable10, solverVariable12, margin10, 0.5f, solverVariable, solverVariable11, margin11, 4);
                                        constraintWidget14 = constraintWidget2;
                                        if (z5) {
                                            constraintWidget14 = null;
                                        }
                                        constraintWidget4 = constraintWidget3;
                                        linearSystem4 = linearSystem3;
                                        constraintWidget11 = constraintWidget14;
                                        constraintWidget13 = constraintWidget;
                                        z2 = z;
                                        i6 = i2;
                                        constraintWidgetContainer = this;
                                    }
                                }
                                constraintWidget3 = constraintWidget4;
                                linearSystem3 = linearSystem4;
                                constraintWidget14 = constraintWidget2;
                                if (z5) {
                                }
                                constraintWidget4 = constraintWidget3;
                                linearSystem4 = linearSystem3;
                                constraintWidget11 = constraintWidget14;
                                constraintWidget13 = constraintWidget;
                                z2 = z;
                                i6 = i2;
                                constraintWidgetContainer = this;
                            }
                            constraintWidget = constraintWidget11;
                            z = z2;
                            constraintWidget3 = constraintWidget4;
                            i2 = i6;
                            linearSystem3 = linearSystem4;
                            if (z5) {
                            }
                            constraintWidget4 = constraintWidget3;
                            linearSystem4 = linearSystem3;
                            constraintWidget11 = constraintWidget14;
                            constraintWidget13 = constraintWidget;
                            z2 = z;
                            i6 = i2;
                            constraintWidgetContainer = this;
                        }
                        ConstraintWidget constraintWidget15 = constraintWidget4;
                        i = i6;
                        linearSystem2 = linearSystem4;
                        if (z3) {
                            ConstraintAnchor constraintAnchor6 = constraintWidget5.mLeft;
                            ConstraintAnchor constraintAnchor7 = constraintWidget12.mRight;
                            int margin12 = constraintAnchor6.getMargin();
                            int margin13 = constraintAnchor7.getMargin();
                            SolverVariable solverVariable13 = constraintWidget15.mLeft.mTarget != null ? constraintWidget15.mLeft.mTarget.mSolverVariable : null;
                            SolverVariable solverVariable14 = constraintWidget12.mRight.mTarget != null ? constraintWidget12.mRight.mTarget.mSolverVariable : null;
                            if (solverVariable13 != null && solverVariable14 != null) {
                                linearSystem2.addLowerThan(constraintAnchor7.mSolverVariable, solverVariable14, -margin13, 1);
                                linearSystem.addCentering(constraintAnchor6.mSolverVariable, solverVariable13, margin12, constraintWidget15.mHorizontalBiasPercent, solverVariable14, constraintAnchor7.mSolverVariable, margin13, 4);
                            }
                        }
                    }
                }
            }
            i6 = i + 1;
            linearSystem4 = linearSystem2;
            i5 = 0;
            constraintWidgetContainer = this;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:219:0x04fa  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x04fc A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void applyVerticalChain(LinearSystem linearSystem) {
        int i;
        LinearSystem linearSystem2;
        ConstraintWidget constraintWidget;
        boolean z;
        int i2;
        ConstraintWidget constraintWidget2;
        ConstraintWidget constraintWidget3;
        LinearSystem linearSystem3;
        SolverVariable solverVariable;
        SolverVariable solverVariable2;
        SolverVariable solverVariable3;
        SolverVariable solverVariable4;
        int i3;
        int i4;
        ConstraintWidgetContainer constraintWidgetContainer = this;
        LinearSystem linearSystem4 = linearSystem;
        int i5 = 0;
        int i6 = 0;
        while (i6 < constraintWidgetContainer.mVerticalChainsSize) {
            ConstraintWidget constraintWidget4 = constraintWidgetContainer.mVerticalChainsArray[i6];
            int countMatchConstraintsChainedWidgets = countMatchConstraintsChainedWidgets(linearSystem, constraintWidgetContainer.mChainEnds, constraintWidget4, 1, constraintWidgetContainer.flags);
            ConstraintWidget constraintWidget5 = constraintWidgetContainer.mChainEnds[2];
            if (constraintWidget5 == null) {
                i = i6;
                linearSystem2 = linearSystem4;
            } else if (constraintWidgetContainer.flags[1]) {
                int drawY = constraintWidget4.getDrawY();
                while (constraintWidget5 != null) {
                    linearSystem4.addEquality(constraintWidget5.mTop.mSolverVariable, drawY);
                    ConstraintWidget constraintWidget6 = constraintWidget5.mVerticalNextWidget;
                    drawY += constraintWidget5.mTop.getMargin() + constraintWidget5.getHeight() + constraintWidget5.mBottom.getMargin();
                    constraintWidget5 = constraintWidget6;
                }
                i = i6;
                linearSystem2 = linearSystem4;
            } else {
                boolean z2 = constraintWidget4.mVerticalChainStyle == 0;
                boolean z3 = constraintWidget4.mVerticalChainStyle == 2;
                boolean z4 = constraintWidgetContainer.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                int i7 = constraintWidgetContainer.mOptimizationLevel;
                if ((i7 == 2 || i7 == 8) && constraintWidgetContainer.flags[i5] && constraintWidget4.mVerticalChainFixedPosition && !z3 && !z4 && constraintWidget4.mVerticalChainStyle == 0) {
                    Optimizer.applyDirectResolutionVerticalChain(constraintWidgetContainer, linearSystem4, countMatchConstraintsChainedWidgets, constraintWidget4);
                    i = i6;
                    linearSystem2 = linearSystem4;
                } else {
                    char c = 3;
                    if (countMatchConstraintsChainedWidgets != 0 && !z3) {
                        float f = 0.0f;
                        ConstraintWidget constraintWidget7 = null;
                        while (constraintWidget5 != null) {
                            if (constraintWidget5.mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                                int margin = constraintWidget5.mTop.getMargin();
                                if (constraintWidget7 != null) {
                                    margin += constraintWidget7.mBottom.getMargin();
                                }
                                linearSystem4.addGreaterThan(constraintWidget5.mTop.mSolverVariable, constraintWidget5.mTop.mTarget.mSolverVariable, margin, constraintWidget5.mTop.mTarget.mOwner.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT ? 2 : 3);
                                int margin2 = constraintWidget5.mBottom.getMargin();
                                if (constraintWidget5.mBottom.mTarget.mOwner.mTop.mTarget != null && constraintWidget5.mBottom.mTarget.mOwner.mTop.mTarget.mOwner == constraintWidget5) {
                                    margin2 += constraintWidget5.mBottom.mTarget.mOwner.mTop.getMargin();
                                }
                                linearSystem4.addLowerThan(constraintWidget5.mBottom.mSolverVariable, constraintWidget5.mBottom.mTarget.mSolverVariable, -margin2, constraintWidget5.mBottom.mTarget.mOwner.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT ? 2 : 3);
                            } else {
                                f += constraintWidget5.mVerticalWeight;
                                if (constraintWidget5.mBottom.mTarget != null) {
                                    i4 = constraintWidget5.mBottom.getMargin();
                                    if (constraintWidget5 != constraintWidgetContainer.mChainEnds[3]) {
                                        i4 += constraintWidget5.mBottom.mTarget.mOwner.mTop.getMargin();
                                    }
                                } else {
                                    i4 = 0;
                                }
                                linearSystem4.addGreaterThan(constraintWidget5.mBottom.mSolverVariable, constraintWidget5.mTop.mSolverVariable, i5, 1);
                                linearSystem4.addLowerThan(constraintWidget5.mBottom.mSolverVariable, constraintWidget5.mBottom.mTarget.mSolverVariable, -i4, 1);
                            }
                            constraintWidget7 = constraintWidget5;
                            constraintWidget5 = constraintWidget5.mVerticalNextWidget;
                        }
                        if (countMatchConstraintsChainedWidgets == 1) {
                            ConstraintWidget constraintWidget8 = constraintWidgetContainer.mMatchConstraintsChainedWidgets[i5];
                            int margin3 = constraintWidget8.mTop.getMargin();
                            if (constraintWidget8.mTop.mTarget != null) {
                                margin3 += constraintWidget8.mTop.mTarget.getMargin();
                            }
                            int margin4 = constraintWidget8.mBottom.getMargin();
                            if (constraintWidget8.mBottom.mTarget != null) {
                                margin4 += constraintWidget8.mBottom.mTarget.getMargin();
                            }
                            SolverVariable solverVariable5 = constraintWidget4.mBottom.mTarget.mSolverVariable;
                            ConstraintWidget[] constraintWidgetArr = constraintWidgetContainer.mChainEnds;
                            if (constraintWidget8 == constraintWidgetArr[3]) {
                                solverVariable5 = constraintWidgetArr[1].mBottom.mTarget.mSolverVariable;
                            }
                            if (constraintWidget8.mMatchConstraintDefaultHeight == 1) {
                                linearSystem4.addGreaterThan(constraintWidget4.mTop.mSolverVariable, constraintWidget4.mTop.mTarget.mSolverVariable, margin3, 1);
                                linearSystem4.addLowerThan(constraintWidget4.mBottom.mSolverVariable, solverVariable5, -margin4, 1);
                                linearSystem4.addEquality(constraintWidget4.mBottom.mSolverVariable, constraintWidget4.mTop.mSolverVariable, constraintWidget4.getHeight(), 2);
                                i = i6;
                                linearSystem2 = linearSystem4;
                            } else {
                                linearSystem4.addEquality(constraintWidget8.mTop.mSolverVariable, constraintWidget8.mTop.mTarget.mSolverVariable, margin3, 1);
                                linearSystem4.addEquality(constraintWidget8.mBottom.mSolverVariable, solverVariable5, -margin4, 1);
                                i = i6;
                                linearSystem2 = linearSystem4;
                            }
                        } else {
                            int i8 = 0;
                            while (true) {
                                int i9 = countMatchConstraintsChainedWidgets - 1;
                                if (i8 >= i9) {
                                    break;
                                }
                                ConstraintWidget[] constraintWidgetArr2 = constraintWidgetContainer.mMatchConstraintsChainedWidgets;
                                ConstraintWidget constraintWidget9 = constraintWidgetArr2[i8];
                                i8++;
                                ConstraintWidget constraintWidget10 = constraintWidgetArr2[i8];
                                SolverVariable solverVariable6 = constraintWidget9.mTop.mSolverVariable;
                                SolverVariable solverVariable7 = constraintWidget9.mBottom.mSolverVariable;
                                SolverVariable solverVariable8 = constraintWidget10.mTop.mSolverVariable;
                                SolverVariable solverVariable9 = constraintWidget10.mBottom.mSolverVariable;
                                ConstraintWidget[] constraintWidgetArr3 = constraintWidgetContainer.mChainEnds;
                                int i10 = countMatchConstraintsChainedWidgets;
                                if (constraintWidget10 != constraintWidgetArr3[c]) {
                                    solverVariable4 = solverVariable9;
                                } else {
                                    solverVariable4 = constraintWidgetArr3[1].mBottom.mSolverVariable;
                                }
                                int margin5 = constraintWidget9.mTop.getMargin();
                                if (constraintWidget9.mTop.mTarget != null && constraintWidget9.mTop.mTarget.mOwner.mBottom.mTarget != null && constraintWidget9.mTop.mTarget.mOwner.mBottom.mTarget.mOwner == constraintWidget9) {
                                    margin5 += constraintWidget9.mTop.mTarget.mOwner.mBottom.getMargin();
                                }
                                linearSystem4.addGreaterThan(solverVariable6, constraintWidget9.mTop.mTarget.mSolverVariable, margin5, 2);
                                int margin6 = constraintWidget9.mBottom.getMargin();
                                if (constraintWidget9.mBottom.mTarget != null && constraintWidget9.mVerticalNextWidget != null) {
                                    margin6 += constraintWidget9.mVerticalNextWidget.mTop.mTarget != null ? constraintWidget9.mVerticalNextWidget.mTop.getMargin() : 0;
                                }
                                linearSystem4.addLowerThan(solverVariable7, constraintWidget9.mBottom.mTarget.mSolverVariable, -margin6, 2);
                                if (i8 != i9) {
                                    i3 = 2;
                                } else {
                                    int margin7 = constraintWidget10.mTop.getMargin();
                                    if (constraintWidget10.mTop.mTarget != null && constraintWidget10.mTop.mTarget.mOwner.mBottom.mTarget != null && constraintWidget10.mTop.mTarget.mOwner.mBottom.mTarget.mOwner == constraintWidget10) {
                                        margin7 += constraintWidget10.mTop.mTarget.mOwner.mBottom.getMargin();
                                    }
                                    linearSystem4.addGreaterThan(solverVariable8, constraintWidget10.mTop.mTarget.mSolverVariable, margin7, 2);
                                    ConstraintAnchor constraintAnchor = constraintWidget10.mBottom;
                                    ConstraintWidget[] constraintWidgetArr4 = constraintWidgetContainer.mChainEnds;
                                    if (constraintWidget10 == constraintWidgetArr4[3]) {
                                        constraintAnchor = constraintWidgetArr4[1].mBottom;
                                    }
                                    int margin8 = constraintAnchor.getMargin();
                                    if (constraintAnchor.mTarget != null && constraintAnchor.mTarget.mOwner.mTop.mTarget != null && constraintAnchor.mTarget.mOwner.mTop.mTarget.mOwner == constraintWidget10) {
                                        margin8 += constraintAnchor.mTarget.mOwner.mTop.getMargin();
                                    }
                                    i3 = 2;
                                    linearSystem4.addLowerThan(solverVariable4, constraintAnchor.mTarget.mSolverVariable, -margin8, 2);
                                }
                                if (constraintWidget4.mMatchConstraintMaxHeight > 0) {
                                    linearSystem4.addLowerThan(solverVariable7, solverVariable6, constraintWidget4.mMatchConstraintMaxHeight, i3);
                                }
                                ArrayRow createRow = linearSystem.createRow();
                                createRow.createRowEqualDimension(constraintWidget9.mVerticalWeight, f, constraintWidget10.mVerticalWeight, solverVariable6, constraintWidget9.mTop.getMargin(), solverVariable7, constraintWidget9.mBottom.getMargin(), solverVariable8, constraintWidget10.mTop.getMargin(), solverVariable4, constraintWidget10.mBottom.getMargin());
                                linearSystem4.addConstraint(createRow);
                                countMatchConstraintsChainedWidgets = i10;
                                c = 3;
                            }
                            i = i6;
                            linearSystem2 = linearSystem4;
                        }
                    } else {
                        ConstraintWidget constraintWidget11 = constraintWidget5;
                        ConstraintWidget constraintWidget12 = null;
                        ConstraintWidget constraintWidget13 = null;
                        boolean z5 = false;
                        while (constraintWidget11 != null) {
                            ConstraintWidget constraintWidget14 = constraintWidget11.mVerticalNextWidget;
                            if (constraintWidget14 == null) {
                                constraintWidget12 = constraintWidgetContainer.mChainEnds[1];
                                z5 = true;
                            }
                            if (z3) {
                                ConstraintAnchor constraintAnchor2 = constraintWidget11.mTop;
                                int margin9 = constraintAnchor2.getMargin();
                                if (constraintWidget13 != null) {
                                    margin9 += constraintWidget13.mBottom.getMargin();
                                }
                                int i11 = constraintWidget5 != constraintWidget11 ? 3 : 1;
                                if (constraintAnchor2.mTarget != null) {
                                    solverVariable2 = constraintAnchor2.mSolverVariable;
                                    solverVariable3 = constraintAnchor2.mTarget.mSolverVariable;
                                } else if (constraintWidget11.mBaseline.mTarget != null) {
                                    solverVariable2 = constraintWidget11.mBaseline.mSolverVariable;
                                    solverVariable3 = constraintWidget11.mBaseline.mTarget.mSolverVariable;
                                    margin9 -= constraintAnchor2.getMargin();
                                } else {
                                    solverVariable2 = null;
                                    solverVariable3 = null;
                                }
                                if (solverVariable2 != null && solverVariable3 != null) {
                                    linearSystem4.addGreaterThan(solverVariable2, solverVariable3, margin9, i11);
                                }
                                if (constraintWidget11.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                                    ConstraintAnchor constraintAnchor3 = constraintWidget11.mBottom;
                                    if (constraintWidget11.mMatchConstraintDefaultHeight == 1) {
                                        linearSystem4.addEquality(constraintAnchor3.mSolverVariable, constraintAnchor2.mSolverVariable, Math.max(constraintWidget11.mMatchConstraintMinHeight, constraintWidget11.getHeight()), 3);
                                    } else {
                                        linearSystem4.addGreaterThan(constraintAnchor2.mSolverVariable, constraintAnchor2.mTarget.mSolverVariable, constraintAnchor2.mMargin, 3);
                                        linearSystem4.addLowerThan(constraintAnchor3.mSolverVariable, constraintAnchor2.mSolverVariable, constraintWidget11.mMatchConstraintMinHeight, 3);
                                    }
                                }
                            } else if (!z2 && z5 && constraintWidget13 != null) {
                                if (constraintWidget11.mBottom.mTarget == null) {
                                    linearSystem4.addEquality(constraintWidget11.mBottom.mSolverVariable, constraintWidget11.getDrawBottom());
                                } else {
                                    linearSystem4.addEquality(constraintWidget11.mBottom.mSolverVariable, constraintWidget12.mBottom.mTarget.mSolverVariable, -constraintWidget11.mBottom.getMargin(), 5);
                                }
                            } else if (!z2 && !z5 && constraintWidget13 == null) {
                                if (constraintWidget11.mTop.mTarget == null) {
                                    linearSystem4.addEquality(constraintWidget11.mTop.mSolverVariable, constraintWidget11.getDrawY());
                                } else {
                                    linearSystem4.addEquality(constraintWidget11.mTop.mSolverVariable, constraintWidget4.mTop.mTarget.mSolverVariable, constraintWidget11.mTop.getMargin(), 5);
                                }
                            } else {
                                ConstraintAnchor constraintAnchor4 = constraintWidget11.mTop;
                                ConstraintAnchor constraintAnchor5 = constraintWidget11.mBottom;
                                int margin10 = constraintAnchor4.getMargin();
                                int margin11 = constraintAnchor5.getMargin();
                                constraintWidget = constraintWidget11;
                                z = z2;
                                linearSystem4.addGreaterThan(constraintAnchor4.mSolverVariable, constraintAnchor4.mTarget.mSolverVariable, margin10, 1);
                                i2 = i6;
                                linearSystem4.addLowerThan(constraintAnchor5.mSolverVariable, constraintAnchor5.mTarget.mSolverVariable, -margin11, 1);
                                SolverVariable solverVariable10 = constraintAnchor4.mTarget != null ? constraintAnchor4.mTarget.mSolverVariable : null;
                                if (constraintWidget13 == null) {
                                    solverVariable10 = constraintWidget4.mTop.mTarget != null ? constraintWidget4.mTop.mTarget.mSolverVariable : null;
                                }
                                if (constraintWidget14 == null) {
                                    constraintWidget2 = constraintWidget12.mBottom.mTarget != null ? constraintWidget12.mBottom.mTarget.mOwner : null;
                                } else {
                                    constraintWidget2 = constraintWidget14;
                                }
                                if (constraintWidget2 != null) {
                                    SolverVariable solverVariable11 = constraintWidget2.mTop.mSolverVariable;
                                    if (!z5) {
                                        solverVariable = solverVariable11;
                                    } else {
                                        solverVariable = constraintWidget12.mBottom.mTarget != null ? constraintWidget12.mBottom.mTarget.mSolverVariable : null;
                                    }
                                    if (solverVariable10 != null && solverVariable != null) {
                                        SolverVariable solverVariable12 = constraintAnchor4.mSolverVariable;
                                        SolverVariable solverVariable13 = constraintAnchor5.mSolverVariable;
                                        SolverVariable solverVariable14 = solverVariable10;
                                        constraintWidget3 = constraintWidget4;
                                        linearSystem3 = linearSystem4;
                                        linearSystem.addCentering(solverVariable12, solverVariable14, margin10, 0.5f, solverVariable, solverVariable13, margin11, 4);
                                        constraintWidget14 = constraintWidget2;
                                        if (z5) {
                                            constraintWidget14 = null;
                                        }
                                        constraintWidget4 = constraintWidget3;
                                        linearSystem4 = linearSystem3;
                                        constraintWidget11 = constraintWidget14;
                                        constraintWidget13 = constraintWidget;
                                        z2 = z;
                                        i6 = i2;
                                        constraintWidgetContainer = this;
                                    }
                                }
                                constraintWidget3 = constraintWidget4;
                                linearSystem3 = linearSystem4;
                                constraintWidget14 = constraintWidget2;
                                if (z5) {
                                }
                                constraintWidget4 = constraintWidget3;
                                linearSystem4 = linearSystem3;
                                constraintWidget11 = constraintWidget14;
                                constraintWidget13 = constraintWidget;
                                z2 = z;
                                i6 = i2;
                                constraintWidgetContainer = this;
                            }
                            constraintWidget = constraintWidget11;
                            z = z2;
                            constraintWidget3 = constraintWidget4;
                            i2 = i6;
                            linearSystem3 = linearSystem4;
                            if (z5) {
                            }
                            constraintWidget4 = constraintWidget3;
                            linearSystem4 = linearSystem3;
                            constraintWidget11 = constraintWidget14;
                            constraintWidget13 = constraintWidget;
                            z2 = z;
                            i6 = i2;
                            constraintWidgetContainer = this;
                        }
                        ConstraintWidget constraintWidget15 = constraintWidget4;
                        i = i6;
                        linearSystem2 = linearSystem4;
                        if (z3) {
                            ConstraintAnchor constraintAnchor6 = constraintWidget5.mTop;
                            ConstraintAnchor constraintAnchor7 = constraintWidget12.mBottom;
                            int margin12 = constraintAnchor6.getMargin();
                            int margin13 = constraintAnchor7.getMargin();
                            SolverVariable solverVariable15 = constraintWidget15.mTop.mTarget != null ? constraintWidget15.mTop.mTarget.mSolverVariable : null;
                            SolverVariable solverVariable16 = constraintWidget12.mBottom.mTarget != null ? constraintWidget12.mBottom.mTarget.mSolverVariable : null;
                            if (solverVariable15 != null && solverVariable16 != null) {
                                linearSystem2.addLowerThan(constraintAnchor7.mSolverVariable, solverVariable16, -margin13, 1);
                                linearSystem.addCentering(constraintAnchor6.mSolverVariable, solverVariable15, margin12, constraintWidget15.mVerticalBiasPercent, solverVariable16, constraintAnchor7.mSolverVariable, margin13, 4);
                            }
                        }
                    }
                }
            }
            i6 = i + 1;
            linearSystem4 = linearSystem2;
            i5 = 0;
            constraintWidgetContainer = this;
        }
    }

    private boolean optimize(LinearSystem linearSystem) {
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            constraintWidget.mHorizontalResolution = -1;
            constraintWidget.mVerticalResolution = -1;
            if (constraintWidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                constraintWidget.mHorizontalResolution = 1;
                constraintWidget.mVerticalResolution = 1;
            }
        }
        boolean z = false;
        int i2 = 0;
        int i3 = 0;
        while (!z) {
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < size; i6++) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) this.mChildren.get(i6);
                if (constraintWidget2.mHorizontalResolution == -1) {
                    if (this.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget2.mHorizontalResolution = 1;
                    } else {
                        Optimizer.checkHorizontalSimpleDependency(this, linearSystem, constraintWidget2);
                    }
                }
                if (constraintWidget2.mVerticalResolution == -1) {
                    if (this.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget2.mVerticalResolution = 1;
                    } else {
                        Optimizer.checkVerticalSimpleDependency(this, linearSystem, constraintWidget2);
                    }
                }
                if (constraintWidget2.mVerticalResolution == -1) {
                    i4++;
                }
                if (constraintWidget2.mHorizontalResolution == -1) {
                    i5++;
                }
            }
            if ((i4 == 0 && i5 == 0) || (i2 == i4 && i3 == i5)) {
                z = true;
            }
            i2 = i4;
            i3 = i5;
        }
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            ConstraintWidget constraintWidget3 = (ConstraintWidget) this.mChildren.get(i9);
            if (constraintWidget3.mHorizontalResolution == 1 || constraintWidget3.mHorizontalResolution == -1) {
                i7++;
            }
            if (constraintWidget3.mVerticalResolution == 1 || constraintWidget3.mVerticalResolution == -1) {
                i8++;
            }
        }
        return i7 == 0 && i8 == 0;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem, int i) {
        boolean z;
        addToSolver(linearSystem, i);
        int size = this.mChildren.size();
        int i2 = this.mOptimizationLevel;
        if (i2 != 2 && i2 != 4) {
            z = true;
        } else if (optimize(linearSystem)) {
            return false;
        } else {
            z = false;
        }
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i3);
            if (constraintWidget instanceof ConstraintWidgetContainer) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.mHorizontalDimensionBehaviour;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget.mVerticalDimensionBehaviour;
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                constraintWidget.addToSolver(linearSystem, i);
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            } else {
                if (z) {
                    Optimizer.checkMatchParent(this, linearSystem, constraintWidget);
                }
                constraintWidget.addToSolver(linearSystem, i);
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            applyHorizontalChain(linearSystem);
        }
        if (this.mVerticalChainsSize > 0) {
            applyVerticalChain(linearSystem);
        }
        return true;
    }

    public void findHorizontalWrapRecursive(ConstraintWidget constraintWidget, boolean[] zArr) {
        int i;
        ConstraintWidget constraintWidget2;
        int i2;
        boolean z = false;
        r3 = 0;
        int i3 = 0;
        z = false;
        z = false;
        if (constraintWidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mDimensionRatio > 0.0f) {
            zArr[0] = false;
            return;
        }
        int optimizerWrapWidth = constraintWidget.getOptimizerWrapWidth();
        if (constraintWidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mDimensionRatio > 0.0f) {
            zArr[0] = false;
            return;
        }
        constraintWidget.mHorizontalWrapVisited = true;
        if (constraintWidget instanceof Guideline) {
            Guideline guideline = (Guideline) constraintWidget;
            if (guideline.getOrientation() == 1) {
                if (guideline.getRelativeBegin() != -1) {
                    i3 = guideline.getRelativeBegin();
                } else if (guideline.getRelativeEnd() != -1) {
                    i2 = guideline.getRelativeEnd();
                }
                i2 = 0;
            } else {
                i2 = optimizerWrapWidth;
                i3 = i2;
            }
            optimizerWrapWidth = i2;
        } else if (!constraintWidget.mRight.isConnected() && !constraintWidget.mLeft.isConnected()) {
            i3 = optimizerWrapWidth + constraintWidget.getX();
        } else if (constraintWidget.mRight.mTarget != null && constraintWidget.mLeft.mTarget != null && (constraintWidget.mRight.mTarget == constraintWidget.mLeft.mTarget || (constraintWidget.mRight.mTarget.mOwner == constraintWidget.mLeft.mTarget.mOwner && constraintWidget.mRight.mTarget.mOwner != constraintWidget.mParent))) {
            zArr[0] = false;
            return;
        } else {
            ConstraintWidget constraintWidget3 = null;
            if (constraintWidget.mRight.mTarget != null) {
                constraintWidget2 = constraintWidget.mRight.mTarget.mOwner;
                i = constraintWidget.mRight.getMargin() + optimizerWrapWidth;
                if (!constraintWidget2.isRoot() && !constraintWidget2.mHorizontalWrapVisited) {
                    findHorizontalWrapRecursive(constraintWidget2, zArr);
                }
            } else {
                i = optimizerWrapWidth;
                constraintWidget2 = null;
            }
            if (constraintWidget.mLeft.mTarget != null) {
                constraintWidget3 = constraintWidget.mLeft.mTarget.mOwner;
                optimizerWrapWidth += constraintWidget.mLeft.getMargin();
                if (!constraintWidget3.isRoot() && !constraintWidget3.mHorizontalWrapVisited) {
                    findHorizontalWrapRecursive(constraintWidget3, zArr);
                }
            }
            if (constraintWidget.mRight.mTarget != null && !constraintWidget2.isRoot()) {
                if (constraintWidget.mRight.mTarget.mType == ConstraintAnchor.Type.RIGHT) {
                    i += constraintWidget2.mDistToRight - constraintWidget2.getOptimizerWrapWidth();
                } else if (constraintWidget.mRight.mTarget.getType() == ConstraintAnchor.Type.LEFT) {
                    i += constraintWidget2.mDistToRight;
                }
                constraintWidget.mRightHasCentered = constraintWidget2.mRightHasCentered || !(constraintWidget2.mLeft.mTarget == null || constraintWidget2.mRight.mTarget == null || constraintWidget2.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                if (constraintWidget.mRightHasCentered && (constraintWidget2.mLeft.mTarget == null || constraintWidget2.mLeft.mTarget.mOwner != constraintWidget)) {
                    i += i - constraintWidget2.mDistToRight;
                }
            }
            if (constraintWidget.mLeft.mTarget != null && !constraintWidget3.isRoot()) {
                if (constraintWidget.mLeft.mTarget.getType() == ConstraintAnchor.Type.LEFT) {
                    optimizerWrapWidth += constraintWidget3.mDistToLeft - constraintWidget3.getOptimizerWrapWidth();
                } else if (constraintWidget.mLeft.mTarget.getType() == ConstraintAnchor.Type.RIGHT) {
                    optimizerWrapWidth += constraintWidget3.mDistToLeft;
                }
                if (constraintWidget3.mLeftHasCentered || (constraintWidget3.mLeft.mTarget != null && constraintWidget3.mRight.mTarget != null && constraintWidget3.mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)) {
                    z = true;
                }
                constraintWidget.mLeftHasCentered = z;
                if (constraintWidget.mLeftHasCentered && (constraintWidget3.mRight.mTarget == null || constraintWidget3.mRight.mTarget.mOwner != constraintWidget)) {
                    i3 = optimizerWrapWidth + (optimizerWrapWidth - constraintWidget3.mDistToLeft);
                    optimizerWrapWidth = i;
                }
            }
            i3 = optimizerWrapWidth;
            optimizerWrapWidth = i;
        }
        if (constraintWidget.getVisibility() == 8) {
            i3 -= constraintWidget.mWidth;
            optimizerWrapWidth -= constraintWidget.mWidth;
        }
        constraintWidget.mDistToLeft = i3;
        constraintWidget.mDistToRight = optimizerWrapWidth;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void findVerticalWrapRecursive(ConstraintWidget constraintWidget, boolean[] zArr) {
        int i;
        ConstraintWidget constraintWidget2;
        int i2;
        boolean z = false;
        int i3 = 0;
        z = false;
        z = false;
        z = false;
        z = false;
        if (constraintWidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mDimensionRatio > 0.0f) {
            zArr[0] = false;
            return;
        }
        int optimizerWrapHeight = constraintWidget.getOptimizerWrapHeight();
        constraintWidget.mVerticalWrapVisited = true;
        if (constraintWidget instanceof Guideline) {
            Guideline guideline = (Guideline) constraintWidget;
            if (guideline.getOrientation() != 0) {
                i2 = optimizerWrapHeight;
                i3 = i2;
            } else if (guideline.getRelativeBegin() != -1) {
                i2 = guideline.getRelativeBegin();
            } else {
                i3 = guideline.getRelativeEnd() != -1 ? guideline.getRelativeEnd() : 0;
                i2 = 0;
            }
            optimizerWrapHeight = i3;
        } else if (constraintWidget.mBaseline.mTarget == null && constraintWidget.mTop.mTarget == null && constraintWidget.mBottom.mTarget == null) {
            i2 = constraintWidget.getY() + optimizerWrapHeight;
        } else if (constraintWidget.mBottom.mTarget != null && constraintWidget.mTop.mTarget != null && (constraintWidget.mBottom.mTarget == constraintWidget.mTop.mTarget || (constraintWidget.mBottom.mTarget.mOwner == constraintWidget.mTop.mTarget.mOwner && constraintWidget.mBottom.mTarget.mOwner != constraintWidget.mParent))) {
            zArr[0] = false;
            return;
        } else if (constraintWidget.mBaseline.isConnected()) {
            ConstraintWidget owner = constraintWidget.mBaseline.mTarget.getOwner();
            if (!owner.mVerticalWrapVisited) {
                findVerticalWrapRecursive(owner, zArr);
            }
            int max = Math.max((owner.mDistToTop - owner.mHeight) + optimizerWrapHeight, optimizerWrapHeight);
            int max2 = Math.max((owner.mDistToBottom - owner.mHeight) + optimizerWrapHeight, optimizerWrapHeight);
            if (constraintWidget.getVisibility() == 8) {
                max -= constraintWidget.mHeight;
                max2 -= constraintWidget.mHeight;
            }
            constraintWidget.mDistToTop = max;
            constraintWidget.mDistToBottom = max2;
            return;
        } else {
            ConstraintWidget constraintWidget3 = null;
            if (constraintWidget.mTop.isConnected()) {
                constraintWidget2 = constraintWidget.mTop.mTarget.getOwner();
                i = constraintWidget.mTop.getMargin() + optimizerWrapHeight;
                if (!constraintWidget2.isRoot() && !constraintWidget2.mVerticalWrapVisited) {
                    findVerticalWrapRecursive(constraintWidget2, zArr);
                }
            } else {
                i = optimizerWrapHeight;
                constraintWidget2 = null;
            }
            if (constraintWidget.mBottom.isConnected()) {
                constraintWidget3 = constraintWidget.mBottom.mTarget.getOwner();
                optimizerWrapHeight += constraintWidget.mBottom.getMargin();
                if (!constraintWidget3.isRoot() && !constraintWidget3.mVerticalWrapVisited) {
                    findVerticalWrapRecursive(constraintWidget3, zArr);
                }
            }
            if (constraintWidget.mTop.mTarget != null && !constraintWidget2.isRoot()) {
                if (constraintWidget.mTop.mTarget.getType() == ConstraintAnchor.Type.TOP) {
                    i += constraintWidget2.mDistToTop - constraintWidget2.getOptimizerWrapHeight();
                } else if (constraintWidget.mTop.mTarget.getType() == ConstraintAnchor.Type.BOTTOM) {
                    i += constraintWidget2.mDistToTop;
                }
                constraintWidget.mTopHasCentered = constraintWidget2.mTopHasCentered || !(constraintWidget2.mTop.mTarget == null || constraintWidget2.mTop.mTarget.mOwner == constraintWidget || constraintWidget2.mBottom.mTarget == null || constraintWidget2.mBottom.mTarget.mOwner == constraintWidget || constraintWidget2.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                if (constraintWidget.mTopHasCentered && (constraintWidget2.mBottom.mTarget == null || constraintWidget2.mBottom.mTarget.mOwner != constraintWidget)) {
                    i2 = i + (i - constraintWidget2.mDistToTop);
                    if (constraintWidget.mBottom.mTarget != null && !constraintWidget3.isRoot()) {
                        if (constraintWidget.mBottom.mTarget.getType() != ConstraintAnchor.Type.BOTTOM) {
                            optimizerWrapHeight += constraintWidget3.mDistToBottom - constraintWidget3.getOptimizerWrapHeight();
                        } else if (constraintWidget.mBottom.mTarget.getType() == ConstraintAnchor.Type.TOP) {
                            optimizerWrapHeight += constraintWidget3.mDistToBottom;
                        }
                        if (!constraintWidget3.mBottomHasCentered || (constraintWidget3.mTop.mTarget != null && constraintWidget3.mTop.mTarget.mOwner != constraintWidget && constraintWidget3.mBottom.mTarget != null && constraintWidget3.mBottom.mTarget.mOwner != constraintWidget && constraintWidget3.mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)) {
                            z = true;
                        }
                        constraintWidget.mBottomHasCentered = z;
                        if (constraintWidget.mBottomHasCentered && (constraintWidget3.mTop.mTarget == null || constraintWidget3.mTop.mTarget.mOwner != constraintWidget)) {
                            optimizerWrapHeight += optimizerWrapHeight - constraintWidget3.mDistToBottom;
                        }
                    }
                }
            }
            i2 = i;
            if (constraintWidget.mBottom.mTarget != null) {
                if (constraintWidget.mBottom.mTarget.getType() != ConstraintAnchor.Type.BOTTOM) {
                }
                if (!constraintWidget3.mBottomHasCentered) {
                }
                z = true;
                constraintWidget.mBottomHasCentered = z;
                if (constraintWidget.mBottomHasCentered) {
                    optimizerWrapHeight += optimizerWrapHeight - constraintWidget3.mDistToBottom;
                }
            }
        }
        if (constraintWidget.getVisibility() == 8) {
            i2 -= constraintWidget.mHeight;
            optimizerWrapHeight -= constraintWidget.mHeight;
        }
        constraintWidget.mDistToTop = i2;
        constraintWidget.mDistToBottom = optimizerWrapHeight;
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    @Override // android.support.constraint.solver.widgets.WidgetContainer
    public void layout() {
        boolean z;
        boolean z2;
        int i = this.mX;
        int i2 = this.mY;
        int max = Math.max(0, getWidth());
        int max2 = Math.max(0, getHeight());
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        if (this.mParent != null) {
            if (this.mSnapshot == null) {
                this.mSnapshot = new Snapshot(this);
            }
            this.mSnapshot.updateFrom(this);
            setX(this.mPaddingLeft);
            setY(this.mPaddingTop);
            resetAnchors();
            resetSolverVariables(this.mSystem.getCache());
        } else {
            this.mX = 0;
            this.mY = 0;
        }
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.mVerticalDimensionBehaviour;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.mHorizontalDimensionBehaviour;
        char c = 2;
        if (this.mOptimizationLevel == 2 && (this.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || this.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
            findWrapSize(this.mChildren, this.flags);
            z = this.flags[0];
            if (max > 0 && max2 > 0 && (this.mWrapWidth > max || this.mWrapHeight > max2)) {
                z = false;
            }
            if (z) {
                if (this.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.mHorizontalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    if (max <= 0 || max >= this.mWrapWidth) {
                        setWidth(Math.max(this.mMinWidth, this.mWrapWidth));
                    } else {
                        this.mWidthMeasuredTooSmall = true;
                        setWidth(max);
                    }
                }
                if (this.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.mVerticalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    if (max2 <= 0 || max2 >= this.mWrapHeight) {
                        setHeight(Math.max(this.mMinHeight, this.mWrapHeight));
                    } else {
                        this.mHeightMeasuredTooSmall = true;
                        setHeight(max2);
                    }
                }
            }
        } else {
            z = false;
        }
        resetChains();
        int size = this.mChildren.size();
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i3);
            if (constraintWidget instanceof WidgetContainer) {
                ((WidgetContainer) constraintWidget).layout();
            }
        }
        boolean z3 = z;
        int i4 = 0;
        boolean z4 = true;
        while (z4) {
            int i5 = i4 + 1;
            try {
                this.mSystem.reset();
                z4 = addChildrenToSolver(this.mSystem, Integer.MAX_VALUE);
                if (z4) {
                    this.mSystem.minimize();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (z4) {
                updateChildrenFromSolver(this.mSystem, Integer.MAX_VALUE, this.flags);
            } else {
                updateFromSolver(this.mSystem, Integer.MAX_VALUE);
                int i6 = 0;
                while (true) {
                    if (i6 >= size) {
                        break;
                    }
                    ConstraintWidget constraintWidget2 = (ConstraintWidget) this.mChildren.get(i6);
                    if (constraintWidget2.mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget2.getWidth() >= constraintWidget2.getWrapWidth()) {
                        if (constraintWidget2.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget2.getHeight() < constraintWidget2.getWrapHeight()) {
                            this.flags[c] = true;
                            break;
                        }
                        i6++;
                    } else {
                        this.flags[c] = true;
                        break;
                    }
                }
            }
            if (i5 < 8 && this.flags[c]) {
                int i7 = 0;
                int i8 = 0;
                for (int i9 = 0; i9 < size; i9++) {
                    ConstraintWidget constraintWidget3 = (ConstraintWidget) this.mChildren.get(i9);
                    i7 = Math.max(i7, constraintWidget3.mX + constraintWidget3.getWidth());
                    i8 = Math.max(i8, constraintWidget3.mY + constraintWidget3.getHeight());
                }
                int max3 = Math.max(this.mMinWidth, i7);
                int max4 = Math.max(this.mMinHeight, i8);
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && getWidth() < max3) {
                    setWidth(max3);
                    this.mHorizontalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    z2 = true;
                    z3 = true;
                } else {
                    z2 = false;
                }
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && getHeight() < max4) {
                    setHeight(max4);
                    this.mVerticalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    z2 = true;
                    z3 = true;
                }
            } else {
                z2 = false;
            }
            int max5 = Math.max(this.mMinWidth, getWidth());
            if (max5 > getWidth()) {
                setWidth(max5);
                this.mHorizontalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                z2 = true;
                z3 = true;
            }
            int max6 = Math.max(this.mMinHeight, getHeight());
            if (max6 > getHeight()) {
                setHeight(max6);
                this.mVerticalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                z2 = true;
                z3 = true;
            }
            if (!z3) {
                if (this.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && max > 0 && getWidth() > max) {
                    this.mWidthMeasuredTooSmall = true;
                    this.mHorizontalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    setWidth(max);
                    z2 = true;
                    z3 = true;
                }
                if (this.mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || max2 <= 0 || getHeight() <= max2) {
                    z4 = z2;
                } else {
                    this.mHeightMeasuredTooSmall = true;
                    this.mVerticalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    setHeight(max2);
                    z3 = true;
                    z4 = true;
                }
            } else {
                z4 = z2;
            }
            i4 = i5;
            c = 2;
        }
        if (this.mParent != null) {
            int max7 = Math.max(this.mMinWidth, getWidth());
            int max8 = Math.max(this.mMinHeight, getHeight());
            this.mSnapshot.applyTo(this);
            setWidth(max7 + this.mPaddingLeft + this.mPaddingRight);
            setHeight(max8 + this.mPaddingTop + this.mPaddingBottom);
        } else {
            this.mX = i;
            this.mY = i2;
        }
        if (z3) {
            this.mHorizontalDimensionBehaviour = dimensionBehaviour2;
            this.mVerticalDimensionBehaviour = dimensionBehaviour;
        }
        resetSolverVariables(this.mSystem.getCache());
        if (this == getRootConstraintContainer()) {
            updateDrawPosition();
        }
    }

    @Override // android.support.constraint.solver.widgets.WidgetContainer, android.support.constraint.solver.widgets.ConstraintWidget
    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        super.reset();
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
    }

    public void updateChildrenFromSolver(LinearSystem linearSystem, int i, boolean[] zArr) {
        zArr[2] = false;
        updateFromSolver(linearSystem, i);
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i2);
            constraintWidget.updateFromSolver(linearSystem, i);
            if (constraintWidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getWidth() < constraintWidget.getWrapWidth()) {
                zArr[2] = true;
            }
            if (constraintWidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getHeight() < constraintWidget.getWrapHeight()) {
                zArr[2] = true;
            }
        }
    }

    private int countMatchConstraintsChainedWidgets(LinearSystem linearSystem, ConstraintWidget[] constraintWidgetArr, ConstraintWidget constraintWidget, int i, boolean[] zArr) {
        int i2;
        zArr[0] = true;
        zArr[1] = false;
        ConstraintWidget constraintWidget2 = null;
        constraintWidgetArr[0] = null;
        constraintWidgetArr[2] = null;
        constraintWidgetArr[1] = null;
        constraintWidgetArr[3] = null;
        float f = 0.0f;
        int i3 = 5;
        if (i == 0) {
            boolean z = constraintWidget.mLeft.mTarget == null || constraintWidget.mLeft.mTarget.mOwner == this;
            constraintWidget.mHorizontalNextWidget = null;
            ConstraintWidget constraintWidget3 = constraintWidget.getVisibility() != 8 ? constraintWidget : null;
            ConstraintWidget constraintWidget4 = constraintWidget;
            ConstraintWidget constraintWidget5 = null;
            ConstraintWidget constraintWidget6 = constraintWidget3;
            i2 = 0;
            while (constraintWidget4.mRight.mTarget != null) {
                constraintWidget4.mHorizontalNextWidget = constraintWidget2;
                if (constraintWidget4.getVisibility() != 8) {
                    if (constraintWidget3 == null) {
                        constraintWidget3 = constraintWidget4;
                    }
                    if (constraintWidget6 != null && constraintWidget6 != constraintWidget4) {
                        constraintWidget6.mHorizontalNextWidget = constraintWidget4;
                    }
                    constraintWidget6 = constraintWidget4;
                } else {
                    linearSystem.addEquality(constraintWidget4.mLeft.mSolverVariable, constraintWidget4.mLeft.mTarget.mSolverVariable, 0, 5);
                    linearSystem.addEquality(constraintWidget4.mRight.mSolverVariable, constraintWidget4.mLeft.mSolverVariable, 0, 5);
                }
                if (constraintWidget4.getVisibility() != 8 && constraintWidget4.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    if (constraintWidget4.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        zArr[0] = false;
                    }
                    if (constraintWidget4.mDimensionRatio <= f) {
                        zArr[0] = false;
                        int i4 = i2 + 1;
                        ConstraintWidget[] constraintWidgetArr2 = this.mMatchConstraintsChainedWidgets;
                        if (i4 >= constraintWidgetArr2.length) {
                            int length = constraintWidgetArr2.length;
                            this.mMatchConstraintsChainedWidgets = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr2, length + length);
                        }
                        this.mMatchConstraintsChainedWidgets[i2] = constraintWidget4;
                        i2 = i4;
                    }
                }
                if (constraintWidget4.mRight.mTarget.mOwner.mLeft.mTarget == null || constraintWidget4.mRight.mTarget.mOwner.mLeft.mTarget.mOwner != constraintWidget4 || constraintWidget4.mRight.mTarget.mOwner == constraintWidget4) {
                    break;
                }
                constraintWidget5 = constraintWidget4.mRight.mTarget.mOwner;
                constraintWidget4 = constraintWidget5;
                constraintWidget2 = null;
                f = 0.0f;
            }
            if (constraintWidget4.mRight.mTarget != null && constraintWidget4.mRight.mTarget.mOwner != this) {
                z = false;
            }
            if (constraintWidget.mLeft.mTarget == null || constraintWidget5.mRight.mTarget == null) {
                zArr[1] = true;
            }
            constraintWidget.mHorizontalChainFixedPosition = z;
            constraintWidget5.mHorizontalNextWidget = null;
            constraintWidgetArr[0] = constraintWidget;
            constraintWidgetArr[2] = constraintWidget3;
            constraintWidgetArr[1] = constraintWidget5;
            constraintWidgetArr[3] = constraintWidget6;
        } else {
            boolean z2 = constraintWidget.mTop.mTarget == null || constraintWidget.mTop.mTarget.mOwner == this;
            constraintWidget.mVerticalNextWidget = null;
            ConstraintWidget constraintWidget7 = constraintWidget;
            ConstraintWidget constraintWidget8 = constraintWidget.getVisibility() != 8 ? constraintWidget : null;
            ConstraintWidget constraintWidget9 = constraintWidget8;
            ConstraintWidget constraintWidget10 = null;
            int i5 = 0;
            while (constraintWidget7.mBottom.mTarget != null) {
                constraintWidget7.mVerticalNextWidget = null;
                if (constraintWidget7.getVisibility() != 8) {
                    if (constraintWidget8 == null) {
                        constraintWidget8 = constraintWidget7;
                    }
                    if (constraintWidget9 != null && constraintWidget9 != constraintWidget7) {
                        constraintWidget9.mVerticalNextWidget = constraintWidget7;
                    }
                    constraintWidget9 = constraintWidget7;
                } else {
                    linearSystem.addEquality(constraintWidget7.mTop.mSolverVariable, constraintWidget7.mTop.mTarget.mSolverVariable, 0, i3);
                    linearSystem.addEquality(constraintWidget7.mBottom.mSolverVariable, constraintWidget7.mTop.mSolverVariable, 0, i3);
                }
                if (constraintWidget7.getVisibility() != 8 && constraintWidget7.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    if (constraintWidget7.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        zArr[0] = false;
                    }
                    if (constraintWidget7.mDimensionRatio <= 0.0f) {
                        zArr[0] = false;
                        int i6 = i5 + 1;
                        ConstraintWidget[] constraintWidgetArr3 = this.mMatchConstraintsChainedWidgets;
                        if (i6 >= constraintWidgetArr3.length) {
                            int length2 = constraintWidgetArr3.length;
                            this.mMatchConstraintsChainedWidgets = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr3, length2 + length2);
                        }
                        this.mMatchConstraintsChainedWidgets[i5] = constraintWidget7;
                        i5 = i6;
                    }
                }
                if (constraintWidget7.mBottom.mTarget.mOwner.mTop.mTarget == null || constraintWidget7.mBottom.mTarget.mOwner.mTop.mTarget.mOwner != constraintWidget7 || constraintWidget7.mBottom.mTarget.mOwner == constraintWidget7) {
                    break;
                }
                constraintWidget7 = constraintWidget7.mBottom.mTarget.mOwner;
                constraintWidget10 = constraintWidget7;
                i3 = 5;
            }
            i2 = i5;
            if (constraintWidget7.mBottom.mTarget != null && constraintWidget7.mBottom.mTarget.mOwner != this) {
                z2 = false;
            }
            if (constraintWidget.mTop.mTarget == null || constraintWidget10.mBottom.mTarget == null) {
                zArr[1] = true;
            }
            constraintWidget.mVerticalChainFixedPosition = z2;
            constraintWidget10.mVerticalNextWidget = null;
            constraintWidgetArr[0] = constraintWidget;
            constraintWidgetArr[2] = constraintWidget8;
            constraintWidgetArr[1] = constraintWidget10;
            constraintWidgetArr[3] = constraintWidget9;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            while (constraintWidget.mLeft.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mRight.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mRight.mTarget == constraintWidget.mLeft && constraintWidget.mLeft.mTarget.mOwner != constraintWidget) {
                constraintWidget = constraintWidget.mLeft.mTarget.mOwner;
            }
            addHorizontalChain(constraintWidget);
        } else if (i == 1) {
            while (constraintWidget.mTop.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mBottom.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mBottom.mTarget == constraintWidget.mTop && constraintWidget.mTop.mTarget.mOwner != constraintWidget) {
                constraintWidget = constraintWidget.mTop.mTarget.mOwner;
            }
            addVerticalChain(constraintWidget);
        }
    }

    public void findWrapSize(ArrayList arrayList, boolean[] zArr) {
        int size = arrayList.size();
        char c = 0;
        zArr[0] = true;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i < size) {
            ConstraintWidget constraintWidget = (ConstraintWidget) arrayList.get(i);
            if (!constraintWidget.isRoot()) {
                if (!constraintWidget.mHorizontalWrapVisited) {
                    findHorizontalWrapRecursive(constraintWidget, zArr);
                }
                if (!constraintWidget.mVerticalWrapVisited) {
                    findVerticalWrapRecursive(constraintWidget, zArr);
                }
                if (zArr[c]) {
                    int width = (constraintWidget.mDistToLeft + constraintWidget.mDistToRight) - constraintWidget.getWidth();
                    int height = (constraintWidget.mDistToTop + constraintWidget.mDistToBottom) - constraintWidget.getHeight();
                    if (constraintWidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                        width = constraintWidget.mRight.mMargin + constraintWidget.getWidth() + constraintWidget.mLeft.mMargin;
                    }
                    if (constraintWidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                        height = constraintWidget.mBottom.mMargin + constraintWidget.getHeight() + constraintWidget.mTop.mMargin;
                    }
                    if (constraintWidget.getVisibility() == 8) {
                        width = 0;
                        height = 0;
                    }
                    i2 = Math.max(i2, constraintWidget.mDistToLeft);
                    i3 = Math.max(i3, constraintWidget.mDistToRight);
                    i6 = Math.max(i6, constraintWidget.mDistToBottom);
                    i5 = Math.max(i5, constraintWidget.mDistToTop);
                    i4 = Math.max(i4, width);
                    i7 = Math.max(i7, height);
                } else {
                    return;
                }
            }
            i++;
            c = 0;
        }
        this.mWrapWidth = Math.max(this.mMinWidth, Math.max(Math.max(i2, i3), i4));
        this.mWrapHeight = Math.max(this.mMinHeight, Math.max(Math.max(i5, i6), i7));
        for (int i8 = 0; i8 < size; i8++) {
            ConstraintWidget constraintWidget2 = (ConstraintWidget) arrayList.get(i8);
            constraintWidget2.mHorizontalWrapVisited = false;
            constraintWidget2.mVerticalWrapVisited = false;
            constraintWidget2.mLeftHasCentered = false;
            constraintWidget2.mRightHasCentered = false;
            constraintWidget2.mTopHasCentered = false;
            constraintWidget2.mBottomHasCentered = false;
        }
    }
}
