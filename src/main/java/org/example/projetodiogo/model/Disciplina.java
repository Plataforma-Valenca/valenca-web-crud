package org.example.projetodiogo.model;

public class Disciplina {
    private int id;
    private String nome;

    // Método Construtor
    public Disciplina(int id, String nome) {
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

    // Método toString

    @Override
    public String toString() {
        return "Disciplina{" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                '}';
    }
}
