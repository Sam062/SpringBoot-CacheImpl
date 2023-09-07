package base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import base.model.Employee;
import base.service.EmployeeService;

@RestController
public class Controller {

	@Autowired
	private EmployeeService service;

	@PostMapping("/save")
	public Employee save(@RequestBody Employee emp) {
		return service.save(emp);
	}

	@PostMapping("/update")
	public Employee update(@RequestBody Employee emp) {
		return service.update(emp.getEmpId(), emp);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		Employee employee = service.getEmployee(id);
		service.delete(employee);
		return "SUCCESS";
	}

	@GetMapping("/getEmp/{id}")
	public Employee getEmployee(@PathVariable Integer id) {
		return service.getEmployee(id);
	}

	@GetMapping("/getAll")
	public List<Employee> getAll() {
		return service.getAll();
	}

}
