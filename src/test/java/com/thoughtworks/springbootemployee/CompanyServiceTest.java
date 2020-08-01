package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.impl.CompanyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    CompanyServiceImpl companyService;

    @Test
    void should_return_specify_company_when_get_company_given_company_id() {
        //given
        int companyID = 1;
        Company company = new Company();
        company.setCompanyID(companyID);
        Mockito.when(companyRepository.findById(companyID)).thenReturn(java.util.Optional.of(company));

        //when
        Company specifyCompany = companyService.getCompany(companyID);

        //then
        assertEquals(companyID, specifyCompany.getCompanyID());
    }

    @Test
    void should_return_true_when_add_a_company_given_a_company_request() {
        //given
        int companyId = 1;
        Company company = new Company();
        company.setCompanyID(companyId);
        Mockito.when(companyRepository.save(any())).thenReturn(company);

        CompanyRequest companyRequest = new CompanyRequest();
        companyRequest.setName("oocl");

        //when
        boolean result = companyService.addCompany(companyRequest);

        //then
        assertEquals(true, result);
    }

    @Test
    void should_return_companies_when_get_all_companies_given_a_company_repository() {

        List<Company> companies = new ArrayList<>();
        for(int index = 0; index < 2; index++){
            companies.add(new Company());
        }
        Mockito.when(companyRepository.findAll()).thenReturn(companies);

        //when
        List<CompanyResponse> companiesReslut = companyService.getAllCompanies();

        //then
        assertEquals(companies.size(), companiesReslut.size());

    }

}
