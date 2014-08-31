package com.maigrir2000v03;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maigrir2000v03.Contact.JSONAsyncTask;
import com.maigrir2000v03.helper.DatabaseHandler;
import com.maigrir2000v03.slidingmenu.model.ContactContainer;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.app.NavUtils;


public class Nutritionniste extends Activity {

	//static final LatLng name = new LatLng(lat, long);
	static final LatLng France = new LatLng(46.227638, 2.213749000000007);
	//static final LatLng CLagouche = new LatLng(45.7899723, 4.857660200000055);

	private DatabaseHandler db;
	private static final String ContactURL = "http://goodme.fr/app/getContacts.php";
	private ArrayList<ContactContainer> contacts;

	private GoogleMap gmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nutritionniste);
		// Show the Up button in the action bar.
		setupActionBar();
		gmap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		gmap.setMyLocationEnabled(true);

		db = new DatabaseHandler(this);
		contacts = new ArrayList<ContactContainer>();

		if (db.getAllContacts().isEmpty() == true)
			new JSONAsyncTask().execute(ContactURL);


		contacts = db.getAllContacts();
		for(int i = 0; i < contacts.size(); i++)
		{
			gmap.addMarker(new MarkerOptions().position(new LatLng(contacts.get(i).getLatitude(), contacts.get(i).getLongitude())).title(contacts.get(i).getName() + " " + contacts.get(i).getSurname()).snippet(contacts.get(i).getAddress() + " - " + contacts.get(i).getZipcode() + " " + contacts.get(i).getCity()));
		}

		// Move the camera instantly to Lyon with a zoom of 15.
		gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(France, 5));
		gmap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

			@Override
			public void onInfoWindowClick(Marker marker) {
				// TODO Auto-generated method stub
				//String uri = "geo:"+ marker.getPosition().latitude + "," + marker.getPosition().longitude;
				startActivity(new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(
						"http://maps.google.com/maps?" +
								"saddr=" +
								"&daddr=" + marker.getPosition().latitude + "," + marker.getPosition().longitude)));
			}


		});
	}

	class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(Nutritionniste.this);
			dialog.setMessage("Loading, please wait");
			dialog.setTitle("Connecting server");
			dialog.show();
			dialog.setCancelable(false);
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
						contact.setMail(object.getString("ADRESSE_MAIL"));
						contact.setImage(object.getString("PHOTO_NUT"));
						contact.setLatitude(object.getDouble("LATITUDE"));
						contact.setLongitude(object.getDouble("LONGITUDE"));
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

		protected void onPostExecute(Boolean result) {
			dialog.cancel();
			if(result == false)
				Toast.makeText(getApplicationContext(), "Problème! Vérifiez votre connexion internet.", Toast.LENGTH_LONG).show();

		}
	}


	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nutritionniste, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;

		case R.id.action_refresh:
			db.deleteAllContact();
			new JSONAsyncTask().execute(ContactURL);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}