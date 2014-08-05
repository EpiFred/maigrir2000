package com.maigrir2000v03;

import com.maigrir2000v03.slidingmenu.model.ContactContainer;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class ContactDetails extends Activity {

	ContactContainer contactcontainer = new ContactContainer();

	private Button bFixe;
	private Button bMobile;
	private Button bMail;

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

		TextView nameText = (TextView) findViewById(R.id.name);
		nameText.setText(contactcontainer.getName() + " " + contactcontainer.getSurname());

		TextView addressText = (TextView) findViewById(R.id.address);
		addressText.setText(contactcontainer.getAddress());

		TextView nAddressText = (TextView) findViewById(R.id.nextAddress);
		nAddressText.setText(contactcontainer.getZipcode() + " " + contactcontainer.getCity() + ", " + contactcontainer.getCountry());

		//BOUTONS APPEL FIXE MOBILE ET EMAIL\\
		bFixe = (Button) findViewById(R.id.bFixe);
		bMobile = (Button) findViewById(R.id.bMobile);
		bMail = (Button) findViewById(R.id.bMail);

		//button listener Fixe
		bFixe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (contactcontainer.getNumber1() != null){
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

				if (contactcontainer.getNumber2() != null){
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
				emailIntent.setData(Uri.parse("mailto:"));
				emailIntent.setType("text/plain");

				emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ contactcontainer.getMail() });
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Maigrir2000 - ");

				try {
					startActivity(Intent.createChooser(emailIntent, "Send mail..."));
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
