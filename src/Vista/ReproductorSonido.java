package Vista;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class ReproductorSonido {
    public static void reproducir(String rutaSonido) {
        try {
            File archivo = new File(rutaSonido);
            if (archivo.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            } else {
                System.out.println("Archivo de sonido no encontrado: " + rutaSonido);
            }
        } catch (Exception e) {
            System.out.println("Error al reproducir el sonido: " + rutaSonido);
            e.printStackTrace();
        }
    }
}

