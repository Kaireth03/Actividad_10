package Validadores;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ValidadorMinusculas extends Thread {
    private String contraseña;
    private ConcurrentLinkedQueue<String> log;

    public ValidadorMinusculas(String contraseña, ConcurrentLinkedQueue<String> log) {
        this.contraseña = contraseña;
        this.log = log;
    }

    @Override
    public void run() {
        long count = contraseña.chars().filter(Character::isLowerCase).count();
        String resultado = (count < 3)
            ? "La contraseña debe contener al menos tres letras minúsculas."
            : "Contiene suficientes letras minúsculas.";
        System.out.println(resultado);
        log.add("Minúsculas: " + resultado);
    }
}

