package com.google.android.libraries.backup;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.SharedPreferences;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PersistentBackupAgentHelper extends BackupAgentHelper {
    private static final String BACKUP_DELIMITER = "/";
    private static final String BACKUP_KEY = "persistent_backup_agent_helper_prefs";
    protected static final String RESERVED_SHARED_PREFERENCES = "persistent_backup_agent_helper";
    private static final String TAG = "PersistentBackupAgentHe";

    private static String buildBackupKey(String str, String str2) {
        return str + BACKUP_DELIMITER + str2;
    }

    private void clearBackupFile() {
        getSharedPreferences(RESERVED_SHARED_PREFERENCES, 0).edit().clear().apply();
    }

    public static boolean isSupportedSharedPreferencesName(String str) {
        return (str.contains(File.separator) || str.contains(BACKUP_DELIMITER) || RESERVED_SHARED_PREFERENCES.equals(str)) ? false : true;
    }

    public static void putSharedPreference(SharedPreferences.Editor editor, String str, Object obj) {
        if (obj instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            editor.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Integer) {
            editor.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            editor.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof String) {
            editor.putString(str, (String) obj);
        } else {
            if (obj instanceof Set) {
                Set<String> set = (Set) obj;
                for (String str2 : set) {
                    if (!(str2 instanceof String)) {
                        Log.w(TAG, "Skipping restore of key " + str + " because its value is a set containing an object of type " + String.valueOf(obj != null ? obj.getClass() : null) + ".");
                        return;
                    }
                }
                editor.putStringSet(str, set);
                return;
            }
            Log.w(TAG, "Skipping restore of key " + str + " because its value is the unrecognized type " + String.valueOf(obj != null ? obj.getClass() : null) + ".");
        }
    }

    private void writeToBackupFile(String str, SharedPreferences.Editor editor, BackupKeyPredicate backupKeyPredicate) {
        if (!isSupportedSharedPreferencesName(str)) {
            throw new IllegalArgumentException("Unsupported shared preferences file name \"" + str + "\"");
        }
        for (Map.Entry<String, ?> entry : getAppSharedPreferences(str).getAll().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (backupKeyPredicate.shouldBeBackedUp(key)) {
                putSharedPreference(editor, buildBackupKey(str, key), value);
            }
        }
    }

    protected SharedPreferences getAppSharedPreferences(String str) {
        return getSharedPreferences(str, 0);
    }

    protected abstract Map getBackupSpecification();

    @Override // android.app.backup.BackupAgentHelper, android.app.backup.BackupAgent
    public void onBackup(ParcelFileDescriptor parcelFileDescriptor, BackupDataOutput backupDataOutput, ParcelFileDescriptor parcelFileDescriptor2) {
        writeFromPreferenceFilesToBackupFile();
        super.onBackup(parcelFileDescriptor, backupDataOutput, parcelFileDescriptor2);
        clearBackupFile();
    }

    @Override // android.app.backup.BackupAgent
    public void onCreate() {
        addHelper(BACKUP_KEY, new SharedPreferencesBackupHelper(this, RESERVED_SHARED_PREFERENCES));
    }

    protected void onPreferencesRestored(Set set, int i) {
    }

    @Override // android.app.backup.BackupAgentHelper, android.app.backup.BackupAgent
    public void onRestore(BackupDataInput backupDataInput, int i, ParcelFileDescriptor parcelFileDescriptor) {
        super.onRestore(backupDataInput, i, parcelFileDescriptor);
        writeFromBackupFileToPreferenceFiles(i);
        clearBackupFile();
    }

    void writeFromPreferenceFilesToBackupFile() {
        Map backupSpecification = getBackupSpecification();
        SharedPreferences.Editor edit = getSharedPreferences(RESERVED_SHARED_PREFERENCES, 0).edit();
        edit.clear();
        for (Map.Entry entry : backupSpecification.entrySet()) {
            writeToBackupFile((String) entry.getKey(), edit, (BackupKeyPredicate) entry.getValue());
        }
        edit.apply();
    }

    void writeFromBackupFileToPreferenceFiles(int i) {
        SharedPreferences sharedPreferences = getSharedPreferences(RESERVED_SHARED_PREFERENCES, 0);
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            int indexOf = key.indexOf(BACKUP_DELIMITER);
            if (indexOf < 0 || indexOf >= key.length() - 1) {
                Log.w(TAG, "Format of key \"" + key + "\" not understood, so skipping its restore.");
            } else {
                String substring = key.substring(0, indexOf);
                String substring2 = key.substring(indexOf + 1);
                SharedPreferences.Editor editor = (SharedPreferences.Editor) hashMap.get(substring);
                if (editor == null) {
                    if (!isSupportedSharedPreferencesName(substring)) {
                        Log.w(TAG, "Skipping unsupported shared preferences file name \"" + substring + "\"");
                    } else {
                        editor = getAppSharedPreferences(substring).edit();
                        hashMap.put(substring, editor);
                    }
                }
                putSharedPreference(editor, substring2, value);
            }
        }
        for (SharedPreferences.Editor editor2 : hashMap.values()) {
            editor2.apply();
        }
        onPreferencesRestored(hashMap.keySet(), i);
    }
}
