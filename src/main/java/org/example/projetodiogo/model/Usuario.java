package org.example.projetodiogo.model;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String tipoUsuario;
    private String cpf;

    // Método construtor
    public Usuario() {

    }

    public Usuario(int id, String nome, String email, String senha, String tipoUsuario, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.cpf = cpf;
    }

    // Métodos getters

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public String getCpf() {
        return cpf;
    }

    // Métodos setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Método toString


    @Override
    public String toString() {
        return "Usuario{" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                ", email = '" + email + '\'' +
                ", senha = '" + senha + '\'' +
                ", tipoUsuario = '" + tipoUsuario + '\'' +
                ", cpf = '" + cpf + '\'' +
                '}';
    }
}