package com.google.android.gms.googlehelp.internal.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.feedback.BaseFeedbackProductSpecificData;
import com.google.android.gms.feedback.FeedbackOptions;
import com.google.android.gms.googlehelp.BaseHelpProductSpecificData;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.GoogleHelpAccessor;
import com.google.android.gms.googlehelp.GoogleHelpApi;
import com.google.android.gms.googlehelp.GoogleHelpTogglingRegister;
import com.google.android.gms.googlehelp.Help;
import com.google.android.gms.googlehelp.InProductHelp;
import com.google.android.gms.googlehelp.internal.common.GetSyncHelpPsdRunnable;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import java.lang.ref.WeakReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleHelpApiImpl implements GoogleHelpApi {
    public static final int HELP_RESULT_CODE = 123;
    private static final Status RESULT_FAILURE = new Status(13);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class BaseGoogleHelpApiMethodImpl extends BaseImplementation$ApiMethodImpl {
        public BaseGoogleHelpApiMethodImpl(GoogleApiClient googleApiClient) {
            super(Help.API, googleApiClient);
        }

        protected abstract void doExecute(Context context, IGoogleHelpService iGoogleHelpService);

        @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
        public final void doExecute(GoogleHelpClientImpl googleHelpClientImpl) {
            doExecute(googleHelpClientImpl.getContext(), (IGoogleHelpService) googleHelpClientImpl.getService());
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class GoogleHelpImpl extends BaseGoogleHelpApiMethodImpl {
        public GoogleHelpImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public Status createFailedResult(Status status) {
            if (status == null) {
                return Status.RESULT_INTERNAL_ERROR;
            }
            return status;
        }
    }

    public SimpleGoogleHelpCallbacks getOnGoogleHelpProcessedCallback(final GoogleHelpImpl googleHelpImpl, final WeakReference weakReference, final Intent intent, final BaseHelpProductSpecificData baseHelpProductSpecificData, final BaseFeedbackProductSpecificData baseFeedbackProductSpecificData) {
        return new SimpleGoogleHelpCallbacks() { // from class: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.2
            {
                GoogleHelpApiImpl.this = this;
            }

            @Override // com.google.android.gms.googlehelp.internal.common.SimpleGoogleHelpCallbacks, com.google.android.gms.googlehelp.internal.common.IGoogleHelpCallbacks
            public void onGoogleHelpProcessed(GoogleHelp googleHelp) {
                long nanoTime = System.nanoTime();
                intent.putExtra("EXTRA_START_TICK", nanoTime);
                Activity activity = (Activity) weakReference.get();
                if (activity == null) {
                    googleHelpImpl.forceFailureUnlessReady(GoogleHelpApiImpl.RESULT_FAILURE);
                    return;
                }
                if (baseHelpProductSpecificData != null || baseFeedbackProductSpecificData != null) {
                    new PsdCollector(googleHelp).getAsyncPsds(activity.getApplicationContext(), baseFeedbackProductSpecificData, baseHelpProductSpecificData, nanoTime);
                }
                GoogleHelpAccessor googleHelpAccessor = new GoogleHelpAccessor(googleHelp);
                googleHelpAccessor.setClientVersion(GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
                if (googleHelpAccessor.getTogglingData() != null) {
                    googleHelpAccessor.getTogglingData().setPipTitle(GoogleHelpApiImpl.this.getActionBarTitle(activity));
                }
                GoogleHelpApiImpl.this.startHelpActivity(googleHelpImpl, activity, intent, googleHelp);
            }
        };
    }

    public static /* synthetic */ void lambda$startHelpActivity$0(Activity activity, Intent intent) {
        activity.startActivityForResult(intent, 123);
    }

    String getActionBarTitle(Activity activity) {
        return GoogleHelpUtils.getActionBarTitle(activity);
    }

    @Override // com.google.android.gms.googlehelp.GoogleHelpApi
    public PendingResult saveAsyncFeedbackPsbd(GoogleApiClient googleApiClient, final GoogleHelp googleHelp, final FeedbackOptions feedbackOptions, final Bundle bundle, final long j) {
        return googleApiClient.enqueue(new GoogleHelpImpl(this, googleApiClient) { // from class: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.11
            @Override // com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.BaseGoogleHelpApiMethodImpl
            protected void doExecute(Context context, IGoogleHelpService iGoogleHelpService) {
                try {
                    iGoogleHelpService.saveAsyncFeedbackPsbd(feedbackOptions, bundle, j, googleHelp, new SimpleGoogleHelpCallbacks() { // from class: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.11.1
                        {
                            AnonymousClass11.this = this;
                        }

                        @Override // com.google.android.gms.googlehelp.internal.common.SimpleGoogleHelpCallbacks, com.google.android.gms.googlehelp.internal.common.IGoogleHelpCallbacks
                        public void onAsyncPsbdSaved() {
                            setResult(Status.RESULT_SUCCESS);
                        }
                    });
                } catch (Exception e) {
                    Log.e("gH_GoogleHelpApiImpl", "Requesting to save the async feedback psbd failed!", e);
                    forceFailureUnlessReady(GoogleHelpApiImpl.RESULT_FAILURE);
                }
            }
        });
    }

    @Override // com.google.android.gms.googlehelp.GoogleHelpApi
    public PendingResult saveAsyncFeedbackPsd(GoogleApiClient googleApiClient, final GoogleHelp googleHelp, final Bundle bundle, final long j) {
        return googleApiClient.enqueue(new GoogleHelpImpl(this, googleApiClient) { // from class: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.10
            @Override // com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.BaseGoogleHelpApiMethodImpl
            protected void doExecute(Context context, IGoogleHelpService iGoogleHelpService) {
                try {
                    iGoogleHelpService.saveAsyncFeedbackPsd(bundle, j, googleHelp, new SimpleGoogleHelpCallbacks() { // from class: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.10.1
                        {
                            AnonymousClass10.this = this;
                        }

                        @Override // com.google.android.gms.googlehelp.internal.common.SimpleGoogleHelpCallbacks, com.google.android.gms.googlehelp.internal.common.IGoogleHelpCallbacks
                        public void onAsyncPsdSaved() {
                            setResult(Status.RESULT_SUCCESS);
                        }
                    });
                } catch (Exception e) {
                    Log.e("gH_GoogleHelpApiImpl", "Requesting to save the async feedback psd failed!", e);
                    forceFailureUnlessReady(GoogleHelpApiImpl.RESULT_FAILURE);
                }
            }
        });
    }

    @Override // com.google.android.gms.googlehelp.GoogleHelpApi
    public PendingResult saveAsyncHelpPsd(GoogleApiClient googleApiClient, final GoogleHelp googleHelp, final Bundle bundle, final long j) {
        return googleApiClient.enqueue(new GoogleHelpImpl(this, googleApiClient) { // from class: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.9
            @Override // com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.BaseGoogleHelpApiMethodImpl
            protected void doExecute(Context context, IGoogleHelpService iGoogleHelpService) {
                try {
                    iGoogleHelpService.saveAsyncHelpPsd(bundle, j, googleHelp, new SimpleGoogleHelpCallbacks() { // from class: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.9.1
                        {
                            AnonymousClass9.this = this;
                        }

                        @Override // com.google.android.gms.googlehelp.internal.common.SimpleGoogleHelpCallbacks, com.google.android.gms.googlehelp.internal.common.IGoogleHelpCallbacks
                        public void onAsyncPsdSaved() {
                            setResult(Status.RESULT_SUCCESS);
                        }
                    });
                } catch (Exception e) {
                    Log.e("gH_GoogleHelpApiImpl", "Requesting to save the async help psd failed!", e);
                    forceFailureUnlessReady(GoogleHelpApiImpl.RESULT_FAILURE);
                }
            }
        });
    }

    void setResult(GoogleHelpImpl googleHelpImpl, Status status) {
        googleHelpImpl.setResult(status);
    }

    @Override // com.google.android.gms.googlehelp.GoogleHelpApi
    public PendingResult startHelp(GoogleApiClient googleApiClient, Activity activity, final Intent intent) {
        final Bitmap bitmap;
        if (GoogleHelpTogglingRegister.isTogglingEnabled()) {
            bitmap = GoogleHelpUtils.getContentScreenshotOnUiThread(activity);
        } else {
            bitmap = null;
        }
        final WeakReference weakReference = new WeakReference(activity);
        return googleApiClient.enqueue(new GoogleHelpImpl(googleApiClient) { // from class: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.1
            {
                GoogleHelpApiImpl.this = this;
            }

            @Override // com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.BaseGoogleHelpApiMethodImpl
            protected void doExecute(Context context, final IGoogleHelpService iGoogleHelpService) {
                GoogleHelp googleHelp = (GoogleHelp) intent.getParcelableExtra("EXTRA_GOOGLE_HELP");
                GoogleHelpAccessor googleHelpAccessor = new GoogleHelpAccessor(googleHelp);
                final BaseHelpProductSpecificData helpPsd = googleHelpAccessor.getHelpPsd();
                final BaseFeedbackProductSpecificData feedbackPsd = googleHelpAccessor.getFeedbackPsd();
                new PsdCollector(googleHelp).getSyncHelpPsd(helpPsd, new GetSyncHelpPsdRunnable.SyncHelpPsdCallback() { // from class: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.1.1
                    {
                        AnonymousClass1.this = this;
                    }

                    @Override // com.google.android.gms.googlehelp.internal.common.GetSyncHelpPsdRunnable.SyncHelpPsdCallback
                    public void onSyncHelpPsdCollected(GoogleHelp googleHelp2) {
                        try {
                            iGoogleHelpService.processGoogleHelpAndPip(googleHelp2, bitmap, GoogleHelpApiImpl.this.getOnGoogleHelpProcessedCallback(this, weakReference, intent, helpPsd, feedbackPsd));
                        } catch (RemoteException e) {
                            Log.e("gH_GoogleHelpApiImpl", "Starting help failed!", e);
                            forceFailureUnlessReady(GoogleHelpApiImpl.RESULT_FAILURE);
                        }
                    }
                });
            }
        });
    }

    void startHelpActivity(GoogleHelpImpl googleHelpImpl, final Activity activity, final Intent intent, GoogleHelp googleHelp) {
        if (intent.hasExtra("EXTRA_GOOGLE_HELP")) {
            intent.putExtra("EXTRA_GOOGLE_HELP", googleHelp);
        } else if (intent.hasExtra("EXTRA_IN_PRODUCT_HELP")) {
            InProductHelp inProductHelp = (InProductHelp) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "EXTRA_IN_PRODUCT_HELP", InProductHelp.CREATOR);
            inProductHelp.setGoogleHelp(googleHelp);
            SafeParcelableSerializer.serializeToIntentExtra(inProductHelp, intent, "EXTRA_IN_PRODUCT_HELP");
        }
        new TracingHandler(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                GoogleHelpApiImpl.lambda$startHelpActivity$0(activity, intent);
            }
        });
        setResult(googleHelpImpl, Status.RESULT_SUCCESS);
    }
}
