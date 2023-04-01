package View;

import Controller.Cozinha;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int id= 1;
        Semaphore semaforo= new Semaphore(1);

        for (int i=1; i<=5; i++){
            Thread Cozinhar= new Cozinha(semaforo, id);
            Cozinhar.start();
            id++;
        }

    }
}
