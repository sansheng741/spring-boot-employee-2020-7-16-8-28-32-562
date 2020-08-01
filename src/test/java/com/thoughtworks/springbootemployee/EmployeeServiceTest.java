package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.dto.EmployeesRequest;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    CompanyRepository companyRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Test
    void should_return_reponse_employee_when_add_employee_given_request_employee() {
        //given
        int companyId = 1;
        EmployeesRequest employeesRequest = new EmployeesRequest("employee 1","male",18,companyId);
        Company company = new Company();
        company.setCompanyID(companyId);
        company.setName("oocl");
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

        Employee employee = new Employee("employee 1","male",18,company);
        EmployeeResponse employeeResponse = new EmployeeResponse("employee 1","male",company.getName());
        when(employeeRepository.save(any())).thenReturn(employee);

        //when
        EmployeeResponse employeeResponseSaved = employeeService.addEmployees(employeesRequest);

        //then
        assertEquals(employeeResponse.toString(), employeeResponseSaved.toString());

    }
}
