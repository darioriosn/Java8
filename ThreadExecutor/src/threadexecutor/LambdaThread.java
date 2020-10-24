/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadexecutor;

/**
 *
 * @author Dario
 */
public class LambdaThread {
     public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": Prueba de thread con lambda");
 
        // Anonymous Runnable
 
        Runnable task1 = new Runnable(){
 
          @Override
          public void run(){
            System.out.println(Thread.currentThread().getName() + " está corriendo");
          }
        };
 
 
        // Passing a Runnable when creating a new thread
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName() + " está corriendo");
            }
        });
 
 
        // Lambda Runnable
        Runnable task3 = () -> {
            System.out.println(Thread.currentThread().getName() + " está corriendo");
        };
 
        Thread thread1 = new Thread(task1);
 
        thread1.start();
        thread2.start();
 
        new Thread(task3).start();
 
    }
}
