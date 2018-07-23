package com.doozycod.nikache.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.doozycod.nikache.MainActivity;
import com.doozycod.nikache.R;

public class OrdersFragment extends Fragment {

    Button orderButton;
    ActionBar actionBar;

    public OrdersFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        // Setting the color of title on action bar
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);
        //-----------------------------

        orderButton = (Button)view.findViewById(R.id.iv_order_button1);
        orderButton.setOnClickListener(orderButtonClickListener);
                return view;
    }

   View.OnClickListener orderButtonClickListener = new View.OnClickListener() {

       @Override
       public void onClick(View v) {
           Intent intent = new Intent(getContext(), MainActivity.class);
           startActivity(intent);
       }
   };



}
