import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import Validadores.ValidadorCaracterEspecial;
import Validadores.ValidadorLongitud;
import Validadores.ValidadorMayusculas;
import Validadores.ValidadorMinusculas;
import Validadores.ValidadorNumero;

public class LoggerUtil {

    public static void guardarEnArchivo(String contraseña, ConcurrentLinkedQueue<String> log) {
        try (FileWriter writer = new FileWriter("registro_validaciones.txt", true)) {
            writer.write("\nContraseña: " + contraseña + "\n");
            log.forEach(linea -> {
                try {
                    writer.write(linea + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.write("--------------------------\n");
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String respuesta;

        do{

        System.out.print("Ingrese una contraseña para validar: ");
        String contraseña = scanner.nextLine();

        ConcurrentLinkedQueue<String> log = new ConcurrentLinkedQueue<>();

        Thread longitud = new ValidadorLongitud(contraseña, log);
        Thread especiales = new ValidadorCaracterEspecial(contraseña, log);
        Thread mayusculas = new ValidadorMayusculas(contraseña, log);
        Thread minusculas = new ValidadorMinusculas(contraseña, log);
        Thread numeros = new ValidadorNumero(contraseña, log);

        longitud.start();
        especiales.start();
        mayusculas.start();
        minusculas.start();
        numeros.start();

        try {
            longitud.join();
            especiales.join();
            mayusculas.join();
            minusculas.join();
            numeros.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        guardarEnArchivo(contraseña, log);
        System.out.print("\n¿Desea ingresar otra contraseña? (s/n): ");
        respuesta = scanner.nextLine().toLowerCase();
    } while (respuesta.equals("s"));
    }
}
