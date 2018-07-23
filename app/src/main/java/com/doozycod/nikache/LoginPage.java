package com.doozycod.nikache;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.doozycod.HelperClass.InputValidation;
import com.doozycod.nikache.sqliteDatabase.NikacheContract;
import com.doozycod.nikache.sqliteDatabase.NikacheDBHelper;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

public class LoginPage extends AppCompatActivity {

    private final AppCompatActivity activity = LoginPage.this;

    private ScrollView scrollView;

    private TextView textInputLayoutEmail;
    private TextView textInputLayoutPassword;

    private EditText textInputEditTextEmail;
    private EditText textInputEditTextPassword;

    private InputValidation inputValidation;
    private NikacheDBHelper databaseHelper;
    View nikacheIconOnActionBar;
    ActionBar actionBar;
    Button signUpButtonInLoginScreen;
    Button logInButtonInLoginScreen;
    String email;
    String password;
//    public static boolean statusLogin;

    // -----****Login From Facebook****----------

    public static int APP_REQUEST_CODE = 1;
    TextView skipNow;
    LoginButton loginButton;
    //callbackManager gonna help us handle the result of our login attempt.
    CallbackManager callbackManager;

    // ------*****Login From Facebook ****----------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        actionBar = getSupportActionBar();
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nikacheIconOnActionBar = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(nikacheIconOnActionBar);
        actionBar.setTitle("Log In");
        nikacheIconOnActionBar.setVisibility(View.GONE);
        textInputEditTextEmail = (EditText) findViewById(R.id.et_email_on_login_screen);
        textInputEditTextPassword = (EditText) findViewById(R.id.et_password_on_login_screen);
        logInButtonInLoginScreen = (Button)findViewById(R.id.button_login_on_login_screen);
        logInButtonInLoginScreen.setOnClickListener(loginButtonClickListener);
        signUpButtonInLoginScreen = (Button)findViewById(R.id.button_sign_up_login_screen);
        signUpButtonInLoginScreen.setOnClickListener(onClickGoToSignUpScreen);

        databaseHelper = new NikacheDBHelper(this);
        displayDatabaseInfo();

//        Toast.makeText(this,email + password,Toast.LENGTH_LONG).show();

        //-------------------------------
//        initViews();
////        initListeners();
//        initObjects();

        // -----****Login From Facebook****----------


//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.doozycod.nikache",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
//
//
////        skipNow = (TextView)findViewById(R.id.tv_skip_now);
////        skipNow.setPaintFlags(skipNow.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//
//        FontHelper.setCustomTypeface(findViewById(R.id.view_root));
//
//        // initialize LoginButton
//        loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
//        loginButton.setReadPermissions("email");
//
//        // Login Button callback registration
//        // initialize callbackManager
//        callbackManager = CallbackManager.Factory.create();
//        // Register a callback for the LoginButton.
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                launchAccountActivity();
//            }
//
//            @Override
//            public void onCancel() {
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // display error
//                String toastMessage = exception.getMessage();
//                Toast.makeText(LoginPage.this, toastMessage, Toast.LENGTH_LONG).show();
//            }
//        });
//
//        // check for an existing access token
//        AccessToken accessToken = AccountKit.getCurrentAccessToken();
//        com.facebook.AccessToken loginToken = com.facebook.AccessToken.getCurrentAccessToken();
//        if (accessToken != null || loginToken != null) {
//            // if previously logged in, proceed to the account activity
//            launchAccountActivity();
//        }

        // ------*****Login From Facebook ****----------

    }

    //--------------------------

    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] projection = {
                NikacheContract.UserEntry.COLUMN_USER_ID,
                NikacheContract.UserEntry.COLUMN_USER_FIRST_NAME,
                NikacheContract.UserEntry.COLUMN_USER_LAST_NAME,
                NikacheContract.UserEntry.COLUMN_USER_EMAIL,
                NikacheContract.UserEntry.COLUMN_USER_PASSWORD,
                NikacheContract.UserEntry.COLUMN_USER_MOBILE,
                NikacheContract.UserEntry.COLUMN_USER_GENDER};

        Cursor cursor = db.query(
                NikacheContract.UserEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                  // The sort order


        try {

            if (cursor == null || cursor.getCount() < 1) {
                return;
            }

            if (cursor.moveToFirst()) {
                // Find the columns of pet attributes that we're interested in
                int userIdColumnIndex = cursor.getColumnIndex(NikacheContract.UserEntry.COLUMN_USER_ID);
                int firstNameColumnIndex = cursor.getColumnIndex(NikacheContract.UserEntry.COLUMN_USER_FIRST_NAME);
                int lastNameColumnIndex = cursor.getColumnIndex(NikacheContract.UserEntry.COLUMN_USER_LAST_NAME);
                int emailColumnIndex = cursor.getColumnIndex(NikacheContract.UserEntry.COLUMN_USER_EMAIL);
                int passwordColumnIndex = cursor.getColumnIndex(NikacheContract.UserEntry.COLUMN_USER_PASSWORD);
                int mobileColumnIndex = cursor.getColumnIndex(NikacheContract.UserEntry.COLUMN_USER_MOBILE);
                int genderColumnIndex = cursor.getColumnIndex(NikacheContract.UserEntry.COLUMN_USER_GENDER);

                // Extract out the value from the Cursor for the given column index
                int iD = cursor.getInt(userIdColumnIndex);
                String firstName = cursor.getString(firstNameColumnIndex);
                String lastName = cursor.getString(lastNameColumnIndex);
                email = cursor.getString(emailColumnIndex);
                password = cursor.getString(passwordColumnIndex);
                String mobileNo = cursor.getString(mobileColumnIndex);
                String gender = cursor.getString(genderColumnIndex);

            }
        }finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

    }

    //--------------------------

    View.OnClickListener onClickGoToSignUpScreen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginPage.this,SignUpPage.class);
            startActivity(intent);
        }
    };

    View.OnClickListener loginButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String userLoginEmail = textInputEditTextEmail.getText().toString().trim();
            String userLoginPassword = textInputEditTextPassword.getText().toString().trim();



            if((userLoginEmail.equals(email) ) && (userLoginPassword.equals(password))) {

                SplashScreen.isLogin = true;
                SignUpPage.statusLogin = SplashScreen.isLogin;




                if(SignUpPage.statusLogin == true) {


                    SharedPreferences sharedPref = LoginPage.this.getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("LoginStatus", true);
                    editor.commit();

                    SignUpPage.statusLogin = sharedPref.getBoolean("LoginStatus", false);

                }



                Intent intent = new Intent(LoginPage.this, MainActivity.class);
                startActivity(intent);
            }

        }
    };


    /**
     * This method is to initialize views
     */
    private void initViews() {

        scrollView = (ScrollView) findViewById(R.id.view_root);

        textInputLayoutEmail = (TextView) findViewById(R.id.tv_email_on_login_screen);
        textInputLayoutPassword = (TextView) findViewById(R.id.et_email_on_login_screen);



    }

    /**
     * This method is to initialize listeners
     */
//    private void initListeners() {
//        buttonLogin.setOnClickListener(profileListener);
//        buttonSignUp.setOnClickListener(profileListener);
//    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
//        nikacheDBHelper = new NikacheDBHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    View.OnClickListener profileListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_login_on_login_screen:
//                    verifyFromSQLite();
                    break;
//                case R.id.button_sign_up_login_screen:
//                    // Navigate to RegisterActivity
//                    Intent intentRegister = new Intent(getApplicationContext(), SignUpPage.class);
//                    startActivity(intentRegister);
//                    break;
            }
        }
    };


    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
//    private void verifyFromSQLite() {
//        if (!inputValidation.isEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
//            return;
//        }
//        if (!inputValidation.isEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
//            return;
//        }
//        if (!inputValidation.isEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
//            return;
//        }
//
//        // Snack Bar to show success message that record is wrong
//        if (nikacheDBHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
//                , textInputEditTextPassword.getText().toString().trim())) {
//
//
//            Intent accountsIntent = new Intent(activity, MyProfile.class);
//            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
//            emptyInputEditText();
//            startActivity(accountsIntent);
//
//
//        } else
//            Snackbar.make(scrollView, getString(R.string.error_valid_email_password_of_user), Snackbar.LENGTH_LONG).show();
//    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }


    // ------*****Login From Facebook ****----------


//    @Override
//    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Forward result to the callback manager for Login Button
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//
//        // confirm that this response matches your request
//        if (requestCode == APP_REQUEST_CODE) {
//            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
//            if (loginResult.getError() != null) {
//                // display login error
//                String toastMessage = loginResult.getError().getErrorType().getMessage();
//                Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
//            } else if (loginResult.getAccessToken() != null) {
//                // on successful login, proceed to the account activity
//                launchAccountActivity();
//            }
//        }
//    }

//    private void onLogin(final LoginType loginType) {
//        // create intent for the Account Kit activity
//        final Intent intent = new Intent(this, AccountKitActivity.class);
//
//        // configure login type and response type
//        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
//                new AccountKitConfiguration.AccountKitConfigurationBuilder(
//                        loginType,
//                        AccountKitActivity.ResponseType.TOKEN
//                );
//        final AccountKitConfiguration configuration = configurationBuilder.build();
//
//        // launch the Account Kit activity
//        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configuration);
//        startActivityForResult(intent, APP_REQUEST_CODE);
//    }

//    public void onPhoneLogin(View view) {
//        AppEventsLogger logger = AppEventsLogger.newLogger(this);
//        logger.logEvent("onPhoneLogin");
//
//        onLogin(LoginType.PHONE);
//    }

//    public void onEmailLogin(View view) {
//        AppEventsLogger logger = AppEventsLogger.newLogger(this);
//        logger.logEvent("onEmailLogin");
//
//        onLogin(LoginType.EMAIL);
//    }

//    public void onFacebookLogin(View view) {
//        AppEventsLogger logger = AppEventsLogger.newLogger(this);
//        logger.logEvent("onEmailLogin");
//
//        onLogin(LoginType.EMAIL);
//    }

//    private void launchAccountActivity() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }


    // ------*****Login From Facebook ****----------


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
