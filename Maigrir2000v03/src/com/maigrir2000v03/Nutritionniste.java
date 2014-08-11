package com.maigrir2000v03;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class Nutritionniste extends Activity {

	static final LatLng LYON = new LatLng(45.750, 4.850);
	static final LatLng VENISSIEUX = new LatLng(45.700, 4.885);
	private GoogleMap gmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nutritionniste);
		// Show the Up button in the action bar.
		setupActionBar();
		
		gmap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		Marker lyon = gmap.addMarker(new MarkerOptions().position(LYON).title("Lyon"));
		Marker ven = gmap.addMarker(new MarkerOptions().position(VENISSIEUX).title("Vénissieux").snippet("692 la trick"));
		
		// Move the camera instantly to Lyon with a zoom of 15.
		gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(LYON, 15));
		
		// Zoom in, animating the camera.
		gmap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
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
		getMenuInflater().inflate(R.menu.nutritionniste, menu);
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
