package br.com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.exception.ResourceNotFoundException;
import br.com.springboot.model.Person;
import br.com.springboot.repository.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository repo;

	public Person create(Person p) {
		return repo.save(p);
	}

	public Person update(Person p) {
		Person newPerson = findById(p.getId());
		newPerson.setFirstName(p.getFirstName());
		newPerson.setLastName(p.getLastName());
		newPerson.setAddress(p.getAddress());
		newPerson.setGender(p.getGender());
		return repo.save(newPerson);
	}

	/*public Optional<Person> findBId(Long id) {
		return repo.findById(id);
	}*/
	
	public Person findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
	}

	public List<Person> findAll() {
		return repo.findAll();
	}

	public void delete(Long id) {
		Person delPerson = findById(id);
		repo.delete(delPerson);
	}

}
