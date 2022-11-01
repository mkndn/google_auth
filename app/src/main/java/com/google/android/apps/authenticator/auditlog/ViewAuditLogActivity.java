package com.google.android.apps.authenticator.auditlog;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.apps.authenticator.auditlog.AuditLogEvent;
import com.google.android.apps.authenticator.testability.DependencyInjector;
import com.google.android.apps.authenticator.testability.TestableActivity;
import com.google.android.apps.authenticator2.R;
import java.util.List;
import org.joda.time.format.DateTimeFormat;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ViewAuditLogActivity extends TestableActivity {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class AuditLogListAdapter extends ArrayAdapter {
        private final List events;

        public AuditLogListAdapter(List list) {
            super(ViewAuditLogActivity.this, R.layout.audit_log_list_item, list);
            this.events = list;
        }

        private CharSequence getDescriptionTextFromEvent(AuditLogEvent auditLogEvent) {
            return (CharSequence) auditLogEvent.accept(new AuditLogEvent.Visitor() { // from class: com.google.android.apps.authenticator.auditlog.ViewAuditLogActivity.AuditLogListAdapter.1
                @Override // com.google.android.apps.authenticator.auditlog.AuditLogEvent.Visitor
                public CharSequence visit(AddAccountEvent addAccountEvent) {
                    return ViewAuditLogActivity.this.getString(R.string.audit_log_account_added, new Object[]{addAccountEvent.accountIndex().toString()});
                }

                @Override // com.google.android.apps.authenticator.auditlog.AuditLogEvent.Visitor
                public CharSequence visit(ExportEvent exportEvent) {
                    return ViewAuditLogActivity.this.getResources().getQuantityString(R.plurals.audit_log_accounts_exported, exportEvent.amount(), Integer.valueOf(exportEvent.amount()));
                }

                @Override // com.google.android.apps.authenticator.auditlog.AuditLogEvent.Visitor
                public CharSequence visit(ImportEvent importEvent) {
                    return ViewAuditLogActivity.this.getResources().getQuantityString(R.plurals.audit_log_accounts_imported, importEvent.amount(), Integer.valueOf(importEvent.amount()));
                }
            });
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            int i2 = 0;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.audit_log_list_item, viewGroup, false);
            }
            AuditLogEvent auditLogEvent = (AuditLogEvent) this.events.get(i);
            TextView textView = (TextView) view.findViewById(R.id.audit_log_event_timestamp);
            ((TextView) view.findViewById(R.id.audit_log_event_description)).setText(getDescriptionTextFromEvent(auditLogEvent));
            textView.setText(DateTimeFormat.forPattern("yyyy-MM-dd hh:mm:ss a").print(auditLogEvent.timestamp().toDateTime()));
            long currentTimeMillis = System.currentTimeMillis() - auditLogEvent.timestamp().getMillis();
            textView.setVisibility((currentTimeMillis < 0 || currentTimeMillis >= 5184000000L) ? 4 : 4);
            return view;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(8192, 8192);
        if (getResources().getBoolean(R.bool.isTablet)) {
            setRequestedOrientation(2);
        } else {
            setRequestedOrientation(12);
        }
        setContentView(R.layout.view_audit_log_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.audit_log_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView) findViewById(R.id.audit_log_overview)).setMovementMethod(LinkMovementMethod.getInstance());
        updateAuditLogDisplay(DependencyInjector.getAccountDb().auditLog.getAllEvents());
    }

    void updateAuditLogDisplay(List list) {
        ListView listView = (ListView) findViewById(R.id.audit_log_events_list);
        listView.setAdapter((ListAdapter) new AuditLogListAdapter(list));
        listView.setVisibility(list.isEmpty() ? 8 : 0);
        ((TextView) findViewById(R.id.no_recent_events_notice)).setVisibility(list.isEmpty() ? 0 : 8);
    }
}
