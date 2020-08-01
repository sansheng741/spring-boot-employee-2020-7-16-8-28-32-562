package com.thoughtworks.springbootemployee.Utils;

import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ck
 * @create 2020-08-01 10:30
 */
public class Converter {

    public static void beanListConversion(Object source,Object target){

    }

    public static List<EmployeeResponse> getEmployeeResponses(List<Employee> employeeList) {
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();

        for (Employee employee : employeeList) {
            EmployeeResponse employeeResponse = new EmployeeResponse();

            BeanUtils.copyProperties(employee, employeeResponse);
            employeeResponse.setCompanyName(employee.getCompany().getName());

            employeeResponseList.add(employeeResponse);
        }
        return employeeResponseList;
    }

    public static List<CompanyResponse> getCompanyResponses(List<Company> companyList) {
        List<CompanyResponse> companyResponseList = new ArrayList<>();
        for (Company company : companyList) {
            CompanyResponse companyResponse = new CompanyResponse();
            BeanUtils.copyProperties(company,companyResponse);

            companyResponseList.add(companyResponse);
        }
        return companyResponseList;
    }

}
