package org.example.projetodiogo.model;

public class Avaliacao {
    private int id;
    private String descricao;
    private Double valor;
    private int idNota;
    private  int semestre;

    // Método Construtor
    public Avaliacao() {}

    public Avaliacao(String descricao, Double valor, int semestre) {
        this.descricao = descricao;
        this.valor = valor;
        this.semestre = semestre;
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

    public int getSemestre() { return semestre; }

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

    public void setSemestre(int semestre) { this.semestre = semestre; }

    // toString
    @Override
    public String toString() {
        return "Avaliacao{" +
                "id = " + id +
                ", descricao = '" + descricao + '\'' +
                ", valor = " + valor +
                ", semestre = " + semestre +
                ", idNota = " + idNota +
                '}';
    }
}