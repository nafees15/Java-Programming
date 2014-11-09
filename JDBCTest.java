package csi508;

import java.sql.*;
import java.lang.*;

/**
 * This program is designed to introduce students into JDBC programming. 
 * Students are required to fill the body of each of the following methods:
 * (1) protected static Connection getConnectionToYourDB() throws SQLException; 
 * (2) protected static ResultSet getResultOfPart3A(Connection connection) throws SQLException
 * (3) protected ResultSet getResultOfPart3B(Connection connection) throws SQLException
 * 
 * Once all of the above methods are implemented correctly, this program will
 * display the results of queries in Part 3 (a) and (c) of Programming Assignment 1.
 * 
 *
 *
 */
public class JDBCTest {
	
	/**
	 * Attempts to establish a connection to your database.
	 * @return a Connection to your database.
	 * @throws SQLException if a database access error occurs.
	 */
	protected static Connection getConnectionToYourDB() throws SQLException {
		// (4 points) complete the following line to establish a connection to your database.
		
		Connection connection; 
		
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "andaji");
	
		return connection;
	}
	
	/**
	 * Executes the query in Part 3 (a).
	 * @param connection a Connection to your database.
	 * @return the ResultSet an object that contains the data produced by the query.
	 * @throws SQLException if a database access error occurs or 
	 *   the given SQL statement produces anything other than a single ResultSet object
	 */
	protected static ResultSet getResultOfPart3A(Connection connection) throws SQLException {
		// (4 points) complete the following line to return a ResultSet obtained by running 
		// the query in Part 3 (a).
		
		Statement statement = null;
		ResultSet rs;
		
		String query;
		
		query = "select customer_name from customer where customer_id in (select distinct customer_id from depositor ,account  where depositor.account_number= account.account_number and account.balance >'6000')";
		
			
		statement = connection.createStatement();
		return rs = statement.executeQuery(query);

	}
	
	/**
	 * Executes the query in Part 3 (b).
	 * @param connection a Connection to your database.
	 * @return the ResultSet an object that contains the data produced by the query.
	 * @throws SQLException if a database access error occurs or 
	 *   the given SQL statement produces anything other than a single ResultSet object
	 */
	protected static ResultSet getResultOfPart3B(Connection connection) throws SQLException {
		// (4 points) complete the following line to return a ResultSet obtained by running 
		// the query in Part 3 (b).
		

		Statement statement = null;
		ResultSet rs;
		
		String query;
		
		query = "select account_number from depositor group by account_number having count (customer_id)>'1'";
		
			
		statement = connection.createStatement();
		return rs = statement.executeQuery(query);

		
	}

	public static void main(String[] argv) {
		try {
			Connection connection = getConnectionToYourDB();

			// Part 3 (a)
			System.out.println("The names of all all depositors who have an account with a balance greater than $6,000:");
			ResultSet rs = getResultOfPart3A(connection);
			// (4 points) complete a loop that prints (using System.out.println()) all the relevant customer names.
			
			while(rs.next())
			{
				System.out.println(rs.getString(1));
			}
			// Part 3 (b)
			System.out.println("The the account numbers of all joint accounts (i.e., accounts owned by at least two customers):");
			rs = getResultOfPart3B(connection);
			// (4 points) complete a loop that prints (using System.out.println()) all the relevant account numbers.
			
			while(rs.next())
			{
				System.out.println(rs.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
