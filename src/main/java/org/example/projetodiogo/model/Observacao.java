package org.example.projetodiogo.model;

import java.sql.Timestamp;

public class Observacao {

    private int id;
    private String descricao;
    private Timestamp dataEnvio;
    private int idAluno;
    private int idProfessor;

    public Observacao() {
    }

    public Observacao(int id, String descricao, Timestamp dataEnvio, int idAluno, int idProfessor) {
        this.id = id;
        this.descricao = descricao;
        this.dataEnvio = dataEnvio;
        this.idAluno = idAluno;
        this.idProfessor = idProfessor;
    }

    public Observacao(String descricao, int idAluno, int idProfessor) {
        this.descricao = descricao;
        this.idAluno = idAluno;
        this.idProfessor = idProfessor;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Timestamp getDataEnvio() {
        return dataEnvio;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataEnvio(Timestamp dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    @Override
    public String toString() {
        return "Observacao{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", dataEnvio=" + dataEnvio +
                ", idAluno=" + idAluno +
                ", idProfessor=" + idProfessor +
                '}';
    }
}