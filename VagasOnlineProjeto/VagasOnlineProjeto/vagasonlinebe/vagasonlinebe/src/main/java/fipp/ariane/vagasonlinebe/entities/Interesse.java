package fipp.ariane.vagasonlinebe.entities;

public class Interesse {
    private Vaga vaga;
    private Candidato candidato;

    public Interesse(){
        this(null,null);
    }

    public Interesse(Vaga vaga, Candidato candidato) {
        this.vaga = vaga;
        this.candidato = candidato;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
}
