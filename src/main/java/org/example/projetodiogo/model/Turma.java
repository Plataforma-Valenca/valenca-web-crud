package org.example.projetodiogo.model;

public class Turma {
    private int id;
    private String nome;
    private int ano;

    // Método Construtor
    public Turma() {}

    public Turma(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Turma(String nome) {
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

    // Método toString

    @Override
    public String toString() {
        return "Turma{" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                '}';
    }
}
