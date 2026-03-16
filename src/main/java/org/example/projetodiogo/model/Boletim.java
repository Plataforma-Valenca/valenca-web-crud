package org.example.projetodiogo.model;

public class Boletim {

    private int idBoletim;
    private int idNota;
    private int idDisciplina;
    private String nomeDisciplina;
    private Double media1;
    private Double media2;
    private Double mediaFinal;
    private String situacao;

    public Boletim() {}

    public Boletim(String nomeDisciplina, Double media1, Double media2, Double mediaFinal, String situacao) {
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

    public Boletim(int idNota, int idDisciplina, String nomeDisciplina, Double media1, Double media2, Double mediaFinal) {
        this.idNota = idNota;
        this.idDisciplina = idDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        this.media1 = media1;
        this.media2 = media2;
        this.mediaFinal = mediaFinal;
    }

    public Boletim(int idBoletim, int idAluno, int idDisciplina, double nota) {}

    // Getters
    public int getIdBoletim()        { return idBoletim; }
    public int getIdNota()           { return idNota; }
    public int getIdDisciplina()     { return idDisciplina; }
    public String getNomeDisciplina(){ return nomeDisciplina; }
    public Double getMedia1()        { return media1; }
    public Double getMedia2()        { return media2; }
    public Double getMediaFinal()    { return mediaFinal; }
    public String getSituacao()      { return situacao; }

    // Setters
    public void setIdBoletim(int idBoletim)            { this.idBoletim = idBoletim; }
    public void setIdNota(int idNota)                  { this.idNota = idNota; }
    public void setIdDisciplina(int idDisciplina)      { this.idDisciplina = idDisciplina; }
    public void setNomeDisciplina(String nomeDisciplina){ this.nomeDisciplina = nomeDisciplina; }
    public void setMedia1(Double media1)               { this.media1 = media1; }
    public void setMedia2(Double media2)               { this.media2 = media2; }
    public void setMediaFinal(Double mediaFinal)       { this.mediaFinal = mediaFinal; }
    public void setSituacao(String situacao)           { this.situacao = situacao; }
}