package com.maigrir2000v03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
	
	private String[] alimentationDetails = {
			"Elles peuvent être d'origine animale (poisson, viande, œufs, produits laitiers) ou végétale (légumes secs, céréales…).\n\nElles sont indispensables au développement des muscles et de tous les tissus de notre corps.\n\nUn manque de protéines entraîne une dénutrition, une grande fragilité, une mauvaise résistance aux infections, une fonte musculaire importante.", 
			"Les glucides (sucres) représentent le nutriment énergétique le plus important (c’est le carburant).\n\nIl y a deux catégories de glucides :\nLes sucres simples : La plupart des aliments sucrés (confitures, sirops, sucreries…) contiennent des sucres simples à assimilation rapide qui ne  sont pas indispensables à l'organisme. Ce sont des aliments plaisirs.\nLes sucres complexes ou hydrates de carbone ou “ à assimilation lente ” : Les plus intéressants sont ceux à assimilation lente que l'on trouve dans le pain complet, les pâtes al dente, les légumes secs…). Ils sont indispensables car fournissent régulièrement de l'énergie aux muscles. Ils représentent notre carburant.", 
			"Ce sont les graisses. Elles sont d'origine animale (beurre…) ou végétale (huiles, margarines…).\n\nContrairement aux idées reçues, toutes les graisses ne sont pas nocives.\n\nCertains acides gras sont dits essentiels ou indispensables , c'est à dire qu'ils ne sont pas fabriqués par l'organisme. Ils doivent être apportés par l'alimentation\n\nIls sont également nécessaires à l’absorption de certaines vitamines liposolubles d’origine alimentaire (vitamines A, D, E et K).", 
			"LES VITAMINES se trouvent à l'état naturel dans beaucoup d'aliments (fruits, légumes…).\n\nLES SELS MINERAUX sont des métaux ( fer, magnésium, calcium, phosphore, sodium…) présents à très faible dose.\n\nCes substances ne fournissent pas d’énergie à l’organisme.\nElles sont indispensables au déclenchement des réactions chimiques qui se produisent dans notre organisme.\nUne alimentation variée (viande, laitages, légumes, fruits…) permet d’éviter les carences en vitamines et sels minéraux.", 
			"Elles proviennent des végétaux (fruits et légumes,céréales complètes, légumes secs).\n\nCe sont les parties comestibles d’une plante qui ne peuvent être digérées ou absorbées dans l’intestin grêle et parviennent intactes dans le gros intestin.\n\nElles régulent le transit intestinal, la glycémie, le taux de cholestérol, la satiété.", 
			};
	private String[] alimentationSavoir = {
			"Les déchets résultant du métabolisme des protéines (urée, acide urique) sont toxiques pour l’organisme. Ils sont éliminés par les reins,\n Un excès de protéines altère la fonction rénale avec les conséquences qui en résultent (crise de goutte…),\n Un excès de protéine peut également entraîner une décalcification des os.", 
			"Prises en dehors des repas, les sucreries favorisent la prise de poids par stimulation de la libération d'insuline,\nEn cas de libération excessive d'insuline, on observe une entrée accélérée du glucose dans les cellules, qui peut donner lieu à une hypoglycémie dite réactionnelle. Cette hypoglycémie incite à une nouvelle consommation de sucre et au grignotage,\nLes sucreries sont également responsables de caries dentaires.", 
			"Les graisses supportent mal la cuisson.\n\n Lorsqu'ils sont absorbés en trop grande quantité, les lipides favorisent l’obésité et l’apparition des maladies cardio-vasculaires.", 
			"Un excès de vitamines et de sels minéraux est aussi dangereux qu’une carence.\n     => Attention aux compléments absorbés sans avis médical,\n\n L’alimentation actuelle tend à apporter trop de sodium (sel),\n\n Les aliments très riches en sucres et en graisses sont souvent très pauvres en vitamines et sels minéraux.", 
			"Une carence en fibres augmente les risques de constipation et de cancer du colon.", 
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
		
		ListAlimentation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
		    public void onItemClick(AdapterView<?> adapter, View view, int position, long arg)   {

		    // TODO Auto-generated method stub
				Intent i = new Intent(getView().getContext(), AlimentationDetails.class);
				i.putExtra("alimentation", alimentation[position]);
				i.putExtra("alimentationDetails", alimentationDetails[position]);
				i.putExtra("alimentationSavoir", alimentationSavoir[position]);
				startActivity(i);
		    }
		});

	}
}
