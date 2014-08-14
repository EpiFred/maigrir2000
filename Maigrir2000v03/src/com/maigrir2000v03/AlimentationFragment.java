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
			"Quelques conseils pour composer vos repas avec la Nutrition Optimale du Dr Isabelle BRIVET ...\n\n\nLe Petit Déjeuner\n\tUn fruit (vitamines et fibres) ou jus de fruit (vitamines)\n\tUn féculent : pain, banane … (glucides complexes ou sucres lents)\n\tUn produit laitier (protéine et calcium)\n\tUn liquide pour réhydrater le corps (infusion, chicorée, thé, café, jus de fruit…)\n\nLe Déjeuner\nPensez à varier vos aliments. Il doit comporter des glucides (sucres lents), des protéines et des lipides\n\tUn légume vert\t->\tUne crudité (avec une cuillère d'huile)\n\tPoisson ou œuf ou viande\t->\tUn féculent (avec une noix de beurre)\n\tDu pain (le sucre lent)\t->\tPoisson ou œuf ou viande\n\tUn fromage\t->\tUn produit laitier\n\nLa Collation\nElle permet de réguler l’apport de la journée. Elle est facultative.\nVariable selon le repas de midi : Fruit, produit laitier, pain…\n\nLe Dîner\nLe repas doit être léger et au moins deux heures avant le coucher.\n\tUn potage ou un légume vert ou une crudité\n\tPoisson ou viande blanche ou œuf\n\t1 yaourt ou 1 fromage blanc à 20 %\n\tPain ou féculent (en fonction de l'appétit)\n\tUn fruit\nEvitez les fromages gras ou les desserts trop riches.", 
			"Ce sont des ingrédients que l'on incorpore aux produits alimentaires afin d’en modifier les propriétés (augmentation de la durée de conservation, modification du goût, de la texture, de la couleur…).\n\n\nEnviron 200 additifs sont autorisés en France. Ils sont symbolisés par la lettre E suivie de 3 chiffres (ex : E123, E330…).", 
			"3 grandes catégories de produits :\n\n\tCeux dont on a diminué ou supprimé les graisses,\n\tCeux dont on a diminué ou supprimé le sucre (ou remplacé par des édulcorants ou d'autres substances),\n\tCeux dont on a diminué ou supprimé l'alcool.\n\nTous ont pour objectif de réduire l'apport énergétique.", 
			"Déchiffrer les étiquettes des produits que nous consommons ...\n\n\nLa liste des ingrédients permet de connaître ce qui entre dans la composition des produits.\nLes ingrédients sont classés par ordre décroissant, ainsi le premier ingrédient de la liste est celui qui est présent en plus grande quantité dans l’aliment.\n\nLa composition nutritionnelle indique les quantités des nutriments présents dans l’aliment : protéines, glucides, lipides… (en g pour100g d’aliment).", 
			"Qu'est-ce que la malnutrition ?\n\nLa malnutrition désigne un état physiologique dû à une carence ou à une consommation excessive d'un ou plusieurs éléments nutritifs.\n\nSous-nutrition dans les pays en voie de développement.\nSuralimentation dans nos pays développés qui conduit à l’obésité, au diabète, aux maladies cardiovasculaires.\nLes jeunes enfants sont plus vulnérables car c’est à cette période de la vie qu’ils préparent leur capital santé.", 
			};
	private String[] alimentationSavoir = {
			"Les déchets résultant du métabolisme des protéines (urée, acide urique) sont toxiques pour l’organisme. Ils sont éliminés par les reins,\n Un excès de protéines altère la fonction rénale avec les conséquences qui en résultent (crise de goutte…),\n Un excès de protéine peut également entraîner une décalcification des os.", 
			"La plupart des bonbons destinés aux enfants sont essentiellement composés d’additifs (colorants, épaississants, gélifiants, acidifiants pour pimenter le goût, édulcorants pour les light, arômes…).\nDonc le bonbon seulement pour le plaisir mais pas pour se nourrir !\n\nLes biscuits apéritifs sont bien souvent un fourre-tout d'additifs.\nAliments industrialisés jusqu'à l'extrême, ils contiennent bien souvent des colorants pour les rendre appétissants, des exhausteurs de goût, des agents de texture et beaucoup de sel pour stimuler l'appétence. Ce ne sont tout simplement pas des aliments. Leur effet est dévastateur pour notre santé.\n\nIl faut également être très vigilant envers les produits laitiers. Certaines crème-desserts ne contiennent que de l’eau, des gélifiants, des arômes et du sucre.", 
			"Un produit allégé, comme le yaourt aux fruits à 0%, peut être allégé en matières grasses mais très riche en sucres.\n\nCertains biscuits ou chocolats allégés en sucre contiennent plus de graisses.\n\nUne crème ou un beurre allégé contient beaucoup plus d’eau que le produit de base. Il contient également beaucoup plus d’additifs pour lui redonner la saveur et la texture du produit d’origine.", 
			"Dans la liste des ingrédients, le sucre représente le sucre ajouté au produit lors de sa fabrication.\nUne petite compote individuelle de 100g, sucrée à 25%, contient 5 morceaux de sucre.Un litre de limonade contient 24 morceaux de sucre.\n\nLes glucides indiqués dans la composition nutritionnelle d'un aliment correspondent aux sucres totaux (sucre naturellement présents dans l’aliment, additionnés des sucres ajoutés lors de la fabrication).", 
			"Il est possible d’être en surpoids et carencé en nutriments essentiels (vitamines et sels minéraux).\n\nL’obésité n’est que le signal visible d’une mauvaise alimentation. Ce n’est pas parce qu’une personne n’a pas de problème de poids que sa santé n’est pas en danger.", 
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
