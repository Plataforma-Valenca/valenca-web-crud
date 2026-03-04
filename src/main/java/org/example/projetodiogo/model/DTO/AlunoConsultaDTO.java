package org.example.projetodiogo.model.DTO;

public class AlunoConsultaDTO {
    private int idAluno;
    private int idUsuario;
    private String nome;
    private String matricula;
    private String cpf;
    private String turma;

    // Método construtor
    public AlunoConsultaDTO() {}

    public AlunoConsultaDTO(int idAluno, int idUsuario, String nome, String matricula, String cpf, String turma) {
        this.idAluno = idAluno;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.turma = turma;
    }

    public AlunoConsultaDTO(int idAluno, String nome, String matricula, String cpf, String turma) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.turma = turma;
    }

    public AlunoConsultaDTO(String nome, String matricula, String cpf, String turma) {
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.turma = turma;
    }

    // Métodos getters
    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTurma() {
        return turma;
    }

    public int getIdAluno() {return idAluno;}

    // Métodos setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    // Método toString

    @Override
    public String toString() {
        return "AlunoConsultaDTO{" +
                "nome = '" + nome + '\'' +
                ", matricula = '" + matricula + '\'' +
                ", cpf = '" + cpf + '\'' +
                ", turma = '" + turma + '\'' +
                '}';
    }


}