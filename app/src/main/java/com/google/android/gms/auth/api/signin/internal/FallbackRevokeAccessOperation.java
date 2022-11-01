package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.libs.punchclock.network.HttpURLConnectionFactory;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FallbackRevokeAccessOperation implements Runnable {
    private static final Logger mLogger = new Logger("RevokeAccessOperation", new String[0]);
    private final StatusPendingResult mPendingResult = new StatusPendingResult(null);
    private final String mToken;

    public FallbackRevokeAccessOperation(String str) {
        this.mToken = Preconditions.checkNotEmpty(str);
    }

    public static PendingResult execute(String str) {
        if (str == null) {
            return PendingResults.immediateFailedResult(new Status(4), null);
        }
        FallbackRevokeAccessOperation fallbackRevokeAccessOperation = new FallbackRevokeAccessOperation(str);
        new Thread(fallbackRevokeAccessOperation).start();
        return fallbackRevokeAccessOperation.getPendingResult();
    }

    private PendingResult getPendingResult() {
        return this.mPendingResult;
    }

    @Override // java.lang.Runnable
    public void run() {
        Status status = Status.RESULT_INTERNAL_ERROR;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) HttpURLConnectionFactory.getInstance().openConnection(new URL("https://accounts.google.com/o/oauth2/revoke?token=" + this.mToken), "auth-api");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                mLogger.e("Unable to revoke access!", new Object[0]);
            } else {
                status = Status.RESULT_SUCCESS;
            }
            mLogger.d("Response Code: " + responseCode, new Object[0]);
        } catch (IOException e) {
            mLogger.e("IOException when revoking access: " + e.toString(), new Object[0]);
        } catch (Exception e2) {
            mLogger.e("Exception when revoking access: " + e2.toString(), new Object[0]);
        }
        this.mPendingResult.setResult(status);
    }
}
