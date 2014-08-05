package com.maigrir2000v03;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class HomeFragment extends Fragment {

	private ImageButton Imc;
	private ImageButton Reseau;
	private ImageButton Recette;
	private ImageButton Contact;
	private ImageButton Nutritionniste;
	private ImageButton Nutrition;
	private ImageButton Regime;


	public HomeFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home, container, false);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initButtons();

	}
	
	private void initButtons()
	{
		Imc = (ImageButton) getView().findViewById(R.id.imc);
		Imc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), Imc.class));

			}
		});
		
		Reseau = (ImageButton) getView().findViewById(R.id.reseau);
		Reseau.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), Reseau.class));
			}
		});
		Recette = (ImageButton) getView().findViewById(R.id.recettes);
		Recette.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), Recette.class));

			}
		});		
		Contact = (ImageButton) getView().findViewById(R.id.contact);
		Contact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), Contact.class));

			}
		});
		Nutritionniste = (ImageButton) getView().findViewById(R.id.nutritionniste);
		Nutritionniste.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), Nutritionniste.class));

			}
		});
		Nutrition = (ImageButton) getView().findViewById(R.id.nutrition);
		Nutrition.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), Nutrition.class));

			}
		});
		Regime = (ImageButton) getView().findViewById(R.id.regime);
		Regime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), Regime.class));

			}
		});
	}
}
