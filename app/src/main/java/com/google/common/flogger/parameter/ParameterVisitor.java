package com.google.common.flogger.parameter;

import com.google.common.flogger.backend.FormatChar;
import com.google.common.flogger.backend.FormatOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ParameterVisitor {
    void visit(Object obj, FormatChar formatChar, FormatOptions formatOptions);

    void visitDateTime(Object obj, DateTimeFormat dateTimeFormat, FormatOptions formatOptions);

    void visitMissing();

    void visitNull();
}
