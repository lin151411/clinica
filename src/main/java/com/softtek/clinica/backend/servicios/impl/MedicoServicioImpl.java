package com.softtek.clinica.backend.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.clinica.backend.modelo.Medico;
import com.softtek.clinica.backend.repo.IGenericRepo;
import com.softtek.clinica.backend.repo.IMedicoRepo;
import com.softtek.clinica.backend.servicios.IMedicoServicio;

@Service
public class MedicoServicioImpl extends CRUDImpl<Medico, Integer> implements IMedicoServicio{

	@Autowired
	IMedicoRepo repo;

	@Override
	protected IGenericRepo<Medico, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}
	
	
	
}
