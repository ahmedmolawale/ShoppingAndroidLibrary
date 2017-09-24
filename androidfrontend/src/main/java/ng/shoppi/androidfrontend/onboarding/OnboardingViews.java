package ng.shoppi.androidfrontend.onboarding;

import android.app.Activity;
import android.content.Intent;

import ng.shoppi.androidfrontend.R;


/**
 * Onboarding Views
 *
 * @author Olawale
 */

public class OnboardingViews {

    protected static final String FRAGMENT_LAYOUT = "fragment";
    protected static final String DEFAULT_FRAGMENT_LAYOUT = "default_fragment";
    public static Activity mActivity;

    /**
     * Used to initialize the fragments of the on-boarding views
     *
     * @param activity        Activity or Context object
     * @param fragmentLayouts an int array of the fragments' layouts
     */
    public static void init(Activity activity, int[] fragmentLayouts) {
        mActivity = activity;
        Intent intent = new Intent(activity, OnboardingActivity.class);
        intent.putExtra(FRAGMENT_LAYOUT, fragmentLayouts);
        activity.startActivity(intent);
    }

    /**
     * Used to initialize the fragments of the on-boarding views using the default fragments
     *
     * @param activity Activity or Context object
     */
    public static void init(Activity activity) {
        mActivity = activity;
        Intent intent = new Intent(activity, OnboardingActivity.class);
        intent.putExtra(DEFAULT_FRAGMENT_LAYOUT, R.layout.default_onboarding_fragment);
        activity.startActivity(intent);
    }
}
