package Modelo;

// Clase que representa a la pieza "Caballo" en un juego de ajedrez
public class Caballo extends Pieza {

    // Constructor de la clase Caballo
    // Recibe el color de la pieza, su fila y columna en el tablero
    public Caballo(String color, int fila, int columna) {
        super(color, fila, columna); // Llama al constructor de la clase base Pieza
    }

    // Método que verifica si un movimiento del Caballo es válido
    @Override
    public boolean esMovimientoValido(int nuevaFila, int nuevaColumna, Pieza[][] tablero) {
        // Calcula la diferencia entre la fila y la columna del movimiento
        int deltaFila = Math.abs(nuevaFila - obtenerFila());
        int deltaColumna = Math.abs(nuevaColumna - obtenerColumna());

        // Verifica si el movimiento sigue la forma de "L" del Caballo (2 casillas en una dirección y 1 en la otra)
        if ((deltaFila == 2 && deltaColumna == 1) || (deltaFila == 1 && deltaColumna == 2)) {
            // Verifica si la casilla de destino está ocupada por una pieza del mismo color
            if (tablero[nuevaFila][nuevaColumna] != null && tablero[nuevaFila][nuevaColumna].obtenerColor().equals(obtenerColor())) {
                return false; // No se puede mover a una casilla ocupada por una pieza del mismo color
            }
            return true; // El movimiento es válido si sigue la forma de "L" y no hay piezas del mismo color
        }

        return false; // Si el movimiento no sigue la forma de "L", no es válido para el Caballo
    }
}



