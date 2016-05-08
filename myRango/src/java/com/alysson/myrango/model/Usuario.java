
package com.alysson.myrango.model;

import com.alysson.myrango.util.Base;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;




/**
 *
 * @author Alysson
 */
@Entity
@Table(name = "usuario")
public class Usuario extends Base implements Serializable{
    
    private Modulo modulo;
    
    private Funcao funcao;
    
    private List<Refeicao> refeicoes;
    
    private List<Operacao> operacoes;
    
    private float saldo;
    
    private float debito;
    
    private String matricula;
    
    private String senha;
    
    @Transient
    private float debitoDoDia;
    
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "usuario")	
    public List<Operacao> getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(List<Operacao> operacoes) {
        this.operacoes = operacoes;
    }
    
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
    public List<Refeicao> getRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(List<Refeicao> refeicoes) {
        this.refeicoes = refeicoes;
    }
    
    
    
    
    
    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="idModulo",nullable=false)
    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="idFuncao",nullable=false)    
    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }
    
    
    

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getDebito() {
        return debito;
    }

    public void setDebito(float debito) {
        this.debito = debito;
    }

    @Transient
    public float getDebitoDoDia() {
        return debitoDoDia;
    }

    public void setDebitoDoDia(float debitoDoDia) {
        this.debitoDoDia = debitoDoDia;
    }

    
    
    
}
