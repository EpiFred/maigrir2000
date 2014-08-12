package com.maigrir2000v03.helper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.maigrir2000v03.slidingmenu.model.ContactContainer;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	 
    // Database Name
    private static final String DATABASE_NAME = "Maigrir2000";
 
    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";
     
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_CITY = "city";
    private static final String KEY_ZIPCODE = "zipcode";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_NB1 = "number1";
    private static final String KEY_NB2 = "number2";
    private static final String KEY_MAIL = "mail";
    private static final String KEY_IMAGE = "image";
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
 // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SURNAME + " TEXT," + KEY_ADDRESS + " TEXT," 
                + KEY_CITY + " TEXT," +KEY_ZIPCODE + " TEXT," +KEY_COUNTRY + " TEXT," 
                + KEY_NB1 + " TEXT," + KEY_NB2 + " TEXT," +KEY_MAIL + " TEXT," +KEY_IMAGE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
 
        // Create tables again
        onCreate(db);
    }
    
 // Adding new contact
    public void addContact(ContactContainer contact) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_SURNAME, contact.getSurname()); // Contact Name
        values.put(KEY_ADDRESS, contact.getAddress()); // Contact Name
        values.put(KEY_CITY, contact.getCity()); // Contact Name
        values.put(KEY_ZIPCODE, contact.getZipcode()); // Contact Name
        values.put(KEY_COUNTRY, contact.getCountry()); // Contact Name
        values.put(KEY_NB1, contact.getNumber1()); // Contact Phone Number
        values.put(KEY_NB2, contact.getNumber2()); // Contact Name
        values.put(KEY_MAIL, contact.getMail()); // Contact Name
        values.put(KEY_IMAGE, contact.getImage()); // Contact Name
     
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }
     
    // Getting single contact
    public ContactContainer getContact(int id) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	 
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                KEY_NAME, KEY_SURNAME, KEY_ADDRESS, KEY_CITY, KEY_ZIPCODE, KEY_COUNTRY, KEY_NB1, KEY_NB2, KEY_MAIL, KEY_IMAGE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        ContactContainer contact = new ContactContainer(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
        // return contact
        return contact;
    }
     
    // Getting All Contacts
    public ArrayList<ContactContainer> getAllContacts() {
    	ArrayList<ContactContainer> contactList = new ArrayList<ContactContainer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ContactContainer contact = new ContactContainer();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setSurname(cursor.getString(2));
                contact.setAddress(cursor.getString(3));
                contact.setCity(cursor.getString(4));
                contact.setZipcode(cursor.getString(5));
                contact.setCountry(cursor.getString(6));
                contact.setNumber1(cursor.getString(7));
                contact.setNumber2(cursor.getString(8));
                contact.setMail(cursor.getString(9));
                contact.setImage(cursor.getString(10));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
     
        // return contact list
        return contactList;
    }
     
    // Getting contacts Count
    public int getContactsCount() {
    	String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
    // Updating single contact
    public int updateContact(ContactContainer contact) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_SURNAME, contact.getSurname());
        values.put(KEY_ADDRESS, contact.getAddress());
        values.put(KEY_CITY, contact.getCity());
        values.put(KEY_ZIPCODE, contact.getZipcode());
        values.put(KEY_COUNTRY, contact.getCountry());
        values.put(KEY_NB1, contact.getNumber1());
        values.put(KEY_NB2, contact.getNumber2());
        values.put(KEY_MAIL, contact.getMail());
        values.put(KEY_IMAGE, contact.getImage());
     
        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }
     
    // Deleting single contact
    public void deleteContact(ContactContainer contact) {
    	SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }

}
