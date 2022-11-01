package org.joda.time.tz;

import java.util.Set;
import org.joda.time.DateTimeZone;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Provider {
    Set getAvailableIDs();

    DateTimeZone getZone(String str);
}
