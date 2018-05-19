package practica10;

import hilos.HiloDisparoAmigo;
import hilos.HiloDisparoEnemigo;
import hilos.HiloPajaroEnemigo;
import hilos.HiloPajaroMio;
import hilos.HiloPiedras;
import objetos.DisparoAmigo;
import objetos.PajaroMio;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Teclado implements KeyListener {
	
	private MiPanel miPanel;

	public Teclado(MiPanel miPanel){
		this.miPanel = miPanel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==32) {
			//Indicaremos al usuario un mensaje de que debe pulsar la barra espaciadora para comenzar a jugar.
			if (miPanel.getPantalla()==0||miPanel.getPantalla()==2) { //si viene de pantallas Inicio o Game Over
				miPanel.setCoordXFondo(0);
				miPanel.setPuntuacion(0);
				miPanel.setVidas(3);
				miPanel.setNuevaPuntuacionGuardada(false);
				miPanel.sethPajaroMio(new HiloPajaroMio(miPanel));
				miPanel.gethPajaroMio().start();
				miPanel.sethPiedras(new HiloPiedras(miPanel)); // Las piedras se crean en hPiedra
				miPanel.gethPiedras().start();
				//Nos irán saliendo pájaros enemigos
				miPanel.sethPajaroEnemigo(new HiloPajaroEnemigo(miPanel));
				miPanel.gethPajaroEnemigo().start();
				miPanel.sethDisparoAmigo(new HiloDisparoAmigo(miPanel)); // disparosAmigo se crean en hDisparoAmigo
				miPanel.sethDisparoEnemigo(new HiloDisparoEnemigo(miPanel)); // disparosEnemigo se crean en hDisparoEnemigo
				miPanel.setPantalla(1); //pantalla Juego
			} else {
				//Y para disparar utilizaremos la barra espaciadora.
				if (miPanel.getPantalla()==1) { //si viene de pantalla Juego
					PajaroMio pj = miPanel.getPajaroMio();
					ArrayList<Image> auxImgsDisparoAmigo;
					int tipoDisparo;
					//Cuando llego a un total de 500 puntos, a partir de ese momento, tendré el disparo de tipo "disparoamigo2" 
					if (miPanel.getPuntuacion()<500) {
						tipoDisparo = 1;
						auxImgsDisparoAmigo = miPanel.getAuxImgsDisparoAmigo1();
					} else {
						tipoDisparo = 2;
						auxImgsDisparoAmigo = miPanel.getAuxImgsDisparoAmigo2();
					}
					DisparoAmigo nuevoDisparo = new DisparoAmigo(pj.getCoordX()+pj.getImgs().get(0).getWidth(null),
							pj.getCoordY()+pj.getImgs().get(0).getHeight(null)/2, tipoDisparo,
							auxImgsDisparoAmigo);
					// se ha elegido disparar desde coordY+height/2 para poder disparar a cualquier altura, incluidos los bordes de pantalla
					// crear disparo nuevo:
					miPanel.gethDisparoAmigo().getDisparosAmigo().add(nuevoDisparo);
					if (miPanel.gethDisparoAmigo().isAlive()==false) { // Si no se ha iniciado hDisparoAmigo
						miPanel.gethDisparoAmigo().start();
					}
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
