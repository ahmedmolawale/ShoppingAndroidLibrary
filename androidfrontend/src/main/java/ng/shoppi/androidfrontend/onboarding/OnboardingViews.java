package ng.shoppi.androidfrontend.onboarding;

import android.app.Activity;
import android.content.Intent;

import ng.shoppi.androidfrontend.R;

/**
 * @author Olawale
 */

public class OnboardingViews {

    protected static final String FRAGMENT_LAYOUT = "fragment";
    protected static final String DEFAULT_FRAGMENT_LAYOUT = "default_fragment";
    public static Activity mActivity;
    public static void init(Activity activity, int[] fragmentLayouts){
        mActivity = activity;
        Intent intent = new Intent(activity, OnboardingActivity.class);
        intent.putExtra(FRAGMENT_LAYOUT, fragmentLayouts);
        activity.startActivity(intent);
    }
    public static void init(Activity activity){
        mActivity = activity;
        Intent intent = new Intent(activity, OnboardingActivity.class);
        intent.putExtra(DEFAULT_FRAGMENT_LAYOUT, R.layout.default_onboarding_fragment);
        activity.startActivity(intent);
    }
}
