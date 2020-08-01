package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.dto.EmployeesRequest;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getEmployees();

    EmployeeResponse getSpecificEmployee(int id);

    EmployeeResponse addEmployees(EmployeesRequest employeesRequest);

    void deleteEmployees(int id);

    List<EmployeeResponse> getMaleEmployees(String gender);

    void updateEmployees(Employee employee);

    List<EmployeeResponse> pagingQueryEmployees(Pageable pageable);
}
