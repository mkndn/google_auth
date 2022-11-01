package com.google.common.base;

import java.io.IOException;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Joiner {
    private final String separator;

    private Joiner(String str) {
        this.separator = (String) Preconditions.checkNotNull(str);
    }

    public static Joiner on(String str) {
        return new Joiner(str);
    }

    public Appendable appendTo(Appendable appendable, Iterator it) {
        Preconditions.checkNotNull(appendable);
        if (it.hasNext()) {
            appendable.append(toString(it.next()));
            while (it.hasNext()) {
                appendable.append(this.separator);
                appendable.append(toString(it.next()));
            }
        }
        return appendable;
    }

    public final String join(Iterable iterable) {
        return join(iterable.iterator());
    }

    CharSequence toString(Object obj) {
        obj.getClass();
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    public final String join(Iterator it) {
        return appendTo(new StringBuilder(), it).toString();
    }

    public final StringBuilder appendTo(StringBuilder sb, Iterable iterable) {
        return appendTo(sb, iterable.iterator());
    }

    public final StringBuilder appendTo(StringBuilder sb, Iterator it) {
        try {
            appendTo((Appendable) sb, it);
            return sb;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
