package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.card.MaterialCardView;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    private void forceUniformHeight(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, makeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    private void forceUniformWidth(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    void drawDividersHorizontal(Canvas canvas) {
        int right;
        int left;
        int virtualChildCount = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i = 0; i < virtualChildCount; i++) {
            View virtualChildAt = getVirtualChildAt(i);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (isLayoutRtl) {
                    left = virtualChildAt.getRight() + layoutParams.rightMargin;
                } else {
                    left = (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, left);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                if (isLayoutRtl) {
                    right = getPaddingLeft();
                } else {
                    right = (getWidth() - getPaddingRight()) - this.mDividerWidth;
                }
            } else {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (isLayoutRtl) {
                    right = (virtualChildAt2.getLeft() - layoutParams2.leftMargin) - this.mDividerWidth;
                } else {
                    right = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                }
            }
            drawVerticalDivider(canvas, right);
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int bottom;
        int virtualChildCount = getVirtualChildCount();
        for (int i = 0; i < virtualChildCount; i++) {
            View virtualChildAt = getVirtualChildAt(i);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                bottom = virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, bottom);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        int i = this.mOrientation;
        if (i == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.mBaselineAlignedChildIndex;
        if (childCount <= i2) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i2);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int i3 = this.mBaselineChildTop;
        if (this.mOrientation == 1 && (i = this.mGravity & 112) != 48) {
            switch (i) {
                case 16:
                    i3 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                    break;
                case 80:
                    i3 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                    break;
            }
        }
        return i3 + ((LayoutParams) childAt.getLayoutParams()).topMargin + baseline;
    }

    int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    public int getGravity() {
        return this.mGravity;
    }

    int getLocationOffset(View view) {
        return 0;
    }

    int getNextLocationOffset(View view) {
        return 0;
    }

    View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasDividerBeforeChildAt(int i) {
        if (i == 0) {
            return (this.mShowDividers & 1) != 0;
        } else if (i == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        } else if ((this.mShowDividers & 2) == 0) {
            return false;
        } else {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (getChildAt(i2).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0106  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void layoutHorizontal(int i, int i2, int i3, int i4) {
        int paddingLeft;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingTop = getPaddingTop();
        int i14 = i4 - i2;
        int paddingBottom = i14 - getPaddingBottom();
        int paddingBottom2 = (i14 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        int i15 = this.mGravity;
        int i16 = i15 & 112;
        boolean z = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        switch (GravityCompat.getAbsoluteGravity(i15 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK, ViewCompat.getLayoutDirection(this))) {
            case 1:
                paddingLeft = getPaddingLeft() + (((i3 - i) - this.mTotalLength) / 2);
                break;
            case 5:
                paddingLeft = ((getPaddingLeft() + i3) - i) - this.mTotalLength;
                break;
            default:
                paddingLeft = getPaddingLeft();
                break;
        }
        int i17 = -1;
        if (isLayoutRtl) {
            i5 = virtualChildCount - 1;
            i6 = -1;
        } else {
            i5 = 0;
            i6 = 1;
        }
        int i18 = 0;
        while (i18 < virtualChildCount) {
            int i19 = i5 + (i6 * i18);
            View virtualChildAt = getVirtualChildAt(i19);
            if (virtualChildAt == null) {
                paddingLeft += measureNullChild(i19);
                i7 = paddingTop;
                i8 = paddingBottom;
                i9 = paddingBottom2;
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (z) {
                    i10 = i18;
                    if (layoutParams.height != i17) {
                        i11 = virtualChildAt.getBaseline();
                        i12 = layoutParams.gravity;
                        if (i12 < 0) {
                            i12 = i16;
                        }
                        switch (i12 & 112) {
                            case 16:
                                i8 = paddingBottom;
                                i13 = ((((paddingBottom2 - measuredHeight) / 2) + paddingTop) + layoutParams.topMargin) - layoutParams.bottomMargin;
                                break;
                            case 48:
                                i8 = paddingBottom;
                                i13 = layoutParams.topMargin + paddingTop;
                                if (i11 != -1) {
                                    i13 += iArr[1] - i11;
                                    break;
                                }
                                break;
                            case 80:
                                i8 = paddingBottom;
                                i13 = (paddingBottom - measuredHeight) - layoutParams.bottomMargin;
                                if (i11 == -1) {
                                    break;
                                } else {
                                    i13 -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - i11);
                                    break;
                                }
                            default:
                                i8 = paddingBottom;
                                i13 = paddingTop;
                                break;
                        }
                        if (hasDividerBeforeChildAt(i19)) {
                            paddingLeft += this.mDividerWidth;
                        }
                        int i20 = paddingLeft + layoutParams.leftMargin;
                        i7 = paddingTop;
                        i9 = paddingBottom2;
                        setChildFrame(virtualChildAt, i20 + getLocationOffset(virtualChildAt), i13, measuredWidth, measuredHeight);
                        i18 = i10 + getChildrenSkipCount(virtualChildAt, i19);
                        paddingLeft = i20 + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(virtualChildAt);
                    }
                } else {
                    i10 = i18;
                }
                i11 = -1;
                i12 = layoutParams.gravity;
                if (i12 < 0) {
                }
                switch (i12 & 112) {
                    case 16:
                        break;
                    case 48:
                        break;
                    case 80:
                        break;
                }
                if (hasDividerBeforeChildAt(i19)) {
                }
                int i202 = paddingLeft + layoutParams.leftMargin;
                i7 = paddingTop;
                i9 = paddingBottom2;
                setChildFrame(virtualChildAt, i202 + getLocationOffset(virtualChildAt), i13, measuredWidth, measuredHeight);
                i18 = i10 + getChildrenSkipCount(virtualChildAt, i19);
                paddingLeft = i202 + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(virtualChildAt);
            } else {
                i7 = paddingTop;
                i8 = paddingBottom;
                i9 = paddingBottom2;
            }
            i18++;
            paddingBottom2 = i9;
            paddingBottom = i8;
            paddingTop = i7;
            i17 = -1;
        }
    }

    void layoutVertical(int i, int i2, int i3, int i4) {
        int paddingTop;
        int i5;
        int paddingLeft = getPaddingLeft();
        int i6 = i3 - i;
        int paddingRight = i6 - getPaddingRight();
        int paddingRight2 = (i6 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i7 = this.mGravity;
        int i8 = i7 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        switch (i7 & 112) {
            case 16:
                paddingTop = getPaddingTop() + (((i4 - i2) - this.mTotalLength) / 2);
                break;
            case 80:
                paddingTop = ((getPaddingTop() + i4) - i2) - this.mTotalLength;
                break;
            default:
                paddingTop = getPaddingTop();
                break;
        }
        int i9 = 0;
        while (i9 < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i9);
            if (virtualChildAt == null) {
                paddingTop += measureNullChild(i9);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                int i10 = layoutParams.gravity;
                if (i10 < 0) {
                    i10 = i8;
                }
                switch (GravityCompat.getAbsoluteGravity(i10, ViewCompat.getLayoutDirection(this)) & 7) {
                    case 1:
                        i5 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                        break;
                    case 5:
                        i5 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                        break;
                    default:
                        i5 = layoutParams.leftMargin + paddingLeft;
                        break;
                }
                if (hasDividerBeforeChildAt(i9)) {
                    paddingTop += this.mDividerHeight;
                }
                int i11 = paddingTop + layoutParams.topMargin;
                setChildFrame(virtualChildAt, i5, i11 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                i9 += getChildrenSkipCount(virtualChildAt, i9);
                paddingTop = i11 + measuredHeight + layoutParams.bottomMargin + getNextLocationOffset(virtualChildAt);
            }
            i9++;
        }
    }

    void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void measureHorizontal(int i, int i2) {
        int[] iArr;
        int i3;
        int i4;
        int i5;
        int baseline;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z;
        boolean z2;
        View view;
        int i10;
        boolean z3;
        int measuredHeight;
        int max;
        int baseline2;
        int i11;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] iArr2 = this.mMaxAscent;
        int[] iArr3 = this.mMaxDescent;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        iArr3[3] = -1;
        iArr3[2] = -1;
        iArr3[1] = -1;
        iArr3[0] = -1;
        boolean z4 = this.mBaselineAligned;
        boolean z5 = this.mUseLargestChild;
        int i12 = 1073741824;
        boolean z6 = mode == 1073741824;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        boolean z7 = true;
        float f = 0.0f;
        boolean z8 = false;
        boolean z9 = false;
        int i18 = 0;
        while (true) {
            iArr = iArr3;
            if (i14 >= virtualChildCount) {
                break;
            }
            View virtualChildAt = getVirtualChildAt(i14);
            if (virtualChildAt == null) {
                this.mTotalLength += measureNullChild(i14);
                max = i13;
                z = z5;
                z2 = z4;
            } else if (virtualChildAt.getVisibility() == 8) {
                i14 += getChildrenSkipCount(virtualChildAt, i14);
                max = i13;
                z = z5;
                z2 = z4;
            } else {
                if (hasDividerBeforeChildAt(i14)) {
                    this.mTotalLength += this.mDividerWidth;
                }
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                f += layoutParams.weight;
                if (mode != i12 || layoutParams.width != 0 || layoutParams.weight <= 0.0f) {
                    int i19 = i13;
                    if (layoutParams.width == 0 && layoutParams.weight > 0.0f) {
                        layoutParams.width = -2;
                        i6 = 0;
                    } else {
                        i6 = Integer.MIN_VALUE;
                    }
                    if (f == 0.0f) {
                        i7 = this.mTotalLength;
                    } else {
                        i7 = 0;
                    }
                    i8 = i14;
                    i9 = i19;
                    z = z5;
                    z2 = z4;
                    int i20 = i6;
                    measureChildBeforeLayout(virtualChildAt, i8, i, i7, i2, 0);
                    if (i20 != Integer.MIN_VALUE) {
                        layoutParams.width = i20;
                    }
                    int measuredWidth = virtualChildAt.getMeasuredWidth();
                    if (z6) {
                        view = virtualChildAt;
                        this.mTotalLength += layoutParams.leftMargin + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(view);
                    } else {
                        view = virtualChildAt;
                        int i21 = this.mTotalLength;
                        this.mTotalLength = Math.max(i21, i21 + measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin + getNextLocationOffset(view));
                    }
                    if (!z) {
                        i10 = i18;
                        i18 = i10;
                        if (mode2 == 1073741824) {
                        }
                        z3 = false;
                        int i22 = layoutParams.topMargin + layoutParams.bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i22;
                        i15 = View.combineMeasuredStates(i15, view.getMeasuredState());
                        if (z2) {
                            int i23 = (((layoutParams.gravity >= 0 ? this.mGravity : layoutParams.gravity) & 112) >> 4) >> 1;
                            iArr2[i23] = Math.max(iArr2[i23], baseline2);
                            iArr[i23] = Math.max(iArr[i23], measuredHeight - baseline2);
                        }
                        max = Math.max(i9, measuredHeight);
                        if (z7) {
                        }
                        if (layoutParams.weight <= 0.0f) {
                        }
                        int i24 = i8;
                        i14 = getChildrenSkipCount(view, i24) + i24;
                    } else {
                        i18 = Math.max(measuredWidth, i18);
                        if (mode2 == 1073741824 && layoutParams.height == -1) {
                            z3 = true;
                            z8 = true;
                        } else {
                            z3 = false;
                        }
                        int i222 = layoutParams.topMargin + layoutParams.bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i222;
                        i15 = View.combineMeasuredStates(i15, view.getMeasuredState());
                        if (z2 && (baseline2 = view.getBaseline()) != -1) {
                            int i232 = (((layoutParams.gravity >= 0 ? this.mGravity : layoutParams.gravity) & 112) >> 4) >> 1;
                            iArr2[i232] = Math.max(iArr2[i232], baseline2);
                            iArr[i232] = Math.max(iArr[i232], measuredHeight - baseline2);
                        }
                        max = Math.max(i9, measuredHeight);
                        z7 = !z7 && layoutParams.height == -1;
                        if (layoutParams.weight <= 0.0f) {
                            if (!z3) {
                                i222 = measuredHeight;
                            }
                            i17 = Math.max(i17, i222);
                        } else {
                            int i25 = i17;
                            if (!z3) {
                                i222 = measuredHeight;
                            }
                            i16 = Math.max(i16, i222);
                            i17 = i25;
                        }
                        int i242 = i8;
                        i14 = getChildrenSkipCount(view, i242) + i242;
                    }
                } else {
                    if (z6) {
                        i11 = i13;
                        this.mTotalLength += layoutParams.leftMargin + layoutParams.rightMargin;
                    } else {
                        i11 = i13;
                        int i26 = this.mTotalLength;
                        this.mTotalLength = Math.max(i26, layoutParams.leftMargin + i26 + layoutParams.rightMargin);
                    }
                    if (z4) {
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                        virtualChildAt.measure(makeMeasureSpec, makeMeasureSpec);
                        z = z5;
                        z2 = z4;
                        view = virtualChildAt;
                        i9 = i11;
                        i8 = i14;
                        i10 = i18;
                        i18 = i10;
                        if (mode2 == 1073741824) {
                        }
                        z3 = false;
                        int i2222 = layoutParams.topMargin + layoutParams.bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i2222;
                        i15 = View.combineMeasuredStates(i15, view.getMeasuredState());
                        if (z2) {
                        }
                        max = Math.max(i9, measuredHeight);
                        if (z7) {
                        }
                        if (layoutParams.weight <= 0.0f) {
                        }
                        int i2422 = i8;
                        i14 = getChildrenSkipCount(view, i2422) + i2422;
                    } else {
                        z = z5;
                        z2 = z4;
                        view = virtualChildAt;
                        i9 = i11;
                        z9 = true;
                        i8 = i14;
                        if (mode2 == 1073741824) {
                        }
                        z3 = false;
                        int i22222 = layoutParams.topMargin + layoutParams.bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i22222;
                        i15 = View.combineMeasuredStates(i15, view.getMeasuredState());
                        if (z2) {
                        }
                        max = Math.max(i9, measuredHeight);
                        if (z7) {
                        }
                        if (layoutParams.weight <= 0.0f) {
                        }
                        int i24222 = i8;
                        i14 = getChildrenSkipCount(view, i24222) + i24222;
                    }
                }
            }
            i14++;
            i13 = max;
            iArr3 = iArr;
            z5 = z;
            z4 = z2;
            i12 = 1073741824;
        }
        int i27 = i13;
        boolean z10 = z5;
        boolean z11 = z4;
        int i28 = i16;
        int i29 = i17;
        int i30 = i18;
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            this.mTotalLength += this.mDividerWidth;
        }
        int i31 = iArr2[1];
        int max2 = (i31 == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) ? i27 : Math.max(i27, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(i31, iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        if (z10 && (mode == Integer.MIN_VALUE || mode == 0)) {
            this.mTotalLength = 0;
            int i32 = 0;
            while (i32 < virtualChildCount) {
                View virtualChildAt2 = getVirtualChildAt(i32);
                if (virtualChildAt2 == null) {
                    this.mTotalLength += measureNullChild(i32);
                } else if (virtualChildAt2.getVisibility() == 8) {
                    i32 += getChildrenSkipCount(virtualChildAt2, i32);
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                    if (z6) {
                        this.mTotalLength += i30 + layoutParams2.leftMargin + layoutParams2.rightMargin + getNextLocationOffset(virtualChildAt2);
                    } else {
                        int i33 = this.mTotalLength;
                        this.mTotalLength = Math.max(i33, i33 + i30 + layoutParams2.leftMargin + layoutParams2.rightMargin + getNextLocationOffset(virtualChildAt2));
                    }
                }
                i32++;
            }
        }
        int paddingLeft = this.mTotalLength + getPaddingLeft() + getPaddingRight();
        this.mTotalLength = paddingLeft;
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i, 0);
        int i34 = (16777215 & resolveSizeAndState) - this.mTotalLength;
        if (!z9 && (i34 == 0 || f <= 0.0f)) {
            i28 = Math.max(i28, i29);
            if (z10 && mode != 1073741824) {
                for (int i35 = 0; i35 < virtualChildCount; i35++) {
                    View virtualChildAt3 = getVirtualChildAt(i35);
                    if (virtualChildAt3 != null && virtualChildAt3.getVisibility() != 8 && ((LayoutParams) virtualChildAt3.getLayoutParams()).weight > 0.0f) {
                        virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(i30, 1073741824), View.MeasureSpec.makeMeasureSpec(virtualChildAt3.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            i3 = i2;
            i4 = virtualChildCount;
        } else {
            float f2 = this.mWeightSum;
            if (f2 > 0.0f) {
                f = f2;
            }
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            this.mTotalLength = 0;
            int i36 = -1;
            int i37 = 0;
            while (i37 < virtualChildCount) {
                View virtualChildAt4 = getVirtualChildAt(i37);
                if (virtualChildAt4 == null) {
                    i5 = virtualChildCount;
                } else if (virtualChildAt4.getVisibility() == 8) {
                    i5 = virtualChildCount;
                } else {
                    LayoutParams layoutParams3 = (LayoutParams) virtualChildAt4.getLayoutParams();
                    float f3 = layoutParams3.weight;
                    if (f3 > 0.0f) {
                        int i38 = (int) ((i34 * f3) / f);
                        f -= f3;
                        int i39 = i34 - i38;
                        i5 = virtualChildCount;
                        int childMeasureSpec = getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + layoutParams3.topMargin + layoutParams3.bottomMargin, layoutParams3.height);
                        if (layoutParams3.width == 0 && mode == 1073741824) {
                            if (i38 <= 0) {
                                i38 = 0;
                            }
                            virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(i38, 1073741824), childMeasureSpec);
                        } else {
                            int measuredWidth2 = virtualChildAt4.getMeasuredWidth() + i38;
                            if (measuredWidth2 < 0) {
                                measuredWidth2 = 0;
                            }
                            virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2, 1073741824), childMeasureSpec);
                        }
                        i15 = View.combineMeasuredStates(i15, virtualChildAt4.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                        i34 = i39;
                    } else {
                        i5 = virtualChildCount;
                    }
                    if (z6) {
                        this.mTotalLength += virtualChildAt4.getMeasuredWidth() + layoutParams3.leftMargin + layoutParams3.rightMargin + getNextLocationOffset(virtualChildAt4);
                    } else {
                        int i40 = this.mTotalLength;
                        this.mTotalLength = Math.max(i40, virtualChildAt4.getMeasuredWidth() + i40 + layoutParams3.leftMargin + layoutParams3.rightMargin + getNextLocationOffset(virtualChildAt4));
                    }
                    boolean z12 = mode2 != 1073741824 && layoutParams3.height == -1;
                    int i41 = layoutParams3.topMargin + layoutParams3.bottomMargin;
                    int measuredHeight2 = virtualChildAt4.getMeasuredHeight() + i41;
                    i36 = Math.max(i36, measuredHeight2);
                    if (!z12) {
                        i41 = measuredHeight2;
                    }
                    int max3 = Math.max(i28, i41);
                    boolean z13 = z7 && layoutParams3.height == -1;
                    if (z11 && (baseline = virtualChildAt4.getBaseline()) != -1) {
                        int i42 = (((layoutParams3.gravity < 0 ? this.mGravity : layoutParams3.gravity) & 112) >> 4) >> 1;
                        iArr2[i42] = Math.max(iArr2[i42], baseline);
                        iArr[i42] = Math.max(iArr[i42], measuredHeight2 - baseline);
                    }
                    z7 = z13;
                    i28 = max3;
                }
                i37++;
                virtualChildCount = i5;
            }
            i3 = i2;
            i4 = virtualChildCount;
            this.mTotalLength += getPaddingLeft() + getPaddingRight();
            int i43 = iArr2[1];
            if (i43 != -1 || iArr2[0] != -1 || iArr2[2] != -1 || iArr2[3] != -1) {
                max2 = Math.max(i36, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(i43, iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
            } else {
                max2 = i36;
            }
        }
        if (!z7 && mode2 != 1073741824) {
            max2 = i28;
        }
        setMeasuredDimension(((-16777216) & i15) | resolveSizeAndState, View.resolveSizeAndState(Math.max(max2 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i3, i15 << 16));
        if (z8) {
            forceUniformHeight(i4, i);
        }
    }

    int measureNullChild(int i) {
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:151:0x0331  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void measureVertical(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z;
        boolean z2;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        View view;
        boolean z3;
        int max;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i17 = this.mBaselineAlignedChildIndex;
        boolean z4 = this.mUseLargestChild;
        float f = 0.0f;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        boolean z5 = true;
        boolean z6 = false;
        boolean z7 = false;
        while (true) {
            int i24 = 8;
            int i25 = i21;
            if (i23 >= virtualChildCount) {
                int i26 = i18;
                int i27 = i20;
                int i28 = virtualChildCount;
                int i29 = mode2;
                int i30 = i25;
                if (this.mTotalLength > 0) {
                    i3 = i28;
                    if (hasDividerBeforeChildAt(i3)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                } else {
                    i3 = i28;
                }
                if (z4) {
                    i4 = i29;
                    if (i4 == Integer.MIN_VALUE || i4 == 0) {
                        this.mTotalLength = 0;
                        int i31 = 0;
                        while (i31 < i3) {
                            View virtualChildAt = getVirtualChildAt(i31);
                            if (virtualChildAt == null) {
                                this.mTotalLength += measureNullChild(i31);
                            } else if (virtualChildAt.getVisibility() == i24) {
                                i31 += getChildrenSkipCount(virtualChildAt, i31);
                            } else {
                                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                                int i32 = this.mTotalLength;
                                this.mTotalLength = Math.max(i32, i32 + i26 + layoutParams.topMargin + layoutParams.bottomMargin + getNextLocationOffset(virtualChildAt));
                            }
                            i31++;
                            i24 = 8;
                        }
                    }
                } else {
                    i4 = i29;
                }
                int paddingTop = this.mTotalLength + getPaddingTop() + getPaddingBottom();
                this.mTotalLength = paddingTop;
                int i33 = i27;
                int resolveSizeAndState = View.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i2, 0);
                int i34 = (16777215 & resolveSizeAndState) - this.mTotalLength;
                if (z7 || (i34 != 0 && f > 0.0f)) {
                    float f2 = this.mWeightSum;
                    if (f2 > 0.0f) {
                        f = f2;
                    }
                    this.mTotalLength = 0;
                    int i35 = i22;
                    int i36 = 0;
                    while (i36 < i3) {
                        View virtualChildAt2 = getVirtualChildAt(i36);
                        if (virtualChildAt2.getVisibility() == 8) {
                            i8 = i34;
                        } else {
                            LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                            float f3 = layoutParams2.weight;
                            if (f3 > 0.0f) {
                                int i37 = (int) ((i34 * f3) / f);
                                float f4 = f - f3;
                                int i38 = i34 - i37;
                                int childMeasureSpec = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + layoutParams2.leftMargin + layoutParams2.rightMargin, layoutParams2.width);
                                if (layoutParams2.height == 0 && i4 == 1073741824) {
                                    if (i37 <= 0) {
                                        i37 = 0;
                                    }
                                    virtualChildAt2.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(i37, 1073741824));
                                } else {
                                    int measuredHeight = virtualChildAt2.getMeasuredHeight() + i37;
                                    if (measuredHeight < 0) {
                                        measuredHeight = 0;
                                    }
                                    virtualChildAt2.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
                                }
                                i30 = View.combineMeasuredStates(i30, virtualChildAt2.getMeasuredState() & (-256));
                                f = f4;
                                i7 = i38;
                            } else {
                                i7 = i34;
                            }
                            float f5 = f;
                            int i39 = layoutParams2.leftMargin + layoutParams2.rightMargin;
                            int measuredWidth = virtualChildAt2.getMeasuredWidth() + i39;
                            i35 = Math.max(i35, measuredWidth);
                            if (mode != 1073741824) {
                                i8 = i7;
                                if (layoutParams2.width == -1) {
                                    z = true;
                                    if (!z) {
                                        i39 = measuredWidth;
                                    }
                                    int max2 = Math.max(i33, i39);
                                    if (z5 && layoutParams2.width == -1) {
                                        z2 = true;
                                        int i40 = this.mTotalLength;
                                        this.mTotalLength = Math.max(i40, i40 + virtualChildAt2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin + getNextLocationOffset(virtualChildAt2));
                                        i33 = max2;
                                        z5 = z2;
                                        f = f5;
                                    }
                                    z2 = false;
                                    int i402 = this.mTotalLength;
                                    this.mTotalLength = Math.max(i402, i402 + virtualChildAt2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin + getNextLocationOffset(virtualChildAt2));
                                    i33 = max2;
                                    z5 = z2;
                                    f = f5;
                                }
                            } else {
                                i8 = i7;
                            }
                            z = false;
                            if (!z) {
                            }
                            int max22 = Math.max(i33, i39);
                            if (z5) {
                                z2 = true;
                                int i4022 = this.mTotalLength;
                                this.mTotalLength = Math.max(i4022, i4022 + virtualChildAt2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin + getNextLocationOffset(virtualChildAt2));
                                i33 = max22;
                                z5 = z2;
                                f = f5;
                            }
                            z2 = false;
                            int i40222 = this.mTotalLength;
                            this.mTotalLength = Math.max(i40222, i40222 + virtualChildAt2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin + getNextLocationOffset(virtualChildAt2));
                            i33 = max22;
                            z5 = z2;
                            f = f5;
                        }
                        i36++;
                        i34 = i8;
                    }
                    i5 = i;
                    this.mTotalLength += getPaddingTop() + getPaddingBottom();
                    i22 = i35;
                    i6 = i33;
                } else {
                    i6 = Math.max(i33, i19);
                    if (z4 && i4 != 1073741824) {
                        for (int i41 = 0; i41 < i3; i41++) {
                            View virtualChildAt3 = getVirtualChildAt(i41);
                            if (virtualChildAt3 != null && virtualChildAt3.getVisibility() != 8 && ((LayoutParams) virtualChildAt3.getLayoutParams()).weight > 0.0f) {
                                virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(virtualChildAt3.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(i26, 1073741824));
                            }
                        }
                    }
                    i5 = i;
                }
                if (z5 || mode == 1073741824) {
                    i6 = i22;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max(i6 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i5, i30), resolveSizeAndState);
                if (z6) {
                    forceUniformWidth(i3, i2);
                    return;
                }
                return;
            }
            View virtualChildAt4 = getVirtualChildAt(i23);
            if (virtualChildAt4 == null) {
                this.mTotalLength += measureNullChild(i23);
                i12 = virtualChildCount;
                i13 = mode2;
                i21 = i25;
            } else {
                int i42 = i18;
                if (virtualChildAt4.getVisibility() == 8) {
                    i23 += getChildrenSkipCount(virtualChildAt4, i23);
                    i12 = virtualChildCount;
                    i21 = i25;
                    i18 = i42;
                    i13 = mode2;
                } else {
                    if (hasDividerBeforeChildAt(i23)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                    LayoutParams layoutParams3 = (LayoutParams) virtualChildAt4.getLayoutParams();
                    float f6 = f + layoutParams3.weight;
                    if (mode2 == 1073741824 && layoutParams3.height == 0 && layoutParams3.weight > 0.0f) {
                        int i43 = this.mTotalLength;
                        this.mTotalLength = Math.max(i43, layoutParams3.topMargin + i43 + layoutParams3.bottomMargin);
                        i11 = i20;
                        view = virtualChildAt4;
                        i15 = i22;
                        i12 = virtualChildCount;
                        i18 = i42;
                        i10 = i19;
                        z7 = true;
                        i16 = i23;
                        i13 = mode2;
                        i14 = i25;
                    } else {
                        int i44 = i19;
                        if (layoutParams3.height != 0 || layoutParams3.weight <= 0.0f) {
                            i9 = Integer.MIN_VALUE;
                        } else {
                            layoutParams3.height = -2;
                            i9 = 0;
                        }
                        int i45 = i9;
                        i10 = i44;
                        i11 = i20;
                        i12 = virtualChildCount;
                        i13 = mode2;
                        i14 = i25;
                        i15 = i22;
                        i16 = i23;
                        measureChildBeforeLayout(virtualChildAt4, i23, i, 0, i2, f6 == 0.0f ? this.mTotalLength : 0);
                        if (i45 != Integer.MIN_VALUE) {
                            layoutParams3.height = i45;
                        }
                        int measuredHeight2 = virtualChildAt4.getMeasuredHeight();
                        int i46 = this.mTotalLength;
                        view = virtualChildAt4;
                        this.mTotalLength = Math.max(i46, i46 + measuredHeight2 + layoutParams3.topMargin + layoutParams3.bottomMargin + getNextLocationOffset(view));
                        i18 = z4 ? Math.max(measuredHeight2, i42) : i42;
                    }
                    if (i17 >= 0 && i17 == i16 + 1) {
                        this.mBaselineChildTop = this.mTotalLength;
                    }
                    if (i16 < i17 && layoutParams3.weight > 0.0f) {
                        throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                    }
                    if (mode == 1073741824 || layoutParams3.width != -1) {
                        z3 = false;
                    } else {
                        z3 = true;
                        z6 = true;
                    }
                    int i47 = layoutParams3.leftMargin + layoutParams3.rightMargin;
                    int measuredWidth2 = view.getMeasuredWidth() + i47;
                    i22 = Math.max(i15, measuredWidth2);
                    int combineMeasuredStates = View.combineMeasuredStates(i14, view.getMeasuredState());
                    z5 = z5 && layoutParams3.width == -1;
                    if (layoutParams3.weight > 0.0f) {
                        if (!z3) {
                            i47 = measuredWidth2;
                        }
                        i19 = Math.max(i10, i47);
                        max = i11;
                    } else {
                        int i48 = i10;
                        if (!z3) {
                            i47 = measuredWidth2;
                        }
                        max = Math.max(i11, i47);
                        i19 = i48;
                    }
                    i21 = combineMeasuredStates;
                    i23 = getChildrenSkipCount(view, i16) + i16;
                    i20 = max;
                    f = f6;
                }
            }
            i23++;
            virtualChildCount = i12;
            mode2 = i13;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            drawDividersVertical(canvas);
        } else {
            drawDividersHorizontal(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.support.v7.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.support.v7.widget.LinearLayoutCompat");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.mDivider) {
            return;
        }
        this.mDivider = drawable;
        if (drawable != null) {
            this.mDividerWidth = drawable.getIntrinsicWidth();
            this.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= GravityCompat.START;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class LayoutParams extends LinearLayout.LayoutParams {
        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = MaterialCardView.CHECKED_ICON_GRAVITY_TOP_START;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R$styleable.LinearLayoutCompat, i, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, R$styleable.LinearLayoutCompat, attributeSet, obtainStyledAttributes.getWrappedTypeArray(), i, 0);
        int i2 = obtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_android_orientation, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = obtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_android_gravity, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        boolean z = obtainStyledAttributes.getBoolean(R$styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = obtainStyledAttributes.getFloat(R$styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = obtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = obtainStyledAttributes.getBoolean(R$styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(obtainStyledAttributes.getDrawable(R$styleable.LinearLayoutCompat_divider));
        this.mShowDividers = obtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(R$styleable.LinearLayoutCompat_dividerPadding, 0);
        obtainStyledAttributes.recycle();
    }
}
