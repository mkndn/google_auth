package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.Cache;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ConstraintWidget {
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    boolean mBottomHasCentered;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    DimensionBehaviour mHorizontalDimensionBehaviour;
    ConstraintWidget mHorizontalNextWidget;
    float mHorizontalWeight;
    boolean mHorizontalWrapVisited;
    boolean mLeftHasCentered;
    protected int mMinHeight;
    protected int mMinWidth;
    boolean mRightHasCentered;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    DimensionBehaviour mVerticalDimensionBehaviour;
    ConstraintWidget mVerticalNextWidget;
    float mVerticalWeight;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    private int mWrapHeight;
    private int mWrapWidth;
    public int mHorizontalResolution = -1;
    public int mVerticalResolution = -1;
    int mMatchConstraintDefaultWidth = 0;
    int mMatchConstraintDefaultHeight = 0;
    int mMatchConstraintMinWidth = 0;
    int mMatchConstraintMaxWidth = 0;
    int mMatchConstraintMinHeight = 0;
    int mMatchConstraintMaxHeight = 0;
    ConstraintAnchor mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
    ConstraintAnchor mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
    ConstraintAnchor mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
    ConstraintAnchor mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
    ConstraintAnchor mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
    ConstraintAnchor mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
    ConstraintAnchor mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
    ConstraintAnchor mCenter = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
    protected ArrayList mAnchors = new ArrayList();
    ConstraintWidget mParent = null;
    int mWidth = 0;
    int mHeight = 0;
    protected float mDimensionRatio = 0.0f;
    protected int mDimensionRatioSide = -1;
    private int mSolverLeft = 0;
    private int mSolverTop = 0;
    private int mSolverRight = 0;
    private int mSolverBottom = 0;
    protected int mX = 0;
    protected int mY = 0;
    private int mDrawX = 0;
    private int mDrawY = 0;
    private int mDrawWidth = 0;
    private int mDrawHeight = 0;
    protected int mOffsetX = 0;
    protected int mOffsetY = 0;
    int mBaselineDistance = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: android.support.constraint.solver.widgets.ConstraintWidget$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_X.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public ConstraintWidget() {
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalWeight = 0.0f;
        this.mVerticalWeight = 0.0f;
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        addAnchors();
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mBaseline);
    }

    private void applyConstraints(LinearSystem linearSystem, boolean z, boolean z2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, int i2, int i3, int i4, float f, boolean z3, boolean z4, int i5, int i6, int i7) {
        boolean z5;
        int i8;
        int i9 = i6;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintAnchor);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(constraintAnchor2);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(constraintAnchor.getTarget());
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(constraintAnchor2.getTarget());
        int margin = constraintAnchor.getMargin();
        int margin2 = constraintAnchor2.getMargin();
        if (this.mVisibility == 8) {
            z5 = true;
            i8 = 0;
        } else {
            z5 = z2;
            i8 = i3;
        }
        if (createObjectVariable3 == null && createObjectVariable4 == null) {
            linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, i));
            if (!z3) {
                if (z) {
                    linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i4, true));
                } else if (!z5) {
                    linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, i2));
                } else {
                    linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i8, false));
                }
            }
        } else if (createObjectVariable3 != null && createObjectVariable4 == null) {
            linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, createObjectVariable3, margin));
            if (z) {
                linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i4, true));
            } else if (!z3) {
                if (z5) {
                    linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable, i8));
                } else {
                    linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, i2));
                }
            }
        } else if (createObjectVariable3 == null && createObjectVariable4 != null) {
            linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable4, -margin2));
            if (z) {
                linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i4, true));
            } else if (!z3) {
                if (z5) {
                    linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable, i8));
                } else {
                    linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, i));
                }
            }
        } else if (z5) {
            if (!z) {
                linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable, i8));
            } else {
                linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i4, true));
            }
            if (constraintAnchor.getStrength() != constraintAnchor2.getStrength()) {
                if (constraintAnchor.getStrength() == ConstraintAnchor.Strength.STRONG) {
                    linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, createObjectVariable3, margin));
                    SolverVariable createSlackVariable = linearSystem.createSlackVariable();
                    ArrayRow createRow = linearSystem.createRow();
                    createRow.createRowLowerThan(createObjectVariable2, createObjectVariable4, createSlackVariable, -margin2);
                    linearSystem.addConstraint(createRow);
                    return;
                }
                SolverVariable createSlackVariable2 = linearSystem.createSlackVariable();
                ArrayRow createRow2 = linearSystem.createRow();
                createRow2.createRowGreaterThan(createObjectVariable, createObjectVariable3, createSlackVariable2, margin);
                linearSystem.addConstraint(createRow2);
                linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable4, -margin2));
            } else if (createObjectVariable3 == createObjectVariable4) {
                linearSystem.addConstraint(LinearSystem.createRowCentering(linearSystem, createObjectVariable, createObjectVariable3, 0, 0.5f, createObjectVariable4, createObjectVariable2, 0, true));
            } else if (!z4) {
                linearSystem.addConstraint(LinearSystem.createRowGreaterThan(linearSystem, createObjectVariable, createObjectVariable3, margin, constraintAnchor.getConnectionType() != ConstraintAnchor.ConnectionType.STRICT));
                linearSystem.addConstraint(LinearSystem.createRowLowerThan(linearSystem, createObjectVariable2, createObjectVariable4, -margin2, constraintAnchor2.getConnectionType() != ConstraintAnchor.ConnectionType.STRICT));
                linearSystem.addConstraint(LinearSystem.createRowCentering(linearSystem, createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2, false));
            }
        } else if (z3) {
            linearSystem.addGreaterThan(createObjectVariable, createObjectVariable3, margin, 3);
            linearSystem.addLowerThan(createObjectVariable2, createObjectVariable4, -margin2, 3);
            linearSystem.addConstraint(LinearSystem.createRowCentering(linearSystem, createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2, true));
        } else if (z4) {
        } else {
            if (i5 == 1) {
                if (i9 <= i8) {
                    i9 = i8;
                }
                if (i7 > 0) {
                    if (i7 >= i9) {
                        linearSystem.addLowerThan(createObjectVariable2, createObjectVariable, i7, 3);
                    } else {
                        i9 = i7;
                    }
                }
                linearSystem.addEquality(createObjectVariable2, createObjectVariable, i9, 3);
                linearSystem.addGreaterThan(createObjectVariable, createObjectVariable3, margin, 2);
                linearSystem.addLowerThan(createObjectVariable2, createObjectVariable4, -margin2, 2);
                linearSystem.addCentering(createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2, 4);
            } else if (i9 == 0 && i7 == 0) {
                linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, createObjectVariable3, margin));
                linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable4, -margin2));
            } else {
                if (i7 > 0) {
                    linearSystem.addLowerThan(createObjectVariable2, createObjectVariable, i7, 3);
                }
                linearSystem.addGreaterThan(createObjectVariable, createObjectVariable3, margin, 2);
                linearSystem.addLowerThan(createObjectVariable2, createObjectVariable4, -margin2, 2);
                linearSystem.addCentering(createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2, 4);
            }
        }
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (AnonymousClass1.$SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[type.ordinal()]) {
            case 1:
                return this.mLeft;
            case 2:
                return this.mTop;
            case 3:
                return this.mRight;
            case 4:
                return this.mBottom;
            case 5:
                return this.mBaseline;
            case 6:
                return this.mCenterX;
            case 7:
                return this.mCenterY;
            case 8:
                return this.mCenter;
            default:
                return null;
        }
    }

    public ArrayList getAnchors() {
        return this.mAnchors;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public int getDrawBottom() {
        return getDrawY() + this.mDrawHeight;
    }

    public int getDrawRight() {
        return getDrawX() + this.mDrawWidth;
    }

    public int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mHorizontalDimensionBehaviour;
    }

    public int getOptimizerWrapHeight() {
        int i = this.mHeight;
        if (this.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultHeight == 1) {
                i = Math.max(this.mMatchConstraintMinHeight, i);
            } else {
                i = this.mMatchConstraintMinHeight;
                if (i > 0) {
                    this.mHeight = i;
                } else {
                    i = 0;
                }
            }
            int i2 = this.mMatchConstraintMaxHeight;
            if (i2 > 0 && i2 < i) {
                return i2;
            }
        }
        return i;
    }

    public int getOptimizerWrapWidth() {
        int i = this.mWidth;
        if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultWidth == 1) {
                i = Math.max(this.mMatchConstraintMinWidth, i);
            } else {
                i = this.mMatchConstraintMinWidth;
                if (i > 0) {
                    this.mWidth = i;
                } else {
                    i = 0;
                }
            }
            int i2 = this.mMatchConstraintMaxWidth;
            if (i2 > 0 && i2 < i) {
                return i2;
            }
        }
        return i;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getRootX() {
        return this.mX + this.mOffsetX;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mVerticalDimensionBehaviour;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public boolean hasBaseline() {
        return this.mBaselineDistance > 0;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, ConstraintAnchor.Strength.STRONG, 0, true);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        this.mHorizontalWeight = 0.0f;
        this.mVerticalWeight = 0.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent != null && (parent instanceof ConstraintWidgetContainer) && ((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            return;
        }
        int size = this.mAnchors.size();
        for (int i = 0; i < size; i++) {
            ((ConstraintAnchor) this.mAnchors.get(i)).reset();
        }
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setDimensionRatio(String str) {
        int i;
        float parseFloat;
        if (str != null && str.length() != 0) {
            int length = str.length();
            int indexOf = str.indexOf(44);
            int i2 = 0;
            if (indexOf > 0 && indexOf < length - 1) {
                String substring = str.substring(0, indexOf);
                if (!substring.equalsIgnoreCase("W")) {
                    i2 = substring.equalsIgnoreCase("H") ? 1 : -1;
                }
                int i3 = i2;
                i2 = indexOf + 1;
                i = i3;
            } else {
                i = -1;
            }
            int indexOf2 = str.indexOf(58);
            if (indexOf2 >= 0 && indexOf2 < length - 1) {
                String substring2 = str.substring(i2, indexOf2);
                String substring3 = str.substring(indexOf2 + 1);
                if (substring2.length() > 0 && substring3.length() > 0) {
                    try {
                        float parseFloat2 = Float.parseFloat(substring2);
                        float parseFloat3 = Float.parseFloat(substring3);
                        if (parseFloat2 <= 0.0f || parseFloat3 <= 0.0f) {
                            parseFloat = 0.0f;
                        } else if (i == 1) {
                            parseFloat = Math.abs(parseFloat3 / parseFloat2);
                        } else {
                            parseFloat = Math.abs(parseFloat2 / parseFloat3);
                        }
                    } catch (NumberFormatException e) {
                    }
                }
                parseFloat = 0.0f;
            } else {
                String substring4 = str.substring(i2);
                if (substring4.length() > 0) {
                    try {
                        parseFloat = Float.parseFloat(substring4);
                    } catch (NumberFormatException e2) {
                    }
                }
                parseFloat = 0.0f;
            }
            if (parseFloat > 0.0f) {
                this.mDimensionRatio = parseFloat;
                this.mDimensionRatioSide = i;
                return;
            }
            return;
        }
        this.mDimensionRatio = 0.0f;
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3 - i;
        int i8 = i4 - i2;
        this.mX = i;
        this.mY = i2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.FIXED && i7 < (i6 = this.mWidth)) {
            i7 = i6;
        }
        if (this.mVerticalDimensionBehaviour == DimensionBehaviour.FIXED && i8 < (i5 = this.mHeight)) {
            i8 = i5;
        }
        this.mWidth = i7;
        this.mHeight = i8;
        int i9 = this.mMinHeight;
        if (i8 < i9) {
            this.mHeight = i9;
        }
        int i10 = this.mMinWidth;
        if (i7 < i10) {
            this.mWidth = i10;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public void setHorizontalDimension(int i, int i2) {
        this.mX = i;
        int i3 = i2 - i;
        this.mWidth = i3;
        int i4 = this.mMinWidth;
        if (i3 < i4) {
            this.mWidth = i4;
        }
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mHorizontalDimensionBehaviour = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        this.mMatchConstraintMaxWidth = i3;
    }

    public void setHorizontalWeight(float f) {
        this.mHorizontalWeight = f;
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public void setOrigin(int i, int i2) {
        this.mX = i;
        this.mY = i2;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public void setVerticalDimension(int i, int i2) {
        this.mY = i;
        int i3 = i2 - i;
        this.mHeight = i3;
        int i4 = this.mMinHeight;
        if (i3 < i4) {
            this.mHeight = i4;
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mVerticalDimensionBehaviour = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    public void setVerticalMatchStyle(int i, int i2, int i3) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        this.mMatchConstraintMaxHeight = i3;
    }

    public void setVerticalWeight(float f) {
        this.mVerticalWeight = f;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public void setWidth(int i) {
        this.mWidth = i;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
    }

    public void setWrapHeight(int i) {
        this.mWrapHeight = i;
    }

    public void setWrapWidth(int i) {
        this.mWrapWidth = i;
    }

    public void setX(int i) {
        this.mX = i;
    }

    public void setY(int i) {
        this.mY = i;
    }

    public String toString() {
        return (this.mType != null ? "type: " + this.mType + " " : "") + (this.mDebugName != null ? "id: " + this.mDebugName + " " : "") + "(" + this.mX + ", " + this.mY + ") - (" + this.mWidth + " x " + this.mHeight + ") wrap: (" + this.mWrapWidth + " x " + this.mWrapHeight + ")";
    }

    public void updateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mWidth;
        int i4 = this.mHeight;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = (i3 + i) - i;
        this.mDrawHeight = (i4 + i2) - i2;
    }

    public void updateFromSolver(LinearSystem linearSystem, int i) {
        if (i == Integer.MAX_VALUE) {
            setFrame(linearSystem.getObjectVariableValue(this.mLeft), linearSystem.getObjectVariableValue(this.mTop), linearSystem.getObjectVariableValue(this.mRight), linearSystem.getObjectVariableValue(this.mBottom));
        } else if (i == -2) {
            setFrame(this.mSolverLeft, this.mSolverTop, this.mSolverRight, this.mSolverBottom);
        } else {
            if (this.mLeft.mGroup == i) {
                this.mSolverLeft = linearSystem.getObjectVariableValue(this.mLeft);
            }
            if (this.mTop.mGroup == i) {
                this.mSolverTop = linearSystem.getObjectVariableValue(this.mTop);
            }
            if (this.mRight.mGroup == i) {
                this.mSolverRight = linearSystem.getObjectVariableValue(this.mRight);
            }
            if (this.mBottom.mGroup == i) {
                this.mSolverBottom = linearSystem.getObjectVariableValue(this.mBottom);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:195:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x041d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:217:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0441  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x053f  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x05ff  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x068a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addToSolver(LinearSystem linearSystem, int i) {
        boolean z;
        boolean z2;
        int i2;
        int i3;
        boolean z3;
        int i4;
        float f;
        boolean z4;
        boolean z5;
        int i5;
        SolverVariable solverVariable;
        SolverVariable solverVariable2;
        SolverVariable solverVariable3;
        SolverVariable solverVariable4;
        int i6;
        SolverVariable solverVariable5;
        int i7;
        boolean z6;
        int i8;
        ConstraintWidget constraintWidget;
        SolverVariable solverVariable6;
        ConstraintWidget constraintWidget2;
        SolverVariable solverVariable7;
        SolverVariable solverVariable8;
        int i9;
        SolverVariable solverVariable9;
        SolverVariable solverVariable10;
        LinearSystem linearSystem2;
        SolverVariable solverVariable11;
        ConstraintAnchor constraintAnchor;
        int i10;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        boolean z7;
        boolean z8;
        SolverVariable createObjectVariable = (i == Integer.MAX_VALUE || this.mLeft.mGroup == i) ? linearSystem.createObjectVariable(this.mLeft) : null;
        SolverVariable createObjectVariable2 = (i == Integer.MAX_VALUE || this.mRight.mGroup == i) ? linearSystem.createObjectVariable(this.mRight) : null;
        SolverVariable createObjectVariable3 = (i == Integer.MAX_VALUE || this.mTop.mGroup == i) ? linearSystem.createObjectVariable(this.mTop) : null;
        SolverVariable createObjectVariable4 = (i == Integer.MAX_VALUE || this.mBottom.mGroup == i) ? linearSystem.createObjectVariable(this.mBottom) : null;
        SolverVariable createObjectVariable5 = (i == Integer.MAX_VALUE || this.mBaseline.mGroup == i) ? linearSystem.createObjectVariable(this.mBaseline) : null;
        if (this.mParent != null) {
            if ((this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != this.mLeft) && (this.mRight.mTarget == null || this.mRight.mTarget.mTarget != this.mRight)) {
                z7 = false;
            } else {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 0);
                z7 = true;
            }
            if ((this.mTop.mTarget == null || this.mTop.mTarget.mTarget != this.mTop) && (this.mBottom.mTarget == null || this.mBottom.mTarget.mTarget != this.mBottom)) {
                z8 = false;
            } else {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 1);
                z8 = true;
            }
            if (this.mParent.getHorizontalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT && !z7) {
                if (this.mLeft.mTarget == null || this.mLeft.mTarget.mOwner != this.mParent) {
                    SolverVariable createObjectVariable6 = linearSystem.createObjectVariable(this.mParent.mLeft);
                    ArrayRow createRow = linearSystem.createRow();
                    createRow.createRowGreaterThan(createObjectVariable, createObjectVariable6, linearSystem.createSlackVariable(), 0);
                    linearSystem.addConstraint(createRow);
                } else if (this.mLeft.mTarget != null && this.mLeft.mTarget.mOwner == this.mParent) {
                    this.mLeft.setConnectionType(ConstraintAnchor.ConnectionType.STRICT);
                }
                if (this.mRight.mTarget == null || this.mRight.mTarget.mOwner != this.mParent) {
                    SolverVariable createObjectVariable7 = linearSystem.createObjectVariable(this.mParent.mRight);
                    ArrayRow createRow2 = linearSystem.createRow();
                    createRow2.createRowGreaterThan(createObjectVariable7, createObjectVariable2, linearSystem.createSlackVariable(), 0);
                    linearSystem.addConstraint(createRow2);
                } else if (this.mRight.mTarget != null && this.mRight.mTarget.mOwner == this.mParent) {
                    this.mRight.setConnectionType(ConstraintAnchor.ConnectionType.STRICT);
                }
            }
            if (this.mParent.getVerticalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT && !z8) {
                if (this.mTop.mTarget == null || this.mTop.mTarget.mOwner != this.mParent) {
                    SolverVariable createObjectVariable8 = linearSystem.createObjectVariable(this.mParent.mTop);
                    ArrayRow createRow3 = linearSystem.createRow();
                    createRow3.createRowGreaterThan(createObjectVariable3, createObjectVariable8, linearSystem.createSlackVariable(), 0);
                    linearSystem.addConstraint(createRow3);
                } else if (this.mTop.mTarget != null && this.mTop.mTarget.mOwner == this.mParent) {
                    this.mTop.setConnectionType(ConstraintAnchor.ConnectionType.STRICT);
                }
                if (this.mBottom.mTarget == null || this.mBottom.mTarget.mOwner != this.mParent) {
                    SolverVariable createObjectVariable9 = linearSystem.createObjectVariable(this.mParent.mBottom);
                    ArrayRow createRow4 = linearSystem.createRow();
                    createRow4.createRowGreaterThan(createObjectVariable9, createObjectVariable4, linearSystem.createSlackVariable(), 0);
                    linearSystem.addConstraint(createRow4);
                } else if (this.mBottom.mTarget != null && this.mBottom.mTarget.mOwner == this.mParent) {
                    this.mBottom.setConnectionType(ConstraintAnchor.ConnectionType.STRICT);
                }
            }
            z = z7;
            z2 = z8;
        } else {
            z = false;
            z2 = false;
        }
        int i11 = this.mWidth;
        int i12 = this.mMinWidth;
        if (i11 < i12) {
            i11 = i12;
        }
        int i13 = this.mHeight;
        int i14 = this.mMinHeight;
        if (i13 < i14) {
            i13 = i14;
        }
        boolean z9 = this.mHorizontalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z10 = this.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT;
        if (!z9 && (constraintAnchor3 = this.mLeft) != null && this.mRight != null && (constraintAnchor3.mTarget == null || this.mRight.mTarget == null)) {
            z9 = true;
        }
        if (!z10 && (constraintAnchor2 = this.mTop) != null && this.mBottom != null && ((constraintAnchor2.mTarget == null || this.mBottom.mTarget == null) && (this.mBaselineDistance == 0 || (this.mBaseline != null && (this.mTop.mTarget == null || this.mBaseline.mTarget == null))))) {
            z10 = true;
        }
        int i15 = this.mDimensionRatioSide;
        float f2 = this.mDimensionRatio;
        SolverVariable solverVariable12 = createObjectVariable4;
        if (f2 > 0.0f && this.mVisibility != 8) {
            if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && this.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                if (z9 && !z10) {
                    i2 = i11;
                    i3 = i13;
                    z3 = z10;
                    f = f2;
                    i4 = 0;
                } else if (z9 || !z10) {
                    i2 = i11;
                    i3 = i13;
                    z3 = z10;
                    i4 = i15;
                    f = f2;
                } else if (this.mDimensionRatioSide == -1) {
                    i2 = i11;
                    i3 = i13;
                    f = 1.0f / f2;
                    i4 = 1;
                    z4 = true;
                    z3 = z10;
                    z5 = z9;
                } else {
                    i2 = i11;
                    i3 = i13;
                    z3 = z10;
                    f = f2;
                    i4 = 1;
                }
                z4 = true;
                z5 = z9;
            } else if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                i2 = (int) (this.mHeight * f2);
                i3 = i13;
                z3 = z10;
                f = f2;
                z5 = true;
                i4 = 0;
                z4 = false;
            } else if (this.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                if (this.mDimensionRatioSide == -1) {
                    f2 = 1.0f / f2;
                }
                i2 = i11;
                i3 = (int) (this.mWidth * f2);
                z5 = z9;
                f = f2;
                i4 = 1;
                z3 = true;
                z4 = false;
            }
            boolean z11 = !z4 && (i4 == 0 || i4 == -1);
            boolean z12 = this.mHorizontalDimensionBehaviour != DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
            if (this.mHorizontalResolution != 2) {
                i5 = i3;
                solverVariable = createObjectVariable5;
                solverVariable2 = createObjectVariable3;
                solverVariable3 = createObjectVariable2;
                solverVariable4 = createObjectVariable;
                i6 = i4;
                solverVariable5 = solverVariable12;
            } else if (i != Integer.MAX_VALUE && (this.mLeft.mGroup != i || this.mRight.mGroup != i)) {
                i5 = i3;
                solverVariable = createObjectVariable5;
                solverVariable2 = createObjectVariable3;
                solverVariable3 = createObjectVariable2;
                solverVariable4 = createObjectVariable;
                i6 = i4;
                solverVariable5 = solverVariable12;
            } else if (!z11 || this.mLeft.mTarget == null || this.mRight.mTarget == null) {
                i5 = i3;
                solverVariable = createObjectVariable5;
                solverVariable5 = solverVariable12;
                ConstraintAnchor constraintAnchor4 = this.mLeft;
                ConstraintAnchor constraintAnchor5 = this.mRight;
                int i16 = this.mX;
                int i17 = i2;
                solverVariable2 = createObjectVariable3;
                solverVariable3 = createObjectVariable2;
                solverVariable4 = createObjectVariable;
                i6 = i4;
                applyConstraints(linearSystem, z12, z5, constraintAnchor4, constraintAnchor5, i16, i16 + i2, i17, this.mMinWidth, this.mHorizontalBiasPercent, z11, z, this.mMatchConstraintDefaultWidth, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth);
            } else {
                SolverVariable createObjectVariable10 = linearSystem.createObjectVariable(this.mLeft);
                SolverVariable createObjectVariable11 = linearSystem.createObjectVariable(this.mRight);
                SolverVariable createObjectVariable12 = linearSystem.createObjectVariable(this.mLeft.getTarget());
                SolverVariable createObjectVariable13 = linearSystem.createObjectVariable(this.mRight.getTarget());
                linearSystem.addGreaterThan(createObjectVariable10, createObjectVariable12, this.mLeft.getMargin(), 3);
                linearSystem.addLowerThan(createObjectVariable11, createObjectVariable13, -this.mRight.getMargin(), 3);
                if (z) {
                    i5 = i3;
                    solverVariable = createObjectVariable5;
                    solverVariable5 = solverVariable12;
                    solverVariable2 = createObjectVariable3;
                    solverVariable3 = createObjectVariable2;
                    solverVariable4 = createObjectVariable;
                    i6 = i4;
                } else {
                    i5 = i3;
                    solverVariable = createObjectVariable5;
                    solverVariable5 = solverVariable12;
                    linearSystem.addCentering(createObjectVariable10, createObjectVariable12, this.mLeft.getMargin(), this.mHorizontalBiasPercent, createObjectVariable13, createObjectVariable11, this.mRight.getMargin(), 4);
                    solverVariable2 = createObjectVariable3;
                    solverVariable3 = createObjectVariable2;
                    solverVariable4 = createObjectVariable;
                    i6 = i4;
                }
            }
            if (this.mVerticalResolution != 2) {
                return;
            }
            boolean z13 = this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
            if (z4) {
                i7 = i6;
                if (i7 == 1 || i7 == -1) {
                    z6 = true;
                    if (this.mBaselineDistance <= 0) {
                        ConstraintAnchor constraintAnchor6 = this.mBottom;
                        if (i == Integer.MAX_VALUE || (constraintAnchor6.mGroup == i && this.mBaseline.mGroup == i)) {
                            linearSystem2 = linearSystem;
                            solverVariable11 = solverVariable2;
                            linearSystem2.addEquality(solverVariable, solverVariable11, getBaselineDistance(), 5);
                        } else {
                            linearSystem2 = linearSystem;
                            solverVariable11 = solverVariable2;
                        }
                        if (this.mBaseline.mTarget != null) {
                            i10 = this.mBaselineDistance;
                            constraintAnchor = this.mBaseline;
                        } else {
                            constraintAnchor = constraintAnchor6;
                            i10 = i5;
                        }
                        if (i != Integer.MAX_VALUE && (this.mTop.mGroup != i || constraintAnchor.mGroup != i)) {
                            solverVariable6 = solverVariable11;
                            i8 = i7;
                        } else if (!z6 || this.mTop.mTarget == null || this.mBottom.mTarget == null) {
                            solverVariable6 = solverVariable11;
                            ConstraintAnchor constraintAnchor7 = this.mTop;
                            int i18 = this.mY;
                            i8 = i7;
                            applyConstraints(linearSystem, z13, z3, constraintAnchor7, constraintAnchor, i18, i18 + i10, i10, this.mMinHeight, this.mVerticalBiasPercent, z6, z2, this.mMatchConstraintDefaultHeight, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight);
                            linearSystem.addEquality(solverVariable5, solverVariable6, i5, 5);
                        } else {
                            SolverVariable createObjectVariable14 = linearSystem2.createObjectVariable(this.mTop);
                            SolverVariable createObjectVariable15 = linearSystem2.createObjectVariable(this.mBottom);
                            SolverVariable createObjectVariable16 = linearSystem2.createObjectVariable(this.mTop.getTarget());
                            SolverVariable createObjectVariable17 = linearSystem2.createObjectVariable(this.mBottom.getTarget());
                            linearSystem2.addGreaterThan(createObjectVariable14, createObjectVariable16, this.mTop.getMargin(), 3);
                            linearSystem2.addLowerThan(createObjectVariable15, createObjectVariable17, -this.mBottom.getMargin(), 3);
                            if (z2) {
                                solverVariable6 = solverVariable11;
                                i8 = i7;
                            } else {
                                solverVariable6 = solverVariable11;
                                linearSystem.addCentering(createObjectVariable14, createObjectVariable16, this.mTop.getMargin(), this.mVerticalBiasPercent, createObjectVariable17, createObjectVariable15, this.mBottom.getMargin(), 4);
                                i8 = i7;
                            }
                        }
                    } else {
                        i8 = i7;
                        int i19 = i5;
                        SolverVariable solverVariable13 = solverVariable5;
                        SolverVariable solverVariable14 = solverVariable2;
                        if (i != Integer.MAX_VALUE) {
                            constraintWidget = this;
                            if (constraintWidget.mTop.mGroup != i || constraintWidget.mBottom.mGroup != i) {
                                solverVariable5 = solverVariable13;
                                solverVariable6 = solverVariable14;
                            }
                        } else {
                            constraintWidget = this;
                        }
                        if (!z6 || constraintWidget.mTop.mTarget == null || constraintWidget.mBottom.mTarget == null) {
                            ConstraintAnchor constraintAnchor8 = constraintWidget.mTop;
                            ConstraintAnchor constraintAnchor9 = constraintWidget.mBottom;
                            int i20 = constraintWidget.mY;
                            solverVariable5 = solverVariable13;
                            solverVariable6 = solverVariable14;
                            applyConstraints(linearSystem, z13, z3, constraintAnchor8, constraintAnchor9, i20, i20 + i19, i19, constraintWidget.mMinHeight, constraintWidget.mVerticalBiasPercent, z6, z2, constraintWidget.mMatchConstraintDefaultHeight, constraintWidget.mMatchConstraintMinHeight, constraintWidget.mMatchConstraintMaxHeight);
                        } else {
                            SolverVariable createObjectVariable18 = linearSystem.createObjectVariable(constraintWidget.mTop);
                            SolverVariable createObjectVariable19 = linearSystem.createObjectVariable(constraintWidget.mBottom);
                            SolverVariable createObjectVariable20 = linearSystem.createObjectVariable(constraintWidget.mTop.getTarget());
                            SolverVariable createObjectVariable21 = linearSystem.createObjectVariable(constraintWidget.mBottom.getTarget());
                            linearSystem.addGreaterThan(createObjectVariable18, createObjectVariable20, constraintWidget.mTop.getMargin(), 3);
                            linearSystem.addLowerThan(createObjectVariable19, createObjectVariable21, -constraintWidget.mBottom.getMargin(), 3);
                            if (z2) {
                                solverVariable5 = solverVariable13;
                                solverVariable6 = solverVariable14;
                            } else {
                                linearSystem.addCentering(createObjectVariable18, createObjectVariable20, constraintWidget.mTop.getMargin(), constraintWidget.mVerticalBiasPercent, createObjectVariable21, createObjectVariable19, constraintWidget.mBottom.getMargin(), 4);
                                solverVariable5 = solverVariable13;
                                solverVariable6 = solverVariable14;
                            }
                        }
                    }
                    if (z4) {
                        return;
                    }
                    ArrayRow createRow5 = linearSystem.createRow();
                    if (i != Integer.MAX_VALUE) {
                        constraintWidget2 = this;
                        if (constraintWidget2.mLeft.mGroup != i || constraintWidget2.mRight.mGroup != i) {
                            return;
                        }
                    } else {
                        constraintWidget2 = this;
                    }
                    int i21 = i8;
                    if (i21 == 0) {
                        linearSystem.addConstraint(createRow5.createRowDimensionRatio(solverVariable3, solverVariable4, solverVariable5, solverVariable6, f));
                        return;
                    } else if (i21 == 1) {
                        linearSystem.addConstraint(createRow5.createRowDimensionRatio(solverVariable5, solverVariable6, solverVariable3, solverVariable4, f));
                        return;
                    } else {
                        int i22 = constraintWidget2.mMatchConstraintMinWidth;
                        if (i22 > 0) {
                            solverVariable7 = solverVariable4;
                            solverVariable8 = solverVariable3;
                            i9 = 3;
                            linearSystem.addGreaterThan(solverVariable8, solverVariable7, i22, 3);
                        } else {
                            solverVariable7 = solverVariable4;
                            solverVariable8 = solverVariable3;
                            i9 = 3;
                        }
                        int i23 = constraintWidget2.mMatchConstraintMinHeight;
                        if (i23 > 0) {
                            solverVariable9 = solverVariable5;
                            solverVariable10 = solverVariable6;
                            linearSystem.addGreaterThan(solverVariable9, solverVariable10, i23, i9);
                        } else {
                            solverVariable9 = solverVariable5;
                            solverVariable10 = solverVariable6;
                        }
                        createRow5.createRowDimensionRatio(solverVariable8, solverVariable7, solverVariable9, solverVariable10, f);
                        SolverVariable createErrorVariable = linearSystem.createErrorVariable();
                        SolverVariable createErrorVariable2 = linearSystem.createErrorVariable();
                        createErrorVariable.strength = 4;
                        createErrorVariable2.strength = 4;
                        createRow5.addError(createErrorVariable, createErrorVariable2);
                        linearSystem.addConstraint(createRow5);
                        return;
                    }
                }
            } else {
                i7 = i6;
            }
            z6 = false;
            if (this.mBaselineDistance <= 0) {
            }
            if (z4) {
            }
        }
        i2 = i11;
        i3 = i13;
        z3 = z10;
        i4 = i15;
        f = f2;
        z4 = false;
        z5 = z9;
        if (z4) {
        }
        if (this.mHorizontalDimensionBehaviour != DimensionBehaviour.WRAP_CONTENT) {
        }
        if (this.mHorizontalResolution != 2) {
        }
        if (this.mVerticalResolution != 2) {
        }
    }
}
