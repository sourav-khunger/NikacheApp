package com.doozycod.nikache.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doozycod.nikache.MyBag;
import com.doozycod.nikache.PojoClasses.WallDecor;
import com.doozycod.nikache.R;

import java.util.ArrayList;

public class MyBagProductsAdapter extends RecyclerView.Adapter<MyBagProductsAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<WallDecor> wallDecorProductsList;
    WallDecor selectedWallDecor;
//    String qtyVal;

    @Override
    public MyBagProductsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mybag_recyclerview_item, parent, false);

        return new MyBagProductsAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyBagProductsAdapter.MyViewHolder holder, int position) {
        selectedWallDecor = wallDecorProductsList.get(position);
        holder.productName.setText(selectedWallDecor.getName());
//        String qtyVal = selectedWallDecor.getProductQtyValue();
        holder.tvQtyValue.setText(MyBag.qtyValue);
        holder.tvProductPrice.setText(selectedWallDecor.getWallDecorPrice());
        Glide.with(mContext).load(selectedWallDecor.getThumbnail()).into(holder.thumbnail);

    }


    @Override
    public int getItemCount() {
        return wallDecorProductsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView productName, tvQtyValue, tvProductPrice;
        public ImageView thumbnail;


        public MyViewHolder(View itemView) {
            super(itemView);

            thumbnail = (ImageView) itemView.findViewById(R.id.iv_selected_product_image_in_mybag);
            productName = (TextView) itemView.findViewById(R.id.tv_selected_product_name_in_mybag);
            tvQtyValue = (TextView) itemView.findViewById(R.id.tv_qty_value);
            tvProductPrice = (TextView)itemView.findViewById(R.id.tv_product_price_mybag);


        }
    }

    public MyBagProductsAdapter(Context mContext, ArrayList<WallDecor> wallDecorProductsList){
        this.mContext = mContext;
        this.wallDecorProductsList = wallDecorProductsList;
    }
}
