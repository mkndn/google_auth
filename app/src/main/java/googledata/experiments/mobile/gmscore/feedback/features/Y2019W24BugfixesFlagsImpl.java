package googledata.experiments.mobile.gmscore.feedback.features;

import com.google.android.libraries.phenotype.client.PhenotypeConstants;
import com.google.android.libraries.phenotype.client.PhenotypeFlag;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Y2019W24BugfixesFlagsImpl implements Y2019W24BugfixesFlags {
    public static final PhenotypeFlag fixSuggestedArticleCrash;
    public static final PhenotypeFlag selectLastChosenAccountInEmailSpinner;
    public static final PhenotypeFlag throwOnSetScreenshotButNoPiiAllowed;
    public static final PhenotypeFlag trimFeedbackSubmission;

    static {
        PhenotypeFlag.Factory disableBypassPhenotypeForDebug = new PhenotypeFlag.Factory(PhenotypeConstants.getContentProviderUri("com.google.android.gms.feedback")).skipGservices().disableBypassPhenotypeForDebug();
        fixSuggestedArticleCrash = disableBypassPhenotypeForDebug.createFlagRestricted("AndroidFeedback__fix_suggested_article_crash", true);
        selectLastChosenAccountInEmailSpinner = disableBypassPhenotypeForDebug.createFlagRestricted("AndroidFeedback__select_last_chosen_account_in_email_spinner", true);
        throwOnSetScreenshotButNoPiiAllowed = disableBypassPhenotypeForDebug.createFlagRestricted("AndroidFeedback__throw_on_set_screenshot_but_no_pii_allowed", true);
        trimFeedbackSubmission = disableBypassPhenotypeForDebug.createFlagRestricted("AndroidFeedback__trim_feedback_submission", true);
    }

    @Override // googledata.experiments.mobile.gmscore.feedback.features.Y2019W24BugfixesFlags
    public boolean throwOnSetScreenshotButNoPiiAllowed() {
        return ((Boolean) throwOnSetScreenshotButNoPiiAllowed.get()).booleanValue();
    }
}
