package com.google.android.libraries.performance.primes;

import android.content.Context;
import com.google.common.collect.ImmutableSet;
import javax.inject.Inject;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashOnBadPrimesConfiguration {
    private final String packageName;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public CrashOnBadPrimesConfiguration(Context context) {
        this.packageName = context.getPackageName();
    }

    private boolean appIsAllowlistedForBackgroundInit() {
        return ImmutableSet.of((Object) "com.android.vending", (Object) "com.google.android.GoogleCamera", (Object) "com.google.android.GoogleCameraEng", (Object) "com.google.android.apps.docs", (Object) "com.google.android.apps.docs.editors.docs", (Object) "com.google.android.apps.docs.editors.sheets", (Object[]) new String[]{"com.google.android.apps.docs.editors.slides", "com.google.android.apps.geo.food.omniapp.nomni", "com.google.android.apps.gmail.testing.unit", "com.google.android.apps.gmm", "com.google.android.apps.gmm.ads.label.testing.app", "com.google.android.apps.gmm.search.map.testing.app", "com.google.android.apps.googlecamera.fishfood", "com.google.android.apps.jamkiosk", "com.google.android.apps.messaging", "com.google.android.apps.tasks", "com.google.android.apps.tasks.ui.scuba", "com.google.android.apps.work.clouddpc", "com.google.android.apps.work.clouddpc.arc", "com.google.android.apps.youtube.creator", "com.google.android.apps.youtube.kids", "com.google.android.apps.youtube.mango", "com.google.android.apps.youtube.music", "com.google.android.apps.youtube.unplugged", "com.google.android.apps.youtube.vr", "com.google.android.apps.youtube.vr.oculus", "com.google.android.gms", "com.google.android.googlequicksearchbox", "com.google.android.inputmethod.latin", "com.google.android.inputmethod.latin.canary", "com.google.android.inputmethod.latin.dev", "com.google.android.play.games", "com.google.android.youtube", "com.google.android.youtube.test", "com.google.android.youtube.tv", "com.google.android.youtube.tvunplugged", "com.google.intelligence.sense.ambientmusic.functional.emulator", "com.google.intelligence.sense.ambientmusic.history.functional"}).contains(this.packageName);
    }

    private boolean appIsAllowlistedForOutOfOrderLifecycleEvents() {
        if (appIsAllowlistedForBackgroundInit()) {
            return true;
        }
        return ImmutableSet.of((Object) "com.google.android.apps.accessibility.reveal", (Object) "com.google.android.apps.adwords", (Object) "com.google.android.apps.adwords.devel", (Object) "com.google.android.apps.adwords.dogfood", (Object) "com.google.android.apps.adwords.fishfood", (Object) "com.google.android.apps.adwords.nightly", (Object[]) new String[]{"com.google.android.apps.diagnosticstool", "com.google.android.apps.dragonfly", "com.google.android.apps.dynamite", "com.google.android.apps.gmm.home.cards.yourexplore", "com.google.android.apps.internal.admobsdk.mediumtest.stability", "com.google.android.apps.nbu.paisa.user.integration.home", "com.google.android.apps.nbu.paisa.user.integration.homescreen", "com.google.android.apps.nbu.paisa.user.integration.microapp", "com.google.android.apps.nbu.paisa.user.integration.qrcode", "com.google.android.apps.searchlite.homescreen", "com.google.android.flutter.testing.integrationtest.skeleton", "com.google.android.libraries.performance.primes.sample.profiling.application", "com.google.android.marvin.talkback", "com.google.android.street"}).contains(this.packageName);
    }

    public void observedBackgroundInitialization() {
        if (appIsAllowlistedForOutOfOrderLifecycleEvents()) {
            return;
        }
        throw new IllegalStateException("Primes init triggered from background in package: " + this.packageName);
    }

    public void observedOutOfOrderLifecycleEvent() {
        if (appIsAllowlistedForOutOfOrderLifecycleEvents()) {
            return;
        }
        throw new IllegalStateException("Primes did not observe lifecycle events in the expected order. Either you are initializing Primes incorrectly, or your tests are calling lifecycle methods incorrectly.");
    }
}
