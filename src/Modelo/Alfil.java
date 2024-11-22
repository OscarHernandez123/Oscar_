package Modelo;

// Clase que representa a la pieza "Alfil" en un juego de ajedrez
public class Alfil extends Pieza {

    // Constructor de la clase Alfil
    // Recibe el color de la pieza, su fila y columna en el tablero
    public Alfil(String color, int fila, int columna) {
        super(color, fila, columna); // Llama al constructor de la clase base Pieza
    }

    // Método que verifica si un movimiento del Alfil es válido
    @Override
    public boolean esMovimientoValido(int nuevaFila, int nuevaColumna, Pieza[][] tablero) {
        // Calcula la diferencia entre la fila y la columna del movimiento
        int deltaFila = Math.abs(nuevaFila - obtenerFila());
        int deltaColumna = Math.abs(nuevaColumna - obtenerColumna());

        // Verifica si el movimiento es diagonal (el Alfil se mueve diagonalmente)
        if (deltaFila == deltaColumna) {
            // Calcula los pasos a seguir en las filas y columnas dependiendo de la dirección del movimiento
            int pasoFila = (nuevaFila > obtenerFila()) ? 1 : -1; // Si el movimiento es hacia abajo, se suma 1, si es hacia arriba, se resta 1
            int pasoColumna = (nuevaColumna > obtenerColumna()) ? 1 : -1; // Si el movimiento es hacia la derecha, se suma 1, si es hacia la izquierda, se resta 1
            int filaTemp = obtenerFila() + pasoFila; // Inicializa la fila temporal con el primer paso
            int columnaTemp = obtenerColumna() + pasoColumna; // Inicializa la columna temporal con el primer paso

            // Recorre las casillas intermedias en el camino del Alfil hasta llegar a la casilla destino
            while (filaTemp != nuevaFila || columnaTemp != nuevaColumna) {
                // Si hay alguna pieza en el camino, el movimiento no es válido
                if (tablero[filaTemp][columnaTemp] != null) {
                    return false; // Hay una pieza en el camino
                }
                filaTemp += pasoFila; // Avanza una casilla en la dirección de las filas
                columnaTemp += pasoColumna; // Avanza una casilla en la dirección de las columnas
            }

            // Verifica si la casilla de destino está ocupada por una pieza del mismo color
            if (tablero[nuevaFila][nuevaColumna] != null && tablero[nuevaFila][nuevaColumna].obtenerColor().equals(obtenerColor())) {
                return false; // No se puede mover a una casilla ocupada por una pieza del mismo color
            }

            return true; // El movimiento es válido si no hay piezas en el camino y la casilla destino es válida
        }

        return false; // Si el movimiento no es diagonal, no es válido para un Alfil
    }
}



