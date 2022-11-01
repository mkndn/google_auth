package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BaseDateTime;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DateTime extends BaseDateTime implements ReadableInstant, Serializable {
    private static final long serialVersionUID = -5171125899451703815L;

    public DateTime() {
    }

    public DateTime(long j, Chronology chronology) {
        super(j, chronology);
    }
}
