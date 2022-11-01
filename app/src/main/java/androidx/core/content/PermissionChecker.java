package androidx.core.content;

import android.content.Context;
import android.os.Process;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.util.ObjectsCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PermissionChecker {
    public static final int PERMISSION_DENIED = -1;
    public static final int PERMISSION_DENIED_APP_OP = -2;
    public static final int PERMISSION_GRANTED = 0;

    public static int checkPermission(Context context, String str, int i, int i2, String str2) {
        int noteProxyOpNoThrow;
        if (context.checkPermission(str, i, i2) == -1) {
            return -1;
        }
        String permissionToOp = AppOpsManagerCompat.permissionToOp(str);
        if (permissionToOp == null) {
            return 0;
        }
        if (str2 == null) {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i2);
            if (packagesForUid == null || packagesForUid.length <= 0) {
                return -1;
            }
            str2 = packagesForUid[0];
        }
        if (Process.myUid() == i2 && ObjectsCompat.equals(context.getPackageName(), str2)) {
            noteProxyOpNoThrow = AppOpsManagerCompat.checkOrNoteProxyOp(context, i2, permissionToOp, str2);
        } else {
            noteProxyOpNoThrow = AppOpsManagerCompat.noteProxyOpNoThrow(context, permissionToOp, str2);
        }
        return noteProxyOpNoThrow == 0 ? 0 : -2;
    }

    public static int checkSelfPermission(Context context, String str) {
        return checkPermission(context, str, Process.myPid(), Process.myUid(), context.getPackageName());
    }
}
