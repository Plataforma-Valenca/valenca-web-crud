package org.example.projetodiogo.model;

public class Usuario {
    private int id;
    private String login;
    private String senha;
    private String tipoUsuario;

    // Método construtor

    public Usuario(int id, String login, String senha, String tipoUsuario) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    // Métodos getters

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    // Métodos setters

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    // Método toString

    @Override
    public String toString() {
        return "Usuario{" +
                "id = " + id +
                ", login = '" + login + '\'' +
                ", senha = '" + senha + '\'' +
                ", tipoUsuario = '" + tipoUsuario + '\'' +
                '}';
    }
}