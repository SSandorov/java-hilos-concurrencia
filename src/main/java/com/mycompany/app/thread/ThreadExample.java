package com.mycompany.app.thread;

//* La primera manera de crear hilos es extendido la clase Thread

//* La clase Thread nos permite representar un hilo de ejecucion en Java
//* Esta clase tiene la capacidad de ejecutar codigo en paralelo
public class ThreadExample extends Thread{

    private String name;

    public ThreadExample(String name) {
        this.name = name;
    }

    //* Primero debemos sobreescribir el metodo run de la clase Thread
    @Override
    public void run() {
        //* Dentro del metodo run va el codigo que queramos que se ejecute en el hilo
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + " Mensaje: " + i + " Ejecutando en: " + Thread.currentThread().getName());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Inicio: " + Thread.currentThread().getName());

        ThreadExample threadExample = new ThreadExample("Proceso A");
        ThreadExample threadExample2 = new ThreadExample("Proceso B");

        //* Debemos llamar al metodo start para ejecutar el thread, no el metodo run
        threadExample.start();
        threadExample2.start();

        System.out.println("Fin del hilo principal");
    }

}
