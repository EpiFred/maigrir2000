package com.maigrir2000v03.slidingmenu.model;

import java.io.Serializable;

public class AgendaContainer implements Serializable {

	private static final long serialVersionUID = 69200;

	private String title;
	private String nutritionniste;
	private String date;
	private String desc1;
	private String desc2;

	public AgendaContainer(){

	}

	public AgendaContainer(String title, String nutritionniste, String date, String desc1, String desc2){

		this.title = title;
		this.nutritionniste = nutritionniste;
		this.date = date;
		this.desc1 = desc1;
		this.desc2 = desc2;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNutritionniste() {
		return nutritionniste;
	}

	public void setNutritionniste(String nutritionniste) {
		this.nutritionniste = nutritionniste;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getDesc2() {
		return desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}



}
