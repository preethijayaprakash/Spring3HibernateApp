package com.dineshonjava.dao;

import java.util.List;

import com.dineshonjava.model.Person;

/**
 * @author Dinesh Rajput
 *
 */
public interface PersonDao {
	
	public void addEmployee(Person employee);

	public List<Person> listEmployeess();
	
	public Person getEmployee(int empid);
	
	public void deleteEmployee(Person employee);
}
