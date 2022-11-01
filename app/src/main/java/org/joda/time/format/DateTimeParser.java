package org.joda.time.format;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface DateTimeParser {
    int estimateParsedLength();

    int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i);
}
