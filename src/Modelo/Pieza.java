package Modelo;

// Clase abstracta que representa una pieza de ajedrez.
// Todas las piezas del ajedrez heredan de esta clase.
public abstract class Pieza {
    // Atributos privados para almacenar el color y la posición (fila y columna) de la pieza
    private String color;
    private int fila;
    private int columna;

    // Constructor que inicializa los atributos de la pieza (color, fila y columna)
    public Pieza(String color, int fila, int columna) {
        this.color = color;  // Asigna el color de la pieza (ej. "blanco" o "negro")
        this.fila = fila;    // Asigna la fila donde está ubicada la pieza
        this.columna = columna;  // Asigna la columna donde está ubicada la pieza
    }

    // Método para obtener el color de la pieza
    public String obtenerColor() {
        return color;  // Devuelve el color de la pieza
    }

    // Método para obtener la fila actual de la pieza
    public int obtenerFila() {
        return fila;  // Devuelve la fila donde se encuentra la pieza
    }

    // Método para obtener la columna actual de la pieza
    public int obtenerColumna() {
        return columna;  // Devuelve la columna donde se encuentra la pieza
    }

    // Método para mover la pieza a una nueva posición
    // Actualiza la fila y columna de la pieza
    public void mover(int nuevaFila, int nuevaColumna) {
        this.fila = nuevaFila;     // Asigna la nueva fila
        this.columna = nuevaColumna;  // Asigna la nueva columna
    }

    // Método abstracto que debe ser implementado por las clases hijas.
    // Cada tipo de pieza implementará este método para verificar si el movimiento es válido
    public abstract boolean esMovimientoValido(int nuevaFila, int nuevaColumna, Pieza[][] tablero);
}





