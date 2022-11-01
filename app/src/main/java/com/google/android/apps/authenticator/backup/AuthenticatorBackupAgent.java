package com.google.android.apps.authenticator.backup;

import android.app.backup.BackupManager;
import android.app.backup.FullBackupDataOutput;
import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.google.android.libraries.backup.Backup;
import com.google.android.libraries.backup.BackupKeyPredicates;
import com.google.android.libraries.backup.PersistentBackupAgentHelper;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AuthenticatorBackupAgent extends PersistentBackupAgentHelper {
    private static final String DEFAULT_PREFERENCES_SUFFIX = "_preferences";

    private static String getSharedPreferencesFileName(Context context) {
        return context.getPackageName() + DEFAULT_PREFERENCES_SUFFIX;
    }

    public static void scheduleBackup(Context context) {
        BackupManager.dataChanged(context.getPackageName());
    }

    @Override // com.google.android.libraries.backup.PersistentBackupAgentHelper
    protected Map getBackupSpecification() {
        HashMap hashMap = new HashMap();
        hashMap.put(getSharedPreferencesFileName(this), BackupKeyPredicates.buildPredicateFromAnnotatedFieldsIn(Backup.class, BackupKeys.class));
        return hashMap;
    }

    @Override // android.app.backup.BackupAgent
    public void onFullBackup(FullBackupDataOutput fullBackupDataOutput) {
    }

    @Override // android.app.backup.BackupAgent
    public void onRestoreFile(ParcelFileDescriptor parcelFileDescriptor, long j, File file, int i, long j2, long j3) {
    }
}
