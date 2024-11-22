Este es un juego de ajedrez implementado en Java, con una interfaz gráfica de usuario (GUI) que permite jugar al ajedrez entre dos jugadores. La aplicación simula una partida de ajedrez, permitiendo a los jugadores mover las piezas en un tablero de ajedrez virtual.

El juego cuenta con las funcionalidades básicas de ajedrez, como el movimiento de las piezas, el seguimiento de los turnos y las reglas de validación de movimientos para cada tipo de pieza. Además, se incluye la capacidad de guardar la partida, reiniciar el juego o salir de la aplicación.

Funcionalidades Implementadas
Interfaz Gráfica:

Se muestra un tablero de ajedrez visualmente con botones para cada casilla.
Cada pieza se representa con una imagen y se puede mover dentro del tablero al hacer clic en las casillas.
Los botones permiten al jugador guardar la partida, reiniciar el juego o salir de la aplicación.

1.Piezas de Ajedrez:

El juego incluye todas las piezas tradicionales de ajedrez: Reina, Torre, Rey, Caballo, Alfil y Peón.
Las piezas tienen un color (blanco o negro) que se determina al inicio de la partida.
Cada pieza puede realizar movimientos válidos según las reglas tradicionales de ajedrez (por ejemplo, el Caballo mueve en forma de "L" y la Reina puede mover en cualquier dirección).

2.Turnos:

El juego alterna los turnos entre los jugadores, con el jugador blanco moviendo primero.
Después de cada movimiento, el turno se alterna automáticamente y se indica en la interfaz.

3.Validación de Movimientos:

Se implementan las reglas de movimiento para cada pieza, asegurando que los movimientos sean válidos según las reglas del ajedrez (por ejemplo, una Torre no puede moverse en diagonal).
El jugador no puede mover una pieza a una casilla ocupada por una pieza de su propio color.

4.Guardar Partida:

El jugador puede guardar el estado actual de la partida, lo que incluye la posición de todas las piezas en el tablero, el turno del jugador y otras configuraciones del juego.

5.Reiniciar Partida:

El jugador puede reiniciar la partida en cualquier momento, lo que restablecerá el tablero a su estado inicial con las piezas en sus posiciones originales.

6.Salir de la Aplicación:

El jugador puede salir de la aplicación en cualquier momento mediante un botón de salida.

Estructura del Proyecto:
1.Clase Pieza: Representa una pieza genérica de ajedrez con propiedades como color y posición.

2.Clases Torre, Reina, Rey, Caballo, Alfil y Peon: Heredan de la clase Pieza y representan cada tipo de pieza en el juego. Cada clase tiene métodos específicos para validar los movimientos de cada pieza.

3.Clase Tablero: Contiene el tablero de ajedrez y gestiona las piezas.

4.Clase VistaAjedrez: Proporciona la interfaz gráfica de usuario (GUI) utilizando botones y etiquetas.

5.Clase ControladorAjedrez: Maneja la lógica del juego, incluyendo el manejo de los turnos, las piezas y la validación de los movimientos.