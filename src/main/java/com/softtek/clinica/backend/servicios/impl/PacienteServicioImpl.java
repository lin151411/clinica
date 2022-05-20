package com.softtek.clinica.backend.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.clinica.backend.modelo.Paciente;
import com.softtek.clinica.backend.repo.IGenericRepo;
import com.softtek.clinica.backend.repo.IPacienteRepo;
import com.softtek.clinica.backend.servicios.IPacienteServicio;

@Service
public class PacienteServicioImpl extends CRUDImpl<Paciente, Integer> implements IPacienteServicio {

	@Autowired
	private IPacienteRepo repo;

	@Override
	protected IGenericRepo<Paciente, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}
	
	
}
