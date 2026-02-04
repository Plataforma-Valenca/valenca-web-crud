package org.example.projetodiogo.model;

public class Aluno {

    private int id;
    private String nome;
    private String sobrenome;
    private int matricula;
    private int idPermissao;

    // Método Construtor;
    public Aluno(int id, String nome, String sobrenome, int matricula, int idPermissao) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.matricula = matricula;
        this.idPermissao = idPermissao;
    }

    // Métodos getters;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public int getMatricula() {
        return matricula;
    }

    public int getIdPermissao() {
        return idPermissao;
    }

    // Métodos setters;

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setIdPermissao(int idPermissao) {
        this.idPermissao = idPermissao;
    }

    // Método toString;

    @Override
    public String toString() {
        return "Aluno{" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                ", sobrenome = '" + sobrenome + '\'' +
                ", matricula = " + matricula + '\'' +
                ", idPermissao = " + idPermissao +
                '}';
    }
}
