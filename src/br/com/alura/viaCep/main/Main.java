package br.com.alura.viaCep.main;

import br.com.alura.viaCep.classes.ConsultaCEP;
import br.com.alura.viaCep.classes.Endereco;
import br.com.alura.viaCep.classes.GravaArquivoJSON;
import com.google.gson.Gson;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpClient;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ConsultaCEP consultaCEP = new ConsultaCEP();
        GravaArquivoJSON gravador = new GravaArquivoJSON();

        Endereco endereco = consultaCEP.buscaEndereco(JOptionPane.showInputDialog("Insira o CEP"));

        gravador.gravaArquivo(endereco);

    }
}
