package org.example.projetodiogo.model;

public class Nota {
    private int id;
    private int idAluno;
    private int idDisciplina;

    // Método Construtor

    public Nota(int id, int idAluno, int idDisciplina) {
        this.id = id;
        this.idAluno = idAluno;
        this.idDisciplina = idDisciplina;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    // Métodos setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    // Método toString

    @Override
    public String toString() {
        return "Nota{" +
                "id = " + id +
                ", idAluno = " + idAluno +
                ", idDisciplina = " + idDisciplina +
                '}';
    }
}