package com.doozycod.nikache.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doozycod.nikache.PojoClasses.CustomerFeedback;
import com.doozycod.nikache.R;

import java.util.List;


public class CustomerFeedbackAdapter extends RecyclerView.Adapter<CustomerFeedbackAdapter.MyViewHolder> {

    private Context mContext;
    private List<CustomerFeedback> customerFeedbackList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card2, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        CustomerFeedback customerFeedback = customerFeedbackList.get(position);

        holder.customerFeed.setText(customerFeedback.getCustomerFeedback());

        holder.customerNam.setText("@" + customerFeedback.getCustomerName());

    }

    @Override
    public int getItemCount() {
//        int customerFeedListSize = customerFeedbackList.size();
//        String customerFeedListSizeInString = String.valueOf(customerFeedbackList.size());
//        Toast.makeText(mContext,categorySizeInString,Toast.LENGTH_LONG).show();
        return customerFeedbackList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView customerFeed,customerNam;

        public MyViewHolder(View itemView) {
            super(itemView);

            customerFeed = (TextView) itemView.findViewById(R.id.tv_customer_feedback);
            customerNam = (TextView) itemView.findViewById(R.id.tv_customer_name);

        }
    }

    public CustomerFeedbackAdapter(Context mContext, List<CustomerFeedback> customerFeedbackList){
        this.mContext = mContext;
        this.customerFeedbackList = customerFeedbackList;
    }


}

