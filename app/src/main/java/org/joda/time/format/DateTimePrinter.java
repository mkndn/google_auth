package org.joda.time.format;

import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface DateTimePrinter {
    int estimatePrintedLength();

    void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale);
}
