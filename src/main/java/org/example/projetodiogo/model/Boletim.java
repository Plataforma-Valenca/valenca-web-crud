package org.example.projetodiogo.model;

public class Boletim {

    private int idBoletim;
    private String nomeDisciplina;
    private Double media1;
    private Double media2;
    private Double mediaFinal;
    private String situacao;
    private int idDisciplina;

    // Método construtor
    public Boletim() {}

    public Boletim(int idDisciplina, String nomeDisciplina, Double media1, Double media2, Double mediaFinal, String situacao) {
        this.idDisciplina = idDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        this.media1 = media1;
        this.media2 = media2;
        this.mediaFinal = mediaFinal;
        this.situacao = situacao;
    }

    public Boletim(int idDisciplina, String nomeDisciplina, Double media1, Double media2, Double mediaFinal) {
        this.idDisciplina = idDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        this.media1 = media1;
        this.media2 = media2;
        this.mediaFinal = mediaFinal;
    }

    public Boletim(int idBoletim, int idAluno, int idDisciplina, double nota) {

    }

    // métodos getters
    public int getIdDisciplina() {
        return idDisciplina;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public Double getMedia1() {
        return media1;
    }

    public Double getMedia2() {
        return media2;
    }

    public Double getMediaFinal() {
        return mediaFinal;
    }

    // Métodos setters
    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public void setMedia1(Double media1) {
        this.media1 = media1;
    }

    public void setMedia2(Double media2) {
        this.media2 = media2;
    }

    public void setMediaFinal(Double mediaFinal) {
        this.mediaFinal = mediaFinal;
    }



}

