package com.example.vagasonline;

import java.io.Serializable;
import java.util.List;

class Empresa implements Serializable{
    public String nome_fantasia;
    public String razao_social;
    public String tipo;

    public Empresa() {
    }

    public Empresa(String nome_fantasia, String razao_social, String tipo) {
        this.nome_fantasia = nome_fantasia;
        this.razao_social = razao_social;
        this.tipo = tipo;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}


 public  class   Vagas implements Serializable {
    public String _id;
    public String registro;
    public Empresa empresa;
    public String cargo;
    public String cidade;
    public String estado;
    public String pre_requisitos;
    public String formacao;
    public String conhecimentos_requeridos;
    public String regime;
    public String jornada_trabalho;
    public String remuneracao;

     public Vagas() {
     }

     public Vagas(String _id, String registro, Empresa empresa, String cargo, String cidade, String estado, String pre_requisitos, String formacao, String conhecimentos_requeridos, String regime, String jornada_trabalho, String remuneracao) {
         this._id = _id;
         this.registro = registro;
         this.empresa = empresa;
         this.cargo = cargo;
         this.cidade = cidade;
         this.estado = estado;
         this.pre_requisitos = pre_requisitos;
         this.formacao = formacao;
         this.conhecimentos_requeridos = conhecimentos_requeridos;
         this.regime = regime;
         this.jornada_trabalho = jornada_trabalho;
         this.remuneracao = remuneracao;
     }

     public String get_id() {
         return _id;
     }

     public void set_id(String _id) {
         this._id = _id;
     }

     public String getRegistro() {
         return registro;
     }

     public void setRegistro(String registro) {
         this.registro = registro;
     }

     public Empresa getEmpresa() {
         return empresa;
     }

     public void setEmpresa(Empresa empresa) {
         this.empresa = empresa;
     }

     public String getCargo() {
         return cargo;
     }

     public void setCargo(String cargo) {
         this.cargo = cargo;
     }

     public String getCidade() {
         return cidade;
     }

     public void setCidade(String cidade) {
         this.cidade = cidade;
     }

     public String getEstado() {
         return estado;
     }

     public void setEstado(String estado) {
         this.estado = estado;
     }

     public String getPre_requisitos() {
         return pre_requisitos;
     }

     public void setPre_requisitos(String pre_requisitos) {
         this.pre_requisitos = pre_requisitos;
     }

     public String getFormacao() {
         return formacao;
     }

     public void setFormacao(String formacao) {
         this.formacao = formacao;
     }

     public String getConhecimentos_requeridos() {
         return conhecimentos_requeridos;
     }

     public void setConhecimentos_requeridos(String conhecimentos_requeridos) {
         this.conhecimentos_requeridos = conhecimentos_requeridos;
     }

     public String getRegime() {
         return regime;
     }

     public void setRegime(String regime) {
         this.regime = regime;
     }

     public String getJornada_trabalho() {
         return jornada_trabalho;
     }

     public void setJornada_trabalho(String jornada_trabalho) {
         this.jornada_trabalho = jornada_trabalho;
     }

     public String getRemuneracao() {
         return remuneracao;
     }

     public void setRemuneracao(String remuneracao) {
         this.remuneracao = remuneracao;
     }

     @Override
     public String toString() {
         return "Vaga{" +
                 "_id='" + _id + '\'' +
                 ", registro='" + registro + '\'' +
                 ", empresa=" + empresa +
                 ", cargo='" + cargo + '\'' +
                 ", cidade='" + cidade + '\'' +
                 ", estado='" + estado + '\'' +
                 ", pre_requisitos='" + pre_requisitos + '\'' +
                 ", formacao='" + formacao + '\'' +
                 ", conhecimentos_requeridos='" + conhecimentos_requeridos + '\'' +
                 ", regime='" + regime + '\'' +
                 ", jornada_trabalho='" + jornada_trabalho + '\'' +
                 ", remuneracao='" + remuneracao + '\'' +
                 '}';
     }
 }

