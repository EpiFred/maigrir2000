package com.maigrir2000v03;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AlimentationDetails extends Activity {

	private TextView AlimentationDesc;
	private TextView AlimentationSavoirTitle;
	private TextView AlimentationSavoir;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alimentation_details);
		
		AlimentationDesc = (TextView) findViewById(R.id.AlimentationDesc);
		AlimentationSavoirTitle = (TextView) findViewById(R.id.AlimentationSavoirTitle);
		AlimentationSavoir = (TextView) findViewById(R.id.AlimentationSavoir);
		
		this.setTitle(getIntent().getExtras().getString("alimentation"));
		
		AlimentationDesc.setText(getIntent().getExtras().getString("alimentationDetails"));
		AlimentationSavoirTitle.setText("A Savoir sur les " + getIntent().getExtras().getString("alimentation"));
		AlimentationSavoir.setText(getIntent().getExtras().getString("alimentationSavoir"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alimentation_details, menu);
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
