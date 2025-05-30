package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.excessao.ErroDeConversaoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();

        Scanner scanner = new Scanner(System.in);
        String busca = "";

        List<Titulo> filmes = new ArrayList<>();


        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Insira o filme que deseja buscar: ");
            busca = scanner.nextLine();

            if (busca.equalsIgnoreCase("sair")) {
                break;
            }

            String chave = "becabc03";
            String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=" + chave;

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();

                TituloOmdb meuTituloOmbdb = gson.fromJson(json, TituloOmdb.class);
                Titulo meuTitulo = new Titulo(meuTituloOmbdb);
                System.out.println(meuTitulo);

                filmes.add(meuTitulo);
            } catch (NumberFormatException e) {
                System.out.println("ERRO");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro na busca, verifique o endereco");
            } catch (ErroDeConversaoException e) {
                System.out.println(e);
            }
        }


            FileWriter escrita = new FileWriter("filmes.json");
            escrita.write(gson.toJson(filmes));
            escrita.close();

            System.out.println("FOI!");
    }
}
