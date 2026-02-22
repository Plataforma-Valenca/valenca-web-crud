package org.example.projetodiogo.model;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String username;
    private String senha;
    private String cpf;
    private String tipoUsuario;
    private boolean cadastroCompleto = false;

    // Método construtor
    public Usuario() {

    }

    public Usuario(int id, String nome, String email, String username, String senha, String cpf, String tipoUsuario, boolean cadastroCompleto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.senha = senha;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
        this.cadastroCompleto = cadastroCompleto;
    }

    public Usuario(int id, String nome, String email, String senha, String cpf, String tipoUsuario, boolean cadastroCompleto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
        this.cadastroCompleto = cadastroCompleto;
    }

    public Usuario(String nome, String email, String username, String senha, String cpf, String tipoUsuario, boolean cadastroCompleto) {
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.senha = senha;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
        this.cadastroCompleto = cadastroCompleto;
    }

    public Usuario(String nome, String email, String senha, String cpf, String tipoUsuario, boolean cadastroCompleto) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
        this.cadastroCompleto = cadastroCompleto;
    }

    public Usuario(int id, String nome, String email, String senha, boolean cadastroCompleto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cadastroCompleto = cadastroCompleto;
    }

    public Usuario(String senha, String cpf, String tipoUsuario, boolean cadastroCompleto) {
        this.senha = senha;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
        this.cadastroCompleto = cadastroCompleto;
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

    public String getUsername() {
        return username;
    }

    public boolean isCadastroCompleto() {
        return cadastroCompleto;
    }

    // Métodos setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCadastroCompleto(boolean cadastroCompleto) {
        this.cadastroCompleto = cadastroCompleto;
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