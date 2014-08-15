package com.maigrir2000v03;

import com.maigrir2000v03.slidingmenu.model.AgendaContainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class AgendaDetails extends Activity {
	
	AgendaContainer agendacontainer = new AgendaContainer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda_details);
		
		//RECUPERER LES DONNEES DE LA LISTE DE CONTACT\\
				Intent intent = getIntent();
				if (null != intent){

					agendacontainer = (AgendaContainer) intent.getExtras().get("AgendaList");
				}
				
				this.setTitle(agendacontainer.getTitle());
				
				TextView titleText = (TextView) findViewById(R.id.title);
				titleText.setText(agendacontainer.getTitle());

				TextView desc1Text = (TextView) findViewById(R.id.desc1);
				desc1Text.setText(agendacontainer.getDesc1());

				TextView nutText = (TextView) findViewById(R.id.nutritionniste);
				nutText.setText("Nutritionniste: " + agendacontainer.getNutritionniste());
				
				TextView dateEventText = (TextView) findViewById(R.id.dateEvent);
				dateEventText.setText("Évenement le " + agendacontainer.getDate());
				
				TextView desc2Text = (TextView) findViewById(R.id.desc2);
				desc2Text.setText(agendacontainer.getDesc2());
				
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agenda_details, menu);
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
