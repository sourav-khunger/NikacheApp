package com.doozycod.nikache.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doozycod.nikache.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;


public class WalletFragment extends Fragment implements PaymentResultListener {


    View view;
    Button bAddMoney;
    EditText etAddMoneyValue;
    private static final String TAG = WalletFragment.class.getSimpleName();
    int moneyInPaise;
    String moneyToBeAddedInWallet;

    public WalletFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_wallet, container, false);

        initViews();
        setClickListener();





         /*
         To ensure faster loading of the Checkout form,
          call this method as early as possible in your checkout flow.
         */
        Checkout.preload(getActivity());

        return view;
    }

    private void initViews(){
        bAddMoney = (Button)view.findViewById(R.id.b_add_money);
        etAddMoneyValue = (EditText)view.findViewById(R.id.et_add_money_value);
    }

    private void setClickListener(){
        bAddMoney.setOnClickListener(addMoneyClickListener);
    }

    View.OnClickListener addMoneyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(etAddMoneyValue.getText() != null){
                moneyToBeAddedInWallet = etAddMoneyValue.getText().toString();
                int money = Integer.parseInt(moneyToBeAddedInWallet);
                moneyInPaise = money*100;
                startPayment();}
        }
    };

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = getActivity();

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
//            options.put("name", "Razorpay Corp");
            options.put("name", "NIKACHE");
//            options.put("description", "Demoing Charges");
            options.put("description", "PAY AMOUNT");
            //You can omit the image option to fetch the image from dashboard
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("image", "https://lh3.googleusercontent.com/VayZ8j3x_l_1kdQAbLFbKfWOQoxCzGfF1eXI4Ud2Z_YXqcHMjxJ0hL4QEw60dE-5uBQSmvw=s85");
            options.put("currency", "INR");
            options.put("amount", String.valueOf(moneyInPaise));

            JSONObject preFill = new JSONObject();
            preFill.put("email", "tsunil28k@gmail.com");
            preFill.put("contact", "8084694717");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        try {
            Toast.makeText(getActivity(), "Payment Successful: " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String s) {

        try {
            Toast.makeText(getActivity(), "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
}
