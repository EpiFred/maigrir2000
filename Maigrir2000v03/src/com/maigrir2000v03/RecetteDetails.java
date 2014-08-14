package com.maigrir2000v03;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.maigrir2000v03.slidingmenu.model.RecetteContainer;

public class RecetteDetails extends Activity {

	RecetteContainer recette = new RecetteContainer();
	ProgressDialog pd;
	ImageView iv;
	Bitmap image ;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recette_details);

		Intent intent = getIntent();
		if (intent != null)
		{
			recette = (RecetteContainer) intent.getExtras().get("recette");
		}

		this.setTitle(recette.getTitre());

		TextView temps = (TextView) findViewById(R.id.Temps);
		temps.setText("Préparation: " + recette.getTemps());

		TextView cuisson = (TextView) findViewById(R.id.Cuisson);
		cuisson.setText("Cuisson: " + recette.getCuisson());

		TextView personne = (TextView) findViewById(R.id.Personne);
		personne.setText("Personne: " + recette.getPersonne());

		TextView cout = (TextView) findViewById(R.id.Cout);
		cout.setText("Cout: " + recette.getCout());

		TextView difficulte = (TextView) findViewById(R.id.Difficulte);
		difficulte.setText("Difficulté: " + recette.getDifficulte());

		TextView region = (TextView) findViewById(R.id.Region);
		region.setText("Pays/Région: " + recette.getRegion());

		TextView saison = (TextView) findViewById(R.id.Saison);
		saison.setText("Saison(s): " + recette.getSaison());
		
		TextView desc = (TextView) findViewById(R.id.Description);
		desc.setText(recette.getDescription());

		TextView ingredient = (TextView) findViewById(R.id.Ingredient);
		ingredient.setText(recette.getIngredient());

		TextView preparationTitre = (TextView) findViewById(R.id.PreparationTitre);
		preparationTitre.setText("Préparation " + recette.getTitre());

		TextView preparation = (TextView) findViewById(R.id.Preparation);
		preparation.setText(recette.getPreparation());

		TextView suggestion = (TextView) findViewById(R.id.Suggestion);
		suggestion.setText(recette.getSuggestion());

		iv = (ImageView) findViewById(R.id.RecettePreview);
		
		pd = new ProgressDialog(this);
	    pd.setMessage("Loading..");
	    new TheTask().execute(); 
	}

	class TheTask extends AsyncTask<Void,Void,Void>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd.show();
		}


		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try
			{
				image = downloadBitmap(recette.getPhoto());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			if(image!=null)
			{
				iv.setImageBitmap(image);
			}

		}   
	}
	private Bitmap downloadBitmap(String url) {
		// initilize the default HTTP client object
		final DefaultHttpClient client = new DefaultHttpClient();

		//forming a HttoGet request 
		final HttpGet getRequest = new HttpGet(url);
		try {

			HttpResponse response = client.execute(getRequest);

			//check 200 OK for success
			final int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				Log.w("ImageDownloader", "Error " + statusCode + " while retrieving bitmap from " + url);
				return null;

			}

			final HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = null;
				try {
					// getting contents from the stream 
					inputStream = entity.getContent();

					// decoding stream data back into image Bitmap that android understands
					image = BitmapFactory.decodeStream(inputStream);


				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
					entity.consumeContent();
				}
			}
		} catch (Exception e) {
			// You Could provide a more explicit error message for IOException
			getRequest.abort();
			Log.e("ImageDownloader", "Something went wrong while" + " retrieving bitmap from " + url + e.toString());
		} 

		return image;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recette_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
