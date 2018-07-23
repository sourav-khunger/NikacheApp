package com.doozycod.nikache;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.doozycod.nikache.Adapter.MyBagProductsAdapter;
import com.doozycod.nikache.PojoClasses.WallDecor;

import java.util.ArrayList;

public class MyBag extends AppCompatActivity {

    View nikacheIconOnActionBar;
    ActionBar actionBar;
    Button myBagButton;
    ConstraintLayout mEmptyBag , mBagContainer;
    WallDecor wallDecor;
    private ArrayList<WallDecor> wallDecorProductsList = new ArrayList<>();
    RecyclerView rvWallDecorProducts;
    public static String qtyValue;
    Button bProceedToCheckout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bag);
        mEmptyBag = (ConstraintLayout) findViewById(R.id.empty_bag);
        mEmptyBag.setVisibility(View.GONE);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nikacheIconOnActionBar = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(nikacheIconOnActionBar);
        actionBar.setTitle("My Bag");
        nikacheIconOnActionBar.setVisibility(View.GONE);

        myBagButton = (Button)findViewById(R.id.iv_order_button1);

        mBagContainer = (ConstraintLayout) findViewById(R.id.bag_container);
        mBagContainer.setVisibility(View.VISIBLE);

        myBagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyBag.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Intent i = getIntent();

        wallDecor = i.getParcelableExtra(WallDecorProductDetails.PRODUCT_DETAIL_KEY);

        qtyValue = i.getStringExtra(WallDecorProductDetails.PRODUCT_QTY_VALUE);



        wallDecorProductsList.add(wallDecor);

        rvWallDecorProducts = (RecyclerView) findViewById(R.id.first_card_view_inmybag);
        MyBagProducts myBagProducts = new MyBagProducts();
        myBagProducts.recyclerViewRelatedProducts(rvWallDecorProducts);

        bProceedToCheckout = (Button)findViewById(R.id.b_proceed_to_checkout);
        bProceedToCheckout.setOnClickListener(proceedToCheckoutClickListener);
    }


    View.OnClickListener proceedToCheckoutClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MyBag.this, AddAddress.class);
            startActivity(intent);
        }
    };

//     if (data.getCount() > 0) {
//
//        findViewById(R.id.wishlist_container).setVisibility(View.VISIBLE);
//        mEmptyBag.setVisibility(View.GONE);
//     }else {
//
//        findViewById(R.id.wishlist_container).setVisibility(View.GONE);
//        mEmptyBag.setVisibility(View.VISIBLE);
//        myBagButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MyBag.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
//     }


    public class MyBagProducts  {

        private MyBagProductsAdapter adapter;

        public MyBagProducts(){

        }

        public  void recyclerViewRelatedProducts(RecyclerView recyclerView1){

            adapter = new MyBagProductsAdapter(MyBag.this, wallDecorProductsList);


            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(MyBag.this, LinearLayoutManager.VERTICAL, false);

            recyclerView1.setLayoutManager(mLayoutManager);

            // adding custom divider line with padding 16dp
            recyclerView1.addItemDecoration(new HomePageItemDecoration(MyBag.this, LinearLayoutManager.VERTICAL));

            recyclerView1.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }

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
