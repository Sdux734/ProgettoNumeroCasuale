package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class MioThread extends Thread{

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    public MioThread(Socket s) throws IOException {
        socket = s;
        
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        
        Scanner scanner = new Scanner(System.in);   

        System.out.println("Client connesso!");

        out.println("Scegli un numero tra 0-100!");

        Random rand = new Random();
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


