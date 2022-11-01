package com.google.android.apps.authenticator.api;

import android.content.Context;
import android.os.Build;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FacetIdCalculator {
    static final byte[] AUTHENTICATOR_DEV_FINGERPRINT;
    static final byte[] AUTHENTICATOR_FINGERPRINT;
    static final String AUTHENTICATOR_PACKAGE_NAME = "com.google.android.apps.authenticator2";
    static final byte[] CHROME_BETA_ON_N_PLUS_FINGERPRINT;
    static final String CHROME_BETA_PACKAGE_NAME = "com.chrome.beta";
    static final byte[] CHROME_CANARY_ON_N_PLUS_FINGERPRINT;
    static final String CHROME_CANARY_PACKAGE_NAME = "com.chrome.canary";
    static final byte[] CHROME_DEV_AND_BETA_ON_PRE_N_FINGERPRINT;
    static final byte[] CHROME_DEV_ON_N_PLUS_FINGERPRINT;
    static final String CHROME_DEV_PACKAGE_NAME = "com.chrome.dev";
    static final byte[] CHROME_STABLE_ON_RELEASE_BUILD_FINGERPRINT;
    static final byte[] CHROME_STABLE_ON_USERDEBUG_BUILD_FINGERPRINT;
    static final String CHROME_STABLE_PACKAGE_NAME = "com.android.chrome";
    private static final byte[] COMMON_RELEASE_FINGERPRINT;
    private final BuildTypeManager buildTypeManager;
    private final Context context;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class BuildTypeManager {
        BuildTypeManager() {
        }

        int getSdkVersion() {
            return Build.VERSION.SDK_INT;
        }

        boolean isDevKeysBuild(Context context) {
            return GooglePlayServicesUtilLight.honorsDebugCertificates(context);
        }
    }

    static {
        byte[] bArr = {-16, -3, 108, 91, 65, Ascii.SI, 37, -53, 37, -61, -75, 51, 70, -56, -105, 47, -82, 48, -8, -18, 116, 17, -33, -111, 4, UnsignedBytes.MAX_POWER_OF_TWO, -83, 107, 45, 96, -37, -125};
        COMMON_RELEASE_FINGERPRINT = bArr;
        CHROME_STABLE_ON_RELEASE_BUILD_FINGERPRINT = bArr;
        CHROME_STABLE_ON_USERDEBUG_BUILD_FINGERPRINT = new byte[]{Ascii.EM, 117, -78, -15, 113, 119, -68, -119, -91, -33, -13, Ascii.US, -98, 100, -90, -54, -30, -127, -91, 61, -63, -47, -43, -101, Ascii.GS, Ascii.DC4, Ascii.DEL, -31, -56, 42, -6, 0};
        CHROME_BETA_ON_N_PLUS_FINGERPRINT = new byte[]{-38, 99, 61, 52, -74, -98, 99, -82, 33, 3, -76, -99, 83, -50, 5, 47, -59, -9, -13, -59, 58, -85, -108, -3, -62, -94, 8, -67, -3, Ascii.DC4, 36, -100};
        CHROME_DEV_ON_N_PLUS_FINGERPRINT = new byte[]{-112, 68, -18, 95, -18, 75, -68, 94, 33, -35, 68, 102, 84, 49, -60, -21, Ascii.US, Ascii.US, 113, -93, 39, Ascii.SYN, -96, -68, -110, 123, -53, -77, -110, 51, -54, -65};
        CHROME_DEV_AND_BETA_ON_PRE_N_FINGERPRINT = new byte[]{61, 122, Ascii.DC2, 35, 1, -102, -93, -99, -98, -96, -29, 67, 106, -73, -64, -119, 107, -5, 79, -74, 121, -12, -34, 95, -25, -62, 63, 50, 108, -113, -103, 74};
        CHROME_CANARY_ON_N_PLUS_FINGERPRINT = new byte[]{32, Ascii.EM, -33, -95, -5, 35, -17, -65, 112, -59, -68, -47, 68, 60, 91, -22, -80, 79, 63, 47, -12, 54, 110, -102, -63, -29, 69, 118, 57, -94, 76, -4};
        AUTHENTICATOR_FINGERPRINT = bArr;
        AUTHENTICATOR_DEV_FINGERPRINT = new byte[]{Ascii.DLE, 57, 56, -18, 69, 55, -27, -98, -114, -25, -110, -10, 84, 80, 79, -72, 52, 111, -58, -77, 70, -48, -69, -60, 65, 95, -61, 57, -4, -4, -114, -63};
    }

    public FacetIdCalculator(Context context) {
        this.context = context;
        this.buildTypeManager = new BuildTypeManager();
    }

    private boolean isSignatureInSet(byte[] bArr, Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (Arrays.equals(bArr, (byte[]) it.next())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean isKnownBrowser(String str, Set set) {
        HashSet<byte[]> hashSet = new HashSet();
        switch (str.hashCode()) {
            case -1450225804:
                if (str.equals(AUTHENTICATOR_PACKAGE_NAME)) {
                    hashSet.add(AUTHENTICATOR_FINGERPRINT);
                    if (this.buildTypeManager.isDevKeysBuild(this.context)) {
                        hashSet.add(AUTHENTICATOR_DEV_FINGERPRINT);
                    }
                    for (byte[] bArr : hashSet) {
                        if (isSignatureInSet(bArr, set)) {
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            case -1221330953:
                if (str.equals(CHROME_BETA_PACKAGE_NAME)) {
                    if (this.buildTypeManager.getSdkVersion() >= 24) {
                        hashSet.add(CHROME_BETA_ON_N_PLUS_FINGERPRINT);
                    } else {
                        hashSet.add(CHROME_DEV_AND_BETA_ON_PRE_N_FINGERPRINT);
                    }
                    while (r5.hasNext()) {
                    }
                    return false;
                }
                return false;
            case -1148214049:
                if (!str.equals(CHROME_CANARY_PACKAGE_NAME) || this.buildTypeManager.getSdkVersion() < 24) {
                    return false;
                }
                hashSet.add(CHROME_CANARY_ON_N_PLUS_FINGERPRINT);
                while (r5.hasNext()) {
                }
                return false;
            case 256457446:
                if (str.equals(CHROME_STABLE_PACKAGE_NAME)) {
                    hashSet.add(CHROME_STABLE_ON_RELEASE_BUILD_FINGERPRINT);
                    if (this.buildTypeManager.isDevKeysBuild(this.context)) {
                        hashSet.add(CHROME_STABLE_ON_USERDEBUG_BUILD_FINGERPRINT);
                    }
                    while (r5.hasNext()) {
                    }
                    return false;
                }
                return false;
            case 1900266798:
                if (str.equals(CHROME_DEV_PACKAGE_NAME)) {
                    if (this.buildTypeManager.getSdkVersion() >= 24) {
                        hashSet.add(CHROME_DEV_ON_N_PLUS_FINGERPRINT);
                    } else {
                        hashSet.add(CHROME_DEV_AND_BETA_ON_PRE_N_FINGERPRINT);
                    }
                    while (r5.hasNext()) {
                    }
                    return false;
                }
                return false;
            default:
                return false;
        }
    }

    public FacetIdCalculator(Context context, BuildTypeManager buildTypeManager) {
        this.context = context;
        this.buildTypeManager = buildTypeManager;
    }
}
