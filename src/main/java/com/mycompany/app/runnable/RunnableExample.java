package com.mycompany.app.runnable;

//* La segunda manera de crear hilos es implementando la interfaz Runnable

//* La interfaz Runnable nos permite definir una tarea que puede ser ejecutada
//* por un hilo

//* La ventaja principal es que podemos implementar las interfaces que queramos,
//* pero solo podemos extender de una clase. Por lo que el uso de la interfaz nos
//* otorga mayor flexibilidad

//* Otra ventaja es que promueve una mejor separacion de resposabilidades:
//* 1. La clase se encarga de la tarea
//* 2. El Thread se encarga de la ejecucion

public class RunnableExample implements Runnable {

    private String name;

    public RunnableExample(String name) {
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

        //* La diferencia con la extension de la clase Thread, es que primero debemos crear una
        //* instancia Thread y luego pasarle la instancia de la clase que implementa la interfaz Runnable

        Thread thread = new Thread(new RunnableExample("Tarea A"));
        Thread thread2 = new Thread(new RunnableExample("Tarea B"));

        //* No olvidar el uso del metodo start
        thread.start();
        thread2.start();

        System.out.println("Fin del hilo principal");
    }
    
}
