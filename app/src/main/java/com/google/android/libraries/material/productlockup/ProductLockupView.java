package com.google.android.libraries.material.productlockup;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.android.libraries.stitch.util.Preconditions;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ProductLockupView extends ViewGroup {
    public static final int LOGO_COLOR_DARK = 1;
    public static final int LOGO_COLOR_GREY = 3;
    public static final int LOGO_COLOR_LIGHT = 2;
    public static final int LOGO_COLOR_STANDARD = 0;
    public static final int SIZING_MODE_AUTO = 0;
    public static final int SIZING_MODE_CUSTOM = -1;
    public static final int SIZING_MODE_NORMAL = 1;
    public static final int SIZING_MODE_SMALL = 2;
    private static Typeface productSansRegular;
    private int[] customResolvedSizingModes;
    private SizingModeDimens customSizingModeDimens;
    private int logoColor;
    private Drawable logoDrawable;
    final ImageView logoView;
    private final SizingModeDimens normalSizingModeDimens;
    private String productName;
    private boolean productNameContainsGoogleWithSpace;
    private boolean productNameLatin;
    final TextView productNameView;
    private int sizingMode;
    SizingModeDimens sizingModeDimens;
    private final SizingModeDimens smallSizingModeDimens;
    private boolean startsWithLogo;
    private static final Pattern GOOGLE_WITH_SPACE_PATTERN = Pattern.compile(String.format(" %1$s|%1$s ", AccountDb.GOOGLE_ISSUER_NAME));
    private static final int[] RESOLVED_SIZING_MODES_NORMAL = {1};
    private static final int[] RESOLVED_SIZING_MODES_SMALL = {2};
    private static final int[] RESOLVED_SIZING_MODES_AUTO = {1, 2};

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SizingModeDimens {
        final int logoHeight;
        final int logoMarginTop;
        final int logoWidth;
        final int separationMargin;
        final float textSize;

        public SizingModeDimens(Resources resources, int i, int i2, int i3, int i4, int i5) {
            this.textSize = resources.getDimension(i);
            this.logoMarginTop = resources.getDimensionPixelOffset(i2);
            this.logoWidth = resources.getDimensionPixelSize(i3);
            this.logoHeight = resources.getDimensionPixelSize(i4);
            this.separationMargin = resources.getDimensionPixelOffset(i5);
        }
    }

    public ProductLockupView(Context context) {
        this(context, null);
    }

    private int calculateHeight() {
        return Math.max(this.logoView.getMeasuredHeight() + this.sizingModeDimens.logoMarginTop, this.productNameView.getMeasuredHeight());
    }

    private int calculateLogoLeft() {
        if (this.productNameView.getVisibility() == 8 || this.startsWithLogo) {
            return 0;
        }
        return getSeparationMarginIfNeeded(this.sizingModeDimens) + this.productNameView.getMeasuredWidth();
    }

    private int calculateProductNameLeft() {
        if (this.logoView.getVisibility() == 8 || !this.startsWithLogo) {
            return 0;
        }
        return getSeparationMarginIfNeeded(this.sizingModeDimens) + this.logoView.getMeasuredWidth();
    }

    private String getProductNameToRender() {
        if (this.productName.startsWith(AccountDb.GOOGLE_ISSUER_NAME)) {
            return this.productName.substring(AccountDb.GOOGLE_ISSUER_NAME.length()).trim();
        }
        if (this.productName.endsWith(AccountDb.GOOGLE_ISSUER_NAME)) {
            String str = this.productName;
            return str.substring(0, str.length() - AccountDb.GOOGLE_ISSUER_NAME.length()).trim();
        }
        return this.productName;
    }

    private int[] getResolvedSizingModes(int i) {
        switch (i) {
            case -1:
                return this.customResolvedSizingModes;
            case 0:
                return RESOLVED_SIZING_MODES_AUTO;
            case 1:
                return RESOLVED_SIZING_MODES_NORMAL;
            case 2:
                return RESOLVED_SIZING_MODES_SMALL;
            default:
                throw new IllegalStateException("Unrecognized sizingMode: " + i);
        }
    }

    private int getSeparationMarginIfNeeded(SizingModeDimens sizingModeDimens) {
        return Math.round(sizingModeDimens.separationMargin * (this.productNameContainsGoogleWithSpace ? 1.0f : 0.15f));
    }

    private Drawable getTintedLogoDrawable(int i) {
        Drawable mutate = DrawableCompat.wrap(this.logoDrawable).mutate();
        DrawableCompat.setTint(mutate, ContextCompat.getColor(getContext(), i));
        return mutate;
    }

    private static boolean isStringLatin(String str) {
        return TextUtils.isEmpty(str) || Charset.forName("ISO-8859-1").newEncoder().canEncode(str);
    }

    private void layoutLogo() {
        int calculateLogoLeft = calculateLogoLeft();
        int i = this.sizingModeDimens.logoMarginTop;
        layoutChild(this.logoView, calculateLogoLeft, i, calculateLogoLeft + this.logoView.getMeasuredWidth(), i + this.logoView.getMeasuredHeight());
    }

    private void layoutProductName() {
        int calculateProductNameLeft = calculateProductNameLeft();
        int measuredWidth = this.productNameView.getMeasuredWidth();
        layoutChild(this.productNameView, calculateProductNameLeft, 0, calculateProductNameLeft + measuredWidth, this.productNameView.getMeasuredHeight());
    }

    private void measureLogo() {
        this.logoView.measure(View.MeasureSpec.makeMeasureSpec(this.sizingModeDimens.logoWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(this.sizingModeDimens.logoHeight, 1073741824));
    }

    private void measureLogoAndProductName(int i) {
        this.logoView.setVisibility(0);
        this.productNameView.setVisibility(0);
        this.startsWithLogo = this.productName.startsWith(AccountDb.GOOGLE_ISSUER_NAME);
        boolean endsWith = this.productName.endsWith(AccountDb.GOOGLE_ISSUER_NAME);
        int[] resolvedSizingModes = getResolvedSizingModes(this.sizingMode);
        if (this.startsWithLogo || endsWith) {
            for (int i2 : resolvedSizingModes) {
                setSizingModeDimens(i2);
                if (fitsInAvailableWidth(i, true)) {
                    measureLogo();
                    measureProductName();
                    return;
                }
            }
        }
        for (int i3 : resolvedSizingModes) {
            setSizingModeDimens(i3);
            if (fitsInAvailableWidth(i, false)) {
                measureProductName();
                this.logoView.setVisibility(8);
                return;
            }
        }
        setSizingModeDimens(1);
        this.startsWithLogo = true;
        measureLogo();
        this.productNameView.setVisibility(8);
    }

    private void measureProductName() {
        this.productNameView.setTextSize(0, this.sizingModeDimens.textSize);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.productNameView.measure(makeMeasureSpec, makeMeasureSpec);
    }

    private static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    private void setSizingModeDimens(int i) {
        switch (i) {
            case -1:
                this.sizingModeDimens = this.customSizingModeDimens;
                return;
            case 0:
                this.sizingModeDimens = this.normalSizingModeDimens;
                return;
            case 1:
                this.sizingModeDimens = this.normalSizingModeDimens;
                return;
            case 2:
                this.sizingModeDimens = this.smallSizingModeDimens;
                return;
            default:
                throw new IllegalStateException("Unrecognized sizingMode: " + i);
        }
    }

    private void updateLogoDrawable() {
        int i = this.logoColor;
        if (i == 0) {
            this.logoView.setImageDrawable(this.logoDrawable);
        } else {
            this.logoView.setImageDrawable(getTintedLogoDrawable(convertLogoColorToResourceId(i)));
        }
    }

    protected int convertLogoColorToResourceId(int i) {
        switch (i) {
            case 1:
                return R$color.google_black;
            case 2:
                return R$color.google_white;
            case 3:
                return R$color.google_grey700;
            default:
                throw new IllegalStateException("Unrecognized logoColor: " + i);
        }
    }

    public boolean isLogoVisible() {
        return this.logoView.getVisibility() == 0;
    }

    public boolean isProductNameVisible() {
        return this.productNameView.getVisibility() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.logoView.getVisibility() != 8) {
            layoutLogo();
        }
        if (this.productNameView.getVisibility() != 8) {
            layoutProductName();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        measureLogoAndProductName(View.MeasureSpec.getSize(i));
        setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(calculateWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(calculateHeight(), 1073741824));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.productName = savedState.productName;
        this.productNameContainsGoogleWithSpace = savedState.productNameContainsGoogleWithSpace;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.productName = this.productName;
        savedState.productNameContainsGoogleWithSpace = this.productNameContainsGoogleWithSpace;
        return savedState;
    }

    public void setLogoColor(int i) {
        this.logoColor = i;
        updateLogoDrawable();
    }

    public void setProductName(String str) {
        String trim = nullToEmpty(str).trim();
        this.productName = trim;
        this.productNameContainsGoogleWithSpace = GOOGLE_WITH_SPACE_PATTERN.matcher(trim).find();
        this.productNameLatin = isStringLatin(str);
        this.productNameView.setText(getProductNameToRender());
        setContentDescription(this.productName.isEmpty() ? AccountDb.GOOGLE_ISSUER_NAME : this.productName);
        requestLayout();
    }

    public void setProductNameTextColor(int i) {
        this.productNameView.setTextColor(i);
    }

    private int calculateWidth() {
        int i;
        boolean isLogoVisible = isLogoVisible();
        boolean isProductNameVisible = isProductNameVisible();
        if (isLogoVisible) {
            i = this.logoView.getMeasuredWidth();
        } else {
            i = 0;
        }
        if (isProductNameVisible) {
            i += this.productNameView.getMeasuredWidth();
        }
        if (isLogoVisible && isProductNameVisible) {
            return i + getSeparationMarginIfNeeded(this.sizingModeDimens);
        }
        return i;
    }

    private boolean fitsInAvailableWidth(int i, boolean z) {
        int i2;
        if (z) {
            i2 = this.sizingModeDimens.logoWidth + getSeparationMarginIfNeeded(this.sizingModeDimens);
        } else {
            i2 = 0;
        }
        measureProductName();
        return i2 + this.productNameView.getMeasuredWidth() <= i;
    }

    private void layoutChild(View view, int i, int i2, int i3, int i4) {
        boolean z = true;
        z = (ViewCompat.getLayoutDirection(this) != 1 || this.productNameLatin) ? false : false;
        int measuredWidth = z ? getMeasuredWidth() - i3 : i;
        if (z) {
            i3 = getMeasuredWidth() - i;
        }
        view.layout(measuredWidth, i2, i3, i4);
    }

    public ProductLockupView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.productLockupViewStyle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.google.android.libraries.material.productlockup.ProductLockupView.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        String productName;
        boolean productNameContainsGoogleWithSpace;

        private SavedState(Parcel parcel) {
            super(parcel);
            this.productName = parcel.readString();
            this.productNameContainsGoogleWithSpace = parcel.readInt() == 1;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.productName);
            parcel.writeInt(this.productNameContainsGoogleWithSpace ? 1 : 0);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public ProductLockupView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (productSansRegular == null) {
            productSansRegular = Typeface.createFromAsset(context.getAssets(), "ProductSans-Regular.ttf");
        }
        this.normalSizingModeDimens = new SizingModeDimens(getResources(), R$dimen.product_name_text_size, R$dimen.logo_margin_top, R$dimen.logo_width, R$dimen.logo_height, R$dimen.separation_margin);
        this.smallSizingModeDimens = new SizingModeDimens(getResources(), R$dimen.product_name_text_size_small, R$dimen.logo_margin_top_small, R$dimen.logo_width_small, R$dimen.logo_height_small, R$dimen.separation_margin_small);
        LayoutInflater.from(context).inflate(R$layout.product_lockup_view, (ViewGroup) this, true);
        this.logoView = (ImageView) Preconditions.checkNotNull((ImageView) findViewById(R$id.logo));
        TextView textView = (TextView) Preconditions.checkNotNull((TextView) findViewById(R$id.product_name));
        this.productNameView = textView;
        textView.setTypeface(productSansRegular);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ProductLockupView, i, R$style.Widget_GoogleMaterial_ProductLockupView);
        if (obtainStyledAttributes.hasValue(R$styleable.ProductLockupView_logo)) {
            this.logoDrawable = obtainStyledAttributes.getDrawable(R$styleable.ProductLockupView_logo);
        } else {
            this.logoDrawable = AppCompatResources.getDrawable(context, R$drawable.googlelogo_standard_color_74x24);
        }
        int i2 = obtainStyledAttributes.getInt(R$styleable.ProductLockupView_lockupSizingMode, 0);
        this.sizingMode = i2;
        setSizingModeDimens(i2);
        setProductNameTextColor(obtainStyledAttributes.getColor(R$styleable.ProductLockupView_productNameTextColor, 0));
        setProductName(obtainStyledAttributes.getString(R$styleable.ProductLockupView_android_text));
        setLogoColor(obtainStyledAttributes.getInt(R$styleable.ProductLockupView_logoColor, 0));
        obtainStyledAttributes.recycle();
    }
}
