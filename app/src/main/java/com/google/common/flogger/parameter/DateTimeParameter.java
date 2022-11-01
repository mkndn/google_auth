package com.google.common.flogger.parameter;

import com.google.common.flogger.backend.FormatOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DateTimeParameter extends Parameter {
    private final DateTimeFormat format;
    private final String formatString;

    private DateTimeParameter(FormatOptions formatOptions, int i, DateTimeFormat dateTimeFormat) {
        super(formatOptions, i);
        this.format = dateTimeFormat;
        this.formatString = formatOptions.appendPrintfOptions(new StringBuilder("%")).append(formatOptions.shouldUpperCase() ? 'T' : 't').append(dateTimeFormat.getChar()).toString();
    }

    public static Parameter of(DateTimeFormat dateTimeFormat, FormatOptions formatOptions, int i) {
        return new DateTimeParameter(formatOptions, i, dateTimeFormat);
    }

    @Override // com.google.common.flogger.parameter.Parameter
    protected void accept(ParameterVisitor parameterVisitor, Object obj) {
        parameterVisitor.visitDateTime(obj, this.format, getFormatOptions());
    }
}
