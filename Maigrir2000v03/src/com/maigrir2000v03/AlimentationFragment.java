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
			"Les Produits All�g�s", 
			"D�chiffrer les �tiquettes", 
			"La Malnutrition", 
			};
	
	private String[] alimentationDetails = {
			"Elles peuvent �tre d'origine animale (poisson, viande, �ufs, produits laitiers) ou v�g�tale (l�gumes secs, c�r�ales�).\n\nElles sont indispensables au d�veloppement des muscles et de tous les tissus de notre corps.\n\nUn manque de prot�ines entra�ne une d�nutrition, une grande fragilit�, une mauvaise r�sistance aux infections, une fonte musculaire importante.", 
			"Les glucides (sucres) repr�sentent le nutriment �nerg�tique le plus important (c�est le carburant).\n\nIl y a deux cat�gories de glucides :\nLes sucres simples : La plupart des aliments sucr�s (confitures, sirops, sucreries�) contiennent des sucres simples � assimilation rapide qui ne  sont pas indispensables � l'organisme. Ce sont des aliments plaisirs.\nLes sucres complexes ou hydrates de carbone ou � � assimilation lente � : Les plus int�ressants sont ceux � assimilation lente que l'on trouve dans le pain complet, les p�tes al dente, les l�gumes secs�). Ils sont indispensables car fournissent r�guli�rement de l'�nergie aux muscles. Ils repr�sentent notre carburant.", 
			"Ce sont les graisses. Elles sont d'origine animale (beurre�) ou v�g�tale (huiles, margarines�).\n\nContrairement aux id�es re�ues, toutes les graisses ne sont pas nocives.\n\nCertains acides gras sont dits essentiels ou indispensables , c'est � dire qu'ils ne sont pas fabriqu�s par l'organisme. Ils doivent �tre apport�s par l'alimentation\n\nIls sont �galement n�cessaires � l�absorption de certaines vitamines liposolubles d�origine alimentaire (vitamines A, D, E et K).", 
			"LES VITAMINES se trouvent � l'�tat naturel dans beaucoup d'aliments (fruits, l�gumes�).\n\nLES SELS MINERAUX sont des m�taux ( fer, magn�sium, calcium, phosphore, sodium�) pr�sents � tr�s faible dose.\n\nCes substances ne fournissent pas d��nergie � l�organisme.\nElles sont indispensables au d�clenchement des r�actions chimiques qui se produisent dans notre organisme.\nUne alimentation vari�e (viande, laitages, l�gumes, fruits�) permet d��viter les carences en vitamines et sels min�raux.", 
			"Elles proviennent des v�g�taux (fruits et l�gumes,c�r�ales compl�tes, l�gumes secs).\n\nCe sont les parties comestibles d�une plante qui ne peuvent �tre dig�r�es ou absorb�es dans l�intestin gr�le et parviennent intactes dans le gros intestin.\n\nElles r�gulent le transit intestinal, la glyc�mie, le taux de cholest�rol, la sati�t�.", 
			};
	private String[] alimentationSavoir = {
			"Les d�chets r�sultant du m�tabolisme des prot�ines (ur�e, acide urique) sont toxiques pour l�organisme. Ils sont �limin�s par les reins,\n Un exc�s de prot�ines alt�re la fonction r�nale avec les cons�quences qui en r�sultent (crise de goutte�),\n Un exc�s de prot�ine peut �galement entra�ner une d�calcification des os.", 
			"Prises en dehors des repas, les sucreries favorisent la prise de poids par stimulation de la lib�ration d'insuline,\nEn cas de lib�ration excessive d'insuline, on observe une entr�e acc�l�r�e du glucose dans les cellules, qui peut donner lieu � une hypoglyc�mie dite r�actionnelle. Cette hypoglyc�mie incite � une nouvelle consommation de sucre et au grignotage,\nLes sucreries sont �galement responsables de caries dentaires.", 
			"Les graisses supportent mal la cuisson.\n\n Lorsqu'ils sont absorb�s en trop grande quantit�, les lipides favorisent l�ob�sit� et l�apparition des maladies cardio-vasculaires.", 
			"Un exc�s de vitamines et de sels min�raux est aussi dangereux qu�une carence.\n     => Attention aux compl�ments absorb�s sans avis m�dical,\n\n L�alimentation actuelle tend � apporter trop de sodium (sel),\n\n Les aliments tr�s riches en sucres et en graisses sont souvent tr�s pauvres en vitamines et sels min�raux.", 
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
