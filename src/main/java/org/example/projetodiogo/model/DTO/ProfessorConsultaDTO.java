package org.example.projetodiogo.model.DTO;

public class ProfessorConsultaDTO {
    private int idUsuario;
    private int idProfessor;
    private String nome;
    private String email;
    private String cpf;
    private String username;
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

    public ProfessorConsultaDTO(int idUsuario, int idProfessor, String nome,
                                String email, String cpf, String username, String disciplina) {
        this.idUsuario = idUsuario;
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.username = username;
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

    public String getUsername(){return username;}

    public int getIdProfessor(){return  idProfessor;}


}
