package com.naveen.datagen;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = { "name", "id", "cost" })
public class Parts {

	@XmlElement
	private String name;
	@XmlElement
	private String id;
	@XmlElement
	private int cost;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Parts() {

	}

	public Parts(String name, String code) {
		this.setName(name);
		this.setId(code);
	}

	public Parts(String Part_name, String code, int cost_in_cents) {
		this.setName(Part_name);
		this.setId(code);
		this.setCost(cost_in_cents);
	}

	public void setCost(int cost_in_cents) {
		this.cost = cost_in_cents;
	}

	public int getCost() {
		return cost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * finding the part from the total parts list
	 * 
	 * @param part_supplied
	 * @param dat
	 * @return part
	 */
	private static Parts findPart(String part_id, Data dat) {
		for (Parts part : dat.getParts_list()) {
			if (part.getId().equalsIgnoreCase(part_id)) {
				return part;
			}
		}
		return null;
	}

}
