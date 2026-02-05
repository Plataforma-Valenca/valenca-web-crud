package org.example.projetodiogo.model;

public class Professor {
    private int id;
    private String nome;
    private int idUsuario;

    // Método Construtor

    public Professor(int id, String nome, String sobrenome, int idUsuario) {
        this.id = id;
        this.nome = nome;
        this.idUsuario = idUsuario;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    // Método setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Método toString

    @Override
    public String toString() {
        return "Professor{" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                ", idUsuario = '" + idUsuario + '\'' +
                '}';
    }
}