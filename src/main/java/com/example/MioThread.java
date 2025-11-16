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

        out.println("Client connesso!");

        int r = 0; 
        boolean b = false;

        String line = "";
        
        // Do-while con try-catch DENTRO per gestire input non validi
        do {
            try {
                out.println("scegli il range con cui giocare:");
                out.println("1. 0-10");
                out.println("2. 0-100");
                out.println("3. 0-1000");

                line = in.readLine();
                int numero = Integer.parseInt(line);

                if(numero == 1){
                    r = 11; 
                    out.println("Scegli un numero tra 0-10!"); 
                    b = true;
                } 
                else if(numero == 2){
                    r = 101; 
                    out.println("Scegli un numero tra 0-100!"); 
                    b = true;
                } 
                else if(numero == 3){
                    r = 1001; 
                    out.println("Scegli un numero tra 0-1000!"); 
                    b = true;
                }
                else {
                    out.println("Scelta non valida! Riprova.");
                }
                
            } catch (NumberFormatException e) {
                // Se il client invia un valore non numerico
                out.println("Errore! Inserisci un numero valido (1, 2 o 3).");
            } catch (IOException e) {
                System.err.println("Errore nella comunicazione: " + e.getMessage());
                e.printStackTrace();
                break;  // Esce dal ciclo in caso di errore critico
            }
        } while(!b);  // Ripete finché b è false (scelta non valida)
            
        Random rand = new Random(); //creazione dell'oggetto Random
        int n = rand.nextInt(r); // da 0 a 10 / 100 / 1000
        System.out.println(n);

        
        boolean numeroValido = false;   // Do-while per gestire la scelta del numero dal client
        do {
            try {
                line = in.readLine();
                int numero = Integer.parseInt(line); 

                if(numero == n){
                    out.println("Hai vinto!");
                }
                else{
                    out.println("Hai perso!");
                }
                numeroValido = true;  // Esce dal ciclo se tutto va bene
                
            } catch (NumberFormatException e) { // Se il client invia un valore non numerico
                out.println("Errore! Inserisci un NUMERO valido.");

            } catch (IOException e) {   //gestione eccezioni
                System.err.println("Errore nella comunicazione con il client: " + e.getMessage());
                e.printStackTrace();
                numeroValido = true;  // Esce dal ciclo in caso di errore critico
            }
        } while(!numeroValido);  // Ripete finché non riceve un numero valido
        
        
        try {   // FINALLY: Chiusura delle risorse - si esegue SEMPRE (FUORI dal ciclo)
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


