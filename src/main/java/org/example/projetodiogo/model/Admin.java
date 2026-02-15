package org.example.projetodiogo.model;

public class Admin {
    private int id;
    private int idUsuario;

    //Método Construtor
    public Admin() {}

    public Admin(int id, int idUsuario) {
        this.idUsuario = idUsuario;
    }

    //Métodos getters
    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    //Métodos setters

    public void setId(int id) {
        this.id = id;
    }

    public void setidUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    //Método toString

    @Override
    public String toString() {
        return "Admin{" +
                "id = " + id +
                ", idUsuario = " + idUsuario +
                '}';
    }
}
