package com.doozycod.nikache.Adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.doozycod.nikache.R;

/**
 * View pager adapter
 */
public class MyViewPagerAdapter2 extends PagerAdapter {
    private LayoutInflater layoutInflater;
    Context context;
    TypedArray bannerArray;


    public MyViewPagerAdapter2(Context context,TypedArray bannerArray) {
        this.context = context;
        this.bannerArray = bannerArray;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //return super.instantiateItem(container, position);
        LayoutInflater inflater = LayoutInflater.from(context);
//        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View bannerLayout = (View) inflater.inflate(R.layout.ad_banner2_home_page, container, false);

        ImageView secondBanner = (ImageView) bannerLayout.findViewById(R.id.iv_ad2);

        secondBanner.setImageResource(bannerArray.getResourceId(position, 0));

        container.addView(bannerLayout);
        bannerLayout.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        return bannerLayout;
    }

    @Override
    public int getCount() {
        return bannerArray.length();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}