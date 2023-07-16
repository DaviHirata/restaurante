package model;

public class Feedback {
    private int id;
    private String nomeCliente;
    private String dataFeedback;
    private int classificacao;

    public Feedback() {
    }

    public Feedback(int id, String nomeCliente, String dataFeedback, int classificacao) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.dataFeedback = dataFeedback;
        this.classificacao = classificacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDataFeedback() {
        return dataFeedback;
    }

    public void setDataFeedback(String dataFeedback) {
        this.dataFeedback = dataFeedback;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }
}