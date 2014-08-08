package com.maigrir2000v03;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NutrimentFragment extends Fragment {
	
	private ListView ListNutriment;
	private ArrayAdapter<String> adapter;
	
	private String[] nutriments = {
			"Les Protéines", 
			"Les Glucides", 
			"Les Lipides", 
			"Vitamines - Sels Mineraux", 
			"Les Fibres", 
			"L'Eau", 
			};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_nutriment, container, false);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		adapter = new ArrayAdapter<String>(getActivity(), R.layout.simple_listview_row, nutriments);
		ListNutriment = (ListView) getView().findViewById(R.id.ListNutriment);
		ListNutriment.setAdapter(adapter);

	}
}
