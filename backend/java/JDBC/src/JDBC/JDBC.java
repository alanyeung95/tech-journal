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
            
            String sqlQuery = "SELECT * FROM EngineeringStudents";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
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
                System.out.print("GPA: " + gpa);
                System.out.println(); // New line for each student
            }
            
            resultSet.close();
            preparedStatement.close();
            connection.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
	}
}
