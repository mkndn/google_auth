package com.google.common.flogger.parser;

import com.google.common.flogger.backend.FormatChar;
import com.google.common.flogger.backend.FormatOptions;
import com.google.common.flogger.parameter.DateTimeFormat;
import com.google.common.flogger.parameter.DateTimeParameter;
import com.google.common.flogger.parameter.Parameter;
import com.google.common.flogger.parameter.ParameterVisitor;
import com.google.common.flogger.parameter.SimpleParameter;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DefaultPrintfMessageParser extends PrintfMessageParser {
    private static final PrintfMessageParser INSTANCE = new DefaultPrintfMessageParser();

    private DefaultPrintfMessageParser() {
    }

    public static PrintfMessageParser getInstance() {
        return INSTANCE;
    }

    private static Parameter wrapHexParameter(final FormatOptions formatOptions, int i) {
        return new Parameter(formatOptions, i) { // from class: com.google.common.flogger.parser.DefaultPrintfMessageParser.1
            @Override // com.google.common.flogger.parameter.Parameter
            protected void accept(ParameterVisitor parameterVisitor, Object obj) {
                parameterVisitor.visit(Integer.valueOf(obj.hashCode()), FormatChar.HEX, getFormatOptions());
            }
        };
    }

    @Override // com.google.common.flogger.parser.PrintfMessageParser
    public int parsePrintfTerm(MessageBuilder messageBuilder, int i, String str, int i2, int i3, int i4) {
        Parameter of;
        int i5 = i4 + 1;
        char charAt = str.charAt(i4);
        FormatOptions parse = FormatOptions.parse(str, i3, i4, (charAt & ' ') == 0);
        FormatChar of2 = FormatChar.of(charAt);
        if (of2 != null) {
            if (!parse.areValidFor(of2)) {
                throw ParseException.withBounds("invalid format specifier", str, i2, i5);
            }
            of = SimpleParameter.of(i, of2, parse);
        } else if (charAt != 't' && charAt != 'T') {
            if (charAt != 'h' && charAt != 'H') {
                throw ParseException.withBounds("invalid format specification", str, i2, i5);
            }
            if (!parse.validate(AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_REFINING_EXPERIMENTS_VALUE, false)) {
                throw ParseException.withBounds("invalid format specification", str, i2, i5);
            }
            of = wrapHexParameter(parse, i);
        } else if (!parse.validate(AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_REFINING_EXPERIMENTS_VALUE, false)) {
            throw ParseException.withBounds("invalid format specification", str, i2, i5);
        } else {
            int i6 = i5 + 1;
            if (i6 > str.length()) {
                throw ParseException.atPosition("truncated format specifier", str, i2);
            }
            DateTimeFormat of3 = DateTimeFormat.of(str.charAt(i5));
            if (of3 == null) {
                throw ParseException.atPosition("illegal date/time conversion", str, i5);
            }
            of = DateTimeParameter.of(of3, parse, i);
            i5 = i6;
        }
        messageBuilder.addParameter(i2, i5, of);
        return i5;
    }
}
