package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;

//    @GetMapping("/companies/{id}")
//    private Company getCompany(@PathVariable("id") int id) {
//        return companyService.getCompany(id);
//    }

    @GetMapping("/companies/{id}")
    private CompanyResponse getCompany(@PathVariable("id") int id) {
        return companyService.getCompany2(id);
    }

//    @GetMapping("/companies/{id}/employees")
//    private List<Employee> getAllEmployeesOfCompany(@PathVariable("id") int id) {
//        return companyService.getAllEmployeesOfCompany(id);
//    }

    @GetMapping("/companies/{id}/employees")
    private List<EmployeeResponse> getAllEmployeesOfCompany(@PathVariable("id") int id) {
        return companyService.getAllEmployeesOfCompany(id);
    }

    @GetMapping(value = "/companies")
    private List<Company> queryCompanies(@PageableDefault(size = 2) Pageable pageable,@RequestParam(defaultValue = "false",required = false) boolean unpaged) {
        if(unpaged){
            return companyService.getAllCompanies();
        }
        return companyService.pagingQueryCompanies(pageable);
    }

    @PostMapping("/companies")
    private void addCompany(@RequestBody() Company company) {
        companyService.addCompany(company);
    }

    @DeleteMapping("/companies/{id}")
    private void deleteTheCompanyAllInfo(@PathVariable("id") int id) {
        companyService.deleteTheCompanyAllInfo(id);
    }

    @PutMapping("/companies")
    public void updateCompany(@RequestBody Company company) {
        companyService.updateCompany(company);
    }
}
