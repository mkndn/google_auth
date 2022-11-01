package com.google.android.apps.authenticator.api;

import android.content.Intent;
import android.util.Log;
import com.google.android.apps.authenticator.util.IntentUtils;
import com.google.android.gms.fido.u2f.api.messagebased.ParseException;
import com.google.android.gms.fido.u2f.api.messagebased.RequestMessage;
import com.google.android.gms.fido.u2f.api.messagebased.RequestMessageParser;

/* compiled from: PG */
/* loaded from: classes.dex */
public class IntentExtraParser {
    private static final String EXTRA_REQUEST = "request";
    private static final String TAG = IntentExtraParser.class.getSimpleName();

    public RequestMessage readRequestFromIntent(Intent intent) {
        try {
            String mandatoryExtraString = IntentUtils.getMandatoryExtraString(intent, EXTRA_REQUEST);
            Log.d(TAG, "Interpreting request " + mandatoryExtraString);
            return RequestMessageParser.parseRequestJsonString(mandatoryExtraString);
        } catch (IntentUtils.IntentParsingException e) {
            throw new ParseException(null, null, "Missing required request value in intent");
        }
    }
}
