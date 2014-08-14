package com.maigrir2000v03;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class NutrimentDetails extends Activity {

	private TextView NutrimentDesc;
	private TextView NutrimentSavoirTitle;
	private TextView NutrimentSavoir;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nutriment_details);
		
		NutrimentDesc = (TextView) findViewById(R.id.NutrimentDesc);
		NutrimentSavoirTitle = (TextView) findViewById(R.id.NutrimentSavoirTitle);
		NutrimentSavoir = (TextView) findViewById(R.id.NutrimentSavoir);
		
		this.setTitle(getIntent().getExtras().getString("nutriments"));
		
		NutrimentDesc.setText(getIntent().getExtras().getString("nutrimentsDetails"));
		NutrimentSavoirTitle.setText("A Savoir sur les " + getIntent().getExtras().getString("nutriments"));
		NutrimentSavoir.setText(getIntent().getExtras().getString("nutrimentsSavoir"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nutriment_details, menu);
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
