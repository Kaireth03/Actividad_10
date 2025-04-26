package Validadores;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ValidadorNumero extends Thread {
    private String contraseña;
    private ConcurrentLinkedQueue<String> log;

    public ValidadorNumero(String contraseña, ConcurrentLinkedQueue<String> log) {
        this.contraseña = contraseña;
        this.log = log;
    }

    @Override
    public void run() {
        String resultado = (!contraseña.matches(".*\\d.*"))
            ? "La contraseña debe contener al menos un número."
            : "Contiene al menos un número.";
        System.out.println(resultado);
        log.add("Número: " + resultado);
    }
}

