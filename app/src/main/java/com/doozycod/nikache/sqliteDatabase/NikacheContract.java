package com.doozycod.nikache.sqliteDatabase;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class NikacheContract {

    public static final String CONTENT_AUTHORITY = "com.doozycod.nikache";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PRODUCT = "product";
    public static final String PATH_USER = "user";

    public static final class ProductEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PRODUCT).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCT;

        // Product table name
        public static final String TABLE_NAME = "product";

        public static final String COLUMN_PRODUCT_ID = "product_id";
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_PRODUCT_PRICE = "product_price";
        public static final String COLUMN_PRODUCT_IMAGE_PATH = "image_path";

        public static Uri buildProductUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static final String[] PRODUCT_COLUMNS = {

                COLUMN_PRODUCT_ID,
                COLUMN_PRODUCT_NAME,
                COLUMN_PRODUCT_PRICE,
                COLUMN_PRODUCT_IMAGE_PATH
        };

        public static final int COL_PRODUCT_ID = 0;
        public static final int COL_PRODUCT_NAME = 1;
        public static final int COL_PRODUCT_PRICE = 2;
        public static final int COL_PRODUCT_IMAGE_PATH =3;

    }

    public static final class UserEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        // User table name
        public static final String TABLE_NAME = "user";

        // User Table Columns names
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_USER_FIRST_NAME = "user_first_name";
        public static final String COLUMN_USER_LAST_NAME = "user_last_name";
        public static final String COLUMN_USER_EMAIL = "user_email";
        public static final String COLUMN_USER_PASSWORD = "user_password";
        public static final String COLUMN_USER_MOBILE = "user_mobile";
        public static final String COLUMN_USER_GENDER = "user_gender";

        public static Uri buildUserUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static final String[] USER_COLUMNS = {

                COLUMN_USER_ID,
                COLUMN_USER_FIRST_NAME,
                COLUMN_USER_LAST_NAME,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_MOBILE,
                COLUMN_USER_GENDER
        };


        public static final int COL_USER_ID = 0;
        public static final int COL_USER_FIRST_NAME = 1;
        public static final int COL_USER_LAST_NAME = 2;
        public static final int COL_USER_EMAIL =3;
        public static final int COL_USER_PASSWORD =4;
        public static final int COL_USER_MOBILE =5;
        public static final int COL_USER_GENDER =6;

        // Possible values for the gender of the user.
//        public static final int GENDER_MALE = 0;
//        public static final int GENDER_FEMALE = 1;

    }

}
