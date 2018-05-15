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
	Image pajaro;

	public MiPanel() {
		Toolkit t = Toolkit.getDefaultToolkit();
		pantalla = 0;

		// Cargar imágenes
		fondo = t.getImage(getClass().getResource("fondo.jpg"));
		pajaro = t.getImage(getClass().getResource("Mi pajaro/pajaromio01.png"));
		//Las piedras irán girando. Para ello utilizaremos las imágenes: piedra01.png hasta piedra08.png
		for (int i = 1; i <= 8;i++){
			Image image = t.getImage(getClass().getResource("piedra/piedra0"+i+".png"));
			imgsPiedra.add(image);
		}

		HiloFondo hFondo = new HiloFondo(this, fondo);
		hFondo.start();
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
					tirarPiedras(g);
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
		//TODO: confirmar que el parpadeo es por la tarjeta gráfica
		Image resizedPajaro = pajaro.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		g.drawImage(resizedPajaro, 100, 100, this);
		g.drawImage(resizedPajaro, 200, 100, this);
		g.drawImage(resizedPajaro, 300, 100, this);

		//TODO: Nivel de vida
		//En el centro de la barra superior vamos a colocar una franja de color para ir indicando el estado de la vida que tenemos actualmente.
		//colocar una franja roja de 100% de ancho y luego encima una franja azul. La franja roja siempre será de 100%
		//y la azul es la que iremos poniendo más pequeña
	}
	
	public void tirarPiedras(Graphics g){
		//Estas piedras aparecerán de forma aleatoria por la parte derecha de la pantalla
	}
}