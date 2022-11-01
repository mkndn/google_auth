package androidx.transition;

import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.os.Build;
import android.util.Property;

/* compiled from: PG */
/* loaded from: classes.dex */
class PropertyValuesHolderUtils {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class Api21Impl {
        static PropertyValuesHolder ofObject(Property property, Path path) {
            return PropertyValuesHolder.ofObject(property, (TypeConverter) null, path);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PropertyValuesHolder ofPointF(Property property, Path path) {
        if (Build.VERSION.SDK_INT >= 21) {
            return Api21Impl.ofObject(property, path);
        }
        return PropertyValuesHolder.ofFloat(new PathProperty(property, path), 0.0f, 1.0f);
    }
}
