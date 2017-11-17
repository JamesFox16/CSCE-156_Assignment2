package com.ceg.ext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ceg.ext.DatabaseInfo;

import cinac.Address;
import cinac.Customer;
import cinac.Invoice;
import cinac.Person;
import cinac.Product;

/*
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 15 methods in total, add more if required.
 * Do not change any method signatures or the package name.
 * 
 */

public class InvoiceData {

	/**
	 * 1. Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		String query = "SET FOREIGN_KEY_CHECKS=0";
		Connection conn = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate(query);
			ps.close();
			
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		query = "TRUNCATE TABLE Person";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		query =  "SET FOREIGN_KEY_CHECKS=1";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 2. Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 * */
	
	
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country) {
		
		String query = "select AddressID from Address WHERE Street = ? AND City = ? AND State = ?";
		int addressID=0;
		boolean addressExists=false;
		Connection conn = DatabaseInfo.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				if(!rs.wasNull()) {
					addressExists = true;
					addressID = rs.getInt("AddressID");
				}else {
					addressExists = false;
				}
			}else {
				addressExists = false;
			}
			conn.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		if(addressExists) {// Only goes through this if when the address is already in the database
			query = "INSERT INTO Person (PersonID, FirstName,LastName,AddressID) VALUES (?,?,?,?)";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, personCode);
				ps.setString(2, firstName);
				ps.setString(3, lastName);
				ps.setInt(4, addressID);
				
				ps.executeUpdate();
				
				ps.close();
				conn.close();
			}catch(SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		
		}else {// Else is where the address is not in the database.
			// Adding the address to the database
			addAddress(street, city, state, zip, country);
			
			// Getting the newly added address' ID
			query = "SELECT AddressID from Address WHERE Street = ? AND City = ? AND State = ?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, street);
				ps.setString(2, city);
				ps.setString(3, state);
				ResultSet rs = ps.executeQuery();
				addressID = rs.getInt("AddressID");
				//ps.close();
				conn.close();
				
			}catch(SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			// Adding Person now that we have addressID
			query = "INSERT INTO Person (PersonID, FirstName,LastName,AddressID) VALUES (?,?,?,?)";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, personCode);
				ps.setString(2, firstName);
				ps.setString(3, lastName);
				ps.setInt(4, addressID);
				
				ps.executeUpdate();
				
				ps.close();
				conn.close();
			}catch(SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		String query = "INSERT INTO Email (EmailAddress, PersonID) VALUES (?,?)";
		Connection conn = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, personCode);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}catch(SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 4. Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {
		Connection conn = DatabaseInfo.getConnection();
		String query = "SET FOREIGN_KEY_CHECKS=0";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate(query);
			ps.close();
			
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		query = "TRUNCATE TABLE Customer";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
		query = "SET FOREIGN_KEY_CHECKS=1";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate(query);
			ps.close();
			conn.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode,String name, String street, String city, String state, String zip, String country) {
		
		String query = "select AddressID from Address WHERE Street = ? AND City = ? AND State = ?";
		int addressID=0;
		boolean addressExists=false;
		boolean personExists=false;
		Connection conn = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				if(!rs.wasNull()) {
					addressExists = true;
					addressID = rs.getInt("AddressID");
				}else {
					addressExists = false;
				}
			}else {
				addressExists = false;
			}
			conn.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		query = "SELECT PersonID FROM Person WHERE PersonID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, primaryContactPersonCode);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				if(!rs.wasNull()) {
					personExists = true;
				}else {
					personExists = false;
				}
			}else {
				personExists = false;
			}
			conn.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		if(personExists && addressExists) {
			query = "INSERT INTO Customer (CustomerID, CustomerName, CustomerType, PersonID, AddressID) VALUES (?,?,?,?,?)";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, customerCode);
				ps.setString(2, name);
				ps.setString(3, customerType);
				ps.setString(4, primaryContactPersonCode);
				ps.setInt(5, addressID);
				
				ps.executeUpdate();
				ps.close();
				conn.close();
			}catch (SQLException e){
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		
		}
		
	}
	
	/**
	 * 5. Removes all product records from the database
	 */
	public static void removeAllProducts() {
		Connection conn = DatabaseInfo.getConnection();
		String query = "SET FOREIGN_KEY_CHECKS=0";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate(query);
			ps.close();
			
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		query = "TRUNCATE TABLE Product";
		conn = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		conn = DatabaseInfo.getConnection();
		query = "SET FOREIGN_KEY_CHECKS=1";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate(query);
			ps.close();
			conn.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 6. Adds an movieTicket record to the database with the provided data.
	 */
	public static void addMovieTicket(String productCode, String dateTime, String movieName, String street, String city,String state, String zip, String country, String screenNo, double pricePerUnit) {
		String query = "INSERT INTO Product (ProductID, ProductName, ProductType, Price) VALUES (?,?,?,?)";
		String type = "M";
		Connection conn = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, movieName);
			ps.setString(3, type);
			ps.setDouble(4, pricePerUnit);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 7. Adds a seasonPass record to the database with the provided data.
	 */
	public static void addSeasonPass(String productCode, String name, String seasonStartDate, String seasonEndDate,	double cost) {
		String query = "INSERT INTO Product (ProductID, ProductName, ProductType, Price) VALUES (?,?,?,?)";
		String type = "S";
		Connection conn = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, name);
			ps.setString(3, type);
			ps.setDouble(4, cost);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 8. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingFee) {
		String query = "INSERT INTO Product (ProductID, ProductName, ProductType, Price) VALUES (?,?,?,?)";
		String productName = "Parking Pass";
		String productType = "P";
		Connection conn = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, productName);
			ps.setString(3, productType);
			ps.setDouble(4, parkingFee);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 9. Adds a refreshment record to the database with the provided data.
	 */
	public static void addRefreshment(String productCode, String name, double cost) {
		String query = "INSERT INTO Product (ProductID, ProductName, ProductType, Price) VALUES (?,?,?,?)";
		String type = "R";
		Connection conn = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, name);
			ps.setString(3, type);
			ps.setDouble(4, cost);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 10. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		removeAllProducts();
		removeAllPersons();
		removeAllCustomers();
		Connection conn = DatabaseInfo.getConnection();
		String query = "SET FOREIGN_KEY_CHECKS=0";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate(query);
			ps.close();
			
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
		query = "TRUNCATE TABLE Invoice";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
			
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		query = "SET FOREIGN_KEY_CHECKS=1";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate(query);
			ps.close();
			conn.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 11. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode, String invoiceDate) {
		String query = "SELECT AddressID FROM Person WHERE PersonCode = ?";
		Connection conn = DatabaseInfo.getConnection();
		int addressID=0;
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, salesPersonCode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				addressID = rs.getInt("AddressID");
			}
			rs.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		query = "INSERT INTO Invoice (InvoiceCode, InvoiceDate, AddressID, CustomerID, PersonID)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			ps.setString(2, invoiceDate);
			ps.setInt(3, addressID);
			ps.setString(4, customerCode);
			ps.setString(5, salesPersonCode);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 12. Adds a particular movieticket (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 */

	public static void addMovieTicketToInvoice(String invoiceCode, String productCode, int quantity) {
		String query = "SELECT AdressID, CustomerID, PersonID, InvoiceDate FROM Invoice WHERE InvoiceCode = ?";
		Connection conn = DatabaseInfo.getConnection();
		int addressID=0;
		String customerID="";
		String personID = "";
		String invoiceDate="";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				addressID = rs.getInt("AdressID");
				customerID = rs.getString("CustomerID");
				personID = rs.getString("PersonID");
				invoiceDate = rs.getString("InvoiceDate");
			}
			rs.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		query = "INSERT INTO Invoice (InvoiceCode, InvoiceDate, AddressID, CustomerID, PersonID, ProductID, Quantity) VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			ps.setString(2, invoiceDate);
			ps.setInt(3, addressID);
			ps.setString(4, customerID);
			ps.setString(5, personID);
			ps.setString(6, productCode);
			ps.setInt(7, quantity);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}

	/*
	 * 13. Adds a particular seasonpass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addSeasonPassToInvoice(String invoiceCode, String productCode, int quantity) {
		String query = "SELECT AdressID, CustomerID, PersonID, InvoiceDate FROM Invoice WHERE InvoiceCode = ?";
		Connection conn = DatabaseInfo.getConnection();
		int addressID=0;
		String customerID="";
		String personID = "";
		String invoiceDate="";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				addressID = rs.getInt("AdressID");
				customerID = rs.getString("CustomerID");
				personID = rs.getString("PersonID");
				invoiceDate = rs.getString("InvoiceDate");
			}
			rs.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		query = "INSERT INTO Invoice (InvoiceCode, InvoiceDate, AddressID, CustomerID, PersonID, ProductID, Quantity) VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			ps.setString(2, invoiceDate);
			ps.setInt(3, addressID);
			ps.setString(4, customerID);
			ps.setString(5, personID);
			ps.setString(6, productCode);
			ps.setInt(7, quantity);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

     /**
     * 14. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     * NOTE: ticketCode may be null
     */
    public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String ticketCode) {
    	String query = "SELECT AdressID, CustomerID, PersonID, InvoiceDate FROM Invoice WHERE InvoiceCode = ?";
		Connection conn = DatabaseInfo.getConnection();
		int addressID=0;
		String customerID="";
		String personID = "";
		String invoiceDate="";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				addressID = rs.getInt("AdressID");
				customerID = rs.getString("CustomerID");
				personID = rs.getString("PersonID");
				invoiceDate = rs.getString("InvoiceDate");
			}
			rs.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		query = "INSERT INTO Invoice (InvoiceCode, InvoiceDate, AddressID, CustomerID, PersonID, ProductID, Quantity) VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			ps.setString(2, invoiceDate);
			ps.setInt(3, addressID);
			ps.setString(4, customerID);
			ps.setString(5, personID);
			ps.setString(6, productCode);
			ps.setInt(7, quantity);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
	
    /**
     * 15. Adds a particular refreshment (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity. 
     */
    public static void addRefreshmentToInvoice(String invoiceCode, String productCode, int quantity) {
    	String query = "SELECT AdressID, CustomerID, PersonID, InvoiceDate FROM Invoice WHERE InvoiceCode = ?";
		Connection conn = DatabaseInfo.getConnection();
		int addressID=0;
		String customerID="";
		String personID = "";
		String invoiceDate="";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				addressID = rs.getInt("AdressID");
				customerID = rs.getString("CustomerID");
				personID = rs.getString("PersonID");
				invoiceDate = rs.getString("InvoiceDate");
			}
			rs.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		query = "INSERT INTO Invoice (InvoiceCode, InvoiceDate, AddressID, CustomerID, PersonID, ProductID, Quantity) VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			ps.setString(2, invoiceDate);
			ps.setInt(3, addressID);
			ps.setString(4, customerID);
			ps.setString(5, personID);
			ps.setString(6, productCode);
			ps.setInt(7, quantity);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
    
    public static void addAddress(String street, String city, String state, String zip, String country) {
    		String query = "INSERT INTO Address (Street, City, State, ZIP, Country) VALUES (?,?,?,?,?)";
		Connection conn = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setInt(4, Integer.parseInt(zip));
			ps.setString(5, country);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}catch(SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
    
    public static ArrayList<Invoice> getInvoiceList(){
    		ArrayList<Invoice> inl = new ArrayList<Invoice>();
    		ArrayList<Invoice> finalListToReturn = new ArrayList<Invoice>();
    		String query = "SELECT InvoiceCode, CustomerID, PersonID, InvoiceDate, ProductID, Quantity FROM Invoice GROUP BY InvoiceCode";
    		Connection conn = DatabaseInfo.getConnection(); 
    		String invoiceCode ="";
    		String customerCode = "";
    		String personCode = "";
    		String invoiceDate = "";
    		String productID = "";
    		int quantity = 0;
    		try {
    			PreparedStatement ps = conn.prepareStatement(query);
    			ResultSet rs = ps.executeQuery();
    			while(rs.next()) {
    				invoiceCode = rs.getString("InvoiceCode");
    				customerCode = rs.getString("CustomerID");
    				personCode = rs.getString("PersonID");
    				invoiceDate = rs.getString("InvoiceDate");
    				productID = rs.getString("ProductID");
    				quantity = rs.getInt("Quantity");
    				Invoice in = new Invoice(invoiceCode, customerCode, personCode, invoiceDate, productID, quantity);
//    				System.out.println(invoiceCode);
//    				System.out.println(invoiceDate);
    				inl.add(in);
    			}
    			rs.close();
    			
    		}catch(SQLException e) {
    			System.out.println("SQLException: ");
    			e.printStackTrace();
    			throw new RuntimeException(e);
    		}
    		for(int i=0; i<inl.size(); i++) {
    			ArrayList<Product> prodList = new ArrayList<Product>();
    			ArrayList<String> quantList = new ArrayList<String>();
    			String tempInvoiceCode = inl.get(i).getCode();
    			String tempCust = inl.get(i).getCustomerCode();
    			String tempPerson = inl.get(i).getPersonCode();
    			String tempDate = inl.get(i).getDate();
    			int j = 0;
    			while(tempInvoiceCode.equals(inl.get(j).getCode())) {
    				prodList.add(getProductFromDB(inl.get(j).getCode()));
    				quantList.add(""+inl.get(j).getQuantity());
    				j++;
    			}
    			//i=j;
    			finalListToReturn.add(new Invoice(tempInvoiceCode, getCustomerFromDB(tempCust),
    					getPersonFromDB(tempPerson), tempDate, prodList, quantList));
    			
    		}
    		
    		return finalListToReturn;
    }
    
    public static Customer getCustomerFromDB(String customerCode) {
    		String query = "SELECT CustomerType, CustomerName, PersonID, AddressID FROM Customer WHERE CustomerCode = ?";
    		Connection conn = DatabaseInfo.getConnection();
    		Customer newCustomer;
    		String type = "";
    		String name = "";
    		String person = "";
    		int address = 0;
    		try {
    			PreparedStatement ps = conn.prepareStatement(query);
    			ps.setString(1, customerCode);
    			ResultSet rs = ps.executeQuery();
    			while (rs.next()) {
    				type = rs.getString("CustomerType");
    				name = rs.getString("CustomerName");
    				person = rs.getString("PersonID");
    				address = rs.getInt("AddressID");	
    			}
    			rs.close();
    			newCustomer = new Customer(customerCode, type, getPersonFromDB(person), name, getAddressFromDB(address));
    		}catch(SQLException e) {
    			System.out.println("SQLException: ");
    			e.printStackTrace();
    			throw new RuntimeException(e);
    		}
    		
    		return newCustomer;
    }
    
    public static Person getPersonFromDB(String personCode) {
    		String query = "SELECT FirstName, LastName, AddressID FROM Person WHERE PersonCode = ?";
    		Connection conn = DatabaseInfo.getConnection();
    		String firstName="";
    		String lastName="";
    		int addressID=0;
    		Person newPerson;
    		try {
    			PreparedStatement ps = conn.prepareStatement(query);
    			ps.setString(1, personCode);
    			ResultSet rs = ps.executeQuery();
    			while(rs.next()) {
    				firstName = rs.getString("FirstName");
    				lastName = rs.getString("LastName");
    				addressID = rs.getInt("AddressID");
    			}
    			rs.close();
    			newPerson = new Person(personCode, firstName+" "+lastName, getAddressFromDB(addressID));
    		}catch(SQLException e) {
    			System.out.println("SQLException: ");
    			e.printStackTrace();
    			throw new RuntimeException(e);
    		}
    		
    		return newPerson;
    }
    
    public static Address getAddressFromDB(int addressID) {
    		String query = "SELECT Street, City, State, ZIP, Country FROM Address WHERE AddressID = ?";
    		Connection conn = DatabaseInfo.getConnection();
    		String street="";
    		String city = "";
    		String state ="";
    		String zip = "";
    		String country = "";
    		Address newAddress;
    		try {
    			PreparedStatement ps = conn.prepareStatement(query);
    			ps.setInt(1, addressID);
    			ResultSet rs = ps.executeQuery();
    			while(rs.next()) {
    				street = rs.getString("Street");
    				city = rs.getString("City");
    				state = rs.getString("State");
    				zip = ""+rs.getInt("ZIP");
    				country = rs.getString("Country");
    			}
    			rs.close();
    			newAddress = new Address(street, city, state, zip, country);
    		}catch(SQLException e) {
    			System.out.println("SQLException: ");
    			e.printStackTrace();
    			throw new RuntimeException(e);
    		}
    		
    		return newAddress;
    }
    
    
    public static Product getProductFromDB(String productCode) {
    		String query="SELECT ProductName, ProductType, Price FROM Product WHERE ProductCode = ?";
    		Connection conn = DatabaseInfo.getConnection();
    		String type="";
    		String name="";
    		double price=0;
    		Product newProduct;
    		try {
    			PreparedStatement ps = conn.prepareStatement(query);
    			ps.setString(1, productCode);
    			ResultSet rs = ps.executeQuery();
    			while(rs.next()) {
    				type = rs.getString("ProductType");
    				name = rs.getString("ProductName");
    				price = rs.getDouble("Price");
    			}
    			rs.close();
    			newProduct = new Product(productCode, type, price, name);
    		}catch(SQLException e) {
    			System.out.println("SQLException: ");
    			e.printStackTrace();
    			throw new RuntimeException(e);
    		}
    		return newProduct;
    		
    }
    
   

}
