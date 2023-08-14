package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC {

	public static void main(String[] args) throws SQLException {
		String jdbcUrl = "jdbc:mysql://localhost:3306/university";
		String username = "root";
		String password = "my-secret-pw";

		try {
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			addStudentRecord(connection, 4, "Emily", "Davis", "Female", "2001-03-20", "Chemical Engineering", 1, 3.5);
			System.out.println("Added a new student record.");
			System.out.println();
			
			displayAllRecords(connection);
			System.out.println();

			deleteStudentRecord(connection, 4);
			System.out.println("Deleted the new student record.");
			System.out.println();

			displayAllRecords(connection);

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void addStudentRecord(Connection connection, int studentId, String firstName, String lastName,
			String gender, String dateOfBirth, String major, int yearOfStudy, double gpa) throws SQLException {
		String insertSql = "INSERT INTO EngineeringStudents (student_id, first_name, last_name, gender, date_of_birth, major, year_of_study, gpa) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
			insertStatement.setInt(1, studentId);
			insertStatement.setString(2, firstName);
			insertStatement.setString(3, lastName);
			insertStatement.setString(4, gender);
			insertStatement.setString(5, dateOfBirth);
			insertStatement.setString(6, major);
			insertStatement.setInt(7, yearOfStudy);
			insertStatement.setDouble(8, gpa);
			insertStatement.executeUpdate();
		}
	}

	private static void displayAllRecords(Connection connection) throws SQLException {
		String retrieveSql = "SELECT * FROM EngineeringStudents";
		try (PreparedStatement retrieveStatement = connection.prepareStatement(retrieveSql);
				ResultSet resultSet = retrieveStatement.executeQuery()) {

			System.out.println("All Student Records:");
			while (resultSet.next()) {
				int studentId = resultSet.getInt("student_id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String gender = resultSet.getString("gender");
				String dateOfBirth = resultSet.getString("date_of_birth");
				String major = resultSet.getString("major");
				int yearOfStudy = resultSet.getInt("year_of_study");
				double gpa = resultSet.getDouble("gpa");

				System.out.print("Student ID: " + studentId + " ");
				System.out.print("Name: " + firstName + " " + lastName + " ");
				System.out.print("Gender: " + gender + " ");
				System.out.print("Date of Birth: " + dateOfBirth + " ");
				System.out.print("Major: " + major + " ");
				System.out.print("Year of Study: " + yearOfStudy + " ");
				System.out.print("GPA: " + gpa + " ");
				System.out.println();
			}
		}
	}

	private static void deleteStudentRecord(Connection connection, int studentId) throws SQLException {
		String deleteSql = "DELETE FROM EngineeringStudents WHERE student_id = ?";
		try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
			deleteStatement.setInt(1, studentId);
			deleteStatement.executeUpdate();
		}
	}
}
