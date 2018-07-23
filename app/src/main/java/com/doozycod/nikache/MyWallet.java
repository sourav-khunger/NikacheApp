package com.doozycod.nikache;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.doozycod.nikache.PojoClasses.StoreCurrentWalletAmt;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MyWallet extends AppCompatActivity implements PaymentResultListener {

    private static final String TAG = MyWallet.class.getSimpleName();
    Button bAddMoney;
    EditText etAddMoneyValue;
    Float moneyInPaise;
    String moneyToBeAddedInWallet;
    ActionBar actionBar;
    View walletAmtActionBar;
    TextView tvWalletMoney;
    Float money;
    public static float moneyOnSuccess;
    public static String TAG_WALLET_MONEY = "currentWalletMoney";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
//        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        walletAmtActionBar = getLayoutInflater().inflate(R.layout.wallet_amt_display, null);
        actionBar.setCustomView(walletAmtActionBar,
                new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT
                ));

        initViews();
        setClickListener();

        moneyOnSuccess = StoreCurrentWalletAmt.getDefaults(TAG_WALLET_MONEY,getApplicationContext());

        tvWalletMoney = (TextView) walletAmtActionBar.findViewById(R.id.tv_wallet_money);
        tvWalletMoney.setText(String.valueOf(moneyOnSuccess));
    }

    private void initViews(){
        bAddMoney = (Button)findViewById(R.id.b_add_money);
        etAddMoneyValue = (EditText)findViewById(R.id.et_add_money_value);

    }

    private void setClickListener(){
        bAddMoney.setOnClickListener(addMoneyClickListener);
    }

    View.OnClickListener addMoneyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            moneyToBeAddedInWallet = etAddMoneyValue.getText().toString();
           if(moneyToBeAddedInWallet.equals("")){
               Toast.makeText(getApplicationContext(),"Please enter the Amount",Toast.LENGTH_SHORT).show();
           }else if(!(moneyToBeAddedInWallet.equals(""))){
               money = Float.parseFloat(moneyToBeAddedInWallet);
                moneyInPaise = money*100;
                startPayment();
           }
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
            options.put("amount", String.valueOf(moneyInPaise));

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


    @Override
    public void onPaymentSuccess(String s) {
        try {
            moneyOnSuccess += money;

            StoreCurrentWalletAmt.setDefaults(TAG_WALLET_MONEY, moneyOnSuccess, getApplicationContext());

            moneyOnSuccess = StoreCurrentWalletAmt.getDefaults(TAG_WALLET_MONEY,getApplicationContext());

            tvWalletMoney.setText(String.valueOf(moneyOnSuccess));
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
