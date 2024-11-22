package Controlador;

import Modelo.*;
import Vista.ReproductorSonido;
import Vista.VistaAjedrez;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class ControladorAjedrez {
    private Tablero tablero;  // Referencia al tablero de ajedrez
    private VistaAjedrez vista;  // Vista para mostrar el tablero y la interfaz gráfica
    private int filaSeleccionada = -1;  // Fila de la pieza seleccionada, inicialmente no hay pieza seleccionada
    private int columnaSeleccionada = -1;  // Columna de la pieza seleccionada, inicialmente no hay pieza seleccionada
    private boolean esTurnoBlanco = true;  // Indica si es el turno de las piezas blancas, el turno inicial es blanco
    private PGNWriter pgnWriter = new PGNWriter();  // Para registrar los movimientos en formato PGN (Partie Game Notation)

    // Constructor que inicializa el controlador con el tablero y la vista
    public ControladorAjedrez(Tablero tablero, VistaAjedrez vista) {
        this.tablero = tablero;
        this.vista = vista;

        // Agregar los escuchadores de eventos a los botones de la vista (guardar, salir, reiniciar)
        vista.agregarEscuchadorGuardar(new EscuchadorGuardar());
        vista.agregarEscuchadorSalir(new EscuchadorSalir());
        vista.agregarEscuchadorReiniciar(new EscuchadorReiniciar());

        // Agregar los escuchadores de los botones del tablero
        agregarEscuchadoresTablero();

        // Mostrar las piezas en la vista según el estado actual del tablero
        vista.mostrarPiezasEnVista(tablero.obtenerTablero());

        // Mostrar el turno inicial (blanco o negro)
        vista.mostrarTurno(esTurnoBlanco ? "Blanco" : "Negro");
    }

    // Método que agrega los escuchadores de eventos de clics en cada casilla del tablero
    private void agregarEscuchadoresTablero() {
        // Iterar sobre cada fila y columna del tablero
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                // Capturamos el valor final de fila y columna para usarlos dentro del listener
                int finalFila = fila;
                int finalColumna = columna;
                // Agregar un escuchador de clic para cada botón en el tablero
                vista.obtenerBoton(fila, columna).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Cuando se hace clic, manejar el clic en la casilla correspondiente
                        manejarClick(finalFila, finalColumna);
                    }
                });
            }
        }
    }

    // Método para manejar el clic de un jugador en una casilla del tablero
    private void manejarClick(int fila, int columna) {
        // Restaurar los colores originales del tablero antes de hacer cualquier acción
        vista.restaurarColoresOriginales();

        // Si no se ha seleccionado ninguna pieza (fila y columna -1), se selecciona una pieza
        if (filaSeleccionada == -1 && columnaSeleccionada == -1) {
            // Obtener la pieza en la casilla seleccionada
            Pieza piezaSeleccionada = tablero.obtenerPieza(fila, columna);

            // Verificar si la pieza seleccionada pertenece al jugador cuyo turno es
            if (piezaSeleccionada != null &&
                    ((esTurnoBlanco && piezaSeleccionada.obtenerColor().equals("blanco")) ||
                            (!esTurnoBlanco && piezaSeleccionada.obtenerColor().equals("negro")))) {

                // Guardar la posición de la pieza seleccionada
                filaSeleccionada = fila;
                columnaSeleccionada = columna;

                // Obtener los movimientos posibles de la pieza seleccionada
                List<int[]> movimientos = tablero.obtenerMovimientosPosibles(fila, columna);

                // Resaltar las casillas a las que puede moverse la pieza
                for (int[] movimiento : movimientos) {
                    int filaDestino = movimiento[0];
                    int columnaDestino = movimiento[1];

                    // Obtener la pieza en la casilla de destino (si existe)
                    Pieza piezaDestino = tablero.obtenerPieza(filaDestino, columnaDestino);

                    // Definir el color de resaltado: verde si la casilla está vacía, rojo si hay pieza
                    Color colorResaltado = (piezaDestino == null) ? Color.GREEN : Color.RED;
                    // Resaltar la casilla en la vista
                    vista.resaltarCasilla(filaDestino, columnaDestino, colorResaltado);
                }
            } else {
                // Si no es el turno del jugador o la casilla seleccionada está vacía, mostrar mensaje
                JOptionPane.showMessageDialog(vista, "No es el turno de este color.");
            }
        } else {
            // Si ya hay una pieza seleccionada, intentar moverla
            if (tablero.moverPieza(filaSeleccionada, columnaSeleccionada, fila, columna)) {
                // Si el movimiento es válido, obtener la notación del movimiento en PGN
                String movimiento = obtenerNotacionPieza(tablero.obtenerPieza(fila, columna)) +
                        obtenerNotacionPosicion(fila, columna);

                // Registrar el movimiento en el archivo PGN
                pgnWriter.agregarMovimiento(movimiento);

                // Restaurar los colores originales del tablero y actualizar la vista
                vista.restaurarColoresOriginales();
                vista.mostrarPiezasEnVista(tablero.obtenerTablero());

                // Reproducir un sonido para el movimiento
                String rutaSonidoMovimiento = "src/Sonidos/move.wav";
                ReproductorSonido.reproducir(rutaSonidoMovimiento);

                // Alternar el turno después de un movimiento
                alternarTurno();

                // Verificar si el juego ha terminado (mate, empate, etc.)
                verificarEstadoDelJuego();
            } else {
                // Si el movimiento es inválido, imprimir un mensaje en consola
                System.out.println("Movimiento inválido");
            }

            // Restablecer la selección de la pieza después de realizar el movimiento
            filaSeleccionada = -1;
            columnaSeleccionada = -1;
        }
    }

    // Método para alternar el turno entre los jugadores
    private void alternarTurno() {
        esTurnoBlanco = !esTurnoBlanco; // Cambiar el turno
        vista.mostrarTurno(esTurnoBlanco ? "Blanco" : "Negro"); // Actualizar la vista
    }

    // Escuchador para guardar la partida
    private class EscuchadorGuardar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Llamar al método que guarda la partida
            guardarPartida("Jugador Blanco", "Jugador Negro");
        }
    }

    // Escuchador para salir del juego
    private class EscuchadorSalir implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Preguntar al usuario si está seguro de salir
            int respuesta = JOptionPane.showConfirmDialog(vista, "¿Estás seguro de salir?");
            if (respuesta == JOptionPane.YES_OPTION) {
                System.exit(0); // Salir del programa
            }
        }
    }

    // Escuchador para reiniciar la partida
    private class EscuchadorReiniciar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Reiniciar el tablero y el turno
            tablero = new Tablero();
            esTurnoBlanco = true; // Las blancas comienzan de nuevo
            vista.mostrarPiezasEnVista(tablero.obtenerTablero());
            vista.mostrarTurno("Blanco");
        }
    }

    // Método para verificar si el juego ha terminado (Rey capturado)
    private void verificarEstadoDelJuego() {
        boolean reyBlancoPresente = estaElReyEnElTablero("blanco");
        boolean reyNegroPresente = estaElReyEnElTablero("negro");

        // Si uno de los reyes ha sido capturado, terminar la partida
        if (!reyBlancoPresente || !reyNegroPresente) {
            String resultado = reyBlancoPresente ? "1-0" : "0-1"; // Determinar el ganador
            pgnWriter.setResultado(resultado); // Guardar el resultado en PGN

            // Guardar la partida al final
            guardarPartida("Jugador Blanco", "Jugador Negro");

            // Mostrar mensaje de finalización de partida
            JOptionPane.showMessageDialog(vista, "¡Partida terminada! Resultado: " + resultado);
            System.exit(0); // Salir del juego
        }
    }

    // Método para verificar si el rey de un color está presente en el tablero
    private boolean estaElReyEnElTablero(String color) {
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Pieza pieza = tablero.obtenerPieza(fila, columna);
                // Buscar el Rey del color especificado
                if (pieza instanceof Rey && pieza.obtenerColor().equals(color)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Método para obtener la notación correspondiente a una pieza
    private String obtenerNotacionPieza(Pieza pieza) {
        if (pieza instanceof Rey) return "K";
        if (pieza instanceof Reina) return "Q";
        if (pieza instanceof Torre) return "R";
        if (pieza instanceof Alfil) return "B";
        if (pieza instanceof Caballo) return "N";
        return ""; // El peón no tiene notación específica
    }

    // Método para obtener la notación de la posición en formato estándar
    private String obtenerNotacionPosicion(int fila, int columna) {
        char columnaLetra = (char) ('a' + columna); // Convertir la columna a letra (a-h)
        int filaNumero = 8 - fila; // Invertir la fila para el formato estándar (1-8)
        return "" + columnaLetra + filaNumero;
    }

    // Método para guardar la partida en un archivo PGN
    private void guardarPartida(String jugadorBlanco, String jugadorNegro) {
        String ruta = "src/partidasPGN/partida.pgn"; // Ruta para guardar el archivo
        File carpeta = new File("src/partidasPGN");

        // Crear carpeta si no existe
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        // Guardar archivo PGN
        pgnWriter.guardarPartida(ruta, jugadorBlanco, jugadorNegro);

        JOptionPane.showMessageDialog(vista, "Partida guardada en: " + ruta);
    }
}




