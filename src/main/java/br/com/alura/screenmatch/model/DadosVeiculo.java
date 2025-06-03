package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(Integer TipoVeiculo, String Valor, String Marca, String Modelo, Integer AnoModelo,
                           String Combustivel, String MesReferencia) {
}
