package br.com.alura.screenmatch.modelos;

public class Veiculo {
    private String volante;
    private String rodas;
    private String janelas;
    private String acelerador;
    private String freio;

    public Veiculo() {
    }

    public Veiculo(String volante, String rodas, String janelas, String acelerador, String freio) {
        this.volante = volante;
        this.rodas = rodas;
        this.janelas = janelas;
        this.acelerador = acelerador;
        this.freio = freio;
    }

    public String getVolante() {
        return volante;
    }

    public void setVolante(String volante) {
        this.volante = volante;
    }

    public String getRodas() {
        return rodas;
    }

    public void setRodas(String rodas) {
        this.rodas = rodas;
    }

    public String getJanelas() {
        return janelas;
    }

    public void setJanelas(String janelas) {
        this.janelas = janelas;
    }

    public String getAcelerador() {
        return acelerador;
    }

    public void setAcelerador(String acelerador) {
        this.acelerador = acelerador;
    }

    public String getFreio() {
        return freio;
    }

    public void setFreio(String freio) {
        this.freio = freio;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "volante='" + volante + '\'' +
                ", rodas='" + rodas + '\'' +
                ", janelas='" + janelas + '\'' +
                ", acelerador='" + acelerador + '\'' +
                ", freio='" + freio + '\'' +
                '}';
    }
}
