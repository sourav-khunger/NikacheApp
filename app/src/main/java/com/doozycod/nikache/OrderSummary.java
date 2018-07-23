package com.doozycod.nikache;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class OrderSummary extends AppCompatActivity implements PaymentResultListener {

    View orderSummaryActionBar;
    ActionBar actionBar;
    ImageView orderSummaryCancelButton;

    CheckBox checkboxOrderSummary;
    TextView tvUserNameOrderSummary,tvUserAdd,tvUserCity,tvUserState,tvUserPincode,tvUserMobile,
            tvUserEmailOrderSummary,tvDefaultAdd,tvChangeAdd,tvSubTotalPrice,tvFreeInMybag,
            tvPayAmtVal;
    Button bProceedToCheckout;
    private static final String TAG = OrderSummary.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
//        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        orderSummaryActionBar = getLayoutInflater().inflate(R.layout.order_summary_cancel_button_on_actionbar, null);
        actionBar.setCustomView(orderSummaryActionBar,
                new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT
//                        ,
//                        Gravity.CENTER

                ));
        orderSummaryCancelButton = (ImageView) orderSummaryActionBar.findViewById(R.id.order_summary_cancel_button);

        orderSummaryCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OrderSummary.this, AddAddress.class);
                startActivity(intent);
            }
        });

        initView();
        setViewsValue();

         /*
         To ensure faster loading of the Checkout form,
          call this method as early as possible in your checkout flow.
         */
        Checkout.preload(getApplicationContext());

    }

    public void initView(){
        checkboxOrderSummary = (CheckBox)findViewById(R.id.checkbox_order_summary);
        tvUserNameOrderSummary = (TextView)findViewById(R.id.tv_user_name_order_summary);
        tvUserAdd = (TextView)findViewById(R.id.tv_user_add);
        tvUserCity = (TextView)findViewById(R.id.tv_user_city);
        tvUserState = (TextView)findViewById(R.id.tv_user_state);
        tvUserPincode = (TextView)findViewById(R.id.tv_user_pincode);
        tvUserMobile = (TextView)findViewById(R.id.tv_user_mobile);
        tvUserEmailOrderSummary = (TextView)findViewById(R.id.tv_user_email_order_summary);
        tvDefaultAdd = (TextView)findViewById(R.id.tv_default_add);
        tvChangeAdd = (TextView)findViewById(R.id.tv_change_add);
        tvChangeAdd.setOnClickListener(changeAddressClickListner);

//        tvSubTotalPrice = (TextView)findViewById(R.id.tv_sub_total_price_order_summary);
//        tvFreeInMybag = (TextView)findViewById(R.id.tv_free_in_order_summary);
//        tvPayAmtVal = (TextView)findViewById(R.id.tv_pay_amt_val_order_summary);
        bProceedToCheckout = (Button)findViewById(R.id.b_proceed_to_checkout_order_summary);
        bProceedToCheckout.setOnClickListener(proceedToCheckoutClickListener);
    }

    View.OnClickListener changeAddressClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(OrderSummary.this, AddAddress.class);
            startActivity(intent);
        }
    };

    View.OnClickListener proceedToCheckoutClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(OrderSummary.this, Payment.class);
//            startActivity(intent);
            if(checkboxOrderSummary.isChecked()){
            startPayment();}
        }
    };


    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

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
            options.put("amount", "169900");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "tsunil28k@gmail.com");
            preFill.put("contact", "8084694717");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }


    public void setViewsValue(){
        tvUserNameOrderSummary.setText("Sunil Kumar");
        tvUserAdd.setText("Indira Colony, Near Shivalik Garden,\nManimajra, Chandigarh");
        tvUserCity.setText("Chandigarh");
        tvUserState.setText("CHANDIGARH");
        tvUserPincode.setText("160601");
        tvUserMobile.setText("8084694717");
        tvUserEmailOrderSummary.setText("tsunil28k@gmail.com");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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


    @Override
    public void onPaymentSuccess(String s) {
        try {
            Toast.makeText(this, "Payment Successful: " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String s) {

        try {
            Toast.makeText(this, "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
}
