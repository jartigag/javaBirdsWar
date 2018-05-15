package practica10;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MiFrame mf = new MiFrame();
		mf.setVisible(true);
		
		Puntos pts = new Puntos();
		pts.conectarDB();
		//TODO: Al arrancar el programa mostrará un listado (cargado de la base de datos) con las 10 mejores puntuaciones
	}
}
