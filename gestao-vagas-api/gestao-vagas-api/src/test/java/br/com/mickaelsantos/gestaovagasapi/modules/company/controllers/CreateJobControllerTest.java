package br.com.mickaelsantos.gestaovagasapi.modules.company.controllers;


import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.mickaelsantos.gestaovagasapi.modules.company.dto.CreateJobDTO;
import br.com.mickaelsantos.gestaovagasapi.modules.company.models.Company;
import br.com.mickaelsantos.gestaovagasapi.modules.company.repositories.CompanyRepository;
import br.com.mickaelsantos.gestaovagasapi.modules.company.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest 
{
    
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Value("${security.token.secret}")
    private String secret;

    @BeforeEach
    public void setup()
    {
        mvc = MockMvcBuilders.webAppContextSetup(context)
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception 
    {
        var company = Company.builder()
        .description("DESCRIPTION_TEST")
        .email("contato.test@gmail.com")
        .password("123456789*")
        .username("USERNAME_TEST")
        .name("NAME_TEST")
        .build();

        var savedCompany = companyRepository.saveAndFlush(company);    

        var createdJobDTO = CreateJobDTO.builder()
        .benefits("BENEFIT_TEST")
        .description("DESCRIPTION_TEST")
        .level("LEVEL_TEST")
        .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtils.objectToJson(createdJobDTO))
        .header("Authorization",
             TestUtils.generateTestToken(savedCompany.getUuid(), secret))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);

    }

    @Test
    public void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception
    {
        var job = CreateJobDTO.builder()
        .benefits("BENEFIT_TEST")
        .description("DESCRIPTION_TEST")
        .level("LEVEL_TEST")
        .build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtils.objectToJson(job))
        .header("Authorization",
            TestUtils.generateTestToken(UUID.randomUUID(), secret))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}
 