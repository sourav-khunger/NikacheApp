package com.doozycod.nikache.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doozycod.nikache.PojoClasses.BrasDecor;
import com.doozycod.nikache.R;
import com.doozycod.nikache.sqliteDatabase.NikacheContract;

import java.util.List;


public class BrasDecorAdapter extends RecyclerView.Adapter<BrasDecorAdapter.MyViewHolder> {

    private Context mContext;
    private List<BrasDecor> brasDecorList;
    BrasDecor brasDecor;
    private final String TAG = "Price Value: ";
    private final String WISHLISTSTATUS = "Wishlist value : ";
    boolean wishlistStatus;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wall_decor_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        brasDecor = brasDecorList.get(position);
        holder.title.setText(brasDecor.getName());
        holder.count.setText("Rs. " + brasDecor.getBrassDecorPrice());

        Glide.with(mContext).load(brasDecor.getThumbnail()).into(holder.thumbnail);
//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showPopupMenu(holder.overflow);
//            }
//        });


        holder.lFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removeFromFavorites();

                wishlistStatus = isFavorite();

                if (wishlistStatus) {
                    Log.i(WISHLISTSTATUS,wishlistStatus + "");

                    holder.lFavourite.setVisibility(View.GONE);
                    holder.lUnFavourite.setVisibility(View.VISIBLE);

                } else {
                    Log.i(WISHLISTSTATUS,wishlistStatus + "");

                    holder.lFavourite.setVisibility(View.VISIBLE);
                    holder.lUnFavourite.setVisibility(View.GONE);

                }


            }
        });

        holder.lUnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                markAsFavorite();

                wishlistStatus = isFavorite();

                if (wishlistStatus) {
                    Log.i(WISHLISTSTATUS,wishlistStatus + "");
                    holder.lFavourite.setVisibility(View.GONE);
                    holder.lUnFavourite.setVisibility(View.VISIBLE);



                } else {
                    Log.i(WISHLISTSTATUS,wishlistStatus + "");
                    holder.lFavourite.setVisibility(View.VISIBLE);
                    holder.lUnFavourite.setVisibility(View.GONE);
                }

            }
        });

    }

    public void removeFromFavorites() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                if (isFavorite()) {
//                    mContext.getContentResolver().delete(NikacheContract.ProductEntry.CONTENT_URI, NikacheContract.ProductEntry.COLUMN_PRODUCT_ID + " =? ", new String[]{wallDecor.getProductId()});
                    mContext.getContentResolver().delete(NikacheContract.ProductEntry.CONTENT_URI, NikacheContract.ProductEntry.COLUMN_PRODUCT_ID + " = " + brasDecor.getProductId(), null);

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                Toast.makeText(mContext, "Product removed from wishlist", Toast.LENGTH_SHORT).show();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void markAsFavorite() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                if (!isFavorite()) {

                    ContentValues productValues = new ContentValues();
                    productValues.put(NikacheContract.ProductEntry.COLUMN_PRODUCT_ID,
                            brasDecor.getProductId());
                    productValues.put(NikacheContract.ProductEntry.COLUMN_PRODUCT_NAME,
                            brasDecor.getName());
                    productValues.put(NikacheContract.ProductEntry.COLUMN_PRODUCT_PRICE,
                            brasDecor.getBrassDecorPrice());
                    productValues.put(NikacheContract.ProductEntry.COLUMN_PRODUCT_IMAGE_PATH,
                            brasDecor.getThumbnail());
                    mContext.getContentResolver().insert(NikacheContract.ProductEntry.CONTENT_URI, productValues);

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                Toast.makeText(mContext, "Product added to wishlist", Toast.LENGTH_SHORT).show();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private boolean isFavorite() {

//        Cursor wallDecorCursor = mContext.getContentResolver().query(
//                NikacheContract.ProductEntry.CONTENT_URI,
//                new String[]{NikacheContract.ProductEntry.COLUMN_PRODUCT_ID},
//                NikacheContract.ProductEntry.COLUMN_PRODUCT_ID + " =? ", new String[]{wallDecor.getProductId()}, null);

        Cursor wallDecorCursor = mContext.getContentResolver().query(
                NikacheContract.ProductEntry.CONTENT_URI,
                new String[]{NikacheContract.ProductEntry.COLUMN_PRODUCT_ID},
                NikacheContract.ProductEntry.COLUMN_PRODUCT_ID + " = " + brasDecor.getProductId(), null, null);



        if (wallDecorCursor != null && wallDecorCursor.moveToFirst()) {
            wallDecorCursor.close();
            return true;
        } else {
            return false;
        }
    }

    private void showPopupMenu(View view){
        PopupMenu popup = new PopupMenu(mContext,view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_wall_decor, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener{

        public MyMenuItemClickListener(){

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return brasDecorList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title, count;
        public ImageView thumbnail, lFavourite, lUnFavourite;


        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            count = (TextView) itemView.findViewById(R.id.count);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            lFavourite = (ImageView)itemView.findViewById(R.id.mark_as_favorite);
            lUnFavourite = (ImageView)itemView.findViewById(R.id.remove_from_favorites);

        }
    }

    public BrasDecorAdapter(Context mContext, List<BrasDecor> brasDecorList){
        this.mContext = mContext;
        this.brasDecorList = brasDecorList;
    }


}


