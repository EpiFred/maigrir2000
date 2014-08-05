package com.maigrir2000v03;

import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class Regime extends ListActivity {

	private String[] categories = {
			"Le régime hypercalorique", 
			"Le régime hyperprotéiné", 
			"Le régime dissocié", 
			"Les régimes restrictifs", 
			"Méthode Ligne en Ligne", 
			"Méthode Linecoaching", 
			"Anneau gastrique virtuel", 
			"Le régime sans gluten"
			};
	
	private String[] content = {
			"Le régime hypocalorique consiste à réduire arbitrairement et parfois considérablement l’apport énergétique.\nCe type de régime est souvent très rigide : pesée des aliments (régime hypocalorique classique), comptage de points (Weight Watchers), suppression de certaines catégories d’aliments comme les glucides et les lipides (Scardale, MAYO) …\n\nA savoir sur le régime hypocalorique\n\nDifficile à mettre en œuvre, le régime hypocalorique n’est pas naturel et donc souvent suivi d’une reprise de poids.",
			"En supprimant l’apport de glucides à l’organisme, le régime hyperprotéiné l’oblige à aller chercher l'énergie dans les graisses.\nLors du premier régime, la perte de poids est rapide. Cependant, l'organisme s'adapte à la situation en diminuant ses dépenses énergétiques.\nLe retour à une alimentation normale provoque donc une reprise de poids, en général, supérieure au poids de départ. De plus, sur le long terme, ce type de régime entraine des compulsions alimentaires, de la fatigue et des carences.\n\nA savoir sur le régime hyperprotéiné\n\nIl existe différents régimes hyperprotéinés : \n- Régime hyperprotéiné à base d’aliments riches en protéines animales \n- Régime hyperprotéiné à base de substituts de repas \n- Régime Dukan…",
			"Le régime dissocié, monotone, conduit à une réduction des quantités qui induit une perte de poids. A l’arrêt du régime, on assiste a une reprise de poids rapide. \n\nA savoir sur le régime dissocié \n\n- Le régime ATKINS : Tous les glucides (céréales, légumineuses, laitages) sont supprimés. Les aliments riches en lipides ou en protéines (viandes, poissons, œufs, fromages, huiles, beurres, margarines, mayonnaises…) sont consommés à volonté. Ces régimes sont responsables de carence en vitamines, sels minéraux et fibres à cause du manque de fruits et légumes et sont dangereux pour le cœur et les artères à cause de leur excès de graisses. \n\n- Le régime MONTIGNAC \n\n- Le régime ANTOINE : Un seul type d’aliment par jour (poisson ou viande ou fruits ou légumes). \n\n- Le régime SHELTON : La dissociation se fait sur le repas.",
			"Ces régimes restrictifs sont dangereux pour la santé. Ils entrainent une reprise de poids rapide, une perte de masse musculaire, une grande fatigue et des problèmes de santé plus graves s’ils sont conduits sur de longues périodes. \n\nExemples : Régime soupe au chou, régime raisin, régime ananas, régime fruits, régime légumes, cure de jeûne…",
			"En association avec Ligne en ligne, Audrey Terel, diététicienne-nutritionniste a mis en place une nouvelle approche pour maigrir durablement. \nElle n’exclut aucun aliments plaisir, comme le chocolat ou le fromage, mais vous apprend à les gérer durablement. \n\nLes 4 étapes de la méthode chocolat : \n\n- Définir ses aliments tabous \n- Apprendre à les gérer au quotidien en modifiant son comportement alimentaire \n- Dissocier la faim et la prise alimentaire motivée par les émotions \n- Retrouver une alimentation spontanée \n\nLa Méthode Chocolat est une méthode globale pour un mieux-être général. Elle permet de prendre en main son alimentation et également d’élaborer un travail sur son comportement alimentaire grâce à des outils inspirés des thérapies comportementales en psychologie. \n\nCombien de kilos peut-on perdre ?\n- 2 à 3 kilos par mois pour les petites pertes de poids. \n- Jusqu’à 5 kilos par mois pour les plus grandes pertes de poids.",
			"La méthode LineCoaching permet de maigrir grâce à un programme minceur personnalisé et l’encadrement de coachs au quotidien. \n\nCette méthode est mise au point par G. Apfeldorfer, psychothérapeute, et JP Zermati, nutritionniste, spécialistes du comportement alimentaire depuis plus de 20 ans et fondateurs du Groupe de réflexion sur l'obésité et le surpoids.",
			"La méthode Réduction Virtuelle de l’Estomac est une Thérapie de Programmation Positive qui mêle L’Hypnose thérapeutique et la PNL. \n\nElle utilise le pouvoir extraordinaire de l’inconscient pour convaincre le patient qu’il a subit une opération de gastroplastie, ce qui va induire chez lui une diminution de la prise d’aliments à chaque repas. \n\nAssociée à des conseils diététiques et à de la relaxation elle est très efficace.",
			"Un régime sans gluten ne fait pas maigrir, ne booste pas l’énergie ou autre... \n\nIl ne garantit pas non plus une alimentation plus saine, plus équilibrée, moins calorique ou meilleure pour la santé. \n\nDe plus, il peut entraîner des carences, est très contraignant et peut même nuire à un bon équilibre alimentaire lorsque la personne mange par exemple en collectivité."
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
