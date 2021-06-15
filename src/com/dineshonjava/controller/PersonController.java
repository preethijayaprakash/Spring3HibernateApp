package com.dineshonjava.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dineshonjava.bean.PersonBean;
import com.dineshonjava.model.Person;
import com.dineshonjava.service.PersonService;

/**
 * @author Dinesh Rajput
 *
 */
@Controller
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute("command") PersonBean personBean, 
			BindingResult result) {
		Person employee = prepareModel(personBean);
		personService.addEmployee(employee);
		return new ModelAndView("redirect:/add.html");
	}

	@RequestMapping(value="/employees", method = RequestMethod.GET)
	public ModelAndView listEmployees() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees",  prepareListofBean(personService.listEmployeess()));
		return new ModelAndView("employeesList", model);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addEmployee(@ModelAttribute("command")  PersonBean personBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees",  prepareListofBean(personService.listEmployeess()));
		return new ModelAndView("addEmployee", model);
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView editEmployee(@ModelAttribute("command")  PersonBean personBean,
			BindingResult result) {
		personService.deleteEmployee(prepareModel(personBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee", null);
		model.put("employees",  prepareListofBean(personService.listEmployeess()));
		return new ModelAndView("addEmployee", model);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(@ModelAttribute("command")  PersonBean personBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee", prepareEmployeeBean(personService.getEmployee(personBean.getId())));
		model.put("employees",  prepareListofBean(personService.listEmployeess()));
		return new ModelAndView("addEmployee", model);
	}
	
	private Person prepareModel(PersonBean personBean){
		Person employee = new Person();
		employee.setPersonAddress(personBean.getAddress());
		employee.setPersonAge(personBean.getAge());
		employee.setPersonName(personBean.getName());
		employee.setSalary(personBean.getSalary());
		employee.setPersonId(personBean.getId());
		personBean.setId(null);
		return employee;
	}
	
	private List<PersonBean> prepareListofBean(List<Person> employees){
		List<PersonBean> beans = null;
		if(employees != null && !employees.isEmpty()){
			beans = new ArrayList<PersonBean>();
			PersonBean bean = null;
			for(Person employee : employees){
				bean = new PersonBean();
				bean.setName(employee.getPersonName());
				bean.setId(employee.getPersonId());
				bean.setAddress(employee.getPersonAddress());
				bean.setSalary(employee.getSalary());
				bean.setAge(employee.getPersonAge());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	private PersonBean prepareEmployeeBean(Person employee){
		PersonBean bean = new PersonBean();
		bean.setAddress(employee.getPersonAddress());
		bean.setAge(employee.getPersonAge());
		bean.setName(employee.getPersonName());
		bean.setSalary(employee.getSalary());
		bean.setId(employee.getPersonId());
		return bean;
	}
}
