package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(2000);
        
        System.out.println("Server in ascolto sulla porta 2000...");
        
        
        while(true) {   // Loop infinito per accettare più client
            Socket s = ss.accept(); // Attende un client
            System.out.println("Nuovo client connesso!");   
            
            MioThread m = new MioThread(s);
            m.start(); // Avvia il thread - ogni client ha il suo thread
        }
    }
}