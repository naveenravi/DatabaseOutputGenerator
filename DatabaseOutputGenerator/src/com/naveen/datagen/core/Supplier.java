package com.naveen.datagen.core;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "name", "ID", "parts_supplied" })
public class Supplier {

	private int ID;
	private String name;
	private String code;
	private String telephone;
	private String email;
	private ArrayList<Parts> parts_supplied;

	@XmlElement(name = "Part_Supplied")
	public void setParts_supplied(ArrayList<Parts> parts_supplied) {
		this.parts_supplied = parts_supplied;
	}

	@XmlElement(name = "Id")
	public void setID(int iD) {
		ID = iD;
	}

	@XmlElement(name = "Name")
	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return this.ID;
	}

	public String getName() {
		return this.name;
	}

	public String getCode() {
		return this.code;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public String getEmail() {
		return this.email;
	}

	public ArrayList<Parts> getParts_supplied() {
		if (parts_supplied == null)
			return parts_supplied = new ArrayList<Parts>();
		return this.parts_supplied;
	}

	public Supplier() {
	}

	public Supplier(int id, String Name, String Code, String tele, String Email) {
		this.setID(id);
		this.setName(Name);
		this.code = Code;
		this.telephone = tele;
		this.email = Email;
	}

	public Supplier(int id, String name, Parts part) {
		this.setID(id);
		this.setName(name);
		getParts_supplied().add(part);
	}

	@Override
	public String toString() {

		return String.format(
				"Name : %s , ID : %d, Code: %s, Telephone : %s, Email : %s",
				getName(), getID(), getTelephone(), getEmail());
	}
}
