package org.example.projetodiogo.model.DTO;

public class ProfessorConsultaDTO {

    private int idProfessor;
    private int idUsuario;
    private int idDisciplina;

    private String nome;
    private String email;
    private String cpf;
    private String disciplina;

    // construtor vazio
    public ProfessorConsultaDTO() {}

    // construtor completo
    public ProfessorConsultaDTO(int idProfessor, int idUsuario, int idDisciplina,
                                String nome, String email, String cpf, String disciplina) {

        this.idProfessor = idProfessor;
        this.idUsuario = idUsuario;
        this.idDisciplina = idDisciplina;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.disciplina = disciplina;
    }

    // getters

    public int getIdProfessor() {
        return idProfessor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDisciplina() {
        return disciplina;
    }
}