package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.app.FragmentAnim;
import android.support.v4.app.SpecialEffectsController;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
class DefaultSpecialEffectsController extends SpecialEffectsController {

    /* compiled from: PG */
    /* renamed from: android.support.v4.app.DefaultSpecialEffectsController$10  reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State;

        static {
            int[] iArr = new int[SpecialEffectsController.Operation.State.values().length];
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State = iArr;
            try {
                iArr[SpecialEffectsController.Operation.State.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator mAnimation;
        private boolean mIsPop;
        private boolean mLoadedAnim;

        AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z) {
            super(operation, cancellationSignal);
            this.mLoadedAnim = false;
            this.mIsPop = z;
        }

        FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            if (this.mLoadedAnim) {
                return this.mAnimation;
            }
            FragmentAnim.AnimationOrAnimator loadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.mIsPop);
            this.mAnimation = loadAnimation;
            this.mLoadedAnim = true;
            return loadAnimation;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final CancellationSignal mSignal;

        SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
        }

        void completeSpecialEffect() {
            this.mOperation.completeSpecialEffect(this.mSignal);
        }

        SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        CancellationSignal getSignal() {
            return this.mSignal;
        }

        boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(this.mOperation.getFragment().mView);
            SpecialEffectsController.Operation.State finalState = this.mOperation.getFinalState();
            return from == finalState || !(from == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
    }

    private void startAnimations(List list, List list2, boolean z, Map map) {
        int i;
        boolean z2;
        final SpecialEffectsController.Operation operation;
        final ViewGroup container = getContainer();
        Context context = container.getContext();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        boolean z3 = false;
        while (true) {
            i = 2;
            if (!it.hasNext()) {
                break;
            }
            final AnimationInfo animationInfo = (AnimationInfo) it.next();
            if (animationInfo.isVisibilityUnchanged()) {
                animationInfo.completeSpecialEffect();
            } else {
                FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
                if (animation == null) {
                    animationInfo.completeSpecialEffect();
                } else {
                    final Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(animationInfo);
                    } else {
                        final SpecialEffectsController.Operation operation2 = animationInfo.getOperation();
                        Fragment fragment = operation2.getFragment();
                        if (Boolean.TRUE.equals(map.get(operation2))) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            animationInfo.completeSpecialEffect();
                        } else {
                            boolean z4 = operation2.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            if (z4) {
                                list2.remove(operation2);
                            }
                            final View view = fragment.mView;
                            container.startViewTransition(view);
                            final boolean z5 = z4;
                            animator.addListener(new AnimatorListenerAdapter() { // from class: android.support.v4.app.DefaultSpecialEffectsController.2
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    container.endViewTransition(view);
                                    if (z5) {
                                        operation2.getFinalState().applyState(view);
                                    }
                                    animationInfo.completeSpecialEffect();
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v("FragmentManager", "Animator from operation " + operation2 + " has ended.");
                                    }
                                }
                            });
                            animator.setTarget(view);
                            animator.start();
                            if (!FragmentManager.isLoggingEnabled(2)) {
                                operation = operation2;
                            } else {
                                operation = operation2;
                                Log.v("FragmentManager", "Animator from operation " + operation + " has started.");
                            }
                            animationInfo.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: android.support.v4.app.DefaultSpecialEffectsController.3
                                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                                public void onCancel() {
                                    animator.end();
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v("FragmentManager", "Animator from operation " + operation + " has been canceled.");
                                    }
                                }
                            });
                            z3 = true;
                        }
                    }
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            final AnimationInfo animationInfo2 = (AnimationInfo) it2.next();
            final SpecialEffectsController.Operation operation3 = animationInfo2.getOperation();
            Fragment fragment2 = operation3.getFragment();
            if (z) {
                if (FragmentManager.isLoggingEnabled(i)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo2.completeSpecialEffect();
            } else if (z3) {
                if (FragmentManager.isLoggingEnabled(i)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo2.completeSpecialEffect();
            } else {
                final View view2 = fragment2.mView;
                Animation animation2 = (Animation) Preconditions.checkNotNull(((FragmentAnim.AnimationOrAnimator) Preconditions.checkNotNull(animationInfo2.getAnimation(context))).animation);
                if (operation3.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                    view2.startAnimation(animation2);
                    animationInfo2.completeSpecialEffect();
                    z2 = z3;
                } else {
                    container.startViewTransition(view2);
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation2, container, view2);
                    z2 = z3;
                    endViewTransitionAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: android.support.v4.app.DefaultSpecialEffectsController.4
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation3) {
                            container.post(new Runnable() { // from class: android.support.v4.app.DefaultSpecialEffectsController.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    container.endViewTransition(view2);
                                    animationInfo2.completeSpecialEffect();
                                }
                            });
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Animation from operation " + operation3 + " has ended.");
                            }
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation3) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation3) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Animation from operation " + operation3 + " has reached onAnimationStart.");
                            }
                        }
                    });
                    view2.startAnimation(endViewTransitionAnimation);
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Animation from operation " + operation3 + " has started.");
                    }
                }
                animationInfo2.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: android.support.v4.app.DefaultSpecialEffectsController.5
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public void onCancel() {
                        view2.clearAnimation();
                        container.endViewTransition(view2);
                        animationInfo2.completeSpecialEffect();
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "Animation from operation " + operation3 + " has been cancelled.");
                        }
                    }
                });
                i = 2;
                z3 = z2;
            }
        }
    }

    private Map startTransitions(List list, List list2, final boolean z, final SpecialEffectsController.Operation operation, final SpecialEffectsController.Operation operation2) {
        String str;
        View view;
        View view2;
        String str2;
        View view3;
        ArrayList arrayList;
        Object obj;
        Object obj2;
        String str3;
        ArrayList arrayList2;
        SpecialEffectsController.Operation operation3;
        View view4;
        ArrayMap arrayMap;
        SpecialEffectsController.Operation operation4;
        View view5;
        FragmentTransitionImpl fragmentTransitionImpl;
        ArrayList arrayList3;
        ArrayList arrayList4;
        Rect rect;
        HashMap hashMap;
        View view6;
        SharedElementCallback enterTransitionCallback;
        SharedElementCallback exitTransitionCallback;
        ArrayList arrayList5;
        final Rect rect2;
        String findKeyForValue;
        ArrayList arrayList6;
        boolean z2 = z;
        SpecialEffectsController.Operation operation5 = operation;
        SpecialEffectsController.Operation operation6 = operation2;
        HashMap hashMap2 = new HashMap();
        Iterator it = list.iterator();
        final FragmentTransitionImpl fragmentTransitionImpl2 = null;
        while (it.hasNext()) {
            TransitionInfo transitionInfo = (TransitionInfo) it.next();
            if (!transitionInfo.isVisibilityUnchanged()) {
                FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
                if (fragmentTransitionImpl2 != null) {
                    if (handlingImpl != null && fragmentTransitionImpl2 != handlingImpl) {
                        throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition  type than other Fragments.");
                    }
                } else {
                    fragmentTransitionImpl2 = handlingImpl;
                }
            }
        }
        if (fragmentTransitionImpl2 == null) {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                TransitionInfo transitionInfo2 = (TransitionInfo) it2.next();
                hashMap2.put(transitionInfo2.getOperation(), false);
                transitionInfo2.completeSpecialEffect();
            }
            return hashMap2;
        }
        View view7 = new View(getContainer().getContext());
        Rect rect3 = new Rect();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayMap arrayMap2 = new ArrayMap();
        Iterator it3 = list.iterator();
        Object obj3 = null;
        View view8 = null;
        boolean z3 = false;
        while (true) {
            str = "FragmentManager";
            if (!it3.hasNext()) {
                break;
            }
            TransitionInfo transitionInfo3 = (TransitionInfo) it3.next();
            if (transitionInfo3.hasSharedElementTransition() && operation5 != null && operation6 != null) {
                Object wrapTransitionInSet = fragmentTransitionImpl2.wrapTransitionInSet(fragmentTransitionImpl2.cloneTransition(transitionInfo3.getSharedElementTransition()));
                ArrayList sharedElementSourceNames = operation2.getFragment().getSharedElementSourceNames();
                ArrayList sharedElementSourceNames2 = operation.getFragment().getSharedElementSourceNames();
                ArrayList sharedElementTargetNames = operation.getFragment().getSharedElementTargetNames();
                View view9 = view8;
                int i = 0;
                while (true) {
                    hashMap = hashMap2;
                    view6 = view7;
                    if (i >= sharedElementTargetNames.size()) {
                        break;
                    }
                    int indexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i));
                    if (indexOf != -1) {
                        sharedElementSourceNames.set(indexOf, (String) sharedElementSourceNames2.get(i));
                    }
                    i++;
                    view7 = view6;
                    hashMap2 = hashMap;
                }
                ArrayList sharedElementTargetNames2 = operation2.getFragment().getSharedElementTargetNames();
                if (!z2) {
                    enterTransitionCallback = operation.getFragment().getExitTransitionCallback();
                    exitTransitionCallback = operation2.getFragment().getEnterTransitionCallback();
                } else {
                    enterTransitionCallback = operation.getFragment().getEnterTransitionCallback();
                    exitTransitionCallback = operation2.getFragment().getExitTransitionCallback();
                }
                int size = sharedElementSourceNames.size();
                int i2 = 0;
                while (i2 < size) {
                    arrayMap2.put((String) sharedElementSourceNames.get(i2), (String) sharedElementTargetNames2.get(i2));
                    i2++;
                    size = size;
                    rect3 = rect3;
                }
                Rect rect4 = rect3;
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", ">>> entering view names <<<");
                    for (Iterator it4 = sharedElementTargetNames2.iterator(); it4.hasNext(); it4 = it4) {
                        Log.v("FragmentManager", "Name: " + ((String) it4.next()));
                    }
                    Log.v("FragmentManager", ">>> exiting view names <<<");
                    for (Iterator it5 = sharedElementSourceNames.iterator(); it5.hasNext(); it5 = it5) {
                        Log.v("FragmentManager", "Name: " + ((String) it5.next()));
                    }
                }
                ArrayMap arrayMap3 = new ArrayMap();
                findNamedViews(arrayMap3, operation.getFragment().mView);
                arrayMap3.retainAll(sharedElementSourceNames);
                if (enterTransitionCallback != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Executing exit callback for operation " + operation5);
                    }
                    enterTransitionCallback.onMapSharedElements(sharedElementSourceNames, arrayMap3);
                    int size2 = sharedElementSourceNames.size() - 1;
                    while (size2 >= 0) {
                        String str4 = (String) sharedElementSourceNames.get(size2);
                        View view10 = (View) arrayMap3.get(str4);
                        if (view10 == null) {
                            arrayMap2.remove(str4);
                            arrayList6 = sharedElementSourceNames;
                        } else {
                            arrayList6 = sharedElementSourceNames;
                            if (!str4.equals(ViewCompat.getTransitionName(view10))) {
                                arrayMap2.put(ViewCompat.getTransitionName(view10), (String) arrayMap2.remove(str4));
                            }
                        }
                        size2--;
                        sharedElementSourceNames = arrayList6;
                    }
                    arrayList5 = sharedElementSourceNames;
                } else {
                    arrayList5 = sharedElementSourceNames;
                    arrayMap2.retainAll(arrayMap3.keySet());
                }
                final ArrayMap arrayMap4 = new ArrayMap();
                findNamedViews(arrayMap4, operation2.getFragment().mView);
                arrayMap4.retainAll(sharedElementTargetNames2);
                arrayMap4.retainAll(arrayMap2.values());
                if (exitTransitionCallback != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Executing enter callback for operation " + operation6);
                    }
                    exitTransitionCallback.onMapSharedElements(sharedElementTargetNames2, arrayMap4);
                    for (int size3 = sharedElementTargetNames2.size() - 1; size3 >= 0; size3--) {
                        String str5 = (String) sharedElementTargetNames2.get(size3);
                        View view11 = (View) arrayMap4.get(str5);
                        if (view11 == null) {
                            String findKeyForValue2 = FragmentTransition.findKeyForValue(arrayMap2, str5);
                            if (findKeyForValue2 != null) {
                                arrayMap2.remove(findKeyForValue2);
                            }
                        } else if (!str5.equals(ViewCompat.getTransitionName(view11)) && (findKeyForValue = FragmentTransition.findKeyForValue(arrayMap2, str5)) != null) {
                            arrayMap2.put(findKeyForValue, ViewCompat.getTransitionName(view11));
                        }
                    }
                } else {
                    FragmentTransition.retainValues(arrayMap2, arrayMap4);
                }
                retainMatchingViews(arrayMap3, arrayMap2.keySet());
                retainMatchingViews(arrayMap4, arrayMap2.values());
                if (arrayMap2.isEmpty()) {
                    arrayList7.clear();
                    arrayList8.clear();
                    arrayMap = arrayMap2;
                    operation4 = operation6;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    view8 = view9;
                    view5 = view6;
                    hashMap2 = hashMap;
                    obj3 = null;
                    arrayList3 = arrayList8;
                    arrayList4 = arrayList7;
                    rect = rect4;
                } else {
                    FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z2, arrayMap3, true);
                    arrayMap = arrayMap2;
                    arrayList3 = arrayList8;
                    ArrayList arrayList9 = arrayList7;
                    OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: android.support.v4.app.DefaultSpecialEffectsController.6
                        @Override // java.lang.Runnable
                        public void run() {
                            FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z, arrayMap4, false);
                        }
                    });
                    arrayList9.addAll(arrayMap3.values());
                    if (!arrayList5.isEmpty()) {
                        View view12 = (View) arrayMap3.get((String) arrayList5.get(0));
                        fragmentTransitionImpl2.setEpicenter(wrapTransitionInSet, view12);
                        view8 = view12;
                    } else {
                        view8 = view9;
                    }
                    arrayList3.addAll(arrayMap4.values());
                    if (sharedElementTargetNames2.isEmpty()) {
                        rect2 = rect4;
                    } else {
                        final View view13 = (View) arrayMap4.get((String) sharedElementTargetNames2.get(0));
                        if (view13 != null) {
                            rect2 = rect4;
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: android.support.v4.app.DefaultSpecialEffectsController.7
                                @Override // java.lang.Runnable
                                public void run() {
                                    fragmentTransitionImpl2.getBoundsOnScreen(view13, rect2);
                                }
                            });
                            z3 = true;
                        } else {
                            rect2 = rect4;
                        }
                    }
                    view5 = view6;
                    fragmentTransitionImpl2.setSharedElementTargets(wrapTransitionInSet, view5, arrayList9);
                    rect = rect2;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    fragmentTransitionImpl2.scheduleRemoveTargets(wrapTransitionInSet, null, null, null, null, wrapTransitionInSet, arrayList3);
                    arrayList4 = arrayList9;
                    hashMap2 = hashMap;
                    operation5 = operation;
                    hashMap2.put(operation5, true);
                    operation4 = operation2;
                    hashMap2.put(operation4, true);
                    obj3 = wrapTransitionInSet;
                }
            } else {
                arrayMap = arrayMap2;
                operation4 = operation6;
                view5 = view7;
                fragmentTransitionImpl = fragmentTransitionImpl2;
                arrayList3 = arrayList8;
                arrayList4 = arrayList7;
                rect = rect3;
                view8 = view8;
            }
            z2 = z;
            view7 = view5;
            rect3 = rect;
            arrayList8 = arrayList3;
            arrayList7 = arrayList4;
            operation6 = operation4;
            fragmentTransitionImpl2 = fragmentTransitionImpl;
            arrayMap2 = arrayMap;
        }
        View view14 = view8;
        ArrayMap arrayMap5 = arrayMap2;
        SpecialEffectsController.Operation operation7 = operation6;
        View view15 = view7;
        FragmentTransitionImpl fragmentTransitionImpl3 = fragmentTransitionImpl2;
        Collection<?> collection = arrayList8;
        Collection<?> collection2 = arrayList7;
        Rect rect5 = rect3;
        boolean z4 = false;
        ArrayList arrayList10 = new ArrayList();
        Iterator it6 = list.iterator();
        Object obj4 = null;
        Object obj5 = null;
        while (it6.hasNext()) {
            TransitionInfo transitionInfo4 = (TransitionInfo) it6.next();
            if (transitionInfo4.isVisibilityUnchanged()) {
                hashMap2.put(transitionInfo4.getOperation(), Boolean.valueOf(z4));
                transitionInfo4.completeSpecialEffect();
                it6 = it6;
                obj4 = obj4;
            } else {
                Iterator it7 = it6;
                Object obj6 = obj4;
                Object cloneTransition = fragmentTransitionImpl3.cloneTransition(transitionInfo4.getTransition());
                SpecialEffectsController.Operation operation8 = transitionInfo4.getOperation();
                boolean z5 = obj3 != null && (operation8 == operation5 || operation8 == operation7);
                if (cloneTransition == null) {
                    if (!z5) {
                        hashMap2.put(operation8, Boolean.valueOf(z4));
                        transitionInfo4.completeSpecialEffect();
                    }
                    view3 = view15;
                    arrayList = collection;
                    obj2 = obj5;
                    str3 = str;
                    arrayList2 = collection2;
                    view4 = view14;
                } else {
                    final ArrayList arrayList11 = new ArrayList();
                    Object obj7 = obj5;
                    captureTransitioningViews(arrayList11, operation8.getFragment().mView);
                    if (z5) {
                        if (operation8 == operation5) {
                            arrayList11.removeAll(collection2);
                        } else {
                            arrayList11.removeAll(collection);
                        }
                    }
                    if (arrayList11.isEmpty()) {
                        fragmentTransitionImpl3.addTarget(cloneTransition, view15);
                        view3 = view15;
                        arrayList = collection;
                        operation3 = operation8;
                        str3 = str;
                        arrayList2 = collection2;
                        obj = obj6;
                        obj2 = obj7;
                    } else {
                        fragmentTransitionImpl3.addTargets(cloneTransition, arrayList11);
                        view3 = view15;
                        arrayList = collection;
                        obj = obj6;
                        obj2 = obj7;
                        str3 = str;
                        arrayList2 = collection2;
                        fragmentTransitionImpl3.scheduleRemoveTargets(cloneTransition, cloneTransition, arrayList11, null, null, null, null);
                        if (operation8.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation3 = operation8;
                            list2.remove(operation3);
                            ArrayList arrayList12 = new ArrayList(arrayList11);
                            arrayList12.remove(operation3.getFragment().mView);
                            fragmentTransitionImpl3.scheduleHideFragmentView(cloneTransition, operation3.getFragment().mView, arrayList12);
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: android.support.v4.app.DefaultSpecialEffectsController.8
                                @Override // java.lang.Runnable
                                public void run() {
                                    FragmentTransition.setViewVisibility(arrayList11, 4);
                                }
                            });
                        } else {
                            operation3 = operation8;
                        }
                    }
                    if (operation3.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList10.addAll(arrayList11);
                        if (!z3) {
                            view4 = view14;
                        } else {
                            fragmentTransitionImpl3.setEpicenter(cloneTransition, rect5);
                            view4 = view14;
                        }
                    } else {
                        view4 = view14;
                        fragmentTransitionImpl3.setEpicenter(cloneTransition, view4);
                    }
                    hashMap2.put(operation3, true);
                    if (transitionInfo4.isOverlapAllowed()) {
                        obj2 = fragmentTransitionImpl3.mergeTransitionsTogether(obj2, cloneTransition, null);
                        obj6 = obj;
                    } else {
                        obj6 = fragmentTransitionImpl3.mergeTransitionsTogether(obj, cloneTransition, null);
                    }
                }
                it6 = it7;
                obj5 = obj2;
                view14 = view4;
                obj4 = obj6;
                view15 = view3;
                collection = arrayList;
                str = str3;
                collection2 = arrayList2;
                z4 = false;
            }
        }
        ArrayList arrayList13 = collection;
        String str6 = str;
        ArrayList arrayList14 = collection2;
        Object mergeTransitionsInSequence = fragmentTransitionImpl3.mergeTransitionsInSequence(obj5, obj4, obj3);
        if (mergeTransitionsInSequence == null) {
            return hashMap2;
        }
        Iterator it8 = list.iterator();
        while (it8.hasNext()) {
            final TransitionInfo transitionInfo5 = (TransitionInfo) it8.next();
            if (!transitionInfo5.isVisibilityUnchanged()) {
                Object transition = transitionInfo5.getTransition();
                final SpecialEffectsController.Operation operation9 = transitionInfo5.getOperation();
                boolean z6 = obj3 != null && (operation9 == operation5 || operation9 == operation7);
                if (transition != null || z6) {
                    if (!ViewCompat.isLaidOut(getContainer())) {
                        if (!FragmentManager.isLoggingEnabled(2)) {
                            str2 = str6;
                        } else {
                            str2 = str6;
                            Log.v(str2, "SpecialEffectsController: Container " + getContainer() + " has not been laid out. Completing operation " + operation9);
                        }
                        transitionInfo5.completeSpecialEffect();
                        str6 = str2;
                    } else {
                        fragmentTransitionImpl3.setListenerForTransitionEnd(transitionInfo5.getOperation().getFragment(), mergeTransitionsInSequence, transitionInfo5.getSignal(), new Runnable() { // from class: android.support.v4.app.DefaultSpecialEffectsController.9
                            @Override // java.lang.Runnable
                            public void run() {
                                transitionInfo5.completeSpecialEffect();
                                if (FragmentManager.isLoggingEnabled(2)) {
                                    Log.v("FragmentManager", "Transition for operation " + operation9 + "has completed");
                                }
                            }
                        });
                    }
                }
            }
        }
        String str7 = str6;
        if (!ViewCompat.isLaidOut(getContainer())) {
            return hashMap2;
        }
        FragmentTransition.setViewVisibility(arrayList10, 4);
        ArrayList prepareSetNameOverridesReordered = fragmentTransitionImpl3.prepareSetNameOverridesReordered(arrayList13);
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(str7, ">>>>> Beginning transition <<<<<");
            Log.v(str7, ">>>>> SharedElementFirstOutViews <<<<<");
            Iterator it9 = arrayList14.iterator();
            while (it9.hasNext()) {
                Log.v(str7, "View: " + ((View) it9.next()) + " Name: " + ViewCompat.getTransitionName(view2));
            }
            Log.v(str7, ">>>>> SharedElementLastInViews <<<<<");
            Iterator it10 = arrayList13.iterator();
            while (it10.hasNext()) {
                Log.v(str7, "View: " + ((View) it10.next()) + " Name: " + ViewCompat.getTransitionName(view));
            }
        }
        fragmentTransitionImpl3.beginDelayedTransition(getContainer(), mergeTransitionsInSequence);
        fragmentTransitionImpl3.setNameOverridesReordered(getContainer(), arrayList14, arrayList13, prepareSetNameOverridesReordered, arrayMap5);
        FragmentTransition.setViewVisibility(arrayList10, 0);
        fragmentTransitionImpl3.swapSharedElementTargets(obj3, arrayList14, arrayList13);
        return hashMap2;
    }

    void applyContainerChanges(SpecialEffectsController.Operation operation) {
        operation.getFinalState().applyState(operation.getFragment().mView);
    }

    void captureTransitioningViews(ArrayList arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
                if (!arrayList.contains(view)) {
                    arrayList.add(viewGroup);
                    return;
                }
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    captureTransitioningViews(arrayList, childAt);
                }
            }
        } else if (!arrayList.contains(view)) {
            arrayList.add(view);
        }
    }

    void findNamedViews(Map map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    void retainMatchingViews(ArrayMap arrayMap, Collection collection) {
        Iterator it = arrayMap.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(ViewCompat.getTransitionName((View) ((Map.Entry) it.next()).getValue()))) {
                it.remove();
            }
        }
    }

    @Override // android.support.v4.app.SpecialEffectsController
    void executeOperations(List list, boolean z) {
        Iterator it = list.iterator();
        SpecialEffectsController.Operation operation = null;
        SpecialEffectsController.Operation operation2 = null;
        while (it.hasNext()) {
            SpecialEffectsController.Operation operation3 = (SpecialEffectsController.Operation) it.next();
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(operation3.getFragment().mView);
            switch (AnonymousClass10.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[operation3.getFinalState().ordinal()]) {
                case 1:
                case 2:
                case 3:
                    if (from == SpecialEffectsController.Operation.State.VISIBLE && operation == null) {
                        operation = operation3;
                        break;
                    }
                    break;
                case 4:
                    if (from == SpecialEffectsController.Operation.State.VISIBLE) {
                        break;
                    } else {
                        operation2 = operation3;
                        break;
                    }
            }
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Executing operations from " + operation + " to " + operation2);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final ArrayList<SpecialEffectsController.Operation> arrayList3 = new ArrayList(list);
        Iterator it2 = list.iterator();
        while (true) {
            boolean z2 = true;
            if (it2.hasNext()) {
                final SpecialEffectsController.Operation operation4 = (SpecialEffectsController.Operation) it2.next();
                CancellationSignal cancellationSignal = new CancellationSignal();
                operation4.markStartedSpecialEffect(cancellationSignal);
                arrayList.add(new AnimationInfo(operation4, cancellationSignal, z));
                CancellationSignal cancellationSignal2 = new CancellationSignal();
                operation4.markStartedSpecialEffect(cancellationSignal2);
                if (!z) {
                    if (operation4 == operation2) {
                        arrayList2.add(new TransitionInfo(operation4, cancellationSignal2, z, z2));
                        operation4.addCompletionListener(new Runnable() { // from class: android.support.v4.app.DefaultSpecialEffectsController.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (arrayList3.contains(operation4)) {
                                    arrayList3.remove(operation4);
                                    DefaultSpecialEffectsController.this.applyContainerChanges(operation4);
                                }
                            }
                        });
                    }
                    z2 = false;
                    arrayList2.add(new TransitionInfo(operation4, cancellationSignal2, z, z2));
                    operation4.addCompletionListener(new Runnable() { // from class: android.support.v4.app.DefaultSpecialEffectsController.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (arrayList3.contains(operation4)) {
                                arrayList3.remove(operation4);
                                DefaultSpecialEffectsController.this.applyContainerChanges(operation4);
                            }
                        }
                    });
                } else {
                    if (operation4 == operation) {
                        arrayList2.add(new TransitionInfo(operation4, cancellationSignal2, z, z2));
                        operation4.addCompletionListener(new Runnable() { // from class: android.support.v4.app.DefaultSpecialEffectsController.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (arrayList3.contains(operation4)) {
                                    arrayList3.remove(operation4);
                                    DefaultSpecialEffectsController.this.applyContainerChanges(operation4);
                                }
                            }
                        });
                    }
                    z2 = false;
                    arrayList2.add(new TransitionInfo(operation4, cancellationSignal2, z, z2));
                    operation4.addCompletionListener(new Runnable() { // from class: android.support.v4.app.DefaultSpecialEffectsController.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (arrayList3.contains(operation4)) {
                                arrayList3.remove(operation4);
                                DefaultSpecialEffectsController.this.applyContainerChanges(operation4);
                            }
                        }
                    });
                }
            } else {
                Map startTransitions = startTransitions(arrayList2, arrayList3, z, operation, operation2);
                startAnimations(arrayList, arrayList3, startTransitions.containsValue(true), startTransitions);
                for (SpecialEffectsController.Operation operation5 : arrayList3) {
                    applyContainerChanges(operation5);
                }
                arrayList3.clear();
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Completed executing operations from " + operation + " to " + operation2);
                    return;
                }
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class TransitionInfo extends SpecialEffectsInfo {
        private final boolean mOverlapAllowed;
        private final Object mSharedElementTransition;
        private final Object mTransition;

        TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z, boolean z2) {
            super(operation, cancellationSignal);
            Object exitTransition;
            Object enterTransition;
            boolean allowEnterTransitionOverlap;
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                if (z) {
                    enterTransition = operation.getFragment().getReenterTransition();
                } else {
                    enterTransition = operation.getFragment().getEnterTransition();
                }
                this.mTransition = enterTransition;
                if (z) {
                    allowEnterTransitionOverlap = operation.getFragment().getAllowReturnTransitionOverlap();
                } else {
                    allowEnterTransitionOverlap = operation.getFragment().getAllowEnterTransitionOverlap();
                }
                this.mOverlapAllowed = allowEnterTransitionOverlap;
            } else {
                if (z) {
                    exitTransition = operation.getFragment().getReturnTransition();
                } else {
                    exitTransition = operation.getFragment().getExitTransition();
                }
                this.mTransition = exitTransition;
                this.mOverlapAllowed = true;
            }
            if (z2) {
                if (z) {
                    this.mSharedElementTransition = operation.getFragment().getSharedElementReturnTransition();
                    return;
                } else {
                    this.mSharedElementTransition = operation.getFragment().getSharedElementEnterTransition();
                    return;
                }
            }
            this.mSharedElementTransition = null;
        }

        FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.mSharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl != null ? handlingImpl : handlingImpl2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
        }

        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        Object getTransition() {
            return this.mTransition;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }

        private FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(obj)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(obj)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
