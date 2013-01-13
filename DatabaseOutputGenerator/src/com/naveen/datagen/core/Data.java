package com.naveen.datagen.core;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "suppliers_list", "parts_list" })
public class Data {

	private ArrayList<Supplier> suppliers_list = new ArrayList<Supplier>();
	private ArrayList<Parts> parts_list = new ArrayList<Parts>();

	@XmlElementWrapper(name = "Parts")
	@XmlElement(name = "Part")
	public void setParts_list(ArrayList<Parts> parts_list) {
		this.parts_list = parts_list;
	}

	@XmlElementWrapper(name = "Suppliers")
	@XmlElement(name = "Supplier")
	public void setSuppliers_list(ArrayList<Supplier> sup_list) {
		this.suppliers_list = sup_list;
	}

	public ArrayList<Supplier> getSuppliers_list() {
		return suppliers_list;
	}

	public ArrayList<Parts> getParts_list() {
		return parts_list;
	}

}
