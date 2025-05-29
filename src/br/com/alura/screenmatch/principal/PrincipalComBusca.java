package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.excessao.ErroDeConversaoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        try {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Insira o filme que deseja buscar: ");
            var filme = scanner.nextLine();

            String chave = "becabc03";
            String endereco = "https://www.omdbapi.com/?t=" + filme.replace(" ", "+") + "&apikey=" + chave;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            System.out.println(json);

            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            TituloOmdb meuTituloOmbdb = gson.fromJson(json, TituloOmdb.class);
            Titulo meuTitulo = new Titulo(meuTituloOmbdb);
            System.out.println(meuTitulo);

        }catch (NumberFormatException e){
            System.out.println("ERRO");
            System.out.println(e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println("Erro na busca, verifique o endereco");
        }catch (ErroDeConversaoException e){
            System.out.println(e);
        }


    }
}
