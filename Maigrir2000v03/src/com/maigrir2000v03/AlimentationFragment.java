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
			"Quelques conseils pour composer vos repas avec la Nutrition Optimale du Dr Isabelle BRIVET ...\n\n\nLe Petit D�jeuner\n\tUn fruit (vitamines et fibres) ou jus de fruit (vitamines)\n\tUn f�culent : pain, banane � (glucides complexes ou sucres lents)\n\tUn produit laitier (prot�ine et calcium)\n\tUn liquide pour r�hydrater le corps (infusion, chicor�e, th�, caf�, jus de fruit�)\n\nLe D�jeuner\nPensez � varier vos aliments. Il doit comporter des glucides (sucres lents), des prot�ines et des lipides\n\tUn l�gume vert\t->\tUne crudit� (avec une cuill�re d'huile)\n\tPoisson ou �uf ou viande\t->\tUn f�culent (avec une noix de beurre)\n\tDu pain (le sucre lent)\t->\tPoisson ou �uf ou viande\n\tUn fromage\t->\tUn produit laitier\n\nLa Collation\nElle permet de r�guler l�apport de la journ�e. Elle est facultative.\nVariable selon le repas de midi : Fruit, produit laitier, pain�\n\nLe D�ner\nLe repas doit �tre l�ger et au moins deux heures avant le coucher.\n\tUn potage ou un l�gume vert ou une crudit�\n\tPoisson ou viande blanche ou �uf\n\t1 yaourt ou 1 fromage blanc � 20 %\n\tPain ou f�culent (en fonction de l'app�tit)\n\tUn fruit\nEvitez les fromages gras ou les desserts trop riches.", 
			"Ce sont des ingr�dients que l'on incorpore aux produits alimentaires afin d�en modifier les propri�t�s (augmentation de la dur�e de conservation, modification du go�t, de la texture, de la couleur�).\n\n\nEnviron 200 additifs sont autoris�s en France. Ils sont symbolis�s par la lettre E suivie de 3 chiffres (ex : E123, E330�).", 
			"3 grandes cat�gories de produits :\n\n\tCeux dont on a diminu� ou supprim� les graisses,\n\tCeux dont on a diminu� ou supprim� le sucre (ou remplac� par des �dulcorants ou d'autres substances),\n\tCeux dont on a diminu� ou supprim� l'alcool.\n\nTous ont pour objectif de r�duire l'apport �nerg�tique.", 
			"D�chiffrer les �tiquettes des produits que nous consommons ...\n\n\nLa liste des ingr�dients permet de conna�tre ce qui entre dans la composition des produits.\nLes ingr�dients sont class�s par ordre d�croissant, ainsi le premier ingr�dient de la liste est celui qui est pr�sent en plus grande quantit� dans l�aliment.\n\nLa composition nutritionnelle indique les quantit�s des nutriments pr�sents dans l�aliment : prot�ines, glucides, lipides� (en g pour100g d�aliment).", 
			"Qu'est-ce que la malnutrition ?\n\nLa malnutrition d�signe un �tat physiologique d� � une carence ou � une consommation excessive d'un ou plusieurs �l�ments nutritifs.\n\nSous-nutrition dans les pays en voie de d�veloppement.\nSuralimentation dans nos pays d�velopp�s qui conduit � l�ob�sit�, au diab�te, aux maladies cardiovasculaires.\nLes jeunes enfants sont plus vuln�rables car c�est � cette p�riode de la vie qu�ils pr�parent leur capital sant�.", 
			};
	private String[] alimentationSavoir = {
			"Les d�chets r�sultant du m�tabolisme des prot�ines (ur�e, acide urique) sont toxiques pour l�organisme. Ils sont �limin�s par les reins,\n Un exc�s de prot�ines alt�re la fonction r�nale avec les cons�quences qui en r�sultent (crise de goutte�),\n Un exc�s de prot�ine peut �galement entra�ner une d�calcification des os.", 
			"La plupart des bonbons destin�s aux enfants sont essentiellement compos�s d�additifs (colorants, �paississants, g�lifiants, acidifiants pour pimenter le go�t, �dulcorants pour les light, ar�mes�).\nDonc le bonbon seulement pour le plaisir mais pas pour se nourrir !\n\nLes biscuits ap�ritifs sont bien souvent un fourre-tout d'additifs.\nAliments industrialis�s jusqu'� l'extr�me, ils contiennent bien souvent des colorants pour les rendre app�tissants, des exhausteurs de go�t, des agents de texture et beaucoup de sel pour stimuler l'app�tence. Ce ne sont tout simplement pas des aliments. Leur effet est d�vastateur pour notre sant�.\n\nIl faut �galement �tre tr�s vigilant envers les produits laitiers. Certaines cr�me-desserts ne contiennent que de l�eau, des g�lifiants, des ar�mes et du sucre.", 
			"Un produit all�g�, comme le yaourt aux fruits � 0%, peut �tre all�g� en mati�res grasses mais tr�s riche en sucres.\n\nCertains biscuits ou chocolats all�g�s en sucre contiennent plus de graisses.\n\nUne cr�me ou un beurre all�g� contient beaucoup plus d�eau que le produit de base. Il contient �galement beaucoup plus d�additifs pour lui redonner la saveur et la texture du produit d�origine.", 
			"Dans la liste des ingr�dients, le sucre repr�sente le sucre ajout� au produit lors de sa fabrication.\nUne petite compote individuelle de 100g, sucr�e � 25%, contient 5 morceaux de sucre.Un litre de limonade contient 24 morceaux de sucre.\n\nLes glucides indiqu�s dans la composition nutritionnelle d'un aliment correspondent aux sucres totaux (sucre naturellement pr�sents dans l�aliment, additionn�s des sucres ajout�s lors de la fabrication).", 
			"Il est possible d��tre en surpoids et carenc� en nutriments essentiels (vitamines et sels min�raux).\n\nL�ob�sit� n�est que le signal visible d�une mauvaise alimentation. Ce n�est pas parce qu�une personne n�a pas de probl�me de poids que sa sant� n�est pas en danger.", 
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
