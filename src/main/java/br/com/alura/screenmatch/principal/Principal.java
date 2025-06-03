package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.Dados;
import br.com.alura.screenmatch.model.DadosVeiculo;
import br.com.alura.screenmatch.model.Modelos;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() throws JsonProcessingException {
        System.out.println("Qual veiculo deseja buscar?" +
                "\n1-Carros" +
                "\n2-Motos" +
                "\n3-Caminhoes");

        var enderecoBusca = "";

        switch (scanner.nextInt()){
            case 1:
                enderecoBusca = ENDERECO + "carros/marcas";
                break;
            case 2:
                enderecoBusca = ENDERECO + "motos/marcas";
                break;
            case 3:
                enderecoBusca = ENDERECO + "caminhoes/marcas";
            default:
                System.out.println("Tipo invalido");
                break;
        }

        var resposta = consumoAPI.obterDados(enderecoBusca);
        var dadosMarcas = converteDados.obterLista(resposta, Dados.class);

        dadosMarcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Qual das marcas acima deseja procurar o veiculo?");
        var marcaBusca = scanner.next();

        enderecoBusca = enderecoBusca + "/" + marcaBusca + "/modelos";
        resposta = consumoAPI.obterDados(enderecoBusca);
        var modeloLista = converteDados.obterDados(resposta, Modelos.class);

        System.out.println("Modelos desta marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Qual dos modelos acima deseja procurar?");
        var modeloBusca = scanner.next();


        enderecoBusca = enderecoBusca + "/" + modeloBusca + "/anos";
        resposta = consumoAPI.obterDados(enderecoBusca);
        var listaAno = converteDados.obterLista(resposta, Dados.class);

        System.out.println("Qual ano desse modelo deseja procurar: ");

        listaAno.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        var anoModelo = scanner.next();

        enderecoBusca = enderecoBusca + "/" + anoModelo;
        resposta = consumoAPI.obterDados(enderecoBusca);
        var info = converteDados.obterDados(resposta, DadosVeiculo.class);

        System.out.println(info);
    }
}