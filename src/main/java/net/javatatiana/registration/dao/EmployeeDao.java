package net.javatatiana.registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.javatatiana.registration.model.Employee;

public class EmployeeDao {

	public int registerEmployee(Employee employee) throws ClassNotFoundException {
		String INSERT_USERS_SQL = "INSERT INTO employee"
				+ " (id, first_name, last_name, username, password, address, contact) VALUES "
				+ " (?, ?, ?, ?, ?,?,?);";
		int result = 0;
		Class.forName("com.mysql.jdbc.Driver");//carga la clase del driver  de la conexion
// step1  conectarse
		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/employees?useSSL=false", "root", "");
// Step 2:Create a statement using connection object
		    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setInt(1, employee.getId());
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setString(4, employee.getUsername());
			preparedStatement.setString(5, employee.getPassword());
			preparedStatement.setString(6, employee.getAddress());
			preparedStatement.setString(7, employee.getContact());
			System.out.println(preparedStatement);
		
// Step 3: Execute the query or update query
			result = preparedStatement.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
// process sql exception
			e.printStackTrace();
		}
		return result;
	}
}
