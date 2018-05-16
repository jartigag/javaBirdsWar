package practica10;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Puntos {
	Connection connect;
	
	public Connection getConnect() {
		return connect;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
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

			if (connect != null) {
				System.out.println("Conectado");
			} else {
				System.out.println("Error de conexion");
			}
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

}
