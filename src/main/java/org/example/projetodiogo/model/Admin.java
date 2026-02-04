package org.example.projetodiogo.model;

public class Admin {
    private int id;
    private String nome;
    private int idPermissao;

    //Método Construtor
    public Admin(int id, String nome, int idPermissao) {
        this.id = id;
        this.nome = nome;
        this.idPermissao = idPermissao;
    }

    //Métodos getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdPermissao() {
        return idPermissao;
    }

    //Métodos setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdPermissao(int idPermissao) {
        this.idPermissao = idPermissao;
    }

    //Método toString

    @Override
    public String toString() {
        return "Admin{" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                ", idPermissao = " + idPermissao +
                '}';
    }
}
