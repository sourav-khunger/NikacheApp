package com.doozycod.nikache;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

public class SignIn extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "signin1";

    private SignInButton mSignInButton;
    private Button mSignOutButton;
    private Button mRevokeButton;
    private TextView mStatus;

    private int mSignInProgress;
    private static final int STATE_SIGNED_IN = 0;
    private static final int STATE_SIGN_IN = 1;
    private static final int STATE_IN_PROGRESS = 2;

    private PendingIntent mSignInIntent;
    private int mSignInError;


    private static final int RC_SIGN_IN = 0;
    private static final int DIALOG_PLAY_SERVICES_ERROR = 0;
//    TextView userName;
//    private View navHeader;
//    private NavigationView navigationView;
//    private DrawerLayout drawer;
//    private ImageView imgNavHeaderLogo, imgProfile;
//    private TextView txtName, txtWelcome;
//    private static final String urlProfileImg = "https://lh3.googleusercontent.com/GZ1Zuk3Kvc0F2dDXCWlo2pzwhnxxk5Je80wTEqJpEHJNhkRDwvQKZbowFmmxhTQa4YX0=s85";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        mSignOutButton = (Button) findViewById(R.id.sign_out_button);
        mRevokeButton = (Button) findViewById(R.id.revoke_access_button);
        mStatus = (TextView)findViewById(R.id.sign_in_status);

        mSignInButton.setOnClickListener(this);
        mSignOutButton.setOnClickListener(this);
        mRevokeButton.setOnClickListener(this);

        mGoogleApiClient = buildApiClient();

        Spannable text = new SpannableString(getSupportActionBar().getTitle());
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.app_title)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(text);

        setUpToolBar();
    }

    private void setUpToolBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle(" ");
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_imageview, null);
//        View v1 = inflator.inflate(R.layout.custom_imageview_logo, null);
        actionBar.setCustomView(v);
//        actionBar.setCustomView(v1);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
//width of action bar is the same as width of whole screen
        final int actionBarWidth = size.x;


        v.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                int x = (int) v.getX();
                int logoImageWidth = v.getWidth();
                        double logoPosition = actionBarWidth / 2 - logoImageWidth / 2;
//                double logoPosition = actionBarWidth/4.5  - logoImageWidth /3;
                if (x != logoPosition) {
                    v.setX((float) logoPosition);
                    v.requestLayout();
                } else {
                    v.removeOnLayoutChangeListener(this);
                }
            }

        });

//        v1.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v1, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                int x1 = (int) v1.getX();
//                int logoImageWidth1 = v1.getWidth();
//                double logoPosition1 = actionBarWidth /2 - logoImageWidth1 / 2;
////                double logoPosition = actionBarWidth/4.5  - logoImageWidth /3;
//                if (x1 != logoPosition1) {
//                    v1.setX((float) logoPosition1);
//                    v1.requestLayout();
//                } else {
//                    v1.removeOnLayoutChangeListener(this);
//                }
//            }
//        });

    }

    public GoogleApiClient buildApiClient(){
        return new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(new Scope(Scopes.PROFILE))
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {

        // Reaching onConnected means we consider the user signed in.
        Log.i(TAG, "onConnected");

        // Update the user interface to reflect that the user is signed in.
        mSignInButton.setEnabled(false);
        mSignOutButton.setEnabled(true);
        mRevokeButton.setEnabled(true);

        // Indicate that the sign in process is complete.
        mSignInProgress = STATE_SIGNED_IN;



        // We are signed in!
        // Retrieve some profile information to personalize our app for the user.
        Person currentUser = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
        mStatus.setText(currentUser.getDisplayName());
//        mStatus.setText(String.format("Signed In to G+ as %s", currentUser.getDisplayName()));
        String userName = mStatus.getText().toString();
        if(!mSignInButton.isEnabled()){
            Intent intent = new Intent(this, MainActivity.class);

//            Toast.makeText(this,userName,Toast.LENGTH_SHORT).show();
            intent.putExtra("name", userName);
            Toast.makeText(this,"Logged In",Toast.LENGTH_LONG).show();
            startActivity(intent);}

    }

    @Override
    public void onConnectionSuspended(int i) {

        mGoogleApiClient.connect();
        Log.i(TAG, "onConnectionSuspended:"+i);
    }

    private void resolveSignInError() {
        if (mSignInIntent != null) {
            // We have an intent which will allow our user to sign in or
            // resolve an error.  For example if the user needs to
            // select an account to sign in with, or if they need to consent
            // to the permissions your app is requesting.

            try {
                // Send the pending intent that we stored on the most recent
                // OnConnectionFailed callback.  This will allow the user to
                // resolve the error currently preventing our connection to
                // Google Play services.
                mSignInProgress = STATE_IN_PROGRESS;
                startIntentSenderForResult(mSignInIntent.getIntentSender(),
                        RC_SIGN_IN, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                Log.i(TAG, "Sign in intent could not be sent: "
                        + e.getLocalizedMessage());
                // The intent was canceled before it was sent.  Attempt to connect to
                // get an updated ConnectionResult.
                mSignInProgress = STATE_SIGN_IN;
                mGoogleApiClient.connect();
            }
        } else {
            // Google Play services wasn't able to provide an intent for some
            // error types, so we show the default Google Play services error
            // dialog which may still start an intent on our behalf if the
            // user can resolve the issue.
            showDialog(DIALOG_PLAY_SERVICES_ERROR);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RC_SIGN_IN:
                if (resultCode == RESULT_OK) {
                    // If the error resolution was successful we should continue
                    // processing errors.
                    mSignInProgress = STATE_SIGN_IN;

                } else {
                    // If the error resolution was not successful or the user canceled,
                    // we should stop processing errors.
                    mSignInProgress = STATE_SIGNED_IN;
                }

                if (!mGoogleApiClient.isConnecting()) {
                    // If Google Play services resolved the issue with a dialog then
                    // onStart is not called so we need to re-attempt connection here.
                    mGoogleApiClient.connect();
                }
                break;
        }
    }

    private void onSignedOut() {
        // Update the UI to reflect that the user is signed out.
        mSignInButton.setEnabled(true);
        mSignOutButton.setEnabled(false);
        mRevokeButton.setEnabled(false);

        mStatus.setText("Logged out");
//        userName.setText("Log In/Sign Up");
        String userStatus = mStatus.getText().toString();

//        if(mSignInButton.isEnabled()){
//            Intent intent = new Intent(this, MainActivity.class);
//
////            Toast.makeText(this,userName,Toast.LENGTH_SHORT).show();
////            intent.putExtra("name1", userStatus);
//            Toast.makeText(this,"Logged Out",Toast.LENGTH_LONG).show();
//            startActivity(intent);}


    }



    @Override
    public void onClick(View v) {

        if(! mGoogleApiClient.isConnecting()){
            switch (v.getId()){
                case R.id.sign_in_button:
                    mStatus.setText("Signing In");
                    resolveSignInError();

                    break;
                case R.id.sign_out_button:
                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    mGoogleApiClient.disconnect();
                    mGoogleApiClient.connect();
//                    Intent intent = new Intent(this, MainActivity.class);
//                    String userName = mStatus.getText().toString();
//                    intent.putExtra("user_name", userName);
//                    Toast.makeText(this,"Signed out",Toast.LENGTH_LONG).show();
//                    startActivity(intent);
                    break;
                case R.id.revoke_access_button:
                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient);
                    mGoogleApiClient = buildApiClient();
                    mGoogleApiClient.connect();
                    break;
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Refer to the javadoc for ConnectionResult to see what error codes might
        // be returned in onConnectionFailed.
        Log.i(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());

        if (mSignInProgress != STATE_IN_PROGRESS) {
            // We do not have an intent in progress so we should store the latest
            // error resolution intent for use when the sign in button is clicked.
            mSignInIntent = connectionResult.getResolution();
            mSignInError = connectionResult.getErrorCode();

            if (mSignInProgress == STATE_SIGN_IN) {
                // STATE_SIGN_IN indicates the user already clicked the sign in button
                // so we should continue processing errors until the user is signed in
                // or they click cancel.
                resolveSignInError();
            }
        }

        // In this sample we consider the user signed out whenever they do not have
        // a connection to Google Play services.
        onSignedOut();
    }
}
