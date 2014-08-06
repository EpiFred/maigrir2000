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

import com.maigrir2000v03.slidingmenu.adapter.ContactAdapter;
import com.maigrir2000v03.slidingmenu.model.ContactContainer;

import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Contact extends Activity {

	ArrayList<ContactContainer> ContactList;

	ContactAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		ContactList = new ArrayList<ContactContainer>();
		new JSONAsyncTask().execute("http://sejelm.fr/m2000/getRecipes.php");

		ListView listview = (ListView)findViewById(android.R.id.list);
		adapter = new ContactAdapter(getApplicationContext(), R.layout.view_contact, ContactList);

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
						contact.setMail(object.getString("ADRESSE_MAIL"));
						//contact.setImage(object.getString("PHOTO_NUT"));//EN SUSPENS JUSKA UNE IDEE MEILLEURE
						ContactList.add(contact);
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
				Toast.makeText(getApplicationContext(), "Problème ! Vérifiez votre connexion internet.", Toast.LENGTH_LONG).show();

		}
	}
}
