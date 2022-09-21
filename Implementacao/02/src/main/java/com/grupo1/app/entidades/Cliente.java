package com.grupo1.app.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

// @Entity
public class Cliente extends Usuario{
    
    // @Id
    private String cpf;
    private String rg;
    private String profissao;
    List<Empregador> empregadores = new ArrayList<Empregador>(3);

    public Cliente() {
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public String getProfissao() {
        return profissao;
    }
    public void setProfissao(String profissao){
        this.profissao = profissao;
    }
    public List<Empregador> getEmpregadores() {
        return empregadores;
    }
    public void setEmpregadores(List<Empregador> empregadores) {
        this.empregadores = empregadores;
    }
}
