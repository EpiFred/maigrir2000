package com.maigrir2000v03.slidingmenu.model;

import java.io.Serializable;

public class ContactContainer implements Serializable {
	
	private static final long serialVersionUID = 69200;

	private String name;
	private String surname;
	private String address;
	private String city;
	private String zipcode;
	private String country;
	private String number1;
	private String number2;
	private String mail;
	private String image;

	public ContactContainer(){

	}

	public ContactContainer(String name, String surname, String address, String city, String zipcode,
			String country, String number1, String number2, String mail, String image){

		this.name = name;
		this.surname = surname;
		this.address = address;
		this.city = city;
		this.zipcode = zipcode;
		this.country = country;
		this.number1 = number1;
		this.number2 = number2;
		this.mail = mail;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getNumber1() {
		return number1;
	}

	public void setNumber1(String number1) {
		this.number1 = number1;
	}

	public String getNumber2() {
		return number2;
	}

	public void setNumber2(String number2) {
		this.number2 = number2;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
