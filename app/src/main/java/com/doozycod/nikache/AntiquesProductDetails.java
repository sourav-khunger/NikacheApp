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

import com.doozycod.nikache.Adapter.AntiquesRelatedProductsAdapter;
import com.doozycod.nikache.Adapter.HintAdapter;
import com.doozycod.nikache.Fragment.AntiquesFragment;
import com.doozycod.nikache.PojoClasses.Antiques;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AntiquesProductDetails extends AppCompatActivity {

    private Antiques antiques;
    private String productId;
    private String name;
    private String antiquesPrice;
    private int thumbnail;
    private String antiquesDescription;
    private String antiquesLength;
    private String antiquesSize;
    private String antiquesMaterial;
    private String antiquesFeatures;
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
    RecyclerView rvAntiquesRelatedProducts;

    int antiquesCurrentPosition;
    private ArrayList<Antiques> antiquesRelatedProductsList = new ArrayList<>();
    ArrayList<Antiques> antiquesList;
    int antiquesThumbnail;
    ImageView ivProductThumbnail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antiques_product_details);

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

        antiques = i.getParcelableExtra(AntiquesFragment.ANTIQUES_KEY);

        antiquesCurrentPosition = i.getIntExtra(AntiquesFragment.ANTIQUES_CURRENT_POSITION, 0);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            antiquesList = extras.getParcelableArrayList(AntiquesFragment.ANTIQUES_LIST_DATA);
        }

        antiquesList.remove(antiquesCurrentPosition);
        antiquesRelatedProductsList = antiquesList;


        productId = antiques.getProductId();
        name = antiques.getName();
        antiquesPrice = antiques.getAntiquesPrice();
        antiquesDescription = antiques.getAntiquesDescription();
        antiquesLength = antiques.getAntiquesLength();
        antiquesSize = antiques.getAntiquesSize();
        antiquesMaterial = antiques.getAntiquesMaterial();
        antiquesFeatures = antiques.getAntiquesFeatures();
        antiquesThumbnail = antiques.getThumbnail();

        setValueInViews();

        Picasso.with(this).load(antiquesThumbnail).into(ivProductThumbnail);

        rvAntiquesRelatedProducts = (RecyclerView) findViewById(R.id.rv_antiques_related_products);
        AntiquesRelatedProducts antiquesRelatedProducts = new AntiquesRelatedProducts();
        antiquesRelatedProducts.recyclerViewRelatedProducts(rvAntiquesRelatedProducts);
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
        tvProductPrice.setText(antiquesPrice);
        tvProductSummary.setText(antiquesDescription);
        tvLengthHeaderValue.setText(antiquesLength);
        tvSizeHeaderValue.setText(antiquesSize);
        tvMaterialHeaderValue.setText(antiquesMaterial);
        tvFeaturesHeaderValue.setText(antiquesFeatures);
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
                Toast.makeText(AntiquesProductDetails.this, "Products in wishlist", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_shopping_cart:
                Toast.makeText(AntiquesProductDetails.this, "Products in bag", Toast.LENGTH_SHORT).show();
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

    public class AntiquesRelatedProducts  {

        private AntiquesRelatedProductsAdapter adapter;

        public AntiquesRelatedProducts(){

        }

        public  void recyclerViewRelatedProducts(RecyclerView recyclerView1){


            adapter = new AntiquesRelatedProductsAdapter(AntiquesProductDetails.this, antiquesRelatedProductsList);


            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(AntiquesProductDetails.this, LinearLayoutManager.HORIZONTAL, false);

            recyclerView1.setLayoutManager(mLayoutManager);

            // adding custom divider line with padding 16dp
            recyclerView1.addItemDecoration(new HomePageItemDecoration(AntiquesProductDetails.this, LinearLayoutManager.HORIZONTAL));

            recyclerView1.setAdapter(adapter);

            adapter.notifyDataSetChanged();

        }





    }

}
