package base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Employee update(Employee e) {
		return empRepo.save(e);
	}

	public void delete(Employee e) {
		empRepo.delete(e);
	}

	public List<Employee> getAll() {
		return empRepo.findAll();
	}

	@Cacheable(value = "employees", key="#empId")
	public Employee getEmployee(Integer empId) {
		return empRepo.findById(empId).orElse(null);
	}

}
