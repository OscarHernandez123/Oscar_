package Modelo;

// Clase que representa a la pieza Rey en el ajedrez, que hereda de la clase Pieza.
public class Rey extends Pieza {

    // Constructor que recibe el color, fila y columna del Rey
    public Rey(String color, int fila, int columna) {
        super(color, fila, columna);  // Llama al constructor de la clase base (Pieza)
    }

    // Método que determina si el movimiento del Rey es válido según las reglas del ajedrez
    @Override
    public boolean esMovimientoValido(int nuevaFila, int nuevaColumna, Pieza[][] tablero) {
        // Calcula las diferencias absolutas entre las filas y columnas de la pieza actual y la nueva posición
        int deltaFila = Math.abs(nuevaFila - obtenerFila());
        int deltaColumna = Math.abs(nuevaColumna - obtenerColumna());

        // El Rey puede moverse una casilla en cualquier dirección: vertical, horizontal o diagonal
        if (deltaFila <= 1 && deltaColumna <= 1) {
            // Verificar si la casilla de destino está ocupada por una pieza del mismo color
            if (tablero[nuevaFila][nuevaColumna] != null && tablero[nuevaFila][nuevaColumna].obtenerColor().equals(obtenerColor())) {
                return false; // No se puede mover a una casilla ocupada por una pieza del mismo color
            }
            return true; // El movimiento es válido si está dentro del rango permitido (1 casilla) y la casilla no está ocupada
        }

        return false; // Si el movimiento no es válido (más de 1 casilla en cualquier dirección)
    }
}



