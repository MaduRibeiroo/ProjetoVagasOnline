package com.example.vagasonline;


import java.io.Serializable;

class Candidato implements Serializable {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String formacao;

    public Candidato() {
    }

    public Candidato(String nome, String cpf, String email, String telefone, String formacao) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.formacao = formacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
}



public class Interesse implements Serializable {

    private String _id;
    private Vagas vaga;
    private Candidato candidato;

    public Interesse() {
    }

    public Interesse(String _id, Vagas vaga, Candidato candidato) {
        this._id = _id;
        this.vaga = vaga;
        this.candidato = candidato;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public Vagas getVaga() {
        return vaga;
    }

    public void setVaga(Vagas vaga) {
        this.vaga = vaga;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
}