package com.doozycod.nikache;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddAddress extends AppCompatActivity {

    View nikacheIconOnActionBar;
    ActionBar actionBar;
    EditText etNameVal,etPinCodeVal,etCityValue,etStateValue,etAddressValue,etEmailValue,etMobileValue;
    TextView tvLabelName,tvLabelPinCode,tvCityHeader,tvStateHeader,tvAddressHeader,tvEmailHeader,tvMobileHeader;
    Button bAddAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nikacheIconOnActionBar = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(nikacheIconOnActionBar);
        actionBar.setTitle("ADD ADDRESS");
        nikacheIconOnActionBar.setVisibility(View.GONE);

        initViews();

//        etNameVal.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                tvLabelName.setText("Name*");
//
////                if(MotionEvent.ACTION_UP == event.getAction()){
////                    Toast.makeText(AddAddress.this,"HEllo",Toast.LENGTH_SHORT).show();
////                }
//                return false;
//            }
//        });

//        etNameVal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus){
//                    tvLabelName.setText("Name*");
//                    etNameVal.setHint("");}
//                else{
//                    tvLabelName.setText("Name*");
//                    etNameVal.setHint("");
//                }
//
//            }
//        });

        etNameVal.setOnFocusChangeListener(editTextOnFocusChangeListner);
        etPinCodeVal.setOnFocusChangeListener(editTextOnFocusChangeListner);
        etCityValue.setOnFocusChangeListener(editTextOnFocusChangeListner);
        etStateValue.setOnFocusChangeListener(editTextOnFocusChangeListner);
        etAddressValue.setOnFocusChangeListener(editTextOnFocusChangeListner);
        etEmailValue.setOnFocusChangeListener(editTextOnFocusChangeListner);
        etMobileValue.setOnFocusChangeListener(editTextOnFocusChangeListner);

    }

    View.OnFocusChangeListener editTextOnFocusChangeListner = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if( etNameVal.getId() == v.getId() ){
                if (hasFocus){
                    tvLabelName.setText("Name*");
                    etNameVal.setHint("");}
                else{
                    tvLabelName.setText("Name*");
                    etNameVal.setHint("");
                }
            }else if(etPinCodeVal.getId() == v.getId()){
                if (hasFocus){
                    tvLabelPinCode.setText("Pin Code*");
                    etPinCodeVal.setHint("");}
                else{
                    tvLabelPinCode.setText("Pin Code*");
                    etPinCodeVal.setHint("");
                }
            }else if(etCityValue.getId() == v.getId()){
                if (hasFocus){
                    tvCityHeader.setText("City*");
                    etCityValue.setHint("");}
                else{
                    tvCityHeader.setText("");
                    etCityValue.setHint("City*");
                }
            }else if(etStateValue.getId() == v.getId()){
                if (hasFocus){
                    tvStateHeader.setText("State*");
                    etStateValue.setHint("");}
                else{
                    tvStateHeader.setText("");
                    etStateValue.setHint("State*");
                }
            }else if(etAddressValue.getId() == v.getId()){
                if (hasFocus){
                    tvAddressHeader.setText("Address*");
                    etAddressValue.setHint("");}
                else{
                    tvAddressHeader.setText("Address*");
                    etAddressValue.setHint("");
                }
            }else if(etEmailValue.getId() == v.getId()){
                if (hasFocus){
                    tvEmailHeader.setText("Email*");
                    etEmailValue.setHint("");}
                else{
                    tvEmailHeader.setText("Email*");
                    etEmailValue.setHint("");
                }
            }else if(etMobileValue.getId() == v.getId()){
                if (hasFocus){
                    tvMobileHeader.setText("Mobile*");
                    etMobileValue.setHint("");}
                else{
                    tvMobileHeader.setText("");
                    etMobileValue.setHint("Mobile*");
                }
            }

        }
    };



    public void initViews(){
        tvLabelName = (TextView)findViewById(R.id.tv_label_name);
        etNameVal = (EditText)findViewById(R.id.et_name_value);
        tvLabelPinCode = (TextView)findViewById(R.id.tv_pincode_header);
        etPinCodeVal = (EditText)findViewById(R.id.et_pincode_value);
        tvCityHeader = (TextView)findViewById(R.id.tv_city_header);
        etCityValue = (EditText)findViewById(R.id.et_city_value);
        tvStateHeader = (TextView)findViewById(R.id.tv_state_header);
        etStateValue = (EditText)findViewById(R.id.et_state_value);
        tvAddressHeader = (TextView)findViewById(R.id.tv_address_header);
        etAddressValue = (EditText)findViewById(R.id.et_address_value);
        tvEmailHeader = (TextView)findViewById(R.id.tv_email_header);
        etEmailValue = (EditText)findViewById(R.id.et_email_value);
        tvMobileHeader = (TextView)findViewById(R.id.tv_mobile_header);
        etMobileValue = (EditText)findViewById(R.id.et_mobile_value);
        bAddAddress = (Button)findViewById(R.id.b_add_address);

        bAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAddress.this,OrderSummary.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_address_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                super.onBackPressed();
                return true;

            case R.id.action_search: {

                Toast.makeText(AddAddress.this,"Seacrh", Toast.LENGTH_SHORT).show();
                return true;
            }

            case R.id.action_shopping_cart: {

                Intent intent = new Intent(AddAddress.this, MyBag.class);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
