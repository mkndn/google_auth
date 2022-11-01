package android.support.v7.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.drawable.DrawableContainer;
import android.util.AttributeSet;
import android.util.StateSet;

/* compiled from: PG */
/* loaded from: classes.dex */
class StateListDrawable extends DrawableContainer {
    private boolean mMutated;
    private StateListState mStateListState;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class StateListState extends DrawableContainer.DrawableContainerState {
        int[][] mStateSets;

        /* JADX INFO: Access modifiers changed from: package-private */
        public StateListState(StateListState stateListState, StateListDrawable stateListDrawable, Resources resources) {
            super(stateListState, stateListDrawable, resources);
            if (stateListState != null) {
                this.mStateSets = stateListState.mStateSets;
            } else {
                this.mStateSets = new int[getCapacity()];
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int addStateSet(int[] iArr, Drawable drawable) {
            int addChild = addChild(drawable);
            this.mStateSets[addChild] = iArr;
            return addChild;
        }

        @Override // android.support.v7.graphics.drawable.DrawableContainer.DrawableContainerState
        public void growArray(int i, int i2) {
            super.growArray(i, i2);
            int[][] iArr = new int[i2];
            System.arraycopy(this.mStateSets, 0, iArr, 0, i);
            this.mStateSets = iArr;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int indexOfStateSet(int[] iArr) {
            int[][] iArr2 = this.mStateSets;
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (StateSet.stateSetMatches(iArr2[i], iArr)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // android.support.v7.graphics.drawable.DrawableContainer.DrawableContainerState
        void mutate() {
            int[][] iArr = this.mStateSets;
            int[][] iArr2 = new int[iArr.length];
            for (int length = iArr.length - 1; length >= 0; length--) {
                int[] iArr3 = this.mStateSets[length];
                iArr2[length] = iArr3 != null ? (int[]) iArr3.clone() : null;
            }
            this.mStateSets = iArr2;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new StateListDrawable(this, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new StateListDrawable(this, resources);
        }
    }

    StateListDrawable() {
        this(null, null);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mStateListState.mutate();
            this.mMutated = true;
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        int indexOfStateSet = this.mStateListState.indexOfStateSet(iArr);
        if (indexOfStateSet < 0) {
            indexOfStateSet = this.mStateListState.indexOfStateSet(StateSet.WILD_CARD);
        }
        return selectDrawable(indexOfStateSet) || onStateChange;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v7.graphics.drawable.DrawableContainer
    public void setConstantState(DrawableContainer.DrawableContainerState drawableContainerState) {
        super.setConstantState(drawableContainerState);
        if (drawableContainerState instanceof StateListState) {
            this.mStateListState = (StateListState) drawableContainerState;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v7.graphics.drawable.DrawableContainer
    public StateListState cloneConstantState() {
        return new StateListState(this.mStateListState, this, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] extractStateSet(AttributeSet attributeSet) {
        int attributeCount = attributeSet.getAttributeCount();
        int[] iArr = new int[attributeCount];
        int i = 0;
        for (int i2 = 0; i2 < attributeCount; i2++) {
            int attributeNameResource = attributeSet.getAttributeNameResource(i2);
            switch (attributeNameResource) {
                case 0:
                case 16842960:
                case 16843161:
                    break;
                default:
                    int i3 = i + 1;
                    if (!attributeSet.getAttributeBooleanValue(i2, false)) {
                        attributeNameResource = -attributeNameResource;
                    }
                    iArr[i] = attributeNameResource;
                    i = i3;
                    break;
            }
        }
        return StateSet.trimStateSet(iArr, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StateListDrawable(StateListState stateListState) {
        if (stateListState != null) {
            setConstantState(stateListState);
        }
    }

    StateListDrawable(StateListState stateListState, Resources resources) {
        setConstantState(new StateListState(stateListState, this, resources));
        onStateChange(getState());
    }
}
