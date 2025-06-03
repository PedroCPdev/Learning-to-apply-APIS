package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=;";
    private final String API_KEY = "&apikey=becabc03";

    public void exibeMenu() throws JsonProcessingException {
        System.out.println("Digite o nome da serie para busca: ");
        var nomeSerie = scanner.nextLine();

        var json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
//        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        if (dados.totalTemporadas() != null) {
            for (int i = 1; i <= dados.totalTemporadas(); i++) {
                json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);


            temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

            List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                    .flatMap(t -> t.episodios().stream()).collect(Collectors.toList());

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(t -> t.episodios().stream()
                            .map(d -> new Episodio(t.numero(), d))
                    ).collect(Collectors.toList());

            episodios.forEach(System.out::println);

//            System.out.println("Digite o titulo do episodio: ");
//            var trechoTitulo = scanner.nextLine();
//            Optional<Episodio> episodioBuscado = episodios.stream()
//                    .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
//                    .findFirst();
//
//            if (episodioBuscado.isPresent()){
//                System.out.println("Episodio encontrado!");
//                System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
//            }

//            System.out.println("top 5");
//            episodios.stream()
//                    .filter(e -> !Boolean.parseBoolean(e.getAvaliacao().toString())).sorted(Comparator.comparing(Episodio::getAvaliacao).reversed()).limit(5).forEach(System.out::println);

//            System.out.println("A partir de que ano voce deseja ver os episodios?");
//            var ano = scanner.nextInt();
//            scanner.nextLine();
//
//            LocalDate dataBusca = LocalDate.of(ano,1, 1);
//
//            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            episodios.stream()
//                    .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                    .forEach(e -> System.out.println(
//                            "Temporada: " + e.getTemporada() +
//                                    " Episodio: " + e.getTitulo() +
//                                    " Data Lancamento: " + e.getDataLancamento().format(formatador)
//                    ));

//            Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
//                    .filter(e -> e.getAvaliacao() > 0.0)
//                    .collect(Collectors.groupingBy(Episodio::getTemporada,
//                            Collectors.averagingDouble(Episodio::getAvaliacao)));
//            System.out.println(avaliacoesPorTemporada);

            DoubleSummaryStatistics est = episodios.stream()
                    .filter(e -> e.getAvaliacao() > 0)
                    .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));

            System.out.println("media: " + est.getAverage());
            System.out.println("Melhor episodio: " + est.getMax());
            System.out.println("Pior episodio: " + est.getMin());
            System.out.println("Quantidade: " + est.getCount());
        }
    }
}