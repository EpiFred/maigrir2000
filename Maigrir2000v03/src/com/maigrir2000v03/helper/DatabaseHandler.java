package com.maigrir2000v03.helper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.maigrir2000v03.slidingmenu.model.ContactContainer;
import com.maigrir2000v03.slidingmenu.model.RecetteContainer;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "Maigrir2000";

	// Contacts table name
	private static final String TABLE_CONTACTS = "contacts";
	private static final String TABLE_RECETTES = "recettes";

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
	private static final String KEY_LAT = "lat";
	private static final String KEY_LONG = "long";


	private static final String KEY_ID2 = "id";
	private static final String KEY_TITRE = "titre";
	private static final String KEY_CATEGORIE = "categorie";
	private static final String KEY_DESC = "description";
	private static final String KEY_TEMPS = "temps";
	private static final String KEY_CUISSON = "cuisson";
	private static final String KEY_PERSONNE = "personne";
	private static final String KEY_COUT = "cout";
	private static final String KEY_DIFFICULTE = "difficulte";
	private static final String KEY_REGION = "region";
	private static final String KEY_SAISON = "saison";
	private static final String KEY_INGREDIENT = "ingredient";
	private static final String KEY_PREPARATION = "preparation";
	private static final String KEY_SUGGESTION = "suggestion";
	private static final String KEY_PHOTO = "photo";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		try{

			String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
					+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
					+ KEY_SURNAME + " TEXT," + KEY_ADDRESS + " TEXT," 
					+ KEY_CITY + " TEXT," +KEY_ZIPCODE + " TEXT," +KEY_COUNTRY + " TEXT," 
					+ KEY_NB1 + " TEXT," + KEY_NB2 + " TEXT," +KEY_MAIL + " TEXT," +KEY_IMAGE + " TEXT," + KEY_LAT + " DOUBLE," + KEY_LONG + " DOUBLE" + ")";

			String CREATE_RECETTES_TABLE = "CREATE TABLE " + TABLE_RECETTES + "("
					+ KEY_ID2 + " INTEGER PRIMARY KEY," + KEY_TITRE + " TEXT," + KEY_CATEGORIE + " TEXT," + KEY_DESC + " TEXT,"
					+ KEY_TEMPS + " TEXT," + KEY_CUISSON + " TEXT," + KEY_PERSONNE + " TEXT," + KEY_COUT + " TEXT,"
					+ KEY_DIFFICULTE + " TEXT," + KEY_REGION + " TEXT," + KEY_SAISON + " TEXT," + KEY_INGREDIENT + " TEXT,"
					+ KEY_PREPARATION + " TEXT," + KEY_SUGGESTION + " TEXT," + KEY_PHOTO + " TEXT" + ")";

			db.execSQL(CREATE_CONTACTS_TABLE);
			db.execSQL(CREATE_RECETTES_TABLE);
		}
		catch (SQLException se) {
			Log.v("DatabaseHandler Oncreate SQLException",
					Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v("DatabaseHandler Oncreate Exception",
					Log.getStackTraceString(e));
		}
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			// Drop older table if existed
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECETTES);

			// Create tables again
			onCreate(db);
		} catch (SQLException se) {
			Log.v("DatabaseHandler onUpgrade SQLException",
					Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v("DatabaseHandler onUpgrade Exception",
					Log.getStackTraceString(e));
		}
	}

	// Adding new contact
	public void addContact(ContactContainer contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		try {
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
			values.put(KEY_LONG, contact.getLongitude()); // Contact Name
			values.put(KEY_LAT, contact.getLatitude()); // Contact Name

			// Inserting Row
			db.insert(TABLE_CONTACTS, null, values);
		} catch (SQLiteException se) {
			Log.v("DatabaseHandler insertStudentRecord Exception",
					Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v("DatabaseHandler insertStudentRecord Exception",
					Log.getStackTraceString(e));
		} finally {
			db.close();
		}
	}

	// Getting single contact
	public ContactContainer getContact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
				KEY_NAME, KEY_SURNAME, KEY_ADDRESS, KEY_CITY, KEY_ZIPCODE, KEY_COUNTRY, KEY_NB1, KEY_NB2, KEY_MAIL, KEY_IMAGE, KEY_LONG, KEY_LAT }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		ContactContainer contact = new ContactContainer(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
				cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getDouble(11), cursor.getDouble(12));
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
				contact.setLongitude(cursor.getDouble(11));
				contact.setLatitude(cursor.getDouble(12));
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

	// Deleting single contact
	public void deleteContact(ContactContainer contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getId()) });
		db.close();
	}
	
	public void deleteAllContact() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, null, null);
		db.close();
	}


	public void addRecette(RecetteContainer rct) {
		SQLiteDatabase db = this.getWritableDatabase();

		try {
			ContentValues values = new ContentValues();
			values.put(KEY_TITRE, rct.getTitre()); 
			values.put(KEY_CATEGORIE, rct.getCategorie()); 
			values.put(KEY_DESC, rct.getDescription()); 
			values.put(KEY_TEMPS, rct.getTemps()); 
			values.put(KEY_CUISSON, rct.getCuisson());
			values.put(KEY_PERSONNE, rct.getPersonne());
			values.put(KEY_COUT, rct.getCout());
			values.put(KEY_DIFFICULTE, rct.getDifficulte());
			values.put(KEY_REGION, rct.getRegion());
			values.put(KEY_SAISON, rct.getSaison());
			values.put(KEY_INGREDIENT, rct.getIngredient());
			values.put(KEY_PREPARATION, rct.getPreparation());
			values.put(KEY_SUGGESTION, rct.getSuggestion());
			values.put(KEY_PHOTO, rct.getPhoto());

			// Inserting Row
			db.insert(TABLE_RECETTES, null, values);
		} catch (SQLiteException se) {
			Log.v("DatabaseHandler insertStudentRecord Exception",
					Log.getStackTraceString(se));
		} catch (Exception e) {
			Log.v("DatabaseHandler insertStudentRecord Exception",
					Log.getStackTraceString(e));
		} finally {
			db.close();
		}
	}

	public RecetteContainer getRecette(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_RECETTES, new String[] { KEY_ID,
				KEY_TITRE, KEY_CATEGORIE, KEY_DESC, KEY_TEMPS, KEY_CUISSON, KEY_PERSONNE, KEY_COUT, KEY_DIFFICULTE, KEY_REGION, KEY_SAISON, KEY_INGREDIENT, KEY_PREPARATION, KEY_SUGGESTION, KEY_PHOTO }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		RecetteContainer recette = new RecetteContainer(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
				cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10),
				cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14));
		// return contact
		return recette;
	}

	public ArrayList<RecetteContainer> getAllRecettes() {
		ArrayList<RecetteContainer> recetteList = new ArrayList<RecetteContainer>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_RECETTES;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				RecetteContainer rct = new RecetteContainer();
				rct.setId(Integer.parseInt(cursor.getString(0)));
				rct.setTitre(cursor.getString(1));
				rct.setCategorie(cursor.getString(2));
				rct.setDescription(cursor.getString(3));
				rct.setTemps(cursor.getString(4));
				rct.setCuisson(cursor.getString(5));
				rct.setPersonne(cursor.getString(6));
				rct.setCout(cursor.getString(7));
				rct.setDifficulte(cursor.getString(8));
				rct.setRegion(cursor.getString(9));
				rct.setSaison(cursor.getString(10));
				rct.setIngredient(cursor.getString(11));
				rct.setPreparation(cursor.getString(12));
				rct.setSuggestion(cursor.getString(13));
				rct.setPhoto(cursor.getString(14));
				// Adding contact to list
				recetteList.add(rct);
			} while (cursor.moveToNext());
		}

		// return contact list
		return recetteList;
	}

	public ArrayList<RecetteContainer> getRecettesByCategorie(String categorie) {
		ArrayList<RecetteContainer> recetteList = new ArrayList<RecetteContainer>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_RECETTES;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {				
				if (cursor.getString(2).equals(categorie))
				{
					RecetteContainer rct = new RecetteContainer();
					rct.setId(Integer.parseInt(cursor.getString(0)));
					rct.setTitre(cursor.getString(1));
					rct.setCategorie(cursor.getString(2));
					rct.setDescription(cursor.getString(3));
					rct.setTemps(cursor.getString(4));
					rct.setCuisson(cursor.getString(5));
					rct.setPersonne(cursor.getString(6));
					rct.setCout(cursor.getString(7));
					rct.setDifficulte(cursor.getString(8));
					rct.setRegion(cursor.getString(9));
					rct.setSaison(cursor.getString(10));
					rct.setIngredient(cursor.getString(11));
					rct.setPreparation(cursor.getString(12));
					rct.setSuggestion(cursor.getString(13));
					rct.setPhoto(cursor.getString(14));
					// Adding contact to list
					recetteList.add(rct);
				}
			} while (cursor.moveToNext());
		}

		// return contact list
		return recetteList;
	}

	public int getRecettesCount() {
		String countQuery = "SELECT  * FROM " + TABLE_RECETTES;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
	
	public void deleteRecette() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_RECETTES, null, null);
		db.close();
	}
}
