package com.doozycod.nikache;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doozycod.nikache.Adapter.ClockRelatedProductsAdapter;
import com.doozycod.nikache.Adapter.HintAdapter;
import com.doozycod.nikache.Fragment.ClocksFragment;
import com.doozycod.nikache.PojoClasses.Clocks;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ClocksProductDetails extends AppCompatActivity {

    private Clocks clocks;
    private String productId;
    private String name;
    private String clocksPrice;
    private int thumbnail;
    private String clocksDescription;
    private String clocksLength;
    private String clocksSize;
    private String clocksMaterial;
    private String clocksFeatures;
    TextView tvProductId;
    TextView tvProductName;
    TextView tvProductPrice;
    TextView tvProductSummary;
    TextView tvLengthHeaderValue;
    TextView tvSizeHeaderValue;
    TextView tvMaterialHeaderValue;
    TextView tvFeaturesHeaderValue;
    View view;
    Toolbar toolbar;
    RecyclerView rvClockRelatedProducts;

    int clocksCurrentPosition;
    private ArrayList<Clocks> clocksRelatedProductsList = new ArrayList<>();
    ArrayList<Clocks> clocksList;
    int clocksThumbnail;
    ImageView ivProductThumbnail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clocks_product_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

        String[] num_array = {"1","2","3","4","5","Qty :"};

        HintAdapter adapter = new HintAdapter(this, num_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());
        spinner.setPrompt("Qty :");

        Intent i = getIntent();

        clocks = i.getParcelableExtra(ClocksFragment.CLOCKS_KEY);

        clocksCurrentPosition = i.getIntExtra(ClocksFragment.CLOCKS_CURRENT_POSITION, 0);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            clocksList = extras.getParcelableArrayList(ClocksFragment.CLOCKS_LIST_DATA);
        }

        clocksList.remove(clocksCurrentPosition);
        clocksRelatedProductsList = clocksList;




        productId = clocks.getProductId();
        name = clocks.getName();
        clocksPrice = clocks.getClocksPrice();
        clocksDescription = clocks.getClocksDescription();
        clocksLength = clocks.getClocksLength();
        clocksSize = clocks.getClocksSize();
        clocksMaterial = clocks.getClocksMaterial();
        clocksFeatures = clocks.getClocksFeatures();
        clocksThumbnail = clocks.getThumbnail();

        setValueInViews();

        Picasso.with(this).load(clocksThumbnail).into(ivProductThumbnail);

        rvClockRelatedProducts = (RecyclerView) findViewById(R.id.rv_clocks_related_products);
        ClockRelatedProducts clockRelatedProducts = new ClockRelatedProducts();
        clockRelatedProducts.recyclerViewRelatedProducts(rvClockRelatedProducts);
    }

    private void initViews(){
        tvProductId = (TextView)findViewById(R.id.tv_product_id_value);
        tvProductName = (TextView)findViewById(R.id.tv_product_name);
        tvProductPrice = (TextView)findViewById(R.id.tv_product_price);
        tvProductSummary = (TextView)findViewById(R.id.tv_product_summary);
        tvLengthHeaderValue = (TextView)findViewById(R.id.tv_length_header_value);
        tvSizeHeaderValue = (TextView)findViewById(R.id.tv_size_header_value);
        tvMaterialHeaderValue = (TextView)findViewById(R.id.tv_material_header_value);
        tvFeaturesHeaderValue = (TextView)findViewById(R.id.tv_features_header_value);
        ivProductThumbnail = (ImageView)findViewById(R.id.product_thumbnail);
    }

    private void setValueInViews(){
        tvProductId.setText(productId);
        tvProductName.setText(name);
        tvProductPrice.setText(clocksPrice);
        tvProductSummary.setText(clocksDescription);
        tvLengthHeaderValue.setText(clocksLength);
        tvSizeHeaderValue.setText(clocksSize);
        tvMaterialHeaderValue.setText(clocksMaterial);
        tvFeaturesHeaderValue.setText(clocksFeatures);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_details_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.added_to_wishlist:
                Toast.makeText(ClocksProductDetails.this, "Products in wishlist", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_shopping_cart:
                Toast.makeText(ClocksProductDetails.this, "Products in bag", Toast.LENGTH_SHORT).show();
                return true;
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

    public class ClockRelatedProducts  {


        private ClockRelatedProductsAdapter adapter;


        public ClockRelatedProducts(){

        }

        public  void recyclerViewRelatedProducts(RecyclerView recyclerView1){


            adapter = new ClockRelatedProductsAdapter(ClocksProductDetails.this, clocksRelatedProductsList);


            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(ClocksProductDetails.this, LinearLayoutManager.HORIZONTAL, false);

            recyclerView1.setLayoutManager(mLayoutManager);

            // adding custom divider line with padding 16dp
            recyclerView1.addItemDecoration(new HomePageItemDecoration(ClocksProductDetails.this, LinearLayoutManager.HORIZONTAL));

            recyclerView1.setAdapter(adapter);

            adapter.notifyDataSetChanged();


        }

    }
}
