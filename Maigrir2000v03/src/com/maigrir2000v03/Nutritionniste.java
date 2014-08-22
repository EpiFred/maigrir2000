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
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maigrir2000v03.Contact.JSONAsyncTask;
import com.maigrir2000v03.helper.DatabaseHandler;
import com.maigrir2000v03.slidingmenu.model.ContactContainer;

import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class Nutritionniste extends Activity {

	//static final LatLng name = new LatLng(lat, long);
	static final LatLng France = new LatLng(46.227638, 2.213749000000007);

	static final LatLng CLagouche = new LatLng(45.7899723, 4.857660200000055);
	static final LatLng FAnterieux = new LatLng(45.6776853, 4.770113199999969);
	static final LatLng VOertel = new LatLng(45.7528096, 4.8520780999999715);
	static final LatLng IBrivet = new LatLng(47.0623377, 5.494086899999957);
	
	static final LatLng EPorcher = new LatLng(48.804286, 2.1583619000000454);
	static final LatLng SLSViolant = new LatLng(48.908587, 2.149776999999972);
	static final LatLng EGouacide = new LatLng(48.691442, 2.3750451999999314);
	static final LatLng EPorcher2 = new LatLng(48.80753439999999, 2.186664999999948);
	static final LatLng RSantarelli = new LatLng(43.5753306, 1.4633410999999796);
	static final LatLng MPagliardini = new LatLng(44.8371438, -0.5768442999999479);
	static final LatLng ASaunois = new LatLng(48.120219, -1.6547044999999798);
	static final LatLng AAdam = new LatLng(50.67637089999999, 3.1419255000000703);
	static final LatLng AMougenot = new LatLng(48.68008409999999, 6.190898199999992);
	static final LatLng OGodard = new LatLng(47.804899, 3.532801000000063);
	static final LatLng CdvIweins = new LatLng(44.5796551, 4.731993099999954);
	
	private GoogleMap gmap;
	private DatabaseHandler db;
	private static final String ContactURL = "http://goodme.fr/app/getContacts.php";
	private ArrayList<ContactContainer> contacts;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nutritionniste);
		// Show the Up button in the action bar.
		setupActionBar();

		gmap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		gmap.setMyLocationEnabled(true);

		if (db.getAllContacts().isEmpty() == true)
			new JSONAsyncTask().execute(ContactURL);
		contacts = new ArrayList<ContactContainer>();
		contacts = db.getAllContacts();
		for(int i = 0; i < contacts.size(); i++)
		{
			gmap.addMarker(new MarkerOptions().position(new LatLng(contacts.get(i).getLatitude(), contacts.get(i).getLongitude())).title(contacts.get(i).getName() + " " + contacts.get(i).getSurname()).snippet(contacts.get(i).getAddress() + " - " + contacts.get(i).getZipcode() + " " + contacts.get(i).getCity()));
		}
		
		/*Marker clagouche = gmap.addMarker(new MarkerOptions().position(CLagouche).title("Cécile Lagouche").snippet("122 Grande rue de Saint Clair - 69300 CALUIRE"));
		Marker fanterieux = gmap.addMarker(new MarkerOptions().position(FAnterieux).title("Florence Antérieux").snippet("2A route de Lyon - 69530 Brignais "));
		Marker voertel = gmap.addMarker(new MarkerOptions().position(VOertel).title("Valérie Oertel").snippet("310 rue André Philip - 69003 Lyon"));
		Marker ibrivet = gmap.addMarker(new MarkerOptions().position(IBrivet).title("Isabelle Brivet").snippet("447, Avenue du Maréchal Juin - 39100 Dole (Crissey)"));
		Marker eporcher = gmap.addMarker(new MarkerOptions().position(EPorcher).title("Eve Porcher").snippet("Cabinet Diététique 15 Place Royal - 78000 VERSAILLES"));
		Marker eporcher2 = gmap.addMarker(new MarkerOptions().position(EPorcher2).title("Eve Porcher").snippet("1713, Avenue Roger Salengro 3ème étage (ascenseur) - 92370 Chaville"));
		Marker slsviolant = gmap.addMarker(new MarkerOptions().position(SLSViolant).title("Sylvie Le Souder-Violant").snippet("78360 MONTESSON"));
		Marker egouacide = gmap.addMarker(new MarkerOptions().position(EGouacide).title("Emeline Gouacide").snippet("5 rue du docteur vinot - 91260 Juvisy sur Orge"));
		Marker rsantarelli = gmap.addMarker(new MarkerOptions().position(RSantarelli).title("Raphaëlle Santarelli").snippet("8 rue Claude Forbin - 31400 Toulouse"));*/
		
		
		// Move the camera instantly to Lyon with a zoom of 15.
		gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(France, 5));

		// Zoom in, animating the camera.
		//gmap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);*/
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
		}
		return super.onOptionsItemSelected(item);
	}

}
