package org.joda.time.tz;

import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface NameProvider {
    String getName(Locale locale, String str, String str2);

    String getShortName(Locale locale, String str, String str2);
}
