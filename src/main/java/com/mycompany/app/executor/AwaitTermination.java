package com.mycompany.app.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AwaitTermination {
    public static void main(String[] args) throws InterruptedException {
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

        boolean error = true;

        if (error) {
            System.out.println("Situacion critica");
            executor.shutdownNow();
        } else {
            System.out.println("Finalizacion correcta");
            executor.shutdown();
        }

        //* El metodo awaitTermination espera que terminen todas las tareas activas hasta un tiempo limite
        //* Sin este metodo no sabremos si las tareas finalizaron correctamente o no

        //* Es necesario emplearlo cuando pidamos recursos de un servidor, ya que no esta en nuestro control
        //* si esos recursos nos llegan o no
        if (executor.awaitTermination(10, TimeUnit.SECONDS)) {
            System.out.println("tareas finalizadas correctamente");
        } else {
            System.out.println("Las tareas no finalizaron corertamente");
        }
    }
}
