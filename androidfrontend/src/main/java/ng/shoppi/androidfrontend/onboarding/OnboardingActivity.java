package ng.shoppi.androidfrontend.onboarding;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ng.shoppi.androidfrontend.R;
import ng.shoppi.androidfrontend.listeners.OnboardingListener;


/**
 * @author Olawale
 */

public class OnboardingActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Context context;
    private LinearLayout layoutDots;
    private ViewPager mViewPager;
    private Button buttonSkip;
    private Button buttonNext;

    ArrayList<Fragment> layouts = new ArrayList<>();
    private TextView[] dots;

    private OnboardingListener onboardingListener;

    private static final int DEFAULT_FRAGMENT_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.onborading_skeleton);
        layoutDots = (LinearLayout) findViewById(R.id.layoutDots);
        mViewPager = (ViewPager) findViewById(R.id.container);
        buttonSkip = (Button) findViewById(R.id.btn_skip);
        buttonNext = (Button) findViewById(R.id.btn_next);

        context = OnboardingViews.mActivity;
        onboardingListener = (OnboardingListener) context;
        setStatusBar();
        int[] layoutIds;
        //grab the message sent via intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(OnboardingViews.FRAGMENT_LAYOUT)) {
            layoutIds = intent.getIntArrayExtra(OnboardingViews.FRAGMENT_LAYOUT);
            //for each layout id, we create a placeholder fragment
            for (int i = 0; i < layoutIds.length; i++) {
                layouts.add(PlaceholderFragment.newInstance(layoutIds[i]));
            }
        } else if (intent != null && intent.hasExtra(OnboardingViews.DEFAULT_FRAGMENT_LAYOUT)) {
            //create DEFAULT_FRAGMENT_COUNT fragments for default
            for (int i = 0; i < DEFAULT_FRAGMENT_COUNT; i++) {
                layouts.add(PlaceholderFragment.newInstance(R.layout.default_onboarding_fragment));
            }

        }

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        //mViewPager = (ViewPager)findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //initialise button dot
        addBottomDots(0);


        //handle the clicks
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getItem(+1);
                if (current < layouts.size()) {
                    // move to next screen
                    mViewPager.setCurrentItem(current);
                } else {
                    onboardingListener.onReady();
                }

            }
        });
        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onboardingListener.onSkipClick();
            }
        });
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.size()];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        layoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            layoutDots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return layouts.get(position);
            //return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return layouts.size();
            //return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "SECTION 1";
//                case 1:
//                    return "SECTION 2";
//                case 2:
//                    return "SECTION 3";
//            }
            return null;
        }
    }

    private void setStatusBar() {
        //Requires APi 21
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //noinspection deprecation
            window.setStatusBarColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String LAYOUT_ID = "layout_id";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int layoutId) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt(LAYOUT_ID, layoutId);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int layoutId = getArguments().getInt(LAYOUT_ID);
            return inflater.inflate(layoutId, container, false);
        }
    }
}
