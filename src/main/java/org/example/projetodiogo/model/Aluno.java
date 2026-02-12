package org.example.projetodiogo.model;

public class Aluno {

    private int id;
    private int matricula;
    private int idUsuario;

    // Método Construtor;
    public Aluno(int id, int matricula, int idUsuario) {
        this.id = id;
        this.matricula = matricula;
        this.idUsuario = idUsuario;
    }

    // Métodos getters;

    public int getId() {
        return id;
    }

    public int getMatricula() {
        return matricula;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    // Métodos setters;

    public void setId(int id) {
        this.id = id;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Método toString;

    @Override
    public String toString() {
        return "Aluno{" +
                "id = " + id +
                ", matricula = " + matricula + '\'' +
                ", idUsuario = " + idUsuario +
                '}';
    }
}
