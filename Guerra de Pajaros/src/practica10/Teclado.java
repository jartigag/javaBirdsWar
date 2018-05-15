package practica10;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {
	
	private MiPanel miPanel;

	public Teclado(MiPanel miPanel){
		this.miPanel = miPanel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==32) {
			miPanel.pantalla = 1;
			miPanel.repaint();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
