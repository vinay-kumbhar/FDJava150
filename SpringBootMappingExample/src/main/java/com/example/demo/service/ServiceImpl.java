package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class ServiceImpl implements ServiceI{

	@Autowired
	private DepartmentRepository dr;
	
	@Autowired
	private EmployeeRepository er;
	
	
	@Override
	public void addDepartment(Department department) {
		dr.save(department);
	}


	@Override
	public void addEmployeeWithDepartment(Employee employee,String dname) {
		Department department  =dr.findByDname(dname);
		  employee.setDepartment(department);
		  er.save(employee);
		
		
	}


	@Override
	public List<Employee> deletePerticularEmployee(int id) {
		   Employee e=er.findById(id).get(); 
		   System.out.println(e);
		   er.delete(e);
		return  er.findAll();
	}


	@Override
	public List<Employee> deleteAlData() {
	  List<Employee>elist= er.findAll();
	  er.deleteAll();
		return null;
	}


	@Override
	public List<Employee> getAllEmployeeUnderPerticularDeprtment(String name) {
		 Department d=dr.findByDname(name);
		 List<Employee>  elist=er.findByDepartment_Id(d.getId());
		return elist;
	}

}
