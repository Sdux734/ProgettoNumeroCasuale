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
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        int numero = Integer.parseInt(line); 

        if(numero == n){

            out.println("Hai vinto!");
        }
        else{

            out.println("Hai perso!");
        }

        scanner.close();

    }
    
    }


