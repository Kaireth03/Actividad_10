package Validadores;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ValidadorCaracterEspecial extends Thread {
    private String contraseña;
    private ConcurrentLinkedQueue<String> log;

    public ValidadorCaracterEspecial(String contraseña, ConcurrentLinkedQueue<String> log) {
        this.contraseña = contraseña;
        this.log = log;
    }

    @Override
    public void run() {
        String resultado = (!contraseña.matches(".*[!@#$%^&*(),.?\":{}|<>].*"))
            ? "La contraseña debe contener al menos un carácter especial."
            : "Contiene al menos un carácter especial.";
        System.out.println(resultado);
        log.add("Carácter especial: " + resultado);
    }
}

