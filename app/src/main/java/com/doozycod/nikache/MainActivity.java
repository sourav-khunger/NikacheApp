package com.doozycod.nikache;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doozycod.nikache.Adapter.ExpandableListAdapter;
import com.doozycod.nikache.Fragment.AntiquesFragment;
import com.doozycod.nikache.Fragment.BrasDecorFragment;
import com.doozycod.nikache.Fragment.ClocksFragment;
import com.doozycod.nikache.Fragment.ContactUs;
import com.doozycod.nikache.Fragment.FurnitureFragment;
import com.doozycod.nikache.Fragment.Home;
import com.doozycod.nikache.Fragment.HotDealOfDayFragment;
import com.doozycod.nikache.Fragment.MyAccountFragment;
import com.doozycod.nikache.Fragment.OrdersFragment;
import com.doozycod.nikache.Fragment.UtilityItemsFragment;
import com.doozycod.nikache.Fragment.WallDecorFragment;
import com.doozycod.nikache.Fragment.WishListFragment;
import com.doozycod.nikache.PojoClasses.ExpandableListViewMenuModel;
import com.doozycod.nikache.sqliteDatabase.NikacheContract;
import com.doozycod.nikache.sqliteDatabase.NikacheDBHelper;
import com.facebook.ProfileTracker;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.doozycod.nikache.PojoClasses.ExpandableListViewMenuModel.listDataChild;
import static com.doozycod.nikache.PojoClasses.ExpandableListViewMenuModel.listDataHeader;


public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    Fragment homeFragment;
    Fragment grpFragment;
    Fragment subFragment;
    public static DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    int childPos;
    TextView tvSubItem;
    TextView tvGrpItem;
    private SignInButton mSignInButton;

    private View navHeader;
    public static ImageView imgNavHeaderLogo, imgProfile;
    private TextView  txtWelcome;
    Intent i;
    SignIn signIn;
    private GoogleApiClient mGoogleApiClient;
    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    View nikacheIconOnActionBar;
    View notification_icon;


//    private static final String urlProfileImg = "https://lh3.googleusercontent.com/GZ1Zuk3Kvc0F2dDXCWlo2pzwhnxxk5Je80wTEqJpEHJNhkRDwvQKZbowFmmxhTQa4YX0=s85";

    TextView id;
    TextView info;
    ProfileTracker profileTracker;
    // toolbar title respected to selected nav menu item
    private String[] fragmentTitle;
    // index to identify current nav menu item
    public static int navItemSubItemIndex = 0;
    // tags used for each navigation drawer menu item
    public static final String TAG_HOME = "home";
    private static final String TAG_WALL_DECOR = "wall decor";
    private static final String TAG_FURNITURE = "movies";
    private static final String TAG_UTILITY_ITEMS = "notifications";
    private static final String TAG_BRAS_DECOR = "settings";
    private static final String TAG_CLOCKS = "home";
    private static final String TAG_ANTIQUES = "wall decor";
    public static final String TAG_HOT_DEAL_OF_DAY = "movies";
    private static final String TAG_WISHLIST = "notifications";
    private static final String TAG_ORDERS = "settings";
    private static final String TAG_WALLET = "notifications";
    private static final String TAG_TRACK_YOUR_ORDER= "settings";
    public static final String TAG_MY_ACCOUNT= "my account";
    private static final String TAG_CONTACT_US= "contact us";
    public static String CURRENT_TAG = TAG_HOME;
    ActionBar actionBar;
    public static int actionBarWidth;
    LayoutInflater inflator;
    TextView tvMyWishlist, tvMyOrders;
    Toolbar toolbar;
    private NikacheDBHelper databaseHelper;
    public static View viewBetLogInAndSignUp;
    public static TextView tvUserNameNavHeader,loginTextInNavHeader,
            txtSignupInNavHeader,tvSolidCircleNavHeader,tvProfilePlaceHolder;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new NikacheDBHelper(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpNavigationDrawerAndExpandableListView();

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);

        // load nav menu header data
        loadNavHeader();

        setUpToolBar();

//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
                if(savedInstanceState == null){
                    navItemSubItemIndex = 0;
                    CURRENT_TAG = TAG_HOME;
                    setUpNavigationDrawerAndExpandableListView();
                }
//            }
//        });

        displayDatabaseInfo();
        hideShowIcon();

        //-----------------------------------------

        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signIn = new SignIn();

        //----------------------------------------
//        mGoogleApiClient = buildApiClient();
        //----------------------------------------

        // load toolbar title from string resources
        fragmentTitle = getResources().getStringArray(R.array.nav_menu_items_fragment_title);

        //-------------------**************-----------**************----------------------***************--------------------------------------------

        // To change the color of labels on ActionBar.

//        Spannable text = new SpannableString(getSupportActionBar().getTitle());
//        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        actionBar.setTitle(text);

        //-------------------**************-----------**************----------------------***************---------------------------------------------

        //---------------------------****************** Showing User's Details in Navigation Drawer **************************------------------------

        // register a receiver for the onCurrentProfileChanged event


//        profileTracker = new ProfileTracker() {
//            @Override
//            protected void onCurrentProfileChanged (Profile oldProfile, Profile currentProfile) {
//                if (currentProfile != null) {
//                    displayProfileInfo(currentProfile);
//                }
//            }
//        };
//
//        if (AccessToken.getCurrentAccessToken() != null) {
//            // If there is an access token then Login Button was used
//            // Check if the profile has already been fetched
//            Profile currentProfile = Profile.getCurrentProfile();
//            if (currentProfile != null) {
//                displayProfileInfo(currentProfile);
//            }
//            else {
//                // Fetch the profile, which will trigger the onCurrentProfileChanged receiver
//                Profile.fetchProfileForCurrentAccessToken();
//            }
//        }else {
//
//            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
//                @Override
//                public void onSuccess(final Account account) {
//                    // Get Account Kit ID
////                String accountKitId = account.getId();
////                id.setText(accountKitId);
//
//                    PhoneNumber phoneNumber = account.getPhoneNumber();
//                    if (account.getPhoneNumber() != null) {
//                        // if the phone number is available, display it
//                        String formattedPhoneNumber = formatPhoneNumber(phoneNumber.toString());
//                        loginTextInNavHeader.setText(formattedPhoneNumber);
//                    } else {
//                        // if the email address is available, display it
//                        String emailString = account.getEmail();
//                        loginTextInNavHeader.setText(emailString);
//                    }
//
//                }
//
//                @Override
//                public void onError(final AccountKitError error) {
//                    // display error
//                    String toastMessage = error.getErrorType().getMessage();
//                    Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
//                }
//            });
//        }

        //--------------------********************* UP CODE - Showing User's Details in Navigation Drawer **************************-----------------------------

        //-------------------**************-----------**************----------------------***************--------------------------------------------

//        if (savedInstanceState == null) {
//            navItemSubItemIndex = 0;
//            CURRENT_TAG = TAG_HOME;
//            setUpNavigationDrawerAndExpandableListView();
//        }

        //-------------------**************-----------**************----------------------***************--------------------------------------------

    } // onCreate Method close here.

    /***
     * Load Home fragment in MainActivity by default.
     */
//    public  void loadHomeFragment(final String current_tag ) {

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app


//    }

    //-----------------------------**************-------------------------------**************----------------------------------------

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        // unregister the profile tracker receiver
//        profileTracker.stopTracking();
//    }

    //----------------------------------------

//    private void displayProfileInfo(Profile profile) {
//        // get Profile ID
////        String profileId = profile.getId();
////        id.setText(profileId);
//
//        // display the Profile name
//        String name = profile.getName();
//        loginTextInNavHeader.setText(name);
//
//        // display the profile picture
//        Uri profilePicUri = profile.getProfilePictureUri(100, 100);
//        displayProfilePic(profilePicUri);
//    }

    //----------------------------------------

//    private void launchLoginActivity() {
//        Intent intent = new Intent(MainActivity.this, LoginPage.class);
//        startActivity(intent);
//        finish();
//    }

    //----------------------------------------

//    private String formatPhoneNumber(String phoneNumber) {
//        // helper method to format the phone number for display
//        try {
//            PhoneNumberUtil pnu = PhoneNumberUtil.getInstance();
//            Phonenumber.PhoneNumber pn = pnu.parse(phoneNumber, Locale.getDefault().getCountry());
//            phoneNumber = pnu.format(pn, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
//        }
//        catch (NumberParseException e) {
//            e.printStackTrace();
//        }
//        return phoneNumber;
//    }

    //----------------------------------------

//    private void displayProfilePic(Uri uri) {
//        // helper method to load the profile pic in a circular imageview
//        Transformation transformation = new RoundedTransformationBuilder()
//                .cornerRadiusDp(30)
//                .oval(false)
//                .build();
//        Picasso.with(MainActivity.this)
//                .load(uri)
//                .transform(transformation)
//                .into(imgProfile);
//    }

    //----------------------------------------

//    public GoogleApiClient buildApiClient(){
//        return new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(signIn)
//                .addOnConnectionFailedListener(signIn)
//                .addApi(Plus.API, Plus.PlusOptions.builder().build())
//                .addScope(new Scope(Scopes.PROFILE))
//                .build();
//    }

    //----------------------------------------

    //-------------------**************-----------**************----------------------***************---------------------------------------------

    // Load Navigation header in Naviagtion Drawer.

    private void loadNavHeader() {


        loginTextInNavHeader = (TextView) navHeader.findViewById(R.id.tv_loginin_nav_header);
        loginTextInNavHeader.setTextColor(Color.parseColor("#1F4C23"));
        txtSignupInNavHeader = (TextView) navHeader.findViewById(R.id.tv_signup_nav_header);
        tvUserNameNavHeader = (TextView) navHeader.findViewById(R.id.tv_user_name_nav_header);
        tvSolidCircleNavHeader = (TextView) navHeader.findViewById(R.id.tv_solid_circle);
        tvProfilePlaceHolder = (TextView) navHeader.findViewById(R.id.tvAlphabetPlaceHolder);
        viewBetLogInAndSignUp = (View) navHeader.findViewById(R.id.v_after_loginIn_in_nav_header);

        txtWelcome = (TextView) navHeader.findViewById(R.id.tv_welcome);
        tvMyWishlist = (TextView) navHeader.findViewById(R.id.tv_my_wishlist);
        tvMyOrders = (TextView) navHeader.findViewById(R.id.tv_my_orders);
        tvMyWishlist.setOnClickListener(clickListenerForWishlistAndMyOrder);
        tvMyOrders.setOnClickListener(clickListenerForWishlistAndMyOrder);

//        imgNavHeaderLogo = (ImageView) navHeader.findViewById(R.id.nav_header_nikache_logo);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        loginTextInNavHeader.setText("LOG IN");

        txtSignupInNavHeader.setText("SIGN UP");

        loginTextInNavHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginPage.class);
                startActivity(intent);

            }
        });

        txtSignupInNavHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUpPage.class);
                startActivity(intent);
            }
        });

        txtWelcome.setText("Welcome !");
        txtWelcome.setTextColor(Color.parseColor("#1F4C23"));

//        imgNavHeaderLogo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navItemSubItemIndex = 0;
//                CURRENT_TAG = TAG_HOME;
//                setUpDrawer();
//            }
//        });

    } // Load Navigation Header method closes here.

    //-------------------------

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
                String email = cursor.getString(emailColumnIndex);
                String password = cursor.getString(passwordColumnIndex);
                String mobileNo = cursor.getString(mobileColumnIndex);
                String gender = cursor.getString(genderColumnIndex);


                if (firstName != null) {
                    tvUserNameNavHeader.setVisibility(View.VISIBLE);
                    loginTextInNavHeader.setVisibility(View.GONE);
                    txtSignupInNavHeader.setVisibility(View.GONE);
                    tvUserNameNavHeader.setText(firstName + " " + lastName);
                    imgProfile.setVisibility(View.GONE);
                    tvSolidCircleNavHeader.setVisibility(View.VISIBLE);
                    tvProfilePlaceHolder.setVisibility(View.VISIBLE);
                    viewBetLogInAndSignUp.setVisibility(View.GONE);
                    tvProfilePlaceHolder.setText(firstName.substring(0, 1));
                } else {
                    imgProfile.setVisibility(View.VISIBLE);
                    tvSolidCircleNavHeader.setVisibility(View.GONE);
                    tvProfilePlaceHolder.setVisibility(View.GONE);
                    tvUserNameNavHeader.setVisibility(View.GONE);
                    loginTextInNavHeader.setVisibility(View.VISIBLE);
                    txtSignupInNavHeader.setVisibility(View.VISIBLE);
                    viewBetLogInAndSignUp.setVisibility(View.VISIBLE);
                }


            }
        }finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

    }

    private void hideShowIcon(){

//        if(SignUpPage.statusLogin == true) {

            SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.putBoolean("LoginStatus", true);
//            editor.commit();

            SplashScreen.isLogin = sharedPref.getBoolean("LoginStatus", false);

//        }


        if(SplashScreen.isLogin  == true){
            tvUserNameNavHeader.setVisibility(View.VISIBLE);
            loginTextInNavHeader.setVisibility(View.GONE);
            txtSignupInNavHeader.setVisibility(View.GONE);
            imgProfile.setVisibility(View.GONE);
            tvSolidCircleNavHeader.setVisibility(View.VISIBLE);
            tvProfilePlaceHolder.setVisibility(View.VISIBLE);
            viewBetLogInAndSignUp.setVisibility(View.GONE);
        }else{
            imgProfile.setVisibility(View.VISIBLE);
            tvSolidCircleNavHeader.setVisibility(View.GONE);
            tvProfilePlaceHolder.setVisibility(View.GONE);
            tvUserNameNavHeader.setVisibility(View.GONE);
            loginTextInNavHeader.setVisibility(View.VISIBLE);
            txtSignupInNavHeader.setVisibility(View.VISIBLE);
            viewBetLogInAndSignUp.setVisibility(View.VISIBLE);
        }
    }

    //-------------------**************-----------**************----------------------***************---------------------------------------------

    // Opens the fragment, on clicking item from Navigation Header whether "My Wishlist" or "My Order" correspondingly

    View.OnClickListener clickListenerForWishlistAndMyOrder = new View.OnClickListener()
    {


        @Override
        public void onClick( View v )
        {

            if( tvMyWishlist.getId() == v.getId() )
            {
                //-----------------***** On clicking, "Wishlist" item in Navigation Drawer menu,opens Wishlist Fragment *****---------------------------------

//                new Handler().post(new Runnable() {
//                    @Override
//                    public void run() {

                        Fragment wishListFragment = new WishListFragment();
                        navItemSubItemIndex = 8;
                        CURRENT_TAG = TAG_WISHLIST;
                        setToolbarTitle();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.content_frame, wishListFragment,CURRENT_TAG);
                        fragmentTransaction.addToBackStack(CURRENT_TAG);
                        fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
//                    }
//                });

                //******------------------------**********---------------------------------

            }else if( tvMyOrders.getId() == v.getId() )
            {
                //-----------------***** On clicking, "My Order" item in Navigation Drawer menu, opens "My Order" Fragment *****---------------------------------

//                new Handler().post(new Runnable() {
//                    @Override
//                    public void run() {

                        Fragment ordersFragment = new OrdersFragment();
                        navItemSubItemIndex = 9;
                        CURRENT_TAG = TAG_ORDERS;
                        setToolbarTitle();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.content_frame, ordersFragment,CURRENT_TAG);
                        fragmentTransaction.addToBackStack(CURRENT_TAG);
                        fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
//                    }
//                });
            } // else-if close here
        }   // onClick ends here
    }; // "clickListenerForWishlistAndMyOrder" ends here.

    //-------------------**************-----------**************----------------------***************---------------------------------------------

    private void setUpToolBar(){

                actionBar = getSupportActionBar();

                // Display "Nikache" word as icon on Action Bar.
                actionBar.setDisplayShowCustomEnabled(true);
                actionBar.setTitle(" ");
                inflator = (LayoutInflater) MainActivity.this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                nikacheIconOnActionBar = inflator.inflate(R.layout.custom_imageview, null);
                actionBar.setCustomView(nikacheIconOnActionBar);

                // Set the position of the icon "Nikache" on the left side of the Action Bar.
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);

                //width of action bar is the same as width of whole screen
                actionBarWidth = size.x;

                // Change the position of Title or label on the Action Bar.

                nikacheIconOnActionBar.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

                    @Override
                    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                        double x = v.getX();
                        double logoImageWidth = v.getWidth();
                        // int logoPosition = actionBarWidth / 2 - logoImageWidth / 2;
                        double logoPosition = actionBarWidth/4.5  - logoImageWidth /3;
                        if (x != logoPosition) {
                            v.setX((float) logoPosition);
                            v.requestLayout();
                        } else {
                            v.removeOnLayoutChangeListener(this);
                        }
                    }

                });

        // Calculate ActionBar height--------------------
//        TypedValue tv = new TypedValue();
//        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
//        {
//           int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
//          Toast.makeText(this,"ActionBar height: " + String.valueOf(actionBarHeight),Toast.LENGTH_LONG).show();
//        }
        //-----------------------------------------
//        float valueInPixelOf10dp = getResources().getDimension(R.dimen.standard_10);
//        Toast.makeText(this,"10dp value in pixel: " + String.valueOf(valueInPixelOf10dp),Toast.LENGTH_LONG).show();
        //-------------------------------------------

    } // End setUpToolBar method here.

    //-------------------**************-----------**************----------------------***************---------------------------------------------

    public void setUpNavigationDrawerAndExpandableListView() {

        // get the Expandable listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing the data for menu and sub-menu in Navigation Drawer
        ExpandableListViewMenuModel.prepareListData();

//        populateExpandableList();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawer,toolbar, R.string.open, R.string.close){

            @Override
            public void onDrawerClosed(View drawerView) {

                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //get the Navigation view
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        // load nav menu header data
//        loadNavHeader();

//    } // setUpNavigationDrawerAndExpandableListView() method closed

    //-------------------**************-----------**************----------------------***************---------------------------------------------

//    private void populateExpandableList() {

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        //-------------------- Implementing Expandable Listview setOnGroupClickListener---------------------------

        /**
         * on clicking the menu item of
         * expandable listview in navigation drawer,
         * concerned fragment is opened.
         */
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, final View v, int groupPosition, long id) {


                switch (groupPosition) {

                    case 0:
                        navItemSubItemIndex = 12;

                        listAdapter = new ExpandableListAdapter(getBaseContext(), listDataHeader, listDataChild);
                        expListView.setAdapter(listAdapter);

                        break;
                    case 1:
                        grpFragment = null;
                        grpFragment = new HotDealOfDayFragment();
                        navItemSubItemIndex = 7;
                        CURRENT_TAG = TAG_HOT_DEAL_OF_DAY;
                        setToolbarTitle();
                        openHeaderFragment(CURRENT_TAG);
                        break;

                    case 2:
                        grpFragment = null;
                        grpFragment = new MyAccountFragment();
                        navItemSubItemIndex = 13;
                        CURRENT_TAG = TAG_MY_ACCOUNT;
                        setToolbarTitle();
                        openHeaderFragment(CURRENT_TAG);
                        break;

                    case 3:
                        grpFragment = null;
                        grpFragment = new ContactUs();
                        navItemSubItemIndex = 15;
                        CURRENT_TAG = TAG_CONTACT_US;
                        setToolbarTitle();
                        openHeaderFragment(CURRENT_TAG);
                        break;

                    case 4:

//                        ImageView ivFacebook = (ImageView) v.findViewById(R.id.iv_facebook);
//                        ImageView ivTwitter = (ImageView)v.findViewById(R.id.iv_twitter);
//                        ImageView ivYoutube = (ImageView)v.findViewById(R.id.iv_youtube);
//
//                            if(groupPosition == 4){
//                                ivFacebook.setClickable(true);
//                                ivFacebook.setOnClickListener(new View.OnClickListener() {
//
//                                    @Override
//                                    public void onClick(View v) {
//
//                                        Uri uri = Uri.parse("https://www.facebook.com"); // missing 'http://' will cause crashed
//                                        openSocialNetworkWebsite(uri);
//
//                                    }
//                                });
//                            }


                        if (listDataHeader[groupPosition]== " ") {

                            ImageView ivFacebook = (ImageView) v.findViewById(R.id.iv_facebook);

                            if(ivFacebook != null){
                                parent.setDescendantFocusability(parent.FOCUS_AFTER_DESCENDANTS);
                                ivFacebook.requestFocus();
                                ivFacebook.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {

                                        Uri uri = Uri.parse("https://www.facebook.com"); // missing 'http://' will cause crashed
                                        openSocialNetworkWebsite(uri);

                                    }
                                });

                            }

                            ImageView ivTwitter = (ImageView)v.findViewById(R.id.iv_twitter);

                            if(ivTwitter != null){
                                parent.setDescendantFocusability(parent.FOCUS_AFTER_DESCENDANTS);
                                ivTwitter.requestFocus();
                                ivTwitter.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {

                                        Uri uri = Uri.parse("https://twitter.com"); // missing 'http://' will cause crashed
                                        openSocialNetworkWebsite(uri);
                                    }
                                });
                            }

                            ImageView ivYoutube = (ImageView)v.findViewById(R.id.iv_youtube);

                            if(ivYoutube != null){
                                parent.setDescendantFocusability(parent.FOCUS_AFTER_DESCENDANTS);
                                ivYoutube.requestFocus();
                                ivYoutube.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {

                                        Uri uri = Uri.parse("https://www.youtube.com"); // missing 'http://' will cause crashed
                                        openSocialNetworkWebsite(uri);
                                    }
                                });
                            }

                            if(! parent.isFocused()){
                                parent.setDescendantFocusability(parent.FOCUS_BEFORE_DESCENDANTS);
                                parent.requestFocus();
                                drawer.closeDrawer(GravityCompat.START);
                            }

                        }

                        break;

                    default:
                        navItemSubItemIndex = 0;

                }

                return false;
            } // onGroupClick method close here
        }); // expListView.setOnGroupClickListener close here

        //---------------------**********-------------------------------****************---------------------------

        /**
         * on clicking the sub-menu item of
         * expandable listview in navigation drawer,
         * concerned fragment is opened.
         */
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id)
            {

                subFragment = null;

                switch (groupPosition) {

                    case 0:

                        switch (childPosition) {
                            case 0:
                                subFragment = new WallDecorFragment();
                                navItemSubItemIndex = 1;
                                CURRENT_TAG = TAG_WALL_DECOR;
                                setToolbarTitle();
                                openChildFragment(CURRENT_TAG);
                                break;
                            case 1:
                                subFragment = new FurnitureFragment();
                                navItemSubItemIndex = 2;
                                CURRENT_TAG = TAG_FURNITURE;
                                setToolbarTitle();
                                openChildFragment(CURRENT_TAG);
                                break;
                            case 2:
                                subFragment = new UtilityItemsFragment();
                                navItemSubItemIndex = 3;
                                CURRENT_TAG = TAG_UTILITY_ITEMS;
                                setToolbarTitle();
                                openChildFragment(CURRENT_TAG);
                                break;
                            case 3:
                                subFragment = new BrasDecorFragment();
                                navItemSubItemIndex = 4;
                                CURRENT_TAG = TAG_BRAS_DECOR;
                                setToolbarTitle();
                                openChildFragment(CURRENT_TAG);
                                break;
                            case 4:
                                subFragment = new ClocksFragment();
                                navItemSubItemIndex = 5;
                                CURRENT_TAG = TAG_CLOCKS;
                                setToolbarTitle();
                                openChildFragment(CURRENT_TAG);
                                break;
                            case 5:
                                subFragment = new AntiquesFragment();
                                navItemSubItemIndex = 6;
                                CURRENT_TAG = TAG_ANTIQUES;
                                setToolbarTitle();
                                openChildFragment(CURRENT_TAG);
                                break;
                            default:
                                break;
                        }
                        break;

                    default:
                        navItemSubItemIndex = 0;

                } // switch case ends

                return false;
            } // onChildClick Method close here.
        }); // expListView.setOnChildClickListener close here

        new Handler().post(new Runnable() {
            @Override
            public void run() {

                if (navItemSubItemIndex == 0) {
                    homeFragment = new Home();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, homeFragment, CURRENT_TAG);
                    ft.commit();
                }
            }
        });

    }

    //-------------------**************-----------**************----------------------***************---------------------------------------------

    // Method called from expListView.setOnGroupClickListener to open header fragment

    private void openHeaderFragment(final String currentTag){

        new Handler().post(new Runnable() {
            @Override
            public void run() {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, grpFragment, currentTag);
                fragmentTransaction.addToBackStack(currentTag);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    //-------------------**************-----------**************----------------------***************---------------------------------------------

    // Method called from expListView.setOnChildClickListener to open header fragment

    private void openChildFragment(final String currentTag){

        new Handler().post(new Runnable() {
            @Override
            public void run() {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, subFragment, currentTag);
                fragmentTransaction.addToBackStack(currentTag);
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    //-------------------**************-----------**************----------------------***************---------------------------------------------

    // Method called from expListView.setOnGroupClickListener to open social network website

    private void openSocialNetworkWebsite(final Uri uri){

        new Handler().post(new Runnable() {
            @Override
            public void run() {

                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
    }

    //-------------------**************-----------**************----------------------***************---------------------------------------------

    /**
     * Toolbar title changes, according to the item choosed
     * from the expandable listview under navigation drawer.
     */
    private void setToolbarTitle()
    {
        View customView = actionBar.getCustomView();
        customView.setVisibility(View.GONE);
        actionBar.setTitle(fragmentTitle[navItemSubItemIndex]);

        // To change the color of labels on ActionBar.

        Spannable text = new SpannableString(getSupportActionBar().getTitle());
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);

    }

    //-------------------**************-----------**************----------------------***************---------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.notification, menu);

        return true;
    }

    //-------------------**************-----------**************----------------------***************---------------------------------------------

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        if (id == R.id.action_clear_notification) {
            Intent intent = new Intent(MainActivity.this, MyNotification.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_shopping_cart) {

            Intent intent = new Intent(MainActivity.this, MyBag.class);
            startActivity(intent);
            return true;
        }



        /**
         * On clicking, hamburger icon on Action bar Navigation Drawer open
         * and on clicking back arrow on action bar, while navigation drawer
         * is open, it will close the navigation drawer.
         */

//        if (item != null && id == android.R.id.home) {
//
//                    toggle();
//        }

        return super.onOptionsItemSelected(item);
    } // onOptionsItemSelected method closed

    //-------------------**************-----------**************----------------------***************---------------------------------------------

    private void toggle() {

                if (drawer.isDrawerVisible(GravityCompat.START)) {

//                    new Handler().post(new Runnable() {
//                        @Override
//                        public void run() {
                            drawer.closeDrawer(GravityCompat.START);
//                        }
//                    });
                }
                else {

//                    new Handler().post(new Runnable() {
//                        @Override
//                        public void run() {
                            drawer.openDrawer(GravityCompat.START);
//                        }
//                    });
                }
    }

    //-------------------**************-----------**************----------------------***************---------------------------------------------
    boolean doubleBackToExitPressedOnce = false;
    Handler statusUpdateHandler;
    private Runnable statusUpdateRunnable;
    Toast toast;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {

        // on back press, closes the navigation drawer.
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }

//        if (shouldLoadHomeFragOnBackPress){
//
//            // If the active fragment is other than "Home" and "My Profile" fragment, then this cond. is true.
            if (navItemSubItemIndex != 0) {
                getFragmentManager().popBackStackImmediate();
                super.onBackPressed();
//                navItemSubItemIndex = 0;
//                CURRENT_TAG = TAG_HOME;
//                loadHomeFragment(CURRENT_TAG);

                return;
            }
//        }

        if(navItemSubItemIndex == 0){
            finishAffinity();
            System.exit(0);
        }



//        if(navItemSubItemIndex == 0){
//            if (doubleBackToExitPressedOnce) {
//                if (getFragmentManager().getBackStackEntryCount() == 0) {
//                    finishAffinity();
//                    toast.cancel();
//                    System.exit(0);
//                } else {
//                    getFragmentManager().popBackStackImmediate();
//                }
//                return;
//            }
//
//            if (getFragmentManager().getBackStackEntryCount() == 0) {
//                this.doubleBackToExitPressedOnce = true;
//                toast = Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT);
//                toast.show();
//
//
//                statusUpdateRunnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        doubleBackToExitPressedOnce = false;
//                        toast.cancel();  //Removes the toast after the exit.
//                    }
//                };
//                statusUpdateHandler.postDelayed(statusUpdateRunnable, 2000);
//            }
//            else
//                {
//                getFragmentManager().popBackStackImmediate();
//                }
//        }




//
//        navItemIndex = 0; // Assigning 0 to "My Profile" fragment. By default it's 16.
//
//        if(navItemIndex == 16) // When the "My Profile" fragment is active, this code run.
//        {
//
//            navItemSubItemIndex = 13;
//            navItemIndex = 0;
//            Toast.makeText(this,String.valueOf(navItemSubItemIndex),Toast.LENGTH_LONG).show();
//            CURRENT_TAG = TAG_MY_ACCOUNT;
//            openHeaderFragment(CURRENT_TAG);
//
//            return;
//        }
//        if(navItemSubItemIndex == 0){
//            super.onBackPressed();
//        }
//
//            super.onBackPressed(); // when home fragment active, this get executed to exit the app on back press.



    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (statusUpdateHandler != null) {
//            statusUpdateHandler.removeCallbacks(statusUpdateRunnable);
//        }
//    }


    //-------------------**************-----------**************----------------------***************---------------------------------------------

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        drawer.closeDrawer(GravityCompat.START);
    }


    //-------------------**************-----------**************----------------------***************--------------------------------------

    //    private void setupDrawerContent(NavigationView navigationView) {
//        //revision: this don't works, use setOnChildClickListener() and setOnGroupClickListener() above instead
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        menuItem.setChecked(true);
//                        drawer.closeDrawers();
//                        // refresh toolbar menu
//                        invalidateOptionsMenu();
//                        return true;
//                    }
//                });
//    }

//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState); // Sync the toggle state after
//        // onRestoreInstanceState has
//        // occurred.
//        actionBarDrawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig); // Pass any configuration
//        // change to the drawer
//        // toggls
//        actionBarDrawerToggle.onConfigurationChanged(newConfig);
//    }

//    private DrawerListener mDrawerListener = new DrawerListener() {
//
//        @Override
//        public void onDrawerStateChanged(int status) {
//
//        }
//
//        @Override
//        public void onDrawerSlide(View view, float slideArg) {
//
//        }
//
//        @Override
//        public void onDrawerOpened(View view) {
////            getActionBar().setTitle(mDrawerTitle);
//            // calling onPrepareOptionsMenu() to hide action bar icons
////            invalidateOptionsMenu();
//        }
//
//        @Override
//        public void onDrawerClosed(View view) {
////            getActionBar().setTitle(mTitle);
//            // calling onPrepareOptionsMenu() to show action bar icons
////            invalidateOptionsMenu();
//        }
//    };



//new Handler().post(new Runnable() {
//        @Override
//        public void run() {
//
//
//
//        }
//    });

}
