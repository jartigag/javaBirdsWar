package practica10;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMover implements MouseMotionListener {

	private MiPanel miPanel;

	public MouseMover(MiPanel miPanel){
		this.miPanel = miPanel;
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//Nuestro pájaro lo moveremos por la pantalla utilizando el ratón.
		int anchoPajaroMio = miPanel.getPajaroMio().getImgsPajaroMio().get(0).getWidth(null);
		int altoajaroMio = miPanel.getPajaroMio().getImgsPajaroMio().get(0).getHeight(null);
		miPanel.getPajaroMio().setCoordXPajaroMio(e.getPoint().x-anchoPajaroMio/2);
		miPanel.getPajaroMio().setCoordYPajaroMio(e.getPoint().y-altoajaroMio/2);
	}

}
