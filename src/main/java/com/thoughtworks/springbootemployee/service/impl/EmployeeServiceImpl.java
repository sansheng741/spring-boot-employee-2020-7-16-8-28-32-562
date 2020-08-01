package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.dto.EmployeesRequest;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getSpecificEmployee(int id) {
        return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public EmployeeResponse addEmployees(EmployeesRequest employeesRequest) {
        Company company = companyRepository.findById(employeesRequest.getCompanyId()).orElseThrow(CompanyNotFoundException::new);
        Employee employee = new Employee();
        employee.setName(employeesRequest.getName());
        employee.setAge(employeesRequest.getAge());
        employee.setGender(employeesRequest.getGender());
        employee.setCompany(company);

        Employee employeeSaved = employeeRepository.save(employee);

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setName(employeeSaved.getName());
        employeeResponse.setGender(employeeSaved.getGender());
        employeeResponse.setCompanyName(employeeSaved.getCompany().getName());
        return employeeResponse;
    }

    public void deleteEmployees(int id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> getMaleEmployees(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public void updateEmployees(Employee employee) {
        employeeRepository.save(employee);
    }


    public Page<Employee> pagingQueryEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}
