package com.doozycod.nikache;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

public class OurLatestProducts extends AppCompatActivity {

    View nikacheIconOnActionBar;
    ActionBar actionBar;
    public static int actionBarWidth;
    LayoutInflater inflator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_latest_products);
        setUpToolBar();
    }

    private void setUpToolBar(){

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Display "Nikache" word as icon on Action Bar.

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle(" ");
        inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nikacheIconOnActionBar = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(nikacheIconOnActionBar);

        // Set the position of the icon "Nikache" on the left side of the Action Bar.
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        //width of action bar is the same as width of whole screen
        actionBarWidth = size.x;

        nikacheIconOnActionBar.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                double x = v.getX();
                double logoImageWidth = v.getWidth();
                // int logoPosition = actionBarWidth / 2 - logoImageWidth / 2;
                double logoPosition = actionBarWidth/4.5  - logoImageWidth /3;
                if (x != logoPosition) {
                    v.setX((float) logoPosition);
                    v.requestLayout();
                } else {
                    v.removeOnLayoutChangeListener(this);
                }
            }

        });

        actionBar.setTitle("Our Latest Products");
        nikacheIconOnActionBar.setVisibility(View.GONE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
