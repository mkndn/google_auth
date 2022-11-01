package com.google.android.apps.authenticator.migration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.android.apps.authenticator.auditlog.ExportEvent;
import com.google.android.apps.authenticator.migration.MigrationExportActivity;
import com.google.android.apps.authenticator.migration.OfflineMigration;
import com.google.android.apps.authenticator.migration.OfflineMigrationEnums;
import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.android.apps.authenticator.testability.DependencyInjector;
import com.google.android.apps.authenticator.testability.TestableActivity;
import com.google.android.apps.authenticator.util.Base32String;
import com.google.android.apps.authenticator2.R;
import com.google.protobuf.ByteString;
import com.google.protobuf.contrib.android.ProtoParsers;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MigrationExportActivity extends TestableActivity {
    private static final String LOCAL_TAG = "GAuthenticator.MigrationExportActivity";
    private static final int MAX_CAPACITY = 10;
    private Button exportButton;
    private final List allAccounts = new ArrayList();
    private final Set selectedAccounts = new HashSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class AccountIndexListAdapter extends ArrayAdapter {
        public AccountIndexListAdapter(List list) {
            super(MigrationExportActivity.this, R.layout.migration_export_list_item, list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.migration_export_list_item, viewGroup, false);
            }
            final AccountDb.AccountIndex accountIndex = (AccountDb.AccountIndex) getItem(i);
            final CheckBox checkBox = (CheckBox) view.findViewById(R.id.migration_export_list_item_account_selected);
            checkBox.setChecked(MigrationExportActivity.this.selectedAccounts.contains(accountIndex));
            checkBox.setText(accountIndex.toString());
            checkBox.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.apps.authenticator.migration.MigrationExportActivity$AccountIndexListAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    MigrationExportActivity.AccountIndexListAdapter.this.m75x56324e31(checkBox, accountIndex, view2);
                }
            });
            return view;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getView$0$com-google-android-apps-authenticator-migration-MigrationExportActivity$AccountIndexListAdapter  reason: not valid java name */
        public /* synthetic */ void m75x56324e31(CheckBox checkBox, AccountDb.AccountIndex accountIndex, View view) {
            if (checkBox.isChecked()) {
                MigrationExportActivity.this.selectedAccounts.add(accountIndex);
            } else {
                MigrationExportActivity.this.selectedAccounts.remove(accountIndex);
            }
            MigrationExportActivity.this.updateButtonState();
        }
    }

    private static OfflineMigration.OtpParameters convert(AccountDb.AccountIndex accountIndex) {
        OfflineMigration.OtpParameters.Builder newBuilder = OfflineMigration.OtpParameters.newBuilder();
        if (accountIndex.getIssuer() != null) {
            newBuilder.setIssuer(accountIndex.getIssuer());
        }
        if (accountIndex.getName() != null) {
            newBuilder.setName(accountIndex.getName());
        }
        AccountDb accountDb = DependencyInjector.getAccountDb();
        newBuilder.setSecret(ByteString.copyFrom(Base32String.decode(accountDb.getSecret(accountIndex))));
        newBuilder.setAlgorithm(OfflineMigrationEnums.Algorithm.SHA1);
        newBuilder.setDigits(OfflineMigrationEnums.DigitCount.SIX);
        if (accountDb.getType(accountIndex) == AccountDb.OtpType.HOTP) {
            newBuilder.setType(OfflineMigrationEnums.OtpType.HOTP);
            newBuilder.setCounter(accountDb.getCounter(accountIndex).intValue());
        } else {
            newBuilder.setType(OfflineMigrationEnums.OtpType.TOTP);
        }
        return (OfflineMigration.OtpParameters) newBuilder.build();
    }

    private ArrayList serializeAccounts(Set set) {
        ArrayList arrayList = new ArrayList();
        OfflineMigration.MigrationPayload.Builder newBuilder = OfflineMigration.MigrationPayload.newBuilder();
        arrayList.add(newBuilder);
        ArrayList<OfflineMigration.OtpParameters> arrayList2 = new ArrayList();
        for (AccountDb.AccountIndex accountIndex : this.allAccounts) {
            if (set.contains(accountIndex)) {
                arrayList2.add(convert(accountIndex));
            }
        }
        for (OfflineMigration.OtpParameters otpParameters : arrayList2) {
            if (newBuilder.getOtpParametersCount() >= 10) {
                newBuilder = OfflineMigration.MigrationPayload.newBuilder();
                arrayList.add(newBuilder);
            }
            newBuilder.addOtpParameters(otpParameters);
        }
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList3.add((OfflineMigration.MigrationPayload) ((OfflineMigration.MigrationPayload.Builder) arrayList.get(i)).setVersion(1).setBatchIndex(i).setBatchSize(arrayList.size()).setBatchId(arrayList2.hashCode()).build());
        }
        return arrayList3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateButtonState() {
        boolean z = !this.selectedAccounts.isEmpty();
        this.exportButton.setEnabled(z);
        this.exportButton.setAlpha(z ? 1.0f : 0.4f);
    }

    public void buttonExportClicked(View view) {
        DependencyInjector.getAccountDb().auditLog.addEvent(ExportEvent.builder().setAmount(this.selectedAccounts.size()).build());
        try {
            ArrayList serializeAccounts = serializeAccounts(this.selectedAccounts);
            Intent intent = new Intent(this, MigrationExportBarcodeActivity.class);
            ProtoParsers.put(intent, MigrationExportBarcodeActivity.EXTRA_DATA, serializeAccounts);
            startActivity(intent);
        } catch (Base32String.DecodingException e) {
            Log.e(LOCAL_TAG, e.getMessage());
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
        setContentView(R.layout.migration_export_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.migration_export_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.exportButton = (Button) findViewById(R.id.migration_export_button);
        List accounts = DependencyInjector.getAccountDb().getAccounts();
        this.allAccounts.addAll(accounts);
        this.selectedAccounts.addAll(this.allAccounts);
        updateButtonState();
        ((ListView) findViewById(R.id.migration_export_list)).setAdapter((ListAdapter) new AccountIndexListAdapter(accounts));
    }
}
