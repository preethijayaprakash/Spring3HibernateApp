package com.dineshonjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dineshonjava.dao.PersonDao;
import com.dineshonjava.model.Person;

/**
 * @author Dinesh Rajput
 *
 */
@Service("employeeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addEmployee(Person employee) {
		personDao.addEmployee(employee);
	}
	
	public List<Person> listEmployeess() {
		return personDao.listEmployeess();
	}

	public Person getEmployee(int empid) {
		return personDao.getEmployee(empid);
	}
	
	public void deleteEmployee(Person employee) {
		personDao.deleteEmployee(employee);
	}

}
