package com.mycompany.app.scheduledTasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Schedule {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        // executorService.schedule(() -> {
        //     System.out.println("Tarea despues de 4 segundos");
        // }, 4, TimeUnit.SECONDS);

        // //* Siempre debemos cerrar los hilos, pero es importante hacerlo cuando sepamos
        // //* que no van a ser empleados
        // executorService.shutdown();

        // //* EN el pasado se empleaba el Timer

        // Timer timer = new Timer();

        // //* no recibe ni runnable ni callable
        // timer.schedule(new TimerTask() {
        //     @Override
        //     public void run() {
        //         System.out.println("Tarea de dos segundos");
        //         //* Siempre debemos calcelarlo para que se termine la tarea
        //         timer.cancel();
        //     }
            
        // }, 2000);

        Runnable task = new Runnable() {
            int counter = 0;

            @Override
            public void run() {
                System.out.println("Enviando recordatorio");
                counter++;
                if (counter > 3) {
                    System.out.println("Se enviaron todos los recordatorios...");
                    executorService.shutdown();
                }
            }
            
        };

        //* EL metodo scheduleAtFixedRate nos crea una tarea periodica
        //* las tareas se ejecutan indefinidamanete, pero si queremos que
        //* se ejecuten un numero limitado de veces podemos crear un
        //* Runnable que limite el numero de veces
        executorService.scheduleAtFixedRate(task, 0, 3, TimeUnit.SECONDS);

        //* Para manejar fechas concretas podemos hacer lo siguiente
        LocalDateTime dateTime = LocalDateTime.of(2025, 5, 14, 10, 9);

        LocalDateTime now = LocalDateTime.now();

        long delay = Duration.between(now, dateTime).toMillis();

        if (delay < 0) {
            System.out.println("la fecha ya paso...");
            return;
        }

        executorService.schedule(() -> {
            System.out.println("Tarea despues de 4 segundos");
            executorService.shutdown();
        }, delay, TimeUnit.MILLISECONDS);
    }
}
