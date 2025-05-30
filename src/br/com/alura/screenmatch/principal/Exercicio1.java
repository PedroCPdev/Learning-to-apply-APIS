package br.com.alura.screenmatch.principal;

import java.io.FileWriter;
import java.io.IOException;

public class Exercicio1 {
    public static void main(String[] args) throws IOException {
        FileWriter arquivo = new FileWriter("arquivo.txt");

        arquivo.write("Conteúdo a ser gravado no arquivo");
        arquivo.close();
    }
}
