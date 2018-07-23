package com.doozycod.nikache.sqliteDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NikacheDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "nikache.db";

    public NikacheDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIES_TABLE = "CREATE TABLE " + NikacheContract.ProductEntry.TABLE_NAME + " (" +
                NikacheContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NikacheContract.ProductEntry.COLUMN_PRODUCT_ID + " TEXT NOT NULL, " +
                NikacheContract.ProductEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                NikacheContract.ProductEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL, " +
                NikacheContract.ProductEntry.COLUMN_PRODUCT_IMAGE_PATH + " INTEGER NOT NULL "  +
                " );";

        // create table sql query
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + NikacheContract.UserEntry.TABLE_NAME + "(" +
                NikacheContract.UserEntry.COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NikacheContract.UserEntry.COLUMN_USER_FIRST_NAME + " TEXT NOT NULL," +
                NikacheContract.UserEntry.COLUMN_USER_LAST_NAME + " TEXT NOT NULL," +
                NikacheContract.UserEntry.COLUMN_USER_EMAIL + " TEXT NOT NULL," +
                NikacheContract.UserEntry.COLUMN_USER_PASSWORD + " TEXT NOT NULL," +
                NikacheContract.UserEntry.COLUMN_USER_MOBILE + " TEXT NOT NULL," +
                NikacheContract.UserEntry.COLUMN_USER_GENDER + " TEXT NOT NULL " +
                " )";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NikacheContract.ProductEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NikacheContract.UserEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

//    /**
//     * This method is to create user record
//     *
//     * @param user
//     */
//    public void addUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Log.v("SQLite: ","add user method called");
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USER_ID, user.getId());
//        values.put(COLUMN_USER_FIRST_NAME, user.getFirstName());
//        values.put(COLUMN_USER_LAST_NAME, user.getLastName());
//        values.put(COLUMN_USER_EMAIL, user.getEmail());
//        values.put(COLUMN_USER_PASSWORD, user.getPassword());
//        values.put(COLUMN_USER_MOBILE, user.getMobileNo());
//        values.put(COLUMN_USER_GENDER, user.getGender());
//
//        // Inserting Row
//        db.insert(TABLE_USER, null, values);
//        db.close();
//    }
//
//    /**
//     * This method is to fetch all user and return the list of user records
//     *
//     * @return list
//     */
//    public List<User> getAllUser() {
//        // array of columns to fetch
//        String[] columns = {
//                COLUMN_USER_ID,
//                COLUMN_USER_EMAIL,
//                COLUMN_USER_FIRST_NAME,
//                COLUMN_USER_LAST_NAME,
//                COLUMN_USER_PASSWORD,
//                COLUMN_USER_MOBILE,
//                COLUMN_USER_GENDER
//        };
//        // sorting orders
//        String sortOrder =
//                COLUMN_USER_FIRST_NAME + " ASC";
//        List<User> userList = new ArrayList<User>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // query the user table
//        /**
//         * Here query function is used to fetch records from user table this function works like we use sql query.
//         * SQL query equivalent to this query function is
//         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
//         */
//        Cursor cursor = db.query(TABLE_USER, //Table to query
//                columns,    //columns to return
//                null,        //columns for the WHERE clause
//                null,        //The values for the WHERE clause
//                null,       //group the rows
//                null,       //filter by row groups
//                sortOrder); //The sort order
//
//
//        // Traversing through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                User user = new User(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))),
//                        cursor.getString(cursor.getColumnIndex(COLUMN_USER_FIRST_NAME)),
//                        cursor.getString(cursor.getColumnIndex(COLUMN_USER_LAST_NAME)),
//                        cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)),
//                        cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)),
//                        cursor.getString(cursor.getColumnIndex(COLUMN_USER_MOBILE)),
//                        cursor.getString(cursor.getColumnIndex(COLUMN_USER_GENDER))
//                );
////                user.setId());
////                user.setFirstName();
////                user.setLastName();
////                user.setEmail();
////                user.setPassword();
////                user.setMobileNo();
////                user.setGender();
//                // Adding user record to list
//                userList.add(user);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//
//        // return user list
//        return userList;
//    }
//
//    /**
//     * This method to update user record
//     *
//     * @param user
//     */
//    public void updateUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USER_FIRST_NAME, user.getFirstName());
//        values.put(COLUMN_USER_LAST_NAME, user.getLastName());
//        values.put(COLUMN_USER_EMAIL, user.getEmail());
//        values.put(COLUMN_USER_PASSWORD, user.getPassword());
//        values.put(COLUMN_USER_MOBILE, user.getMobileNo());
//        values.put(COLUMN_USER_GENDER, user.getGender());
//
//        // updating row
//        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
//                new String[]{String.valueOf(user.getId())});
//        db.close();
//    }
//
//
//    /**
//     * This method is to delete user record
//     *
//     * @param user
//     */
//    public void deleteUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        // delete user record by id
//        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
//                new String[]{String.valueOf(user.getId())});
//        db.close();
//    }
//
//
//    /**
//     * This method to check user exist or not
//     *
//     * @param email
//     * @return true/false
//     */
//    public boolean checkUser(String email) {
//
//        // array of columns to fetch
//        String[] columns = {
//                COLUMN_USER_ID
//        };
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // selection criteria
//        String selection = COLUMN_USER_EMAIL + " = ?";
//
//        // selection argument
//        String[] selectionArgs = {email};
//
//        // query user table with condition
//        /**
//         * Here query function is used to fetch records from user table this function works like we use sql query.
//         * SQL query equivalent to this query function is
//         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
//         */
//        Cursor cursor = db.query(TABLE_USER, //Table to query
//                columns,                    //columns to return
//                selection,                  //columns for the WHERE clause
//                selectionArgs,              //The values for the WHERE clause
//                null,                       //group the rows
//                null,                      //filter by row groups
//                null);                      //The sort order
//        int cursorCount = cursor.getCount();
//        cursor.close();
//        db.close();
//
//        if (cursorCount > 0) {
//            return true;
//        }
//
//        return false;
//    }
//
//    /**
//     * This method to check user exist or not
//     *
//     * @param email
//     * @param password
//     * @return true/false
//     */
//    public boolean checkUser(String email, String password) {
//
//        // array of columns to fetch
//        String[] columns = {
//                COLUMN_USER_ID
//        };
//        SQLiteDatabase db = this.getReadableDatabase();
//        // selection criteria
//        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
//
//        // selection arguments
//        String[] selectionArgs = {email, password};
//
//        // query user table with conditions
//        /**
//         * Here query function is used to fetch records from user table this function works like we use sql query.
//         * SQL query equivalent to this query function is
//         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
//         */
//        Cursor cursor = db.query(TABLE_USER, //Table to query
//                columns,                    //columns to return
//                selection,                  //columns for the WHERE clause
//                selectionArgs,              //The values for the WHERE clause
//                null,                       //group the rows
//                null,                       //filter by row groups
//                null);                      //The sort order
//
//        int cursorCount = cursor.getCount();
//
//        cursor.close();
//        db.close();
//        if (cursorCount > 0) {
//            return true;
//        }
//
//        return false;
//    }

}
