package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PathParser {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;

        ExtractFloatResult() {
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class PathDataNode {
        public float[] mParams;
        public char mType;

        PathDataNode(char c, float[] fArr) {
            this.mType = c;
            this.mParams = fArr;
        }

        private static void arcToBezier(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
            double d10 = d3;
            int ceil = (int) Math.ceil(Math.abs((d9 * 4.0d) / 3.141592653589793d));
            double cos = Math.cos(d7);
            double sin = Math.sin(d7);
            double cos2 = Math.cos(d8);
            double sin2 = Math.sin(d8);
            double d11 = -d10;
            double d12 = d11 * cos;
            double d13 = d4 * sin;
            double d14 = (d12 * sin2) - (d13 * cos2);
            double d15 = d11 * sin;
            double d16 = d4 * cos;
            double d17 = (sin2 * d15) + (cos2 * d16);
            double d18 = ceil;
            Double.isNaN(d18);
            double d19 = d9 / d18;
            double d20 = d8;
            double d21 = d17;
            double d22 = d14;
            int i = 0;
            double d23 = d5;
            double d24 = d6;
            while (i < ceil) {
                double d25 = d20 + d19;
                double sin3 = Math.sin(d25);
                double cos3 = Math.cos(d25);
                double d26 = (d + ((d10 * cos) * cos3)) - (d13 * sin3);
                double d27 = d2 + (d10 * sin * cos3) + (d16 * sin3);
                double d28 = (d12 * sin3) - (d13 * cos3);
                double d29 = (sin3 * d15) + (cos3 * d16);
                double d30 = d25 - d20;
                double tan = Math.tan(d30 / 2.0d);
                double sin4 = (Math.sin(d30) * (Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d)) / 3.0d;
                path.rLineTo(0.0f, 0.0f);
                path.cubicTo((float) (d23 + (d22 * sin4)), (float) (d24 + (d21 * sin4)), (float) (d26 - (sin4 * d28)), (float) (d27 - (sin4 * d29)), (float) d26, (float) d27);
                i++;
                d24 = d27;
                d23 = d26;
                sin = sin;
                d20 = d25;
                d21 = d29;
                cos = cos;
                d22 = d28;
                ceil = ceil;
                d10 = d3;
            }
        }

        private static void drawArc(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            double d;
            double d2;
            double radians = Math.toRadians(f7);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d3 = f;
            Double.isNaN(d3);
            double d4 = d3 * cos;
            double d5 = f2;
            Double.isNaN(d5);
            double d6 = f5;
            Double.isNaN(d6);
            double d7 = (d4 + (d5 * sin)) / d6;
            double d8 = -f;
            Double.isNaN(d8);
            Double.isNaN(d5);
            double d9 = f6;
            Double.isNaN(d9);
            double d10 = ((d8 * sin) + (d5 * cos)) / d9;
            double d11 = f3;
            Double.isNaN(d11);
            double d12 = f4;
            Double.isNaN(d12);
            Double.isNaN(d6);
            double d13 = ((d11 * cos) + (d12 * sin)) / d6;
            double d14 = -f3;
            Double.isNaN(d14);
            Double.isNaN(d12);
            Double.isNaN(d9);
            double d15 = ((d14 * sin) + (d12 * cos)) / d9;
            double d16 = d7 - d13;
            double d17 = d10 - d15;
            double d18 = (d7 + d13) / 2.0d;
            double d19 = (d10 + d15) / 2.0d;
            double d20 = (d16 * d16) + (d17 * d17);
            if (d20 == 0.0d) {
                Log.w("PathParser", " Points are coincident");
                return;
            }
            double d21 = (1.0d / d20) - 0.25d;
            if (d21 < 0.0d) {
                Log.w("PathParser", "Points are too far apart " + d20);
                float sqrt = (float) (Math.sqrt(d20) / 1.99999d);
                drawArc(path, f, f2, f3, f4, f5 * sqrt, f6 * sqrt, f7, z, z2);
                return;
            }
            double sqrt2 = Math.sqrt(d21);
            double d22 = d16 * sqrt2;
            double d23 = sqrt2 * d17;
            if (z == z2) {
                d = d18 - d23;
                d2 = d19 + d22;
            } else {
                d = d18 + d23;
                d2 = d19 - d22;
            }
            double atan2 = Math.atan2(d10 - d2, d7 - d);
            double atan22 = Math.atan2(d15 - d2, d13 - d) - atan2;
            if (z2 != (atan22 >= 0.0d)) {
                if (atan22 > 0.0d) {
                    atan22 -= 6.283185307179586d;
                } else {
                    atan22 += 6.283185307179586d;
                }
            }
            Double.isNaN(d6);
            double d24 = d * d6;
            Double.isNaN(d9);
            double d25 = d2 * d9;
            arcToBezier(path, (d24 * cos) - (d25 * sin), (d24 * sin) + (d25 * cos), d6, d9, d3, d5, radians, atan2, atan22);
        }

        public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
            float[] fArr = new float[6];
            char c = 'm';
            for (int i = 0; i < pathDataNodeArr.length; i++) {
                PathDataNode pathDataNode = pathDataNodeArr[i];
                addCommand(path, fArr, c, pathDataNode.mType, pathDataNode.mParams);
                c = pathDataNodeArr[i].mType;
            }
        }

        public void interpolatePathDataNode(PathDataNode pathDataNode, PathDataNode pathDataNode2, float f) {
            this.mType = pathDataNode.mType;
            int i = 0;
            while (true) {
                float[] fArr = pathDataNode.mParams;
                if (i < fArr.length) {
                    this.mParams[i] = (fArr[i] * (1.0f - f)) + (pathDataNode2.mParams[i] * f);
                    i++;
                } else {
                    return;
                }
            }
        }

        private static void addCommand(Path path, float[] fArr, char c, char c2, float[] fArr2) {
            int i;
            float f;
            float f2;
            int i2;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            float f8 = fArr[0];
            float f9 = fArr[1];
            float f10 = fArr[2];
            float f11 = fArr[3];
            float f12 = fArr[4];
            float f13 = fArr[5];
            switch (c2) {
                case 'A':
                case 'a':
                    i = 7;
                    break;
                case 'C':
                case 'c':
                    i = 6;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i = 1;
                    break;
                case 'L':
                case 'M':
                case 'T':
                case 'l':
                case 'm':
                case 't':
                    i = 2;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    path.moveTo(f12, f13);
                    f8 = f12;
                    f10 = f8;
                    f9 = f13;
                    f11 = f9;
                    i = 2;
                    break;
                default:
                    i = 2;
                    break;
            }
            float f14 = f8;
            float f15 = f9;
            float f16 = f12;
            float f17 = f13;
            int i3 = 0;
            char c3 = c;
            while (i3 < fArr2.length) {
                float f18 = 0.0f;
                switch (c2) {
                    case 'A':
                        i2 = i3;
                        int i4 = i2 + 5;
                        int i5 = i2 + 6;
                        drawArc(path, f14, f15, fArr2[i4], fArr2[i5], fArr2[i2], fArr2[i2 + 1], fArr2[i2 + 2], fArr2[i2 + 3] != 0.0f, fArr2[i2 + 4] != 0.0f);
                        f14 = fArr2[i4];
                        f15 = fArr2[i5];
                        f11 = f15;
                        f10 = f14;
                        break;
                    case 'C':
                        i2 = i3;
                        int i6 = i2 + 2;
                        int i7 = i2 + 3;
                        int i8 = i2 + 4;
                        int i9 = i2 + 5;
                        path.cubicTo(fArr2[i2], fArr2[i2 + 1], fArr2[i6], fArr2[i7], fArr2[i8], fArr2[i9]);
                        f14 = fArr2[i8];
                        float f19 = fArr2[i9];
                        float f20 = fArr2[i6];
                        float f21 = fArr2[i7];
                        f15 = f19;
                        f11 = f21;
                        f10 = f20;
                        break;
                    case 'H':
                        i2 = i3;
                        path.lineTo(fArr2[i2], f15);
                        f14 = fArr2[i2];
                        break;
                    case 'L':
                        i2 = i3;
                        int i10 = i2 + 1;
                        path.lineTo(fArr2[i2], fArr2[i10]);
                        f14 = fArr2[i2];
                        f15 = fArr2[i10];
                        break;
                    case 'M':
                        i2 = i3;
                        f14 = fArr2[i2];
                        f15 = fArr2[i2 + 1];
                        if (i2 > 0) {
                            path.lineTo(f14, f15);
                            break;
                        } else {
                            path.moveTo(f14, f15);
                            f17 = f15;
                            f16 = f14;
                            break;
                        }
                    case 'Q':
                        i2 = i3;
                        int i11 = i2 + 1;
                        int i12 = i2 + 2;
                        int i13 = i2 + 3;
                        path.quadTo(fArr2[i2], fArr2[i11], fArr2[i12], fArr2[i13]);
                        float f22 = fArr2[i2];
                        float f23 = fArr2[i11];
                        f14 = fArr2[i12];
                        f15 = fArr2[i13];
                        f10 = f22;
                        f11 = f23;
                        break;
                    case 'S':
                        i2 = i3;
                        float f24 = f15;
                        float f25 = f14;
                        if (c3 == 'c' || c3 == 's' || c3 == 'C' || c3 == 'S') {
                            float f26 = (f25 + f25) - f10;
                            f4 = (f24 + f24) - f11;
                            f5 = f26;
                        } else {
                            f4 = f24;
                            f5 = f25;
                        }
                        int i14 = i2 + 1;
                        int i15 = i2 + 2;
                        int i16 = i2 + 3;
                        path.cubicTo(f5, f4, fArr2[i2], fArr2[i14], fArr2[i15], fArr2[i16]);
                        float f27 = fArr2[i2];
                        float f28 = fArr2[i14];
                        float f29 = fArr2[i15];
                        f15 = fArr2[i16];
                        f11 = f28;
                        f14 = f29;
                        f10 = f27;
                        break;
                    case 'T':
                        i2 = i3;
                        float f30 = f15;
                        float f31 = f14;
                        if (c3 == 'q' || c3 == 't' || c3 == 'Q' || c3 == 'T') {
                            f6 = (f31 + f31) - f10;
                            f7 = (f30 + f30) - f11;
                        } else {
                            f6 = f31;
                            f7 = f30;
                        }
                        int i17 = i2 + 1;
                        path.quadTo(f6, f7, fArr2[i2], fArr2[i17]);
                        f11 = f7;
                        f10 = f6;
                        f14 = fArr2[i2];
                        f15 = fArr2[i17];
                        break;
                    case 'V':
                        i2 = i3;
                        path.lineTo(f14, fArr2[i2]);
                        f15 = fArr2[i2];
                        break;
                    case 'a':
                        int i18 = i3 + 5;
                        int i19 = i3 + 6;
                        i2 = i3;
                        drawArc(path, f14, f15, fArr2[i18] + f14, fArr2[i19] + f15, fArr2[i3], fArr2[i3 + 1], fArr2[i3 + 2], fArr2[i3 + 3] != 0.0f, fArr2[i3 + 4] != 0.0f);
                        f14 += fArr2[i18];
                        f15 += fArr2[i19];
                        f11 = f15;
                        f10 = f14;
                        break;
                    case 'c':
                        int i20 = i3 + 2;
                        int i21 = i3 + 3;
                        int i22 = i3 + 4;
                        int i23 = i3 + 5;
                        path.rCubicTo(fArr2[i3], fArr2[i3 + 1], fArr2[i20], fArr2[i21], fArr2[i22], fArr2[i23]);
                        float f32 = fArr2[i20] + f14;
                        float f33 = fArr2[i21] + f15;
                        f14 += fArr2[i22];
                        f15 += fArr2[i23];
                        f10 = f32;
                        f11 = f33;
                        i2 = i3;
                        break;
                    case 'h':
                        path.rLineTo(fArr2[i3], 0.0f);
                        f14 += fArr2[i3];
                        i2 = i3;
                        break;
                    case 'l':
                        int i24 = i3 + 1;
                        path.rLineTo(fArr2[i3], fArr2[i24]);
                        f14 += fArr2[i3];
                        f15 += fArr2[i24];
                        i2 = i3;
                        break;
                    case 'm':
                        float f34 = fArr2[i3];
                        f14 += f34;
                        float f35 = fArr2[i3 + 1];
                        f15 += f35;
                        if (i3 > 0) {
                            path.rLineTo(f34, f35);
                            i2 = i3;
                            break;
                        } else {
                            path.rMoveTo(f34, f35);
                            i2 = i3;
                            f17 = f15;
                            f16 = f14;
                            break;
                        }
                    case 'q':
                        int i25 = i3 + 1;
                        int i26 = i3 + 2;
                        int i27 = i3 + 3;
                        path.rQuadTo(fArr2[i3], fArr2[i25], fArr2[i26], fArr2[i27]);
                        float f36 = fArr2[i3] + f14;
                        float f37 = fArr2[i25] + f15;
                        f14 += fArr2[i26];
                        f15 += fArr2[i27];
                        f10 = f36;
                        f11 = f37;
                        i2 = i3;
                        break;
                    case 's':
                        if (c3 == 'c' || c3 == 's' || c3 == 'C' || c3 == 'S') {
                            float f38 = f14 - f10;
                            f = f15 - f11;
                            f2 = f38;
                        } else {
                            f2 = 0.0f;
                            f = 0.0f;
                        }
                        int i28 = i3 + 1;
                        int i29 = i3 + 2;
                        int i30 = i3 + 3;
                        path.rCubicTo(f2, f, fArr2[i3], fArr2[i28], fArr2[i29], fArr2[i30]);
                        float f39 = fArr2[i3] + f14;
                        float f40 = fArr2[i28] + f15;
                        f14 += fArr2[i29];
                        f15 += fArr2[i30];
                        f10 = f39;
                        f11 = f40;
                        i2 = i3;
                        break;
                    case 't':
                        if (c3 == 'q' || c3 == 't' || c3 == 'Q' || c3 == 'T') {
                            f18 = f14 - f10;
                            f3 = f15 - f11;
                        } else {
                            f3 = 0.0f;
                        }
                        int i31 = i3 + 1;
                        path.rQuadTo(f18, f3, fArr2[i3], fArr2[i31]);
                        float f41 = f18 + f14;
                        float f42 = f3 + f15;
                        f14 += fArr2[i3];
                        f15 += fArr2[i31];
                        f11 = f42;
                        f10 = f41;
                        i2 = i3;
                        break;
                    case 'v':
                        path.rLineTo(0.0f, fArr2[i3]);
                        f15 += fArr2[i3];
                        i2 = i3;
                        break;
                    default:
                        i2 = i3;
                        break;
                }
                i3 = i2 + i;
                c3 = c2;
            }
            fArr[0] = f14;
            fArr[1] = f15;
            fArr[2] = f10;
            fArr[3] = f11;
            fArr[4] = f16;
            fArr[5] = f17;
        }

        PathDataNode(PathDataNode pathDataNode) {
            this.mType = pathDataNode.mType;
            float[] fArr = pathDataNode.mParams;
            this.mParams = PathParser.copyOfRange(fArr, 0, fArr.length);
        }
    }

    private static void addNode(ArrayList arrayList, char c, float[] fArr) {
        arrayList.add(new PathDataNode(c, fArr));
    }

    public static boolean canMorph(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr.length != pathDataNodeArr2.length) {
            return false;
        }
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            if (pathDataNodeArr[i].mType != pathDataNodeArr2[i].mType || pathDataNodeArr[i].mParams.length != pathDataNodeArr2[i].mParams.length) {
                return false;
            }
        }
        return true;
    }

    static float[] copyOfRange(float[] fArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (i >= 0 && i <= length) {
            int i3 = i2 - i;
            int min = Math.min(i3, length - i);
            float[] fArr2 = new float[i3];
            System.arraycopy(fArr, i, fArr2, 0, min);
            return fArr2;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public static PathDataNode[] createNodesFromPathData(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 0;
        while (i < str.length()) {
            int nextStart = nextStart(str, i);
            String trim = str.substring(i2, nextStart).trim();
            if (trim.length() > 0) {
                addNode(arrayList, trim.charAt(0), getFloats(trim));
            }
            i2 = nextStart;
            i = nextStart + 1;
        }
        if (i - i2 == 1 && i2 < str.length()) {
            addNode(arrayList, str.charAt(i2), new float[0]);
        }
        return (PathDataNode[]) arrayList.toArray(new PathDataNode[arrayList.size()]);
    }

    public static Path createPathFromPathData(String str) {
        Path path = new Path();
        PathDataNode[] createNodesFromPathData = createNodesFromPathData(str);
        if (createNodesFromPathData != null) {
            try {
                PathDataNode.nodesToPath(createNodesFromPathData, path);
                return path;
            } catch (RuntimeException e) {
                throw new RuntimeException("Error in parsing " + str, e);
            }
        }
        return null;
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] pathDataNodeArr) {
        if (pathDataNodeArr == null) {
            return null;
        }
        PathDataNode[] pathDataNodeArr2 = new PathDataNode[pathDataNodeArr.length];
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            pathDataNodeArr2[i] = new PathDataNode(pathDataNodeArr[i]);
        }
        return pathDataNodeArr2;
    }

    private static float[] getFloats(String str) {
        if (str.charAt(0) != 'z' && str.charAt(0) != 'Z') {
            try {
                float[] fArr = new float[str.length()];
                ExtractFloatResult extractFloatResult = new ExtractFloatResult();
                int length = str.length();
                int i = 1;
                int i2 = 0;
                while (i < length) {
                    extract(str, i, extractFloatResult);
                    int i3 = extractFloatResult.mEndPosition;
                    if (i < i3) {
                        fArr[i2] = Float.parseFloat(str.substring(i, i3));
                        i2++;
                    }
                    if (extractFloatResult.mEndWithNegOrDot) {
                        i = i3;
                    } else {
                        i = i3 + 1;
                    }
                }
                return copyOfRange(fArr, 0, i2);
            } catch (NumberFormatException e) {
                throw new RuntimeException("error in parsing \"" + str + "\"", e);
            }
        }
        return new float[0];
    }

    private static int nextStart(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (((charAt - 'A') * (charAt - 'Z') <= 0 || (charAt - 'a') * (charAt - 'z') <= 0) && charAt != 'e' && charAt != 'E') {
                return i;
            }
            i++;
        }
        return i;
    }

    public static void updateNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        for (int i = 0; i < pathDataNodeArr2.length; i++) {
            pathDataNodeArr[i].mType = pathDataNodeArr2[i].mType;
            for (int i2 = 0; i2 < pathDataNodeArr2[i].mParams.length; i2++) {
                pathDataNodeArr[i].mParams[i2] = pathDataNodeArr2[i].mParams[i2];
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void extract(String str, int i, ExtractFloatResult extractFloatResult) {
        extractFloatResult.mEndWithNegOrDot = false;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int i2 = i; i2 < str.length(); i2++) {
            switch (str.charAt(i2)) {
                case ' ':
                case ',':
                    z2 = false;
                    z3 = true;
                    break;
                case '-':
                    if (i2 != i && !z2) {
                        extractFloatResult.mEndWithNegOrDot = true;
                        z2 = false;
                        z3 = true;
                        break;
                    }
                    z2 = false;
                    break;
                case '.':
                    if (!z) {
                        z = true;
                        z2 = false;
                        break;
                    } else {
                        extractFloatResult.mEndWithNegOrDot = true;
                        z2 = false;
                        z3 = true;
                        break;
                    }
                case 'E':
                case 'e':
                    z2 = true;
                    break;
                default:
                    z2 = false;
                    break;
            }
            if (z3) {
                extractFloatResult.mEndPosition = i2;
            }
        }
        extractFloatResult.mEndPosition = i2;
    }
}
