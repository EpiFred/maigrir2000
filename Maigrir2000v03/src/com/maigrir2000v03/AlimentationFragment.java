package com.maigrir2000v03;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AlimentationFragment extends Fragment {
	
	private ListView ListAlimentation;
	private ArrayAdapter<String> adapter;
	
	private String[] alimentation = {
			"Composer son repas", 
			"Les Additifs Alimentaires", 
			"Les Produits Allégés", 
			"Déchiffrer les étiquettes", 
			"La Malnutrition", 
			};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_alimentation, container, false);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		adapter = new ArrayAdapter<String>(getActivity(),R.layout.simple_listview_row, alimentation);
		ListAlimentation = (ListView) getView().findViewById(R.id.ListAlimentation);
		ListAlimentation.setAdapter(adapter);

	}
}
