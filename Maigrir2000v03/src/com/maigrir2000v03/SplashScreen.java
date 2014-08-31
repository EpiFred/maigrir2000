package com.maigrir2000v03;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.maigrir2000v03.helper.DatabaseHandler;
import com.maigrir2000v03.slidingmenu.model.ContactContainer;
import com.maigrir2000v03.slidingmenu.model.RecetteContainer;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashScreen extends Activity {

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;
	DatabaseHandler db;
	private Boolean check = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		db = new DatabaseHandler(this);
		if (db.getAllContacts().isEmpty() == true)
		{
			new ContactData().execute("http://goodme.fr/app/getContacts.php");
		}

		if (db.getAllRecettes().isEmpty() == true)
		{
			new RecetteData().execute("http://goodme.fr/app/getRecipes.php");
		}
		new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
				startActivity(i);

				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);
	}
	/**
	 * Async Task to make http call
	 */
	private class ContactData extends AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// before making http calls         

		}

		@Override
		protected Boolean doInBackground(String... urls) {
			try {

				//------------------>>
				HttpGet httppost = new HttpGet(urls[0]);
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response = httpclient.execute(httppost);

				// StatusLine stat = response.getStatusLine();
				int status = response.getStatusLine().getStatusCode();

				if (status == 200) {
					HttpEntity entity = response.getEntity();
					String data = EntityUtils.toString(entity);


					JSONArray ja = new JSONArray(data);

					for (int i = 0; i < ja.length(); i++) {
						JSONObject object = ja.getJSONObject(i);

						/// PENSER A FAIRE ContactList.add(new ContactContainer("Kevin", "Nougat", "Bron", 5689, "Hehe", "Hoho")); UNE FOIS TOUT INTEGRER

						ContactContainer contact = new ContactContainer();

						contact.setName(object.getString("PRENOM"));
						contact.setSurname(object.getString("NOM"));
						contact.setAddress(object.getString("ADRESSE"));
						contact.setCity(object.getString("VILLE"));
						contact.setZipcode(object.getString("CODE_POSTAL"));
						contact.setCountry(object.getString("PAYS"));
						contact.setNumber1(object.getString("TELEPHONE"));
						contact.setNumber2(object.getString("TELEPHONE_2"));
						//contact.setMail("freddy.louvier@gmail.com");
						contact.setMail(object.getString("ADRESSE_MAIL"));
						contact.setImage(object.getString("PHOTO_NUT"));//EN SUSPENS JUSKA UNE IDEE MEILLEURE
						contact.setLongitude(object.getDouble("LONGITUDE"));
						contact.setLatitude(object.getDouble("LATITUDE"));
						db.addContact(contact);
						//ContactList.add(contact);
					}
					return true;
				}

				//------------------>>

			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			//Intent i = new Intent(SplashScreen.this, MainActivity.class);
			//startActivity(i);

			// close this activity
			//finish();
		}

	}/**
	 * Async Task to make http call
	 */
	private class RecetteData extends AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// before making http calls         

		}

		@Override
		protected Boolean doInBackground(String... urls) {
			try {

				//------------------>>
				HttpGet httppost = new HttpGet(urls[0]);
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response = httpclient.execute(httppost);

				// StatusLine stat = response.getStatusLine();
				int status = response.getStatusLine().getStatusCode();

				if (status == 200) {
					HttpEntity entity = response.getEntity();
					String data = EntityUtils.toString(entity);


					JSONArray ja = new JSONArray(data);

					for (int i = 0; i < ja.length(); i++) {
						JSONObject object = ja.getJSONObject(i);

						RecetteContainer recette = new RecetteContainer();

						recette.setTitre(object.getString("TITRE"));
						recette.setCategorie(object.getString("CATEGORIE"));
						recette.setDescription(object.getString("DESCRIPTION"));
						recette.setTemps(object.getString("TEMPS_PREPARATION"));
						recette.setCuisson(object.getString("CUISSON"));
						recette.setPersonne(object.getString("PERSONNE"));
						recette.setCout(object.getString("COUT"));
						recette.setDifficulte(object.getString("DIFFICULTE"));
						recette.setRegion(object.getString("REGION"));
						recette.setSaison(object.getString("SAISON"));
						recette.setIngredient(object.getString("INGREDIENT"));
						recette.setPreparation(object.getString("PREPARATION"));
						recette.setSuggestion(object.getString("SUGGESTION"));
						recette.setPhoto(object.getString("PHOTO"));
						db.addRecette(recette);
						//contact.setImage(object.getString("PHOTO_NUT"));//EN SUSPENS JUSKA UNE IDEE MEILLEURE
					}
					return true;
				}

				//------------------>>

			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			//Intent i = new Intent(SplashScreen.this, MainActivity.class);
			//startActivity(i);

			// close this activity
			//finish();
		}

	}

}