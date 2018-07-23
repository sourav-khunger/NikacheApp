package com.doozycod.nikache.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doozycod.nikache.PojoClasses.CouponCard;
import com.doozycod.nikache.R;

import java.util.ArrayList;

/**
 * Created by sunilkumar on 23/07/18.
 */

public class CouponsRecyclerViewAdapter extends RecyclerView.Adapter<CouponsRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<CouponCard> couponCardArrayList;

    @Override
    public CouponsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupons_card, parent, false);

        return new CouponsRecyclerViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CouponsRecyclerViewAdapter.MyViewHolder holder, int position) {
        CouponCard couponCard = couponCardArrayList.get(position);
        holder.couponTitle.setText(couponCard.getCouponLabel());
        holder.couponDescription.setText(couponCard.getCouponDescription());
    }

    @Override
    public int getItemCount() {
        return couponCardArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView couponTitle, couponDescription;

        public MyViewHolder(View itemView) {
            super(itemView);
            couponTitle = (TextView) itemView.findViewById(R.id.coupons_label);
            couponDescription = (TextView) itemView.findViewById(R.id.tv_coupons_desc);
        }
    }

    public CouponsRecyclerViewAdapter(Context mContext, ArrayList<CouponCard> couponCardArrayList){
        this.mContext = mContext;
        this.couponCardArrayList = couponCardArrayList;
    }

}
