package practica10;

import hilos.HiloDisparoAmigo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Teclado implements KeyListener {
	
	private MiPanel miPanel;

	public Teclado(MiPanel miPanel){
		this.miPanel = miPanel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		HiloDisparoAmigo hD = new HiloDisparoAmigo(miPanel,miPanel.getAuxImgsDisparoAmigo1());
		if (e.getKeyCode()==32) {
			//Indicaremos al usuario un mensaje de que debe pulsar la barra espaciadora para comenzar a jugar.
			if (miPanel.getPantalla()==0) {
				miPanel.setPantalla(1);
			} else {
				//Y para disparar utilizaremos la barra espaciadora.
				if (miPanel.getPantalla()==1) {
					miPanel.sethDisparoAmigo(hD);
					miPanel.gethDisparoAmigo().start();
				}
			}	
			miPanel.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// Auto-generated method stub
		
	}

}
