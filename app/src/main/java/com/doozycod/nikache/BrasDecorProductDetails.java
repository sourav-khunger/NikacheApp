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

import com.doozycod.nikache.Adapter.BrassDecorRelatedProductsAdapter;
import com.doozycod.nikache.Adapter.HintAdapter;
import com.doozycod.nikache.Fragment.BrasDecorFragment;
import com.doozycod.nikache.PojoClasses.BrasDecor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BrasDecorProductDetails extends AppCompatActivity {

    private BrasDecor brasDecor;
    private String productId;
    private String name;
    private String brasDecorPrice;
    private int thumbnail;
    private String brasDecorDescription;
    private String brasDecorLength;
    private String brasDecorSize;
    private String brasDecorMaterial;
    private String brasDecorFeatures;
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
    RecyclerView rvBrasDecorRelatedProducts;

    int brassDecorCurrentPosition;
    private ArrayList<BrasDecor> brassDecorRelatedProductsList = new ArrayList<>();
    ArrayList<BrasDecor> brassDecorList;
    int brassDecorThumbnail;
    ImageView ivProductThumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bras_decor_product_details);

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

        brasDecor = i.getParcelableExtra(BrasDecorFragment.BRASS_DECOR_KEY);

        brassDecorCurrentPosition = i.getIntExtra(BrasDecorFragment.BRASS_DECOR_CURRENT_POSITION, 0);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            brassDecorList = extras.getParcelableArrayList(BrasDecorFragment.BRASS_DECOR_LIST_DATA);
        }

        brassDecorList.remove(brassDecorCurrentPosition);
        brassDecorRelatedProductsList = brassDecorList;



        productId = brasDecor.getProductId();
        name = brasDecor.getName();
        brasDecorPrice = brasDecor.getBrassDecorPrice();
        brasDecorDescription = brasDecor.getBrassDecorDescription();
        brasDecorLength = brasDecor.getBrassDecorLength();
        brasDecorSize = brasDecor.getBrassDecorSize();
        brasDecorMaterial = brasDecor.getBrassDecorMaterial();
        brasDecorFeatures = brasDecor.getBrassDecorFeatures();
        brassDecorThumbnail = brasDecor.getThumbnail();

        setValueInViews();

        Picasso.with(this).load(brassDecorThumbnail).into(ivProductThumbnail);

        rvBrasDecorRelatedProducts = (RecyclerView) findViewById(R.id.rv_brass_decor_related_products);
        BrassDecorRelatedProducts brassDecorRelatedProducts = new BrassDecorRelatedProducts();
        brassDecorRelatedProducts.recyclerViewRelatedProducts(rvBrasDecorRelatedProducts);
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
        tvProductPrice.setText(brasDecorPrice);
        tvProductSummary.setText(brasDecorDescription);
        tvLengthHeaderValue.setText(brasDecorLength);
        tvSizeHeaderValue.setText(brasDecorSize);
        tvMaterialHeaderValue.setText(brasDecorMaterial);
        tvFeaturesHeaderValue.setText(brasDecorFeatures);
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
                Toast.makeText(BrasDecorProductDetails.this, "Products in wishlist", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_shopping_cart:
                Toast.makeText(BrasDecorProductDetails.this, "Products in bag", Toast.LENGTH_SHORT).show();
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



    public class BrassDecorRelatedProducts  {


        private BrassDecorRelatedProductsAdapter adapter;


        public BrassDecorRelatedProducts(){

        }

        public  void recyclerViewRelatedProducts(RecyclerView recyclerView1){


            adapter = new BrassDecorRelatedProductsAdapter(BrasDecorProductDetails.this, brassDecorRelatedProductsList);


            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(BrasDecorProductDetails.this, LinearLayoutManager.HORIZONTAL, false);

            recyclerView1.setLayoutManager(mLayoutManager);

            // adding custom divider line with padding 16dp
            recyclerView1.addItemDecoration(new HomePageItemDecoration(BrasDecorProductDetails.this, LinearLayoutManager.HORIZONTAL));

            recyclerView1.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }

    }

}
