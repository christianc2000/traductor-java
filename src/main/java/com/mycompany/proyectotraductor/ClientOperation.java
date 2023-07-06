/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectotraductor;

/**
 *
 * @author Christian
 */
import java.rmi.Naming;
import java.util.Scanner;

public class ClientOperation {

    private String traduccion;

    public ClientOperation() {
        this.traduccion = "";
    }

    public String getTraduccion() {
        return this.traduccion;
    }

    public void traducir(String texto, String origen, String destino) {
        try {
            System.out.println("texto: " + texto + ", origen: " + origen + ", destino: " + destino);
            RMIInterface server = (RMIInterface) Naming.lookup("rmi://localhost/ServerOperation");
            String translatedText = server.translate(texto, origen, destino);
            this.traduccion = translatedText;
        } catch (Exception e) {
            this.traduccion = "";
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    /* public static void main(String[] args) {
        try {
            RMIInterface server = (RMIInterface) Naming.lookup("rmi://localhost/ServerOperation");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el texto a traducir:");
            String text = scanner.nextLine();

            System.out.println("Ingrese el idioma de origen:");
            String sourceLanguage = scanner.nextLine();

            System.out.println("Ingrese el idioma de destino:");
            String targetLanguage = scanner.nextLine();

            String translatedText = server.translate(text, sourceLanguage, targetLanguage);
            System.out.println("Texto traducido: " + translatedText);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }*/
}
