package br.com.alura.viaCep.classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GravaArquivoJSON {

    public void gravaArquivo(Endereco endereco){
        FileWriter escritor = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            escritor = new FileWriter("Endereco.json");
            escritor.write(gson.toJson(endereco));
            escritor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
