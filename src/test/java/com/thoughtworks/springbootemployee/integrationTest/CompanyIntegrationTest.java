package com.thoughtworks.springbootemployee.integrationTest;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    MockMvc mockMvc;

    @AfterEach
    void teardown() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }


    @Test
    void should_return_company_when_getCompany_given_company_id() throws Exception {
        Company company = new Company("oocl");
        Company savedCompany = companyRepository.save(company);

        mockMvc.perform(MockMvcRequestBuilders.get("/companies/" + savedCompany.getCompanyID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("oocl"));
    }

    @Test
    void should_return_all_employee_when_getAllEmployeesOfCompany_given_company_id() throws Exception {
        Company company = new Company("oocl");
        Company savedCompany = companyRepository.save(company);

        employeeRepository.save(new Employee("colin", "male", 22, savedCompany));


        mockMvc.perform(MockMvcRequestBuilders.get("/companies/" + company.getCompanyID() + "/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1));
    }

    @Test
    void should_return_all_company_when_queryCompanies() throws Exception {
        Company company1 = new Company("oocl");
        companyRepository.save(company1);
        Company company2 = new Company("tw");
        companyRepository.save(company2);

        mockMvc.perform(MockMvcRequestBuilders.get("/companies/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(2));

    }

    @Test
    void should_return_1_company_when_addCompany_given_company() throws Exception {

        String company = "{\"companyID\":1,\"name\":\"oocl\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/companies/")
                .contentType(MediaType.APPLICATION_JSON).content(company))
                .andExpect(status().isOk());
        assertEquals(1, companyRepository.findAll().size());
    }


    @Test
    void should_zero_company_when_deleteTheCompanyAllInfo_given_company_id() throws Exception {
        Company company = new Company("oocl");
        Company savedCompany = companyRepository.save(company);

        mockMvc.perform(MockMvcRequestBuilders.delete("/companies/" + company.getCompanyID()))
                .andExpect(status().isOk());
        assertEquals(0, companyRepository.findAll().size());
    }

    @Test
    void should_return_Modified_company_when_updateCompany_given_company() throws Exception {
        Company company = new Company("oocl");
        Company savedCompany = companyRepository.save(company);

        String companyInfo = "{\"companyID\":1,\"name\":\"tw\"}";


        mockMvc.perform(MockMvcRequestBuilders.put("/companies/")
                .contentType(MediaType.APPLICATION_JSON).content(companyInfo))
                .andExpect(status().isOk());
        assertEquals("tw", companyRepository.findById(1).get().getName());
    }
}
