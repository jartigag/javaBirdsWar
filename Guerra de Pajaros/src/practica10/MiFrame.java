package practica10;

import java.awt.Color;
import javax.swing.JFrame;

public class MiFrame extends JFrame {
	
	MiPanel miPanel = new MiPanel();
	Teclado miTeclado = new Teclado(miPanel);
	Mouse miMouse = new Mouse(miPanel);

	public MiFrame(){
		setSize(1000,700);
		setDefaultCloseOperation(3);
		setResizable(false);
		setLocationRelativeTo(null);
		miPanel.addKeyListener(miTeclado);
		miPanel.setFocusable(true);
		add(miPanel);
	}
}

