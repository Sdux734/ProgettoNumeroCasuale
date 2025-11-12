package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);   

        ServerSocket ss = new ServerSocket(2000);
        Socket s = ss.accept();
        System.out.println("Client connesso!");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream())); 
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        out.println("Scegli un numero tra 0-100!");

        Random rand = new Random();
        int n = rand.nextInt(101); // da 0 a 100
        System.out.println(n);

        String line = in.readLine();
        int numero = Integer.parseInt(line); 

        if(numero == n){

            out.println("Hai vinto!");
        }
        else{

            out.println("Hai perso!");
        }

        scanner.close();
        ss.close();

    }
}