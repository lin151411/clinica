package com.softtek.clinica.backend.modelo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "consultas")
public class Consulta {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConsulta;
	
	@ManyToOne
	@JoinColumn(name="id_paciente", nullable = false, foreignKey = @ForeignKey(name="FK_consulta_paciente"))
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name="id_medico", nullable = false, foreignKey = @ForeignKey(name="FK_consulta_medico"))
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name="id_especialidad", nullable = false, foreignKey = @ForeignKey(name="FK_consulta_especialidad"))
	private Especialidad especialidad;
	
	@Column(name="fecha",nullable = false)
	private LocalDateTime fecha;
	
	
	@OneToMany(mappedBy = "consulta", cascade = {CascadeType.ALL},orphanRemoval = true)
	private List<DetalleConsulta> detalleConsulta;

}
