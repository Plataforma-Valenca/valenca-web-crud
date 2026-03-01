package org.example.projetodiogo.model.DTO;

public class DisciplinasResumoDTO {
    private int idDisciplina;
    private String nomeDisciplina;
    private String nomeProfessor;
    private int quantidadeTurmas;
    private Double mediaGeral;

    // construtor
    public DisciplinasResumoDTO() {}

    public DisciplinasResumoDTO(int idDisciplina, String nomeDisciplina, String nomeProfessor, int quantidadeTurmas, Double mediaGeral) {
        this.idDisciplina = idDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        this.nomeProfessor = nomeProfessor;
        this.quantidadeTurmas = quantidadeTurmas;
        this.mediaGeral = mediaGeral;
    }

    // getters
    public int getIdDisciplina() {
        return idDisciplina;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public int getQuantidadeTurmas() {
        return quantidadeTurmas;
    }

    public Double getMediaGeral() {
        return mediaGeral;
    }

    // setters
    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public void setQuantidadeTurmas(int quantidadeTurmas) {
        this.quantidadeTurmas = quantidadeTurmas;
    }

    public void setMediaGeral(Double mediaGeral) {
        this.mediaGeral = mediaGeral;
    }
}