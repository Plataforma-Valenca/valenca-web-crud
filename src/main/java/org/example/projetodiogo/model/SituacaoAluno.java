package org.example.projetodiogo.model;

public class SituacaoAluno {
    private int id;
    private String nome;

    // Construtor
    public SituacaoAluno() {}

    public SituacaoAluno(int id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    // Métodos setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return "Situação Aluno{" +
                "id = " + id +
                ", situação = '" + nome + '\'' +
                '}';
    }
}
