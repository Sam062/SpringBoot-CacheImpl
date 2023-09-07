package base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import base.model.EmpRepo;
import base.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmpRepo empRepo;

	public Employee save(Employee e) {
		return empRepo.save(e);
	}

	@CachePut(value = "employees", key = "#empId")
	public Employee update(Integer empId, Employee e) {
		Employee e1 = empRepo.findById(empId).orElseThrow(() -> new RuntimeException("Employee not found"));
		e1.setName(e.getName());
		e1.setSalary(e.getSalary());
		return empRepo.save(e1);
	}

	@CacheEvict(value = "employees", key = "#empId", allEntries = true) // All entries means it will remove the
																		// associations as well
	public void delete(Employee e) {
		empRepo.delete(e);
	}

	public List<Employee> getAll() {
		return empRepo.findAll();
	}

	@Cacheable(value = "employees", key = "#empId")
	public Employee getEmployee(Integer empId) {
		return empRepo.findById(empId).orElse(null);
	}

}
