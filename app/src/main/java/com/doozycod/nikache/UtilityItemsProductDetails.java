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

import com.doozycod.nikache.Adapter.HintAdapter;
import com.doozycod.nikache.Adapter.UtilityItemsRelatedProductsAdapter;
import com.doozycod.nikache.Fragment.UtilityItemsFragment;
import com.doozycod.nikache.PojoClasses.UtilityItems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UtilityItemsProductDetails extends AppCompatActivity {

    private UtilityItems utilityItems;
    private String productId;
    private String name;
    private String utilityItemsPrice;
    private int thumbnail;
    private String utilityItemsDescription;
    private String utilityItemsLength;
    private String utilityItemsSize;
    private String utilityItemsMaterial;
    private String utilityItemsFeatures;
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
    RecyclerView rvUtilityitemsRelatedProducts;

    int utilityItemsCurrentPosition;
    private ArrayList<UtilityItems> utilityItemsRelatedProductsList = new ArrayList<>();
    ArrayList<UtilityItems> utilityItemsList;
    int utilityItemsThumbnail;
    ImageView ivProductThumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_items_product_details);

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

        utilityItems = i.getParcelableExtra(UtilityItemsFragment.UTILITY_ITEMS_KEY);

        utilityItemsCurrentPosition = i.getIntExtra(UtilityItemsFragment.UTILITY_ITEMS_CURRENT_POSITION, 0);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            utilityItemsList = extras.getParcelableArrayList(UtilityItemsFragment.UTILITY_ITEMS_LIST_DATA);
        }

        utilityItemsList.remove(utilityItemsCurrentPosition);
        utilityItemsRelatedProductsList = utilityItemsList;

        productId = utilityItems.getProductId();
        name = utilityItems.getName();
        utilityItemsPrice = utilityItems.getUtilityItemsPrice();
        utilityItemsDescription = utilityItems.getUtilityItemsDescription();
        utilityItemsLength = utilityItems.getUtilityItemsLength();
        utilityItemsSize = utilityItems.getUtilityItemsSize();
        utilityItemsMaterial = utilityItems.getUtilityItemsMaterial();
        utilityItemsFeatures = utilityItems.getUtilityItemsFeatures();
        utilityItemsThumbnail = utilityItems.getThumbnail();
        setValueInViews();

        Picasso.with(this).load(utilityItemsThumbnail).into(ivProductThumbnail);

        rvUtilityitemsRelatedProducts = (RecyclerView) findViewById(R.id.rv_utility_items_related_products);
        UtilityItemsRelatedProducts utilityItemsRelatedProducts = new UtilityItemsRelatedProducts();
        utilityItemsRelatedProducts.recyclerViewRelatedProducts(rvUtilityitemsRelatedProducts);
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
        tvProductPrice.setText(utilityItemsPrice);
        tvProductSummary.setText(utilityItemsDescription);
        tvLengthHeaderValue.setText(utilityItemsLength);
        tvSizeHeaderValue.setText(utilityItemsSize);
        tvMaterialHeaderValue.setText(utilityItemsMaterial);
        tvFeaturesHeaderValue.setText(utilityItemsFeatures);
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
                Toast.makeText(UtilityItemsProductDetails.this, "Products in wishlist", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_shopping_cart:
                Toast.makeText(UtilityItemsProductDetails.this, "Products in bag", Toast.LENGTH_SHORT).show();
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

    public class UtilityItemsRelatedProducts  {


        private UtilityItemsRelatedProductsAdapter adapter;


        public UtilityItemsRelatedProducts(){

        }

        public  void recyclerViewRelatedProducts(RecyclerView recyclerView1){


            adapter = new UtilityItemsRelatedProductsAdapter(UtilityItemsProductDetails.this, utilityItemsRelatedProductsList);


            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(UtilityItemsProductDetails.this, LinearLayoutManager.HORIZONTAL, false);

            recyclerView1.setLayoutManager(mLayoutManager);

            // adding custom divider line with padding 16dp
            recyclerView1.addItemDecoration(new HomePageItemDecoration(UtilityItemsProductDetails.this, LinearLayoutManager.HORIZONTAL));

            recyclerView1.setAdapter(adapter);

            adapter.notifyDataSetChanged();

        }

    }

}
