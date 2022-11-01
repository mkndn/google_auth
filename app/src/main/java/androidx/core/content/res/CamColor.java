package androidx.core.content.res;

import androidx.core.graphics.ColorUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class CamColor {
    private final float mAstar;
    private final float mBstar;
    private final float mChroma;
    private final float mHue;
    private final float mJ;
    private final float mJstar;
    private final float mM;
    private final float mQ;
    private final float mS;

    CamColor(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.mHue = f;
        this.mChroma = f2;
        this.mJ = f3;
        this.mQ = f4;
        this.mM = f5;
        this.mS = f6;
        this.mJstar = f7;
        this.mAstar = f8;
        this.mBstar = f9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CamColor fromColor(int i) {
        return fromColorInViewingConditions(i, ViewingConditions.DEFAULT);
    }

    static CamColor fromColorInViewingConditions(int i, ViewingConditions viewingConditions) {
        float f;
        float[] xyzFromInt = CamUtils.xyzFromInt(i);
        float[][] fArr = CamUtils.XYZ_TO_CAM16RGB;
        float f2 = xyzFromInt[0];
        float[] fArr2 = fArr[0];
        float f3 = fArr2[0];
        float f4 = xyzFromInt[1];
        float f5 = fArr2[1];
        float f6 = xyzFromInt[2];
        float f7 = fArr2[2];
        float[] fArr3 = fArr[1];
        float f8 = fArr3[0];
        float f9 = fArr3[1];
        float f10 = fArr3[2];
        float[] fArr4 = fArr[2];
        float f11 = fArr4[0];
        float f12 = fArr4[1];
        float f13 = fArr4[2];
        float f14 = viewingConditions.getRgbD()[0] * ((f3 * f2) + (f5 * f4) + (f7 * f6));
        float f15 = viewingConditions.getRgbD()[1] * ((f8 * f2) + (f9 * f4) + (f10 * f6));
        float f16 = viewingConditions.getRgbD()[2] * ((f2 * f11) + (f4 * f12) + (f6 * f13));
        double fl = viewingConditions.getFl() * Math.abs(f14);
        Double.isNaN(fl);
        float pow = (float) Math.pow(fl / 100.0d, 0.42d);
        double fl2 = viewingConditions.getFl() * Math.abs(f15);
        Double.isNaN(fl2);
        float pow2 = (float) Math.pow(fl2 / 100.0d, 0.42d);
        double fl3 = viewingConditions.getFl() * Math.abs(f16);
        Double.isNaN(fl3);
        float pow3 = (float) Math.pow(fl3 / 100.0d, 0.42d);
        float signum = ((Math.signum(f14) * 400.0f) * pow) / (pow + 27.13f);
        float signum2 = ((Math.signum(f15) * 400.0f) * pow2) / (pow2 + 27.13f);
        float signum3 = ((Math.signum(f16) * 400.0f) * pow3) / (pow3 + 27.13f);
        double d = signum;
        Double.isNaN(d);
        double d2 = signum2;
        Double.isNaN(d2);
        double d3 = (d * 11.0d) + (d2 * (-12.0d));
        double d4 = signum3;
        Double.isNaN(d4);
        float f17 = ((float) (d3 + d4)) / 11.0f;
        double d5 = signum + signum2;
        Double.isNaN(d4);
        Double.isNaN(d4);
        Double.isNaN(d5);
        float f18 = ((float) (d5 - (d4 + d4))) / 9.0f;
        float f19 = signum2 * 20.0f;
        float f20 = (((signum * 20.0f) + f19) + (21.0f * signum3)) / 20.0f;
        float f21 = (((signum * 40.0f) + f19) + signum3) / 20.0f;
        float atan2 = (((float) Math.atan2(f18, f17)) * 180.0f) / 3.1415927f;
        if (atan2 < 0.0f) {
            f = atan2 + 360.0f;
        } else {
            if (atan2 >= 360.0f) {
                atan2 -= 360.0f;
            }
            f = atan2;
        }
        float f22 = (3.1415927f * f) / 180.0f;
        float pow4 = ((float) Math.pow((f21 * viewingConditions.getNbb()) / viewingConditions.getAw(), viewingConditions.getC() * viewingConditions.getZ())) * 100.0f;
        float c = (4.0f / viewingConditions.getC()) * ((float) Math.sqrt(pow4 / 100.0f)) * (viewingConditions.getAw() + 4.0f) * viewingConditions.getFlRoot();
        double d6 = ((double) f) < 20.14d ? 360.0f + f : f;
        Double.isNaN(d6);
        double cos = Math.cos(((d6 * 3.141592653589793d) / 180.0d) + 2.0d);
        float pow5 = ((float) Math.pow(1.64d - Math.pow(0.29d, viewingConditions.getN()), 0.73d)) * ((float) Math.pow((((((((float) (cos + 3.8d)) * 0.25f) * 3846.1538f) * viewingConditions.getNc()) * viewingConditions.getNcb()) * ((float) Math.sqrt((f17 * f17) + (f18 * f18)))) / (f20 + 0.305f), 0.9d));
        double d7 = pow4;
        Double.isNaN(d7);
        float sqrt = pow5 * ((float) Math.sqrt(d7 / 100.0d));
        float flRoot = sqrt * viewingConditions.getFlRoot();
        double sqrt2 = Math.sqrt((pow5 * viewingConditions.getC()) / (viewingConditions.getAw() + 4.0f));
        float log = ((float) Math.log((0.0228f * flRoot) + 1.0f)) * 43.85965f;
        double d8 = f22;
        return new CamColor(f, sqrt, pow4, c, flRoot, ((float) sqrt2) * 50.0f, (1.7f * pow4) / ((0.007f * pow4) + 1.0f), log * ((float) Math.cos(d8)), log * ((float) Math.sin(d8)));
    }

    private static CamColor fromJch(float f, float f2, float f3) {
        return fromJchInFrame(f, f2, f3, ViewingConditions.DEFAULT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int toColor(float f, float f2, float f3) {
        return toColor(f, f2, f3, ViewingConditions.DEFAULT);
    }

    float distance(CamColor camColor) {
        float jStar = getJStar() - camColor.getJStar();
        float aStar = getAStar() - camColor.getAStar();
        float bStar = getBStar() - camColor.getBStar();
        return (float) (Math.pow(Math.sqrt((jStar * jStar) + (aStar * aStar) + (bStar * bStar)), 0.63d) * 1.41d);
    }

    float getAStar() {
        return this.mAstar;
    }

    float getBStar() {
        return this.mBstar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getChroma() {
        return this.mChroma;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getHue() {
        return this.mHue;
    }

    float getJ() {
        return this.mJ;
    }

    float getJStar() {
        return this.mJstar;
    }

    int viewed(ViewingConditions viewingConditions) {
        float f;
        if (getChroma() == 0.0d || getJ() == 0.0d) {
            f = 0.0f;
        } else {
            float chroma = getChroma();
            double j = getJ();
            Double.isNaN(j);
            f = chroma / ((float) Math.sqrt(j / 100.0d));
        }
        double d = f;
        double pow = Math.pow(1.64d - Math.pow(0.29d, viewingConditions.getN()), 0.73d);
        Double.isNaN(d);
        float pow2 = (float) Math.pow(d / pow, 1.1111111111111112d);
        double hue = (getHue() * 3.1415927f) / 180.0f;
        Double.isNaN(hue);
        double cos = Math.cos(2.0d + hue);
        float aw = viewingConditions.getAw();
        float j2 = getJ();
        float c = viewingConditions.getC();
        float z = viewingConditions.getZ();
        double d2 = j2;
        Double.isNaN(d2);
        double d3 = c;
        Double.isNaN(d3);
        double d4 = 1.0d / d3;
        double d5 = z;
        Double.isNaN(d5);
        double pow3 = Math.pow(d2 / 100.0d, d4 / d5);
        float nc = viewingConditions.getNc();
        float ncb = viewingConditions.getNcb();
        float nbb = (aw * ((float) pow3)) / viewingConditions.getNbb();
        float sin = (float) Math.sin(hue);
        float cos2 = (float) Math.cos(hue);
        float f2 = (((0.305f + nbb) * 23.0f) * pow2) / (((((((((float) (cos + 3.8d)) * 0.25f) * 3846.1538f) * nc) * ncb) * 23.0f) + ((11.0f * pow2) * cos2)) + ((pow2 * 108.0f) * sin));
        float f3 = cos2 * f2;
        float f4 = f2 * sin;
        float f5 = nbb * 460.0f;
        float f6 = (((451.0f * f3) + f5) + (288.0f * f4)) / 1403.0f;
        float f7 = ((f5 - (891.0f * f3)) - (261.0f * f4)) / 1403.0f;
        float f8 = ((f5 - (f3 * 220.0f)) - (f4 * 6300.0f)) / 1403.0f;
        float abs = Math.abs(f6);
        float abs2 = Math.abs(f6);
        double d6 = abs;
        Double.isNaN(d6);
        double d7 = abs2;
        Double.isNaN(d7);
        double max = Math.max(0.0d, (d6 * 27.13d) / (400.0d - d7));
        float signum = Math.signum(f6);
        float fl = viewingConditions.getFl();
        double pow4 = Math.pow((float) max, 2.380952380952381d);
        float abs3 = Math.abs(f7);
        float abs4 = Math.abs(f7);
        double d8 = abs3;
        Double.isNaN(d8);
        double d9 = abs4;
        Double.isNaN(d9);
        double max2 = Math.max(0.0d, (d8 * 27.13d) / (400.0d - d9));
        float signum2 = Math.signum(f7);
        float fl2 = viewingConditions.getFl();
        double pow5 = Math.pow((float) max2, 2.380952380952381d);
        float abs5 = Math.abs(f8);
        float abs6 = Math.abs(f8);
        double d10 = abs5;
        Double.isNaN(d10);
        double d11 = abs6;
        Double.isNaN(d11);
        double max3 = Math.max(0.0d, (d10 * 27.13d) / (400.0d - d11));
        float signum3 = Math.signum(f8);
        float fl3 = viewingConditions.getFl();
        double pow6 = Math.pow((float) max3, 2.380952380952381d);
        float f9 = ((signum * (100.0f / fl)) * ((float) pow4)) / viewingConditions.getRgbD()[0];
        float f10 = ((signum2 * (100.0f / fl2)) * ((float) pow5)) / viewingConditions.getRgbD()[1];
        float f11 = ((signum3 * (100.0f / fl3)) * ((float) pow6)) / viewingConditions.getRgbD()[2];
        float[][] fArr = CamUtils.CAM16RGB_TO_XYZ;
        float[] fArr2 = fArr[0];
        float f12 = (fArr2[0] * f9) + (fArr2[1] * f10) + (fArr2[2] * f11);
        float[] fArr3 = fArr[1];
        float[] fArr4 = fArr[2];
        return ColorUtils.XYZToColor(f12, (fArr3[0] * f9) + (fArr3[1] * f10) + (fArr3[2] * f11), (f9 * fArr4[0]) + (f10 * fArr4[1]) + (f11 * fArr4[2]));
    }

    int viewedInSrgb() {
        return viewed(ViewingConditions.DEFAULT);
    }

    private static CamColor fromJchInFrame(float f, float f2, float f3, ViewingConditions viewingConditions) {
        float c = viewingConditions.getC();
        double d = f;
        Double.isNaN(d);
        double d2 = d / 100.0d;
        double sqrt = Math.sqrt(d2);
        float aw = viewingConditions.getAw();
        float flRoot = viewingConditions.getFlRoot();
        float flRoot2 = viewingConditions.getFlRoot() * f2;
        double sqrt2 = Math.sqrt(d2);
        double sqrt3 = Math.sqrt(((f2 / ((float) sqrt2)) * viewingConditions.getC()) / (viewingConditions.getAw() + 4.0f));
        double d3 = flRoot2;
        Double.isNaN(d3);
        float log = ((float) Math.log((d3 * 0.0228d) + 1.0d)) * 43.85965f;
        double d4 = (3.1415927f * f3) / 180.0f;
        return new CamColor(f3, f2, f, (4.0f / c) * ((float) sqrt) * (aw + 4.0f) * flRoot, flRoot2, ((float) sqrt3) * 50.0f, (1.7f * f) / ((0.007f * f) + 1.0f), log * ((float) Math.cos(d4)), log * ((float) Math.sin(d4)));
    }

    static int toColor(float f, float f2, float f3, ViewingConditions viewingConditions) {
        if (f2 >= 1.0d && Math.round(f3) > 0.0d && Math.round(f3) < 100.0d) {
            float min = f < 0.0f ? 0.0f : Math.min(360.0f, f);
            float f4 = f2;
            CamColor camColor = null;
            float f5 = 0.0f;
            boolean z = true;
            while (Math.abs(f5 - f2) >= 0.4f) {
                CamColor findCamByJ = findCamByJ(min, f4, f3);
                if (z) {
                    if (findCamByJ == null) {
                        f4 = ((f2 - f5) / 2.0f) + f5;
                        z = false;
                    } else {
                        return findCamByJ.viewed(viewingConditions);
                    }
                } else {
                    if (findCamByJ == null) {
                        f2 = f4;
                    } else {
                        f5 = f4;
                        camColor = findCamByJ;
                    }
                    f4 = ((f2 - f5) / 2.0f) + f5;
                }
            }
            if (camColor == null) {
                return CamUtils.intFromLStar(f3);
            }
            return camColor.viewed(viewingConditions);
        }
        return CamUtils.intFromLStar(f3);
    }

    private static CamColor findCamByJ(float f, float f2, float f3) {
        float f4 = 100.0f;
        float f5 = 1000.0f;
        CamColor camColor = null;
        float f6 = 1000.0f;
        float f7 = 0.0f;
        while (Math.abs(f7 - f4) > 0.01f) {
            float f8 = ((f4 - f7) / 2.0f) + f7;
            int viewedInSrgb = fromJch(f8, f2, f).viewedInSrgb();
            float lStarFromInt = CamUtils.lStarFromInt(viewedInSrgb);
            float abs = Math.abs(f3 - lStarFromInt);
            if (abs < 0.2f) {
                CamColor fromColor = fromColor(viewedInSrgb);
                float distance = fromColor.distance(fromJch(fromColor.getJ(), fromColor.getChroma(), f));
                if (distance <= 1.0f) {
                    camColor = fromColor;
                    f6 = abs;
                    f5 = distance;
                }
            }
            if (f6 == 0.0f && f5 == 0.0f) {
                break;
            } else if (lStarFromInt < f3) {
                f7 = f8;
            } else {
                f4 = f8;
            }
        }
        return camColor;
    }
}
