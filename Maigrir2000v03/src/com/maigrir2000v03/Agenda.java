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

import com.maigrir2000v03.slidingmenu.adapter.AgendaAdapter;
import com.maigrir2000v03.slidingmenu.adapter.AgendaAdapter;
import com.maigrir2000v03.slidingmenu.model.AgendaContainer;
import com.maigrir2000v03.slidingmenu.model.AgendaContainer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class Agenda extends Activity {
	
	ArrayList<AgendaContainer> AgendaList;
	
	AgendaAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda);
		AgendaList = new ArrayList<AgendaContainer>();
		new JSONAsyncTask().execute("http://sejelm.fr/m2000/getAgenda.php");

		ListView listview = (ListView)findViewById(android.R.id.list);
		adapter = new AgendaAdapter(getApplicationContext(), R.layout.view_agenda, AgendaList);

		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), AgendaList.get(position).getTitle(), Toast.LENGTH_LONG).show();
				
				//PUTEXTRA\\
				AgendaContainer agendacontainer = (AgendaContainer) adapter.getItem(position);
				
				Intent intent = new Intent(getApplicationContext(), AgendaDetails.class);				
				intent.putExtra("AgendaList", agendacontainer);
				
				startActivity(intent);
			}
		});
	}

	class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(Agenda.this);
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

						 AgendaContainer agenda = new AgendaContainer();

						agenda.setTitle(object.getString("TITRE"));
						agenda.setNutritionniste(object.getString("NUTRITIONNISTE"));
						agenda.setDate(object.getString("DATE"));
						agenda.setDesc1(object.getString("DESC1"));
						agenda.setDesc2(object.getString("DESC2"));
						AgendaList.add(agenda);
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

}
