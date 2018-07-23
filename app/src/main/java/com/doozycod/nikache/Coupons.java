package com.doozycod.nikache;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.doozycod.nikache.Adapter.CouponsRecyclerViewAdapter;
import com.doozycod.nikache.PojoClasses.CouponCard;

import java.util.ArrayList;

public class Coupons extends AppCompatActivity {

    ActionBar actionBar;
    View couponsActionBar;
    EditText etPromoCode;
    RecyclerView couponsRecyclerView;
    Button couponButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);
        actionBar = getSupportActionBar();

        actionBar.setTitle("Coupons");

        initViews();

        CouponsRecyclerView couponCardRecyclerView = new CouponsRecyclerView();
        couponCardRecyclerView.recyclerViewFirstMethod(couponsRecyclerView);

    }

    private void initViews(){
        etPromoCode = (EditText)findViewById(R.id.et_promo_code);
        couponsRecyclerView = (RecyclerView)findViewById(R.id.coupons_recycler_view);
        couponButton = (Button)findViewById(R.id.coupon_button);
//        couponButton.setVisibility(View.GONE);
    }

    public class CouponsRecyclerView  {

        private ArrayList<CouponCard> couponsRecyclerViewList = new ArrayList<>();
        private CouponsRecyclerViewAdapter adapter;

        public CouponsRecyclerView(){

        }

        public  void recyclerViewFirstMethod(RecyclerView recyclerView1){

            adapter = new CouponsRecyclerViewAdapter(getApplicationContext(), couponsRecyclerViewList);


            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

            recyclerView1.setLayoutManager(mLayoutManager);

            // adding custom divider line with padding 16dp
//            recyclerView1.addItemDecoration(new HomePageItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));

            recyclerView1.setAdapter(adapter);

            prepareAlbums();

        }

        private void prepareAlbums(){
            CouponCard a = new CouponCard("ONCEAMONTH","Use promocode ONCEAMONTH and get flat" +
                    "Rs. 200 Cashback on your 1st order this month. Min order value Rs.299. Also, get extra gold worth" +
                    "upto Rs 500 on purchase of Paytm Gold");
            couponsRecyclerViewList.add(a);

            a = new CouponCard("FASH30", "Use promocode FASH30 to get 30% cashback (max cashback 4000). COD" +
                                       " Option Will Not Be Available On Applying This Promocode");
            couponsRecyclerViewList.add(a);

            a = new CouponCard("STYLE10","Use promocode STYLE10 to get 10% cashback(max cashback 4000). COD" +
                                       " Option Will Not Be Available On Applying This Promocode");
            couponsRecyclerViewList.add(a);

            adapter.notifyDataSetChanged();
        }

    }

}
