package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Veiculo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) throws IOException {
        List<Veiculo> veiculos = new ArrayList<>();
        Veiculo veiculo = new Veiculo();
        FileWriter arquivoVeiculo = new FileWriter("Veiculo.json");
        Scanner entrada = new Scanner(System.in);
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();


        int i = 0;
        while (i <= 2){
            System.out.println("Insira o volante:");
            veiculo.setVolante(entrada.nextLine());
            System.out.println("Insira o acelerador:");
            veiculo.setAcelerador(entrada.nextLine());
            System.out.println("Insira o freio:");
            veiculo.setFreio(entrada.nextLine());
            System.out.println("Insira a janela:");
            veiculo.setJanelas(entrada.nextLine());
            System.out.println("Insira a roda:");
            veiculo.setRodas(entrada.nextLine());
            veiculos.add(veiculo);
            i++;
        }
        System.out.println(veiculos);
        arquivoVeiculo.write(gson.toJson(veiculos));
        arquivoVeiculo.close();

    }
}
