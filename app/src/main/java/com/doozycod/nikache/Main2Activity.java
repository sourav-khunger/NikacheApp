package com.doozycod.nikache;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.doozycod.nikache.PojoClasses.Contacts;

public class Main2Activity extends AppCompatActivity {

    private Contacts contact;
    TextView tvName,tvEmail,tvMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvName = (TextView)findViewById(R.id.tv_name);
        tvEmail = (TextView)findViewById(R.id.tv_email);
        tvMobile = (TextView)findViewById(R.id.tv_mobile);

        Intent i = getIntent();

        String name = i.getExtras().getString("contact_name");
        tvName.setText(name);
        String email = i.getExtras().getString("contact_email_id");
        tvEmail.setText(email);
        String mobile = i.getExtras().getString("contact_mobile_no");
        tvMobile.setText(mobile);

        Spannable text = new SpannableString(getSupportActionBar().getTitle());
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.app_title)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(text);


    }
}
