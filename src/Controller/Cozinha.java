package Controller;

import java.util.concurrent.Semaphore;

public class Cozinha extends Thread{

    private int id;
    private String prato;
    private Semaphore semaforo;

    public Cozinha(Semaphore semaforo, int id){
        this.id= id;
        this.semaforo= semaforo;
    }

    @Override
    public void run() {
        Cozinhando();
        try {
            semaforo.acquire();
            Entregando();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            semaforo.release();
        }
    }

    private void Cozinhando() {
        int tempo= 0;
        int percentual= 0;
        int segundos= 0;

        if (id % 2 != 0){
            tempo= (int) (Math.random()* 301)+ 500;
            prato= "Sopa de Cebola";
        }
        else {
            tempo= (int) (Math.random()* 601)+ 600;
            prato= "Lasanha a Bolonhesa";
        }
        System.out.println("O prato "+ prato +" do cliente #"+ id +" começou a cozinhar");
        while (segundos < tempo){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            segundos+= 100;
            percentual= (100 * segundos) / tempo;
            if (percentual > 100){
                percentual= 100;
            }
            System.out.println("O prato "+ prato +" do cliente #"+ id +" está "+ percentual +"% pronto.");

        }
        System.out.println("O prato "+ prato +" do cliente #"+ id +" está pronto.");
    }

    private void Entregando() {
        try {
            System.out.println("O prato "+ prato +" do cliente #"+ id +" foi para a entrega.");
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("O prato "+ prato +" do cliente #"+ id +" foi entregue.");
    }
}
