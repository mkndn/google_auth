package androidx.core.os;

import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
interface LocaleListInterface {
    Locale get(int i);

    Object getLocaleList();

    boolean isEmpty();

    int size();

    String toLanguageTags();
}
