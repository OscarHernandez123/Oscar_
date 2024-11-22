package Modelo;

public class Torre extends Pieza {

    // Constructor de la Torre que recibe el color y las coordenadas de la pieza
    public Torre(String color, int fila, int columna) {
        super(color, fila, columna);
    }

    // Método que verifica si el movimiento de la torre es válido
    @Override
    public boolean esMovimientoValido(int nuevaFila, int nuevaColumna, Pieza[][] tablero) {
        // Verifica si el movimiento es horizontal o vertical (la torre solo se mueve así)
        if (nuevaFila == obtenerFila() || nuevaColumna == obtenerColumna()) {
            // Calcula la dirección del movimiento (hacia arriba, abajo, izquierda o derecha)
            int pasoFila = (nuevaFila > obtenerFila()) ? 1 : (nuevaFila < obtenerFila()) ? -1 : 0;
            int pasoColumna = (nuevaColumna > obtenerColumna()) ? 1 : (nuevaColumna < obtenerColumna()) ? -1 : 0;

            // Comienza desde la posición actual de la torre y verifica el camino hacia la nueva posición
            int filaTemp = obtenerFila() + pasoFila;
            int columnaTemp = obtenerColumna() + pasoColumna;

            // Recorre las casillas intermedias entre la posición actual y la nueva posición
            while (filaTemp != nuevaFila || columnaTemp != nuevaColumna) {
                // Si hay una pieza en el camino, el movimiento no es válido
                if (tablero[filaTemp][columnaTemp] != null) {
                    return false; // Hay una pieza bloqueando el camino
                }
                filaTemp += pasoFila; // Avanza a la siguiente casilla en la dirección de la fila
                columnaTemp += pasoColumna; // Avanza a la siguiente casilla en la dirección de la columna
            }

            // Verifica si la casilla de destino está ocupada por una pieza del mismo color
            if (tablero[nuevaFila][nuevaColumna] != null && tablero[nuevaFila][nuevaColumna].obtenerColor().equals(obtenerColor())) {
                return false; // No se puede mover a una casilla ocupada por una pieza del mismo color
            }

            // Si no hay piezas en el camino y la casilla de destino es libre o tiene una pieza enemiga, el movimiento es válido
            return true;
        }

        // Si el movimiento no es horizontal ni vertical, no es válido para la torre
        return false;
    }
}



