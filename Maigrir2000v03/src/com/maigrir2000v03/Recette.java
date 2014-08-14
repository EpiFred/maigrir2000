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

import com.maigrir2000v03.Contact.JSONAsyncTask;
import com.maigrir2000v03.helper.DatabaseHandler;
import com.maigrir2000v03.slidingmenu.adapter.RecetteAdapter;
import com.maigrir2000v03.slidingmenu.model.RecetteContainer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Recette extends Activity {

	private Spinner RecetteSpinner;
	private ArrayList<String> RecetteSpinnerList;
	private ArrayAdapter<String> SpinnerAdapter;

	private ListView list;
	private RecetteAdapter adapter;
	private ArrayList<RecetteContainer> recettes;
	
	private DatabaseHandler db;
	
	private static final String RecettesURL = "http://sejelm.fr/m2000/getRecipes.php";
	
	private String[] categoriesIndex = {
			"aperitif",
			"entree froide",
			"entree chaude",
			"plats a base de poisson",
			"plats a base de viande",
			"plats complets",
			"plats vegetariens",
			"accompagnements de plats",
			"desserts",
			"boissons",
			"Assaisonnements",
			"en-cas collation",
			"recettes pour le soir"
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recette);
		// Show the Up button in the action bar.
		setupActionBar();
		
		recettes = new ArrayList<RecetteContainer>();

		RecetteSpinner = (Spinner) findViewById(R.id.RecetteSpinner);
		RecetteSpinnerList = new ArrayList<String>();
		SpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, RecetteSpinnerList);
		initSpinner();
		RecetteSpinner.setAdapter(SpinnerAdapter);
		
		db = new DatabaseHandler(this);

		if (db.getAllRecettes().isEmpty() == true)
			new JSONAsyncTask().execute(RecettesURL);

		list = (ListView) findViewById(R.id.RecetteList);
		
		RecetteSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				recettes.clear();
				for (int i = 0; i < db.getRecettesByCategorie(categoriesIndex[position]).size(); i++)
				{
					recettes.add(db.getRecettesByCategorie(categoriesIndex[position]).get(i));
				}
				adapter = new RecetteAdapter(Recette.this, R.layout.view_recette, recettes);
				list.setAdapter(adapter);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void initSpinner() {
		RecetteSpinnerList.add("Ap�ritifs");
		RecetteSpinnerList.add("Entr�es Froides");
		RecetteSpinnerList.add("Entr�es Chaudes");
		RecetteSpinnerList.add("Plats a base de poisson");
		RecetteSpinnerList.add("Plats a base de viande");
		RecetteSpinnerList.add("Plats Complets");
		RecetteSpinnerList.add("Plats V�g�tariens");
		RecetteSpinnerList.add("Accompagnements de plats");
		RecetteSpinnerList.add("Desserts");
		RecetteSpinnerList.add("Boissons");
		RecetteSpinnerList.add("Assaisonnements");
		RecetteSpinnerList.add("En-cas, collation");
		RecetteSpinnerList.add("Recettes pour le soir");


	}

	class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(Recette.this);
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

		protected void onPostExecute(Boolean result) {
			dialog.cancel();
			adapter.notifyDataSetChanged();
			if(result == false)
				Toast.makeText(getApplicationContext(), "Probl�me! V�rifiez votre connexion internet.", Toast.LENGTH_LONG).show();

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
		getMenuInflater().inflate(R.menu.recette, menu);
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
