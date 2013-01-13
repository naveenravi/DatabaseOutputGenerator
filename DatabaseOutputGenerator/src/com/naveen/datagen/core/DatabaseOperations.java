package com.naveen.datagen.core;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class performs the role of connecting with the database 
 * and executing the queries and returning the result
 * @author naveen
 *
 */
final class DatabaseOperations {

	private static final String username = "root";
	private static final String password = "omegastar";
	private Data dat = new Data();

	private Connection con;

	public final void connect() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3308/uat_au_nav_130113", username,
				password);

	}

	/**
	 * executes the queries and using the results the Content tree objects are
	 * created using the supplier and parts classes
	 * 
	 * @return
	 * @throws SQLException
	 */
	public final Data execeuteQueries() throws SQLException {

		Statement stat = con.createStatement();
		Statement stat2 = con.createStatement();
		Statement stat3 = con.createStatement();

		// will provide a list of all the parts starting from the one with the
		// highest cost
		ResultSet result1 = stat
				.executeQuery("select sp.part_name,sp.cost_in_cents,sp.part_code from supplier_parts sp "
						+ "ORDER BY cost_in_cents DESC");

		// getting all the suppliers
		ResultSet result2 = stat2.executeQuery("SELECT * from suppliers");

		// will provide the list of suppliers with their supplied parts
		ResultSet result3 = stat3
				.executeQuery("select s.supplier_id,s.name, sp.part_name,sp.part_code"
						+ " from supplier_parts sp "
						+ "INNER JOIN suppliers s ON s.supplier_id = sp.supplier_id");

		// adding all the parts to the total parts list
		while (result1.next()) {
			String part_name = result1.getString("part_name");
			int cost_of_part = result1.getInt("cost_in_cents");
			String part_id = result1.getString("part_code");

			/*
			 * if (checkIfPartExists(dat, part_name)) {
			 * System.out.println("Multiple records found for the same part");
			 * System.out.println("Part:" + part_name +
			 * " already exists!! Skipping this part!!"); } else {
			 * dat.getParts_list().add( new Parts(part_name, part_id,
			 * cost_of_part)); }
			 */

			// assuming that all the parts are distinct
			dat.getParts_list()
					.add(new Parts(part_name, part_id, cost_of_part));
		}

		// adding all the suppliers to the list irrespective of parts supplied;
		while (result2.next()) {
			int id = result2.getInt("supplier_id");
			String name = result2.getString("name");
			String code = result2.getString("code");
			String tele = result2.getString("telephone_number");
			String email = result2.getString("email_address");
			// not checking for multiple entries because of id as the primary
			// key
			dat.getSuppliers_list().add(
					new Supplier(id, name, code, tele, email));
		}

		// adding the parts to their suppliers
		while (result3.next()) {
			String supplier_name = result3.getString("name");
			String part_supplied = result3.getString("part_name");
			int supplier_id = result3.getInt("supplier_id");
			String part_code = result3.getString("part_code");

			Parts part = findPart(part_code, dat);
			if (part == null) {
				part = new Parts(part_supplied, part_code);
			}
			// checking if it is a known supplier; if so part is added to the
			// supplied parts list
			// else new supplier is created with the part supplied
			if (checkIfSupplierExists(supplier_id, part, dat) == false) {
				dat.getSuppliers_list().add(
						new Supplier(supplier_id, supplier_name, part));
			}
		}
		// closing all the connections
		stat.close();
		stat2.close();
		stat3.close();
		con.close();
		return dat;
	}

	/**
	 * checking if supplier is registered
	 * 
	 * @param supplier_id
	 * @param part
	 * @param dat
	 * @return
	 */
	private static boolean checkIfSupplierExists(int supplier_id, Parts part,
			Data dat) {

		for (Supplier sup : dat.getSuppliers_list()) {
			if (sup.getID() == supplier_id) {
				// int i = dat.getSuppliers_list().indexOf(sup);
				sup.getParts_supplied().add(part);
				// dat.getSuppliers_list().get(i).getParts_supplied().add(part);
				return true;
			}
		}
		return false;
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

	/**
	 * checking if the part is in the Total Parts List
	 * 
	 * @param dat
	 * @param part_name
	 * @return
	 */
	/*
	 * private static boolean checkIfPartExists(Data dat, String part_name) {
	 * for (Parts p : dat.getParts_list()) { if
	 * (p.getName().equalsIgnoreCase(part_name)) { return true; } } return
	 * false; }
	 */
}
