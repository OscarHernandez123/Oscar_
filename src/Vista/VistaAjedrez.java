package Vista;

import Modelo.Pieza;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaAjedrez extends JFrame {
    // Matriz de botones para cada casilla del tablero de ajedrez
    private JButton[][] botones = new JButton[8][8];
    // Botones de control para guardar, salir y reiniciar
    private JButton botonGuardar, botonSalir, botonReiniciar;
    // Etiqueta que muestra el turno del jugador
    private JLabel etiquetaTurno;

    // Colores de las casillas, uno claro y otro oscuro
    private final Color COLOR_CLARO = new Color(238, 214, 179);
    private final Color COLOR_OSCURO = new Color(118, 83, 48);

    public VistaAjedrez() {
        setTitle("Ajedrez");
        setLayout(new BorderLayout());

        // Panel que dibuja el tablero de ajedrez con fondo alternado
        JPanel panelTablero = new JPanel(new GridLayout(8, 8)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibuja las casillas del tablero alternando colores
                for (int fila = 0; fila < 8; fila++) {
                    for (int columna = 0; columna < 8; columna++) {
                        // Alterna entre colores claros y oscuros según la posición de la casilla
                        if ((fila + columna) % 2 == 0) {
                            g.setColor(COLOR_CLARO); // Casilla clara
                        } else {
                            g.setColor(COLOR_OSCURO); // Casilla oscura
                        }
                        // Dibuja cada casilla en la ubicación correspondiente
                        g.fillRect(columna * getWidth() / 8, fila * getHeight() / 8, getWidth() / 8, getHeight() / 8);
                    }
                }
            }
        };

        // Crear y añadir botones para cada casilla del tablero
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                botones[fila][columna] = new JButton();
                botones[fila][columna].setOpaque(true); // Hacer visible el fondo
                botones[fila][columna].setContentAreaFilled(true); // Permitir rellenar el área de contenido
                botones[fila][columna].setBorderPainted(false); // Quitar el borde
                Color colorOriginal = (fila + columna) % 2 == 0 ? COLOR_CLARO : COLOR_OSCURO;
                botones[fila][columna].setBackground(colorOriginal); // Asignar color de fondo
                panelTablero.add(botones[fila][columna]); // Añadir el botón al panel
            }
        }

        // Crear los botones de control: guardar, salir y reiniciar
        botonGuardar = new JButton("Guardar Partida");
        botonSalir = new JButton("Salir");
        botonReiniciar = new JButton("Reiniciar Partida");

        // Crear un panel para los botones de control y añadir los botones
        JPanel panelControl = new JPanel();
        panelControl.add(botonGuardar);
        panelControl.add(botonSalir);
        panelControl.add(botonReiniciar);

        // Añadir los paneles al JFrame (el tablero al centro y el panel de control al sur)
        add(panelTablero, BorderLayout.CENTER);
        add(panelControl, BorderLayout.SOUTH);

        // Etiqueta que muestra el turno actual (por defecto Blanco)
        etiquetaTurno = new JLabel("Turno: Blanco");
        add(etiquetaTurno, BorderLayout.NORTH);

        // Configuración básica del JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
    }

    // Método para añadir el escuchador al botón de guardar
    public void agregarEscuchadorGuardar(ActionListener escuchador) {
        botonGuardar.addActionListener(escuchador);
    }

    // Método para añadir el escuchador al botón de salir
    public void agregarEscuchadorSalir(ActionListener escuchador) {
        botonSalir.addActionListener(escuchador);
    }

    // Método para añadir el escuchador al botón de reiniciar
    public void agregarEscuchadorReiniciar(ActionListener escuchador) {
        botonReiniciar.addActionListener(escuchador);
    }

    // Método para obtener el botón correspondiente a una casilla (fila, columna)
    public JButton obtenerBoton(int fila, int columna) {
        return botones[fila][columna];
    }

    // Método para cargar la imagen de una pieza en una casilla específica
    public void cargarImagenPieza(int fila, int columna, String rutaImagen) {
        try {
            java.net.URL imgURL = getClass().getResource(rutaImagen);
            if (imgURL != null) {
                ImageIcon icono = new ImageIcon(imgURL);
                Image imagen = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                botones[fila][columna].setIcon(new ImageIcon(imagen)); // Establece la imagen en el botón
            } else {
                System.out.println("No se pudo encontrar la imagen: " + rutaImagen);
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + rutaImagen);
            e.printStackTrace();
        }
    }

    // Método que actualiza el tablero con las piezas según el estado del tablero
    public void actualizarTablero(Pieza[][] tablero) {
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Pieza pieza = tablero[fila][columna];
                if (pieza == null) {
                    botones[fila][columna].setIcon(null); // Si no hay pieza, se elimina la imagen
                } else {
                    // Si hay pieza, carga la imagen correspondiente según la clase de la pieza y su color
                    String rutaImagen = "/imagenes/" + pieza.getClass().getSimpleName() + "_" + pieza.obtenerColor() + ".png";
                    cargarImagenPieza(fila, columna, rutaImagen);
                }
            }
        }
    }

    // Método para mostrar las piezas en el tablero según su posición
    public void mostrarPiezasEnVista(Pieza[][] tablero) {
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Pieza pieza = tablero[fila][columna];
                if (pieza != null) {
                    String rutaImagen = "/imagenes/" + pieza.obtenerColor() + "_" + pieza.getClass().getSimpleName().toLowerCase() + ".png";
                    cargarImagenPieza(fila, columna, rutaImagen); // Cargar la imagen de la pieza
                } else {
                    obtenerBoton(fila, columna).setIcon(null); // Si no hay pieza, eliminar la imagen
                }
            }
        }
    }

    // Método para actualizar la etiqueta que muestra el turno
    public void mostrarTurno(String turno) {
        etiquetaTurno.setText("Turno: " + turno); // Muestra el turno actual
    }

    // Método para resaltar una casilla con un color específico
    public void resaltarCasilla(int fila, int columna, Color color) {
        botones[fila][columna].setBackground(color); // Cambia el color de fondo de la casilla
    }

    // Método para restaurar los colores originales de las casillas del tablero
    public void restaurarColoresOriginales() {
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Color colorOriginal = (fila + columna) % 2 == 0 ? COLOR_CLARO : COLOR_OSCURO;
                botones[fila][columna].setBackground(colorOriginal); // Restaura el color de fondo original
            }
        }
    }

    // Método para limpiar los colores de todas las casillas, restaurándolas a su color original
    public void limpiarColoresCasillas() {
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Color colorOriginal = (fila + columna) % 2 == 0 ? COLOR_CLARO : COLOR_OSCURO;
                botones[fila][columna].setBackground(colorOriginal); // Restaura los colores originales
            }
        }
    }
}







