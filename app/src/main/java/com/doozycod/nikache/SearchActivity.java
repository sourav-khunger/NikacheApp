package com.doozycod.nikache;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

public class SearchActivity extends AppCompatActivity {

    View nikacheIconOnActionBar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        actionBar = getSupportActionBar();
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nikacheIconOnActionBar = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(nikacheIconOnActionBar);
        actionBar.setTitle("");
        actionBar.setDisplayShowCustomEnabled(true);

        nikacheIconOnActionBar.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                double x = v.getX();
                double logoImageWidth = v.getWidth();
                // int logoPosition = actionBarWidth / 2 - logoImageWidth / 2;
                double logoPosition = MainActivity.actionBarWidth/4.5  - logoImageWidth /3;
                if (x != logoPosition) {
                    v.setX((float) logoPosition);
                    v.requestLayout();
                } else {
                    v.removeOnLayoutChangeListener(this);
                }
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
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
