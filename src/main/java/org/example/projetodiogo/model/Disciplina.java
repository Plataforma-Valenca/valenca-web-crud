package org.example.projetodiogo.model;

public class Disciplina {
    private int id;
    private String nome;
    private int idProfessor;

    // Método Construtor
    public Disciplina() {}

    public Disciplina(int id, String nome, int idProfessor) {
        this.id = id;
        this.nome = nome;
        this.idProfessor = idProfessor;
    }

    // Métodos getters

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public String getNomeFormatado() {
        if (nome == null || nome.isEmpty()) return nome;
        return nome.substring(0,1).toUpperCase() + nome.substring(1).toLowerCase();
    }

    // Métodos setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    // Método toString

    @Override
    public String toString() {
        return "Disciplina{" +
                "id = " + id +
                ", nome = '" + nome +
                ", id professor = '" + idProfessor +
                '\'' +
                '}';
    }
}
