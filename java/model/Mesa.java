package model;

public class Mesa {
    private int id;
    private String localizacao;

    public Mesa() {
    }

    public Mesa(int id, String localizacao) {
        this.id = id;
        this.localizacao = localizacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}