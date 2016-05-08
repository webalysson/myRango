
package com.alysson.myrango.util;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Base extends BaseSemDescricao {
	
	protected String descricao;

	@Column(name = "descricao", unique = true)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao != null ? descricao.trim() : descricao );
	}

	
 }
