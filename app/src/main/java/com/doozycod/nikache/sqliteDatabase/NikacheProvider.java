package com.doozycod.nikache.sqliteDatabase;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Content Provider act as a layer, between the database and the activity.
 * It ensure that wrong data not get entered into the database.
 * If we don't use content provider then any typo can lead to
 * wrong entry into database.
 * Content provider are going to help us a lot later on when we
 * want to take advantage of other framework classes to load data
 * from a database more smoothly into the UI.
 * Content provider works well with those framework classes.
 * With the help of content provider, we can centralize the
 * accessing and editing of the data via the content provider.
 * So in this schema, our UI code will then directly interact with the
 * content provider instead of directly interacting with the database.
 * The content provider, as a layer of data validation, which we saw we needed,
 * when we incorrectly entered invalid weight values. So if there ends up being a
 * bug, we can catch it here.
 * So, the content provider, as this added layer bet. the data source
 * and the UI code, is called an abstraction layer. And this because the content
 * provider abstracts the way or hides the details of how the data is stored.
 * content provider help us modify how the data is stored,while the UI code
 * remaining unaffected.
 * Well, it also work well with other framework classes.so to explain what
 * that means, so take a look at this list of pets here, so currently if pets
 * are added or removed, and we want the latest information displayed in the UI,
 * we must call the query and insert method each time. Well, instead of doing it that
 * tedious method, we can leverage the power of a framework class called the
 * Cursor Loader. So when pets are added or removed to this list, the list will
 * stay updated via our Cursor Loader. Which will automatically check when any
 * changes have been made in the data, and then automatically refresh the list
 * when that data does change.
 *
 * So the cursor loader works in conjunction with the listview and the cursor adapter.
 * And doing so, these display the changes that are made in the list. So
 * implementing the cursor loader requires use of the content provider.
 * Well, it also works with home screen widgets,something called sync adapter
 * that sync our data to the cloud. As well as provides search suggestions in your
 * app. So the framework team wanted to make content provider a consistent way
 * to manage access to a structures set of data in android.
 *
 * One more big adv. of content provider is sharing data.
 * So when you have text data or files inside your app, currently no other
 * apps can access it. So in case of our Pet shelter app,this info. is siloed
 * and say there is a vets app or adoptions app or even a pet lovers app. Well,
 * they can't have access to our existing database.
 * Well, we can expose our data to these other apps using a content provider.
 * Doing so, other apps can access this information by interacting with the
 * provider using the same query,insert,update and delete methods.
 * Well doing so could be a security concern. Because maybe you don't want a
 * particular app to access and modify all your app data. Potentially, a malicious
 * app could delete an entire darabase table. So opening up your data to other apps,
 * could be dangerous if not done properly. Well again, the framework team has this
 * covered.
 * The content provider manages access to data in a secure way. If you want other
 * apps to prompt the user to grant certain permission to access the data, then the
 * content provider can help enforce this.
 * So, other apps without proper permission won't be able to access the data.
 */
public class NikacheProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private NikacheDBHelper mOpenHelper;

    /** Tag for the log messages */
    public static final String LOG_TAG = NikacheProvider.class.getSimpleName();

    static final int USER = 100;
    static final int PRODUCT = 300;
    static final int USER_ID = 101;


    private final String TAG = "Insert Id: ";

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = NikacheContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, NikacheContract.PATH_PRODUCT, PRODUCT);
        matcher.addURI(authority, NikacheContract.PATH_USER, USER);
        matcher.addURI(authority, NikacheContract.PATH_USER + "/#", USER_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new NikacheDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case PRODUCT: {
                cursor = mOpenHelper.getReadableDatabase().query(
                        NikacheContract.ProductEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case USER: {
                cursor = mOpenHelper.getReadableDatabase().query(
                        NikacheContract.UserEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
                case USER_ID:{
                    selection = NikacheContract.UserEntry.COLUMN_USER_ID + "=?";
                    selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                    cursor = mOpenHelper.getReadableDatabase().query(
                            NikacheContract.UserEntry.TABLE_NAME,
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            sortOrder
                    );
                    break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (getContext() != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCT:
                return NikacheContract.ProductEntry.CONTENT_TYPE;
            case USER:
                return NikacheContract.UserEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch (match) {
            case PRODUCT: {
                long id = db.insert(NikacheContract.ProductEntry.TABLE_NAME, null, values);
//                Toast.makeText(getContext(),String.valueOf(id),Toast.LENGTH_SHORT).show();
                Log.i(TAG,String.valueOf(id));
                if (id > 0) {
                    returnUri = NikacheContract.ProductEntry.buildProductUri(id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case USER: {
                long id = db.insert(NikacheContract.UserEntry.TABLE_NAME, null, values);
//                Toast.makeText(getContext(),String.valueOf(id),Toast.LENGTH_SHORT).show();
                Log.i(TAG,String.valueOf(id));
                if (id > 0) {
//                    returnUri = NikacheContract.UserEntry.buildUserUri(id);
                    returnUri = insertUser(uri, values);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return returnUri;
    }

    private Uri insertUser(Uri uri, ContentValues values) {
        // Check that the name is not null
        String firstName = values.getAsString(NikacheContract.UserEntry.COLUMN_USER_FIRST_NAME);
        if (firstName == null) {
            throw new IllegalArgumentException("User requires a First Name");
        }

        String lastName = values.getAsString(NikacheContract.UserEntry.COLUMN_USER_LAST_NAME);
        if (lastName == null) {
            throw new IllegalArgumentException("User requires a Last Name");
        }

        String mobileNo = values.getAsString(NikacheContract.UserEntry.COLUMN_USER_MOBILE);
        if (mobileNo == null) {
            throw new IllegalArgumentException("User requires a Mobile No.");
        }

        // Check that the gender is valid
        String gender = values.getAsString(NikacheContract.UserEntry.COLUMN_USER_GENDER);
        if (gender == null || TextUtils.isEmpty(gender)) {
            throw new IllegalArgumentException("User requires valid gender");
        }

        // Get writeable database
        SQLiteDatabase database = mOpenHelper.getWritableDatabase();

        // Insert the new pet with the given values
        long id = database.insert(NikacheContract.UserEntry.TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Notify all listeners that the data has changed for the pet content URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        if (null == selection) {
            selection = "1";
        }
        switch (match) {
            case PRODUCT:
                rowsDeleted = db.delete(
                        NikacheContract.ProductEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case USER:
                rowsDeleted = db.delete(
                        NikacheContract.UserEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case USER_ID:
                selection = NikacheContract.UserEntry.COLUMN_USER_ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = db.delete(
                        NikacheContract.UserEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsDeleted != 0 && getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case PRODUCT:
                rowsUpdated = db.update(NikacheContract.ProductEntry.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case USER:
                rowsUpdated = db.update(NikacheContract.UserEntry.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case USER_ID:
                // For the PET_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = NikacheContract.UserEntry.COLUMN_USER_ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
               rowsUpdated = db.update(NikacheContract.UserEntry.TABLE_NAME, values, selection, selectionArgs);
               break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0 && getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

}
