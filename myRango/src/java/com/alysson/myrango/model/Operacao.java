package com.alysson.myrango.model;

import com.alysson.myrango.util.BaseSemDescricao;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Alysson
 */
@Entity
@Table(name = "operacao")
public class Operacao extends BaseSemDescricao implements Serializable{
    
    private Usuario usuario;
    
    private Refeicao refeicao;
    
    private String tipo;
    
    private int quantidade;
    
    private float valor;
    
    private float saldo;
    

    
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idUsuario",nullable=false)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    @OneToOne(mappedBy = "operacao")
    public Refeicao getRefeicao() {
        return refeicao;
    }

    public void setRefeicao(Refeicao refeicao) {
        this.refeicao = refeicao;
    }
    
    
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    

    

    @Transient
    @Override
    public String getNomeStatus(){
            if (this.status != null && this.status == 1)
                    return "NÃO";
            else if (this.status != null && this.status == 2)
                    return "SIM";
            return "Indefinido";
    }     
    
    


    
    
    
}
