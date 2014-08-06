package com.maigrir2000v03;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

public class Regime extends ListActivity {

	private String[] categories = {
			"Le r�gime hypercalorique", 
			"Le r�gime hyperprot�in�", 
			"Le r�gime dissoci�", 
			"Les r�gimes restrictifs", 
			"M�thode Ligne en Ligne", 
			"M�thode Linecoaching", 
			"Anneau gastrique virtuel", 
			"Le r�gime sans gluten"
			};
	
	private String[] content = {
			"Le r�gime hypocalorique consiste � r�duire arbitrairement et parfois consid�rablement l�apport �nerg�tique.\nCe type de r�gime est souvent tr�s rigide : pes�e des aliments (r�gime hypocalorique classique), comptage de points (Weight Watchers), suppression de certaines cat�gories d�aliments comme les glucides et les lipides (Scardale, MAYO) �\n\nA savoir sur le r�gime hypocalorique\n\nDifficile � mettre en �uvre, le r�gime hypocalorique n�est pas naturel et donc souvent suivi d�une reprise de poids.",
			"En supprimant l�apport de glucides � l�organisme, le r�gime hyperprot�in� l�oblige � aller chercher l'�nergie dans les graisses.\nLors du premier r�gime, la perte de poids est rapide. Cependant, l'organisme s'adapte � la situation en diminuant ses d�penses �nerg�tiques.\nLe retour � une alimentation normale provoque donc une reprise de poids, en g�n�ral, sup�rieure au poids de d�part. De plus, sur le long terme, ce type de r�gime entraine des compulsions alimentaires, de la fatigue et des carences.\n\nA savoir sur le r�gime hyperprot�in�\n\nIl existe diff�rents r�gimes hyperprot�in�s : \n- R�gime hyperprot�in� � base d�aliments riches en prot�ines animales \n- R�gime hyperprot�in� � base de substituts de repas \n- R�gime Dukan�",
			"Le r�gime dissoci�, monotone, conduit � une r�duction des quantit�s qui induit une perte de poids. A l�arr�t du r�gime, on assiste a une reprise de poids rapide. \n\nA savoir sur le r�gime dissoci� \n\n- Le r�gime ATKINS : Tous les glucides (c�r�ales, l�gumineuses, laitages) sont supprim�s. Les aliments riches en lipides ou en prot�ines (viandes, poissons, �ufs, fromages, huiles, beurres, margarines, mayonnaises�) sont consomm�s � volont�. Ces r�gimes sont responsables de carence en vitamines, sels min�raux et fibres � cause du manque de fruits et l�gumes et sont dangereux pour le c�ur et les art�res � cause de leur exc�s de graisses. \n\n- Le r�gime MONTIGNAC \n\n- Le r�gime ANTOINE : Un seul type d�aliment par jour (poisson ou viande ou fruits ou l�gumes). \n\n- Le r�gime SHELTON : La dissociation se fait sur le repas.",
			"Ces r�gimes restrictifs sont dangereux pour la sant�. Ils entrainent une reprise de poids rapide, une perte de masse musculaire, une grande fatigue et des probl�mes de sant� plus graves s�ils sont conduits sur de longues p�riodes. \n\nExemples : R�gime soupe au chou, r�gime raisin, r�gime ananas, r�gime fruits, r�gime l�gumes, cure de je�ne�",
			"En association avec Ligne en ligne, Audrey Terel, di�t�ticienne-nutritionniste a mis en place une nouvelle approche pour maigrir durablement. \nElle n�exclut aucun aliments plaisir, comme le chocolat ou le fromage, mais vous apprend � les g�rer durablement. \n\nLes 4 �tapes de la m�thode chocolat : \n\n- D�finir ses aliments tabous \n- Apprendre � les g�rer au quotidien en modifiant son comportement alimentaire \n- Dissocier la faim et la prise alimentaire motiv�e par les �motions \n- Retrouver une alimentation spontan�e \n\nLa M�thode Chocolat est une m�thode globale pour un mieux-�tre g�n�ral. Elle permet de prendre en main son alimentation et �galement d��laborer un travail sur son comportement alimentaire gr�ce � des outils inspir�s des th�rapies comportementales en psychologie. \n\nCombien de kilos peut-on perdre ?\n- 2 � 3 kilos par mois pour les petites pertes de poids. \n- Jusqu�� 5 kilos par mois pour les plus grandes pertes de poids.",
			"La m�thode LineCoaching permet de maigrir gr�ce � un programme minceur personnalis� et l�encadrement de coachs au quotidien. \n\nCette m�thode est mise au point par G. Apfeldorfer, psychoth�rapeute, et JP Zermati, nutritionniste, sp�cialistes du comportement alimentaire depuis plus de 20 ans et fondateurs du Groupe de r�flexion sur l'ob�sit� et le surpoids.",
			"La m�thode R�duction Virtuelle de l�Estomac est une Th�rapie de Programmation Positive qui m�le L�Hypnose th�rapeutique et la PNL. \n\nElle utilise le pouvoir extraordinaire de l�inconscient pour convaincre le patient qu�il a subit une op�ration de gastroplastie, ce qui va induire chez lui une diminution de la prise d�aliments � chaque repas. \n\nAssoci�e � des conseils di�t�tiques et � de la relaxation elle est tr�s efficace.",
			"Un r�gime sans gluten ne fait pas maigrir, ne booste pas l��nergie ou autre... \n\nIl ne garantit pas non plus une alimentation plus saine, plus �quilibr�e, moins calorique ou meilleure pour la sant�. \n\nDe plus, il peut entra�ner des carences, est tr�s contraignant et peut m�me nuire � un bon �quilibre alimentaire lorsque la personne mange par exemple en collectivit�."
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regime);
		// Show the Up button in the action bar.
		setupActionBar();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
		ListView list = (ListView) findViewById(android.R.id.list);
		
		list.setAdapter(adapter);
	}
	
	protected void onListItemClick(ListView list, View v, int position, long id){
		Intent intent = new Intent(this, RegimeDescription.class);
		intent.putExtra("categories", categories[position]);
		intent.putExtra("content", content[position]);
		startActivity(intent);
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
		getMenuInflater().inflate(R.menu.regime, menu);
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
