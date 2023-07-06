/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apichatgpt;
//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

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class conectionapi {

    private static final String API_KEY = "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";

    public static void main(String[] args) {
        OpenAiService service = new OpenAiService(API_KEY);

        //CompletionRequest request = CompletionRequest.builder().model("text-davinci-003").prompt("Escriva un eslogan para una barraca de acero").maxTokens(100).build();
        //System.out.println(service.createCompletion(request).getChoices());
        String sourceLanguage = "en"; // Lenguaje de origen
        String targetLanguage = "es"; // Lenguaje de destino
        String textToTranslate = "What do you do?"; // Texto a traducir
        System.out.println("antes de enviar a traducir");
        String prompt = String.format("Translate the following text from %s to %s: \"%s\"", sourceLanguage, targetLanguage, textToTranslate);
        System.out.println("luego de enviar a traducir");
        CompletionRequest request = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(prompt)
                .maxTokens(100)
                .build();

        String translatedText = service.createCompletion(request).getChoices().get(0).getText();

        System.out.println("Texto traducido: " + translatedText);
    }

}
