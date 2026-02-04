package org.example.projetodiogo.model;

public class Professor {
    private int id;
    private String nome;
    private String sobrenome;
    private int idPermissao;

    // Método Construtor

    public Professor(int id, String nome, String sobrenome, int idPermissao) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public int getIdPermissao() {
        return idPermissao;
    }

    // Método setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setIdPermissao(int idPermissao) {
        this.idPermissao = idPermissao;
    }

    // Método toString

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                '}';
    }
}