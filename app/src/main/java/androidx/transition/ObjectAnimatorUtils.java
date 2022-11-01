package androidx.transition;

import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.os.Build;
import android.util.Property;

/* compiled from: PG */
/* loaded from: classes.dex */
class ObjectAnimatorUtils {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class Api21Impl {
        static ObjectAnimator ofObject(Object obj, Property property, Path path) {
            return ObjectAnimator.ofObject(obj, property, (TypeConverter) null, path);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ObjectAnimator ofPointF(Object obj, Property property, Path path) {
        if (Build.VERSION.SDK_INT >= 21) {
            return Api21Impl.ofObject(obj, property, path);
        }
        return ObjectAnimator.ofFloat(obj, new PathProperty(property, path), 0.0f, 1.0f);
    }
}
