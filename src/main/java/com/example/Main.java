package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {


        ServerSocket ss = new ServerSocket(2000);   //creazione della serversocket sulla porta 2000
        Socket s = ss.accept(); //accettazione della connessione dal client
        MioThread m = new MioThread(s); //creazione del thread per la gestione della connessione
        m.start();  //avvio del thread

    }
}