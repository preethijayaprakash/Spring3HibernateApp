package com.dineshonjava.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dineshonjava.model.Person;

/**
 * @author Dinesh Rajput
 *
 */
@Repository("employeeDao")
public class PersonDaoImpl implements PersonDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addEmployee(Person employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
	}

	@SuppressWarnings("unchecked")
	public List<Person> listEmployeess() {
		return (List<Person>) sessionFactory.getCurrentSession().createCriteria(Person.class).list();
	}

	public Person getEmployee(int empid) {
		return (Person) sessionFactory.getCurrentSession().get(Person.class, empid);
	}

	public void deleteEmployee(Person employee) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM Employee WHERE empid = "+employee.getPersonId()).executeUpdate();
	}

}
