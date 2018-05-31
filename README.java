package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import dto.Task;

public class TaskDAO {
	
	Connection connection;
	
	public TaskDAO () {
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/taskManager", "root", "root");
		} catch(SQLException e) {
			System.err.println("Error al conectar a la base de datos: "+e.getMessage());
		}
	}
	
	public List<Task> showTasks(int user_id) {
		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from tasks where user_id="+user_id);
			List<Task> tasks = null;
			while(result.next()) {
				int id = result.getInt(0);
				String title = result.getString(1);
				String description = result.getString(2);
				LocalDate max_date = result.getDate(3).toLocalDate();
				Task task = new Task(id, title, description, max_date);
				tasks.add(task);
			}
			return tasks;
		} catch (SQLException e) {
			System.err.println("Error SQL: "+e.getMessage());
			return null;
		}
		
	}

}
