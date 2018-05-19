package practica10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Puntos {
	Connection connect;
	
	public ArrayList<String> tablaPuntos() {
		ArrayList<String> tabla = new ArrayList<String>();
		try {
			Statement stmt = connect.createStatement();
			connect.setAutoCommit(false);
			ResultSet rs = stmt.executeQuery("SELECT * FROM puntuaciones order by puntos desc limit 10;");
			int i = 0;
			 while (rs.next()) {
				i++;
				int puntos = rs.getInt("puntos");
				tabla.add(i+"º: "+puntos);
			}
			rs.close();
			stmt.close();
			connect.commit();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return tabla;
	}

	public void conectarDB() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

		String url = "puntos.db";

		try {
			connect = DriverManager.getConnection("jdbc:sqlite:" + url);
			connect.setAutoCommit(false);
			Statement stmt = connect.createStatement();
			String query = "CREATE TABLE IF NOT EXISTS `puntuaciones` (`id`	INTEGER NOT NULL,`puntos` INTEGER NOT NULL,	PRIMARY KEY(`id`));";
			stmt.executeUpdate(query);
			stmt.close();
			connect.commit();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cerrarDB() {
		try {
			connect.close();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void insertInto(int puntuacion) {
		try {
			connect.setAutoCommit(false);
			Statement stmt = connect.createStatement();
			String query = "INSERT INTO puntuaciones (puntos) VALUES ("+puntuacion+");";
			stmt.executeUpdate(query);
			stmt.close();
			connect.commit();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
}
