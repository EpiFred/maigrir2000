package com.maigrir2000v03;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Imc extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imc);
		// Show the Up button in the action bar.
		setupActionBar();
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
		getMenuInflater().inflate(R.menu.imc, menu);
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
	
	public void calcImc(View v){
		EditText poid = (EditText) findViewById(R.id.poid);

		EditText taille = (EditText) findViewById(R.id.taille);
		ImageView balance = (ImageView) findViewById(R.id.balance);

		if (taille.getText().toString().trim().isEmpty() || poid.getText().toString().trim().isEmpty())
		{
			Toast.makeText(this, "Merci de rentrer une taille et / ou un poid.", Toast.LENGTH_LONG).show();
		}
		else
		{
			String stringTaille = taille.getText().toString();
			String stringPoid = poid.getText().toString();
			double valTaille = Double.parseDouble(stringTaille) / 100;
			double valPoid = Double.parseDouble(stringPoid);
			double result = Math.floor((valPoid / (valTaille * valTaille)) * 100) / 100;
			if (valTaille != 0 && valPoid != 0){
				TextView tvResult = (TextView) findViewById(R.id.result);

				if (result < 18.5){
					tvResult.setText("Resultat: " + result + "\nClassification : Maigreur");
					balance.setImageResource(R.drawable.balance_bleu);
				}
				else if (result > 18.5 && result < 24.9){
					tvResult.setText("Resultat: " + result + "\nClassification : Normal");
					balance.setImageResource(R.drawable.balance_vert);
					
				}
				else if (result > 24.9 && result < 29.9){
					tvResult.setText("Resultat: " + result + "\nClassification : Surpoids");
					balance.setImageResource(R.drawable.balance_orange);
				}
				else if (result > 29.9 && result < 40){
					tvResult.setText("Resultat: " + result + "\nClassification : Obésité");
					balance.setImageResource(R.drawable.balance_rouge);
				}
				else{
					tvResult.setText("Resultat: " + result + "\nClassification : Obésité morbide");
					balance.setImageResource(R.drawable.balance_rouge);
				}
			}
			else{
				Toast.makeText(this, "Merci de rentrer une taille et / ou un poid valide.", Toast.LENGTH_LONG).show();
			}

		}
	}

}
