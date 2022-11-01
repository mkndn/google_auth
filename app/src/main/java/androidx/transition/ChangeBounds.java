package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ChangeBounds extends Transition {
    private static final String[] sTransitionProperties = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    private static final Property DRAWABLE_ORIGIN_PROPERTY = new Property(PointF.class, "boundsOrigin") { // from class: androidx.transition.ChangeBounds.1
        private Rect mBounds = new Rect();

        @Override // android.util.Property
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.mBounds);
            return new PointF(this.mBounds.left, this.mBounds.top);
        }

        @Override // android.util.Property
        public void set(Drawable drawable, PointF pointF) {
            drawable.copyBounds(this.mBounds);
            this.mBounds.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
            drawable.setBounds(this.mBounds);
        }
    };
    private static final Property TOP_LEFT_PROPERTY = new Property(PointF.class, "topLeft") { // from class: androidx.transition.ChangeBounds.2
        @Override // android.util.Property
        public PointF get(ViewBounds viewBounds) {
            return null;
        }

        @Override // android.util.Property
        public void set(ViewBounds viewBounds, PointF pointF) {
            viewBounds.setTopLeft(pointF);
        }
    };
    private static final Property BOTTOM_RIGHT_PROPERTY = new Property(PointF.class, "bottomRight") { // from class: androidx.transition.ChangeBounds.3
        @Override // android.util.Property
        public PointF get(ViewBounds viewBounds) {
            return null;
        }

        @Override // android.util.Property
        public void set(ViewBounds viewBounds, PointF pointF) {
            viewBounds.setBottomRight(pointF);
        }
    };
    private static final Property BOTTOM_RIGHT_ONLY_PROPERTY = new Property(PointF.class, "bottomRight") { // from class: androidx.transition.ChangeBounds.4
        @Override // android.util.Property
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        public void set(View view, PointF pointF) {
            ViewUtils.setLeftTopRightBottom(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
        }
    };
    private static final Property TOP_LEFT_ONLY_PROPERTY = new Property(PointF.class, "topLeft") { // from class: androidx.transition.ChangeBounds.5
        @Override // android.util.Property
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        public void set(View view, PointF pointF) {
            ViewUtils.setLeftTopRightBottom(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
        }
    };
    private static final Property POSITION_PROPERTY = new Property(PointF.class, "position") { // from class: androidx.transition.ChangeBounds.6
        @Override // android.util.Property
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        public void set(View view, PointF pointF) {
            int round = Math.round(pointF.x);
            int round2 = Math.round(pointF.y);
            ViewUtils.setLeftTopRightBottom(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
        }
    };
    private static RectEvaluator sRectEvaluator = new RectEvaluator();
    private int[] mTempLocation = new int[2];
    private boolean mResizeClip = false;
    private boolean mReparent = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ViewBounds {
        private int mBottom;
        private int mBottomRightCalls;
        private int mLeft;
        private int mRight;
        private int mTop;
        private int mTopLeftCalls;
        private View mView;

        ViewBounds(View view) {
            this.mView = view;
        }

        private void setLeftTopRightBottom() {
            ViewUtils.setLeftTopRightBottom(this.mView, this.mLeft, this.mTop, this.mRight, this.mBottom);
            this.mTopLeftCalls = 0;
            this.mBottomRightCalls = 0;
        }

        void setBottomRight(PointF pointF) {
            this.mRight = Math.round(pointF.x);
            this.mBottom = Math.round(pointF.y);
            int i = this.mBottomRightCalls + 1;
            this.mBottomRightCalls = i;
            if (this.mTopLeftCalls == i) {
                setLeftTopRightBottom();
            }
        }

        void setTopLeft(PointF pointF) {
            this.mLeft = Math.round(pointF.x);
            this.mTop = Math.round(pointF.y);
            int i = this.mTopLeftCalls + 1;
            this.mTopLeftCalls = i;
            if (i == this.mBottomRightCalls) {
                setLeftTopRightBottom();
            }
        }
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (ViewCompat.isLaidOut(view) || view.getWidth() != 0 || view.getHeight() != 0) {
            transitionValues.values.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            transitionValues.values.put("android:changeBounds:parent", transitionValues.view.getParent());
            if (this.mReparent) {
                transitionValues.view.getLocationInWindow(this.mTempLocation);
                transitionValues.values.put("android:changeBounds:windowX", Integer.valueOf(this.mTempLocation[0]));
                transitionValues.values.put("android:changeBounds:windowY", Integer.valueOf(this.mTempLocation[1]));
            }
            if (this.mResizeClip) {
                transitionValues.values.put("android:changeBounds:clip", ViewCompat.getClipBounds(view));
            }
        }
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // androidx.transition.Transition
    public Animator createAnimator(final ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int i;
        final View view;
        ObjectAnimator ofPointF;
        int i2;
        Rect rect;
        Rect rect2;
        ObjectAnimator objectAnimator;
        Animator mergeAnimators;
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        Map map = transitionValues.values;
        Map map2 = transitionValues2.values;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 != null && viewGroup3 != null) {
            final View view2 = transitionValues2.view;
            if (parentMatches(viewGroup2, viewGroup3)) {
                Rect rect3 = (Rect) transitionValues.values.get("android:changeBounds:bounds");
                Rect rect4 = (Rect) transitionValues2.values.get("android:changeBounds:bounds");
                int i3 = rect3.left;
                final int i4 = rect4.left;
                int i5 = rect3.top;
                final int i6 = rect4.top;
                int i7 = rect3.right;
                final int i8 = rect4.right;
                int i9 = rect3.bottom;
                final int i10 = rect4.bottom;
                int i11 = i7 - i3;
                int i12 = i9 - i5;
                int i13 = i8 - i4;
                int i14 = i10 - i6;
                Rect rect5 = (Rect) transitionValues.values.get("android:changeBounds:clip");
                final Rect rect6 = (Rect) transitionValues2.values.get("android:changeBounds:clip");
                if ((i11 != 0 && i12 != 0) || (i13 != 0 && i14 != 0)) {
                    i = (i3 == i4 && i5 == i6) ? 0 : 1;
                    if (i7 != i8 || i9 != i10) {
                        i++;
                    }
                } else {
                    i = 0;
                }
                if ((rect5 != null && !rect5.equals(rect6)) || (rect5 == null && rect6 != null)) {
                    i++;
                }
                if (i > 0) {
                    if (!this.mResizeClip) {
                        view = view2;
                        ViewUtils.setLeftTopRightBottom(view, i3, i5, i7, i9);
                        if (i == 2) {
                            if (i11 == i13 && i12 == i14) {
                                mergeAnimators = ObjectAnimatorUtils.ofPointF(view, POSITION_PROPERTY, getPathMotion().getPath(i3, i5, i4, i6));
                            } else {
                                ViewBounds viewBounds = new ViewBounds(view);
                                ObjectAnimator ofPointF2 = ObjectAnimatorUtils.ofPointF(viewBounds, TOP_LEFT_PROPERTY, getPathMotion().getPath(i3, i5, i4, i6));
                                ObjectAnimator ofPointF3 = ObjectAnimatorUtils.ofPointF(viewBounds, BOTTOM_RIGHT_PROPERTY, getPathMotion().getPath(i7, i9, i8, i10));
                                AnimatorSet animatorSet = new AnimatorSet();
                                animatorSet.playTogether(ofPointF2, ofPointF3);
                                animatorSet.addListener(new AnimatorListenerAdapter(viewBounds) { // from class: androidx.transition.ChangeBounds.7
                                    private ViewBounds mViewBounds;
                                    final /* synthetic */ ViewBounds val$viewBounds;

                                    {
                                        this.val$viewBounds = viewBounds;
                                        this.mViewBounds = viewBounds;
                                    }
                                });
                                mergeAnimators = animatorSet;
                            }
                        } else if (i3 == i4 && i5 == i6) {
                            mergeAnimators = ObjectAnimatorUtils.ofPointF(view, BOTTOM_RIGHT_ONLY_PROPERTY, getPathMotion().getPath(i7, i9, i8, i10));
                        } else {
                            mergeAnimators = ObjectAnimatorUtils.ofPointF(view, TOP_LEFT_ONLY_PROPERTY, getPathMotion().getPath(i3, i5, i4, i6));
                        }
                    } else {
                        view = view2;
                        ViewUtils.setLeftTopRightBottom(view, i3, i5, Math.max(i11, i13) + i3, Math.max(i12, i14) + i5);
                        if (i3 != i4 || i5 != i6) {
                            ofPointF = ObjectAnimatorUtils.ofPointF(view, POSITION_PROPERTY, getPathMotion().getPath(i3, i5, i4, i6));
                        } else {
                            ofPointF = null;
                        }
                        if (rect5 != null) {
                            i2 = 0;
                            rect = rect5;
                        } else {
                            i2 = 0;
                            rect = new Rect(0, 0, i11, i12);
                        }
                        if (rect6 == null) {
                            rect2 = new Rect(i2, i2, i13, i14);
                        } else {
                            rect2 = rect6;
                        }
                        if (!rect.equals(rect2)) {
                            ViewCompat.setClipBounds(view, rect);
                            ObjectAnimator ofObject = ObjectAnimator.ofObject(view, "clipBounds", sRectEvaluator, rect, rect2);
                            ofObject.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.ChangeBounds.8
                                private boolean mIsCanceled;

                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationCancel(Animator animator) {
                                    this.mIsCanceled = true;
                                }

                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator) {
                                    if (!this.mIsCanceled) {
                                        ViewCompat.setClipBounds(view, rect6);
                                        ViewUtils.setLeftTopRightBottom(view, i4, i6, i8, i10);
                                    }
                                }
                            });
                            objectAnimator = ofObject;
                        } else {
                            objectAnimator = null;
                        }
                        mergeAnimators = TransitionUtils.mergeAnimators(ofPointF, objectAnimator);
                    }
                    if (view.getParent() instanceof ViewGroup) {
                        final ViewGroup viewGroup4 = (ViewGroup) view.getParent();
                        ViewGroupUtils.suppressLayout(viewGroup4, true);
                        addListener(new TransitionListenerAdapter() { // from class: androidx.transition.ChangeBounds.9
                            boolean mCanceled = false;

                            @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                            public void onTransitionCancel(Transition transition) {
                                ViewGroupUtils.suppressLayout(viewGroup4, false);
                                this.mCanceled = true;
                            }

                            @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                            public void onTransitionEnd(Transition transition) {
                                if (!this.mCanceled) {
                                    ViewGroupUtils.suppressLayout(viewGroup4, false);
                                }
                                transition.removeListener(this);
                            }

                            @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                            public void onTransitionPause(Transition transition) {
                                ViewGroupUtils.suppressLayout(viewGroup4, false);
                            }

                            @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                            public void onTransitionResume(Transition transition) {
                                ViewGroupUtils.suppressLayout(viewGroup4, true);
                            }
                        });
                    }
                    return mergeAnimators;
                }
                return null;
            }
            int intValue = ((Integer) transitionValues.values.get("android:changeBounds:windowX")).intValue();
            int intValue2 = ((Integer) transitionValues.values.get("android:changeBounds:windowY")).intValue();
            int intValue3 = ((Integer) transitionValues2.values.get("android:changeBounds:windowX")).intValue();
            int intValue4 = ((Integer) transitionValues2.values.get("android:changeBounds:windowY")).intValue();
            if (intValue == intValue3 && intValue2 == intValue4) {
                return null;
            }
            viewGroup.getLocationInWindow(this.mTempLocation);
            Bitmap createBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
            view2.draw(new Canvas(createBitmap));
            final BitmapDrawable bitmapDrawable = new BitmapDrawable(createBitmap);
            final float transitionAlpha = ViewUtils.getTransitionAlpha(view2);
            ViewUtils.setTransitionAlpha(view2, 0.0f);
            ViewUtils.getOverlay(viewGroup).add(bitmapDrawable);
            PathMotion pathMotion = getPathMotion();
            int[] iArr = this.mTempLocation;
            int i15 = iArr[0];
            int i16 = iArr[1];
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, PropertyValuesHolderUtils.ofPointF(DRAWABLE_ORIGIN_PROPERTY, pathMotion.getPath(intValue - i15, intValue2 - i16, intValue3 - i15, intValue4 - i16)));
            ofPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.ChangeBounds.10
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    ViewUtils.getOverlay(viewGroup).remove(bitmapDrawable);
                    ViewUtils.setTransitionAlpha(view2, transitionAlpha);
                }
            });
            return ofPropertyValuesHolder;
        }
        return null;
    }

    @Override // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    private boolean parentMatches(View view, View view2) {
        if (this.mReparent) {
            TransitionValues matchedTransitionValues = getMatchedTransitionValues(view, true);
            return matchedTransitionValues == null ? view == view2 : view2 == matchedTransitionValues.view;
        }
        return true;
    }
}
