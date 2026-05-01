package com.mycompany.app.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTypes {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("Ejecutando la tarea " + Thread.currentThread().getName());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Tarea completada en el hilo " + Thread.currentThread().getName());
        };

        //* Gestiona un numero fijo de hilos
        System.out.println("Ejecutando newFixedThreadPool -------------------");

        ExecutorService fixedPool = Executors.newFixedThreadPool(2);
        
        for (int i = 1; i <= 5; i++) {
            fixedPool.execute(task);
        }
        fixedPool.shutdown();

        //* Crea hilos dinamicamente
        //* Es el mas eficiente pero hay que tener mucho cuidado con el uso de la memoria
        //* podemos desbordarla si ejecutamos tareas complejas
        System.out.println("Ejecutando newCachedThreadPool --------------------");

        ExecutorService cachedPool = Executors.newCachedThreadPool();
        
        for (int i = 1; i <= 5; i++) {
            cachedPool.execute(task);
        }
        cachedPool.shutdown();

        //* Gestiona un solo hilo
        System.out.println("Ejecutando newSingleThreadExecutor --------------------");

        ExecutorService single = Executors.newSingleThreadExecutor();
        
        for (int i = 1; i <= 5; i++) {
            single.execute(task);
        }
        single.shutdown();
    }
}
