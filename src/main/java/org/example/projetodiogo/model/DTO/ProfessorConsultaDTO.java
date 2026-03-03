package org.example.projetodiogo.model.DTO;

public class ProfessorConsultaDTO {
    private int idUsuario;
    private String nome;
    private String email;
    private String cpf;
    private String disciplina;

    // construtores
    public ProfessorConsultaDTO() {}

    public ProfessorConsultaDTO(int idUsuario, String nome, String email, String cpf, String disciplina) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.disciplina = disciplina;
    }

    // getters
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDisciplina() {
        return disciplina;
    }


}
