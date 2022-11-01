package com.google.android.apps.authenticator.howitworks;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.viewpager.widget.ViewPager;
import com.google.android.apps.authenticator.backup.AuthenticatorBackupAgent;
import com.google.android.apps.authenticator.backup.BackupKeys;
import com.google.android.apps.authenticator.enroll2sv.wizard.AddAccountActivity;
import com.google.android.apps.authenticator.testability.TestableActivity;
import com.google.android.apps.authenticator2.R;
import com.google.android.libraries.view.pagingindicator.PagingIndicator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class HowItWorksActivity extends TestableActivity {
    private static final int HOWITWORKS_PAGER_LAYOUT_COUNT;
    private static final int[] HOWITWORKS_PAGER_LAYOUT_ID_LIST;
    public static final String KEY_FIRST_ONBOARDING_EXPERIENCE = "firstOnboardingExperience";
    private Button buttonGetStarted;
    private Button buttonNext;
    private boolean onFirstOnboardingExperience;
    private ViewPager pager;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class HowItWorksFragment extends Fragment {
        public static final String ARG_LAYOUT_ID = "layoutId";

        @Override // android.support.v4.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(getArguments().getInt(ARG_LAYOUT_ID), viewGroup, false);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class HowItWorksPagerAdapter extends FragmentPagerAdapter {
        public HowItWorksPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override // android.support.v4.app.FragmentPagerAdapter, androidx.viewpager.widget.PagerAdapter
        public void finishUpdate(ViewGroup viewGroup) {
            HowItWorksActivity.this.updateNextButtonText();
            super.finishUpdate(viewGroup);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return HowItWorksActivity.HOWITWORKS_PAGER_LAYOUT_COUNT;
        }

        @Override // android.support.v4.app.FragmentPagerAdapter
        public Fragment getItem(int i) {
            HowItWorksFragment howItWorksFragment = new HowItWorksFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(HowItWorksFragment.ARG_LAYOUT_ID, HowItWorksActivity.HOWITWORKS_PAGER_LAYOUT_ID_LIST[i]);
            howItWorksFragment.setArguments(bundle);
            return howItWorksFragment;
        }
    }

    static {
        int[] iArr = {R.layout.howitworks_layout_0, R.layout.howitworks_layout_1, R.layout.howitworks_layout_2};
        HOWITWORKS_PAGER_LAYOUT_ID_LIST = iArr;
        HOWITWORKS_PAGER_LAYOUT_COUNT = iArr.length;
    }

    private void finishHowItWorksActivity() {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(BackupKeys.KEY_ONBOARDING_COMPLETED, true).commit();
        AuthenticatorBackupAgent.scheduleBackup(this);
        if (this.onFirstOnboardingExperience) {
            startActivity(new Intent(this, AddAccountActivity.class));
        }
        finish();
    }

    private void nextPageOrFinish() {
        if (this.pager.getCurrentItem() == this.pager.getAdapter().getCount() - 1) {
            finish();
            return;
        }
        ViewPager viewPager = this.pager;
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNextButtonText() {
        if (this.pager.getCurrentItem() == this.pager.getChildCount()) {
            this.buttonNext.setText(R.string.howitworks_page_button_done);
        } else {
            this.buttonNext.setText(R.string.howitworks_page_button_next);
        }
    }

    int getViewPagerCurrentItem() {
        return this.pager.getCurrentItem();
    }

    int getViewPagerTotalItem() {
        return this.pager.getAdapter().getCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-google-android-apps-authenticator-howitworks-HowItWorksActivity  reason: not valid java name */
    public /* synthetic */ void m71x6cdc602d(View view) {
        finishHowItWorksActivity();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$1$com-google-android-apps-authenticator-howitworks-HowItWorksActivity  reason: not valid java name */
    public /* synthetic */ void m72x5e2defae(View view) {
        nextPageOrFinish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.pager.getCurrentItem() > 0) {
            ViewPager viewPager = this.pager;
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            return;
        }
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getResources().getBoolean(R.bool.isTablet)) {
            setRequestedOrientation(2);
        } else {
            setRequestedOrientation(12);
        }
        setContentView(R.layout.howitworks);
        if (bundle != null) {
            this.onFirstOnboardingExperience = bundle.getBoolean(KEY_FIRST_ONBOARDING_EXPERIENCE);
        } else {
            Intent intent = getIntent();
            this.onFirstOnboardingExperience = intent != null && intent.getBooleanExtra(KEY_FIRST_ONBOARDING_EXPERIENCE, false);
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.howitworks_pager);
        this.pager = viewPager;
        viewPager.setAdapter(new HowItWorksPagerAdapter(getSupportFragmentManager()));
        ((PagingIndicator) findViewById(R.id.paging_indicator)).setViewPager(this.pager);
        this.buttonGetStarted = (Button) findViewById(R.id.howitworks_button_get_started);
        this.buttonNext = (Button) findViewById(R.id.howitworks_button_next);
        this.buttonGetStarted.setVisibility(this.onFirstOnboardingExperience ? 0 : 8);
        this.buttonNext.setVisibility(this.onFirstOnboardingExperience ? 8 : 0);
        this.buttonGetStarted.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.apps.authenticator.howitworks.HowItWorksActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HowItWorksActivity.this.m71x6cdc602d(view);
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.apps.authenticator.howitworks.HowItWorksActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HowItWorksActivity.this.m72x5e2defae(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(KEY_FIRST_ONBOARDING_EXPERIENCE, this.onFirstOnboardingExperience);
    }

    int setViewPagerCurrentItem(int i) {
        this.pager.setCurrentItem(i);
        return this.pager.getCurrentItem();
    }
}
