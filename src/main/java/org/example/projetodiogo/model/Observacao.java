package org.example.projetodiogo.model;

import java.util.Date;

public class Observacao {
    private int id;
    private String descricao;
    private Date dataEnvio;
    private int idAluno;
    private int idProfessor;

    // Método Construtor
    public Observacao(int id, String descricao, Date dataEnvio, int idAluno, int idProfessor) {
        this.id = id;
        this.descricao = descricao;
        this.dataEnvio = dataEnvio;
        this.idAluno = idAluno;
        this.idProfessor = idProfessor;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    // Métodos setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    // Método toString

    @Override
    public String toString() {
        return "Observacao{" +
                "id = " + id +
                ", descricao = '" + descricao + '\'' +
                ", dataEnvio = " + dataEnvio +
                ", idAluno = " + idAluno +
                ", idProfessor = " + idProfessor +
                '}';
    }
}
