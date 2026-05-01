package com.mycompany.app.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//* La diferencia principal es que el executor puede reutilizar hilos

public class ThreadvsExecutor {
    public static void main(String[] args) {
        System.out.println("Con Thread -------------");
        for (int i = 1; i < 4; i++) {
            new Thread(
                () -> System.out.println("Tarea A Thread " + Thread.currentThread().getName())
            ).start();
        }

        System.out.println("Con Executor -------------");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 1; i < 4; i++) {
            executor.execute(
                () -> System.out.println("Tarea A Executor " + Thread.currentThread().getName())
            );
        }

        executor.shutdown();
    }
}
