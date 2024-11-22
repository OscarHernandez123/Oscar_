package Modelo;

// Clase que representa a la pieza Reina en el ajedrez, que hereda de la clase Pieza.
public class Reina extends Pieza {

    // Constructor que recibe el color, fila y columna de la reina
    public Reina(String color, int fila, int columna) {
        super(color, fila, columna);  // Llama al constructor de la clase base (Pieza)
    }

    // Método que determina si el movimiento de la Reina es válido según las reglas del ajedrez
    @Override
    public boolean esMovimientoValido(int nuevaFila, int nuevaColumna, Pieza[][] tablero) {
        // Calcula las diferencias absolutas entre las filas y columnas de la pieza actual y la nueva posición
        int deltaFila = Math.abs(nuevaFila - obtenerFila());
        int deltaColumna = Math.abs(nuevaColumna - obtenerColumna());

        // La Reina puede moverse en línea recta (vertical, horizontal) o diagonal
        if (deltaFila == deltaColumna || nuevaFila == obtenerFila() || nuevaColumna == obtenerColumna()) {
            // Determina la dirección en la que la Reina se moverá (fila y columna)
            int pasoFila = (nuevaFila > obtenerFila()) ? 1 : (nuevaFila < obtenerFila()) ? -1 : 0;
            int pasoColumna = (nuevaColumna > obtenerColumna()) ? 1 : (nuevaColumna < obtenerColumna()) ? -1 : 0;

            // Variables temporales para recorrer las casillas intermedias entre la posición actual y la nueva posición
            int filaTemp = obtenerFila() + pasoFila;
            int columnaTemp = obtenerColumna() + pasoColumna;

            // Recorre las casillas intermedias (a lo largo de la fila y columna) para verificar que están libres de piezas
            while (filaTemp != nuevaFila || columnaTemp != nuevaColumna) {
                // Si hay una pieza en el camino, el movimiento no es válido
                if (tablero[filaTemp][columnaTemp] != null) {
                    return false; // Hay una pieza en el camino
                }
                filaTemp += pasoFila;  // Avanza una casilla en la dirección de la fila
                columnaTemp += pasoColumna;  // Avanza una casilla en la dirección de la columna
            }

            // Verificar si la casilla de destino está ocupada por una pieza del mismo color
            if (tablero[nuevaFila][nuevaColumna] != null && tablero[nuevaFila][nuevaColumna].obtenerColor().equals(obtenerColor())) {
                return false; // No se puede mover a una casilla ocupada por una pieza del mismo color
            }

            return true; // El movimiento es válido si no hay piezas en el camino y la casilla destino es válida
        }

        return false; // Si el movimiento no es válido (ni diagonal, ni vertical, ni horizontal)
    }
}



