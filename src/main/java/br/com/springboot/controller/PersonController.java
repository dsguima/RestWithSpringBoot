package br.com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.Person;
import br.com.springboot.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	PersonServices service;
	
	@PostMapping
	public Person save(@RequestBody Person p) {
		return service.create(p);
	}

	@PutMapping
	public Person update(@RequestBody Person p) {

		return service.update(p);
	}

	@GetMapping("/{id}")
	public Person findBId(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@GetMapping
	public List<Person> findAll() {
		return service.findAll();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}