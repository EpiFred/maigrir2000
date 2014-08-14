package com.maigrir2000v03.slidingmenu.model;

import java.io.Serializable;

public class RecetteContainer implements Serializable {
	
	private static final long serialVersionUID = 98752;
	
	int id;
	String Titre;
	String Categorie;
	String Description;
	String Temps;
	String Cuisson;
	String Personne;
	String Cout;
	String Difficulte;
	String Region;
	String Saison;
	String Ingredient;
	String Preparation;
	String Suggestion;
	String Photo;
	
	
	public RecetteContainer(){}
	
	public RecetteContainer(int id, String titre, String categorie, String description) {
		this.id = id;
		this.Titre = titre;
		this.Categorie = categorie;
		this.Description = description;
	}
	
	public RecetteContainer(int id, String titre, String categorie, String description, String temps,
			String cuisson, String personne, String cout, String difficulte, String region, String saison,
			String ingredient, String preparation, String suggestion, String photo) {
		
		this.id = id;
		this.Titre = titre;
		this.Categorie = categorie;
		this.Description = description;
		this.Temps = temps;
		this.Cuisson = cuisson;
		this.Personne = personne;
		this.Cout = cout;
		this.Difficulte = difficulte;
		this.Region = region;
		this.Saison = saison;
		this.Ingredient = ingredient;
		this.Preparation = preparation;
		this.Suggestion = suggestion;
		this.Photo = photo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return Titre;
	}

	public void setTitre(String titre) {
		Titre = titre;
	}

	public String getCategorie() {
		return Categorie;
	}

	public void setCategorie(String categorie) {
		Categorie = categorie;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getTemps() {
		return Temps;
	}

	public void setTemps(String temps) {
		Temps = temps;
	}

	public String getCuisson() {
		return Cuisson;
	}

	public void setCuisson(String cuisson) {
		Cuisson = cuisson;
	}

	public String getPersonne() {
		return Personne;
	}

	public void setPersonne(String personne) {
		Personne = personne;
	}

	public String getCout() {
		return Cout;
	}

	public void setCout(String cout) {
		Cout = cout;
	}

	public String getDifficulte() {
		return Difficulte;
	}

	public void setDifficulte(String difficulte) {
		Difficulte = difficulte;
	}

	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}

	public String getSaison() {
		return Saison;
	}

	public void setSaison(String saison) {
		Saison = saison;
	}

	public String getIngredient() {
		return Ingredient;
	}

	public void setIngredient(String ingredient) {
		Ingredient = ingredient;
	}

	public String getPreparation() {
		return Preparation;
	}

	public void setPreparation(String preparation) {
		Preparation = preparation;
	}

	public String getSuggestion() {
		return Suggestion;
	}

	public void setSuggestion(String suggestion) {
		Suggestion = suggestion;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

}
