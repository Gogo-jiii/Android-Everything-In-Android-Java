package com.example.viewpager;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

public class ViewPagerUtil {

    private ImageView[] ivArrayDotsPager;
    private static ViewPagerUtil instance = null;

    private ViewPagerUtil() {
    }

    public static ViewPagerUtil getInstance() {
        if (instance == null) {
            instance = new ViewPagerUtil();
        }
        return instance;
    }

    public void setupIndicator(Activity activity, ViewPager2 viewPager2, LinearLayout pagerDots,
                               int size) {

        ivArrayDotsPager = new ImageView[size];

        for (int i = 0; i < size; i++) {
            //create an indicator dot
            ivArrayDotsPager[i] = new ImageView(activity);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(25, 0, 25, 0);

            ivArrayDotsPager[i].setLayoutParams(params);
            ivArrayDotsPager[i].setImageResource(R.drawable.viewpager_indicator_unselected);
            ivArrayDotsPager[i].setOnClickListener(view -> view.setAlpha(1));

            //add that indicator to the layout in the xml
            pagerDots.addView(ivArrayDotsPager[i]);
            pagerDots.bringToFront();
        }

        //set current indicator as selected
        ivArrayDotsPager[0].setImageResource(R.drawable.viewpager_indicator_selected);

        ViewPager2.OnPageChangeCallback onPageChangeCallback =
                new ViewPager2.OnPageChangeCallback() {
                    @Override public void onPageScrolled(int position, float positionOffset,
                                                         int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }

                    @Override public void onPageSelected(int position) {
                        for (ImageView imageView : ivArrayDotsPager) {
                            imageView.setImageResource(R.drawable.viewpager_indicator_unselected);
                        }
                        ivArrayDotsPager[position].setImageResource(R.drawable.viewpager_indicator_selected);
                    }

                    @Override public void onPageScrollStateChanged(int state) {
                        super.onPageScrollStateChanged(state);
                    }
                };
        viewPager2.registerOnPageChangeCallback(onPageChangeCallback);
    }

    public void onBackPressed(final ViewPager2 viewPager2, final FragmentManager fragmentManager) {
        viewPager2.setFocusableInTouchMode(true);
        viewPager2.requestFocus();
        viewPager2.setOnKeyListener((v, keyCode, event) -> {

            if ((keyCode == KeyEvent.KEYCODE_BACK)) {
                if (viewPager2.getCurrentItem() == 0) {
                    fragmentManager.popBackStack();
                } else {
                    viewPager2.setCurrentItem(0, true);
                    viewPager2.setFocusableInTouchMode(false);
                    viewPager2.clearFocus();
                }
                return true;
            }
            return false;
        });
    }
}