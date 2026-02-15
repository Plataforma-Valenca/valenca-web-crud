package org.example.projetodiogo.model;

import java.sql.Timestamp;

public class AlunoTurma {
    private int id;
    private int idTurma;
    private Timestamp dtEntrada;


    // Método construtor
    public AlunoTurma() {}

    public AlunoTurma(int idTurma, Timestamp dtEntrada) {
        this.idTurma = idTurma;
        this.dtEntrada = dtEntrada;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public Timestamp getDtEntrada() {
        return dtEntrada;
    }

    // Métodos setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public void setDtEntrada(Timestamp dtEntrada) {
        this.dtEntrada = dtEntrada;
    }

    // Método toString
    public String toString() {
        return "Avaliacao{" +
                "id = " + id +
                ", id turma = '" + idTurma + '\'' +
                ", data entrada = " + dtEntrada +
                '}';
    }
}
