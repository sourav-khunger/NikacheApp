package com.doozycod.nikache.Fragment;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doozycod.nikache.Adapter.WishListAdapter;
import com.doozycod.nikache.PojoClasses.WishlistAddedProducts;
import com.doozycod.nikache.R;
import com.doozycod.nikache.sqliteDatabase.NikacheContract;
import com.doozycod.nikache.sqliteDatabase.NikacheDBHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WishListFragment extends Fragment {


    ActionBar actionBar;
    Fragment fragment;
    private RecyclerView rvWallDecorRelatedProducts;
    private ArrayList<WishlistAddedProducts> productList;
    private NikacheDBHelper databaseHelper;
    String productId;
    String productName;
    String productPrice;
    int productImage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);

        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        // Setting the color of title on action bar
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);
        //-----------------------------

        productList = new ArrayList<>();
        databaseHelper = new NikacheDBHelper(getContext());

        rvWallDecorRelatedProducts = (RecyclerView) view.findViewById(R.id.recycler_view_wishlist);
        WishlistProducts wishlistProducts = new WishlistProducts();
        wishlistProducts.recyclerViewRelatedProducts(rvWallDecorRelatedProducts);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayDatabaseInfo();
    }






    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] projection = {
                NikacheContract.ProductEntry.COLUMN_PRODUCT_ID,
                NikacheContract.ProductEntry.COLUMN_PRODUCT_NAME,
                NikacheContract.ProductEntry.COLUMN_PRODUCT_PRICE,
                NikacheContract.ProductEntry.COLUMN_PRODUCT_IMAGE_PATH
              };

        Cursor cursor = db.query(
                NikacheContract.ProductEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                  // The sort order


        try {

            if (cursor == null || cursor.getCount() < 1) {
                return;
            }

            if (cursor.moveToFirst()) {
                // Find the columns of pet attributes that we're interested in
                int selectedProductIdColumnIndex = cursor.getColumnIndex(NikacheContract.ProductEntry.COLUMN_PRODUCT_ID);
                int selectedProductNameColumnIndex = cursor.getColumnIndex(NikacheContract.ProductEntry.COLUMN_PRODUCT_NAME);
                int selectedProductPriceColumnIndex = cursor.getColumnIndex(NikacheContract.ProductEntry.COLUMN_PRODUCT_PRICE);
                int selectedProductImageColumnIndex = cursor.getColumnIndex(NikacheContract.ProductEntry.COLUMN_PRODUCT_IMAGE_PATH);


                // Extract out the value from the Cursor for the given column index
                int id = cursor.getInt(selectedProductIdColumnIndex);
                productId = String.valueOf(id);
                productName = cursor.getString(selectedProductNameColumnIndex);
                productPrice = cursor.getString(selectedProductPriceColumnIndex);
                String thumbnail = cursor.getString(selectedProductImageColumnIndex);
                productImage = Integer.parseInt(thumbnail);
                WishlistAddedProducts wishlistAddedProducts = new WishlistAddedProducts(productId,productName,productPrice,productImage);
                productList.add(wishlistAddedProducts);


            }
        }finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

    }

    //---------------------------------------------------

    public class WishlistProducts  {

        private WishListAdapter adapter;

        public WishlistProducts(){

        }

        public  void recyclerViewRelatedProducts(RecyclerView recyclerView1){

            adapter = new WishListAdapter(getContext(), productList);



            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView1.setLayoutManager(mLayoutManager);
            recyclerView1.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));
            recyclerView1.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }

    }

//-------------------------------------------------------

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge){
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);    // item position
            int column = position % spanCount; // item column

            if(includeEdge){
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount;  // (column + 1) * ((1f / spanCount) * spacing)

                if(position < spanCount) {   // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)

                if(position >= spanCount){
                    outRect.top = spacing; // item top
                }
            }

        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp){
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}