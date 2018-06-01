package com.example.theopoipoi.alzheimer_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.os.UserManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_USER = "user";

    // User Table Columns names
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_FIRSTNAME = "user_firstname";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_ADDRESS = "user_address";
    private static final String COLUMN_USER_PHONE = "user_phone";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + " ("
            + COLUMN_USER_NAME + " VARCHAR PRIMARY KEY not null,"
            + COLUMN_USER_FIRSTNAME + " VARCHAR not null,"
            + COLUMN_USER_PASSWORD + " INT(4) not null,"
            + COLUMN_USER_ADDRESS + " VARCHAR not null,"
            + COLUMN_USER_PHONE + " VARCHAR not null"
            + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_PHONE, user.getPhone());
        values.put(COLUMN_USER_ADDRESS, user.getAddress());
        values.put(COLUMN_USER_FIRSTNAME, user.getFirstname());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }


    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD,
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setPassword(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD))));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    /*public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_TEMPERATURE_MAX, user.getTemperature_max());
        values.put(COLUMN_USER_TEMPERATURE_MIN, user.getTemperature_min());
        values.put(COLUMN_USER_BATTERY_MAX, user.getBattery_max());
        values.put(COLUMN_USER_BATTERY_MIN, user.getBattery_min());
        values.put(COLUMN_USER_HUMIDITY_MAX, user.getHumidity_max());
        values.put(COLUMN_USER_HUMIDITY_MIN, user.getHumidity_min());
        values.put(COLUMN_USER_PHONE, user.getPhone());


        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_NAME + "= ?", new String[]{String.valueOf(user.getUsername())});
        db.close();
    }
*/
    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_NAME + " = ?",
                new String[]{String.valueOf(user.getName())});
        db.close();
    }


    public boolean checkPassword(String username, String password) throws SQLException
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_NAME + " = ? AND " + COLUMN_USER_PASSWORD + " = ?", new String[]{username,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }




    /**
     * This method to check user exist or not
     *
     * @param username
     * @return true/false
     **/


    public boolean checkUser(String username) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_NAME
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_NAME + " = ?";

        // selection arguments
        String[] selectionArgs = {username};

        // query user table with conditions
/**
 * Here query function is used to fetch records from user table this function works like we use sql query.
 * SQL query equivalent to this query function is
 * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
 */

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    //This method is used to get informations about a table (an integer)
    public Integer getinfo(int index,String username){
        Integer res = new Integer(0) ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_NAME + " = ?", new String [] {username});
        if (cursor.moveToFirst()) {
            res = cursor.getInt(index);
        }
        Log.w("Values", res.toString());
        cursor.close();
        db.close();

        return res;

    }

/*    //This method is used to get the address of a user
    public String getAddress(int index,String username){
        String res = new String() ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_NAME + " = ?", new String [] {username});
        if (cursor.moveToFirst()) {
            res = cursor.getString(index);
        }
        Log.w("Address :", res);
        cursor.close();
        db.close();

        return res;

    }*/

    public String getAddress (String username) {
        String address = new String();
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor cursor = db.rawQuery(query, null);
        Cursor cursor = db.rawQuery("Select "+ COLUMN_USER_ADDRESS + " FROM " + TABLE_USER + " WHERE " + COLUMN_USER_NAME + " = ?", new String [] {username});
        if (cursor.moveToFirst()) {
            address = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ADDRESS));
        }
        Log.w("Address :", address);
        cursor.close();
        db.close();
        return address;
    }


    public String getNumber (String username) {
        String numb = new String();
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor cursor = db.rawQuery(query, null);
        Cursor cursor = db.rawQuery("Select "+ COLUMN_USER_PHONE + " FROM " + TABLE_USER + " WHERE " + COLUMN_USER_NAME + " = ?", new String [] {username});
        if (cursor.moveToFirst()) {
            numb = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PHONE));
        }
        Log.w("Numéro de téléphone", numb);
        cursor.close();
        db.close();
        return numb;
    }


}
