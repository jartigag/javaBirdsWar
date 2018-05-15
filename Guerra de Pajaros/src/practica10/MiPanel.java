package practica10;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MiPanel extends JPanel {
	
	int pantalla;
	int coordXFondo = 0;
	int puntuacion = 0;

	ArrayList <Image> imgsPiedra = new ArrayList<Image>();
	Image fondo;

	public MiPanel(){
		pantalla = 0;
		Toolkit t = Toolkit.getDefaultToolkit();
		fondo = t.getImage(getClass().getResource("fondo.jpg"));
		HiloFondo hFondo = new HiloFondo(this, fondo);
		hFondo.start();
		
		//Las piedras irán girando. Para ello utilizaremos las imágenes: piedra01.png hasta piedra08.png

		/*for (int i = 1; i <= 8;i++){
			Image image = t.getImage(getClass().getResource("piedra0"+i+".png"));
			imgsPiedra.add(image);
		}*/

	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);

		g.drawImage(fondo, coordXFondo, 0, this); // Siempre el mismo fondo
		g.drawImage(fondo, coordXFondo-fondo.getWidth(null), 0, this); //TODO: repetir la imagen bien
				
		switch (pantalla) {
			case 0: inicio(g);
					break;
			case 1: barraSuperior(g);
					break;
		}
		
	}
	
	public void inicio(Graphics g) {

		Font fuente = new Font("Arial", 1, 30);
		g.setFont(fuente);
		g.setColor(Color.GRAY);
		g.drawString("Pulsa BARRA ESPACIADORA para empezar a jugar", 100, 100);
	}
	
	public void barraSuperior(Graphics g){
		
		// Marcador de puntuación
		Font fuente = new Font("Arial", 1, 30);
		g.setFont(fuente);
		g.setColor(Color.BLACK);
		g.drawString(String.format("%07d", puntuacion), 820, 30);

		// Vidas
		//TODO: En la parte derecha colocaremos tres dibujos de pájaros que indicarán las vidas que tenemos.

		// Nivel de vida
		//En el centro de la barra superior vamos a colocar una franja de color para ir indicando el estado de la vida que tenemos actualmente.
		//colocar una franja roja de 100% de ancho y luego encima una franja azul. La franja roja siempre será de 100%
		//y la azul es la que iremos poniendo más pequeña
		
	}
}