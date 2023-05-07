package com.whs.instructor.smith.fwp10sbootpmsflv1.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.whs.instructor.smith.fwp10sbootpmsflv1.model.Person;


public interface PersonService {
	List<Person> getAllPersons();
	void savePerson(Person employee);
	Person getPersonById(Long id);
	void deletePersonById(Long id);
	Page<Person> listAll(int pageNo, int pageSize, String sortField, String sortDirection); 
}
