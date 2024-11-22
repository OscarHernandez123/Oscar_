import Controlador.ControladorAjedrez;

import Modelo.Tablero;
import Vista.VistaAjedrez;

public class Main {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        VistaAjedrez vista = new VistaAjedrez();
        new ControladorAjedrez(tablero, vista);
    }
}

