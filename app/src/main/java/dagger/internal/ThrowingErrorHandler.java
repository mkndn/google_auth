package dagger.internal;

import dagger.internal.Linker;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ThrowingErrorHandler implements Linker.ErrorHandler {
    @Override // dagger.internal.Linker.ErrorHandler
    public void handleErrors(List list) {
        if (list.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Errors creating object graph:");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            sb.append("\n  ").append((String) it.next());
        }
        throw new IllegalStateException(sb.toString());
    }
}
