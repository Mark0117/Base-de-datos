package aplicacion;

import modelo.ModeloAplicacion;
import controlador.ControladorAplicacion;
import vista.VistaAplicacion;

public class Aplicacion {

	public static void main(String[] args) {
		VistaAplicacion vista = new VistaAplicacion();
		ModeloAplicacion modelo = new ModeloAplicacion();
		ControladorAplicacion control = new ControladorAplicacion(vista, modelo);
		vista.setVisible(true);
	}
}
