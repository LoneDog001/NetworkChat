package client;

import java.io.BufferedReader;
import java.io.IOException;

public class Answer extends Thread{
    private final BufferedReader input;

    public Answer(BufferedReader input) {
        this.input = input;
    }

    @Override
    public void run(){
        try {
            while (!Thread.currentThread().isInterrupted()){
                System.out.println(input.readLine());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
