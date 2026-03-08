package org.example.projetodiogo.model;

import java.sql.Timestamp;

public class Aluno {

    private int id;
    private String matricula;
    private int idUsuario;
    private Timestamp dtMatricula;

    // Método Construtor;
    public Aluno() {}

    public Aluno(int id, int idUsuario, String matricula, Timestamp dtMatricula) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.matricula = matricula;
        this.dtMatricula = dtMatricula;
    }

    // Métodos getters;

    public int getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public Timestamp getDtMatricula() {
        return dtMatricula;
    }

    // Métodos setters;

    public void setId(int id) {
        this.id = id;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setDtMatricula(Timestamp dtMatricula) {
        this.dtMatricula = dtMatricula;
    }

    // Método toString;

    @Override
    public String toString() {
        return "Aluno{" +
                "id = " + id +
                ", matricula = " + matricula + '\'' +
                ", data matricula = " + dtMatricula + '\'' +
                ", idUsuario = " + idUsuario +
                '}';
    }
}
