package com.mycompany.app.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutdownComparison {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 1; i < 5; i++) {
            final int taskID = i;
            executor.execute(
                () -> {
                    System.out.println("Tarea iniciando con Executor " + taskID + " " + Thread.currentThread().getName());

                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        System.out.println("Tarea " + taskID + " fue interrumpida");
                        return;
                    }

                    System.out.println("Tarea finalizada");
                }
            );
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        //* El error va a saltar siempre
        boolean error = true;

        if (error) {
            System.out.println("Situacion critica");
            //* Detiene immediatamente todas las tareas en ejecucion
            //* Y devuelve una lista con las tareas pendientes
            executor.shutdownNow();
        } else {
            System.out.println("Finalizacion correcta");
            //* No detiene las tareas que estan siendo ejecutadas
            //* Una vez terminadas las tareas ya cierra los hilos
            executor.shutdown();
        }
    }
}
