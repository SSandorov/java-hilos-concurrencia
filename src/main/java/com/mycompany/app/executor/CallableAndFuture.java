package com.mycompany.app.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//* La interfaz Callable se emplea cuando nuestro hilo debe devolver un valor
//* El Runnable tiene el metodo run() que no retorna nada, por lo que debemos
//* emplear la interfaz callable que retorna un valor generico con el metodo call()
public class CallableAndFuture implements Callable<Integer> {

    private int number1;
    private int number2;

    

    public CallableAndFuture(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Tarea: Iniciando suma...");

        Thread.sleep(1500);

        int sum = number1 + number2;

        System.out.println("Name: " + Thread.currentThread().getName());
        System.out.println("Tarea: Suma completada");

        return sum;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Integer> sumTask = new CallableAndFuture(5, 60);

        //* Nos devuelve el resultado de la tarea que se ejecuta en un hilo
        //* La funcionalidad es equivalente al Promise en JS
        Future<Integer> result = executor.submit(sumTask);

        while (!result.isDone()) {
            System.out.println("Procesando...");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Resultado: " + result.get());

        executor.shutdown();
    }
    
}
