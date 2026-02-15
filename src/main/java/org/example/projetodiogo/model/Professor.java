package org.example.projetodiogo.model;

public class Professor {
    private int id;
    private int idUsuario;

    // Método Construtor
    public Professor() {}

    public Professor(int id, int idUsuario) {
        this.id = id;
        this.idUsuario = idUsuario;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    // Método setters

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Método toString

    @Override
    public String toString() {
        return "Professor{" +
                "id = " + id +
                ", idUsuario = '" + idUsuario + '\'' +
                '}';
    }
}