package com.doozycod.nikache.Fragment;

import android.annotation.TargetApi;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.doozycod.nikache.Adapter.MyViewPagerAdapter;
import com.doozycod.nikache.R;

import java.util.Timer;
import java.util.TimerTask;


public class ViewPager1 extends Fragment {

        private ViewPager viewPager;
        private LinearLayout mBannerDotsLayout;
        private MyViewPagerAdapter myViewPagerAdapter;
        private View[] mBannerDotViews;
        private TypedArray mBannerArray;
        private int numberOfBannerImage;



        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_view_pager1, container, false);

            /**
             * Initializing banner array variable. "mBannerArray"
             */
            mBannerArray = getResources().obtainTypedArray(R.array.banner_img_array1);

            // Storing the length of banner array size. "numberOfBannerImage"
            numberOfBannerImage=mBannerArray.length();

            // creating an array of type @View and array variable name is mBannerDotViews
            // The size of array is equal to the no. of banners in a banner array named "mBannerArray".
            // Value is not initialized in the array named "mBannerDotViews".
            // It's an empty array.
            mBannerDotViews = new View[numberOfBannerImage];


            // Initializing @ViewPager
            viewPager = (ViewPager) view.findViewById(R.id.view_pager1);

            // Initializing the layout for banner's dots @LinearLayout
            mBannerDotsLayout= (LinearLayout) view.findViewById(R.id.bannerDotsLayout);

            // Initializing banner adapter @mBannerPagerAdapter, and passing parameter @currentActivityContext and @bannerArray
            myViewPagerAdapter=new MyViewPagerAdapter(getContext(),mBannerArray);


            // Setting adapter in ViewPager
            viewPager.setAdapter(myViewPagerAdapter);

            // Initializing loop, it will repeat no. of times equal to the no. of banners that is 4.
            for (int i = 0; i < numberOfBannerImage; i++) {

                // Creating a view for a banner's dot of type @View
                final View bannerDotView = new View(getContext());

                /**
                 * Creating the dynamic dots for banner.
                 */

                // Setting the attribute of LinearLayout, Using @dotLayoutParm object we will set the value of attributes for LinearLayout.
                LinearLayout.LayoutParams dotLayoutParm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                // Height of dot's LinearLayout equals to 10dp.
                dotLayoutParm.height = getResources().getDimensionPixelSize(R.dimen.standard_10);
                // Width of dot's LinearLayout equals to 10dp.
                dotLayoutParm.width = getResources().getDimensionPixelSize(R.dimen.standard_10);
                // Layout Margin of dot's LinearLayout. LeftMargin equals to 8dp.
                dotLayoutParm.setMargins(getResources().getDimensionPixelSize(R.dimen.standard_8),0,0,0);
                // Setting the layout of view @bannerDotView of banner's dot using the object @dotLayoutParm of @LinearLayout.LayoutParams.
                // The value of the attributes of which is already set using @dotLayoutParm object of type @LinearLayout.LayoutParams.
                bannerDotView.setLayoutParams(dotLayoutParm);
                //Setting the background of the view equals to white bordered dot.
                bannerDotView.setBackground(getResources().getDrawable(R.drawable.shape_deselected_dot));
                // Adding a view @bannerDotView (containing a white bordered dot) in Horizontal LinearLayout.
                // This loop will run 4 times and 4 (white bordered dot i.e. unselected dot) will get added into the LinearLayout Horizontally.
                mBannerDotsLayout.addView(bannerDotView);
                // This array of type @View of size 4 is assigning 4 views containing white bordered dot, 1 in each loop, for later reference.
                mBannerDotViews[i] = bannerDotView;
            }



            AutoSwipeBanner();

            viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

            return view;
        }


        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }

//    private void addBottomDots(int currentPage) {
//        dots = new TextView[MyViewPagerAdapter2.layouts.length];
//
//        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
//        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
//
//        dotsLayout.removeAllViews();
//        for (int i = 0; i < dots.length; i++) {
//            dots[i] = new TextView(getContext());
//            dots[i].setText(Html.fromHtml("&#8226;"));
//            dots[i].setTextSize(35);
//            dots[i].setTextColor(colorsInactive[currentPage]);
//            dotsLayout.addView(dots[i]);
//        }
//
//        if (dots.length > 0)
//            dots[currentPage].setTextColor(colorsActive[currentPage]);
//    }

        private int getItem(int i) {
            return viewPager.getCurrentItem() + i;
        }

        // calling method @addOnPageChangeListener on ViewPager, which will do something on the different state of banner page change.
        //  viewpager change listener
        ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

            // THis method will do something when any particular banner page is selected.
            @Override
            public void onPageSelected(int position) {

                // with the change of banner, dot background changes.
                // for example, if third banner is highlighted, then third dot background get filled with full white circle.
                changeDotBG(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        };

        /**
         * Making notification bar transparent
         */
        private void changeStatusBarColor() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        }

        public void AutoSwipeBanner(){

            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {

                    int currentPage = viewPager.getCurrentItem();

                    if (currentPage == mBannerArray.length()-1) {
                        currentPage = -1;
                    }
                    viewPager.setCurrentItem(currentPage+1, true);
                }
            };

            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 500, 3000);

        }


        // with the change of banner, dot background changes.
        // for example, if third banner is highlighted, then third dot background get filled with full white circle.

        // This method is called when the current banner is highlighted.
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        private void changeDotBG(int position){

            // Loop will run 4 times equal to the no. of banners
            for(int i = 0; i < numberOfBannerImage; i++){

                // if position equals to i that means the banner with index position "i" is the current banner.
                if(position==i){
                    // Set the background of the white bordered circle with full white circle, here the current banner index value is "i".
                    mBannerDotViews[i].setBackground(getResources().getDrawable(R.drawable.shape_selected_dot));
                }else{
                    // Set the background of the white bordered circle with white bordered circle only, here the current banner index value is not "i".
                    mBannerDotViews[i].setBackground(getResources().getDrawable(R.drawable.shape_deselected_dot));
                }

            }
        }


    }

