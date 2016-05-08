/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alysson.myrango.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Alysson
 */
@MappedSuperclass
public class BaseSemDescricao {
    
    protected Long id;
    
    protected Calendar dataCad;
    
    protected Calendar dataMod;
    
    @Transient
    protected Date dataInicioPesquisa;
    
    @Transient
    protected Date dataFimPesquisa;    
    
    
    
    protected Integer status;

    public BaseSemDescricao() {
        this.status=1;
    }
    
    
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
            return this.id;
    }

    public void setId(Long id) {
            this.id = id;
    } 
    
    @Column(name = "status")
    public Integer getStatus() {
            return this.status;
    }

    public void setStatus(Integer status) {
            this.status = status;
    }  
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataCad", length = 15)
    public Calendar getDataCad() {
        return dataCad;
    }

    public void setDataCad(Calendar dataCad) {
        this.dataCad = dataCad;
    }   

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataMod", length = 15)    
    public Calendar getDataMod() {
        return dataMod;
    }

    public void setDataMod(Calendar dataMod) {
        this.dataMod = dataMod;
    }

    @Transient
    public Date getDataInicioPesquisa() {
        return dataInicioPesquisa;
    }
 
    @Transient
    public void setDataInicioPesquisa(Date dataInicioPesquisa) {
        this.dataInicioPesquisa = dataInicioPesquisa;
    }

    @Transient
    public Date getDataFimPesquisa() {
        return dataFimPesquisa;
    }
 
    @Transient
    public void setDataFimPesquisa(Date dataFimPesquisa) {
        this.dataFimPesquisa = dataFimPesquisa;
    }
    
    
    
    
    
    @Transient
    public String getNomeStatus(){
            if (this.status != null && this.status == 1)
                    return "Ativo";
            else if (this.status != null && this.status == 2)
                    return "Inativo";
            return "Indefinido";
    }   
    
    
    @Transient
    public String getDataCadFormatada(){
        if(dataCad!=null){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return dateFormat.format(dataCad.getTime());
        }
        return null;
    }    
    
    
    

}
