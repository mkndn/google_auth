package com.google.common.flogger.parameter;

import com.google.common.flogger.backend.FormatChar;
import com.google.common.flogger.backend.FormatOptions;
import com.google.common.flogger.util.Checks;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SimpleParameter extends Parameter {
    private static final Map DEFAULT_PARAMETERS;
    private final FormatChar formatChar;
    private final String formatString;

    static {
        FormatChar[] values;
        EnumMap enumMap = new EnumMap(FormatChar.class);
        for (FormatChar formatChar : FormatChar.values()) {
            enumMap.put((EnumMap) formatChar, (FormatChar) createParameterArray(formatChar));
        }
        DEFAULT_PARAMETERS = Collections.unmodifiableMap(enumMap);
    }

    private SimpleParameter(int i, FormatChar formatChar, FormatOptions formatOptions) {
        super(formatOptions, i);
        String buildFormatString;
        this.formatChar = (FormatChar) Checks.checkNotNull(formatChar, "format char");
        if (formatOptions.isDefault()) {
            buildFormatString = formatChar.getDefaultFormatString();
        } else {
            buildFormatString = buildFormatString(formatOptions, formatChar);
        }
        this.formatString = buildFormatString;
    }

    static String buildFormatString(FormatOptions formatOptions, FormatChar formatChar) {
        int i = formatChar.getChar();
        if (formatOptions.shouldUpperCase()) {
            i &= 65503;
        }
        return formatOptions.appendPrintfOptions(new StringBuilder("%")).append((char) i).toString();
    }

    private static SimpleParameter[] createParameterArray(FormatChar formatChar) {
        SimpleParameter[] simpleParameterArr = new SimpleParameter[10];
        for (int i = 0; i < 10; i++) {
            simpleParameterArr[i] = new SimpleParameter(i, formatChar, FormatOptions.getDefault());
        }
        return simpleParameterArr;
    }

    public static SimpleParameter of(int i, FormatChar formatChar, FormatOptions formatOptions) {
        if (i < 10 && formatOptions.isDefault()) {
            return ((SimpleParameter[]) DEFAULT_PARAMETERS.get(formatChar))[i];
        }
        return new SimpleParameter(i, formatChar, formatOptions);
    }

    @Override // com.google.common.flogger.parameter.Parameter
    protected void accept(ParameterVisitor parameterVisitor, Object obj) {
        parameterVisitor.visit(obj, this.formatChar, getFormatOptions());
    }
}
