package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompany(int id) {
        return companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    public List<Employee> getAllEmployeesOfCompany(int id) {
        Company company = getCompany(id);
        return company.getEmployeeList();
    }

    @Override
    public List<Company> pagingQueryCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable).getContent();
    }

    @Override
    public boolean addCompany(Company company) {
        companyRepository.save(company);
        return true;
    }

    @Override
    public void deleteTheCompanyAllInfo(int id) {
        Company company = getCompany(id);
        employeeRepository.findAll().stream()
                .filter(employee -> employee.getCompany().getCompanyID()==company.getCompanyID())
                .forEach(employee -> employee.setCompany(null));
        companyRepository.deleteById(id);
    }

    @Override
    public void updateCompany(Company company) {
        companyRepository.save(company);
    }


}
