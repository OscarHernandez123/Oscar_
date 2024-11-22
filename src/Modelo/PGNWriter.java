package Modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Clase que maneja la escritura de partidas de ajedrez en formato PGN
public class PGNWriter {
    // Lista que almacenará los movimientos de la partida
    private List<String> movimientos;
    // Resultado final de la partida (por defecto es "*", indicando que aún no se ha determinado)
    private String resultado;

    // Constructor de la clase, inicializa los movimientos y el resultado
    public PGNWriter() {
        this.movimientos = new ArrayList<>(); // Inicializa la lista de movimientos vacía
        this.resultado = "*"; // Resultado inicial, cambiará al final del juego
    }

    // Método para agregar un movimiento a la lista de movimientos
    public void agregarMovimiento(String movimiento) {
        movimientos.add(movimiento); // Añade el movimiento a la lista
    }

    // Método para establecer el resultado final de la partida
    public void setResultado(String resultado) {
        this.resultado = resultado; // Asigna el resultado de la partida (ej. "1-0", "0-1", "1/2-1/2")
    }

    // Método para guardar la partida en un archivo con formato PGN
    public void guardarPartida(String nombreArchivo, String jugadorBlanco, String jugadorNegro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Escribir los encabezados del archivo PGN (información del evento, jugadores, fecha y resultado)
            writer.write("[Event \"Partida de Ajedrez\"]\n"); // Evento
            writer.write("[Site \"Local\"]\n"); // Sitio de la partida (local)
            writer.write("[Date \"" + java.time.LocalDate.now() + "\"]\n"); // Fecha actual
            writer.write("[White \"" + jugadorBlanco + "\"]\n"); // Nombre del jugador blanco
            writer.write("[Black \"" + jugadorNegro + "\"]\n"); // Nombre del jugador negro
            writer.write("[Result \"" + resultado + "\"]\n"); // Resultado de la partida
            writer.write("\n"); // Salto de línea para separar encabezados de movimientos

            // Escribir la secuencia de movimientos
            for (int i = 0; i < movimientos.size(); i++) {
                if (i % 2 == 0) {
                    writer.write((i / 2 + 1) + ". "); // Número del turno, se escribe al principio de cada par de movimientos
                }
                writer.write(movimientos.get(i) + " "); // Escribe el movimiento actual
            }

            writer.write("\n"); // Salto de línea al final de los movimientos
        } catch (IOException e) {
            // Manejo de excepciones si ocurre algún error al escribir el archivo
            System.err.println("Error al guardar la partida en formato PGN: " + e.getMessage());
        }
    }
}


