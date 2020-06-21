package br.com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.data.converter.DozerConverter;
import br.com.springboot.data.model.Person;
import br.com.springboot.data.vo.PersonVO;
import br.com.springboot.exception.ResourceNotFoundException;
import br.com.springboot.repository.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository repo;

	public PersonVO create(PersonVO p) {
		var entity = DozerConverter.parseObject(p, Person.class);
		var vo = DozerConverter.parseObject(repo.save(entity), PersonVO.class);
		return vo;
	}

	public PersonVO update(PersonVO p) {
		Person newPerson = repo.findById(p.getId()).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
		newPerson.setFirstName(p.getFirstName());
		newPerson.setLastName(p.getLastName());
		newPerson.setAddress(p.getAddress());
		newPerson.setGender(p.getGender());
		return DozerConverter.parseObject(repo.save(newPerson), PersonVO.class);
	}

	/*public Optional<Person> findBId(Long id) {
		return repo.findById(id);
	}*/
	
	public PersonVO findById(Long id) {
		var entity = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public List<PersonVO> findAll() {
		return DozerConverter.parseListObjects(repo.findAll(), PersonVO.class);
	}

	public void delete(Long id) {
		Person delPerson = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
		repo.delete(delPerson);
	}

}
