package fipp.ariane.vagasonlinebe.entities;

public class Cargo {

    private String nome;

    public Cargo() {
        this("");
    }

    public Cargo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
