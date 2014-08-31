package com.maigrir2000v03;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.maigrir2000v03.RecetteDetails.TheTask;
import com.maigrir2000v03.slidingmenu.model.ContactContainer;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.R.string;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class ContactDetails extends Activity {

	ContactContainer contactcontainer = new ContactContainer();

	private ImageButton bFixe;
	private ImageButton bMobile;
	private ImageButton bMail;
	
	ProgressDialog pd;
	ImageView photo;
	Bitmap image ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_details);
		// Show the Up button in the action bar.
		setupActionBar();


		//RECUPERER LES DONNEES DE LA LISTE DE CONTACT\\
		Intent intent = getIntent();
		if (null != intent){

			contactcontainer = (ContactContainer) intent.getExtras().get("contactlist");
		}

		photo = (ImageView) findViewById(R.id.ContactPhoto);
		pd = new ProgressDialog(this);
	    pd.setMessage("Loading..");
	    new TheTask().execute();
		//photo.setBackgroundResource(R.drawable.logo_doctor);
		
	    this.setTitle(contactcontainer.getName() + " " + contactcontainer.getSurname());
	    
		TextView nameText = (TextView) findViewById(R.id.name);
		nameText.setText(contactcontainer.getName() + " " + contactcontainer.getSurname());

		TextView addressText = (TextView) findViewById(R.id.address);
		addressText.setText(contactcontainer.getAddress());

		TextView nAddressText = (TextView) findViewById(R.id.nextAddress);
		nAddressText.setText(contactcontainer.getZipcode() + " " + contactcontainer.getCity() + ", " + contactcontainer.getCountry());

		//BOUTONS APPEL FIXE MOBILE ET EMAIL\\
		bFixe = (ImageButton) findViewById(R.id.bFixe);
		bMobile = (ImageButton) findViewById(R.id.bMobile);
		bMail = (ImageButton) findViewById(R.id.bMail);

		//button listener Fixe
		bFixe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (!contactcontainer.getNumber1().isEmpty()){
					makeCall(contactcontainer.getNumber1());
				}else{
					Toast.makeText(getApplicationContext(), "Pas de numero fixe. Essayez le mobile.", Toast.LENGTH_SHORT).show();
				}

			}

		});

		//button listener Mobile
		bMobile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (!contactcontainer.getNumber2().isEmpty()){
					makeCall(contactcontainer.getNumber2());
				}else{
					Toast.makeText(getApplicationContext(), "Pas de numero mobile. Essayez le fixe.", Toast.LENGTH_SHORT).show();
				}

			}

		});

		//button listener Mail
		bMail.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Log.i("Send email", "");

				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				
				//emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {contactcontainer.getMail()});
				emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {contactcontainer.getMail()});
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Maigrir2000 - ");
				emailIntent.setType("message/rfc822");

				try {
					startActivity(Intent.createChooser(emailIntent, "Ecrire au nutritionniste..."));
					finish();
					Log.i("Finished sending email...", "");
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
				}
			}

		});


	}

	public void makeCall(String number){
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:" + number));
		startActivity(callIntent);
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
				image = downloadBitmap(contactcontainer.getImage());
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
				photo.setImageBitmap(image);
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
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact__details, menu);
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
