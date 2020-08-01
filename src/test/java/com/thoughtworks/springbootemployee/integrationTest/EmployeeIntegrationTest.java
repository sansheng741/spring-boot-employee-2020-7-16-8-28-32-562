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
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

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
    void should_return_all_employees_when_get_all_employees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("unpaged", "true"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_employee_when_getSpecificEmployee_given_employee_id() throws Exception {
        Company company = new Company("oocl");
        companyRepository.save(company);
        Employee employee = new Employee("colin", "male", 11, company);
        employeeRepository.save(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/" + employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("colin"));
    }

    @Test
    void should_return_employee_when_add_employee() throws Exception {
        Company company = new Company("oocl");
        companyRepository.save(company);
        String body = " {\n" +
                "                \"name\": \"chengcheng2222\",\n" +
                "                \"gender\": \"male\",\n" +
                "                \"age\": 54,\n" +
                "                \"companyId\":1\n" +
                "            }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());
        List<Employee> employees = employeeRepository.findAll();
        assertEquals(1, employees.size());
    }

    @Test
    void should_return_404_when_add_employee() throws Exception {
        String body = " {\n" +
                "                \"name\": \"chengcheng2222\",\n" +
                "                \"gender\": \"male\",\n" +
                "                \"age\": 54,\n" +
                "                \"companyId\":1\n" +
                "            }";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_zero_employee_when_deleteEmployees_given_employee_id() throws Exception {
        Company company = new Company("oocl");
        companyRepository.save(company);
        Employee employee = new Employee("colin", "male", 11, company);
        employeeRepository.save(employee);


        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/" + employee.getId()))
                .andExpect(status().isOk());

        assertEquals(0, employeeRepository.findAll().size());
    }


    @Test
    void should_return_Modified_employee_when_updateEmployees_given_employee() throws Exception {
        Company company = new Company("oocl");
        companyRepository.save(company);
        Employee employee = new Employee("colin", "male", 11, company);
        employeeRepository.save(employee);

        String body = " {\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"colin\",\n" +
                "                \"gender\": \"male\",\n" +
                "                \"age\": 22,\n" +
                "                \"companyId\":1\n" +
                "            }";


        mockMvc.perform(MockMvcRequestBuilders.put("/employees/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());

        assertEquals(22, employeeRepository.findById(1).get().getAge());

    }

    @Test
    void should_return_1_when_query_by_gender_given_2_employee() throws Exception {
        Company company = new Company("oocl");
        companyRepository.save(company);
        Employee employee = new Employee("colin", "male", 11, company);
        employeeRepository.save(employee);
        Employee employee1 = new Employee("cc", "female", 22, company);
        employeeRepository.save(employee1);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                .param("gender","male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1));


    }
}
