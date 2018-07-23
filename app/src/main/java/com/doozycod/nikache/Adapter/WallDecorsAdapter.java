package com.doozycod.nikache.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doozycod.nikache.PojoClasses.WallDecor;
import com.doozycod.nikache.R;
import com.doozycod.nikache.sqliteDatabase.NikacheContract;

import java.util.List;

public class WallDecorsAdapter extends RecyclerView.Adapter<WallDecorsAdapter.MyViewHolder>{

    private Context mContext;
    private List<WallDecor> wallDecorList;
    private final String TAG = "Price Value: ";
    private final String WISHLISTSTATUS = "Wishlist value : ";
    private final String ISFAVOURITE = "IsFavourite value: ";

    boolean wishlistStatus;

    WallDecor wallDecor;
    int itemPosition;



//    public interface Callbacks{
//        void wallDecorInfo(int position);
//    }


    public WallDecorsAdapter(Context mContext, List<WallDecor> wallDecorList){

        this.mContext = mContext;
        this.wallDecorList = wallDecorList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wall_decor_card, parent, false);
        return new MyViewHolder(itemView);
    }

//    View.OnClickListener mOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            mCallbacks.wallDecorInfo(itemPosition);
//        }
//    };

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final WallDecor currentWallDecor = wallDecorList.get(position);
        itemPosition = position;
        wallDecor = currentWallDecor;

        Log.i(TAG,String.valueOf(wallDecor.getWallDecorPrice()));
        holder.title.setText(wallDecor.getName());
        holder.count.setText("Rs. " + wallDecor.getWallDecorPrice());
        Glide.with(mContext).load(wallDecor.getThumbnail()).into(holder.thumbnail);

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
                    mContext.getContentResolver().delete(NikacheContract.ProductEntry.CONTENT_URI, NikacheContract.ProductEntry.COLUMN_PRODUCT_ID + " = " + wallDecor.getProductId(), null);

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
                            wallDecor.getProductId());
                    productValues.put(NikacheContract.ProductEntry.COLUMN_PRODUCT_NAME,
                            wallDecor.getName());
                    productValues.put(NikacheContract.ProductEntry.COLUMN_PRODUCT_PRICE,
                            wallDecor.getWallDecorPrice());
                    productValues.put(NikacheContract.ProductEntry.COLUMN_PRODUCT_IMAGE_PATH,
                            wallDecor.getThumbnail());
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
                NikacheContract.ProductEntry.COLUMN_PRODUCT_ID + " = " + wallDecor.getProductId(), null, null);



        if (wallDecorCursor != null && wallDecorCursor.moveToFirst()) {
            wallDecorCursor.close();
            return true;
        } else {
            return false;
        }
    }

//    public void add(Cursor cursor) {
//        wallDecorList.clear();
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                String cName = cursor.getString(NikacheContract.ProductEntry.COL_PRODUCT_NAME);
//                int cPrice = cursor.getInt(NikacheContract.ProductEntry.COL_PRODUCT_PRICE);
//                int cThumbnailId = cursor.getInt(NikacheContract.ProductEntry.COL_PRODUCT_IMAGE_PATH);
//
//                WallDecor wallDecor = new WallDecor(cName, cPrice, cThumbnailId);
//                wallDecorList.add(wallDecor);
//            } while (cursor.moveToNext());
//        }
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {
        return wallDecorList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title, count;
        public ImageView thumbnail,lFavourite, lUnFavourite;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            count = (TextView) itemView.findViewById(R.id.count);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            lFavourite = (ImageView)itemView.findViewById(R.id.mark_as_favorite);
            lUnFavourite = (ImageView)itemView.findViewById(R.id.remove_from_favorites);

        }


    }


}
