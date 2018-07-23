package com.doozycod.nikache.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doozycod.nikache.LoginPage;
import com.doozycod.nikache.MainActivity;
import com.doozycod.nikache.MyWallet;
import com.doozycod.nikache.PojoClasses.StoreCurrentWalletAmt;
import com.doozycod.nikache.R;
import com.doozycod.nikache.SignUpPage;
import com.doozycod.nikache.SplashScreen;
import com.doozycod.nikache.sqliteDatabase.NikacheContract;
import com.doozycod.nikache.sqliteDatabase.NikacheDBHelper;

import static com.doozycod.nikache.MainActivity.imgProfile;
import static com.doozycod.nikache.MainActivity.loginTextInNavHeader;
import static com.doozycod.nikache.MainActivity.tvProfilePlaceHolder;
import static com.doozycod.nikache.MainActivity.tvSolidCircleNavHeader;
import static com.doozycod.nikache.MainActivity.tvUserNameNavHeader;
import static com.doozycod.nikache.MainActivity.txtSignupInNavHeader;
import static com.doozycod.nikache.MainActivity.viewBetLogInAndSignUp;
import static com.doozycod.nikache.MyWallet.TAG_WALLET_MONEY;


public class MyAccountFragment extends Fragment {
    ImageView ivSettingMyProfile;
    Fragment myProfileFragment;
    Fragment myWalletFragment;
    Fragment myOrderFragment;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    View nikacheIconOnActionBar;
    ActionBar actionBar;
    public static int navItemIndex = 0;
    private String[] homeFragmentSubTitle;
    public static final String TAG_MY_PROFILE= "my profile";
    public static final String TAG_WISHLIST= "wishlist";
    public static final String TAG_MY_WALLET= "mywallet";
    TextView tvUserName;
    TextView tvEmailId;
    TextView tvAlphabetPlaceHolder;
    ImageView ivImageHolder;
    Bundle args;
    String firstNameOfUser;
    String lastNameOfUser;
    String emailOfUser;
    private NikacheDBHelper databaseHelper;
    TextView tvSolidCircle;
    TextView tvLoginButton;
    View viewBetLoginAndSignUpButton;
    TextView tvSignupButton;
    public static final String TAG_MY_ORDER= "myorder";
    Fragment wishlistFragment;
    public static float currentWalletAmt;
    public static String TAG_CURRENT_WALLET_MONEY = "displayWalletMoneyInAccFragment";
    public static SharedPreferences sharedPref;
    public static SharedPreferences.Editor accEditor;

    Toolbar toolbar;

    public MyAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);

//                toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

//        args = savedInstanceState;

        databaseHelper = new NikacheDBHelper(getContext());

        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        LayoutInflater inflator = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nikacheIconOnActionBar = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(nikacheIconOnActionBar);
        actionBar.setTitle("My Account");
        nikacheIconOnActionBar.setVisibility(View.GONE);


        // Setting the color of title on action bar
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);
        //-----------------------------


        // load toolbar title from string resources
        homeFragmentSubTitle = getResources().getStringArray(R.array.nav_menu_items_fragment_title);

        //---------------------------------

        TextView tvLogout = (TextView) view.findViewById(R.id.tv_logout);
        tvLogout.setOnClickListener(tvLogoutClickListner);

        TextView tvShareOurApp = (TextView) view.findViewById(R.id.tv_share_our_app);
        tvShareOurApp.setOnClickListener(shareOurAppClickListener);

        MyWallet.moneyOnSuccess = StoreCurrentWalletAmt.getDefaults(TAG_WALLET_MONEY,getContext());

            TextView tvWalletMoney = (TextView)view.findViewById(R.id.tv_wallet_money);
            tvWalletMoney.setText(String.valueOf(MyWallet.moneyOnSuccess));


        //---------------------------------

        tvSolidCircle = (TextView)view.findViewById(R.id.tv_solid_circle);
        tvLoginButton = (TextView)view.findViewById(R.id.tv_loginin_my_account);
        viewBetLoginAndSignUpButton = (View)view.findViewById(R.id.v_after_loginIn_in_my_acc);
        tvSignupButton = (TextView)view.findViewById(R.id.tv_signup_my_acc);

        tvLoginButton.setOnClickListener(onLoginSignUpClickListener);
        tvSignupButton.setOnClickListener(onLoginSignUpClickListener);

        ivSettingMyProfile = (ImageView) view.findViewById(R.id.iv_setting_my_profile);
        ivSettingMyProfile.setOnClickListener(ivClickListner);

            tvUserName = (TextView) view.findViewById(R.id.tv_user_id);
            tvEmailId = (TextView) view.findViewById(R.id.tv_user_email_id);
            tvAlphabetPlaceHolder = (TextView) view.findViewById(R.id.tvAlphabetPlaceHolder);
            ivImageHolder = (ImageView) view.findViewById(R.id.iv_acc_circle);

            TextView tvWishlist = (TextView)view.findViewById(R.id.tv_wishlist);
            tvWishlist.setOnClickListener(wishlistOnClickListener);

        TextView tvMyOrders = (TextView)view.findViewById(R.id.tv_my_orders);
        tvMyOrders.setOnClickListener(myOrderOnClickListener);


        TextView tvMyWallet = (TextView)view.findViewById(R.id.tv_my_wallet);
        tvMyWallet.setOnClickListener(myWalletOnClickListener);


        displayDatabaseInfo();
        hideShowIcon();



        return view;

    }

    View.OnClickListener wishlistOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            navItemIndex = 8;
            MainActivity.CURRENT_TAG = TAG_WISHLIST;
            setToolbarTitle();
            wishlistFragment = null;
            wishlistFragment = new WishListFragment();
            fragmentManager = getActivity().getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, wishlistFragment,MainActivity.CURRENT_TAG);
            fragmentTransaction.addToBackStack(MainActivity.CURRENT_TAG);
            fragmentTransaction.commit();
        }
    };

    View.OnClickListener myOrderOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            navItemIndex = 9;
            MainActivity.CURRENT_TAG = TAG_MY_ORDER;
            setToolbarTitle();
            myOrderFragment = null;
            myOrderFragment = new OrdersFragment();
            fragmentManager = getActivity().getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, myOrderFragment,MainActivity.CURRENT_TAG);
            fragmentTransaction.addToBackStack(MainActivity.CURRENT_TAG);
            fragmentTransaction.commit();
        }
    };

    View.OnClickListener myWalletOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            navItemIndex = 10;
//            MainActivity.CURRENT_TAG = TAG_MY_WALLET;
//            setToolbarTitle();
//            myWalletFragment = null;
//            myWalletFragment = new WalletFragment();
//            fragmentManager = getActivity().getSupportFragmentManager();
//            fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.content_frame, myWalletFragment,MainActivity.CURRENT_TAG);
//            fragmentTransaction.addToBackStack(MainActivity.CURRENT_TAG);
//            fragmentTransaction.commit();

            Intent intent = new Intent(getActivity(), MyWallet.class);
            startActivity(intent);
        }
    };


    private void hideShowIcon(){

        if(SplashScreen.isLogin  == true){
            tvSolidCircle.setVisibility(View.VISIBLE);
            ivImageHolder.setVisibility(View.GONE);
            tvAlphabetPlaceHolder.setVisibility(View.VISIBLE);
            tvUserName.setVisibility(View.VISIBLE);
            tvEmailId.setVisibility(View.VISIBLE);
            tvLoginButton.setVisibility(View.GONE);
            viewBetLoginAndSignUpButton.setVisibility(View.GONE);
            tvSignupButton.setVisibility(View.GONE);

            tvUserNameNavHeader.setVisibility(View.VISIBLE);
            loginTextInNavHeader.setVisibility(View.GONE);
            txtSignupInNavHeader.setVisibility(View.GONE);
            imgProfile.setVisibility(View.GONE);
            tvSolidCircleNavHeader.setVisibility(View.VISIBLE);
            tvProfilePlaceHolder.setVisibility(View.VISIBLE);
            viewBetLogInAndSignUp.setVisibility(View.GONE);

        }else{
            tvSolidCircle.setVisibility(View.GONE);
            ivImageHolder.setVisibility(View.VISIBLE);
            tvAlphabetPlaceHolder.setVisibility(View.GONE);
            tvUserName.setVisibility(View.GONE);
            tvEmailId.setVisibility(View.GONE);
            tvLoginButton.setVisibility(View.VISIBLE);
            viewBetLoginAndSignUpButton.setVisibility(View.VISIBLE);
            tvSignupButton.setVisibility(View.VISIBLE);

            imgProfile.setVisibility(View.VISIBLE);
            tvSolidCircleNavHeader.setVisibility(View.GONE);
            tvProfilePlaceHolder.setVisibility(View.GONE);
            tvUserNameNavHeader.setVisibility(View.GONE);
            loginTextInNavHeader.setVisibility(View.VISIBLE);
            txtSignupInNavHeader.setVisibility(View.VISIBLE);
            viewBetLogInAndSignUp.setVisibility(View.VISIBLE);

        }
    }

    View.OnClickListener onLoginSignUpClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if( tvLoginButton.getId() == v.getId() )
            {
                Intent intent = new Intent(getActivity(), LoginPage.class);
                startActivity(intent);
            }else if( tvSignupButton.getId() == v.getId() )
            {
                Intent intent = new Intent(getActivity(), SignUpPage.class);
                startActivity(intent);
            }
        }
    };


    View.OnClickListener shareOurAppClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    String shareSubText = "Nikache - Our Decor Shop";
                    String shareBodyText = "http://doozycod.com/nikache/";
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubText);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
                    shareIntent.setPackage("com.whatsapp");
//                    startActivity(Intent.createChooser(shareIntent, "Share With"));
                    startActivity(shareIntent);
                }
            });

        }
    };

    //------------------------**************---------------------------------------

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

                tvUserName.setText(firstName + " " + lastName);
                tvEmailId.setText(email);
                if (firstName != null) {

                    ivImageHolder.setVisibility(View.GONE);
                    tvAlphabetPlaceHolder.setText(firstName.substring(0, 1));
                } else {
                    ivImageHolder.setVisibility(View.VISIBLE);
                    tvAlphabetPlaceHolder.setVisibility(View.GONE);
                }


            }
        }finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

    }

    //------------------------***************--------------------------------------




    View.OnClickListener ivClickListner = new View.OnClickListener()
    {
        private Handler mHandler;

        @Override
        public void onClick( View v )
        {

            // TODO Auto-generated method stub
            if( ivSettingMyProfile.getId() == v.getId() )
            {
                //******-------***** On Clicking @ViewAll,opens HotDeal of day Fragment *****---------------------------------

                myProfileFragment = null;
                myProfileFragment = new MyProfile();

                // Sometimes, when fragment has huge data, screen seems hanging
                // when switching between navigation menus
                // So using runnable, the fragment is loaded with cross fade effect
                // This effect can be seen in GMail app
                Runnable mPendingRunnable = new Runnable() {
                    @Override
                    public void run() {

                        navItemIndex = 16;
                        MainActivity.CURRENT_TAG = TAG_MY_PROFILE;
                        setToolbarTitle();
                        fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.content_frame, myProfileFragment,MainActivity.CURRENT_TAG);
                        fragmentTransaction.addToBackStack(MainActivity.CURRENT_TAG);
                        fragmentTransaction.commit();

                    }
                };

                // If mPendingRunnable is not null, then add to the message queue
                if (mPendingRunnable != null) {
                    mHandler = new Handler();
                    mHandler.post(mPendingRunnable);
                }

                //******------------------------**********---------------------------------

            }

        }
    };

    /**
     * Toolbar title changes, according to the item choosed
     * from the expandable listview under navigation drawer.
     */
    private void setToolbarTitle()
    {
        actionBar.setTitle(homeFragmentSubTitle[navItemIndex]);
        nikacheIconOnActionBar.setVisibility(View.GONE);

        // Setting the color of title on action bar
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);
        //-----------------------------
    }

    public static Fragment newInstance(String firstName, String lastName, String email) {
        Fragment fragment = new MyAccountFragment();
        Bundle args = new Bundle();
        args.putString("first_name", firstName);
        args.putString("last_name", lastName);
        args.putString("email_id", email);
        fragment.setArguments(args);
        return fragment;
    }

    //----------*****facebook logout*******--------------------

    View.OnClickListener tvLogoutClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // logout of Account Kit
//                                AccountKit.logOut();
//                                // logout of Login Button
//                                LoginManager.getInstance().logOut();
//                                launchLoginActivity();
//                                Toast.makeText(getContext(),"You are logged out", Toast.LENGTH_SHORT).show();

            SplashScreen.isLogin = false;
            tvUserName.setVisibility(View.GONE);
            tvEmailId.setVisibility(View.GONE);
            tvAlphabetPlaceHolder.setVisibility(View.GONE);
            tvSolidCircle.setVisibility(View.GONE);
            ivImageHolder.setVisibility(View.VISIBLE);
            tvLoginButton.setVisibility(View.VISIBLE);
            viewBetLoginAndSignUpButton.setVisibility(View.VISIBLE);
            tvSignupButton.setVisibility(View.VISIBLE);


            imgProfile.setVisibility(View.VISIBLE);
            tvSolidCircleNavHeader.setVisibility(View.GONE);
            tvProfilePlaceHolder.setVisibility(View.GONE);
            tvUserNameNavHeader.setVisibility(View.GONE);
            loginTextInNavHeader.setVisibility(View.VISIBLE);
            txtSignupInNavHeader.setVisibility(View.VISIBLE);
            viewBetLogInAndSignUp.setVisibility(View.VISIBLE);
        }
    };



//    private void launchLoginActivity() {
//        Intent intent = new Intent(getContext(), LoginActivity.class);
//        startActivity(intent);
//        getActivity().finish();
//    }

    //----------*****facebook logout*******--------------------

}
