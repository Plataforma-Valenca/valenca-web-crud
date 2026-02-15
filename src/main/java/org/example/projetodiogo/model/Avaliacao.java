package org.example.projetodiogo.model;

public class Avaliacao {
    private int id;
    private String descricao;
    private Double valor;
    private int idNota;

    // Método Construtor
    public Avaliacao() {}

    public Avaliacao(int id, String descricao, Double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }

    public int getIdNota() {
        return idNota;
    }

    // Métodos getters
    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    // toString
    @Override
    public String toString() {
        return "Avaliacao{" +
                "id = " + id +
                ", descricao = '" + descricao + '\'' +
                ", valor = " + valor +
                ", idNota = " + idNota +
                '}';
    }
}