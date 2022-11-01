package com.google.android.apps.authenticator.testability.android.widget;

import android.widget.AdapterView;
import android.widget.ListAdapter;
import com.google.android.apps.authenticator.testability.android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ListView extends View {
    private final android.widget.ListView listView;

    public ListView(android.widget.ListView listView) {
        super(listView);
        this.listView = listView;
    }

    public ListAdapter getAdapter() {
        return this.listView.getAdapter();
    }

    public void setAdapter(ListAdapter listAdapter) {
        this.listView.setAdapter(listAdapter);
    }

    @Override // com.google.android.apps.authenticator.testability.android.view.View
    public void setEnabled(boolean z) {
        this.listView.setEnabled(z);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.listView.setOnItemClickListener(onItemClickListener);
    }
}
