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

import com.maigrir2000v03.Recette.JSONAsyncTask;
import com.maigrir2000v03.helper.DatabaseHandler;
import com.maigrir2000v03.slidingmenu.adapter.ContactAdapter;
import com.maigrir2000v03.slidingmenu.model.ContactContainer;

import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Contact extends Activity {

	private static final String ContactURL = "http://goodme.fr/app/getContacts.php";

	ArrayList<ContactContainer> ContactList;

	ContactAdapter adapter;

	DatabaseHandler db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		
		setupActionBar();

		db = new DatabaseHandler(this);
		ContactList = new ArrayList<ContactContainer>();
		if (db.getAllContacts().isEmpty() == true)
			new JSONAsyncTask().execute(ContactURL);

		ListView listview = (ListView)findViewById(android.R.id.list);
		ContactList = db.getAllContacts();
		adapter = new ContactAdapter(getApplicationContext(), R.layout.view_contact, ContactList);

		//adapter = new ContactAdapter(getApplicationContext(), R.layout.view_contact, ContactList);

		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), ContactList.get(position).getName(), Toast.LENGTH_LONG).show();

				//PUTEXTRA\\
				ContactContainer contactcontainer = (ContactContainer) adapter.getItem(position);

				Intent intent = new Intent(getApplicationContext(), ContactDetails.class);				
				intent.putExtra("contactlist", contactcontainer);

				startActivity(intent);
			}
		});
	}

	class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(Contact.this);
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
			adapter.notifyDataSetChanged();
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
		getMenuInflater().inflate(R.menu.contact, menu);
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
		case R.id.action_contact:
			db.deleteAllContact();
			new JSONAsyncTask().execute(ContactURL);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
