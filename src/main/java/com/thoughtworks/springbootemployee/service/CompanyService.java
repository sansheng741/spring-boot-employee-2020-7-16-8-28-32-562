package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    Company getCompany(int id);
    CompanyResponse getCompany2(int id);

    List<EmployeeResponse> getAllEmployeesOfCompany(int id);

    List<Company> pagingQueryCompanies(Pageable pageable);

    boolean addCompany(Company company);

    void deleteTheCompanyAllInfo(int id);

    void updateCompany(Company company);
}
