package com.ceg.ext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ceg.ext.DatabaseInfo;

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
		String query = "DELETE * FROM Person";
		Connection conn = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
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
		String query = "DELETE * FROM Customer";
		Connection conn = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
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
		String query = "DELETE * FROM Product";
		Connection conn = DatabaseInfo.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
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
		String query = "DELETE * FROM Invoice";
		Connection conn = DatabaseInfo.getConnection();
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

}
