package com.softtek.clinica.backend.controlador;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.softtek.clinica.backend.dto.PacienteDto;
import com.softtek.clinica.backend.exceptions.ModeloNotFoundException;
import com.softtek.clinica.backend.modelo.Paciente;
import com.softtek.clinica.backend.servicios.IPacienteServicio;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "http://localhost:4200")
public class PacienteControlador {
	
	@Autowired
	private IPacienteServicio servicio;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<PacienteDto>> listar() throws Exception{
		
		List<PacienteDto> lista = servicio.listar().stream().map(x -> mapper.map(x, PacienteDto.class))
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<PacienteDto>>(lista,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PacienteDto> listarPorId(@PathVariable("id") Integer id) throws Exception {
		
		Paciente p= servicio.listarPorId(id);
		
		if(p==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: "+id);
		}
		PacienteDto dtoResponse= mapper.map(p, PacienteDto.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}
	
//	@PostMapping
//	public ResponseEntity<PacienteDto> registrar(@Valid @RequestBody PacienteDto p) throws Exception {
//		
//		Paciente p1=mapper.map(p, Paciente.class);
//		PacienteDto dtoResponse= mapper.map(servicio.registrar(p1), PacienteDto.class);
//				
//		return new ResponseEntity<PacienteDto>(dtoResponse,HttpStatus.CREATED);
//	}
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody PacienteDto p) throws Exception {
		
		Paciente objeto= mapper.map(p, Paciente.class);
		Paciente obj= servicio.registrar(objeto);
		PacienteDto dtoResponse=mapper.map(obj, PacienteDto.class);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dtoResponse.getIdPaciente()).toUri();
		return ResponseEntity.created(location).build();
	}
	

	@PutMapping
	public ResponseEntity<PacienteDto> modificar(@Valid @RequestBody PacienteDto p) throws Exception {
		
		Paciente pacienteRequest= mapper.map(p, Paciente.class);
		Paciente pacienteConsultado= servicio.listarPorId(pacienteRequest.getIdPaciente());
		
		if(pacienteConsultado==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: "+pacienteRequest.getIdPaciente());
		}
		
		Paciente BBDD= servicio.modificar(pacienteRequest);
		PacienteDto pacienteResponse= mapper.map(BBDD, PacienteDto.class);
		return new ResponseEntity<PacienteDto>(pacienteResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  eliminar(@PathVariable Integer id) throws Exception {
		Paciente pacienteConsultado= servicio.listarPorId(id);
		if(pacienteConsultado==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: "+id);
		}
		servicio.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<PacienteDto> listarHateoas(@PathVariable("id") Integer id) throws Exception{
		
		Paciente obj= servicio.listarPorId(id);
		
		if(obj==null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: "+id);
		}
		PacienteDto dto= mapper.map(obj, PacienteDto.class);
		EntityModel<PacienteDto> recurso= EntityModel.of(dto);
		WebMvcLinkBuilder link1= linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link1.withRel("paciente-link"));
		return recurso;
		
	}
}
