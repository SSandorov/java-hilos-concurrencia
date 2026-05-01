package com.mycompany.app.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {
    public static void main(String[] args) {

        //* El ExecutorService es un coordinador de tareas
        //* separa la creacion de los hilos de su ejecucion

        //* A diferencia del uso directo de la clase Thread o la interfaz
        //* Runnable, donde nosotros gestionamos manualmente
        //* Con el executor delegamos la creacion y el manejo de hilos

        //* Este manejo automatico nos mejora la escalabilidad y el rendimiento
        //* de nuestros proyectos

        //* creamos los hilos
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //* El metodo execute recibe como argumento un tipo de dato Runnable
        executor.execute( () -> System.out.println("Tarea A " + Thread.currentThread().getName()) );
        executor.execute( () -> System.out.println("Tarea B " + Thread.currentThread().getName()) );
        executor.execute( () -> System.out.println("Tarea C " + Thread.currentThread().getName()) );

        //* Despues del shutdown no podemos ejecutar mas tareas en paralelo
        executor.shutdown();
        // executor.execute( () -> System.out.println("Tarea A") );
    }
}
