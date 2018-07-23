package com.doozycod.nikache;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.doozycod.HelperClass.InputValidation;
import com.doozycod.nikache.sqliteDatabase.NikacheContract;
import com.doozycod.nikache.sqliteDatabase.NikacheDBHelper;

public class SignUpPage extends AppCompatActivity implements View.OnClickListener{

    View nikacheIconOnActionBar;
    ActionBar actionBar;

    private ScrollView scrollView;
    private EditText userNameOnSignUp;
    private EditText userEmailOnSignUp;
    private EditText userPasswordOnSignUp;
    private EditText userMobileNoOnSignUp;
    RadioGroup radioGroup;
    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;
    private Button buttonRegister;
    String gender="";
    String userFullName;
    String firstName;
    String lastName;
    String userEmail;
    String userPassword;
    String userMobile;
    private NikacheDBHelper mDbHelper;
    public static Uri newUri;
    public static boolean statusLogin;


    private final AppCompatActivity activity = SignUpPage.this;

    private AppCompatTextView appCompatTextViewLoginLink;
    private InputValidation inputValidation;



    int i = 0;
    Uri currentPetUri;
    private static final int USER_LOADER = 0;
    Bundle args;
    String firstNameOfUser;
    String lastNameOfUser;
    String emailOfUser;
    String passwordOfUser;
    String mobileNoOfUser;
    String genderOfUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        args = savedInstanceState;
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nikacheIconOnActionBar = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(nikacheIconOnActionBar);
        actionBar.setTitle("Sign Up");
        nikacheIconOnActionBar.setVisibility(View.GONE);

        initViews();
        initListeners();

//        currentPetUri = NikacheContract.UserEntry.buildUserUri(1);
//        mDbHelper = new NikacheDBHelper(this);

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        mDbHelper = new NikacheDBHelper(this);

        scrollView = (ScrollView) findViewById(R.id.view_root_signup_page);
        userNameOnSignUp = (EditText) findViewById(R.id.et_name_on_sign_up_screen);
        userEmailOnSignUp = (EditText) findViewById(R.id.et_email_on_sign_up_screen);
        userPasswordOnSignUp = (EditText) findViewById(R.id.et_password_on_sign_up_screen);
        userMobileNoOnSignUp = (EditText) findViewById(R.id.et_mobile_on_sign_up_screen);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group_sign_up);
        maleRadioButton = (RadioButton)findViewById(R.id.male_radio_button);
        femaleRadioButton = (RadioButton)findViewById(R.id.female_radio_button);
        buttonRegister = (Button) findViewById(R.id.button_sign_up_signup_screen);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.male_radio_button:
                        gender = "Male";
                        break;
                    case R.id.female_radio_button:
                        gender = "Female";
                        break;

                }
            }
        });

    }

    //------------------------**************------------------------------

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {

        buttonRegister.setOnClickListener(this);


    }

    //------------------------**************------------------------------

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_sign_up_signup_screen:


                postDataToSQLite();
//                displayDatabaseInfo();}
                break;

        }
    }

    //------------------------**************------------------------------

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {

        userFullName = userNameOnSignUp.getText().toString().trim();
        int i = userFullName.indexOf(" ");
        firstName = userFullName.substring(0, i);
        lastName = userFullName.substring(i+1);
        userEmail = userEmailOnSignUp.getText().toString().trim();
        userPassword = userPasswordOnSignUp.getText().toString().trim();
        userMobile = userMobileNoOnSignUp.getText().toString().trim();

        if (TextUtils.isEmpty(firstName) ||
                TextUtils.isEmpty(lastName) || TextUtils.isEmpty(userEmail) ||
                TextUtils.isEmpty(userPassword) || TextUtils.isEmpty(userMobile)
                && TextUtils.isEmpty(gender)) {
            // Since no fields were modified, we can return early without creating a new user.
            // No need to create ContentValues and no need to do any ContentProvider operations.
            return;
        }



        // Gets the data repository in write mode
//            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(NikacheContract.UserEntry.COLUMN_USER_FIRST_NAME, firstName);
            values.put(NikacheContract.UserEntry.COLUMN_USER_LAST_NAME, lastName);
            values.put(NikacheContract.UserEntry.COLUMN_USER_EMAIL, userEmail);
            values.put(NikacheContract.UserEntry.COLUMN_USER_PASSWORD, userPassword);
            values.put(NikacheContract.UserEntry.COLUMN_USER_MOBILE, userMobile);
            values.put(NikacheContract.UserEntry.COLUMN_USER_GENDER, gender);

        newUri = getContentResolver().insert(NikacheContract.UserEntry.CONTENT_URI, values);

        // Show a toast message depending on whether or not the insertion was successful.
        if (newUri == null) {

            Snackbar.make(scrollView, "Registration Unsuccessful", Snackbar.LENGTH_LONG).show();

            // If the new content URI is null, then there was an error with insertion.
//            Toast.makeText(this, getString(R.string.editor_insert_pet_failed),
//                    Toast.LENGTH_SHORT).show();
        } else {
            // Snack Bar to show success message that record saved successfully
            Snackbar.make(scrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();

            // Otherwise, the insertion was successful and we can display a toast.
//            Toast.makeText(this, getString(R.string.editor_insert_pet_successful),
//                    Toast.LENGTH_SHORT).show();
        }

        SplashScreen.isLogin = true;
        statusLogin = SplashScreen.isLogin;

        if(SignUpPage.statusLogin == true) {

            SharedPreferences sharedPref = SignUpPage.this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("LoginStatus", true);
            editor.commit();

            statusLogin = sharedPref.getBoolean("LoginStatus", false);

        }

    }

    //------------------------**************------------------------------

//    private void displayDatabaseInfo() {
//        // Create and/or open a database to read from it
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();
//
//        String[] projection = {
//                NikacheContract.UserEntry.COLUMN_USER_ID,
//                NikacheContract.UserEntry.COLUMN_USER_FIRST_NAME,
//                NikacheContract.UserEntry.COLUMN_USER_LAST_NAME,
//                NikacheContract.UserEntry.COLUMN_USER_EMAIL,
//                NikacheContract.UserEntry.COLUMN_USER_PASSWORD,
//                NikacheContract.UserEntry.COLUMN_USER_MOBILE,
//                NikacheContract.UserEntry.COLUMN_USER_GENDER};
//
//        Cursor cursor = db.query(
//                NikacheContract.UserEntry.TABLE_NAME,   // The table to query
//                projection,            // The columns to return
//                null,                  // The columns for the WHERE clause
//                null,                  // The values for the WHERE clause
//                null,                  // Don't group the rows
//                null,                  // Don't filter by row groups
//                null);                  // The sort order
//
//
//        try {
//
//            if (cursor == null || cursor.getCount() < 1) {
//                return;
//            }
//
//            if (cursor.moveToFirst()) {
//                // Find the columns of pet attributes that we're interested in
//                int userIdColumnIndex = cursor.getColumnIndex(NikacheContract.UserEntry.COLUMN_USER_ID);
//                int firstNameColumnIndex = cursor.getColumnIndex(NikacheContract.UserEntry.COLUMN_USER_FIRST_NAME);
//                int lastNameColumnIndex = cursor.getColumnIndex(NikacheContract.UserEntry.COLUMN_USER_LAST_NAME);
//                int emailColumnIndex = cursor.getColumnIndex(NikacheContract.UserEntry.COLUMN_USER_EMAIL);
//                int passwordColumnIndex = cursor.getColumnIndex(NikacheContract.UserEntry.COLUMN_USER_PASSWORD);
//                int mobileColumnIndex = cursor.getColumnIndex(NikacheContract.UserEntry.COLUMN_USER_MOBILE);
//                int genderColumnIndex = cursor.getColumnIndex(NikacheContract.UserEntry.COLUMN_USER_GENDER);
//
//                // Extract out the value from the Cursor for the given column index
//                int iD = cursor.getInt(userIdColumnIndex);
//                firstNameOfUser = cursor.getString(firstNameColumnIndex);
//                lastNameOfUser = cursor.getString(lastNameColumnIndex);
//                emailOfUser = cursor.getString(emailColumnIndex);
//                passwordOfUser = cursor.getString(passwordColumnIndex);
//                mobileNoOfUser = cursor.getString(mobileColumnIndex);
//                genderOfUser = cursor.getString(genderColumnIndex);
//
//
////                getSupportFragmentManager().beginTransaction().add(R.id.content_frame, MyAccountFragment.newInstance(firstNameOfUser,lastNameOfUser, emailOfUser),MainActivity.TAG_MY_ACCOUNT).commit();
//
//
//
//
////                MyAccountFragment myAccountFragment = new MyAccountFragment();
////                myAccountFragment.setArguments(argss);
////                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////                transaction.replace(R.id.content_frame, myAccountFragment);
////
////                transaction.commit();
//            }
//
//
//        }finally {
//            // Always close the cursor when you're done reading from it. This releases all its
//            // resources and makes it invalid.
//            cursor.close();
//        }
//
//    }



    // -----------------------*************-------------------------------

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        userNameOnSignUp.setText(null);
        userEmailOnSignUp.setText(null);
        userPasswordOnSignUp.setText(null);
        userMobileNoOnSignUp.setText(null);
        maleRadioButton.setChecked(false);
        femaleRadioButton.setChecked(false);
    }

    //------------------------**************------------------------------





    //----------------------***************------------------------------

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

}
