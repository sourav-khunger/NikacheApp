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

import com.doozycod.nikache.Adapter.FurnitureRelatedProductsAdapter;
import com.doozycod.nikache.Adapter.HintAdapter;
import com.doozycod.nikache.Fragment.FurnitureFragment;
import com.doozycod.nikache.PojoClasses.Furniture;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FurnitureProductDetails extends AppCompatActivity {

    private Furniture furniture;
    private String productId;
    private String name;
    private String furniturePrice;
    private int thumbnail;
    private String furnitureDescription;
    private String furnitureLength;
    private String furnitureSize;
    private String furnitureMaterial;
    private String furnitureFeatures;
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
    RecyclerView rvFurnitureRelatedProducts;
    int furnitureCurrentPosition;
    private ArrayList<Furniture> furnitureRelatedProductsList = new ArrayList<>();
    ArrayList<Furniture> furnitureList;
    int furnitureThumbnail;
    ImageView ivProductThumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture_product_details);

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

        furniture = i.getParcelableExtra(FurnitureFragment.FURNITURE_KEY);
        furnitureCurrentPosition = i.getIntExtra(FurnitureFragment.FURNITURE_CURRENT_POSITION, 0);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            furnitureList = extras.getParcelableArrayList(FurnitureFragment.FURNITURE_LIST_DATA);
        }

        furnitureList.remove(furnitureCurrentPosition);
        furnitureRelatedProductsList = furnitureList;

        productId = furniture.getProductId();
        name = furniture.getName();
        furniturePrice = furniture.getFurniturePrice();
        furnitureDescription = furniture.getFurnitureDescription();
        furnitureLength = furniture.getFurnitureLength();
        furnitureSize = furniture.getFurnitureSize();
        furnitureMaterial = furniture.getFurnitureMaterial();
        furnitureFeatures = furniture.getFurnitureFeatures();
        furnitureThumbnail = furniture.getThumbnail();

        setValueInViews();

        Picasso.with(this).load(furnitureThumbnail).into(ivProductThumbnail);

        rvFurnitureRelatedProducts = (RecyclerView) findViewById(R.id.rv_furniture_related_products);
        FurnitureRelatedProducts furnitureRelatedProducts = new FurnitureRelatedProducts();
        furnitureRelatedProducts.recyclerViewRelatedProducts(rvFurnitureRelatedProducts);
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
        tvProductPrice.setText(furniturePrice);
        tvProductSummary.setText(furnitureDescription);
        tvLengthHeaderValue.setText(furnitureLength);
        tvSizeHeaderValue.setText(furnitureSize);
        tvMaterialHeaderValue.setText(furnitureMaterial);
        tvFeaturesHeaderValue.setText(furnitureFeatures);
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
                Toast.makeText(FurnitureProductDetails.this, "Products in wishlist", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_shopping_cart:
                Toast.makeText(FurnitureProductDetails.this, "Products in bag", Toast.LENGTH_SHORT).show();
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


    public class FurnitureRelatedProducts  {


        private FurnitureRelatedProductsAdapter adapter;
        RecyclerView recyclerView1;

        public FurnitureRelatedProducts(){

        }

        public  void recyclerViewRelatedProducts(RecyclerView recyclerView1){


            adapter = new FurnitureRelatedProductsAdapter(FurnitureProductDetails.this, furnitureRelatedProductsList);


            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(FurnitureProductDetails.this, LinearLayoutManager.HORIZONTAL, false);

            recyclerView1.setLayoutManager(mLayoutManager);

            // adding custom divider line with padding 16dp
            recyclerView1.addItemDecoration(new HomePageItemDecoration(FurnitureProductDetails.this, LinearLayoutManager.HORIZONTAL));

            recyclerView1.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }





    }
}
