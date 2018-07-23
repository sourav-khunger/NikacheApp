package com.doozycod.nikache.Fragment;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.doozycod.nikache.R;
import com.doozycod.nikache.sqliteDatabase.NikacheContract;
import com.doozycod.nikache.sqliteDatabase.NikacheDBHelper;

public class MyProfile extends Fragment  {

    private NikacheDBHelper databaseHelper;

    EditText userFirstName;
    EditText userLastName;
    EditText userEmail;
    EditText userMobileNo;
    EditText userOldPassword;
    RadioGroup rgGender;
    RadioButton rbMale;
    RadioButton rbFemale;
    Button button1InMyProf;
    String mGender;
    ScrollView rootViewMyProfile;
    String firstName;
    String lastName;
    String email;
    String password;
    String mobileNo;
    String gender;
    public static Uri newUri;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        userFirstName = (EditText)view.findViewById(R.id.et_first_name);
        userLastName = (EditText)view.findViewById(R.id.et_last_name);
        userEmail = (EditText)view.findViewById(R.id.et_email);
        userMobileNo = (EditText)view.findViewById(R.id.et_mobile);
        userOldPassword = (EditText)view.findViewById(R.id.et_old_password);
        rgGender = (RadioGroup)view.findViewById(R.id.radio_group);
        rbMale = (RadioButton)view.findViewById(R.id.rb_male);
        rbFemale = (RadioButton)view.findViewById(R.id.rb_female);
        button1InMyProf = (Button)view.findViewById(R.id.button1);
        rootViewMyProfile = (ScrollView)view.findViewById(R.id.root_view_my_profile);
        button1InMyProf.setOnClickListener(onButton1ClickListener);

        databaseHelper = new NikacheDBHelper(getContext());



        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayDatabaseInfo();

    }

    View.OnClickListener onButton1ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            SQLiteDatabase db = databaseHelper.getWritableDatabase();

          if(rbMale.isChecked() == true){
              mGender = "Male";
          }else if (rbFemale.isChecked() == true){
              mGender = "Female";
          }

            if (TextUtils.isEmpty(firstName) ||
                    TextUtils.isEmpty(lastName) || TextUtils.isEmpty(email) ||
                    TextUtils.isEmpty(password) || TextUtils.isEmpty(mobileNo)
                    && TextUtils.isEmpty(gender)) {
                // Since no fields were modified, we can return early without creating a new user.
                // No need to create ContentValues and no need to do any ContentProvider operations.
                return;
            }

            ContentValues values = new ContentValues();
            values.put(NikacheContract.UserEntry.COLUMN_USER_FIRST_NAME, userFirstName.getText().toString().trim());
            values.put(NikacheContract.UserEntry.COLUMN_USER_LAST_NAME, userLastName.getText().toString().trim());
//            values.put(NikacheContract.UserEntry.COLUMN_USER_EMAIL, userEmail.getText().toString().trim());
            values.put(NikacheContract.UserEntry.COLUMN_USER_MOBILE, userMobileNo.getText().toString().trim());
            values.put(NikacheContract.UserEntry.COLUMN_USER_GENDER, mGender);

            Uri currentPetUri = ContentUris.withAppendedId(NikacheContract.UserEntry.CONTENT_URI, 1);

            if(currentPetUri == null){

                newUri = getContext().getContentResolver().insert(NikacheContract.UserEntry.CONTENT_URI, values);

                // Show a toast message depending on whether or not the insertion was successful.
                if (newUri == null) {

                    Snackbar.make(rootViewMyProfile, "Profile saved successfully", Snackbar.LENGTH_LONG).show();
                } else {
                    // Snack Bar to show success message that record saved successfully
                    Snackbar.make(rootViewMyProfile, "Profile not saved", Snackbar.LENGTH_LONG).show();
                }
            }else {

                int rowsAffected = getContext().getContentResolver().update(currentPetUri, values, NikacheContract.UserEntry.COLUMN_USER_ID + "= ?", new String[]{"1"});

//            int rowsAffected = getContext().getContentResolver().update(NikacheContract.UserEntry.CONTENT_URI, values, null, null);

                // Show a toast message depending on whether or not the update was successful.
                if (rowsAffected == 0) {
                    // If no rows were affected, then there was an error with the update.
                    Snackbar.make(rootViewMyProfile, "Not Updated", Snackbar.LENGTH_LONG).show();
                } else {
                    // Otherwise, the update was successful and we can display a toast.
                    Snackbar.make(rootViewMyProfile, "Updated successfully", Snackbar.LENGTH_LONG).show();
                }
            }
        }
    };

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
                firstName = cursor.getString(firstNameColumnIndex);
                lastName = cursor.getString(lastNameColumnIndex);
                email = cursor.getString(emailColumnIndex);
                password = cursor.getString(passwordColumnIndex);
                mobileNo = cursor.getString(mobileColumnIndex);
                gender = cursor.getString(genderColumnIndex);

                userFirstName.setText(firstName);
                userLastName.setText(lastName);
                userEmail.setText(email);
                userEmail.setEnabled(false);
                userOldPassword.setText(password);
                userMobileNo.setText(mobileNo);

                switch(gender){
                    case "Male":
                        rbMale.setChecked(true);
                        break;
                    case "Female":
                        rbFemale.setChecked(true);
                        break;

                }
            }
        }finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

    }





}
