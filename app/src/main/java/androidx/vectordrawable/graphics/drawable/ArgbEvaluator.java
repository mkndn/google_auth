package androidx.vectordrawable.graphics.drawable;

import android.animation.TypeEvaluator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ArgbEvaluator implements TypeEvaluator {
    private static final ArgbEvaluator sInstance = new ArgbEvaluator();

    public static ArgbEvaluator getInstance() {
        return sInstance;
    }

    @Override // android.animation.TypeEvaluator
    public Object evaluate(float f, Object obj, Object obj2) {
        int intValue = ((Integer) obj).intValue();
        float f2 = ((intValue >> 24) & 255) / 255.0f;
        int intValue2 = ((Integer) obj2).intValue();
        float pow = (float) Math.pow(((intValue >> 16) & 255) / 255.0f, 2.2d);
        float pow2 = (float) Math.pow(((intValue >> 8) & 255) / 255.0f, 2.2d);
        float pow3 = (float) Math.pow((intValue & 255) / 255.0f, 2.2d);
        double pow4 = Math.pow(((intValue2 >> 16) & 255) / 255.0f, 2.2d);
        double pow5 = Math.pow(((intValue2 >> 8) & 255) / 255.0f, 2.2d);
        float pow6 = pow3 + ((((float) Math.pow((intValue2 & 255) / 255.0f, 2.2d)) - pow3) * f);
        double pow7 = Math.pow(pow2 + (f * (((float) pow5) - pow2)), 0.45454545454545453d);
        double pow8 = Math.pow(pow6, 0.45454545454545453d);
        int round = Math.round(((float) Math.pow(pow + ((((float) pow4) - pow) * f), 0.45454545454545453d)) * 255.0f) << 16;
        return Integer.valueOf(Math.round(((float) pow8) * 255.0f) | round | (Math.round((f2 + (((((intValue2 >> 24) & 255) / 255.0f) - f2) * f)) * 255.0f) << 24) | (Math.round(((float) pow7) * 255.0f) << 8));
    }
}
