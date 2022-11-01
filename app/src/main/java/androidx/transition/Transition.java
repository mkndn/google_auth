package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.graphics.Path;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Transition implements Cloneable {
    public static final int MATCH_ID = 3;
    public static final int MATCH_INSTANCE = 1;
    public static final int MATCH_ITEM_ID = 4;
    public static final int MATCH_NAME = 2;
    private ArrayList mEndValuesList;
    private EpicenterCallback mEpicenterCallback;
    private ArrayMap mNameOverrides;
    TransitionPropagation mPropagation;
    private ArrayList mStartValuesList;
    private static final int[] DEFAULT_MATCH_ORDER = {2, 1, 3, 4};
    private static final PathMotion STRAIGHT_PATH_MOTION = new PathMotion() { // from class: androidx.transition.Transition.1
        @Override // androidx.transition.PathMotion
        public Path getPath(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    };
    private static ThreadLocal sRunningAnimators = new ThreadLocal();
    private String mName = getClass().getName();
    private long mStartDelay = -1;
    long mDuration = -1;
    private TimeInterpolator mInterpolator = null;
    ArrayList mTargetIds = new ArrayList();
    ArrayList mTargets = new ArrayList();
    private ArrayList mTargetNames = null;
    private ArrayList mTargetTypes = null;
    private ArrayList mTargetIdExcludes = null;
    private ArrayList mTargetExcludes = null;
    private ArrayList mTargetTypeExcludes = null;
    private ArrayList mTargetNameExcludes = null;
    private ArrayList mTargetIdChildExcludes = null;
    private ArrayList mTargetChildExcludes = null;
    private ArrayList mTargetTypeChildExcludes = null;
    private TransitionValuesMaps mStartValues = new TransitionValuesMaps();
    private TransitionValuesMaps mEndValues = new TransitionValuesMaps();
    TransitionSet mParent = null;
    private int[] mMatchOrder = DEFAULT_MATCH_ORDER;
    boolean mCanRemoveViews = false;
    ArrayList mCurrentAnimators = new ArrayList();
    private int mNumInstances = 0;
    private boolean mPaused = false;
    private boolean mEnded = false;
    private ArrayList mListeners = null;
    private ArrayList mAnimators = new ArrayList();
    private PathMotion mPathMotion = STRAIGHT_PATH_MOTION;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class AnimationInfo {
        String mName;
        Transition mTransition;
        TransitionValues mValues;
        View mView;
        WindowIdImpl mWindowId;

        AnimationInfo(View view, String str, Transition transition, WindowIdImpl windowIdImpl, TransitionValues transitionValues) {
            this.mView = view;
            this.mName = str;
            this.mValues = transitionValues;
            this.mWindowId = windowIdImpl;
            this.mTransition = transition;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class EpicenterCallback {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface TransitionListener {
        void onTransitionCancel(Transition transition);

        void onTransitionEnd(Transition transition);

        void onTransitionPause(Transition transition);

        void onTransitionResume(Transition transition);

        void onTransitionStart(Transition transition);
    }

    private void addUnmatched(ArrayMap arrayMap, ArrayMap arrayMap2) {
        for (int i = 0; i < arrayMap.size(); i++) {
            TransitionValues transitionValues = (TransitionValues) arrayMap.valueAt(i);
            if (isValidTarget(transitionValues.view)) {
                this.mStartValuesList.add(transitionValues);
                this.mEndValuesList.add(null);
            }
        }
        for (int i2 = 0; i2 < arrayMap2.size(); i2++) {
            TransitionValues transitionValues2 = (TransitionValues) arrayMap2.valueAt(i2);
            if (isValidTarget(transitionValues2.view)) {
                this.mEndValuesList.add(transitionValues2);
                this.mStartValuesList.add(null);
            }
        }
    }

    private static void addViewValues(TransitionValuesMaps transitionValuesMaps, View view, TransitionValues transitionValues) {
        transitionValuesMaps.mViewValues.put(view, transitionValues);
        int id = view.getId();
        if (id >= 0) {
            if (transitionValuesMaps.mIdValues.indexOfKey(id) >= 0) {
                transitionValuesMaps.mIdValues.put(id, null);
            } else {
                transitionValuesMaps.mIdValues.put(id, view);
            }
        }
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            if (transitionValuesMaps.mNameValues.containsKey(transitionName)) {
                transitionValuesMaps.mNameValues.put(transitionName, null);
            } else {
                transitionValuesMaps.mNameValues.put(transitionName, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (transitionValuesMaps.mItemIdValues.indexOfKey(itemIdAtPosition) >= 0) {
                    View view2 = (View) transitionValuesMaps.mItemIdValues.get(itemIdAtPosition);
                    if (view2 != null) {
                        ViewCompat.setHasTransientState(view2, false);
                        transitionValuesMaps.mItemIdValues.put(itemIdAtPosition, null);
                        return;
                    }
                    return;
                }
                ViewCompat.setHasTransientState(view, true);
                transitionValuesMaps.mItemIdValues.put(itemIdAtPosition, view);
            }
        }
    }

    private void captureHierarchy(View view, boolean z) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        ArrayList arrayList = this.mTargetIdExcludes;
        if (arrayList != null && arrayList.contains(Integer.valueOf(id))) {
            return;
        }
        ArrayList arrayList2 = this.mTargetExcludes;
        if (arrayList2 != null && arrayList2.contains(view)) {
            return;
        }
        ArrayList arrayList3 = this.mTargetTypeExcludes;
        if (arrayList3 != null) {
            int size = arrayList3.size();
            for (int i = 0; i < size; i++) {
                if (((Class) this.mTargetTypeExcludes.get(i)).isInstance(view)) {
                    return;
                }
            }
        }
        if (view.getParent() instanceof ViewGroup) {
            TransitionValues transitionValues = new TransitionValues(view);
            if (z) {
                captureStartValues(transitionValues);
            } else {
                captureEndValues(transitionValues);
            }
            transitionValues.mTargetedTransitions.add(this);
            capturePropagationValues(transitionValues);
            if (z) {
                addViewValues(this.mStartValues, view, transitionValues);
            } else {
                addViewValues(this.mEndValues, view, transitionValues);
            }
        }
        if (view instanceof ViewGroup) {
            ArrayList arrayList4 = this.mTargetIdChildExcludes;
            if (arrayList4 != null && arrayList4.contains(Integer.valueOf(id))) {
                return;
            }
            ArrayList arrayList5 = this.mTargetChildExcludes;
            if (arrayList5 != null && arrayList5.contains(view)) {
                return;
            }
            ArrayList arrayList6 = this.mTargetTypeChildExcludes;
            if (arrayList6 != null) {
                int size2 = arrayList6.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    if (((Class) this.mTargetTypeChildExcludes.get(i2)).isInstance(view)) {
                        return;
                    }
                }
            }
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                captureHierarchy(viewGroup.getChildAt(i3), z);
            }
        }
    }

    private static ArrayMap getRunningAnimators() {
        ArrayMap arrayMap = (ArrayMap) sRunningAnimators.get();
        if (arrayMap == null) {
            ArrayMap arrayMap2 = new ArrayMap();
            sRunningAnimators.set(arrayMap2);
            return arrayMap2;
        }
        return arrayMap;
    }

    private static boolean isValueChanged(TransitionValues transitionValues, TransitionValues transitionValues2, String str) {
        Object obj = transitionValues.values.get(str);
        Object obj2 = transitionValues2.values.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        return obj == null || obj2 == null || !obj.equals(obj2);
    }

    private void matchIds(ArrayMap arrayMap, ArrayMap arrayMap2, SparseArray sparseArray, SparseArray sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            View view2 = (View) sparseArray.valueAt(i);
            if (view2 != null && isValidTarget(view2) && (view = (View) sparseArray2.get(sparseArray.keyAt(i))) != null && isValidTarget(view)) {
                TransitionValues transitionValues = (TransitionValues) arrayMap.get(view2);
                TransitionValues transitionValues2 = (TransitionValues) arrayMap2.get(view);
                if (transitionValues != null && transitionValues2 != null) {
                    this.mStartValuesList.add(transitionValues);
                    this.mEndValuesList.add(transitionValues2);
                    arrayMap.remove(view2);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void matchInstances(ArrayMap arrayMap, ArrayMap arrayMap2) {
        TransitionValues transitionValues;
        int size = arrayMap.size();
        while (true) {
            size--;
            if (size >= 0) {
                View view = (View) arrayMap.keyAt(size);
                if (view != null && isValidTarget(view) && (transitionValues = (TransitionValues) arrayMap2.remove(view)) != null && isValidTarget(transitionValues.view)) {
                    this.mStartValuesList.add((TransitionValues) arrayMap.removeAt(size));
                    this.mEndValuesList.add(transitionValues);
                }
            } else {
                return;
            }
        }
    }

    private void matchItemIds(ArrayMap arrayMap, ArrayMap arrayMap2, LongSparseArray longSparseArray, LongSparseArray longSparseArray2) {
        View view;
        int size = longSparseArray.size();
        for (int i = 0; i < size; i++) {
            View view2 = (View) longSparseArray.valueAt(i);
            if (view2 != null && isValidTarget(view2) && (view = (View) longSparseArray2.get(longSparseArray.keyAt(i))) != null && isValidTarget(view)) {
                TransitionValues transitionValues = (TransitionValues) arrayMap.get(view2);
                TransitionValues transitionValues2 = (TransitionValues) arrayMap2.get(view);
                if (transitionValues != null && transitionValues2 != null) {
                    this.mStartValuesList.add(transitionValues);
                    this.mEndValuesList.add(transitionValues2);
                    arrayMap.remove(view2);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void matchNames(ArrayMap arrayMap, ArrayMap arrayMap2, ArrayMap arrayMap3, ArrayMap arrayMap4) {
        View view;
        int size = arrayMap3.size();
        for (int i = 0; i < size; i++) {
            View view2 = (View) arrayMap3.valueAt(i);
            if (view2 != null && isValidTarget(view2) && (view = (View) arrayMap4.get(arrayMap3.keyAt(i))) != null && isValidTarget(view)) {
                TransitionValues transitionValues = (TransitionValues) arrayMap.get(view2);
                TransitionValues transitionValues2 = (TransitionValues) arrayMap2.get(view);
                if (transitionValues != null && transitionValues2 != null) {
                    this.mStartValuesList.add(transitionValues);
                    this.mEndValuesList.add(transitionValues2);
                    arrayMap.remove(view2);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void matchStartAndEnd(TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2) {
        ArrayMap arrayMap = new ArrayMap(transitionValuesMaps.mViewValues);
        ArrayMap arrayMap2 = new ArrayMap(transitionValuesMaps2.mViewValues);
        int i = 0;
        while (true) {
            int[] iArr = this.mMatchOrder;
            if (i < iArr.length) {
                switch (iArr[i]) {
                    case 1:
                        matchInstances(arrayMap, arrayMap2);
                        break;
                    case 2:
                        matchNames(arrayMap, arrayMap2, transitionValuesMaps.mNameValues, transitionValuesMaps2.mNameValues);
                        break;
                    case 3:
                        matchIds(arrayMap, arrayMap2, transitionValuesMaps.mIdValues, transitionValuesMaps2.mIdValues);
                        break;
                    case 4:
                        matchItemIds(arrayMap, arrayMap2, transitionValuesMaps.mItemIdValues, transitionValuesMaps2.mItemIdValues);
                        break;
                }
                i++;
            } else {
                addUnmatched(arrayMap, arrayMap2);
                return;
            }
        }
    }

    private void runAnimator(Animator animator, final ArrayMap arrayMap) {
        if (animator != null) {
            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.Transition.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator2) {
                    arrayMap.remove(animator2);
                    Transition.this.mCurrentAnimators.remove(animator2);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator2) {
                    Transition.this.mCurrentAnimators.add(animator2);
                }
            });
            animate(animator);
        }
    }

    public Transition addListener(TransitionListener transitionListener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList();
        }
        this.mListeners.add(transitionListener);
        return this;
    }

    public Transition addTarget(View view) {
        this.mTargets.add(view);
        return this;
    }

    protected void animate(Animator animator) {
        if (animator == null) {
            end();
            return;
        }
        if (getDuration() >= 0) {
            animator.setDuration(getDuration());
        }
        if (getStartDelay() >= 0) {
            animator.setStartDelay(getStartDelay() + animator.getStartDelay());
        }
        if (getInterpolator() != null) {
            animator.setInterpolator(getInterpolator());
        }
        animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.Transition.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                Transition.this.end();
                animator2.removeListener(this);
            }
        });
        animator.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void cancel() {
        for (int size = this.mCurrentAnimators.size() - 1; size >= 0; size--) {
            ((Animator) this.mCurrentAnimators.get(size)).cancel();
        }
        ArrayList arrayList = this.mListeners;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
            int size2 = arrayList2.size();
            for (int i = 0; i < size2; i++) {
                ((TransitionListener) arrayList2.get(i)).onTransitionCancel(this);
            }
        }
    }

    public abstract void captureEndValues(TransitionValues transitionValues);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void capturePropagationValues(TransitionValues transitionValues) {
        String[] propagationProperties;
        if (this.mPropagation == null || transitionValues.values.isEmpty() || (propagationProperties = this.mPropagation.getPropagationProperties()) == null) {
            return;
        }
        boolean z = false;
        int i = 0;
        while (true) {
            if (i < propagationProperties.length) {
                if (!transitionValues.values.containsKey(propagationProperties[i])) {
                    break;
                }
                i++;
            } else {
                z = true;
                break;
            }
        }
        if (!z) {
            this.mPropagation.captureValues(transitionValues);
        }
    }

    public abstract void captureStartValues(TransitionValues transitionValues);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void captureValues(ViewGroup viewGroup, boolean z) {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayMap arrayMap;
        clearValues(z);
        if ((this.mTargetIds.size() <= 0 && this.mTargets.size() <= 0) || (((arrayList = this.mTargetNames) != null && !arrayList.isEmpty()) || ((arrayList2 = this.mTargetTypes) != null && !arrayList2.isEmpty()))) {
            captureHierarchy(viewGroup, z);
        } else {
            for (int i = 0; i < this.mTargetIds.size(); i++) {
                View findViewById = viewGroup.findViewById(((Integer) this.mTargetIds.get(i)).intValue());
                if (findViewById != null) {
                    TransitionValues transitionValues = new TransitionValues(findViewById);
                    if (z) {
                        captureStartValues(transitionValues);
                    } else {
                        captureEndValues(transitionValues);
                    }
                    transitionValues.mTargetedTransitions.add(this);
                    capturePropagationValues(transitionValues);
                    if (z) {
                        addViewValues(this.mStartValues, findViewById, transitionValues);
                    } else {
                        addViewValues(this.mEndValues, findViewById, transitionValues);
                    }
                }
            }
            for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                View view = (View) this.mTargets.get(i2);
                TransitionValues transitionValues2 = new TransitionValues(view);
                if (z) {
                    captureStartValues(transitionValues2);
                } else {
                    captureEndValues(transitionValues2);
                }
                transitionValues2.mTargetedTransitions.add(this);
                capturePropagationValues(transitionValues2);
                if (z) {
                    addViewValues(this.mStartValues, view, transitionValues2);
                } else {
                    addViewValues(this.mEndValues, view, transitionValues2);
                }
            }
        }
        if (!z && (arrayMap = this.mNameOverrides) != null) {
            int size = arrayMap.size();
            ArrayList arrayList3 = new ArrayList(size);
            for (int i3 = 0; i3 < size; i3++) {
                arrayList3.add((View) this.mStartValues.mNameValues.remove((String) this.mNameOverrides.keyAt(i3)));
            }
            for (int i4 = 0; i4 < size; i4++) {
                View view2 = (View) arrayList3.get(i4);
                if (view2 != null) {
                    this.mStartValues.mNameValues.put((String) this.mNameOverrides.valueAt(i4), view2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearValues(boolean z) {
        if (z) {
            this.mStartValues.mViewValues.clear();
            this.mStartValues.mIdValues.clear();
            this.mStartValues.mItemIdValues.clear();
            return;
        }
        this.mEndValues.mViewValues.clear();
        this.mEndValues.mIdValues.clear();
        this.mEndValues.mItemIdValues.clear();
    }

    /* renamed from: clone */
    public Transition m19clone() {
        try {
            Transition transition = (Transition) super.clone();
            transition.mAnimators = new ArrayList();
            transition.mStartValues = new TransitionValuesMaps();
            transition.mEndValues = new TransitionValuesMaps();
            transition.mStartValuesList = null;
            transition.mEndValuesList = null;
            return transition;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void createAnimators(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2, ArrayList arrayList, ArrayList arrayList2) {
        int i;
        int i2;
        Animator createAnimator;
        View view;
        Animator animator;
        TransitionValues transitionValues;
        Animator animator2;
        TransitionValues transitionValues2;
        ArrayMap runningAnimators = getRunningAnimators();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        long j = Long.MAX_VALUE;
        int i3 = 0;
        while (i3 < size) {
            TransitionValues transitionValues3 = (TransitionValues) arrayList.get(i3);
            TransitionValues transitionValues4 = (TransitionValues) arrayList2.get(i3);
            if (transitionValues3 != null && !transitionValues3.mTargetedTransitions.contains(this)) {
                transitionValues3 = null;
            }
            if (transitionValues4 != null && !transitionValues4.mTargetedTransitions.contains(this)) {
                transitionValues4 = null;
            }
            if (transitionValues3 == null && transitionValues4 == null) {
                i = size;
                i2 = i3;
            } else if (!(transitionValues3 == null || transitionValues4 == null || isTransitionRequired(transitionValues3, transitionValues4)) || (createAnimator = createAnimator(viewGroup, transitionValues3, transitionValues4)) == null) {
                i = size;
                i2 = i3;
            } else {
                if (transitionValues4 != null) {
                    view = transitionValues4.view;
                    String[] transitionProperties = getTransitionProperties();
                    if (transitionProperties != null && transitionProperties.length > 0) {
                        TransitionValues transitionValues5 = new TransitionValues(view);
                        animator2 = createAnimator;
                        i = size;
                        TransitionValues transitionValues6 = (TransitionValues) transitionValuesMaps2.mViewValues.get(view);
                        if (transitionValues6 != null) {
                            int i4 = 0;
                            while (i4 < transitionProperties.length) {
                                transitionValues5.values.put(transitionProperties[i4], transitionValues6.values.get(transitionProperties[i4]));
                                i4++;
                                i3 = i3;
                                transitionValues6 = transitionValues6;
                            }
                            i2 = i3;
                        } else {
                            i2 = i3;
                        }
                        int size2 = runningAnimators.size();
                        int i5 = 0;
                        while (true) {
                            if (i5 >= size2) {
                                transitionValues2 = transitionValues5;
                                break;
                            }
                            AnimationInfo animationInfo = (AnimationInfo) runningAnimators.get((Animator) runningAnimators.keyAt(i5));
                            if (animationInfo.mValues != null && animationInfo.mView == view && animationInfo.mName.equals(getName()) && animationInfo.mValues.equals(transitionValues5)) {
                                transitionValues2 = transitionValues5;
                                animator2 = null;
                                break;
                            }
                            i5++;
                        }
                    } else {
                        animator2 = createAnimator;
                        i = size;
                        i2 = i3;
                        transitionValues2 = null;
                    }
                    transitionValues = transitionValues2;
                    animator = animator2;
                } else {
                    i = size;
                    i2 = i3;
                    view = transitionValues3.view;
                    animator = createAnimator;
                    transitionValues = null;
                }
                if (animator != null) {
                    TransitionPropagation transitionPropagation = this.mPropagation;
                    if (transitionPropagation != null) {
                        long startDelay = transitionPropagation.getStartDelay(viewGroup, this, transitionValues3, transitionValues4);
                        sparseIntArray.put(this.mAnimators.size(), (int) startDelay);
                        j = Math.min(startDelay, j);
                    }
                    runningAnimators.put(animator, new AnimationInfo(view, getName(), this, ViewUtils.getWindowId(viewGroup), transitionValues));
                    this.mAnimators.add(animator);
                    j = j;
                }
            }
            i3 = i2 + 1;
            size = i;
        }
        if (sparseIntArray.size() != 0) {
            for (int i6 = 0; i6 < sparseIntArray.size(); i6++) {
                Animator animator3 = (Animator) this.mAnimators.get(sparseIntArray.keyAt(i6));
                animator3.setStartDelay((sparseIntArray.valueAt(i6) - j) + animator3.getStartDelay());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void end() {
        int i = this.mNumInstances - 1;
        this.mNumInstances = i;
        if (i == 0) {
            ArrayList arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((TransitionListener) arrayList2.get(i2)).onTransitionEnd(this);
                }
            }
            for (int i3 = 0; i3 < this.mStartValues.mItemIdValues.size(); i3++) {
                View view = (View) this.mStartValues.mItemIdValues.valueAt(i3);
                if (view != null) {
                    ViewCompat.setHasTransientState(view, false);
                }
            }
            for (int i4 = 0; i4 < this.mEndValues.mItemIdValues.size(); i4++) {
                View view2 = (View) this.mEndValues.mItemIdValues.valueAt(i4);
                if (view2 != null) {
                    ViewCompat.setHasTransientState(view2, false);
                }
            }
            this.mEnded = true;
        }
    }

    public long getDuration() {
        return this.mDuration;
    }

    public EpicenterCallback getEpicenterCallback() {
        return this.mEpicenterCallback;
    }

    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransitionValues getMatchedTransitionValues(View view, boolean z) {
        TransitionSet transitionSet = this.mParent;
        if (transitionSet != null) {
            return transitionSet.getMatchedTransitionValues(view, z);
        }
        ArrayList arrayList = z ? this.mStartValuesList : this.mEndValuesList;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            }
            TransitionValues transitionValues = (TransitionValues) arrayList.get(i);
            if (transitionValues == null) {
                return null;
            }
            if (transitionValues.view == view) {
                break;
            }
            i++;
        }
        if (i < 0) {
            return null;
        }
        return (TransitionValues) (z ? this.mEndValuesList : this.mStartValuesList).get(i);
    }

    public String getName() {
        return this.mName;
    }

    public PathMotion getPathMotion() {
        return this.mPathMotion;
    }

    public TransitionPropagation getPropagation() {
        return this.mPropagation;
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    public List getTargetIds() {
        return this.mTargetIds;
    }

    public List getTargetNames() {
        return this.mTargetNames;
    }

    public List getTargetTypes() {
        return this.mTargetTypes;
    }

    public List getTargets() {
        return this.mTargets;
    }

    public String[] getTransitionProperties() {
        return null;
    }

    public TransitionValues getTransitionValues(View view, boolean z) {
        TransitionSet transitionSet = this.mParent;
        if (transitionSet != null) {
            return transitionSet.getTransitionValues(view, z);
        }
        return (TransitionValues) (z ? this.mStartValues : this.mEndValues).mViewValues.get(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isValidTarget(View view) {
        ArrayList arrayList;
        ArrayList arrayList2;
        int id = view.getId();
        ArrayList arrayList3 = this.mTargetIdExcludes;
        if (arrayList3 == null || !arrayList3.contains(Integer.valueOf(id))) {
            ArrayList arrayList4 = this.mTargetExcludes;
            if (arrayList4 == null || !arrayList4.contains(view)) {
                ArrayList arrayList5 = this.mTargetTypeExcludes;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    for (int i = 0; i < size; i++) {
                        if (((Class) this.mTargetTypeExcludes.get(i)).isInstance(view)) {
                            return false;
                        }
                    }
                }
                if (this.mTargetNameExcludes == null || ViewCompat.getTransitionName(view) == null || !this.mTargetNameExcludes.contains(ViewCompat.getTransitionName(view))) {
                    if ((this.mTargetIds.size() == 0 && this.mTargets.size() == 0 && (((arrayList = this.mTargetTypes) == null || arrayList.isEmpty()) && ((arrayList2 = this.mTargetNames) == null || arrayList2.isEmpty()))) || this.mTargetIds.contains(Integer.valueOf(id)) || this.mTargets.contains(view)) {
                        return true;
                    }
                    ArrayList arrayList6 = this.mTargetNames;
                    if (arrayList6 == null || !arrayList6.contains(ViewCompat.getTransitionName(view))) {
                        if (this.mTargetTypes != null) {
                            for (int i2 = 0; i2 < this.mTargetTypes.size(); i2++) {
                                if (((Class) this.mTargetTypes.get(i2)).isInstance(view)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public void pause(View view) {
        if (!this.mEnded) {
            for (int size = this.mCurrentAnimators.size() - 1; size >= 0; size--) {
                AnimatorUtils.pause((Animator) this.mCurrentAnimators.get(size));
            }
            ArrayList arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size2 = arrayList2.size();
                for (int i = 0; i < size2; i++) {
                    ((TransitionListener) arrayList2.get(i)).onTransitionPause(this);
                }
            }
            this.mPaused = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void playTransition(ViewGroup viewGroup) {
        AnimationInfo animationInfo;
        this.mStartValuesList = new ArrayList();
        this.mEndValuesList = new ArrayList();
        matchStartAndEnd(this.mStartValues, this.mEndValues);
        ArrayMap runningAnimators = getRunningAnimators();
        int size = runningAnimators.size();
        WindowIdImpl windowId = ViewUtils.getWindowId(viewGroup);
        for (int i = size - 1; i >= 0; i--) {
            Animator animator = (Animator) runningAnimators.keyAt(i);
            if (animator != null && (animationInfo = (AnimationInfo) runningAnimators.get(animator)) != null && animationInfo.mView != null && windowId.equals(animationInfo.mWindowId)) {
                TransitionValues transitionValues = animationInfo.mValues;
                View view = animationInfo.mView;
                TransitionValues transitionValues2 = getTransitionValues(view, true);
                TransitionValues matchedTransitionValues = getMatchedTransitionValues(view, true);
                if (transitionValues2 == null && matchedTransitionValues == null) {
                    matchedTransitionValues = (TransitionValues) this.mEndValues.mViewValues.get(view);
                }
                if (!(transitionValues2 == null && matchedTransitionValues == null) && animationInfo.mTransition.isTransitionRequired(transitionValues, matchedTransitionValues)) {
                    if (animator.isRunning() || animator.isStarted()) {
                        animator.cancel();
                    } else {
                        runningAnimators.remove(animator);
                    }
                }
            }
        }
        createAnimators(viewGroup, this.mStartValues, this.mEndValues, this.mStartValuesList, this.mEndValuesList);
        runAnimators();
    }

    public Transition removeListener(TransitionListener transitionListener) {
        ArrayList arrayList = this.mListeners;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(transitionListener);
        if (this.mListeners.size() == 0) {
            this.mListeners = null;
        }
        return this;
    }

    public Transition removeTarget(View view) {
        this.mTargets.remove(view);
        return this;
    }

    public void resume(View view) {
        if (this.mPaused) {
            if (!this.mEnded) {
                for (int size = this.mCurrentAnimators.size() - 1; size >= 0; size--) {
                    AnimatorUtils.resume((Animator) this.mCurrentAnimators.get(size));
                }
                ArrayList arrayList = this.mListeners;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                    int size2 = arrayList2.size();
                    for (int i = 0; i < size2; i++) {
                        ((TransitionListener) arrayList2.get(i)).onTransitionResume(this);
                    }
                }
            }
            this.mPaused = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void runAnimators() {
        start();
        ArrayMap runningAnimators = getRunningAnimators();
        Iterator it = this.mAnimators.iterator();
        while (it.hasNext()) {
            Animator animator = (Animator) it.next();
            if (runningAnimators.containsKey(animator)) {
                start();
                runAnimator(animator, runningAnimators);
            }
        }
        this.mAnimators.clear();
        end();
    }

    public Transition setDuration(long j) {
        this.mDuration = j;
        return this;
    }

    public void setEpicenterCallback(EpicenterCallback epicenterCallback) {
        this.mEpicenterCallback = epicenterCallback;
    }

    public Transition setInterpolator(TimeInterpolator timeInterpolator) {
        this.mInterpolator = timeInterpolator;
        return this;
    }

    public void setPathMotion(PathMotion pathMotion) {
        if (pathMotion == null) {
            this.mPathMotion = STRAIGHT_PATH_MOTION;
        } else {
            this.mPathMotion = pathMotion;
        }
    }

    public void setPropagation(TransitionPropagation transitionPropagation) {
        this.mPropagation = transitionPropagation;
    }

    public Transition setStartDelay(long j) {
        this.mStartDelay = j;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void start() {
        if (this.mNumInstances == 0) {
            ArrayList arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((TransitionListener) arrayList2.get(i)).onTransitionStart(this);
                }
            }
            this.mEnded = false;
        }
        this.mNumInstances++;
    }

    public String toString() {
        return toString("");
    }

    public boolean isTransitionRequired(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues != null && transitionValues2 != null) {
            String[] transitionProperties = getTransitionProperties();
            if (transitionProperties != null) {
                for (String str : transitionProperties) {
                    if (isValueChanged(transitionValues, transitionValues2, str)) {
                        return true;
                    }
                }
                return false;
            }
            for (String str2 : transitionValues.values.keySet()) {
                if (isValueChanged(transitionValues, transitionValues2, str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String toString(String str) {
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.mDuration != -1) {
            str2 = str2 + "dur(" + this.mDuration + ") ";
        }
        if (this.mStartDelay != -1) {
            str2 = str2 + "dly(" + this.mStartDelay + ") ";
        }
        if (this.mInterpolator != null) {
            str2 = str2 + "interp(" + this.mInterpolator + ") ";
        }
        if (this.mTargetIds.size() > 0 || this.mTargets.size() > 0) {
            String str3 = str2 + "tgts(";
            if (this.mTargetIds.size() > 0) {
                for (int i = 0; i < this.mTargetIds.size(); i++) {
                    if (i > 0) {
                        str3 = str3 + ", ";
                    }
                    str3 = str3 + this.mTargetIds.get(i);
                }
            }
            if (this.mTargets.size() > 0) {
                for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                    if (i2 > 0) {
                        str3 = str3 + ", ";
                    }
                    str3 = str3 + this.mTargets.get(i2);
                }
            }
            return str3 + ")";
        }
        return str2;
    }
}
