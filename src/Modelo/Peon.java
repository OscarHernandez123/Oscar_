package Modelo;

// Clase que representa a la pieza "Peon" en un juego de ajedrez
public class Peon extends Pieza {

    // Constructor de la clase Peon
    // Recibe el color de la pieza, su fila y columna en el tablero
    public Peon(String color, int fila, int columna) {
        super(color, fila, columna); // Llama al constructor de la clase base Pieza
    }

    // Método que verifica si el movimiento del Peon es válido
    @Override
    public boolean esMovimientoValido(int nuevaFila, int nuevaColumna, Pieza[][] tablero) {
        // Determina la dirección del movimiento del peón dependiendo de su color
        // Si el peón es blanco, la dirección es hacia abajo (1), si es negro, es hacia arriba (-1)
        int direccion = obtenerColor().equals("blanco") ? 1 : -1;
        // Determina la fila inicial del peón según su color
        int filaInicial = obtenerColor().equals("blanco") ? 1 : 6;

        // Movimiento de una casilla hacia adelante
        if (nuevaColumna == obtenerColumna() && nuevaFila == obtenerFila() + direccion) {
            // Verificar si la casilla de destino está vacía
            if (tablero[nuevaFila][nuevaColumna] == null) {
                return true; // El movimiento es válido si la casilla está vacía
            }
        }

        // Movimiento de dos casillas hacia adelante desde la fila inicial
        if (nuevaColumna == obtenerColumna() && nuevaFila == obtenerFila() + (2 * direccion) && obtenerFila() == filaInicial) {
            // Verificar si ambas casillas están vacías (la casilla inicial y la de destino)
            if (tablero[obtenerFila() + direccion][nuevaColumna] == null && tablero[nuevaFila][nuevaColumna] == null) {
                return true; // El movimiento es válido si ambas casillas están vacías
            }
        }

        // Movimiento de captura diagonal
        if (Math.abs(nuevaColumna - obtenerColumna()) == 1 && nuevaFila == obtenerFila() + direccion) {
            // Verificar si la casilla de destino tiene una pieza del color contrario
            if (tablero[nuevaFila][nuevaColumna] != null && !tablero[nuevaFila][nuevaColumna].obtenerColor().equals(obtenerColor())) {
                return true; // El movimiento es válido si la casilla está ocupada por una pieza contraria
            }
        }

        return false; // Si no es ninguno de los movimientos válidos, el movimiento no es válido
    }
}




