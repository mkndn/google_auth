package com.google.android.material.motion;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.TypedValue;
import android.view.animation.AnimationUtils;
import androidx.core.graphics.PathParser;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.google.android.material.resources.MaterialAttributes;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MotionUtils {
    private static float getLegacyControlPoint(String[] strArr, int i) {
        float parseFloat = Float.parseFloat(strArr[i]);
        if (parseFloat >= 0.0f && parseFloat <= 1.0f) {
            return parseFloat;
        }
        throw new IllegalArgumentException("Motion easing control point value must be between 0 and 1; instead got: " + parseFloat);
    }

    private static TimeInterpolator getLegacyThemeInterpolator(String str) {
        if (isLegacyEasingType(str, "cubic-bezier")) {
            String[] split = getLegacyEasingContent(str, "cubic-bezier").split(",");
            if (split.length != 4) {
                throw new IllegalArgumentException("Motion easing theme attribute must have 4 control points if using bezier curve format; instead got: " + split.length);
            }
            return PathInterpolatorCompat.create(getLegacyControlPoint(split, 0), getLegacyControlPoint(split, 1), getLegacyControlPoint(split, 2), getLegacyControlPoint(split, 3));
        } else if (isLegacyEasingType(str, "path")) {
            return PathInterpolatorCompat.create(PathParser.createPathFromPathData(getLegacyEasingContent(str, "path")));
        } else {
            throw new IllegalArgumentException("Invalid motion easing type: " + str);
        }
    }

    private static boolean isLegacyEasingAttribute(String str) {
        if (!isLegacyEasingType(str, "cubic-bezier") && !isLegacyEasingType(str, "path")) {
            return false;
        }
        return true;
    }

    private static boolean isLegacyEasingType(String str, String str2) {
        return str.startsWith(new StringBuilder().append(str2).append("(").toString()) && str.endsWith(")");
    }

    public static int resolveThemeDuration(Context context, int i, int i2) {
        return MaterialAttributes.resolveInteger(context, i, i2);
    }

    public static TimeInterpolator resolveThemeInterpolator(Context context, int i, TimeInterpolator timeInterpolator) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i, typedValue, true)) {
            return timeInterpolator;
        }
        if (typedValue.type != 3) {
            throw new IllegalArgumentException("Motion easing theme attribute must be an @interpolator resource for ?attr/motionEasing*Interpolator attributes or a string for ?attr/motionEasing* attributes.");
        }
        String valueOf = String.valueOf(typedValue.string);
        if (isLegacyEasingAttribute(valueOf)) {
            return getLegacyThemeInterpolator(valueOf);
        }
        return AnimationUtils.loadInterpolator(context, typedValue.resourceId);
    }

    private static String getLegacyEasingContent(String str, String str2) {
        return str.substring(str2.length() + "(".length(), str.length() - ")".length());
    }
}
