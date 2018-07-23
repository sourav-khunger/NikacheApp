package com.doozycod.nikache.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doozycod.nikache.PojoClasses.Contacts;
import com.doozycod.nikache.CustomItemClickListener;
import com.doozycod.nikache.R;

import java.util.ArrayList;

/**
 * Created by sunilkumar on 08/06/18.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    //    private setOnContactClick setOnContactClick;
    ArrayList<Contacts> contactList;
    Context context;
    CustomItemClickListener listener;


    public ContactAdapter(Context context,ArrayList<Contacts> contactList,CustomItemClickListener listener) {

        this.context=context;
        this.contactList=contactList;
        this.listener = listener;

    }

//    public interface setOnContactClick{
//        void onContactClick(int position);
//    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, email, mobile;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            mobile = (TextView) view.findViewById(R.id.mobile);
        }


//        @Override
//        public void onClick(View v) {
//
//                int position = getAdapterPosition();
//            setOnContactClick.onContactClick(position);
//
//
//        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_details, parent, false);


//        final RecyclerView.ViewHolder mViewHolder = new RecyclerView.ViewHolder(itemView);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onItemClick(v, mViewHolder.getPosition());
//            }
//        });
//        return mViewHolder;




        final MyViewHolder mViewHolder = new MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getPosition());
            }
        });
        return mViewHolder;


//        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

//        Toast.makeText(context,contactList.get(position).toString(),Toast.LENGTH_SHORT).show();


        if(contactList != null){


            Contacts currentContact = (Contacts) contactList.get(position);
            String name = currentContact.getName();
            String email = currentContact.getEmail();
            String mobile = currentContact.getMobile();

//            Toast.makeText(context,name,Toast.LENGTH_SHORT).show();

            holder.name.setText(name);
            holder.email.setText(email);
            holder.mobile.setText(mobile);
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }



}
