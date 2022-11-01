package androidx.core.widget;

import android.os.Build;
import android.view.View;
import android.widget.ListView;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ListViewCompat {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class Api19Impl {
        static boolean canScrollList(ListView listView, int i) {
            return listView.canScrollList(i);
        }

        static void scrollListBy(ListView listView, int i) {
            listView.scrollListBy(i);
        }
    }

    public static void scrollListBy(ListView listView, int i) {
        View childAt;
        if (Build.VERSION.SDK_INT >= 19) {
            Api19Impl.scrollListBy(listView, i);
            return;
        }
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        if (firstVisiblePosition == -1 || (childAt = listView.getChildAt(0)) == null) {
            return;
        }
        listView.setSelectionFromTop(firstVisiblePosition, childAt.getTop() - i);
    }
}
