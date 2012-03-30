package br.com.vrum.locadora.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoVeiculo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private Double valorDiario;
	private Double valorKm;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setValorDiario(Double valorDiario) {
		this.valorDiario = valorDiario;
	}
	public Double getValorDiario() {
		return valorDiario;
	}
	public void setValorKm(Double valorKm) {
		this.valorKm = valorKm;
	}
	public Double getValorKm() {
		return valorKm;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((valorDiario == null) ? 0 : valorDiario.hashCode());
		result = prime * result + ((valorKm == null) ? 0 : valorKm.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoVeiculo other = (TipoVeiculo) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valorDiario == null) {
			if (other.valorDiario != null)
				return false;
		} else if (!valorDiario.equals(other.valorDiario))
			return false;
		if (valorKm == null) {
			if (other.valorKm != null)
				return false;
		} else if (!valorKm.equals(other.valorKm))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return descricao;
	}

	
	
	
	
	
	
}
