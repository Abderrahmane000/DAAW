package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDAO {

	public UserDAO() {
		
	}

	// connection to database
	public Connection connectDB() throws InstantiationException, IllegalAccessException {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/abdou", "root", "");
			System.out.println("Connection avec succes a la base de donnees !");
		} catch (ClassNotFoundException cnf) {
			System.out.print("Driver non chargé!");
		} catch (SQLException sqlex) {
			System.out.println("Incapable de connecter a la base de donnees...");
		}
		return connection;

	}


	public void addUser(User user) throws InstantiationException, IllegalAccessException, SQLException{

		Connection connection = null;
		String requete;
		PreparedStatement stmt;

		connection = connectDB();
		requete = "INSERT INTO users(firstName,lastName,email) VALUES(?,?,?)";
		stmt = connection.prepareStatement(requete);

		stmt.setString(1, user.getFirstname());
		stmt.setString(2, user.getLastname());
		stmt.setString(3, user.getEmail());

		stmt.executeUpdate();
		stmt.close();

		System.out.println("Inserted !");

	}
	public void updateUser(String fname,String lname, String mail,int id) throws InstantiationException, IllegalAccessException, SQLException {
		Connection connection=null;
		String requete;
		PreparedStatement stmt;

		connection = connectDB();
		requete = "UPDATE users SET firstName=?, lastName=?, email=? WHERE ID=?";
		stmt = connection.prepareStatement(requete);

		stmt.setString(1, fname);
		stmt.setString(2, lname);
		stmt.setString(3, mail);
		stmt.setInt(4, id);

		stmt.executeUpdate();
		stmt.close();

		System.out.println("Updated Succesfully!");
	}
    
	public void deleteUser(int id) throws InstantiationException, IllegalAccessException, SQLException {
		Connection connection=null;
		String requete;
		PreparedStatement stmt;

		connection = connectDB();
		requete = "DELETE FROM users WHERE ID = ?";
		stmt = connection.prepareStatement(requete);

		stmt.setInt(1,id);

		stmt.executeUpdate();
		stmt.close();

		System.out.println("Deleted Succesfully!");
	}
	public void getAllUsers() throws InstantiationException, IllegalAccessException, SQLException {
		Connection connection=null;
		String requete;
		connection = connectDB();
		Statement statement = connection.createStatement();
		requete = "SELECT * FROM users";
		ResultSet resultSet = statement.executeQuery(requete);

		while(resultSet.next()) {
            int userId = resultSet.getInt("ID");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");

            System.out.println("User ID: " + userId);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Email: " + email);
            System.out.println();
		}
	
	}
	public void getUsersByFirstName(String wantedName) throws ReflectiveOperationException, IllegalAccessException, SQLException {
		Connection connection=null;
		String requete;
		connection = connectDB();
        String query = "SELECT * FROM users WHERE firstName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, wantedName);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int userId = resultSet.getInt("ID");
            String userFirstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");

            System.out.println("User ID: " + userId);
            System.out.println("First Name: " + userFirstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Email: " + email);
            System.out.println();
        }
	}
	public List<User> getUserById() throws InstantiationException, IllegalAccessException, SQLException{

		Connection connection=null;
		String requete;
		PreparedStatement stmt;
		User myUser = null;

		connection = connectDB();
		requete = "SELECT * FROM User ";
		stmt = connection.prepareStatement(requete);
		

		ResultSet resultSet = stmt.executeQuery();

		if(resultSet.next()){
			myUser = new User();
			myUser.setId(resultSet.getInt("id"));
			myUser.setFirstname(resultSet.getString("fname"));
			myUser.setLastname(resultSet.getString("lname"));
			myUser.setEmail(resultSet.getString("email"));
			
		}

		return (List<User>) myUser;

	}
	
}
