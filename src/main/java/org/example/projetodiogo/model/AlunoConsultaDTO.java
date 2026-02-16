package org.example.projetodiogo.model;

public class AlunoConsultaDTO {

    private String nome;
    private String matricula;
    private String cpf;
    private String turma;

    public AlunoConsultaDTO(String nome, String matricula, String cpf, String turma) {
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.turma = turma;
    }

    // getters
}

