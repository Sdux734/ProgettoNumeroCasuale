package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class MioThread extends Thread{  //estensione della classe Thread

    private Socket socket;  //dichiarazione della socket
    private BufferedReader in;  //dichiarazione del buffer di input
    private PrintWriter out;    //dichiarazione del buffer di output
    
    public MioThread(Socket s) throws IOException { //costruttore della classe
        
        socket = s; //inizializzazione della socket
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));    //inizializzazione del buffer di input
        out = new PrintWriter(socket.getOutputStream(), true);  //inizializzazione del buffer di output
    }

    @Override   //sovrascrittura del metodo run
    public void run() { //metodo run del thread
        
        Scanner scanner = new Scanner(System.in);   

        System.out.println("Client connesso!");
        out.println("Scegli un numero tra 0-100!");

        Random rand = new Random(); //creazione dell'oggetto Random
        int n = rand.nextInt(101); // da 0 a 100
        System.out.println(n);

        String line = "";   
        try {
            line = in.readLine();
            int numero = Integer.parseInt(line); 

            if(numero == n){
                out.println("Hai vinto!");
            }
            else{
                out.println("Hai perso!");
            }
        } catch (IOException e) {   //gestione eccezioni
            System.err.println("Errore nella comunicazione con il client: " + e.getMessage());
            e.printStackTrace();    
        } finally { // Chiusura delle risorse - si esegue SEMPRE
            
            try {
                System.out.println("Chiusura connessione con il client...");
                
                if (scanner != null) scanner.close();  //chiude lo scanner controllando che non sia null
                if (in != null) in.close();  //chiude il buffer di input controllando che non sia null
                if (out != null) out.close();  //chiude il buffer di output controllando che non sia null
                if (socket != null && !socket.isClosed()) socket.close();  //chiude la socket controllando che non sia null e che non sia già chiusa
                
                System.out.println("Connessione chiusa con successo!");
            } catch (IOException e) {   //gestione eccezioni durante la chiusura
                System.err.println("Errore nella chiusura della connessione: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
}


