package Validadores;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ValidadorMayusculas extends Thread {
    private String contraseña;
    private ConcurrentLinkedQueue<String> log;

    public ValidadorMayusculas(String contraseña, ConcurrentLinkedQueue<String> log) {
        this.contraseña = contraseña;
        this.log = log;
    }

    @Override
    public void run() {
        long count = contraseña.chars().filter(Character::isUpperCase).count();
        String resultado = (count < 2)
            ? "La contraseña debe contener al menos dos letras mayúsculas."
            : "Contiene suficientes letras mayúsculas.";
        System.out.println(resultado);
        log.add("Mayúsculas: " + resultado);
    }
}

