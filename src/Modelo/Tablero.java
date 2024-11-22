package Modelo;

import java.util.ArrayList;
import java.util.List;

// Clase que representa el tablero de ajedrez y gestiona las piezas y sus movimientos
public class Tablero {
    // Matriz bidimensional que representa el tablero, cada celda puede contener una pieza
    private Pieza[][] tablero;

    // Constructor que inicializa el tablero
    public Tablero() {
        tablero = new Pieza[8][8];  // Creamos un tablero de 8x8
        inicializarTablero();  // Llamamos a un método para colocar las piezas iniciales
    }

    // Método privado para colocar las piezas iniciales en el tablero
    private void inicializarTablero() {
        // Colocar las piezas blancas en la primera fila
        tablero[0][0] = new Torre("blanco", 0, 0);
        tablero[0][1] = new Caballo("blanco", 0, 1);
        tablero[0][2] = new Alfil("blanco", 0, 2);
        tablero[0][3] = new Reina("blanco", 0, 3);
        tablero[0][4] = new Rey("blanco", 0, 4);
        tablero[0][5] = new Alfil("blanco", 0, 5);
        tablero[0][6] = new Caballo("blanco", 0, 6);
        tablero[0][7] = new Torre("blanco", 0, 7);

        // Colocar los peones blancos en la segunda fila
        for (int i = 0; i < 8; i++) {
            tablero[1][i] = new Peon("blanco", 1, i);
        }

        // Colocar las piezas negras en la última fila
        tablero[7][0] = new Torre("negro", 7, 0);
        tablero[7][1] = new Caballo("negro", 7, 1);
        tablero[7][2] = new Alfil("negro", 7, 2);
        tablero[7][3] = new Reina("negro", 7, 3);
        tablero[7][4] = new Rey("negro", 7, 4);
        tablero[7][5] = new Alfil("negro", 7, 5);
        tablero[7][6] = new Caballo("negro", 7, 6);
        tablero[7][7] = new Torre("negro", 7, 7);

        // Colocar los peones negros en la séptima fila
        for (int i = 0; i < 8; i++) {
            tablero[6][i] = new Peon("negro", 6, i);
        }
    }

    // Método para obtener la pieza en una posición específica del tablero
    public Pieza obtenerPieza(int fila, int columna) {
        return tablero[fila][columna];  // Retorna la pieza en las coordenadas indicadas
    }

    // Método para mover una pieza desde una posición de origen a una de destino
    public boolean moverPieza(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        Pieza pieza = obtenerPieza(filaOrigen, columnaOrigen);  // Obtenemos la pieza de la posición de origen

        // Verificar si hay una pieza en la posición de origen
        if (pieza == null) {
            System.out.println("No hay ninguna pieza en la posición de origen.");
            return false;  // Si no hay pieza, el movimiento no es válido
        }

        // Verificar si el movimiento de la pieza es válido
        if (!pieza.esMovimientoValido(filaDestino, columnaDestino, tablero)) {
            System.out.println("Movimiento inválido para esta pieza.");
            return false;  // Si el movimiento no es válido, el método retorna falso
        }

        // Verificar si la casilla de destino está ocupada por una pieza del mismo color
        if (tablero[filaDestino][columnaDestino] != null &&
                tablero[filaDestino][columnaDestino].obtenerColor().equals(pieza.obtenerColor())) {
            System.out.println("No puedes capturar una pieza del mismo color.");
            return false;  // No se puede mover a una casilla ocupada por una pieza del mismo color
        }

        // Realizar el movimiento: colocar la pieza en la nueva posición y vaciar la casilla de origen
        tablero[filaDestino][columnaDestino] = pieza;
        tablero[filaOrigen][columnaOrigen] = null;
        pieza.mover(filaDestino, columnaDestino);  // Actualizar la posición de la pieza

        return true;  // Movimiento realizado con éxito
    }

    // Método para mostrar el estado actual del tablero
    public void mostrarTablero() {
        // Recorremos todas las filas y columnas del tablero
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pieza pieza = tablero[i][j];  // Obtenemos la pieza en la posición actual
                if (pieza == null) {
                    System.out.print(" . ");  // Si no hay pieza, mostramos un punto
                } else {
                    // Si hay pieza, mostramos la primera letra del nombre de la clase de la pieza
                    System.out.print(" " + pieza.getClass().getSimpleName().charAt(0) + " ");
                }
            }
            System.out.println();  // Salto de línea después de cada fila
        }
    }

    // Método para obtener el tablero completo
    public Pieza[][] obtenerTablero() {
        return tablero;  // Retorna la matriz del tablero
    }

    // Método que obtiene todos los movimientos posibles para una pieza en una posición específica
    public List<int[]> obtenerMovimientosPosibles(int fila, int columna) {
        List<int[]> movimientosPosibles = new ArrayList<>();  // Lista para almacenar los movimientos válidos
        Pieza pieza = tablero[fila][columna];  // Obtenemos la pieza en la posición indicada

        if (pieza == null) {
            return movimientosPosibles;  // Si no hay pieza, retornamos la lista vacía
        }

        // Iteramos por todas las posiciones del tablero
        for (int filaDestino = 0; filaDestino < 8; filaDestino++) {
            for (int columnaDestino = 0; columnaDestino < 8; columnaDestino++) {
                // Si el movimiento es válido, lo agregamos a la lista
                if (pieza.esMovimientoValido(filaDestino, columnaDestino, tablero)) {
                    movimientosPosibles.add(new int[]{filaDestino, columnaDestino});
                }
            }
        }
        return movimientosPosibles;  // Retornamos la lista con los movimientos válidos
    }
}



