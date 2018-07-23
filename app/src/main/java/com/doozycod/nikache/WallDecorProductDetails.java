package com.doozycod.nikache;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doozycod.nikache.Adapter.HintAdapter;
import com.doozycod.nikache.Adapter.WallDecorRelatedProductsAdapter;
import com.doozycod.nikache.Fragment.WallDecorFragment;
import com.doozycod.nikache.PojoClasses.WallDecor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WallDecorProductDetails extends AppCompatActivity {

    View nikacheIconOnActionBar;
    ActionBar actionBar;
    Toolbar toolbar;
    Fragment productDetailsFragment;
    public static String CURRENT_TAG;

    private WallDecor wallDecor;
    private String productId;
    private String name;
    private String walldecorPrice;
    private int thumbnail;
    private String walldecorDescription;
    private String walldecorLength;
    private String walldecorSize;
    private String walldecorMaterial;
    private String walldecorFeatures;
    TextView tvProductId;
    TextView tvProductName;
    TextView tvProductPrice;
    TextView tvProductSummary;
    TextView tvLengthHeaderValue;
    TextView tvSizeHeaderValue;
    TextView tvMaterialHeaderValue;
    TextView tvFeaturesHeaderValue;
    View view;
    RecyclerView rvWallDecorRelatedProducts;
    int wallDecorThumbnail;
    ImageView ivProductThumbnail;
    int wallDecorCurrentPosition;
    ArrayList<WallDecor> wallDecorList;
    private ArrayList<WallDecor> wallDecorRelatedProductsList = new ArrayList<>();
    Button bAddToBag;
    public static final String PRODUCT_DETAIL_KEY = "product_detail_key";
    public static final String PRODUCT_QTY_VALUE = "product_qty_value";
    String qtyValue;
    Intent intent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_decor_product_details);

//        actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        nikacheIconOnActionBar = inflator.inflate(R.layout.custom_imageview, null);
//        actionBar.setCustomView(nikacheIconOnActionBar);
//        actionBar.setTitle("");
//        nikacheIconOnActionBar.setVisibility(View.GONE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

//        TextView productSummary = (TextView)findViewById(R.id.tv_product_summary);
//        productSummary.setText("Fusce et congue dolor, id placerat lorem. \nVestibulum\n" +
//                "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
//                "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
//                "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
//                "a aliquam ante rutrum eu.");

        String[] num_array = {"1","2","3","4","5","Qty :"};

        HintAdapter adapter = new HintAdapter(this, num_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());
        spinner.setPrompt("Qty :");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                qtyValue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        qtyValue = spinner.getSelectedItem().toString();

        Intent i = getIntent();

        wallDecor = i.getParcelableExtra(WallDecorFragment.WALL_DECOR_KEY);
        wallDecorCurrentPosition = i.getIntExtra(WallDecorFragment.WALL_DECOR_CURRENT_POSITION, 0);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            wallDecorList = extras.getParcelableArrayList(WallDecorFragment.WALL_DECOR_LIST_DATA);
        }

        wallDecorList.remove(wallDecorCurrentPosition);
        wallDecorRelatedProductsList = wallDecorList;

        productId = wallDecor.getProductId();
        name = wallDecor.getName();
        walldecorPrice = wallDecor.getWallDecorPrice();
        walldecorDescription = wallDecor.getWalldecorDescription();
        walldecorLength = wallDecor.getWalldecorLength();
        walldecorSize = wallDecor.getWalldecorSize();
        walldecorMaterial = wallDecor.getwalldecorMaterial();
        walldecorFeatures = wallDecor.getWalldecorFeatures();
        wallDecorThumbnail = wallDecor.getThumbnail();
//        wallDecor.setProductQtyValue(qtyValue);
        setValueInViews();

        Picasso.with(this).load(wallDecorThumbnail).into(ivProductThumbnail);

        rvWallDecorRelatedProducts = (RecyclerView) findViewById(R.id.rv_wall_decor_related_products);
        WallDecorRelatedProducts wallDecorRelatedProducts = new WallDecorRelatedProducts();
        wallDecorRelatedProducts.recyclerViewRelatedProducts(rvWallDecorRelatedProducts);


            bAddToBag.setOnClickListener(addToBagClickListener);
    }

    boolean showingFirst = true;


        View.OnClickListener addToBagClickListener = new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(qtyValue == "1" || qtyValue == "2" ||qtyValue == "3" ||qtyValue == "4" ||qtyValue == "5" )

                {

                    if (showingFirst == true) {
                        v.setBackgroundColor(Color.parseColor("#1F4C23"));
                        if (v instanceof Button) {
                            ((Button) v).setTextColor(Color.parseColor("#FFFFFF"));
                            ((Button) v).setText("GO TO BAG");
                            Toast.makeText(WallDecorProductDetails.this, "Successfully added to bag", Toast.LENGTH_SHORT).show();
                            intent = new Intent(WallDecorProductDetails.this, MyBag.class);
                            intent.putExtra(PRODUCT_DETAIL_KEY, wallDecor);
                            intent.putExtra(PRODUCT_QTY_VALUE, qtyValue);
                        }

                        showingFirst = false;
                    } else {

                        startActivity(intent);
                        showingFirst = true;
                    }

                }
            }
        };


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
        bAddToBag = (Button)findViewById(R.id.b_add_to_bag);
    }

    private void setValueInViews(){
        tvProductId.setText(productId);
        tvProductName.setText(name);
        tvProductPrice.setText(walldecorPrice);
        tvProductSummary.setText(walldecorDescription);
        tvLengthHeaderValue.setText(walldecorLength);
        tvSizeHeaderValue.setText(walldecorSize);
        tvMaterialHeaderValue.setText(walldecorMaterial);
        tvFeaturesHeaderValue.setText(walldecorFeatures);
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
                Toast.makeText(WallDecorProductDetails.this, "Products in wishlist", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_shopping_cart:
                Toast.makeText(WallDecorProductDetails.this, "Products in bag", Toast.LENGTH_SHORT).show();
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

    //-----------------------------------------------------

    public class WallDecorRelatedProducts  {

        private WallDecorRelatedProductsAdapter adapter;

        public WallDecorRelatedProducts(){

        }

        public  void recyclerViewRelatedProducts(RecyclerView recyclerView1){

            adapter = new WallDecorRelatedProductsAdapter(WallDecorProductDetails.this, wallDecorRelatedProductsList);


            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(WallDecorProductDetails.this, LinearLayoutManager.HORIZONTAL, false);

            recyclerView1.setLayoutManager(mLayoutManager);

            // adding custom divider line with padding 16dp
            recyclerView1.addItemDecoration(new HomePageItemDecoration(WallDecorProductDetails.this, LinearLayoutManager.HORIZONTAL));

            recyclerView1.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }

    }
}
