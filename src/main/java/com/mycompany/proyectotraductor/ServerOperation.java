/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectotraductor;

/**
 *
 * @author Christian
 */
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerOperation extends UnicastRemoteObject implements RMIInterface {

    private static final long serialVersionUID = 1L;
    private static final String API_KEY = "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";

    protected ServerOperation() throws RemoteException {
        super();
    }

    @Override
    public String translate(String text, String sourceLanguage, String targetLanguage) throws RemoteException {
        OpenAiService service = new OpenAiService(API_KEY);
        System.out.println("origen: "+sourceLanguage+", destino: "+targetLanguage);
        String prompt = String.format("Traduceme el texto de %s a %s: \"%s\"", sourceLanguage, targetLanguage, text);

        CompletionRequest request = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(prompt)
                .maxTokens(100)
                .build();

        String translatedText = service.createCompletion(request).getChoices().get(0).getText();
        return translatedText;
    }

    public static void main(String[] args) {
        try {
            ServerOperation server = new ServerOperation();
            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("ServerOperation", server);
            System.out.println("Translation Server ready");
        } catch (Exception e) {
            System.err.println("Translation Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
