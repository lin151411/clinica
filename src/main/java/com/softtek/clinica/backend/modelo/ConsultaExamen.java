package com.softtek.clinica.backend.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "consultaExamen")
@IdClass(ConsultaExamenPK.class)
public class ConsultaExamen {
	
	@Id
	private Consulta consulta;
	
	@Id
	private Analitica examen;
	

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Analitica getExamen() {
		return examen;
	}

	public void setExamen(Analitica analitica) {
		this.examen = analitica;
	}
	

	
	

}
