package org.example.projetodiogo.model;

public class Nota {
    private int id;
    private int idAluno;
    private int idDisciplina;
    private Double media;
    private int idSituacao;

    // Método Construtor
    public Nota() {}

    public Nota(int idAluno, int idDisciplina, Double media, int idSituacao) {
        this.idAluno = idAluno;
        this.idDisciplina = idDisciplina;
        this.media = media;
        this.idSituacao = idSituacao;
    }

    public Nota(Double media, int idSituacao) {
        this.media = media;
        this.idSituacao = idSituacao;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public Double getMedia() { return media; }

    public int getIdSituacao() { return idSituacao; }

    // Métodos setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public void setMedia(Double media) { this.media = media; }

    public void setIdSituacao(int idSituacao) { this.idSituacao = idSituacao; }

    // Método toString

    @Override
    public String toString() {
        return "Nota{" +
                "id = " + id +
                ", idAluno = " + idAluno +
                ", media = " + media +
                ", id situação = " + idSituacao +
                ", idDisciplina = " + idDisciplina +
                '}';
    }
}