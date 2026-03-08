package org.example.projetodiogo.model.DTO;

public class DisciplinasResumoDTO {
    private int idDisciplina;
    private String nomeDisciplina;
    private String nomeProfessor;
    private int quantidadeTurmas;
    private Double mediaGeral;
    private int idProfessor;

    // construtor
    public DisciplinasResumoDTO() {}

    public DisciplinasResumoDTO(int idDisciplina, String nomeDisciplina, String nomeProfessor, int quantidadeTurmas, Double mediaGeral, int idProfessor) {
        this.idDisciplina = idDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        this.nomeProfessor = nomeProfessor;
        this.quantidadeTurmas = quantidadeTurmas;
        this.mediaGeral = mediaGeral;
        this.idProfessor=idProfessor;
    }

    // getters
    public int getIdDisciplina() {
        return idDisciplina;
    }
    public int getIdProfessor() {
        return idProfessor;
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

    public String getNomeFormatado() {
        if (nomeDisciplina == null || nomeDisciplina.isEmpty()) return nomeDisciplina;
        return nomeDisciplina.substring(0,1).toUpperCase() + nomeDisciplina.substring(1).toLowerCase();
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