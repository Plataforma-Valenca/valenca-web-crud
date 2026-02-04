package org.example.projetodiogo.model;

public class Nota {
    private int id;
    private Double nota1;
    private Double nota2;
    private Double media;
    private String Descricao;

    // Método Construtor
    public Nota(int id, Double nota1, Double nota2, Double media, String descricao) {
        this.id = id;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.media = media;
        Descricao = descricao;
    }

    // Método getters
    public int getId() {
        return id;
    }

    public Double getNota1() {
        return nota1;
    }

    public Double getNota2() {
        return nota2;
    }

    public Double getMedia() {
        return media;
    }

    public String getDescricao() {
        return Descricao;
    }

    // Métodos setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNota1(Double nota1) {
        this.nota1 = nota1;
    }

    public void setNota2(Double nota2) {
        this.nota2 = nota2;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    // Métodos toString


    @Override
    public String toString() {
        return "Nota{" +
                "id = " + id +
                ", nota1 = " + nota1 +
                ", nota2 = " + nota2 +
                ", media = " + media +
                ", Descricao = '" + Descricao + '\'' +
                '}';
    }
}