package com.softtek.clinica.backend.servicios.impl;

import java.util.List;
import java.util.Optional;

import com.softtek.clinica.backend.repo.IGenericRepo;

public abstract class CRUDImpl <T,ID>{

	protected abstract IGenericRepo<T, ID> getRepo();
	
	public T registrar(T t) throws Exception {
		return getRepo().save(t);
	}
	public T modificar (T t) throws Exception{
		return getRepo().save(t);
	}
	public List<T> listar() throws Exception {
		return getRepo().findAll();
	}
	public T listarPorId(ID id) throws Exception {

		Optional<T> o1=getRepo().findById(id);
		return o1.isPresent()?o1.get():null;
	}
	public void eliminar(ID id) throws Exception {
		
		getRepo().deleteById(id);
	}
}
