package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.appcompat.R$dimen;
import android.support.v7.appcompat.R$id;
import android.support.v7.appcompat.R$layout;
import android.support.v7.appcompat.R$style;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class TooltipPopup {
    private final View mContentView;
    private final Context mContext;
    private final WindowManager.LayoutParams mLayoutParams;
    private final TextView mMessageView;
    private final int[] mTmpAnchorPos;
    private final int[] mTmpAppPos;
    private final Rect mTmpDisplayFrame;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TooltipPopup(Context context) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        this.mTmpDisplayFrame = new Rect();
        this.mTmpAnchorPos = new int[2];
        this.mTmpAppPos = new int[2];
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R$layout.abc_tooltip, (ViewGroup) null);
        this.mContentView = inflate;
        this.mMessageView = (TextView) inflate.findViewById(R$id.message);
        layoutParams.setTitle(getClass().getSimpleName());
        layoutParams.packageName = context.getPackageName();
        layoutParams.type = 1002;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = R$style.Animation_AppCompat_Tooltip;
        layoutParams.flags = 24;
    }

    private void computePosition(View view, int i, int i2, boolean z, WindowManager.LayoutParams layoutParams) {
        int height;
        int i3;
        int i4;
        layoutParams.token = view.getApplicationWindowToken();
        int dimensionPixelOffset = this.mContext.getResources().getDimensionPixelOffset(R$dimen.tooltip_precise_anchor_threshold);
        if (view.getWidth() < dimensionPixelOffset) {
            i = view.getWidth() / 2;
        }
        if (view.getHeight() >= dimensionPixelOffset) {
            int dimensionPixelOffset2 = this.mContext.getResources().getDimensionPixelOffset(R$dimen.tooltip_precise_anchor_extra_offset);
            height = i2 + dimensionPixelOffset2;
            i3 = i2 - dimensionPixelOffset2;
        } else {
            height = view.getHeight();
            i3 = 0;
        }
        layoutParams.gravity = 49;
        int dimensionPixelOffset3 = this.mContext.getResources().getDimensionPixelOffset(z ? R$dimen.tooltip_y_offset_touch : R$dimen.tooltip_y_offset_non_touch);
        View appRootView = getAppRootView(view);
        if (appRootView == null) {
            Log.e("TooltipPopup", "Cannot find app view");
            return;
        }
        appRootView.getWindowVisibleDisplayFrame(this.mTmpDisplayFrame);
        if (this.mTmpDisplayFrame.left < 0 && this.mTmpDisplayFrame.top < 0) {
            Resources resources = this.mContext.getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            if (identifier != 0) {
                i4 = resources.getDimensionPixelSize(identifier);
            } else {
                i4 = 0;
            }
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            this.mTmpDisplayFrame.set(0, i4, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        appRootView.getLocationOnScreen(this.mTmpAppPos);
        view.getLocationOnScreen(this.mTmpAnchorPos);
        int[] iArr = this.mTmpAnchorPos;
        int i5 = iArr[0];
        int[] iArr2 = this.mTmpAppPos;
        int i6 = i5 - iArr2[0];
        iArr[0] = i6;
        iArr[1] = iArr[1] - iArr2[1];
        layoutParams.x = (i6 + i) - (appRootView.getWidth() / 2);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mContentView.measure(makeMeasureSpec, makeMeasureSpec);
        int measuredHeight = this.mContentView.getMeasuredHeight();
        int i7 = this.mTmpAnchorPos[1];
        int i8 = ((i3 + i7) - dimensionPixelOffset3) - measuredHeight;
        int i9 = i7 + height + dimensionPixelOffset3;
        if (z) {
            if (i8 >= 0) {
                layoutParams.y = i8;
            } else {
                layoutParams.y = i9;
            }
        } else if (measuredHeight + i9 <= this.mTmpDisplayFrame.height()) {
            layoutParams.y = i9;
        } else {
            layoutParams.y = i8;
        }
    }

    private static View getAppRootView(View view) {
        View rootView = view.getRootView();
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        if ((layoutParams instanceof WindowManager.LayoutParams) && ((WindowManager.LayoutParams) layoutParams).type == 2) {
            return rootView;
        }
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return ((Activity) context).getWindow().getDecorView();
            }
        }
        return rootView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void hide() {
        if (!isShowing()) {
            return;
        }
        ((WindowManager) this.mContext.getSystemService("window")).removeView(this.mContentView);
    }

    boolean isShowing() {
        return this.mContentView.getParent() != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void show(View view, int i, int i2, boolean z, CharSequence charSequence) {
        if (isShowing()) {
            hide();
        }
        this.mMessageView.setText(charSequence);
        computePosition(view, i, i2, z, this.mLayoutParams);
        ((WindowManager) this.mContext.getSystemService("window")).addView(this.mContentView, this.mLayoutParams);
    }
}
