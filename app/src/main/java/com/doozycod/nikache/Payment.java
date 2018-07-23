package com.doozycod.nikache;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.doozycod.expandablecardview.ExpandableCardView;

public class Payment extends AppCompatActivity {

    ActionBar actionBar;
    View paymentActionBar;
    ImageView backArrowPaymentScreen;
    ImageView creditCardArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
//        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        paymentActionBar = getLayoutInflater().inflate(R.layout.payment_title, null);
        actionBar.setCustomView(paymentActionBar,
                new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT
//                        ,
//                        Gravity.CENTER

                ));



        backArrowPaymentScreen = (ImageView) paymentActionBar.findViewById(R.id.back_arrow_payment_screen);

        backArrowPaymentScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Payment.this, OrderSummary.class);
                startActivity(intent);
            }
        });

        ExpandableCardView card1 = (ExpandableCardView) findViewById(R.id.creditcard);
        creditCardArrow = card1.findViewById(R.id.arrow);
        creditCardArrow.setVisibility(View.INVISIBLE);
//        card1.setOnExpandedListener(new ExpandableCardView.OnExpandedListener() {
//            @Override
//            public void onExpandChanged(View v, boolean isExpanded) {
//                if(isExpanded) {
//                    creditCardArrow.setVisibility(View.INVISIBLE);
//
//                }
//                else {
//                    creditCardArrow.setVisibility(View.INVISIBLE);
//                }
//            }
//        });

        Button debitCardPayButton = (Button)findViewById(R.id.debit_card_pay_button);
        debitCardPayButton.setText("PAY Rs. 12938");

        Button creditCardPayButton = (Button)findViewById(R.id.credit_card_pay_button);
        creditCardPayButton.setText("PAY Rs. 12938");

        TextView cashOnDeliveryPrice = (TextView)findViewById(R.id.cash_on_delivery_price);
        cashOnDeliveryPrice.setText("12938");

    }



//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // Respond to the action bar's Up/Home button
//            case android.R.id.home:
//                super.onBackPressed();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
}
